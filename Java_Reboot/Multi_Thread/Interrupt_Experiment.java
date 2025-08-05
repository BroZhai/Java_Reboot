package Java_Reboot.Multi_Thread;

public class Interrupt_Experiment {
  // 来探索一下线程的Interrupt 中断
  
  static class Mythread extends Thread{
    public void run(){
      setName("print_thread");
      int counter = 0;
      while(!isInterrupted()){ // 程序没收到外界中断 (一直判断, 直到自身调用interrupted()方法进行中断)
        counter++;
        System.out.println("这是"+Thread.currentThread().getName()+"第"+counter+"次打印");
      }
      // while循环条件被打破, 说明'收到了中断信号'
      System.out.println("收到了外界的中断信号, " + Thread.currentThread().getName() + "现已停止...");
    }
  }
  
  // '嵌套'线程 (等待另一个线程)
  static class LoopThread extends Thread{
    public void run(){
      Thread print_thread = new Mythread();
      print_thread.start();
      try {
        print_thread.join();// LoopThread 等待上面的 MyThread'执行完毕' (不可能'等'到的, 想办法往MyThread中丢一个'中断信号'就'执行完毕'了 XD)
        
      } catch (InterruptedException e) { // 捕获到了'外界传来'的中断信号, 但此时LoopThread并不能直接结束 (在等待内部的print_thread结束), 此时就会抛出InterruptedException异常 (哥们终止不了哦, 你来看看?)
        // 发现LoopThread"不能中断"后, 调用Mythread对象的print_thread.interrupt() 方法即可'嵌套中断'内部的线程, 然后LoopThread自身才可以结束
        System.out.println("LoopThread已收到外部的中断信号并抛出了InterruptedException异常, 已调用print_thread的interrupt()方法进行中断");
        print_thread.interrupt(); // 中断内部
        try {
          Thread.sleep(10);
        } catch (InterruptedException e1) {
          // 休眠计时器, 目的是为了让最内部的 print_thread终止信号"优先输出"
        }
        System.out.println("内部信号应该会先于LoopThread流出, 这里是LoopThread成功终止了(运行结束)"); // 内部中断信号输出后, 再输出LoopThread的中断信号
        
      } 
      
    }
  }
  public static void main(String[] args) throws InterruptedException{
    System.out.println("main线程开始, 2秒后准备执行print_thread(), 随后1秒终止");
    Thread print_thread = new Mythread();
    Thread.sleep(2000); // 单纯阻塞 main, print_thread方法还没start() (没进入'就绪队列')
    print_thread.start();
    Thread.sleep(1000); // main再次被阻塞, 但此时print_thread已就绪, CPU就会去执行print_thread中的运行逻辑
    print_thread.interrupt(); // 1秒后, main醒了, main再进'就绪队列', 在某一次的CPU时间片切换时, 执行main中向print_thread发送'中断信号', print_thread终止运行 
    // 注意: print_thread每次结束的时间不一样, 因为此刻就是CPU切换时间片的时刻, 这是由JVM的'随机调度'决定的 :)

    System.out.println("main: 现在是我的show time! print_thread靠边站!"); // 同理, 时间片位于main时, 优先处理的就是main中的内容, 故这里的消息会先一步于 print_thread中的'中断信息' 出来
    Thread.sleep(1); // 将时间片短暂交回给print_thread '善后' XD
    
    System.out.println("\n----------------分隔符------------------\n");
    // 看看'嵌套中断', 不同的线程间可能join()的'嵌套等待'关系, 那么要中断'最内部的'线程, interrupt()这个方法就需要'层层递进'
    System.out.println("现在我们来看看'嵌套中断'");
    System.out.println("join关系: main() -> LoopThread -> MyThread");
    Thread loop_thread = new LoopThread();
    System.out.println("准备执行loop_thread线程, 延迟2秒, 随后运行0.5秒后发送中断...");
    Thread.sleep(2000); // 延迟2秒后执行
    loop_thread.start();
    Thread.sleep(500); // main进程休眠0.5秒 (让loop_thread运行0.5秒)
    loop_thread.interrupt();
    loop_thread.join(); // 此时不会往下执行了, 等待内部进程(LoopThread)结束
    Thread.sleep(20); // 等待LoopThread中断信号输出
    
    System.out.println("最外层的main结束");  // main的中断信号输入
    


  } // main函数结束

}
