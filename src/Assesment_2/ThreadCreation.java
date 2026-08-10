package Assesment_2;

public class ThreadCreation {

    public static void main(String[] args) {
        BoundedPool pool = new BoundedPool(3);

        for(int i=0;i<5;i++){
            Thread thread = new Thread(()-> {
                try {
                    int conn = pool.acquire();
                    System.out.println(Thread.currentThread().getName() + " got" + conn);
                    Thread.sleep(2000);
                    pool.release(conn);
                    System.out.println(Thread.currentThread().getName() + "Released " + conn);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });

            thread.start();
        }
    }
}
