package Java_Reboot.Properties_Class;

import java.util.Properties; // 导入Properties
import java.util.Set;

public class Properties_Experiment {
  // 我们来简单实验一下 Properties, 就是 全String的键值对, 常用于存储'配置信息'
  // Properties继承自 Hashtable (Map为大类), 具有 Hastable所有的方法, 同时也有自己的方法
  public static void main(String[] args) {
    Properties system_configs = System.getProperties(); // 直接取得系统的Properties对象

    // 内容太多了, 挑部分项进行展示
    // Set<String> config_names = system_configs.stringPropertyNames(); // 使用.stringPropertyNames返回所有'键名'为一个 Set<String>对象
    // System.out.println("取得的系统properties中有如下键名: ");
    // config_names.forEach((name) -> System.out.println(name));

    System.out.println("取得的当前OS名称: " + system_configs.getProperty("os.name"));
    System.out.println("当前系统的'文件分隔符': " + system_configs.getProperty("file.separator"));
    System.out.println("当前用户是: " + system_configs.getProperty("user.name"));
    System.out.println("当前的工作目录是: " + system_configs.getProperty("user.dir"));
    System.out.println("Java运行时环境: " + system_configs.getProperty("java.specification.version"));
    System.out.println("JVM的本地字符编码: " +  system_configs.getProperty("sun.jnu.encoding"));
    System.out.println("JVM的class_path类加载路径: " + system_configs.getProperty("java.class.path"));
    System.out.println("jre安装目录: " + system_configs.getProperty("java.home"));
    
    System.out.println();
    system_configs.setProperty("dirty_data", "Hachimi"); // 调用Hashtable的put()方法
    System.out.println("拿到的自定义dirty_data为: " + system_configs.getProperty("dirty_data"));
    
    
  } // main函数结束
}
