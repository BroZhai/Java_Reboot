package Java_Reboot.FIle_IO_Experiment.Windows_UI_Selector;

// 需要使用的相关类
import java.awt.FileDialog;
import java.awt.Frame;
import java.io.IOException;
// 文件IO相关类
import java.util.Scanner;
import java.nio.file.Path; // 文件/路径对象
import java.nio.file.Paths;  // 文件/路径相关静态方法
import java.nio.file.StandardOpenOption;
import java.nio.file.Files; // 具体读写操作

public class Save_File {
  // 来研究一下如何调用windows的文件管理器窗口指定文件'输出路径'并保存

  public static void main(String[] args) throws IOException{

    Scanner user_input = new Scanner(System.in);
    System.out.print("请输入文件内容: ");
    String file_content = user_input.nextLine();
    

    Frame frame = new Frame("Frame父级窗口"); // 可以理解为'窗口模版'
    frame.setSize(400, 400); // 设置窗口大小 (可选)

    // 使用FileDialog展示'文件窗口', 需要一个已创建的'Frame窗口模版'作为应用
    FileDialog save_window = new FileDialog(frame, "请选择文件保存路径", FileDialog.SAVE);

    save_window.setDirectory("D:\\github project\\Java_Reboot\\Java_Reboot\\FIle_IO_Experiment\\Windows_UI_Selector"); // 指定'默认打开路径' (可选)
    save_window.setFile("output.txt"); // 设置文件'默认输出名称' (可选)

    save_window.setVisible(true); // 正式显示'文件保存对话框'

    // 获取用户选择
    String save_dir = save_window.getDirectory(); // 取得保存路径
    String save_name = save_window.getFile(); // 获取保存文件名

    System.out.println("用户选择的地址为: " + save_dir +", 文件名为: "+ save_name);
    System.out.println("综合地址: " + save_dir+save_name);

    Path output_file = Paths.get(save_dir+save_name); // 直接通过获取的路径一气呵成!
    Files.writeString(output_file, file_content, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE);

    if (Files.exists(output_file)) {
      System.out.println("文件写入成功!");
    }else{
      System.out.println("文件写入异常! 请排查错误...");
    }

    frame.dispose(); // 完成后关闭'父窗口'

  } // main结束

}
