package exam.gold.concurrent;

public class ThreadSample {

    public static void main(String[] args) {
        Runnable r = () -> {
            System.out.println("Thread Running:" + Thread.currentThread().getId());
            for (int i = 0; i <= 10; i++) {
                try {
                    Thread.sleep(10);
                    int num = Concurrentresouce.getAndAdd();
                    System.out.println(" * [" + num + "]:" + Thread.currentThread().getId());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Runnable run2 = () -> {
            for (int i = 0; i <= 10; i++) {
                try {
                    Thread.sleep(50);
                    Concurrentresouce.pushStr("number[" + i + "]");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        };

        Runnable run3 = () -> {
            for (int i = 0; i <= 10; i++) {
                try {
                    Thread.sleep(10);
                    System.out.println("[" + Concurrentresouce.popStr() + "]:" + Thread.currentThread().getId());

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        };

        Thread th1 = new Thread(r);
        Thread th2 = new Thread(r);
        //th1.start();
        //th2.start();

        Thread th3 = new Thread(run2);
        Thread th4 = new Thread(run3);
        th3.start();
        th4.start();

    }

}
