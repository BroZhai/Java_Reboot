package Java_Reboot.Multi_Thread;

public class Daemon_Thread {
  // 我们来看看守护线程是怎么个事 (如果只剩该线程, JVM 将会无视'守护线程'并退出. 在原则上JVM是要等所有的线程都退出后 JVM才会关闭的, 但是'守护线程'例外 XD)
  // 通常守护线程是"用来给其他线程提供服务"的, 但是注意守护线程不能持有任何'需要关闭'的资源 (如打开文件等), 因为压根没机会关 JVM就shutdown了 XD

  static class Mythread extends Thread{
    public volatile boolean keep_running = true; // volatile关键字: 所有线程都'同步'的一个变量
    public void run(){
      while(keep_running){
        System.out.println("进程正在运行, 每0.5秒休息一次");
        try {
          Thread.sleep(500);
        } catch (InterruptedException e) {
          System.out.println("进程在sleep时被中断!");
        }
      }
    }
  }


  public static void main(String[] args) throws InterruptedException{
    Thread daemon_thread = new Mythread();
    daemon_thread.setDaemon(true); // 若注释此行, 进程将"卡死循环"一直执行, JVM 会因为这个线程卡着而'不退出'; 如果是'守护线程', JVM则会'无视守护进程'并直接退出 (其实守护线程还是会被'执行一次')
    daemon_thread.start();
    Thread.sleep(1);  // main 让位给 daemon_thread() 优先执行一下
    System.out.println("JVM已退出!");
  }
}
