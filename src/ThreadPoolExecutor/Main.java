package ThreadPoolExecutor;

import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) {

        ThreadPoolExecutor Executor = new ThreadPoolExecutor(2,4,2, TimeUnit.MINUTES, new ArrayBlockingQueue<>(2), new customThreadFactory(), new customRejectionHandler());

        // create tasks
        for(int i=1;i<=8;i++){

            // submit accepts a runnable
            Executor.submit( ()-> {
                System.out.println("Task Accepted by :" + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            });


        }
        //to shutdown a thread
        Executor.shutdown();
    }
}

// can created custom ThreadFactory or pass default threadfactory method
class customThreadFactory implements ThreadFactory{

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r);
        thread.setPriority(Thread.NORM_PRIORITY);
        thread.setDaemon(false);
        return thread;
    }
}

// use already present methods in executionhandler or override
class customRejectionHandler implements RejectedExecutionHandler{

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        System.out.println("Task Rejected " + executor.toString());
    }
}
