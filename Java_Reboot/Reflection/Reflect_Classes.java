package Java_Reboot.Reflection;

// 导入Java反射相关的包
import java.lang.Class; // 获取Class对象

import java.lang.reflect.Constructor; // 获取'构造函数'对象
import java.lang.reflect.Field; // 获取'属性'对象
import java.lang.reflect.Method; // 获取'方法'对象

// 辅助工具类
import java.util.Arrays;


// 导入测试用类
import Java_Reboot.Reflection.test_classes.Gun;
import Java_Reboot.Reflection.test_classes.Player;
import Java_Reboot.Reflection.test_classes.Shell;
import Java_Reboot.Reflection.test_classes.Advance_Player;

public class Reflect_Classes {
  
  // 在本实验中, 我们来实践一下Java反射
  public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException {

    // 1. 获取要操作的class对象, 这里以Gun为例
    // String class_name_of_Gun = Gun.class.getName();
      Class<?> gun_class = Class.forName(Gun.class.getName());
      System.out.println("已成功取得class: " + gun_class.getName());
      System.out.println("该class的简称为: " + gun_class.getSimpleName());
      // Field get_test_variable = gun_class.getField("total_shells");
      // System.out.println("单独取得的total_shells属性: " + get_test_variable.getName()); // getName()这里会直接取得'简称', toString()则仍是public int Java_Reboot.Reflection.test_classes.Gun.total_shells
      System.out.println();

      // 通过已经取得的Class对象, 进一步获取内部的 属性, 方法, 构造函数等...
      // 取得属性
      Field[] public_variables_in_Gun = gun_class.getFields();
      System.out.println("该类中有如下 public'属性': "); // 注: getFields只能取得Public属性, 全取用getDeclaredFields()
      for(Field i:public_variables_in_Gun){
        System.out.println(i.getName());
      }
      System.out.println();
      Field[] all_variables_in_Gun = gun_class.getDeclaredFields();
      System.out.println("该类的所有属性包含: ");
      for(Field i: all_variables_in_Gun){
        System.out.println(i.getName()); // 这里getNanme()是'简称'
      }

      System.out.println();
      // 取得方法
      Method[] all_methods_in_Gun = gun_class.getDeclaredMethods();
      System.out.println("该类中有如下方法: ");
      for(Method i: all_methods_in_Gun){
        System.out.println(i.getName() + "()"); // 这里也是'简称'
      }
      
      System.out.println("\n现在我们来看一下另一个class: Shell, 它里面有两个构造函数: ");
      // 取得构造函数, 这里换另一个有多个构造函数的Shell类 来做实验
      Class<?> shell_class = Class.forName(Shell.class.getName());
      Constructor<?>[] constructor_in_Shell = shell_class.getConstructors();
      int counter = 1;
      for(Constructor<?> i: constructor_in_Shell){
        // System.out.println(i.getName()); // 这里的get是'全名', 看不出区别, 建议直接用下面的toString()进行查看
        System.out.println("这里是第"+counter+"个构造函数");
        System.out.println(i.toString());
        System.out.println("共计需要 " +i.getParameterCount()+" 个参数" + "\n类型分别是" + Arrays.toString(i.getParameterTypes()) + "\n");
        counter++;
      }

      // 取得某个类'继承的父类'
      System.out.println("现有有一个Advance_Player类继承了Player父类, 展示玩家Vip身份");
      Class<?> advance_player_class = Class.forName(Advance_Player.class.getName());
      System.out.println("当前的类为: " +advance_player_class.getSimpleName());
      Class<?> advance_parent = advance_player_class.getSuperclass(); // 通过该方法取得'父级class'
      System.out.println("该类的父级为: " + advance_parent.getSimpleName());

      // 取得某个类'实现的接口'
      System.out.println("\n现在我们又唐突的定义了一个Ammo接口, 让Shell类对其进行了实现");
      Class<?>[] interfaces_in_shell_class = shell_class.getInterfaces(); // 用上面已经取得过的Shell类
      System.out.println("Shell类实现了如下的接口(class):");
      for(Class i: interfaces_in_shell_class){
        System.out.println(i.getName());
      }
  }
}
