package Assesment_2;

import java.util.ArrayDeque;
import java.util.Deque;

public class BoundedPool {
    /*
    1. Class will have a pool(N no of available connection pool kind of hikari
    2. accquire method will accqire that connection pool
    3. release method will put the connection back to pool

     Ex : 3 Connection are available
     3 threads can get these connections
     but 4th one will wait until one become available
        */

    private final Deque<Integer> available = new ArrayDeque<>();

    public BoundedPool(int n){
        for(int i=0;i<n;i++){
            available.add(i);
        }
    }

    public synchronized int acquire() throws InterruptedException {
        while(available.isEmpty()){
            System.out.println("No Connection pools are available for " + Thread.currentThread().getName() + "Waiting for pools");
            wait();
        }

        return available.pollFirst();
    }

    public synchronized void release(int poolNo){
        available.add(poolNo);
        System.out.println(poolNo + " Available back to the connection pool");
        notifyAll();
    }
}
