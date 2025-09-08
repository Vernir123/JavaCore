package org.example.thirdtask;

import java.util.EnumMap;
import java.util.Map;

public class Faction implements Runnable {

  private final Map <RobotPart, Integer> partsInventory = new EnumMap <>(RobotPart.class);
  private final String name;
  private int partsCollected = 0;
  private int robots = 0;

  public Faction (String name) {
    this.name = name;
    init();
  }

  private void init () {
    for (RobotPart type : RobotPart.values()) {
      partsInventory.put(type , 0);
    }
    System.out.println(name + " Faction is initialized!");
  }

  public int getRobotsAmount () {
    return robots;
  }

  @Override
  public void run () {
    while (Skynet.day <= Skynet.DAY_LIMIT) {
      waitForFactory();
      collectPart();
      createRobot();
      finishDay();
    }
  }

  private void waitForFactory () {
    synchronized (Skynet.LOCK) {
      while (Skynet.isDay) {
        try {
          Skynet.LOCK.wait();
        } catch (InterruptedException e) {
          return;
        }
      }
    }
  }

  private void finishDay () {
    partsCollected = 0;
    synchronized (Skynet.LOCK) {
      Skynet.factionsFinished++;
      if (Skynet.factionsFinished == 2) {
        Skynet.isDay = true;
        Skynet.day++;
        System.out.println("Day is done!");
        Skynet.LOCK.notifyAll();
      } else {
        try {
          Skynet.LOCK.wait();
        } catch (InterruptedException _) {
        }
      }
    }
  }

  private void collectPart () {
    int MAX_PARTS_COLLECT = 5;
    while (partsCollected < MAX_PARTS_COLLECT && !Factory.isEmpty()) {
      RobotPart partTaken = Factory.capturePart();
      if (partTaken != null) {
        partsInventory.put(partTaken , partsInventory.get(partTaken) + 1);
        partsCollected++;
      }
    }
  }

  private void createRobot () {
    while (partsInventory.entrySet().stream().allMatch(entry -> entry.getValue() >= 1)) {
      partsInventory.entrySet().forEach(entry -> entry.setValue(entry.getValue() - 1));
      robots++;
    }
    System.out.println(name + " Faction current parts: " + partsInventory + ". " + name
        + " faction current robots: " + robots);
  }
}

