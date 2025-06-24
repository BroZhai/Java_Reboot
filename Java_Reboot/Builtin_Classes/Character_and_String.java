package Java_Reboot.Builtin_Classes;
import java.lang.Character; // 导入Character类
import java.lang.String; // 导入String类

public class Character_and_String {
  public static void main(String[] args) {
    /* Character字符类 */
    Character eng_char = 'a'; // 自动装箱
    Character chn_char = '啊';
    Character symbol = '?';

    System.out.println("eng_char为: "+ eng_char); // 自动拆箱, eng_char  -> eng_char.charValue()
    System.out.println("chn_char为: " + chn_char.charValue());
    String chn_string = chn_char.toString();
    Class<?> chn_string_type = chn_string.getClass(); // 查询chn_string的类型
    System.out.println("chn_char作为字符串返回: " + chn_string + ", 类型为: " + chn_string_type.getName());

    System.out.println("chn_char是数字吗: " + Character.isDigit(chn_char));
    System.out.println("symbol是字母吗: " + Character.isLetter(symbol));
    System.out.println("eng_char的Unicode字符值为: " + Character.getNumericValue(eng_char));
    System.out.println("eng_char大写: " + Character.toUpperCase(eng_char));

    System.out.println("\n以下开始为String类的实验");

    /* String 字符串类 */
    String cirno = new String("Baka"); // 正式创建String类对象
    // String cirno = "baka"; 自动装箱也支持
    Integer time = 9; // 用Integer类创建, 一会儿再查time的类型awa
    System.out.printf("cirno的值为: %s , 当前time的类型为: %s \n", cirno, time.getClass().getName()); // 模版字符串输出, 关键字printf

    char[] char_arr = {'b','b','k','k','b','k','k'};
    String arr2str = new String(char_arr); // 直接利用char[]数组也可以直接创建String对象
    // 或是用 String.copyToValue(char_arr) 静态方法也可以, 直接返回String字符串 
    System.out.print("arr2str当前内容: " + arr2str + ", index 0到3的子串为: " );
    System.out.println(arr2str.substring(0, 4)); // substring取值范围[0, 4), 拼到上面没换行的sout中

    //使用静态方法String.format() 创建'模版String', String.valueOf() 将'任意类型'转成String
    String time_str = String.valueOf(time); 
    String mix_msg = String.format("这条语句是%s 花了 %s 秒的时间写的, 此时time的类型是: %s \n", cirno, time_str, time_str.getClass().getName());
    System.out.println(mix_msg);

    //实例方法
    System.out.println("arr2str的长度为: " + arr2str.length()); // 7
    String nothing = "";
    System.out.println("nothing是空的吗? " + nothing.isEmpty()); // true
    String num1 = "123";
    Integer num2 = 123;
    System.out.println("num1和num2是否.equals()相等? " + num1.equals(num2) + ", 此处num2的类型为: " + num2.getClass().getName()); // false
    System.out.println("num1和num2是否.equals()相等? " + num1.equals(String.valueOf(num2)) + ", 此处num2的类型为: " + (String.valueOf(num2)).getClass().getName() + "\n"); // true

    String origin_str = "PiokCaody";
    System.out.println("origin_str replace()前: "+ origin_str);
    origin_str = origin_str.replace("o", "n"); // PinkCandy
    System.out.println("replace()单个字符: " + origin_str);
    System.out.println("现在origin_str仍以'Piok'开头吗? " + origin_str.startsWith("Piok")); // 大小写敏感注意!
    System.out.println("那么以Candy结尾吗? " + origin_str.endsWith("Candy"));
    System.out.println("Candy的首次出现下标为: " + origin_str.indexOf("Candy")); // 匹配"Candy"的首字母C 出现在index 4
    origin_str = origin_str.replace("Pink", "Cyan"); // CyanCandy
    System.out.println("replace()子串: " + origin_str);
    System.out.println("现在origin_str是否包含'Cyan'? " + origin_str.contains("Cyan"));
    System.out.println("全大写origin_str: " + origin_str.toUpperCase());
    char[] str2arr = origin_str.toCharArray(); // .toCharArray(); 将字符串 变成 字符数组[]
    for(int i=2; i<=6 ; i++){
      System.out.print(str2arr[i]);
      if (i == 6) {
        System.out.println("\n转换完的字符数组 str2arr[] index 2-6 打印完毕\n");
      }
    }

    String long_content = "Cirno,紫菜,IceWing";
    System.out.println("long_content长字符串的内容: "+ long_content);
    System.out.print("用','分割split()后返回的String[]数组的每个内容: ");
    String[] content_arr = long_content.split(","); // .split()按照括号内的'规则'对String进行分段, 返回String[] 数组
    for(String cur_con:content_arr){
      System.out.print(cur_con+" "); // Cirno Niko IceWing (共3个元素)
    }
    System.out.print("\n追加limit参数, 只保留'两段内容': ");
    String[] limited_arr = long_content.split(",",2); // 追加limit参数, 限制'内容分段数'
    for(String cur_con:limited_arr){
      System.out.print(cur_con+" "); // Cirno Niko,IceWing (共2个元素)
    }
    
  }
}
