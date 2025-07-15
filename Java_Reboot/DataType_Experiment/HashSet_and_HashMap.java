package Java_Reboot.DataType_Experiment;

import java.util.HashSet; // 导入HashSet类
import java.util.HashMap; // 导入HashMap类

// 辅助工具类
import java.util.Arrays;

// 其他类
import java.util.Map; // HashMap实践的接口, 不属于Collection类中
import java.util.Set; // Collection类中的子类, 一般的'集合'
import java.util.List; // Collection类中的子类, 一般的'列表'


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
    Map<String, Integer> person_list = Map.of("Pancake",114,"RokidnaUG",514,"Yaju Senpai",1919810);
    System.out.println("Map对象person_list中的信息为: " + person_list.toString());
    name_and_age.putAll(person_list);
    System.out.println("将person_list中的内容全部追加到HashMap中: " + name_and_age.toString());
    Integer rtn_value = name_and_age.remove("Yaju Senpai");  
    System.out.println("移除了糟糕的元素, 对应的值为: "+rtn_value); // 1919810
    
    System.out.println();
    HashMap<String, Integer> copied_hashmap = (HashMap<String, Integer>)name_and_age.clone();
    System.out.println("现在将name_and_age中的内容clone()到了新HashMap copied_hashmap中: "+copied_hashmap.toString());
    name_and_age.clear();
    System.out.println("已清除原HashMap name_and_age中的所有内容, name_and_age现在的大小为: " + name_and_age.size()); // 0
    System.out.println("name_and_age现在是空的吗? " + name_and_age.isEmpty()); // true
    System.out.println("尝试虚空索取'baka'键名的值, 拿不到就返回9: " + copied_hashmap.getOrDefault("baka", 9)); // 9
    System.out.println("copied_hashmap中有'baka'键名吗? " + copied_hashmap.containsKey("baka")); // false

    System.out.println();
    copied_hashmap.forEach((k,v) -> {
      System.out.println("当前使用forEach&箭头函数取到的键名: " + k + ", 对应的值: " + v);
    });

    System.out.println("\n现在我们来试一下.keySet()一键返回'所有键名', 返回一个Set对象");
    Set<String> keynames_in_hashmap = copied_hashmap.keySet(); // .keySet() 返回一个 Set'一般集合'对象, 返回当前HashMap中的'所有键名K'
    for(String i:keynames_in_hashmap){
      System.out.println("当前用forEach遍历到Set中的键名K为: " + i);
    }

    System.out.println("\n我们再来试一下HashMap的替换和计算");
    Integer old_value = copied_hashmap.replace("cirno", 8);
    System.out.println("已经'cirno'的值设置成: " + copied_hashmap.get("cirno") + ", 原来的值为: " + old_value);
    copied_hashmap.replaceAll((k,v) -> { // 针对所有的value进行'统一操作' (箭头函数中全部value+1, 并返回到了原函数上)
      v++;
      return v; 
    });
    System.out.println("统一对copied_hashmap中的所有value+1: " + copied_hashmap.toString());

    copied_hashmap.compute("IceWing", (k,v) -> { // 单独针对IceWing的value'计算'翻两倍 (注: 如果发生null报错说明'找不到键名')
      v*=2;
      return v;
    });
    System.out.println("刚刚对IceWing的value通过compute()计算翻了两倍, 当前IceWing的值为: " + copied_hashmap.get("IceWing")); // 52
  }

}
