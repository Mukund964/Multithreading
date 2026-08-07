// shared resource here is our counter, this is op that we are trying to perform
package Assesment_1;

import java.util.concurrent.locks.ReentrantLock;

public class sharedResource_Counter {
    static int counter = 0;
    static ReentrantLock lock = new ReentrantLock();

    //using sync block critical section uses monitor lock on calling object but make it thread safe
    public void incrementCounter(){
        lock.lock();
        try{
            System.out.println(Thread.currentThread().getName() + " Entered Critical Section");

            for(int i=0;i<10000;i++){
                counter++;
            }

            System.out.println("Critical Section End");
        }finally {
            lock.unlock();
        }
    }

    public int getCounter(){
        return counter;
    }
}
