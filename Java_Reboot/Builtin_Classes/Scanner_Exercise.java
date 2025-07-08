package Java_Reboot.Builtin_Classes;
import java.util.Scanner; // 导入Scanner类
import java.util.InputMismatchException; // 输入数据'不匹配'异常

import java.util.Arrays; // 静态数组相关方法
import java.util.regex.Pattern; // 正则表达式, 设定'分隔符'&匹配内容相关

public class Scanner_Exercise {
    
  public static void main(String[] args) {
    // 先来看看最简单的'控制台输入'
    System.out.print("我们来玩下Scanner, 请输入任意内容, 最好有空格(可以直接回车跳过): ");
    Scanner sc = new Scanner(System.in); // 注: 这一行并不会'直接唤起'输入
    if (!sc.nextLine().isEmpty()) { //  直接敲回车时, 默认你还没机会'写'下一行时, 程序就判断你'直接敲了回车' (毛都没输)
      int read_couter = 0;
      while(read_couter<3 && sc.hasNext()){
        System.out.println("你输入的第"+read_couter+"个词为: " + sc.next()); // .next() 返回'下个词'
        read_couter++;
      }
      System.out.println("上述内容只会打印'前三个词'awa");
      sc.close(); // 释放系统资源
    }else{
      System.out.println("用户屁都没输qwq...\n" );
    }

    // 现在是Scanner直接读String
    String two_lines = "This is the first Line of test \nAnd here is the second one.";
    Scanner string_reader = new Scanner(two_lines);
    System.out.println("two_lines第一行的内容为: " + string_reader.nextLine()); 
    System.out.println("第二行的内容为: " + string_reader.nextLine());
    System.out.println("还有内容可以读吗? " + string_reader.hasNextLine()); // false
    string_reader.close(); 

    System.out.println();
    // 玩点花的, 直接读'字面量', 自定义数据之间用','分割
    double[] num_arr = {11.4, 5.14, 1919.810};
    System.out.println("Scanner现准备从 " + Arrays.toString(num_arr) + " 中读取数据, 需要定义'出现的无关符号'作为'分隔符'进行分割"); // [11.4,5.14, 1919.81] 
    Scanner number_reader = new Scanner(Arrays.toString(num_arr)); 
    number_reader.useDelimiter("[\\[\\]\\,\\s]+"); // 分割符包含[, ], 空格 和 逗号(,) (巧用正则表达式)
    System.out.println("已定义分隔符有 '[' ']' ',' '空格' ");
    System.out.println("更直球的RE表达: " + number_reader.delimiter());
    try{
      System.out.println("读出来的double数据:");
        while(number_reader.hasNextDouble()){
          System.out.println(number_reader.nextDouble());
        }
    }catch(InputMismatchException e){ // 当数据类型不匹配时, 就会抛出 InputMismatchException异常
      System.out.println("检测到输入数据类型不匹配!");
    }
    number_reader.close();

    // 利用RE让Scanner从读到的信息中'摘出'对应数据
    String message = "那好啊, 到时下午11点45分你打我手机11451419198就好了";
    Scanner phone_extract = new Scanner(message);
    Pattern phone_pattern = Pattern.compile("1[0-9]{10}"); // 定义一个Pattern的RE表达式对象
    String phone_number = phone_extract.findInLine(phone_pattern); // .findInLine(), 直接通过正则表达式在数据中 直接找'匹配项', 并直接返回为String
    // Tips: 上面的findInLine方法其实就像是 Scanner的 内的'Matcher'
    if (phone_number != null) {
      System.out.println("成功提取到电话号码: " + phone_number);
    }else{
      System.out.println("没有摘取到有效的电话号码...");
    }
    phone_extract.close();

  }


}
