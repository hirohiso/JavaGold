package exam.gold.recursive;

import java.util.concurrent.RecursiveTask;

public class RecursiveMonte extends RecursiveTask<Long> {

    private long tryCount = 0;
    private final long TRY_LIMIT = 100;

    public RecursiveMonte(long tryCount) {
        this.tryCount = tryCount;
    }

    @Override
    protected Long compute() {
        long resut = 0;
        if (this.tryCount < TRY_LIMIT) {
            for (int i = 0; i < this.tryCount; i++) {
                double  x = Math.random();
                double  y = Math.random();
                if((x*x + y*y) < 1D ){
                    resut++;
                }
            }
        }else{
            long a = this.tryCount / 2;
            RecursiveMonte first = new RecursiveMonte(a);
            RecursiveMonte second = new RecursiveMonte(this.tryCount-a);
            //first.fork();
            //second.fork();
            invokeAll(first,second);
            resut = first.join() + second.join();
        }
        return resut;
    }

}
