package Java_Reboot.Builtin_Classes;

import java.util.stream.Stream; // 导入Stream类
import java.util.stream.Collector;
import java.util.stream.Collectors; // 导入Collectors工具包

// 配合使用的'函数式接口'
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.function.Predicate;
import java.util.function.Function;
import java.util.function.Consumer;

// 其他工具类
import java.lang.Math;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

// 导入自定义测试类Person
import Java_Reboot.Lambda_Experiement.BuiltIn_Functional_Interfaces.Person;

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
    /* 中间操作 (针对流元素的各种操作, 返回一个新Stream对象), 注意这里只是'条件定义', 在'终端操作'中才会正式开始拿数据 */

    // filter()过滤原数据流, 得到新数据流stream对象, 同时使老的数据流失效!
    UnaryOperator<Integer> ascending_number = (input_value) -> input_value+1;
    Stream<Integer> original_stream = Stream.iterate(1, ascending_number).limit(10);

    // List<Integer> original_stream_list = odd_stream.toList(); // 注意, toList()会消费 Stream对象 ! (后面尝试再用会报错, 抛出java.lang.IllegalStateException)
    System.out.print("当前orginal_stream的流中有: ");
    original_stream.forEach((val) -> System.out.print(val + " ")); // original_stream对象被消费
    // even_num_only_stream.toList().forEach(System.out::print); // 报错, 此时even流才会'开始'去拿数据, 结果发现origin_stream对象已经被消费了(上面的forEach)
    original_stream = Stream.iterate(1, ascending_number).limit(10); // 重新赋值 original_stream, 一会儿被even_num_only_stream对象'正式拿数据'(会被再次消费)

    Predicate<Integer> filter_even_num = (current_num) -> current_num%2==0;
    Stream<Integer> even_num_only_stream = original_stream.filter(filter_even_num); // 注意, 这里的filter也会'消费'原数据流对象(original_stream), 是个坑 
    System.out.print("\n使用Predicat+filter()过滤后的流为: ");
    even_num_only_stream.forEach(val -> System.out.print(val + " "));

    // skip()跳过前n个元素
    System.out.println();
    original_stream = Stream.iterate(1, ascending_number).limit(10);
    System.out.print("original_stream跳过前五个元素开始遍历: ");
    original_stream.skip(5).forEach((value) -> System.out.print(value + " "));

    // peek()在某一时刻(状态)对元素的内容进行查看
    System.out.println("\n");
    original_stream = Stream.iterate(1, ascending_number).limit(5); 
    System.out.print("重新赋值original: ");
    original_stream.forEach((value) -> {System.out.print(value + " ");});
    original_stream = Stream.iterate(1, ascending_number).limit(5); // 重新赋值
    System.out.println();
    Consumer<Integer> before = (value) -> System.out.print("当前值: " + value);
    Consumer<Integer> after = (value) -> System.out.print(", 修改后: " + value + "\n");
    List<Integer> result = original_stream.peek(before).map((value) -> value + 1).peek(after).collect(Collectors.toList()); // 注意, peek()单用不生效, 需要在结尾带一个'终端操作'才能'带飞'
    System.out.print("最终输出List: ");
    result.forEach((value) -> System.out.print(value + " "));
    


    // map()计算, 操作, 提取流中元素的属性
    System.out.println();
    Person candy = new Person("Pinkcandy", 18);
    Person taike = new Person("Taike", 19);
    Person bing = new Person("IceWing", 22);

    List<Person> person_list = Arrays.asList(candy, taike, bing);
    Function<Person,String> get_name = Person::get_name;
    List<String> names_in_list = person_list.stream().map(get_name).collect(Collectors.toList());
    System.out.print("\n从person_list的stream中取得的用map()取得的人名为: ");
    names_in_list.forEach((name) -> System.out.print(name + " "));
    System.out.println("\n");

    // flatMap()处理嵌套元素流
    List<List<String>> nested_list = Arrays.asList( // 外部List
      Arrays.asList("A","B"), // 内部List
      Arrays.asList("C","D")
      );
    List<String> content_in_nested_list = nested_list.stream().flatMap((inner_list) -> { // nested_list.stream() 外部stream
      return inner_list.stream(); // 内部stream
    }).collect(Collectors.toList());
    System.out.print("content_in_nested_list的内容为: " );
    content_in_nested_list.forEach((value) -> System.out.print(value + " "));

    // sorted() 排序流元素
    System.out.println();
    Comparator<String> string_len_compare = Comparator.comparingInt(String::length); // 比较字符串长度 & 排序 的 Comparator(升序)
    Stream<String> name_stream = Stream.of("baka","pinkcandy", "IceWing", "cirno");
    System.out.print("当前name_stream中有: ");
    name_stream.forEach((name) -> System.out.print(name +" "));
    System.out.println();
    name_stream = Stream.of("baka","pinkcandy", "IceWing", "cirno"); // 重新赋值, 上面forEach被消耗了
    System.out.print("利用sorted()排序后的内容为: ");
    name_stream.sorted(string_len_compare).forEach(
      (name) -> System.out.print(name + " ")
    );
    System.out.println();

    // distinct() 排除重复元素
    System.out.println();
    Person tech = new Person("Taike", 19); // Taike的'小号', 看看会不会被移除 (并不会, 这是一个'独立的对象'!)
    Stream<Person> person_stream = Stream.of(bing, taike, candy, tech , bing, bing, candy, candy);
    System.out.print("person_stream中有: ");
    person_stream.forEach((person_obj) -> System.out.print(person_obj.get_name() + " "));
    System.out.println();
    person_stream = Stream.of(bing, taike, candy, tech); // 重新赋值
    System.out.print("distinct()后的内容有: ");
    person_stream.distinct().forEach((person_obj) -> System.out.print(person_obj.get_name() + " ")); // 后面的bing bing candy candy被移除了, 但是tech的Taike 仍然存在(tech是独立对象!)
    System.out.println();

  } // main函数结束
}
