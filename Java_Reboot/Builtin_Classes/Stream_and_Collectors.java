package Java_Reboot.Builtin_Classes;

import java.util.stream.Stream; // 导入Stream类
import java.util.stream.Collectors; // 导入Collectors工具包

// 配合使用的'函数式接口'
import java.util.function.Supplier;

// 其他工具类
import java.lang.Math;
import java.util.List;

public class Stream_and_Collectors {
  // 我们来看一下 和'操作流' 密切相关Stream类

  public static void main(String[] args) {
    // Stream类没有构造函数, 依赖'静态方法'进行'流实例'的创建 (或者是用对象的.toStream()方法, 如有)
    Stream<String> str_stream = Stream.of("B", "A", "K", "A"); // 利用静态方法.of()创建
    Stream<?> empty_stream = Stream.empty(); // 创建一个'空流' (毛都木有)

    // 看看还有哪些其他的常用静态方法
    Supplier<Integer> get_random_int = () -> (int)Math.random()*10+1; // 随机生成1-10 的Supplier
    List<Integer> five_random_integers = Stream.generate(get_random_int).limit(5).collect(Collectors.toList());
    System.out.println();
    five_random_integers.forEach( System.out::print );
  }
}
