package ProducerConsumeProblem;

public class ProducerConsumer {

    public static void main(String[] args){

        SharedResource sharedBuffer = new SharedResource(3);

        //producer Thread using lambda expression
        Thread producerThread = new Thread(()->{
            try{
                for(int i=1;i<=6;i++){
                    sharedBuffer.produce(i);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        Thread consumerThread = new Thread(()-> {
           try{
               for(int i=1;i<=6;i++){
                   sharedBuffer.consume();
               }
           } catch (Exception e) {
               throw new RuntimeException(e);
           }
        });

        producerThread.start();
        consumerThread.start();
    }
}
