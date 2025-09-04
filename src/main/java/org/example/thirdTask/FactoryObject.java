package org.example.thirdTask;

import java.util.EnumMap;
import java.util.Random;

public class FactoryObject {

    private static final EnumMap<RobotParts, Integer> partsManufactured =new EnumMap<>(RobotParts.class);
    private static final Random rand             = new Random();
    private static final int    MAX_MANUFACTURED = 10;

    static{
        for (RobotParts type : RobotParts.values()) {
            partsManufactured.put(type, 0);
        }
        System.out.println("Factory is working!");
    }

    public static void run(){
        while(Skynet.day < Skynet.DAY_LIMIT) {
            synchronized (Skynet.lock) {
                while (!Skynet.isDay) {
                    try {
                        Skynet.lock.wait();
                    } catch (InterruptedException e) {
                        return;
                    }
                }
            }

            System.out.println("+++++++++++++++++++++++++");
            System.out.println("Day "+Skynet.day+" started!");
            System.out.println("+++++++++++++++++++++++++");

            int partsToday = rand.nextInt(MAX_MANUFACTURED+1);
            System.out.println("Factory manufacturing |"+partsToday+"| parts...");
            for(int i = partsToday; i>0; i--) {
                producePart();
                }
            System.out.println("Factory manufacturing parts done!");
            System.out.println("Day: "+ Skynet.day+ " Manufactured parts: " + partsManufactured);
            System.out.println("----------------------");

            synchronized (Skynet.lock) {
                Skynet.factionsFinished=0;
                Skynet.isDay = false;
                Skynet.lock.notifyAll();
            }
        }
    }

    private static synchronized void producePart(){
        int partRand = rand.nextInt(RobotParts.values().length);
        RobotParts part = RobotParts.values()[partRand];
        partsManufactured.put(part, partsManufactured.get(part)+1);
    }
    public static synchronized boolean isEmpty() {
        return partsManufactured.entrySet().stream().allMatch(e -> e.getValue() == 0);
    }


    public static synchronized RobotParts capturePart() {
        if (isEmpty()) return null;
        RobotParts type;
        do {
            type = RobotParts.values()[rand.nextInt(RobotParts.values().length)];
        } while (partsManufactured.get(type) == 0);
        partsManufactured.put(type, partsManufactured.get(type) - 1);
        return type;


    }

}
