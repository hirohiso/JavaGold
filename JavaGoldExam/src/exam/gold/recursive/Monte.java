package exam.gold.recursive;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

public class Monte {

    //円周率をモンテカルロ法で計算するプログラム
    //並列処理版
    public static void main(String[] args) {
        long count = 1000000;
        ForkJoinTask<Long> task = new RecursiveMonte(count);
        ForkJoinPool pool = new ForkJoinPool();
        long result = pool.invoke(task);
        System.out.println((double)result/count * 4D);
    }
}
