// 这是一个抽象类模版
package Java_Reboot.Strange_Classes.Frameworks;

//抽象类可以带抽象方法() 和 具体方法() 的实现, 可以包含构造函数(), 成员变量任意修饰符
public abstract class AbstractAnimal{
  protected String name;
  protected static int counts=0;
  public abstract void behaviour(); // 在抽象类中声明'抽象方法()'也要带abstract关键字
  public void introduce(){
    System.out.println("Ciallo~ My name is : "+ this.name);
  }

  public void show_count(){
    System.out.println("当前共有 "+ this.counts +" 只小动物");
  }

  // 带一个构造函数, 子类构造函数中记得super()
  public AbstractAnimal(String name){
    this.name=name;
    this.counts++; // 访问静态成员变量(类变量)建议用getters&setters (确保事务操作的'原子性')
    System.out.println(this.name+ " 成功创建!");
  }

}