package learning.springboot.web.app.lock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConditionMain {

    private static final Logger logger = LoggerFactory.getLogger(ConditionMain.class);
    static volatile boolean start = false;

    public static void main(String[] args) {
        final Lock lock = new ReentrantLock();
        final Condition condition = lock.newCondition();
        AtomicReference<String> text = new AtomicReference<>();
        CountDownLatch latch = new CountDownLatch(1);
        new Thread(() -> {
            logger.info("enter thread");
            while (!start) {
                Thread.yield();
            }
            try {
                lock.lock();
                TimeUnit.SECONDS.sleep(2);
                condition.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
                latch.countDown();
            }
            logger.info("exit thread");
        }).start();
        new Thread(() -> {
            logger.info("enter thread");
            try {
                lock.lock();
                start = true;
                condition.await();
                logger.info("获取到锁");
                text.set("abcd");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
            logger.info("exit thread");
        }).start();

        try {
            latch.await();
            System.out.println(text.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
