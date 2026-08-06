package Assesment_1;

import java.util.ArrayList;

public class counterThread {
    public static void main(String[] args) {

        sharedResource_Counter incCountObj = new sharedResource_Counter();

        ArrayList<Thread>threads = new ArrayList<>();

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

        System.out.println("Final Counter = " + incCountObj.getCounter());
    }
}