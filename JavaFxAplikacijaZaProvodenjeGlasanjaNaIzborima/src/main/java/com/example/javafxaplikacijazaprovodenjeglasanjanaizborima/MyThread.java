package com.example.javafxaplikacijazaprovodenjeglasanjanaizborima;

import java.util.Timer;
import java.util.TimerTask;

public class MyThread extends Thread{

    private Timer timer;

    public void run() {
        // Create a new Timer object
        timer = new Timer();

        // Create a new TimerTask object that will run every 1 second
        TimerTask task = new TimerTask() {
            public void run() {
                System.out.println("Ovo je thread primjer");
            }
        };

        // Schedule the TimerTask to run every 1 second
        timer.schedule(task, 0, 10000);

        // Wait for the timer to finish
        try {
            Thread.sleep(5000); // Wait for 5 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Stop the timer
        timer.cancel();
}
}
