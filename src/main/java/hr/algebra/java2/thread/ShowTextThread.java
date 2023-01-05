package hr.algebra.java2.thread;

import hr.algebra.java2.fightinggame1v1.MainGameScreen;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ShowTextThread implements Runnable{

    @Override
    public void run() {
        while (true) {

            showText();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(MainGameScreen.class.getName()).log(
                        Level.SEVERE, null, ex);
            }
        }
    }

    public synchronized void showText() {
        try {
            System.out.println("Thread is adding a move to a list...");
            wait();
        } catch (InterruptedException ex) {
            Logger.getLogger(MainGameScreen.class.getName()).log(
                    Level.SEVERE, null, ex);
        }

        notifyAll();
    }
}
