package Java_Reboot.Builtin_Classes;

import java.util.stream.Stream; // 导入Stream类
import java.util.stream.Collectors; // 导入Collectors工具包
import java.util.stream.Collector; // Collectors的父类接口, 没有用到但是要了解

// 配合使用的'函数式接口'
import java.util.function.Supplier; // 提供数据
import java.util.function.UnaryOperator; // 一元操作
import java.util.function.Predicate; // 条件判断
import java.util.function.Function; // 输入&输出
import java.util.Comparator; // 比较 & 排序
import java.util.function.BinaryOperator; // 二元操作
import java.util.function.Consumer; // 纯消耗数据

// 其他工具类
import java.lang.Math;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.IntSummaryStatistics; // Int类'统计工具'
import java.util.List; // 纯List父类
import java.util.Map; // 键值对父类
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
    System.out.println("\n现在我们来看看Collectors工具类, 常配合stream.collect()方法使用");
    /*  toList() / toSet() 上面用到过了, 这里skip */

    // toCollection() 转换为抽象Collection集合类, 或者是声明成其子类 (如ArrayList)
    person_stream = Stream.of(bing, candy, cirno, taike);
    ArrayList<Person> person_ArrayList = person_stream.collect(Collectors.toCollection(ArrayList::new)); // 转为Collection类, 但声明了具体要转的'子对象'为ArrayList, 返回ArrayList对象
    System.out.println("取得peron_ArrayList下标2的person名称为: " + person_ArrayList.get(2).get_name());

    // toMap() 转换为Map键值对映射, 利用Function直接搞俩'恒等函数'分别作为 键 值
    person_stream = Stream.of(bing, candy, cirno, taike);
    Map<String, Integer> person_map = person_stream.collect(Collectors.toMap(Person::get_name, Person::get_age)); // 指定Person对象 '人名为键', 年龄为值
    // System.out.println(person_map.toString());
    System.out.println(person_map.get(taike.get_name())); // 取得Map中键名为: Taike 的值(年龄), 19 ; 我靠这块补了我半天

    // joining() 拼接, 自定义元素流'分隔符', 前后缀符号
    Stream<String> customized_stream = Stream.of("A","B","C");
    String combined_str = customized_stream.collect(Collectors.joining()); // 直接拼接
    System.out.println("customized_stream用Collectors.joining()直接拼接的结果为: " + combined_str);
    customized_stream = Stream.of("A","B","C");
    String delimiter_prefix_suffix = customized_stream.collect(Collectors.joining("-","[","]")); // 指定分隔符为 -, 前缀 [ , 后缀 ]
    System.out.println("自定义分隔符, 前/后缀返回的结果为: " + delimiter_prefix_suffix);
    
    // groupingBy() 类似与数据库中的GroupBy, 传入一个Function<> 返回 '分组依据' (不想搞的太复杂直接用'恒等函数'), 最后返回一个Map对象
    // mapping() 将元素流 转换为 另一个类型的元素流, 同时设置'输出容器' (下游收集器)
    System.out.println();
    Person black_bing = new Person("BlackWing", 22);
    Person black_candy = new Person("BlackCandy", 18);
    person_stream =  Stream.of(black_bing, candy, taike, bing, black_candy);
    Map<Integer, List<String>> age_map = person_stream.collect(Collectors.groupingBy(Person::get_age, Collectors.mapping( // 这里前面的'age'即为'分类依据', 作为Map的键名<String, >
      Person::get_name, // 将List<Person> 转换成 List<String> (单独的把名字提出来, 输出到下方容器中) 
      Collectors.toList() // 收集并最终输出List<String>, 作为Map的键值 Map< , List<String>>
      )));
    age_map.forEach((age_category, name_list) -> {
      System.out.print("年龄" + age_category + "的有: " );
      name_list.forEach((name) -> { System.out.print(name + ", ");});
      System.out.println();
    });

    // partitioningBy() , 类似上面的groupingBy() , 但是前面的'键名'指定为Boolean, 也就是说true一个组, false一个组, 自己传入UnaryOperator进行判断分组
    System.out.println("\n现在来试试partioningBy(), 将Int流中的奇偶元素 单独各自过滤出来");
    // 沿用64行的 ascending_number 生成方法 和 73行的 Predicate判断 (filter_even_num)
    original_stream = Stream.iterate(1, ascending_number).limit(10); // 1 - 10 数据流
    Map<Boolean, List<Integer>> collected_result = original_stream.collect(Collectors.partitioningBy(filter_even_num));
    collected_result.forEach((is_even, num_list) -> {
      if (is_even) {
        // 展示偶数List
        System.out.println("collected_result中的偶数有: " + num_list.toString());
      }else{
        // 展示基数List
        System.out.println("collected_result中的基数有: " + num_list.toString());
      }
    });

    // summingInt() 求Int元素流总值, averageingInt()求Int流平均值
    original_stream = Stream.iterate(1, ascending_number).limit(10); // 1 - 10 数据流
    int stream_total_value = original_stream.collect(Collectors.summingInt(Integer::intValue)); // 必须用Integer的intValue方法对其中的 Integer对象进行拆箱
    System.out.println("original_stream中的总值为: " + stream_total_value);
    original_stream = Stream.iterate(1, ascending_number).limit(10);
    double stream_average_value = original_stream.collect(Collectors.averagingInt(Integer::intValue));
    System.out.println("original_stream中的平均值为: " + stream_average_value);

    // summarizingInt() 更常用的针对 纯int类 的 操作, 返回一个IntSummaryStatistics对象
    System.out.println("\n看看summarizingInt()的各种方法");
    original_stream = Stream.iterate(1, ascending_number).limit(10);
    IntSummaryStatistics int_stream_summary = original_stream.collect(Collectors.summarizingInt(Integer::intValue));
    System.out.println("int_stream_summary中共有 " + int_stream_summary.getCount() + " 个值");
    System.out.println("其中最大值为: " + int_stream_summary.getMax() + ", 最小值为: " + int_stream_summary.getMin() + ", 总和值为: " + int_stream_summary.getSum() + ", 平均值为: " + int_stream_summary.getAverage());
    System.out.println();

    // .minBy() / .maxBy() 取得最小, 最大值, 需要手动传入一个Comparator对元素流进行排序, 才能取得最小/最大值
    original_stream = Stream.iterate(1, ascending_number).limit(10);
    Optional<Integer> manual_get_max_value = original_stream.collect(Collectors.maxBy(Integer::compareTo)); // 这里直接用Integer(Number类)的通用方法 comapreTo() , 直接返回 1 0 -1, 方便的很
    manual_get_max_value.ifPresent( (value) -> {
      System.out.println("用maxBy()手动取得的最大值为: " + value);
    });

    // .reducing() 类似 Stream流中的reduce(), 针对 Stream流最后collect()的数据 进行'临门一脚'的 归约操作
    original_stream = Stream.iterate(1, ascending_number).limit(10);
    int sum_of_3_elements =original_stream.limit(3).collect(Collectors.reducing(0, (first_value, second_value) -> { // 定义初始值为0, 准备传入BinaryOperator进行操作
      return first_value + second_value; // 对过滤出来的 前3个元素 用 BinaryOperator进行累加, 
    }));
    System.out.println("Collectors.reducing()取得的前三个值的总和为: " + sum_of_3_elements);

    // .filtering() 同上, 这里是进行'临门一脚'的 最终过滤操作
    original_stream = Stream.iterate(1, ascending_number).limit(10);
    List<Integer> filtered_list = original_stream.collect(Collectors.filtering((value) -> value >= 5, Collectors.toList()));
    System.out.println("利用Collectors.filtereing()过滤后输出到filtered_list中的内容为: " + filtered_list.toString());

    // toUnmodfiableMap() 处理键值对后 返回一个不可修改的Map对象
    person_stream =  Stream.of(candy, taike, bing);
    Map<Integer, String> fixed_age_mapping =person_stream.collect(Collectors.toUnmodifiableMap(Person::get_age, Person::get_name, (old_val, new_val) -> new_val));
    System.out.println("fixed_age_mapping中的内容为: " + fixed_age_mapping.toString());
    try {
      fixed_age_mapping.put(99, "Baka");
    } catch (UnsupportedOperationException e) { // 对不可修改的Map对象尝试再次进行修改时, 会抛出java.lang.UnSupportedOperationException异常 (你想干甚?
      System.out.println("出现UnsupportedOperationException异常! 你是不是尝试在修改一个不可改的对象O.o?");
    }


  } // main函数结束
}
