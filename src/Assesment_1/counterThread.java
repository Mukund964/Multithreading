package Assesment_1;

import java.util.ArrayList;

public class counterThread {
    public static void main(String[] args) throws InterruptedException {

        sharedResource_Counter incCountObj = new sharedResource_Counter();

        sharedResource_Counter counterObj = new sharedResource_Counter();

        CounterThreadTwo obj1 = new CounterThreadTwo(counterObj);

        ArrayList<Thread>threads = new ArrayList<>();
        long start = System.nanoTime();
        //creation of threads and starting them
        for(int i = 0; i < 10; i++) {
            threads.add(new Thread(incCountObj::incrementCounter));
            threads.get(i).start();
        }

        //creating thread using another obj
        obj1.createThreads();

        for(Thread t : threads){
            try{
                // waiting for all threads to finish
                t.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        long end = System.nanoTime();

        System.out.println("Time Taken: " + (end-start)/ 1_000_000.0 + "ms");

        //static counter will show both obj access the sync section
        System.out.println("Final Counter1 = " + incCountObj.getCounter());

        // when we have 2 seprate obj diff counters will run sync block correctly
//        System.out.println("Final Counter2 = " + counterObj.getCounter());
    }
}