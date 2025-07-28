package Java_Reboot.Builtin_Classes;

import java.util.stream.Stream; // 导入Stream类
import java.util.stream.Collectors; // 导入Collectors工具包

// 配合使用的'函数式接口'
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

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
    // generate(Supplier提供'返回数据') 生成无限长度的流, limit()进行'生成次数限制'
    Supplier<Integer> get_random_int = () -> (int) (Math.random()*10) +1; // 随机生成1-10 的Supplier (靠, Math.random()应该是先算再转, 之后注意运算优先级问题)
    List<Integer> five_random_integers = Stream.generate(get_random_int).limit(5).collect(Collectors.toList()); // 最后转List对象进行返回
    System.out.println();
    five_random_integers.forEach( System.out::print ); // 单独打印出'每个遍历到的'值 (方法引用一般不支持'自定义加参', 也就是说这里的print只会接收'一个输入参' print(T x) ,不允许自行追加'其他内容' 如 + "String")
    System.out.println();

    // iterate(初始值, UnaryOperator) 持续的对'输入值' 进行操作而生成的'无限流', 同上用limit()进行限制
    UnaryOperator<Integer> add_3_each = (input_value) -> input_value+3;
    List<Integer> five_ascending_integers = Stream.iterate(0, add_3_each).limit(5).toList();
    five_ascending_integers.forEach(System.out::print);
    System.out.println();

    // concat拼接两个Stream流对象 
    UnaryOperator<Integer> add_2_each = (input_value) -> input_value + 2 ;
    Stream<Integer> odd_stream = Stream.iterate(1, add_2_each); // 基数流
    Stream<Integer> even_stream = Stream.iterate(2, add_2_each); // 偶数流
    // Stream<Integer> concated_stream = Stream.concat(odd_stream, even_stream).limit(10); // 这样写将 一直生成odd_stream10次, 因为前面的流'没停'
    Stream<Integer> concated_stream = Stream.concat(odd_stream.limit(5), even_stream.limit(5)); // 正确写法 (在内部限制好两个流的'长度', 这样拼出来的流就是'定长'的了, 无需再在外面进行limit)
    List<Integer> concated_list = concated_stream.toList();
    concated_list.forEach((current_value) -> System.out.print(current_value + " ")); // 五个基数, 五个偶数 流拼接

    System.out.println("\n\n我们来看看Stream类中的'实操方法': ");
    // Stream类中'实操方法'

    
  } // main函数结束
}
