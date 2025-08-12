package Java_Reboot.Multi_Thread.Thread_Pool;

import java.util.concurrent.Future; // 导入Future类
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Future_Class {
  // 我们来研究一下Future这个类, 专门用于接收Callable接口创建线程的执行返回值
  // 本质上和Optioal类的理念有点像
  
  public static void main(String[] args) throws InterruptedException, ExecutionException {
    // 创建一个Callable任务对象, 提交到线程池中
    MyCallable baka_task = new MyCallable();
    ExecutorService thread_pool = Executors.newFixedThreadPool(5); // 创建线程池
    Future<String> baka_task_return = thread_pool.submit(baka_task); // 提交Callable任务, 执行返回值对应到接收的Future<>对象

    while(!baka_task_return.isDone()){
      System.out.println("baka_task仍在执行, Future正在等待下一次查询, 时长1秒");
      Thread.sleep(1000);
    }
      System.out.println("检测到baka_task已经执行完毕, 正在取得返回值");
      System.out.println("取得的返回值为: " + baka_task_return.get());
  }
  
}

// 自定义一个Callable线程
class MyCallable implements Callable<String> { // 这里就要声明'返回数据类型'
  public String call() throws InterruptedException{ // 重写Callable的call()方法
    Thread.currentThread().setName("MyCallable");
    System.out.println("我是"+Thread.currentThread().getName()+", 2秒后取得返回结果");
    Thread.sleep(2000);
    return "Baka";
  }

}