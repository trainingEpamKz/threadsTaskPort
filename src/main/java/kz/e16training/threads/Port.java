package kz.e16training.threads;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Port.
 *
 */
public class Port {
    static Logger portlog = LogManager.getLogger(Port.class.getName());
    private List<Dock> docks;
    private BlockingQueue<Ship> shipsInPort;
    private int numberOfShips;

    public Port(int numberOfDocks, int numberOfShips) {
        this.numberOfShips = numberOfShips;
        this.docks = new ArrayList<>();
        this.shipsInPort = new ArrayBlockingQueue<>(numberOfShips);
        for (int i = 0; i < numberOfDocks; i++) {
            docks.add(new Dock("Dock #" + i, shipsInPort));
        }
    }

    public void addShipsToPort() {
        for (int i = 0; i < numberOfShips; i++) {
            try {
                shipsInPort.put(new Ship("Ship #" + i));
            } catch (InterruptedException e) {
                e.printStackTrace();
                portlog.error(e.getMessage());
            }
        }
    }

    public void startDocking() {
        for (Dock dock : docks) {
            new Thread(dock).start();
        }
    }
}
