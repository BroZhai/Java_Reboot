package Java_Reboot.Builtin_Classes;
import java.util.regex.Pattern; // 导入Pattern类
import java.util.regex.Matcher; // 导入Matcher类

public class Pattern_and_Matcher {
  
  public static void main(String[] args) {
    /* Pattern 和 Matcher类是搭配在一起使用的, 一个用于定义正则表达式, 另一个则是操作'匹配序列'
     * 这里就混在一起实验了, 主要是要知道正则表达式该怎么写 (相关知识请自行查阅'编译原理')
     */
    // 定义各种正则表达式
    Pattern candy_re = Pattern.compile("candy"); // 完全匹配"candy"
    Pattern bxkx_re = Pattern.compile("b.k."); // 匹配 b_k_ , _表示'任意字符'
    Pattern symbol_re = Pattern.compile(".\\*\\*k"); // 完全匹配"_**k" , 如c**k, l**k, b**k
    /* (Tips: 在正则表达式中, 正常的 \特殊符号 表示'匹配一个特殊符号', 
    *  但在Java中直接写 \特殊符号 会和原来定义的各种 '\'冲突, 如\n, 故要双写'\\')
    */
    Pattern any_in_range = Pattern.compile("[as][wi][fm][cp]"); // 只要中[]内的任意单字符就Ok, 如 awmc, simp, aifp等...
    Pattern any_not_range = Pattern.compile("[^su][^md][^0-9][^A-Z]"); // 和上面反过来, 不能是[]中的字符! 如 sm0A, ud3D, sdZ 都中了, 不会匹配; aaaa, 6fk9 就ok
    /* Tips: 可以用 '-' 来快速表示一个范围, 如 [a-z]全小写字母, [0-9]所有数字*/

    // 准备不同类型的内容
    String word = "Ciallo";
    String noise = "Mrio";
    String date = "2005-06-27";
    String email = "bakatekon@114514.com";
    String password = "114514yaju";
    String phone_num = "14139191880";

    String simple_sentence = "Hello, my name is Cirno. My friends are baka_tekon and baka_wing.";
    // System.out.println(Pattern.matches(my_RE, simple_word));
  }

}
