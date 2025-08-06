package Java_Reboot.Multi_Thread.Synchronize_Experiement;

public class Basic_Synchronize {
  // 我们来研究一下基础的锁的创建, 以及Thread中的synchronized(){}临界区
  // 这里用一个最简单的例子, 送奶工 和 喝奶工 XD , 奶箱中空时送奶工送奶, 有有奶时喝奶工去喝
  
  static class PutMilk extends Thread{ // 送奶工, 若奶箱子有奶 则 等待消费... (但是执行时可能会'反复轮回到送奶工', 由CPU调度决定)
    public void run(){
      while(!isInterrupted()){
        synchronized(MilkBox.box_lock){ // 读取'奶箱锁', 若正被喝奶工使用则等待, 没人用时则检查'奶箱是否为空', 为空则放奶
          try {
            Thread.sleep(2000); // 送奶工等待2秒
          } catch (Exception e) {
            System.out.println("送奶工在sleep期间被中断!");
          }
          System.out.println("现在轮到'送奶工'放奶");

        // 没有人在用奶箱, 检查是否有奶
        if(MilkBox.milk_counts !=0){ // 有奶
          System.out.println("送奶工: 咦? 奶箱有奶, 哥们先不放了...");
        }else{ // 没有奶
          MilkBox.milk_counts++;
          System.out.println("送奶工: 奶箱为空, 哥们放奶了!");
        }
        System.out.println("当前奶箱的牛奶数: " + MilkBox.milk_counts);
        System.out.println();
      } // 解除奶箱锁
    }

    }
  }

  static class DrinkMilk extends Thread{
    public void run(){
      while (!isInterrupted()) {
        synchronized(MilkBox.box_lock){// 读取'奶箱锁', 若被'送奶工'使用 则 等待 (可能'反复轮回喝奶工', 同上)
        // 没有人在用奶箱, 检查是否有奶, 有则消耗
          try {
            Thread.sleep(2000); // 喝奶工等1.5秒
          } catch (Exception e) {
            System.out.println("喝奶工在sleep期间被中断!");
          }
        System.out.println("现在轮到了'喝奶的'");

        if(MilkBox.milk_counts == 0){ // 没有奶
          System.out.println("喝奶工: 哎我的奶呢??!");
        }else{  // 有奶
          MilkBox.milk_counts--;
          System.out.println("喝奶工: 咕噜咕噜咕噜~濠河!!");
        }
        System.out.println("当前奶箱的牛奶数: " + MilkBox.milk_counts);
        System.out.println();
      } // 解除奶箱锁

      }

    }
  }

  static class MilkBox{
    public static final Object box_lock = new Object(); // 牛奶箱的'锁' (判断是否再用)
    public static int milk_counts = 0;
  }

  public static void main(String[] args) {
    Thread producer = new PutMilk();
    Thread consumer = new DrinkMilk();
    System.out.println("请等待送奶工/喝奶工开始...约2秒\n");
    producer.start();
    consumer.start();

  }
}
