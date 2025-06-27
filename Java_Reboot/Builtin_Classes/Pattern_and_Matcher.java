package Java_Reboot.Builtin_Classes;
import java.util.regex.Pattern; // 导入Pattern类
import java.util.regex.Matcher; // 导入Matcher类

public class Pattern_and_Matcher {

  // 封装一些方法, 不然下面得写疯了
  class MyFun{
    public static void judge(Pattern regex, String content){ // 单个序列校验
      
      Boolean result = Pattern.matches(regex.pattern(), content);
      System.out.println("序列"+content+"匹配表达式 "+ regex.pattern() + " 的结果为: " + result);
    }
   
    public static void multiJudge(Pattern regex, String[] content_arr){ // 一堆序列校验
      // Boolean[] result = new Boolean[content_arr.length];
      for(int i=0; i<content_arr.length; i++){
        // result[i] = Pattern.matches(regex.pattern(), content_arr[i]); // 挨个判断给最终的Boolean列表赋值
        Boolean current_judge = Pattern.matches(regex.pattern(), content_arr[i]);
        System.out.println("序列"+content_arr[i]+"匹配表达式 "+regex.pattern()+" 的结果为: " + current_judge);
      }
      System.out.println();
    }
    
  }
  
  public static void main(String[] args) {

    /* Pattern 和 Matcher类是搭配在一起使用的, 一个用于定义正则表达式, 另一个则是操作'匹配序列'
     * 这里就混在一起实验了, 主要是要知道正则表达式该怎么写 (相关知识请自行查阅'编译原理')
     */
    // 定义各种正则表达式
    Pattern candy_re = Pattern.compile("candy"); // 完全匹配"candy"
    System.out.println("candy是否匹配candy_re? " + Pattern.matches("candy", "candy")); //.matches(正则表达式, 输入序列), true
    System.out.println("pinkcandy是否匹配candy_re? " + Pattern.matches("candy","pinkcandy")); // false
    // 我靠, 这样敲太费劲了, 封装个函数去
    
    System.out.println();
    Pattern bxkx_re = Pattern.compile("b.k."); // 匹配 b_k_ , _表示'任意字符'
    MyFun.judge(bxkx_re, "baka"); // true, 直接用这个MyFun.judge太爽了
    String[] bxkx_input = {"kaba", "b0k*"};
    MyFun.multiJudge(bxkx_re, bxkx_input); // false true
    // System.out.println("kaba " + MyFun.judge(bxkx_re, "kaba") + ", b0k* " + MyFun.judge(bxkx_re, "b0k*")); // false, true

    Pattern symbol_re = Pattern.compile(".\\*\\*k"); // 完全匹配"_**k" , 如c**k, l**k, b**k
    String[] symbol_input = {"c**k","l**k","fu*k"}; // true true false
    MyFun.multiJudge(symbol_re, symbol_input); 
    /* (Tips: 在正则表达式中, 正常的 \特殊符号 表示'匹配一个特殊符号', 
    *  但在Java中直接写 \特殊符号 会和原来定义的各种 '\'冲突, 如\n, 故要双写'\\' 单独出一个反斜杠'\')
    */
    Pattern any_in_range = Pattern.compile("[as][wi][fm][cp]"); // 只要中[]内的任意单字符就Ok, 如 awmc, simp, aifp等...
    String[] any_in_input = {"awmc", "simp", "aifp", "abcd"}; // true true true false
    MyFun.multiJudge(any_in_range, any_in_input); 
    Pattern any_not_range = Pattern.compile("[^su][^md][^0-9][^A-Z]"); // 和上面反过来, 不能是[]中的字符! 如 sm0A, ud3D, sdZ 都中了, false, 不会匹配; aaaa, 6fk9 就ok
    String[] any_not_input = {"sm0A", "sm0a", "66Aa", "xxA"}; // false false true false
    MyFun.multiJudge(any_not_range, any_not_input); 
    /* Tips: 可以用 '-' 来快速表示一个范围, 如 [a-z]全小写字母, [0-9]所有数字*/

    
    Pattern slash_digit = Pattern.compile("\\d{3}"); // \d匹配数字0-9, 等价于[0-9], 后面可以跟个花括号{}表示连续位数,  该RE匹配3位连续数
    String[] digit_input = {"123","1a3","000"}; // true false true
    MyFun.multiJudge(slash_digit, digit_input);
    Pattern slash_word = Pattern.compile("b\\wd"); // \w匹配 大小写字符 + 数字 + 下划线_, \w+ 匹配'一个单词', 该RE匹配 b1d, bed, bAd
    String[] word_input = {"b*d", "bid", "b1d", "b_d"}; // false true true true
    MyFun.multiJudge(slash_word, word_input);
    Pattern slash_space = Pattern.compile("ba\\ska"); // \s匹配 空格, 该RE匹配"ba ka"
    String[] space_input = {"baka","ba ka"}; // false true
    MyFun.multiJudge(slash_space, space_input);

    Pattern astrek = Pattern.compile("go*d"); // * 连续匹配'前一个字符', 可以为空集, 该RE匹配 gd, god, good...
    String[] astrek_input = {"gd","god","good","goood"}; // true true true true
    MyFun.multiJudge(astrek, astrek_input);
    Pattern plus = Pattern.compile("no+d"); // + 同上, 但'至少出现一次', 该RE匹配nod, nood, noood...
    String[] plus_input = {"nd","nod","nood","noood"}; // false true true true
    MyFun.multiJudge(plus, plus_input);
    Pattern question_mark = Pattern.compile("lo?k"); // ? 表示仅出现 0 或 1次, 该RE匹配 lok, look
    String[] question_input = {"lok","look","loook"}; // true true false
    MyFun.multiJudge(question_mark, question_input);
    Pattern quantity = Pattern.compile("mo{2,}d"); // 表示匹配'前一个字符'2次或以上, 去掉逗号(,)就是'精准匹配两次', 该RE匹配moood, mooood...
    String[] quantity_input = {"md","mod","mood","moood","mooood"}; // false false true true true
    MyFun.multiJudge(quantity, quantity_input); 
    Pattern quantity_refined = Pattern.compile("po{2,4}p"); // 匹配'前一个字符' 2次-4次, 不在区间的就不算, 该RE匹配 pooop, poooop, pooooop
    String[] refined_input = {"pp","pop","poop","pooop","poooop","pooooop","poooooop"}; // false false true true true false false
    MyFun.multiJudge(quantity_refined, refined_input);
    
    Pattern start_string = Pattern.compile("^Baka.*"); // 匹配任何以'Baka'开头的"字符串" (只要开头对上就好), 注意这里 ".*" 的妙用(任意字符延后)
    String[] start_input = {"BakaWing","Baka Candy","SmartTekon"}; // true true false
    MyFun.multiJudge(start_string, start_input);
    Pattern end_string = Pattern.compile(".*wei$"); // 匹配任何以'wei'结尾的"字符串"
    String[] end_input = {"Lauwei","HuaWei","weiwei"}; // true false true
    MyFun.multiJudge(end_string, end_input);

    // Pattern slash_border = Pattern.compile("\b\b"); // 这个好像Java的Pattern类已经实现了, 没啥用, 了解\b单独特指一个'单词'就好
    // String border_input = "This is a baka";
    // MyFun.judge(slash_border, border_input);

    Pattern group = Pattern.compile("(ex)*g"); // ()表示一个分组, 这里将ex变成了一组, RE匹配 g, exg, exexg, exexexg
    String[] group_input = {"g","exg","exexg","exexexg"}; // true true true true
    MyFun.multiJudge(group, group_input);



    // 准备不同类型的内容
    String word = "Ciallo";
    String noise = "Miro";
    String date = "2005-06-27";
    String email = "bakatekon@114514.com";
    String password = "114514yaju";
    String phone_num = "14139191880";

    String simple_sentence = "Hello, my name is Cirno. My friends are baka_tekon and baka_wing.";
    // System.out.println(Pattern.matches(my_RE, simple_word));
  }

}
