package Java_Reboot.Lambda_Experiement.BuiltIn_Functional_Interfaces;

// 函数式接口'四大核心类'
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.function.Function;
import java.util.function.Predicate;

// 其他工具
import java.lang.Math;

public class Functional_Interfaces {

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
  

    // 方法引用 实验区(针对Function)
    Function<Double, Double> get_sqrt = (value) -> Math.sqrt(value);

  } // main函数结束


  
  
}
