package org.example.thirdTask;

import java.util.EnumMap;
import java.util.Map;

public class Faction implements Runnable {

    private final Map<RobotParts, Integer> partsInventory = new EnumMap<>(RobotParts.class);
    private       int                      partsCollected = 0;
    private       int    robots         = 0;
    private final String name;

    public Faction(String name) {
        this.name = name;
        init();
    }

    private void init(){
        for (RobotParts type : RobotParts.values()) {
            partsInventory.put(type, 0);
        }
        System.out.println(name+" Faction is initialized!");
    }

    public int getRobotsAmount(){
        return robots;
    }

    @Override
    public void run(){
        while(Skynet.day<=Skynet.DAY_LIMIT){
            synchronized (Skynet.lock){
                while (Skynet.isDay){
                    try{
                        Skynet.lock.wait();
                }
                catch (InterruptedException e){
                    return;
                }
            }}

            while (partsCollected<5 && !FactoryObject.isEmpty()) {
                RobotParts partTaken = FactoryObject.capturePart();
                if (partTaken != null) {
                    partsInventory.put(partTaken, partsInventory.get(partTaken) + 1);
                    partsCollected++;
                }
            }

            createRobot();
            System.out.println(name+" Faction current parts: " + partsInventory+ ". "+name+" faction current robots: "+robots);
            partsCollected = 0;
            synchronized (Skynet.lock){

                Skynet.factionsFinished++;
                if (Skynet.factionsFinished ==2) {
                    Skynet.isDay = true;
                    Skynet.day++;
                    System.out.println("Day is done!");
                    Skynet.lock.notifyAll();
                }
                else{
                    try{
                        Skynet.lock.wait();
                    } catch (InterruptedException e) {
                        return;
                    }
                }
            }
        }
    }

    private synchronized void createRobot(){
        while(partsInventory.entrySet().stream().allMatch(entry -> entry.getValue() >= 1)){
            partsInventory.entrySet().forEach(entry -> entry.setValue(entry.getValue() - 1));
            robots++;
        }
    }



    }

