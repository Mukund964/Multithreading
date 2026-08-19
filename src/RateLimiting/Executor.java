package RateLimiting;

import java.util.ArrayList;
import java.util.concurrent.*;

public class Executor {



    public static void main(String[] args) {
        long start = System.nanoTime();
         RateLimiter rateLimiter = new RateLimiter();
         ExecutorService poolExecutorObj = Executors.newFixedThreadPool(50);

         ArrayList<Future<?>> futureObjs = new ArrayList<>(100);


        for(int i=0;i<50;i++) {
            int currTask = i;
            futureObjs.add(poolExecutorObj.submit(() -> {
                try {
                    rateLimiter.acquire();
                } catch (InterruptedException e) {
                    System.out.println("exception");
                }
                System.out.println("Task " + currTask + " Taken by " + Thread.currentThread().getName());
                 try {
                     Thread.sleep(1000);
                 } catch (InterruptedException ignored) {
                     System.out.println("Process it");
                 }

             }));
        }
            try{
                for(int i=0;i<50;i++){
                    futureObjs.get(i).get();
                }

            } catch (ExecutionException | InterruptedException ignored) {

            }
        long end = System.nanoTime();
        System.out.println("Total time: " + (end - start) / 1e9 + " seconds");
        
    }

}
