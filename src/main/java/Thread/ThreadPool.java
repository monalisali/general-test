package Thread;

import java.util.concurrent.*;

public class ThreadPool {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        final Future<?> future = threadPool.submit(() -> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("我结束工作啦！");
            throw new RuntimeException();
        });
        System.out.println("我把工作提交啦！");
        System.out.println(future.get());
        System.out.println("我提交的工作做完啦！");
    }
}
