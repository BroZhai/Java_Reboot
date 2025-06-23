package Java_Reboot.Strange_Classes;
// 导入'嵌套接口'
import Java_Reboot.Strange_Classes.Frameworks.NestedInterfaces;

public class NormalHuman implements NestedInterfaces{
  private String name;
  public NormalHuman(String name){ // 空构造函数
    this.name = name;
  }
  // 实现'嵌套接口'里面的各种接口, 但是'无需全部实现'
  public void eat(){
    System.out.println("乌蒙吃");
  }
  public void sleep(){
    System.out.println("睡大撅");
  }

  public void introduce(){
    System.out.println("Hello, my 名字 is: " + this.name);
  }
  static{ // 对象创建是必然会被执行的代码区
    System.out.println("一个人类被创建了");
  }


  public static void main(String[] args) {
    NormalHuman h1 = new NormalHuman("sus");
    h1.introduce();
    h1.eat();
    h1.sleep();
  }

}
