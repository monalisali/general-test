package Thread;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(10);
        for(int i = 0 ; i < 10; i++){
            int finalI = i;
            new Thread(()->{
                int second = new Random().nextInt(10);
                try {
                    Thread.sleep(second * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程" + finalI + "活干完啦！");
                latch.countDown();
            }).start();
        }

        latch.await();
        System.out.println("所有的工人活都干完啦！");
    }
}
