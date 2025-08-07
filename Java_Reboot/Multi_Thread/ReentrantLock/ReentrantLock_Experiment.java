package Java_Reboot.Multi_Thread.ReentrantLock;

import java.util.concurrent.locks.Lock; // 所有Lock的'抽象接口' (ReentrantLock是其一的实现类)
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition; // Lock的Condition类, 用于实现 wait() 和 notify() / notifyAll()
import java.util.concurrent.locks.ReentrantLock; // 导入ReentrantLock包

class MilkBox{
  private static final Lock my_lock = new ReentrantLock();
  private final Condition cond = my_lock.newCondition(); // 利用Lock对象的newCondition()方法 创建一个 Condition实例, 利用实例的.await() / .signal() 和 .signalAll()实现 wait(), notify(), notifyAll()方法

  private int milk_counts = 0;
  
  public void put_milk(){
    try{
      my_lock.lock(); // 手动上锁 (锁的是my_lock)
      // my_lock.lockInterruptibly(); // 上'可以在等待期间被中断' 的 锁 (需要单独写 '等待时中断' 的处理逻辑 catch(InterruptedException e){...})
      // 上锁成功后的'临界区' (定义线程执行的操作)
      System.out.println("送奶工过来送了2瓶奶");
      this.milk_counts+=2;
      get_milk_count();
      cond.signalAll(); // 等效于notifyAll()
    }finally{
      my_lock.unlock(); // 操作完成, 手动解锁
    }
  }

  public void drink_milk(String username){
    try{
      boolean get_lock = my_lock.tryLock(1000, TimeUnit.SECONDS); // 设置在1秒内尝试上锁, 成功上锁返回true, 超时则返回false,
      if(!get_lock){ // 未能在1秒内获取锁, 对应的处理逻辑 
        System.out.println("消费进程未能在1秒内获取锁, 已中断操作");
        return;
      }
      System.out.println("现在轮到了: " + username);
      // 上锁成功的'临界区'
      while(this.milk_counts<=0){
        // 奶箱为空, 发送await (wait())
        System.out.println(username + "发现奶箱中没有奶, 正在等待...\n");
        cond.await(); // Lock 的 "wait"方法
      }
      // 奶箱有奶
      this.milk_counts--;
      System.out.println(username + " 喝掉了一瓶奶 :D");
      get_milk_count();
      my_lock.unlock(); // 消费操作完成, 进行解锁

    }catch(InterruptedException e){
      System.out.println("消费进程在'等待获取锁'时被中断!");
    }
  }

  public void get_milk_count(){
    System.out.println("当前奶箱中共有 " + this.milk_counts + " 瓶奶\n");
  }

}

public class ReentrantLock_Experiment {
  // 我们来研究一下ReenTracntLock (高级synchronized), 重写一下 一个送奶员 和 两个消费者

  public static void main(String[] args) {
    MilkBox box = new MilkBox();
    Thread producer = new Thread(() -> {
      while(true){
        box.put_milk();
        sleep1s();
      }
    });

    Thread consumer1 = new Thread(() -> {
      while (true) {
        box.drink_milk("CyanCandy");
        sleep1s();
      }
    });

    Thread consumer2 = new Thread(() -> {
      while (true) {
        box.drink_milk("Cirno");
        sleep1s();
      }
    });

    consumer1.start();
    producer.start();
    consumer2.start();


  } // main方法结束

  static void sleep1s(){ // 睡眠1s (给线程调用)
    try {
      Thread.sleep(1000);  
    } catch (Exception e) {
      // TODO: handle exception
    }
  }

}
