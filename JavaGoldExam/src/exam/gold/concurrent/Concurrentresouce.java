package exam.gold.concurrent;

public class Concurrentresouce {
    private static int i = 0;
    private static String string;
    private static Object lock = new Object();

    public static synchronized int getAndAdd() {
        int result = i;

        try {
            Thread.sleep(10);
            i++;
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static void pushStr(String atr1){
        synchronized(lock) {
            string = atr1;
            lock.notify();
        }
    }
    public static synchronized String popStr(){
        String result;
        synchronized(lock) {
            while(string == null){
                System.out.print("[wait]");
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    //
                }
            }
            result = string;
            string = null;
        }
        return result;
    }
}
