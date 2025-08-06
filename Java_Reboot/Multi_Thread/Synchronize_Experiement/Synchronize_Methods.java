package Java_Reboot.Multi_Thread.Synchronize_Experiement;

public class Synchronize_Methods {
  // 我们来探索一下'同步方法'

  static class Classmate{
    public static volatile int total_count = 0; // 类的'全局变量' (所有实例通用)

    public static void add(boolean is_boy){
      synchronized(System.out){ // 锁住控制台
      total_count++;
      System.out.println("加入了" + (is_boy ? "男孩子" : "妹子"));
      }
    }

    public static void sub(){
      synchronized(System.out){
        if(total_count <= 0 ){
        System.out.println("队列中已经没得同学了...正在等待新同学加入...");
        }else{
          total_count--;
          System.out.println("有人跑路了QAQ..."); 
        }
      }


    }

    public static void getTotal_count() {
      synchronized(System.out){ // 我们直接锁住'系统'输出流 (限制此时只用我能打印信息!)
        System.out.println("当前的总人数为: " + total_count);
      }
      // return Integer.valueOf(total_count); // 直接返回一个'字面量', 防止total_count被外部修改, 外边也不能直接'改字面量' XD
    }

  }
  
  public static void main(String[] args) {
    var Human_Resource = new Classmate(); // Java 10 特性, 自动静态类型推断 (这里自动推断出来是 Classmate类)

    Thread boy_join = new Thread( ()-> {
      while (true) {
        Classmate.add(true);
        try {
          Thread.sleep(2000);
        } catch (InterruptedException e) {
          System.out.println("boy_join在sleep时被意外中断了");
        }
      }
      
    } );

    Thread girl_join = new Thread( ()-> {
      while(true){
        Classmate.add(false);
        try {
          Thread.sleep(2000);
        } catch (InterruptedException e) {
          System.out.println("girl_join在sleep时被意外中断了");
        }
      }

    } );

    Thread random_leave = new Thread( () -> {
      while(true){
        Classmate.sub();
        try {
          Thread.sleep(2000);
        } catch (InterruptedException e) {
          System.out.println("random_leave在sleep时被意外中断了");
        }
    }

    });

    Thread check_people = new Thread(() -> {
      while (true) { // 注: 这里会有'过早读取'问题(不可重复读), 如刚读了数据后, 数据就发生了变化...
        // System.out.println("当前总人数: " + Classmate.getTotal_count() + "\n");
        Classmate.getTotal_count();
        try {
          Thread.sleep(2500);
        } catch (InterruptedException e) {
          System.out.println("check_people线程在sleep时被意外中断!");
        }
      }
    });

    random_leave.start();
    boy_join.start();
    girl_join.start();
    check_people.start();


  } // main 结束
}
