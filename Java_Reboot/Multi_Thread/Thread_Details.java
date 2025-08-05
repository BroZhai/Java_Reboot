package Java_Reboot.Multi_Thread;

public class Thread_Details {
  // 详细研究一下Thread类有哪些属性

  // 简单继承Thread (注意, 这里的run()在main中是'按顺序'串行调用执行的, 并没有并行执行)
  static class MyThread extends Thread{
    public void run(){
      System.out.println(MyThread.currentThread().getName() + " 突然冒出!!");
    }
  }

  public static void main(String[] args) throws InterruptedException{
    System.out.println("这里是main函数的首行打印消息");
    Thread random_thread = new MyThread();
    random_thread.start(); // 出现位置'随机' 

    System.out.println("main函数中间部分");

    Thread my_thread = new Thread( () -> { // lambda 写 Runnable的 Thread
      try {
        Thread.sleep(8000); // 最常的休眠时间, 确保该线程'留在最后'被执行 (最长的'等待被执行'时间)
        System.out.println("诶嘿,我是 "+ Thread.currentThread().getName() +", 我的8等待时间结束了, 轮到我在最后给大伙收个尾! ");
      } catch (InterruptedException e) {
        System.out.println("线程 "+ Thread.currentThread().getName() + "被意外中断!");
      }
      
    });
    my_thread.start();
    // System.out.println("main函数结束");

    System.out.println("----------------分隔符------------------");
    // 实验一下join()方法
    System.out.println("main: 我先执行, 但是马上就会有个哥们用join()插我的队, 我得等他执行完毕才能继续...");
    Thread suspend_thread = new Thread( () -> {
      System.out.println("诶嘿, 我是时延Thread, 外面的main要等我执行4秒执行完, 才可以继续:3"); // 紧跟着'插队提示'
      try {
        Thread.sleep(3000); // 设置 3 秒的时延
      } catch (InterruptedException e) {
        e.printStackTrace();
      } 
    });
    suspend_thread.start();
    suspend_thread.join(); // 这里 suspend_thread() 插了 main的队, suspend_thread()执行完后才会继续跑main()

    System.out.println("main: 我靠这家伙总算跑完了xwx"); // main等待suspend_thread() 跑完后才执行的语句

  } // main结束

}
