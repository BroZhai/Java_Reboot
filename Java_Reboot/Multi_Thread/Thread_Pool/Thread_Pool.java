package Java_Reboot.Multi_Thread.Thread_Pool;

import java.util.concurrent.ExecutorService; // 线程池总体的'抽象接口'

import java.util.concurrent.ThreadPoolExecutor; // 自定义线程池子类
import java.util.concurrent.TimeUnit; // 时间单位
import java.util.concurrent.ArrayBlockingQueue; // 有界队列 (当创建的线程数达到最大时, 用于存储提交的'任务')

// import java.util.List;

public class Thread_Pool {
  // 我们来研究一下'线程池'

  public static void main(String[] args) {

    // 使用ThreadPoolExecutor创建'自定义属性'的线程池
    ExecutorService thread_pool = new ThreadPoolExecutor(
      3, // 定义'核心线程数' (始终在运行的)
      3, // 定义能创建的'最大线程数'
      5L, // 等待队列中的任务'最大等待时长'  (这里要填一个long值, 5, 5L都可以) 
      TimeUnit.SECONDS, 
      new ArrayBlockingQueue<>(10), // 自定义'任务等待队列'类型 (这里定的是'有界队列', 大小10个任务)
      new ThreadPoolExecutor.AbortPolicy() // 定义'拒绝策略'的处理逻辑 (可选)
      
        /* 使用lambda表达式 自定义拒绝策略
      (被拒绝的'任务'failed_runnable, (当前)线程池形参thread_pool) -> {
        // 实现自定义拒绝逻辑, 这里以尝试'重新提交'为例
        if(!thread_pool.isShutDown()){
          thread_pool.execute(failed_runnable);
        }
      } 
        */
      
      ); 
    // Tips: 上面的自定义类等效于 ExecutorService thread_pool = Executor.newFixedThreadPool(5); 哥们自己手动进行了一次实现而已 XD

    // List<Runnable> shutdown_list = null;

    // 创建任务并提交 (利用循环创建5个'任务'并进行提交)
    
    for(int i=0; i<10; i++){ // 一口气塞10个任务进来, 触发线程池的'AbortPolicy()'策略
      final int taskId = i; // 获取每次循环时生成的'固定值' (防止i更新了之后, 下面的线程才开始工作, 到时显示执行的顺序'异常', 相当于固定每次运行时的'门牌号')
      thread_pool.submit(() -> {
        System.out.println("我是Runnable-"+ taskId +", 现在轮到我执行了");
        try {
          Thread.sleep(2000);
        } catch (InterruptedException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      });
      // System.out.println("日志输出: ");
      // shutdown_list = thread_pool.shutdownNow();
    }


    thread_pool.shutdown(); // 尝试关闭线程池, 配合awaitTermination()使用 
    System.out.println("线程池已关闭任务入口, shutdown后剩余运行时间为2秒...");
    // awaitTermination设置一个'线程关闭后计时器', 在指定时间内'所有任务完成' + '所有线程终止'返回true, 超时 或 任务未完成返回false
    try {

      // 设置线程shutdown后的等待时间为2秒, 若未能关闭则继续等2秒, 直至成功关闭      
      boolean shutdown_signal = thread_pool.awaitTermination(2, TimeUnit.SECONDS); 
      while(!shutdown_signal){
        System.out.println("线程池未能shutdown, 内部仍有任务再跑 / 进程未终止, 继续等待2秒...");
        shutdown_signal = thread_pool.awaitTermination(2, TimeUnit.SECONDS);
      }
      System.out.println("检测到可以shutdown!");
      System.out.println("线程池是否已启用shutdown? " + (thread_pool.isShutdown()? "Yes":"No"));
      System.out.println("线程池当前Terminated了吗? " + (thread_pool.isTerminated()? "Yes":"No")); 
    } catch (InterruptedException e) {
      System.out.println("线程池终止前被意外中断!");
    } 

    
    // System.out.println(shutdown_list.toString());
    // System.out.println("\n结束");

  } // main函数结束
  
}
