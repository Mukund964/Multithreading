package Futures;

import java.util.concurrent.*;

public class FutureExample {

    public static void main(String[] args)  {

        ThreadPoolExecutor executor = new ThreadPoolExecutor(1,1,1,TimeUnit.HOURS, new ArrayBlockingQueue<>(10), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());

        //runnable
        Future<?> futureObj = executor.submit(()->{
            try {
                Thread.sleep(7000);
                System.out.println("Thread Executing Task");
            } catch (Exception ignored) {

            }
        });

        // callable
        Future<Integer> futureObj2 = executor.submit( () -> {
            System.out.println("Callable returns some values");
            return 10;
        });

        System.out.println("Is done :" + futureObj.isDone());

        try{
            futureObj.get(2,TimeUnit.SECONDS);
        } catch (Exception ignored) {

        }

        try{
            futureObj.get();
        }catch(InterruptedException | ExecutionException ignored){

        }


        System.out.println("Is done :" + futureObj.isDone());

    }


}
