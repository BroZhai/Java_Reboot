package Java_Reboot.Multi_Thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException; // FutureTask的异常类
import java.util.concurrent.ExecutorService; // 线程池
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask; // 搭配Callable使用的FutureTask, 用于创建最终的Thread对象

public class Thread_Basics {
  // 研究一下Java多线程
  // 直接继承Thread类创建线程
  static class MyThread extends Thread{ 
    public void run(){ // 重写Thread类中的run()方法
      System.out.println("我是Thread类中的线程执行逻辑");
    }
  }

  // 实现Runnable接口
  static class MyRunnable implements Runnable{
    public void run(){ // 重写Runnable()中的run()方法
      System.out.println("我是Runnable接口中的线程执行逻辑");
    }
  }

  // 实现Callable接口, 类似上边, 需要搭配FutureTask进行使用
  static class MyCallable implements Callable<Integer>{
    public Integer call() throws Exception{ // 重写Callable中的call()方法, 可自定义返回类型
      System.out.println("我是Callable中的线程执行逻辑");
      return 114514;
    }
  }

  // 除此以外还有 lambda 表达式 和 线程池创建, 在main()中进行演示
  
  public static void main(String[] args) throws InterruptedException, ExecutionException{
    // Thread类直接创建
    MyThread direct_thread = new MyThread();
    direct_thread.start();

    // Runnable作为Thread的构造函数参数创建
    MyRunnable my_runnable = new MyRunnable();
    Thread runnable_thread = new Thread(my_runnable);
    runnable_thread.start();

    // Callable搭配FutureTask创建Thread, 常用于"获取线程返回值" (注意定义的'返回类型')
    MyCallable my_callable = new MyCallable();
    FutureTask<Integer> ftr_task = new FutureTask<>(my_callable);
    Thread future_thread = new Thread(ftr_task);
    future_thread.start();
    System.out.println("my_callable搭配FutureTask使用取得的线程返回值为: "+ ftr_task.get()); // 这里的get方法会抛出InterruptedException 和 ExecutionException, 因为会阻塞线程

    // 使用lambda表达式简化Runnable 和 Callable 建 Thread
    new Thread(() -> System.out.println("我是lambda里面实现的Runnable执行逻辑")).start();; // 简化Runnable, 建完后直接启动线程

    FutureTask<Integer> simplified_ftr = new FutureTask<>(() -> {
      System.out.println("我是lambda内实现的Callable执行逻辑");
      return 1919810;
    }); // 直接在FutureTask创建时用lambda实现线程运行逻辑
    new Thread(simplified_ftr).start(); // 启动FutureTask构建的线程

    // 使用"线程池"ExecutorService进行创建 (先了解)
    ExecutorService executor = Executors.newFixedThreadPool(5);
    executor.submit(() -> System.out.println("我是在线程池中创建的线程任务awa")); // 往线程池中'追加'新线程任务
    executor.shutdown(); // 关闭线程池

  } // main函数结束
}
