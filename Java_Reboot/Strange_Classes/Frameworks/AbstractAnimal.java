// 这是一个抽象类模版
package Java_Reboot.Strange_Classes.Frameworks;

//抽象类可以带抽象方法() 和 具体方法() 的实现, 可以包含构造函数(), 成员变量任意修饰符
abstract class AbstractAnimal{
  protected String name;
  protected static int countes=0;
  public abstract void behaviour(); // 在抽象类中声明'抽象方法()'也要带abstract关键字
  public void introduce(){
    System.out.println("Cialo~ My name is : "+ this.name);
  }

}