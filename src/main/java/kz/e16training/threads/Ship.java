package kz.e16training.threads;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Random;

/**
 * Ship.
 *
 */
public class Ship {
    static Logger shiplog = LogManager.getLogger(Ship.class.getName());
    private String nameOfShip;
    private boolean stateOfCargo;

    public Ship(String nameOfShip) {
        this.nameOfShip = nameOfShip;
        stateOfCargo = new Random().nextBoolean();
    }

    private void loading() {
        System.out.println(nameOfShip + " start loading");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            shiplog.error(e.getMessage());
        }
        System.out.println(nameOfShip + " end loading");
    }

    private void unloading() {
        System.out.println(nameOfShip + " start unloading");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            shiplog.error(e.getMessage());
        }
        System.out.println(nameOfShip + " end unloading");
    }

    public void changeStateOfCargo() {
        if (stateOfCargo) {
            if (new Random().nextBoolean()) {
                loading();
            } else {
                unloading();
            }
        } else {
            if (new Random().nextBoolean()) {
                unloading();
                loading();
            } else {
                loading();
            }
        }
    }

    @Override
    public String toString() {
        return nameOfShip;
    }

}
