package Java_Reboot.FIle_IO_Experiment.Windows_UI_Selector;

// Awt类
import java.awt.FileDialog;
import java.awt.Frame;
import java.io.IOException;
// 文件IO类
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

public class Read_File {

  public static void main(String[] args) throws IOException{
    // 创建父级窗口
    Frame window_model = new Frame();
    FileDialog read_window = new FileDialog(window_model, "打开文件", FileDialog.LOAD); // 设定'打开窗口', 应用在父级window_model的模版上

    // 手动显示'打开窗口'
    read_window.setVisible(true);

    // 取得打开文件路径
    String file_path = read_window.getDirectory() + read_window.getFile();
    System.out.println("read_window取得的文件路径为: " + file_path);

    Path tgt_file = Paths.get(file_path);
    if(Files.exists(tgt_file)){
      System.out.println("已成功读取文件!");
      String file_content = Files.readString(tgt_file);
      System.out.println("文件中的内容为: " + file_content);
    }else{
      System.out.println("文件不存在!");
    }

    window_model.dispose(); // 别忘了手动结束'父级窗口', 要不然程序不会终止!
  } // main结束
  
}
