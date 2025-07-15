package Java_Reboot.DataType_Experiment;

import java.util.HashSet; // 导入HashSet类
import java.util.HashMap; // 导入HashMap类

// 辅助工具类
import java.util.Arrays;
import java.util.List;
import java.util.Map; // HashMap实践的接口, 不属于Collection类中

public class HashSet_and_HashMap {
  
  // 在本类中, 我们来玩转一下Hashset和Hashmap
  public static void main(String[] args) {
    // HashSet实验区
    HashSet<String> unique_names = new HashSet<>();
    unique_names.add("cirno");
    unique_names.add("IceWings");
    unique_names.add("DannieL");
    unique_names.add("Mr-Pancake");
    unique_names.add("CyanCandy");
    System.out.println("HashSet现有: " + unique_names.toString()); // 我们发现往HashSet中存入的数据并没有按照我们写入的顺序来存, 因为每个元素都会单独过一遍hash然后被分配到一个特定的桶中
    if(unique_names.add("cirno")){ // 尝试追加重复元素
      System.out.println("This is not freaking possible dude :|");
    }else{
      System.out.println("往HashSet中再次追加'cirno'时发生了异常, 不允许元素重复!");
    }
    System.out.println("当前HashSet的大小为: " + unique_names.size());

    System.out.println("\n现在创建了一个List, 尝试移除List中的交集元素Mr-Pancake, cirno");
    String[] remove_names = {"Mr-Pancake","cirno"};
    List<String> remove_list = Arrays.asList(remove_names); // 定义一个List, 一会儿让HashSet移除'List交集'
    unique_names.removeAll(remove_list);
    System.out.println("HashSet现有: " + unique_names.toString());
    System.out.println("当前HashSet的大小为: " + unique_names.size());
    
    System.out.println();
    // HashMap实验区
    HashMap<String, Integer> name_and_age = new HashMap<>();
    name_and_age.put("otto", -1);
    name_and_age.put("IceWing", 25);
    name_and_age.put("cirno", 9);
    System.out.println("当前HashMap中有: " + name_and_age.toString());
    name_and_age.putIfAbsent("otto", 114); // 这一行不会执行, 因为otto键已存在
    System.out.println("小测试: " + name_and_age.toString());
    Map<String, Integer> person_list = Map.of();
  }

}
