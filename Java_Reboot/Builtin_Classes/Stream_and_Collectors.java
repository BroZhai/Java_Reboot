package Java_Reboot.Builtin_Classes;

import java.util.stream.Stream; // 导入Stream类
import java.util.stream.Collectors; // 导入Collectors工具包
import java.util.stream.Collector; // Collectors的父类接口, 没有用到但是要了解

// 配合使用的'函数式接口'
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.function.Predicate;
import java.util.function.Function;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;

// 其他工具类
import java.lang.Math;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional; // 数据'可能有可能无'

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

    System.out.println("\n接下来让我们看看'终端操作'");
    /*终端操作 (这里单独把.collect()方法拎出来, 放在最下面和Collectors工具类一起搞)*/
    // reduce() Stream流的归约操作
    BinaryOperator<Integer> descend_pace = (current_value, next_value) -> current_value - next_value;
    Stream<Integer> altitude_stream = Stream.of(2500, 500, 400, 300, 200, 100, 50, 30, 20, 10, 5); // Retard!!
    Optional<Integer> last_altitude = altitude_stream.reduce(descend_pace); // 注意此时altitude_stream已被消耗!
    last_altitude.ifPresent((last_value) -> {
      System.out.println("最后的高度值存在! 值为: " + last_value); // 885 correct!
    });

    // count() 统计Stream流元素个数
    person_stream = Stream.of(bing, taike, candy);
    int headcounts = (int)person_stream.count(); // 默认返回long类型, 可以自行转成int
    System.out.println("headcounts中共有 " + headcounts + " 个Baka");

    // anyMatch() 任意true -> true / noneMatch() 全false -> true, AllMatch() 全true -> true
    person_stream = Stream.of(bing, taike, candy);
    Predicate<Person> candy_exists = (current_person) -> {
      return  current_person.get_name().equals("Pinkcandy");
    };
    boolean pinkcandy = person_stream.anyMatch(candy_exists); // 只要有元素匹配true    
    if (pinkcandy) {
      System.out.println("PinkCandy存在!");
    }

    person_stream = Stream.of(bing, taike); // 移除candy, 测试noneMatch()
    boolean no_pinkcandy = person_stream.noneMatch(candy_exists);  // 全部匹配false，返回true
    if(no_pinkcandy){
      System.out.println("修改后的person_stream中没有匹配到Pinkcandy");
    }

    // findFirst() 找到返回第一个元素 / findAny() '针对并行流'随机返回一个元素 (顺序流永远都是第一个, 了解即可)
    Person cirno = new Person("Baka", 9);
    
    person_stream = Stream.of(bing, candy, cirno, taike);
    // Optional<Person> first_person = person_stream.findAny();
    Optional<Person> first_person = person_stream.findFirst();
    first_person.ifPresent( (person_obtained) -> {
      System.out.println("流中的第一个幸运儿为: " + person_obtained.get_name());
    });

    System.out.println();
    // min() / max() 取最小最大值方法, 需传入Comparator配合进行排序
    Stream<Integer> random_ints = Stream.generate(get_random_int).limit(7); // 引用35行定义的'取随机数方法', 生成7个 1-10的随机数
    List<Integer> ints_list = random_ints.collect(Collectors.toList()); // 将取得的随机结果放在list中 (备份stream流中的数据, 防止'关闭'之后找不回来了 XD)
    System.out.print("本次生成的random_ints流为: ");
    ints_list.forEach((current_value) -> System.out.print(current_value + " "));

    random_ints = ints_list.stream(); // 重新赋值数据流, 准备找最小值
    Optional<Integer> obtained_min_value = random_ints.min(Comparator.naturalOrder()); // 自然升序排序, 哥们懒得再写一遍了 XD (注意random_ints流被消耗)
    obtained_min_value.ifPresent( (value) -> {
      System.out.println(", 其中最小的值为: " + value);
    });



    // Collectors工具类方法 (只用静态方法), 常搭配 Stream中的 .collect()终端操作一齐使用
    System.out.println("\n现在我们来看看");
    /*  toList() / toSet() 上面用到过了, 这里skip */

    // toCollection 转换为抽象Collection集合类, 或者是声明成其子类 (如ArrayList)
    person_stream = Stream.of(bing, candy, cirno, taike);
    ArrayList<Person> person_ArrayList = person_stream.collect(Collectors.toCollection(ArrayList::new)); // 转为Collection类, 但声明了具体要转的'子对象'为ArrayList, 返回ArrayList对象
    System.out.println("取得peron_ArrayList下标2的person名称为: " + person_ArrayList.get(2).get_name());


  } // main函数结束
}
