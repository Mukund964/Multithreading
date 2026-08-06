// shared resource here is our counter, this is op that we are trying to perform
package Assesment_1;

public class sharedResource_Counter {
    int counter = 0;
    public void incrementCounter(){

        System.out.println(Thread.currentThread().getName() + " Entered Critical Section");
        for(int i=0;i<10000;i++){
            counter++;
        }

        System.out.println("Critical Section End");

    }

    public int getCounter(){
        return counter;
    }
}
