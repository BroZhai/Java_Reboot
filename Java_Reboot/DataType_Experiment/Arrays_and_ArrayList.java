package Java_Reboot.DataType_Experiment;
import java.util.Arrays; // 导入Arrays类
import java.util.ArrayList; // 导入 ArrayList类

public class Arrays_and_ArrayList{

  public static void main(String[] args) {
    // 简单实践下数组的创建 和 访问
    int[] num_arr = {2,3,1,5,4,6}; // 直接'用数据'创建一维数组
    // int[][] num_2d_arr = new int[3][2]; // 通过Arrays构造函数创建固定大小的二维数组 (长3宽2)
    int[][] num_2d_arr = {{9,8,7}, {1,2,3}, {5,4,6}}; // 通过'直接赋值'的方式创建 二维数组

    int total_1d_sum = 0;
    System.out.print("num_arr当前所有值: ");
    for(int cur_num : num_arr){ // 使用for each循环遍历 一维数组num_arr
      System.out.print(cur_num + " ");
      total_1d_sum += cur_num;
    }
    System.out.println("\nnum_arr中所有值的总和为: " + total_1d_sum); // 22
  
    int total_2d_sum =0;
    System.out.print("num_arr当前所有值: ");
    for(int[] cur_arr: num_2d_arr){ // 遍历二维数组中的'一维数组'
      for(int cur_element: cur_arr){ // 遍历一维数组中的'每个元素'
        System.out.print(cur_element + " ");
        total_2d_sum += cur_element;
      }
    }
    System.out.println("\nnum_2d_arr中所有值的总和为: " + total_2d_sum + "\n"); // 45

    // 来整点高级方法 (all static methods)
    Arrays.sort(num_arr); // Arrays的排序方法, 升序, 直接作用在对象上
    System.out.print("对num_arr用Arrays.sort()进行排序得: ");
    for(int cur_num : num_arr){ 
      System.out.print(cur_num + " ");
    }
    System.out.println("\n在有序的num_arr中用Array的二分查找'5'的下标为: "+Arrays.binarySearch(num_arr, 5));

    System.out.println("\n玩点有意思的");

    // 玩点有意思的
    char[] char_mix = {'蛋', '是', '我', '笨'}; // 86cb, 662f, 6211, 7b28
    System.out.print("char_mix当前内容序列: ");
    for(char curr_char: char_mix){
      System.out.print(curr_char);
    }
    Arrays.sort(char_mix);
    System.out.print("\nchar_mix好像发生了奇妙的变化?: "); // Baka is you!
    for(char curr_char: char_mix){
      System.out.print(curr_char);
    }

    System.out.println();
    /* Arrays.sort() 不支持二维数组的排序 XD */

    // Arrays.sort(num_2d_arr);
    /*System.out.println("\n对num_2d_arr用Arrays.sort()进行排序得: ");
    for(int[] cur_arr: num_2d_arr){ 
      for(int cur_element: cur_arr){ 
        System.out.print(cur_element + " ");
      }
    }*/

  }

}