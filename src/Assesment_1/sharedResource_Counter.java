// shared resource here is our counter, this is op that we are trying to perform
package Assesment_1;

import java.util.concurrent.atomic.AtomicInteger;


public class sharedResource_Counter {

     AtomicInteger atomicCounter = new AtomicInteger(0);

    //using sync block critical section uses monitor lock on calling object but make it thread safe
    public void incrementCounter(){

            try {
                System.out.println(Thread.currentThread().getName() + " Entered Critical Section");

                for (int i = 0; i < 10000; i++) {
                    atomicCounter.incrementAndGet();
                }

                System.out.println("Critical Section End");
            } finally {
                System.out.println("finished Critical Section");
            }
        }

    public int getCounter(){
        return atomicCounter.get();
    }
}
