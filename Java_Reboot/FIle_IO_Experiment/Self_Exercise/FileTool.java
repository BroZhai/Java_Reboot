package Java_Reboot.FIle_IO_Experiment.Self_Exercise;

// 文件IO相关
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
// 获取标准输入流
import java.util.Scanner;

// 正则表达式想换
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileTool {
  // 在本Java中, 尝试来自行实现一下 文件/文件夹 相关的功能
  public static String default_path = "Java_Reboot/File_IO_Experiment/Self_Exercise"; // 默认输出路径

  // 方法定义&实现区域

  // 检测用户输入Y/N单字符, 校验合法性, 返回布尔
  public static boolean validate_yes_no(String user_answer) {
    // Scanner user_input = new Scanner(System.in);
    String input_content = user_answer.trim();
    Pattern yn_RE = Pattern.compile("^[YyNn]{1}$");
    Matcher yn_matcher = yn_RE.matcher(input_content);
    // user_input.close(); // 注意: Scanner(System.in)一旦被close(), 关掉的就是全局的System.in,
    // 你控制台就别想再输东西了, 除非再开一个新System.in
    return yn_matcher.matches();
  }

  // 将用户输入的 Y y N n 转换为boolean进行返回
  public static boolean get_yes_no(String user_input){
    char[] yn_arr = user_input.toCharArray();
    return ((yn_arr[0] == 'Y') || (yn_arr[0]=='y')); // Y y 返回true, N n 为false (需先走一遍上面的validate_yes_no来确保'输入有效')
  }

  // 校验文件名合法性
  public static boolean validate_filename(String filename) {
    // Pattern symbol_set = Pattern.compile("^[^\\/:*?\"\\[\\]<>|]$"); // 文件名不能包含 \
    // / : * ? " < > | (把这些'不要'的符号滤掉)
    Pattern symbol_set = Pattern.compile(
        "^[^\\\\/:*?\\\"\\\\[\\\\]<>|]+$" // 基础字符集（排除控制符和空格）
            + "(?<!\\.)" // 匹配的'文件名'?<!前面 不能以 \ . 开头
            + "(?!\\s)" // 匹配的'文件名'?!后面 不能以! 空格 结尾 (其实Windows会处理结尾的空格, 嘻嘻)
            + "{1,260}" // 长度限制
    );
    Matcher filename_format = symbol_set.matcher(filename);
    boolean legal_filename = filename_format.matches();
    return legal_filename;
  }

  // 写入内容到文件 传入File对象, 是否'追加写入'boolean
  public static boolean write_file(File target_file, boolean append) {
    if (append) {
      System.out.println("\n即将对文件进行'覆盖'写入");
    } else {
      System.out.println("\n本次对文件'追加'写入");
    }
    try {
      BufferedWriter writer = new BufferedWriter(new FileWriter(target_file, !append));
      Scanner content = new Scanner(System.in);
      String input_content;
      boolean continue_write = true;
      int line_counter = 1;
      Pattern end_format = Pattern.compile("^.*/end$");
      boolean end_judge;
      System.out.println("注: 输中文有bug, 字符编码的问题, 这里懒得搞了 XD");
      while (continue_write) {
        System.out.print("请书写第 " + line_counter + " 行的内容, 输入/end退出: ");
        input_content = content.nextLine();
        end_judge = end_format.matcher(input_content).matches(); // 匹配到/end, 说明用户不想输入了
        if (end_judge) {
          input_content = end_format.matcher(input_content).replaceAll(""); // 将/end 替换成空格; (考虑到用户可能会在单行输入 /end)
          writer.write(input_content);
          System.out.println("已终止输入");
          content.close();
          break;
        } else {
          writer.write(input_content);
          writer.newLine();
          line_counter++;
        }
      } // 循环写入结束
      writer.flush();
      writer.close();

    } catch (IOException e) {
      System.out.println("在写入文件 " + target_file.getName() + " 时发生了IO读写异常...");
      return false;
    }
    System.out.println("成功往文件 " + target_file.getName() + " 中写入了内容!");
    return true;
  }

  // 校验'路径名'是否合法
  public static boolean validate_pathname(String pathname){
    Pattern path_format = Pattern.compile("^(/[0-9a-zA-Z_]+)+$"); // 这个就写简单点了, 不搞的太复杂
    boolean is_valid = path_format.matcher(pathname).matches();
    return is_valid;
  }

  // 列出当前目录所有文件&文件夹, 传入一个File对象'指定路径'
  public static String list_files_and_folders(File pathname){
    String[] output = pathname.list();
    return Arrays.toString(output);
  }

  /* ---------------------------------分割线----------------------------------------- */

  // 1. 创建文件 并 写入数据
  public static void create_File() {
    System.out.println("\n欢迎来到'文件创建' (这里仅为创建'字符文件' Structured Data owo)");
    System.out.print("请输入文件名: ");
    Scanner user_input = new Scanner(System.in);
    String filename = user_input.nextLine().trim();
    // System.out.println("\n所用输入的文件名合法吗? " + legal_filename);
    boolean filename_pass = FileTool.validate_filename(filename);
    while (!filename_pass) { // 文件名校验不通过
      System.out.print("输入文件名不合法, 请重新输入: ");
      filename = user_input.nextLine();
      filename_pass = FileTool.validate_filename(filename);
    }
    System.out.println("文件名合法性校验成功!");

    File output_file = new File(default_path, filename);
    if (output_file.exists()) {
      System.out.print("检测到当前目录中已有同名文件, 是否继续进行覆盖写入? [Y/N]: ");
      String answer = user_input.nextLine(); 
      boolean valid_ans = FileTool.validate_yes_no(answer);
      // System.out.println("用户输入了: " + write + " validate_yes_no格式判断的结果为: " +
      // validate_yes_no.matches());
      while (!valid_ans) {
        System.out.print("输入无效, 请重新输入: ");
        answer = user_input.nextLine().trim();
        valid_ans = FileTool.validate_yes_no(answer);
      }
      if (valid_ans) { // 输入有效
        // char[] want_append = answer.toCharArray();
        // boolean append_judge = (want_append[0] == 'Y') || (want_append[0] == 'y');
        boolean append_judge = FileTool.get_yes_no(answer);
        FileTool.write_file(output_file, append_judge);
      }
    } else { 
      try { // 没有找到同名文件, 直接创建新文件
        output_file.createNewFile();
        // output_file.deleteOnExit();
        System.out.print("文件现已成功创建至默认目录" + default_path + "下\n你想往其中写入字符内容吗? [Y/N]: ");
        String answer = user_input.nextLine();
        boolean valid_input = FileTool.validate_yes_no(answer);
        while(!valid_input){
          System.out.println("输入无效, 请重试: ");
          answer = user_input.nextLine();
          valid_input = FileTool.validate_yes_no(answer);
        }
        boolean write_or_not = FileTool.get_yes_no(answer);
        if(write_or_not){
          FileTool.write_file(output_file, write_or_not); // 直接书写(覆盖)
        }else{
          System.out.println("没有往 " + output_file.getName() + " 写入任何信息, 当前为一个空文件");
        }
      } catch (IOException e) {
        System.out.println("修改文件 " + output_file.getName() + " 时发生了IOException异常, 程序已自行退出");
        System.exit(0);
      }

      System.out.println();

    }
    user_input.close();
  }

  // 5. 新建文件夹
  public static void create_Folder() {
    System.out.println("欢迎来到创建文件夹");
    System.out.print("请问是否要创建多级目录? [Y/N]: ");
    Scanner user_input = new Scanner(System.in);
    String input_content = user_input.nextLine();
    String path; // 真正获取到的'合法路径'输入 (这里暂时声明, 一会而取到合法路径就赋值)
    boolean validate_yes_no = FileTool.validate_yes_no(input_content);
    while(!validate_yes_no){ // Yes No 合法判断
      System.out.print("输入无效, 请重试: ");
      input_content = user_input.nextLine();
      validate_yes_no = FileTool.validate_yes_no(input_content);
    }
    boolean is_multiple = FileTool.get_yes_no(input_content);
    if(is_multiple){ // 多级目录 
      System.out.println("\n用户选择了创建'多级目录'");
      System.out.print("请输入多级目录路径 (/folder_A/folder_B/...): ");
      path = user_input.nextLine();
      boolean valid_path = FileTool.validate_pathname(path);
      while (!valid_path) {
        System.out.print("输入的文件路径不合法! 请重新输入 (/folder_A/folder_B/...): ");
        path = user_input.nextLine();
        valid_path = FileTool.validate_pathname(path);
      }
      System.out.println("输入的文件路径 " + path + " 合法!");
      File multi_path = new File(default_path+path);
      
      if(multi_path.mkdirs()){
        System.out.println("成功在创建了多级目录 " + default_path + path);
      }else{
        System.out.println("创建多级目录失败, 可能目录已存在...");
      }
      
    }else{ // 单级目录
      System.out.println("\n用户选择创建'单个文件夹'");
      System.out.print("请输入单路径名称(/my_folder): ");
      path = user_input.nextLine();
      boolean valid_path = FileTool.validate_pathname(path);
      while(!valid_path){
        System.out.print("输入路径不合法! 请重新输入 (/my_folder): ");
        path = user_input.nextLine();
        valid_path = FileTool.validate_pathname(path);
      }
      System.out.println("输入文件夹名称 " + path + "合法!");
      File single_path = new File(default_path,path);
      if(single_path.mkdir()){
        System.out.println("成功创建单级目录: " + default_path + path);
      }else{
        System.out.println("创建单级目录失败, 可能目录已存在 或 输入了多级目录...");
      }
    }
    
  }

  // 6. 删除空文件夹 ()
  public static void delete_Empty_Folder(){
    System.out.println("欢迎来到删除'空文件夹'");
    Scanner user_input = new Scanner(System.in);
    System.out.print("请先输入要前往的路径, 当前位于Self_Exercise目录下, 如/folder_a(回车直接确定为'当前目录'): ");
    String valid_path = user_input.nextLine();
    File go_to_path = new File(default_path, valid_path);
    boolean is_pathname_valid = FileTool.validate_pathname(valid_path);
    boolean is_path_exists = go_to_path.exists();
    while (!is_pathname_valid || !is_path_exists) {
      System.out.print("输入的路径名非法或不存在, 请重新输入: ");
      valid_path = user_input.nextLine();
      is_pathname_valid = FileTool.validate_pathname(valid_path);
      go_to_path = new File(default_path, valid_path);
      is_path_exists = go_to_path.exists();
    }
    System.out.println("\n输入的路径合法且存在!");
    

    String file_and_folders = FileTool.list_files_and_folders(go_to_path);
    System.out.println("前往的目录"+default_path+valid_path+"中有: \n" + file_and_folders);
  }

  // 主函数
  public static void main(String[] args) throws IOException {
    String directory = "Java_Reboot/File_IO_Experiment/Self_Exercise";
    File welcome_text = new File(directory, "welcome.txt");
    if (welcome_text.exists()) { // 文件存在
      Scanner welcome_reader = new Scanner(welcome_text);
      while (welcome_reader.hasNextLine()) {
        System.out.println(welcome_reader.nextLine());
      }
      welcome_reader.close();
    } else {
      System.out.println("未能成功读取welcome.txt");
    }

    System.out.print("\n请提供选择对应数字进行操作: ");
    Scanner user_input = new Scanner(System.in); // 这一行并不会立即唤起'输入'!
    Pattern single_number = Pattern.compile("[0-9]{1}");
    String current_line;
    // Matcher number_matcher = single_number.matcher(current_line);
    // System.out.println("行内容: " + current_line + " 的number_matcher结果: " +
    // number_matcher.matches());

    // boolean range_judge = user_input.findInLine(single_number) == null; // 输入多位数
    // 23 时会'匹配两个', 2 3 给自己挖大坑了

    while (true) {
      current_line = user_input.nextLine(); // 读取当前行输入
      Matcher number_matcher = single_number.matcher(current_line);
      if (current_line.length() == 1 && number_matcher.matches()) { // 用户只输了一个数字, 和matcher的严格匹配对上了, 顺带校验了'输入长度'作为保险
        // System.out.println("输入有效!");
        break;
      } else {
        System.out.print("输入无效, 请重试: ");
      }
    }

    switch (current_line) {
      case "1":
        FileTool.create_File();
        break;

      case "2":

        break;
      case "3":

        break;

      case "4":

        break;

      case "5":
        FileTool.create_Folder();
        break;
      case "6":
        FileTool.delete_Empty_Folder();
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
        user_input.close();
        break;

      default:
        break;
    }

  }
}
