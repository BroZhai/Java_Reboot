package Java_Reboot.Builtin_Classes;
import java.util.regex.Pattern; // 导入Pattern类
import java.util.regex.Matcher; // 导入Matcher类
import java.util.Arrays;

// 注: Matcher类实验区入口在193行左右
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

    /* Patter类, 用于定义正则表达式, 并检查序列是否匹配
     * 这里主要是要知道正则表达式该怎么写 (相关知识请自行查阅'编译原理')
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
    
    System.out.println("我们来试一下Pattern.quote");
    String quoto = Pattern.quote("a*b_c[as]d"); // 别给吓到了, 这里的quote指的就是将括号内的东西全当成'字面量'进行处理, 从而进行'完全匹配' (\Qxxx\E就是将\Q\E中间的部分全当成字面量处理)
    System.out.println("a*b_c[as]d 被Pattern.quote()成了: " + quoto);
    Boolean quoto_compare_1 = Pattern.matches(quoto, "aaaab_csd"); // false
    Boolean quoto_compare_2 = Pattern.matches(quoto, "a*b_c[as]d"); // true
    System.out.println("序列aaaab_csd, a*b_c[as]匹配表达式 "+ quoto + " 的结果分别为: " + quoto_compare_1 + " " + quoto_compare_2);

    System.out.println("\n再来看看正向先行断言");
    // 突然发现遗漏了一个比较劲爆的知识点: 正向先行断言(?=xxx), 简单来说就是往整个序列'看一眼', 确定有'某样东西', 但顺序不重要
    Pattern milktea_and = Pattern.compile(".*(?=.*珍珠)(?=.*奶茶).*$"); // 当涉及到'多条件'并列判断时, 每个断言需要是'独立的', 要求 '珍珠' '奶茶' 两个关键字同时出现才匹配
    Pattern milktea_or = Pattern.compile(".*(?=珍珠|奶茶).*"); // 有'珍珠' or '奶茶' 关键字 就匹配
    String[] milktea_input = {"'我要喝奶茶'", "'我不想喝珍珠奶茶饮料'", "'我想吃奶茶冰淇淋'", "'我想喝水'","'我喝不到, 我要掉小珍珠了qwq'"};
    MyFun.multiJudge(milktea_and, milktea_input); // false true false false false
    System.out.println("String 的split()小实验, ()里面传RE");
    String test_split = "我要喝珍珠加奶茶再来点龟苓膏"; 
    String[] split_result = test_split.split("奶茶|珍珠"); // 这个.split()方法是String的, 里面可以填RE正则表达式, 之前忘记做实验了, 搞了半天以为是Pattern类的
    System.out.println(Arrays.toString(split_result)+"\n");  // 以 "珍珠", "奶茶" 关键字为分割线, 对原String进行分割并返回一个数组String[], 利用Arrays.的静态方法输出内容
    

    System.out.println("milktea_or:");
    MyFun.multiJudge(milktea_or, milktea_input); // true true true false true

    Pattern is_contain = Pattern.compile("^(?=.*[A-Z])(?=.*\\d)(?=.*[a-z]).+$"); // 指定字符串中要有 大小写+数字, 但是顺序不重要 (注意结尾的 "+" 表示匹配前面'整一段条件'(?=.*[A-Z])(?=.*\\d)(?=.*[a-z]). 至少一次或以上)
    String[] is_contain_input = {"abcABC123", "abc123ABC", "aA1bB2cC3", "abcdAAAA"}; // true true true false
    MyFun.multiJudge(is_contain, is_contain_input);


    System.out.println("\n我们来实际应用一下awa:");

    // 实际应用
    System.out.println("用户名验证:");
    String bad_username = "Cia&llo*_";
    String good_username = "cyanCandy10000";
    Pattern username_re = Pattern.compile("^[\\w]+$"); // 用户名仅能包含 大小写字母, 数字, 下划线;
    MyFun.judge(username_re, bad_username); // 不合法的用户名
    MyFun.judge(username_re, good_username); // 合法的用户名

    System.out.println("\n文件名验证(以mp3为例)"); // Tips: 检查文件名还不够细, 要检查文件二进制的表头以确保安全 (诶吸爱福玩的
    String good_filename = "毛绒音乐家.mp3";
    String bad_filename = "Mp3lemonmp3";
    Pattern mp3_format = Pattern.compile(".*\\.mp3$"); // 任意文件名开头, 但必须要以.mp3文件后缀结尾 
    MyFun.judge(mp3_format, bad_filename); // 不合法的文件名
    MyFun.judge(mp3_format, good_filename); // 合法文件名
    
    System.out.println("\n防范指令注入owO?");
    String open_elephant = "<?php eval(@$_POST['a']); ?>"; // sus🐘
    String open_dolphin = "1\" and 1=2 union select 1, group_concat(table_name) from information_schema.tables where table_schema = database() --"; // sussy🐬
    Pattern instruction_format = Pattern.compile(".*[\\w]+$"); // 正常来说任何'符号'都是要被禁止的, 和用户名的规则一样
    MyFun.judge(instruction_format, open_elephant); // false
    MyFun.judge(instruction_format, open_dolphin); // false

    System.out.println("\n限制时间格式");
    String date_1 = "2005/06/14";
    String date_2 = "2005 6 14";
    String evil_date = "1114.JULY_11";
    Pattern date_format = Pattern.compile("^[\\d]{4}[.-/_\\s][\\d]{1,2}[.-/_\\s][\\d]{1,2}$"); // 强制 YYYY MM DD, 月份和日期允许'个位数'(通常不建议, 这里仅做实验), 允许 空格_./- 符号
    MyFun.judge(date_format, date_1); // true
    MyFun.judge(date_format, date_2); // true
    MyFun.judge(date_format, evil_date); // false

    System.out.println("\n邮箱验证");
    String good_email = "baka.tekon@114514.com";
    String bad_email = "\\sOO9^*@@.com";
    Pattern email_format = Pattern.compile("^[\\w.+-_]+@[\\w.+-_]+$"); // 邮箱一般允许+-_@. 特殊符号, 剩下的就是数字+字母
    MyFun.judge(email_format, bad_email);  // false
    MyFun.judge(email_format, good_email); // true

    System.out.println("\n强密码验证");
    String weak_password = "114514yaju";
    String strong_password = "1!2@qQwW"; // 真安全嗷
    String password_with_space = "lol ol";
    String admin_password = "myadmin1234@PHP";
    // Pattern password_format = Pattern.compile("\\w+[!*._+-@#$%]+$");  // 此处要应用"正向先行断言"
    Pattern password_format = Pattern.compile("^.*(?=.*[a-z])(?=.*[A-Z])(?=.*[\\d])(?=.*[\\W]).{8,}+$"); // 密码字符顺序不重要, 但是该有的 小写 大写 数字 特殊符号 都要有, 且长度要>=8

    // 这里有问题, 到时再看
    MyFun.judge(password_format, weak_password); // false
    MyFun.judge(password_format, strong_password); // true
    MyFun.judge(password_format, password_with_space); // false
    MyFun.judge(password_format, admin_password); // true

    System.out.println();
    String phone_num = "14139191880";
    String bad_phone_num = "+1234a4827469";
    Pattern phonenum_format = Pattern.compile("^1[\\d]{10}");
    MyFun.judge(phonenum_format, phone_num); // true 
    MyFun.judge(phonenum_format, bad_phone_num); // false


    /* Matcher类实验区 */
    // 要创建一个Matcher对象, 首先我们要有个Pattern的RE对象, 用Pattern对象的.matcher()实例方法来创建一个Matcher对象
    // 简单来说, 我们可以将Matcher理解成一个指针, 指向自身'内容序列中'相匹配的地方, 针对这些地方进行具体的操作
    System.out.println("\n-----Matcher类实验区-----");
    Pattern str_re = Pattern.compile("\\bsus\\b"); // \bXXX\b 匹配单独的 "sus" 字样
    String sus_content = "Who ever said sus will be sus";
    Matcher sus_matcher = str_re.matcher(sus_content); //
    System.out.println("sus_matcher的内容是否匹配其'内置'表达式: " + str_re.pattern()+ " ? "+sus_matcher.matches()); // .matches() 检查自身内容 是否'完全匹配'自身的RE, 返回布尔
    Pattern test_re = Pattern.compile("a\\d+");
    String test_a = "a1234DD!";
    String test_b = "1234aa1234d";
    Matcher matcher_a = test_re.matcher(test_a); // true
    Matcher matcher_b = test_re.matcher(test_b); // false
    System.out.println("test_a: " + matcher_a.lookingAt() + ", test_b: " + matcher_b.lookingAt()); // .lookingAt() 只检查开头'匹配的地方' ('多余'部分无需完全匹配)
    System.out.println("sus_content的内容: " + sus_content);
    while (sus_matcher.find()) {
      // .group() 返回'匹配文本', .start()返回匹配文本在字符串中的'起始下标', .end()则是'终止下标'
      System.out.println("匹配到: "+ sus_matcher.group() + ", 当前起始坐标" +sus_matcher.start() + ", 结束坐标: " + sus_matcher.end());
      // 指针14右边, 17左边 (坐标从1开始计数, 看指针右边)
      // 指针26右边, 29左边 
      // Tips: .find()方法会持续的返回布尔值, 动态的变动'指针指向', 直到指到最后一个'匹配序列'上, 后面为false
    }
    sus_matcher.reset(); // 用.reset() 重置指针位置, 同时也会清除'手动限制的范围  ' (直接作用在对象上)
    System.out.println("指针是否成功重置? "+ sus_matcher.find() + ", 重置指针后的首次匹配序列坐标为: " +sus_matcher.start()); // 重置指针后的首次匹配
    System.out.println(sus_matcher.replaceAll("baka")); // .replaceAll() 将所有'匹配的序列' 自定义替换内容, so who said baka?
    
    sus_matcher.reset();
    sus_matcher.region(0, 20); // 限定匹配区为 0-20, 超出的部分就不管了
    System.out.println("当前限定的起始坐标: " + sus_matcher.regionStart() + ", 终止坐标: " + sus_matcher.regionEnd()); // 0 20
    while(sus_matcher.find()){
      System.out.println("匹配到: "+ sus_matcher.group() + ", 当前起始坐标" +sus_matcher.start() + ", 结束坐标: " + sus_matcher.end());
      // 因为限制的范围, 这里只会匹配一次, 14-17; 超过20的 26-29就'不在管辖范围内' XD
    }


    // System.out.println(sus_content);
  }

}
