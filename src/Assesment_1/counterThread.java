package Assesment_1;

import java.util.ArrayList;

public class counterThread {
    public static void main(String[] args) {

        sharedResource_Counter incCountObj = new sharedResource_Counter();

        ArrayList<Thread>threads = new ArrayList<>();
        long start = System.nanoTime();
        //creation of threads and starting them
        for(int i = 0; i < 10; i++) {
            threads.add(new Thread(incCountObj::incrementCounter));
            threads.get(i).start();
        }

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
        System.out.println("Final Counter = " + incCountObj.getCounter());
    }
}