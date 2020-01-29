import java.io.*;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

public class Main {
    private static  int i = 0;
    //private static Integer i = 0;
    private static Map<Integer,Integer> map = new HashMap<Integer, Integer>();
    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();
    
    public static void main(String[] args) {
          //new Thread1().start();
          //new Thread2().start();
//        for(int i = 0;i<1000;i++)
//        {
//            new Thread(new Runnable() {
//                public void run() {
//                    slowFileOperation();
//                    //putIfAbsent();
//                }
//            }).start();
//        }

        List<String> aa = new ArrayList();
        aa.forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.printf(s);
            }
        });

        aa.forEach(System.out::print);

        //16进制数字输出A
        System.out.println('\u0041');

    }

    static class Thread1 extends Thread
    {
        @Override
        public void run() {
            synchronized (lock1){
                try{
                    Thread.sleep(100);
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }

               synchronized (lock2){
                   System.out.println("");
               }
            }

            //这样就不会形成死锁了
//            synchronized (lock2){
//                System.out.println("thread1 lock2");
//            }
        }
    }


    static class Thread2 extends Thread
    {
        @Override
        public void run() {
            synchronized (lock2){
                try{
                    Thread.sleep(100);
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }

              synchronized (lock1){
                    System.out.println("");
               }
           }

           //这样就不会形成死锁了
//            synchronized (lock1){
//                System.out.println("thread2 lock1");
//            }
        }
    }



    public static void putIfAbsent()
    {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int r = new Random().nextInt(10);
        if(!map.containsKey(r))
        {
            map.put(r,r);
            System.out.println("Put " + r);
        }

    }
    public static void slowFileOperation(){
        i++;
        System.out.println(i);
    }


}
