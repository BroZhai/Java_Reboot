package Java_Reboot.Multi_Thread;

import java.util.concurrent.Callable;

public class Thread_Basics {
  // 研究一下Java多线程
  // 直接继承Thread类创建线程
  static class MyThread extends Thread{
    public void run(){ // 重写Thread类中的run()方法
      System.out.println("我是Thread类中的线程执行逻辑");
    }
  }

  static class MyRunnable implements Runnable{
    public void run(){ // 重写Runnable()中的run()方法
      System.out.println("我是Runnable接口中的线程执行逻辑");
    }
  }

  static class MyCallable implements Callable{
    public Integer call() throws Exception{ // 重写Callable中的call()方法, 可自定义返回类型

      return 114514;
    }
  }
  
  public static void main(String[] args) {
    
  }
}
