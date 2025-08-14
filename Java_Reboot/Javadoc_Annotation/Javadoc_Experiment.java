package Java_Reboot.Javadoc_Annotation;
/**
 * 这是一个Javadoc实验类, 展示了所有注解相关的用法
 * <p> 该类声明了两个类, 一个普通人类 和 一个继承的'程序员'类 </p>
 * @author BakaCandy
 * @version 1.14
 * @since JDK 1.8
 */

class Human{
  /**
   * 人名
   */
  private String name;
  /**
   * 年龄
   */
  private int age;
  /**
   * 性别 (为{@link #MALE} 或 {@link #FEMALE})
   */
  private int sex;

  /**
   * 定义男性的常量为 {@value}
   */
  public static final int MALE = 1;
  /**
   * 女性的常量则为 {@value}
   */
  public static final int FEMALE = 2;
  
  /**
   * 创建一个人类对象, 以下开始对各种传入形参进行讲解
   * 
   * @param name 定义人类名称
   * @param age 定义用户年龄
   * @param sex 定义用户性别 (只可以是{@link #MALE} 或 {@link #FEMALE})
   * @throws IllegalArgumentException 当性别参数无效时则会抛出该异常
   * 
   */
  public Human(String name, int age, int sex)throws IllegalArgumentException{
    this.name = name;
    this.age = age;
    if (sex != MALE && sex != FEMALE){
      throw new IllegalArgumentException("性别参数无效!");
    }
  }

  /**
   * 取得人类名称
   * @return 取得的名称String
   */
  public String getName(){
    return this.name;
  }

  /**
   * 取得人类年龄
   * @return 取得的年龄int
   */
  public int getAge(){
    return this.age;
  }
  
  /**
   * 让人类进行自我介绍
   * @see #getName() 
   * @see #getAge()
   * @return 介绍的String字符串
   */
  public String introduce(){
    return "你好, 我是" + getName() + ", 今年" + getAge() + "岁";
  }

}

/**
 * 人类的继承子类 -- 程序员Programmer
 * 
 */
class Programmer extends Human{
  /**
   * 程序员特有的'码龄'
   */
  private int coding_age;

  /**
   * 程序员类的构造函数
   * @param name 名称
   * @param age 年龄
   * @param sex 性别
   * @param coding_age 开发经验 (新增)
   */
  public Programmer(String name, int age, int sex, int coding_age){
    super(name, age, sex);
    this.coding_age = coding_age;
  }

  /**
   * 获得该程序员的码龄
   * @return 码龄int
   */
  public int getCodingAge(){
    return this.coding_age;
  }

  /**
   * {@inheritDoc}
   * 重写了Human父级的introduce方法
   * @see #getCodingAge() 取得码龄的方法
   * 
   */
  @Override
  public String introduce(){
    return "你好, 我是程序员" + getName() + ", 今年" + getAge() + "岁, 拥有" + getCodingAge() + "年码龄";
  }

}

/**
 * 运行主类
 */
public class Javadoc_Experiment {
  // 我们来研究一下javadoc里面的各种关键字注解怎么用, 以及生成一个API报告

  /**
   * main函数运行区
   * @param args 命令行传入形参
   */
  public static void main(String[] args) {
    Human person1 = new Human("IceWing", 22,2);
    System.out.println(person1.introduce());

    Programmer person2 = new Programmer("Talented_Cirno", 19, 1,9);
    System.out.println(person2.introduce());
  }
}
