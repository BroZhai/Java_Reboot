package Java_Reboot.Multi_Thread.Synchronize_Experiement;

public class Wait_and_Notify {
  // 我们来研究一下 wait(), notify() 和 notifyALl()
  // 试着实现一下一个送奶工 和 两个喝奶客户
  
  static class MilkBox{
    private int milk_counts = 0;

    public void put_milk(){
      synchronized(this){ // 同步的锁是 "MilkBox实例"
        System.out.println("送奶员来了, 放了2瓶奶");
        this.milk_counts+=2;
        get_milk_count();
        this.notifyAll(); // 通知 所有等待的'客户' (喝奶线程)
      }
    }

    public void drink_milk(String user){
      synchronized(this){ // 也是同步MilkBox实例对象
        System.out.println("轮到"+user+"了");
        while(this.milk_counts <= 0){ // 奶箱没奶
          try {
            System.out.println("用户"+user+"说: 我去, 奶箱没奶了, 哥们得等...\n");
            this.wait(); // 奶箱没奶, 用户线程开始等待
          } catch (InterruptedException e) {
            System.out.println(user + "在等奶期间被意外中断...");
          }
        }
        // 奶箱中有牛奶
        this.milk_counts--;
        System.out.println(user + "喝了一瓶奶 :D");
        get_milk_count();
      }
    }

    public void get_milk_count(){
      System.out.println("当前奶箱有 " + this.milk_counts + " 瓶奶\n");
    }


  }
  public static void main(String[] args) {
    MilkBox box = new MilkBox(); // 创建奶箱实例
    Thread producer = new Thread(() -> { // 放奶线程
      while (true) {
        box.put_milk();
        try {
          Thread.sleep(1000);
        } catch (Exception e) {
          System.out.println("producer在sleep时被中断...");
        } 
      }
      
    });

    Thread drinker1 = new Thread(() -> { // 消费者线程1
      while (true) {
        box.drink_milk("PinkCandy");
        try {
          Thread.sleep(1000); 
        } catch (Exception e) {
          System.out.println("drinker1在sleep时被中断");
        }
      }
    });

    Thread drinker2 = new Thread(() -> { // 消费者线程2
      while (true) {
        box.drink_milk("Bige");
        try {
          Thread.sleep(1000); 
        } catch (Exception e) {
          System.out.println("drinker2在sleep时被中断");
        }
      }

    });

    drinker1.start();
    drinker2.start();
    producer.start();

  }
}
