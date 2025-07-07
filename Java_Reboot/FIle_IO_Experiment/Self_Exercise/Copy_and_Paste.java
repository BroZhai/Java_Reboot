package Java_Reboot.FIle_IO_Experiment.Self_Exercise;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
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
    Scanner user_input = new Scanner(System.in);
    Pattern single_number = Pattern.compile("[\\d]{1}");
    boolean range_judge = user_input.findInLine(single_number) == null; // 找不到, true
    while(range_judge){
      System.out.print("无效输入, 请重试: ");
      user_input.nextLine();
      range_judge = user_input.findInLine(single_number) == null; // 傻帽了, 忘记给break的循环条件赋值了 XD

    }
    
  }
}
