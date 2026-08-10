package Assesment_2;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.Semaphore;

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
    private Semaphore sm;

    public BoundedPool(int n){
        sm = new Semaphore(n);
        for(int i=0;i<n;i++){
            available.add(i);
        }
    }

    public int acquire() throws InterruptedException {
        sm.acquire(); // handle sync for entering this acquire
        synchronized (available){
            return available.pollFirst();
        }
    }

    public  void release(int poolNo){
        synchronized (available){
            available.addLast(poolNo);
        }
        sm.release(); // replaced notifyAll
    }
}
