package Java_Reboot.Multi_Thread;

public class Interrupt_Experiment {
  // 来探索一下线程的Interrupt 中断
  
  static class Mythread extends Thread{
    public void run(){
      setName("print_thread");
      int counter = 0;
      while(!interrupted()){ // 程序没收到外界中断 (一直判断, 直到自身调用interrupted()方法进行中断)
        counter++;
        System.out.println("这是"+Thread.currentThread().getName()+"第"+counter+"次打印");
      }
      // while循环条件被打破, 说明'收到了中断信号'
      System.out.println("收到了外界的中断信号, " + Thread.currentThread().getName() + "现已停止...");
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
    
  } // main函数结束

}
