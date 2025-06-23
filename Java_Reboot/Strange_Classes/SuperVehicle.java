package Java_Reboot.Strange_Classes;

// 单独导入外部三个'独立接口' (具体到'文件名', 关键字import)
import Java_Reboot.Strange_Classes.Frameworks.Flyable;
import Java_Reboot.Strange_Classes.Frameworks.Runable;
import Java_Reboot.Strange_Classes.Frameworks.Swimmable;

/*
interface Flyable{ void fly(); }
interface Runable{ void run(); } 
interface Swimmable{ void swim(); }
 */

// 
class SuperVehicle implements Flyable, Runable, Swimmable{
  // 神奇载具将拥有的各种奇妙功能(接口)
  public void fly(){
    System.out.println("超级载具会飞!");
  }

  public void run(){
    System.out.println("超级载具会跑!");
  }

  public void swim(){
    System.out.println("超级载具会潜水!");
  }

  public SuperVehicle(){ // 创建空的构造函数

  }

  public static void main(String[] args){
    System.out.println("你好, 我是超级载具!\n");
    SuperVehicle v1 = new SuperVehicle();
    v1.fly();
    v1.run();
    v1.swim();
  }

}