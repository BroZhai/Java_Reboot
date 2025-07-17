package Java_Reboot.Serializable_Experiment;

// 导入序列化相关类
import java.io.ObjectOutputStream;  // 序列化输出
import java.io.ObjectInputStream; // 反序列化读入

// 导入文件IO相关工具类 (提供'输入输出流')
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.nio.file.Files;

// 相关异常类
import java.io.IOException;

public class Seralizable_Experiment {
  // 在本class中, 我们来研究一下Java的'序列化'
  // 在同级文件夹下, 我们已有一个可以被序列化的Gun对象

  public static void main(String[] args) throws IOException, ClassNotFoundException{
    Gun shotgun = new Gun("Benelli M3 Super90", "12 Gague", false,141);
    // Gun smg = new Gun("MAC 10", ".45 ACP", false);
    // Gun pistol = new Gun("Beretta M9","9mm",true);

    Path shotgun_path = Paths.get("Java_Reboot/Serializable_Experiment/Gun_Storage","shotgun_m3.ser");
    // Path shotgun_path = Paths.get("Java_Reboot/Serializable_Experiment/Gun_Storage");
    ObjectOutputStream shotgun_obj_out = new ObjectOutputStream(Files.newOutputStream(shotgun_path, StandardOpenOption.CREATE)); // 传入一个'输出流'作为 ObjectOutputStream的'构造形参'
    shotgun_obj_out.writeObject(shotgun);

    ObjectInputStream shotgun_obj_in = new ObjectInputStream(Files.newInputStream(shotgun_path, StandardOpenOption.READ));
    Gun restore_shotgun = (Gun) shotgun_obj_in.readObject();
    System.out.println("当前通过重新读入的 "+ shotgun_path.getFileName() + " 序列文件, 取得的枪名为: " +restore_shotgun.get_name());
    System.out.println("该枪所对应的子弹类型为: " + restore_shotgun.get_ammo_type());
    // System.out.println("尝试读取的gun_id为: " + restore_shotgun.get_gun_id()); // 会出错, 因为gun_id属性被设置了transient, 并没有存储在序列化的文件中, 强行读取会爆null

  }
}
