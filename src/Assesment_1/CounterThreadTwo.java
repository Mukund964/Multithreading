package Assesment_1;

public class CounterThreadTwo implements Runnable{

    private final sharedResource_Counter counterObj;

    CounterThreadTwo(sharedResource_Counter CounterObj){
        this.counterObj = CounterObj;
    }

    @Override
    public void run() {
        counterObj.incrementCounter();
    }

    public void createThreads() throws InterruptedException {
        Thread t1 = new Thread(this);
        Thread t2 = new Thread(this);
        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }
}


