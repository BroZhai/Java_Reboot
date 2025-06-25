package Java_Reboot.DataType_Experiment;
import java.util.Arrays; // 导入Arrays类
import java.util.ArrayList; // 导入 ArrayList类

public class Arrays_and_ArrayList{

  public static void main(String[] args) {
    // 简单实践下数组的创建 和 访问
    int[] num_arr = {2,3,1,5,4,7}; // 直接'用数据'创建一维数组
    // int[][] num_2d_arr = new int[3][2]; // 通过Arrays构造函数创建固定大小的二维数组 (长3宽2)
    int[][] num_2d_arr = {{9,8,7}, {1,2,3}, {5,4,6}}; // 通过'直接赋值'的方式创建 二维数组

    int total_1d_sum = 0;
    for(int cur_num : num_arr){ // 使用for each循环遍历 一维数组num_arr
      total_1d_sum += cur_num;
    }
    System.out.println("num_arr中所有值的总和为: " + total_1d_sum); // 22
  
    int total_2d_sum =0;
    for(int[] cur_arr: num_2d_arr){ // 遍历二维数组中的'一维数组'
      for(int cur_element: cur_arr){ // 遍历一维数组中的'每个元素'
        total_2d_sum += cur_element;
      }
    }
    System.out.println("num_2d_arr中所有值的总和为: " + total_2d_sum ); // 45

  }

}