package kz.e16training.threads;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Running.
 *
 */
public class Run {
    static Logger log = LogManager.getLogger(Run.class.getName());
    public static void main(String[] args) {
        log.info("started");
        Port port = new Port(4, 30);
        port.addShipsToPort();
        port.startDocking();
        log.info("ended");
    }
}
