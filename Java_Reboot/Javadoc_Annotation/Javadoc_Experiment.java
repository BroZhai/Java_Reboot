package Java_Reboot.Javadoc_Annotation;

class Human{
  private String name;
  private int age;
  
  public Human(String name, int age){
    this.name = name;
    this.age = age;
  }

  public String getName(){
    return this.name;
  }

  public int getAge(){
    return this.age;
  }

  public String introduce(){
    return "你好, 我是" + this.name + ", 今年" + this.age + "岁";
  }

}

class Programmer extends Human{
  private int coding_age;

  public Programmer(String name, int age, int coding_age){
    super(name, age);
    this.coding_age = coding_age;
  }

  public int getCodingAge(){
    return this.coding_age;
  }

  @Override
  public String introduce(){
    return "你好, 我是程序员" + getName() + ", 今年" + getAge() + "岁, 拥有" + getCodingAge() + "年码龄";
  }

}

public class Javadoc_Experiment {
  // 我们来研究一下javadoc里面的各种关键字注解怎么用, 以及生成一个API报告

  public static void main(String[] args) {
    Human person1 = new Human("IceWing", 22);
    System.out.println(person1.introduce());

    Programmer person2 = new Programmer("Talented_Cirno", 9, 9);
    System.out.println(person2.introduce());
  }
}
