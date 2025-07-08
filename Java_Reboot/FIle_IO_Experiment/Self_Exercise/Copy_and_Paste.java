package Java_Reboot.FIle_IO_Experiment.Self_Exercise;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Copy_and_Paste {
  // 在本Java中, 尝试来自行实现一下 文件/文件夹 相关的功能

  public static void main(String[] args) throws IOException {
    String directory = "Java_Reboot/File_IO_Experiment/Self_Exercise";
    File welcome_text = new File(directory,"welcome.txt");
    if(welcome_text.exists()){ // 文件存在
      Scanner welcome_reader = new Scanner(welcome_text);
      while(welcome_reader.hasNextLine()){
        System.out.println(welcome_reader.nextLine());
      }
      welcome_reader.close();
    }else{
      System.out.println("未能成功读取welcome.txt");
    }

    System.out.print("\n请提供选择对应数字进行操作: ");
    Scanner user_input = new Scanner(System.in); // 这一行并不会立即唤起'输入'!
    Pattern single_number = Pattern.compile("[0-9]{1}");
    String current_line;
    // Matcher number_matcher = single_number.matcher(current_line);
    // System.out.println("行内容: " + current_line + " 的number_matcher结果: " + number_matcher.matches());

    // boolean range_judge = user_input.findInLine(single_number) == null; // 输入多位数 23 时会'匹配两个', 2 3 给自己挖大坑了
    
    while(true){
      current_line = user_input.nextLine(); // 读取当前行输入
      Matcher number_matcher = single_number.matcher(current_line);
      if(current_line.length()==1 && number_matcher.matches()){ // 用户只输了一个数字, 和matcher的严格匹配对上了, 顺带校验了'输入长度'作为保险
        System.out.println("输入有效!");
        break;
      }else{
        System.out.print("输入无效, 请重试: ");
      }
    }

      // String line = user_input.nextLine().trim(); // 读取当前行
      // // number_matcher = single_number.matcher(line);
      // System.out.println("当前读到的行内容为: " + line);
      // System.out.print("请输入下一行内容: ");
      // line = user_input.nextLine().trim();
      // System.out.println("\n下一行读到的内容为: " + line);
      // System.out.println();
    
    // }
    
  }
}
