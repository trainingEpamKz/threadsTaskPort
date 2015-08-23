package kz.e16training.threads;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Random;
import java.util.concurrent.BlockingQueue;

/**
 * Dock.
 *
 */
public class Dock implements Runnable {
    static Logger docklog = LogManager.getLogger(Dock.class.getName());
    private static final int MAX_COUNT_OF_SHIPS = 100;
    private static volatile int countOfShips;
    private String nameOfDock;
    private BlockingQueue<Ship> shipsInPort;

    public Dock(String nameOfDock, BlockingQueue<Ship> shipsInPort) {
        this.nameOfDock = nameOfDock;
        this.shipsInPort = shipsInPort;
    }

    private void docking(Ship shipInDock) {
        System.out.println(shipInDock + " is arrived in " + nameOfDock);
    }

    private void undocking(Ship shipInDock) {
        System.out.println(shipInDock + " left " + nameOfDock);
    }

    @Override
    public void run() {
        while (countOfShips++ < MAX_COUNT_OF_SHIPS) {
            try {
                Thread.sleep(new Random().nextInt(3000));
                Ship shipInDock = shipsInPort.take();
                docking(shipInDock);
                shipInDock.changeStateOfCargo();
                undocking(shipInDock);
                shipsInPort.put(shipInDock);
            } catch (InterruptedException e) {
                e.printStackTrace();
                docklog.error(e.getMessage());
            }
        }
    }

    @Override
    public String toString() {
        return nameOfDock;
    }
}
