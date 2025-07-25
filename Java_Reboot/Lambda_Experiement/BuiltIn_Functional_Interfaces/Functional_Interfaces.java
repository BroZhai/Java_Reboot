package Java_Reboot.Lambda_Experiement.BuiltIn_Functional_Interfaces;

// 函数式接口'四大核心类'
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.function.Function;
import java.util.function.Predicate;

// 其他常用的函数式接口
import java.util.function.BiFunction;
import java.util.function.UnaryOperator;
import java.util.Comparator; // 常配合Collections类进行使用, 马上就看
import java.util.List;
// 其他工具
import java.lang.Math;
import java.util.Arrays;
import java.util.List;


public class Functional_Interfaces {

  /* static class Person{ // 如果要在内部类上应用函数接口, 需要声明为static, 这是'函数式接口'的要求, 不然得单独写一个class
    public static String name;
    public static int age;

    public Person(String name, int age){
      this.name = name;
      this.age = age;
    }

    public void intro(){
      System.out.println("你好, 我是" + this.name);
    }

    public boolean reset_name(String rename){ // 一会儿用 实例对象::方法 进行引用
      this.name = rename;
      System.out.println("已成功重命名为: " + this.name);
      intro();
      return true;
    }
    

  }
*/

  // 在本class中, 我们来研究一下Java的内置'函数式接口'Functional_Interfaces (就会外面自己定义的Calculator类似, 但是是'内置的')
  public static void main(String[] args) {

    // Consumer<T> 仅接收输入, 无返回数据, 花样重写的方法为accept()
    Consumer<String> sout_printer = (data) -> {
      System.out.println("Consumer接收到的数据为: " + data);
    };
    sout_printer.accept("你好"); // 调用重新的accept()

    System.out.println();
    // Supplier<R> 仅返回数据类型R, 无输入, 花样重写get()
    Supplier<Integer> random_number = () -> {
      int retrun_val = (int) (Math.random()*10)+1; // 生成 1-10 的随机整数并进行返回
      return retrun_val;
    };
    int generated_value = random_number.get();
    System.out.println("本次生成的随机值为: " + generated_value);

    System.out.println();
    // Function<T,R> 有输入也有输出, 分别自行指定即可, 花样重写apply()
    Function<Integer, Integer> add_1 = (value) -> value+1;
    Function<Integer, Integer> multiply_3 = (value) -> value*3;
    Function<Integer, Integer> add_then_multiply = add_1.andThen(multiply_3); // 规定'先加'再乘
    Function<Integer, Integer> multiplu_then_add = add_1.compose(multiply_3); // 规定'先乘'后加
    System.out.println("当前的base值为: " + generated_value + "\n先+1后乘3的值为: " + add_then_multiply.apply(generated_value) + "\n先乘3后加1的值为: " + multiplu_then_add.apply(generated_value));

    System.out.println();
    // Predicate<T> 简单表达式判断, 指定输入类型, 返回值要是布尔, 花样重写test()
    Predicate<Integer> even_judger = (value) -> value%2==0; // 判断输入值是否为偶数
    // Predicate<Integer> odd_judger = even_judger.negate();  // 就是even_judger的'结果取反'
    System.out.println("该base值是偶数吗? " + even_judger.test(generated_value));
  

    /* 方法引用 实验区(针对Function, 但是其他的函数接口也都能使用) */
    System.out.println("\n现在开始'方法引用'实验");

    // 针对'已有类'的静态方法
    Function<Double, Double> get_sqrt = Math::sqrt; // Math.sqrt默认直接接受'一个参'
    System.out.println("base值 "+ generated_value +" 取根号的结果为: " + get_sqrt.apply((double)generated_value));
    // 等价于
    // Function<Double, Double> get_sqrt = (value) -> Math.sqrt(value);

    System.out.println();
    // 引用'已有实例对象'的方法 (这里引Person类的 reset_name方法)
    Person cirno = new Person("Big Baka", 9);
    cirno.intro();
    Function<String, Boolean> person_reset_name = cirno::reset_name;  // 等价于 () -> cirno.reset_name();
    person_reset_name.apply("Cirno"); // 重命名对象为 Cirno
    person_reset_name.apply("PurpleCandy");  // 再次重命名对象 为 PurpleCandy

    System.out.println();
    // 引用'输入参'类型(大类)中定义 的方法 
    Person cyan_dog = new Person("Pinkcandy", 18);
    Person yellow_cake = new Person("Niko", 16);
    Function<Person, String> get_age = Person::get_info;
    System.out.println(get_age.apply(cyan_dog));
    System.out.println(get_age.apply(yellow_cake));

    // 小实验: 分清 '对象方法' 和 输入参大类的方法
    // String test_string = "baka";
    // Function<String, Integer> get_string_length = test_string::length; // 错误的写法 , 等价于 "baka".length(), 这里需要声明为'输入参'的大类 String
    // get_string_length.apply("wowowowo");

    System.out.println();
    // 引用类的'构造函数 创建对象'
    BiFunction<String, Integer, Person> create_person = Person::new; // 类名::new (前面只管定好 '传入参'的类型 就好了)
    Person hugo = create_person.apply("Hugo", 23);
    System.out.println(hugo.get_info());
    Person ug = create_person.apply("油雞", 21);
    System.out.println(ug.get_info());

    // andThen 组合用法实践
    Function<String, String> no_space = String::trim; // 去除首尾空格
    Function<String, String> to_uppercase = String::toUpperCase;
    Function<String, String> trim_then_upper = no_space.andThen(to_uppercase);
    System.out.println(trim_then_upper.apply("  baka     "));  // 输出BAKA


    System.out.println();
    // 其他的函数接口测试
    UnaryOperator<Integer> double_value = value -> value*2;
    System.out.println("base数 " + generated_value + " 的两倍'操作值'为: " + double_value.apply(generated_value));

    
    Comparator<Person> compare_and_swap = (p1, p2) -> { // 这里的Comparator<> 仅用于展示结构, 实际使用是大部分时候用的是 Comparactor<>类的'静态方法'
      return p1.get_age() - p2.get_age(); // 正值 p1 排在 p2 后, 负值则是 p1排在前 (正值默认升序, '整体取反'即为降序)
      // return -(p1.get_age() - p2.get_age()); // 降序
    };

    List<Person> person_list = Arrays.asList(hugo, ug, yellow_cake, cyan_dog, cirno); // 创建一个person List, 准备用于排序 (配合使用Comparator)
    System.out.print("当前person_list中的年龄排序为: ");
    person_list.forEach( person -> System.out.print(person.get_age() + ", ")); // 遍历list中 每个Person的age (在里面sout, 不在外面)
    person_list.sort(Comparator.comparing(Person::get_age)); // 默认是升序
    System.out.print("\n排完序的结果为: ");
    person_list.forEach( person -> System.out.print(person.get_age() + ", ")); // 比较的是Person年龄的大小作为swap条件, 

  } // main函数结束


  
  
}
