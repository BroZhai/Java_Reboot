package Java_Reboot.Multi_Thread.Thread_Pool;

import java.util.concurrent.ExecutorService; // 线程池总体的'抽象接口'

import java.util.concurrent.ThreadPoolExecutor; // 自定义线程池子类
import java.util.concurrent.TimeUnit; // 时间单位
import java.util.concurrent.ArrayBlockingQueue; // 有界队列 (当创建的线程数达到最大时, 用于存储提交的'任务')

public class Thread_Pool {
  // 我们来研究一下'线程池'

  public static void main(String[] args) {

    // 使用ThreadPoolExecutor创建'自定义属性'的线程池
    ExecutorService thread_pool = new ThreadPoolExecutor(
      5, // 定义'核心线程数' (始终在运行的)
      10, // 定义能创建的'最大线程数'
      60L, // 等待队列中的任务'最大等待时长'  (这里要填一个long值, 60, 60L都可以) 
      TimeUnit.SECONDS, 
      new ArrayBlockingQueue<>(100), // 自定义'任务等待队列'类型 (这里定的是'有界队列', 大小100个任务)
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

    // 创建任务并提交 (利用循环创建5个'任务'并进行提交)
    for(int i=0; i<5; i++){
      
    }

  } // main函数结束
  
}
