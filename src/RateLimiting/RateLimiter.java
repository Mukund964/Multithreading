package RateLimiting;



import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class RateLimiter {

    ReentrantLock lock = new ReentrantLock();
    Condition conditionObj = lock.newCondition();
     int counter = 0;

    public RateLimiter(){
        ScheduledExecutorService resetter = Executors.newScheduledThreadPool(1);
        resetter.scheduleAtFixedRate(()->{
            lock.lock();
            try{
                counter = 0;
                conditionObj.signalAll();
            }finally {
                lock.unlock();
            }

        },0,1, TimeUnit.SECONDS);
    }

    void acquire() throws InterruptedException {
        lock.lock();
        try{
            while(counter >= 5) {
                conditionObj.await();
                System.out.println("More than 5 Request");
            }

            counter++;
        }finally {
            lock.unlock();
        }
    }



}
