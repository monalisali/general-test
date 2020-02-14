package Thread;

public class ThreadModel {
    static volatile boolean inited = false; //代表程序已经初始化完毕
    public static void main(String[] args) throws InterruptedException {
         new Thread(()->{
             init();
             inited = true;
         }).start();

         while (true)
         {
             if(inited)
             {
                 //程序初始化完毕后，做的事情
             }else {
                 Thread.sleep(500);
             }
         }
    }

    private static void init()
    {
        //初始化程序的代码
    }
}
