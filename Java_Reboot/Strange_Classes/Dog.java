package Java_Reboot.Strange_Classes;
import Java_Reboot.Strange_Classes.Frameworks.AbstractAnimal;

// 此处位于'不同的包'引用了另一个地方的类, 对应的AbstractAnimal只能用public进行修饰
public class Dog extends AbstractAnimal{

  public Dog(String name){
    super(name); // 在子类的构造函数中继承父类
    // 想干啥干啥
  }
  

  @Override // 明确表示'重写方法'的注解
  public void behaviour(){
    System.out.println(this.name+ "嗷嗷");
  }

  public static void main(String[] args) {
    Dog cyanDog = new Dog("小蓝狗");
    cyanDog.introduce();
    cyanDog.behaviour();
    cyanDog.show_count();

    System.out.println();

    Dog pinkDog = new Dog("小粉狗");
    pinkDog.introduce();
    pinkDog.behaviour();
    pinkDog.show_count();
  }
}
