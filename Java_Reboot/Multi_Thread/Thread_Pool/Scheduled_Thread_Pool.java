package Java_Reboot.Multi_Thread.Thread_Pool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService; // 导入'定期线程池'
import java.util.concurrent.TimeUnit;

public class Scheduled_Thread_Pool {
  // 我们来看看ScheduleThreadPool类是怎么用的, 这个玩意专门用来周期性的执行某一任务


  public static void main(String[] args) {
    // 创建一个ScheduleThreadPool, 对应的大类为ScheduleExecutorService
    ScheduledExecutorService schedule_pool = Executors.newScheduledThreadPool(4); // 定义核心线程数为4
    schedule_pool.schedule(() -> {
      System.out.println("我是一个'一次性任务', 在schedule_pool开始的2秒后才执行, 然后就没我什么事了qwq...");
    }, 2,TimeUnit.SECONDS);

  }
  
}
