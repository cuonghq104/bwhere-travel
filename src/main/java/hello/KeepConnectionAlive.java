package hello;

import control.common.DatabaseConnector;

import javax.xml.crypto.Data;
import java.util.concurrent.TimeUnit;

public class KeepConnectionAlive extends Thread {

    @Override
    public void run() {
        while (true) {
//            System.out.println("Thread is running");
            DatabaseConnector.keepConnection();
            try {
                TimeUnit.MINUTES.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
