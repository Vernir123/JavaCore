package org.example.thirdtask;

import java.util.EnumMap;
import java.util.Random;

public class Factory {

  private static final EnumMap <RobotPart, Integer> partsManufactured = new EnumMap <>(
      RobotPart.class);
  private static final Random rand = new Random();
  private static final int MAX_MANUFACTURED = 10;

  static {
    for (RobotPart type : RobotPart.values()) {
      partsManufactured.put(type , 0);
    }
    System.out.println("Factory is working!");
  }


  public static void run () {
    while (Skynet.day < Skynet.DAY_LIMIT) {
      waitForFactions();
      startFactory();
      finishWork();
    }
  }

  private static void waitForFactions () {
    synchronized (Skynet.LOCK) {
      while (!Skynet.isDay) {
        try {
          Skynet.LOCK.wait();
        } catch (InterruptedException e) {
          return;
        }
      }
    }
  }

  private static void startFactory () {
    System.out.println("+++++++++++++++++++++++++");
    System.out.println("Day " + Skynet.day + " started!");
    System.out.println("+++++++++++++++++++++++++");

    int partsToday = rand.nextInt(MAX_MANUFACTURED + 1);

    System.out.println("Factory manufacturing |" + partsToday + "| parts...");

    for (int i = partsToday; i > 0; i--) {
      producePart();
    }

    System.out.println("Factory manufacturing parts done!");
    System.out.println("Day: " + Skynet.day + " Manufactured parts: " + partsManufactured);
    System.out.println("----------------------");
  }

  private static void finishWork () {
    synchronized (Skynet.LOCK) {
      Skynet.LOCK.notifyAll();
      Skynet.factionsFinished = 0;
      Skynet.isDay = false;
    }
  }

  private static void producePart () {
    int partRand = rand.nextInt(RobotPart.values().length);
    RobotPart part = RobotPart.values()[partRand];
    partsManufactured.put(part , partsManufactured.get(part) + 1);
  }

  public static boolean isEmpty () {
    return partsManufactured.entrySet().stream().allMatch(e -> e.getValue() == 0);
  }

  public static synchronized RobotPart capturePart () {
    if (isEmpty()) {
      return null;
    }
    RobotPart type;
    do {
      type = RobotPart.values()[rand.nextInt(RobotPart.values().length)];
    } while (partsManufactured.get(type) == 0);
    partsManufactured.put(type , partsManufactured.get(type) - 1);
    return type;


  }

}
