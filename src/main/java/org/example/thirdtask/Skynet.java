package org.example.thirdtask;

public class Skynet {

  public static final Object LOCK = new Object();
  public static final int DAY_LIMIT = 100;
  public static volatile boolean isDay = true;
  public static volatile int day = 0;
  public static volatile int factionsFinished = 0;

  public static void startSimulation () {

    Faction wednesday = new Faction("Wednesday");
    Faction world = new Faction("World");

    Thread factoryThread = new Thread(Factory::run , "FactoryThread");
    Thread factionThread1 = new Thread(wednesday , "WednesdayFaction");
    Thread factionThread2 = new Thread(world , "WorldFaction");

    factoryThread.start();
    factionThread1.start();
    factionThread2.start();

    try {
      factoryThread.join();
      factionThread1.join();
      factionThread2.join();
    } catch (InterruptedException e) {
      return;
    }

    System.out.println("---------------------------------------------");
    System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
    System.out.println("---------------------------------------------");

    if (wednesday.getRobotsAmount() > world.getRobotsAmount()) {
      System.out.println("Wednesday Wins!");
    } else if (wednesday.getRobotsAmount() < world.getRobotsAmount()) {
      System.out.println("World Wins!");
    } else {
      System.out.println("TIE");
    }

    System.out.println("---------------------------------------------");
    System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
    System.out.println("---------------------------------------------");


  }


}
