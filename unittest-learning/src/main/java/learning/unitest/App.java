package learning.unitest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hello world!
 */
public class App {

    private static final Logger logger = LoggerFactory.getLogger(App.class);

    private final ReentrantLock lock = new ReentrantLock();

    private int i = 0;

    public void addI() {
        lock.lock();
        logger.info("addI getHoldCount:\t" + lock.getHoldCount());
        lock.lock();
        logger.info("addI getHoldCount:\t" + lock.getHoldCount());
        i++;
        lock.unlock();
        logger.info("addI getHoldCount:\t" + lock.getHoldCount());
        lock.unlock();
    }

    public void minusI() {
        lock.lock();
        logger.info("minusI getHoldCount:\t" + lock.getHoldCount());
        lock.lock();
        logger.info("minusI getHoldCount:\t" + lock.getHoldCount());
        i--;
        lock.unlock();
        logger.info("minusI getHoldCount:\t" + lock.getHoldCount());
        lock.unlock();
    }


    public static void main(String[] args) {
        App app = new App();
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            executorService.submit(app::addI);
            executorService.submit(app::minusI);
        }
        System.out.printf("");

    }
}
