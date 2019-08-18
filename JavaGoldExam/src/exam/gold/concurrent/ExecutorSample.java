package exam.gold.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorSample {
    public static void main(String[] args) throws InterruptedException {
        //従来のRunnableクラスも使える
        //値は返却しない
        Runnable run1 = () -> {
            System.out.println("Thread Running:" + Thread.currentThread().getId());
            for (int i = 0; i <= 10; i++) {
                try {
                    Thread.sleep(10);
                    int num = Concurrentresouce.getAndAdd();
                    System.out.println(" * [" + num + "]:" + Thread.currentThread().getId());
                } catch (InterruptedException e) {
                    System.out.println("タスクが中止された");
                }
            }
        };

        //値を返却することができるCallableクラス
        Callable<Integer> call1 = () -> {
            System.out.println("Thread Running:" + Thread.currentThread().getId());
            int result = 0;
            for (int i = 0; i <= 10; i++) {
                try {
                    Thread.sleep(10);
                    int num = Concurrentresouce.getAndAdd();
                    result += num;
                    System.out.println(" * [" + num + "]:" + Thread.currentThread().getId());
                } catch (InterruptedException e) {
                    System.out.println("タスクが中止された");
                }
            }
            return result;
        };

        //シングルスレッドで動くやつ。実行中のタスクが完了するまで
        //次のタスクは待機する
        ExecutorService pool = Executors.newSingleThreadExecutor();
        //引数で指定した数だけスレッド処理を実行できる
        ExecutorService pool2 = Executors.newFixedThreadPool(5);
        Future<?> future1 = pool.submit(run1);
        future1.cancel(true);
        Future<?> future2 = pool.submit(run1);

        //こいつはfuture2でうけとるタスクがかんりょうするまで待機する
        Future<Integer> future3 = pool.submit(call1);

        try {
            //getでタスクが完了するまで待つ
            future2.get();
            int result = future3.get();
            System.out.println("Callable Result:" + result);
        } catch (ExecutionException e) {
        }

        List<Future<Integer>> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            //10個タスクを入れる。
            //5スレッドがMAXなので、5個は実行待機状態になってる。
            Future<Integer> future = pool2.submit(call1);
            list.add(future);
        }

        for (Future<Integer> ft : list) {
            try {
                //getでタスクが完了するまで待つ
                int result = ft.get();
                System.out.println("Callable Rsult:" + result);
            } catch (ExecutionException e) {
            }
        }

        //これやらないとプログラムが終了しない
        pool.shutdown();
        pool2.shutdown();

    }
}
