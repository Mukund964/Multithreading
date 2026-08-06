package ProducerConsumeProblem;

import java.util.LinkedList;
import java.util.Queue;

public class SharedResource{

    private final Queue<Integer> sharedBuffer;
    private final int bufferSize;

    public SharedResource(int bufferSize){
        sharedBuffer = new LinkedList<>();
        this.bufferSize = bufferSize;
    }

    public synchronized void produce(int item) throws Exception{
        while(sharedBuffer.size()==bufferSize){
            System.out.println("Buffer is Full, producer is waiting for consumer");
            wait();
        }
        sharedBuffer.add(item);
        System.out.println("Produced: " + item);
        //notifies the consumer that items are there to consume
        notify();
    }

    public synchronized int consume() throws Exception{
        while(sharedBuffer.isEmpty()){
            System.out.println("Consumer is waiting for the producer to produce");
            wait();
        }
        int item = sharedBuffer.poll();
        System.out.println("Consumed "+ item);
        notify();
        return item;
    }
}