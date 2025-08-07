package Java_Reboot.Multi_Thread.ReentrantLock;

import java.util.concurrent.locks.Lock; // 所有Lock的'抽象接口' (ReentrantLock是其一的实现类)
import java.util.concurrent.locks.Condition; // Lock的Condition类, 用于实现 wait() 和 notify() / notifyAll()
import java.util.concurrent.locks.ReentrantLock; // 导入ReentrantLock包


class Producer extends Thread{
  public void run(){

  }
}

class Consumer extends Thread{
  public void run(){

  }
}

class MilkBox{
  private static final Lock my_lock = new ReentrantLock();
  private final Condition cond = my_lock.newCondition(); // 利用Lock对象的newCondition()方法 创建一个 Condition实例, 利用实例的.await() / .signal() 和 .signalAll()实现 wait(), notify(), notifyAll()方法
}

public class ReentrantLock_Experiment {
  // 我们来研究一下ReenTracntLock (高级synchronized), 重写一下一个送奶员 和 两个送奶工

  public static void main(String[] args) {
    
  } // main方法结束

  static void sleep1s(){ // 睡眠1s (给线程调用)
    try {
      Thread.sleep(1000);  
    } catch (Exception e) {
      // TODO: handle exception
    }
  }

}
