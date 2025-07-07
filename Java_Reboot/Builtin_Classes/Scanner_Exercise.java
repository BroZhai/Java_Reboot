package Java_Reboot.Builtin_Classes;
import java.util.Scanner; // 导入Scanner类
import java.util.InputMismatchException; // 输入数据'不匹配'异常

import java.util.Arrays; // 静态数组相关方法


public class Scanner_Exercise {
    
  public static void main(String[] args) {
    // 先来看看最简单的'控制台输入'
    System.out.print("我们来玩下Scanner, 请输入任意内容, 最好有空格: ");
    Scanner sc = new Scanner(System.in);
    if (!sc.nextLine().isEmpty()) { //  直接敲回车时, 默认你还没机会'写'下一行时, 程序就判断你'直接敲了回车' (毛都没输)
      int read_couter = 0;
      while(read_couter<3 && sc.hasNext()){
        System.out.println("你输入的第"+read_couter+"个词为: " + sc.next()); // .next() 返回'下个词'
        read_couter++;
      }
      System.out.println("上述内容只会打印'前三个词'awa");
      sc.close();
    }else{
      System.out.println("用户屁都没输qwq...");
    }

    // 现在是Scanner直接读String
    String two_lines = "This is the first Line of test \nAnd here is the second one.";
    Scanner string_reader = new Scanner(two_lines);
    System.out.println("two_lines第一行的内容为: " + string_reader.nextLine()); 
    System.out.println("第二行的内容为: " + string_reader.nextLine());
    System.out.println("还有内容可以读吗? " + string_reader.hasNextLine()); // false
    
    // 玩点花的, 直接读'字面量', 自定义数据之间用','分割 (注意: 只能读System.in)
    double[] num_arr = {11.4, 5.14, 1919.810};
    System.out.println(Arrays.toString(num_arr)); // [11.4,5.14, 1919.81] 
    Scanner number_reader = new Scanner(Arrays.toString(num_arr)); 
    number_reader.useDelimiter("[\\[\\]\\,\\s]+"); // 分割符包含[, ], 空格 和 逗号(,) (巧用正则表达式)
    try{
        while(number_reader.hasNextDouble()){
          System.out.println(number_reader.nextDouble());
        }
    }catch(InputMismatchException e){ // 当数据类型不匹配时, 就会抛出 InputMismatchException异常
      System.out.println("检测到输入数据类型不匹配!");
    }

  }


}
