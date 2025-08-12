package Java_Reboot.Multi_Thread.Thread_Pool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService; // 导入'定期线程池'
import java.util.concurrent.TimeUnit;

public class Scheduled_Thread_Pool {
  // 我们来看看ScheduleThreadPool类是怎么用的, 这个玩意专门用来周期性的执行某一任务

  static void sleep3s(){
    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }


  public static void main(String[] args) {
    // 创建一个ScheduleThreadPool, 对应的大类为ScheduleExecutorService
    ScheduledExecutorService schedule_pool = Executors.newScheduledThreadPool(4); // 定义核心线程数为4

    // 提交带延迟的'一次性任务' .schedule()
    schedule_pool.schedule(() -> {
      System.out.println("我是一个'一次性任务', 在schedule_pool开始的2秒后才执行, 然后就没我什么事了qwq...");
    }, 2,TimeUnit.SECONDS);

    // 提交'固定速率的任务' .scheduleAtFixedRate
    schedule_pool.scheduleAtFixedRate(() -> {
      System.out.println("我是固定速率的任务, 固定每隔2秒执行一次, 不管前面的哥们执行完没有");
      sleep3s();
      System.out.println("我是固定速率的第二个任务, 延迟了3秒才执行\n");
    }, 0, 2, TimeUnit.SECONDS);

    // 提交'固定间隔延迟'的任务 .scheduleWithFixedDelay
    schedule_pool.scheduleWithFixedDelay(() -> {
      System.out.println("我是固定间隔延迟的任务, 每执行结束后5秒执行一次\n");
    }, 0, 5, TimeUnit.SECONDS);

  }
  
}
