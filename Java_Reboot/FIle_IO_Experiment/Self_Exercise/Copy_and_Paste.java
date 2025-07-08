package Java_Reboot.FIle_IO_Experiment.Self_Exercise;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Parameter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Copy_and_Paste {
  // 在本Java中, 尝试来自行实现一下 文件/文件夹 相关的功能
  Pattern yn_RE = Pattern.compile("^[YyNn]+$");
  Matcher yes_or_no;

  // 方法定义&实现区域
  public static void create_File(){
    System.out.println("\n欢迎来到'文件创建'");
    Scanner user_input = new Scanner(System.in);
    // Pattern symbol_set = Pattern.compile("^[^\\/:*?\"\\[\\]<>|]$"); // 文件名不能包含 \ / : * ? " < > | (把这些'不要'的符号滤掉)
    Pattern symbol_set = Pattern.compile(
        "^[^\\\\/:*?\\\"\\\\[\\\\]<>|]+$"    // 基础字符集（排除控制符和空格）
        + "(?<!\\.)"                         // 匹配的'文件名'?<!前面 不能以 \ . 开头
        + "(?!\\s)"                          // 匹配的'文件名'?!后面 不能以! 空格 结尾 (其实Windows会处理结尾的空格, 嘻嘻)
        + "{1,260}"                          // 长度限制
    );
    System.out.print("请输入文件名: ");
    String filename = user_input.nextLine(); // 读取用户输入的文件名
    Matcher filename_format = symbol_set.matcher(filename);
    boolean legal_filename = filename_format.matches();
    // System.out.println("\n所用输入的文件名合法吗? " + legal_filename);
    while(!legal_filename){
      System.out.print("输入文件名不合法, 请重新输入: ");
      filename = user_input.nextLine();
      legal_filename = symbol_set.matcher(filename).matches();
    }
    System.out.println("文件名校验成功!");

  }

  // 主函数
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
        // System.out.println("输入有效!");
        break;
      }else{
        System.out.print("输入无效, 请重试: ");
      }
    }
    
    switch (current_line) {
      case "1":
        Copy_and_Paste.create_File();
        break;

      case "2":
        
        break;
      case "3":
        
        break;

      case "4":
        
        break;

      case "5":
        
        break;
      case "6":
        
        break;
      case "7":
        
        break;

      case "8":
        
        break;
      case "9":
        
        break;

      case "0":
        System.out.println("\n欢迎下次光临~~");
        System.exit(0);
        break;
                
      default:
        break;
    }
    
  }
}
