package hr.algebra.java2.thread;

import hr.algebra.java2.fightinggame1v1.MainGameScreen;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LoadMovesThread implements Runnable{
    @Override
    public void run() {
        while (true) {

            loadMove();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(MainGameScreen.class.getName()).log(
                        Level.SEVERE, null, ex);
            }
        }
    }

    public synchronized void loadMove() {
        try {
            System.out.println("Thread is loading new moves...");
            wait();
        } catch (InterruptedException ex) {
            Logger.getLogger(MainGameScreen.class.getName()).log(
                    Level.SEVERE, null, ex);
        }

        notifyAll();
    }
}
