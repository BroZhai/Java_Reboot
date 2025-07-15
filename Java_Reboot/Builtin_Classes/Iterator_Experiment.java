package Java_Reboot.Builtin_Classes;

import java.util.Iterator;
import java.util.ArrayList;

public class Iterator_Experiment {
  // 在本class中, 我们来简单实验一下iterator 这个'万能遍历'工具, 
  public static void main(String[] args)  {
    // 新建一个ArrayList
    ArrayList<String> arr = new ArrayList<>();
    arr.add("BakaWing");
    arr.add("BakaPancake");
    arr.add("BakaCandy");
    Iterator arr_iterator = arr.iterator(); // 获得ArrayList的 iterator迭代器对象;
    while(arr_iterator.hasNext()){ // iterator的默认位置在'空', next()后直接指向第一个元素
      String current_value = (String) arr_iterator.next();
      System.out.println(current_value);
    }

  }
}
