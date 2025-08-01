package Java_Reboot.DataType_Experiment;

// 导入Vector类
import java.util.Vector;

// 导入其他工具类
import java.util.Arrays;
import java.util.Enumeration; // 枚举类 
import java.util.List;
import java.util.Stack;

public class Vector_and_Stack {

  // 在本类中, 我们来玩玩 Vector 及其子类 Stack栈

  // Stack相关的自定义'可视化'方法
  public static void showPush(Stack<Integer> stack,int value){
    stack.push(value);
    System.out.println("已将"+ value +"压入栈顶");
  }

  public static void showStack(Stack<Integer> stack){
    System.out.print("当前Stack中的元素从底 到 顶部有: ");
    for(Integer i: stack){
      System.out.print(i + " ");
    }
  }

  public static void showPop(Stack<Integer> stack){
    int popped_num = stack.pop();
    System.out.println("pop()移除了" + popped_num);
  }

  public static void main(String[] args) {

    /* Vector实验区 */
    // 创建一个Vector对象
    Vector<Integer> my_vect = new Vector<>();
    my_vect.add(0); // 单纯追加元素
    System.out.println("当前my_vect中有: " + my_vect.get(0)); // Tips: Vector()实现了List接口, 所有这里可以用List的get()方法获取元素, 当然也可以用自己的elementAt();
    List<Integer> num_list = Arrays.asList(1,3,2);
    my_vect.addAll(num_list); // 直接追加Collection类对象
    System.out.print("追加元素后的my_vect为: ");
    for(Integer i: my_vect){
      System.out.print(i + " ");
    }
    System.out.println("当前的第一个元素是: " + my_vect.firstElement()+ ", 最后一个元素为: " + my_vect.lastElement());

    // 返回Enumeratin枚举类对象
    my_vect.add(3);
    Enumeration<Integer> my_enum = my_vect.elements();
    System.out.print("追加一个元素后返回的Enumeration枚举类对象中有: ");
    while(my_enum.hasMoreElements()){
      int current_num = my_enum.nextElement();
      System.out.print(current_num + " ");
    }
    System.out.println();

    // my_vect.remove(2); // 移除索引2 的元素 (3)
    System.out.print("移除第一个出现的2的结果为: ");
    my_vect.remove(Integer.valueOf(2)); // 移除'第一个出现的2'
    for(Integer i: my_vect){
      System.out.print(i + " ");
    }
    System.out.println();

    my_vect.set(0, 9);
    System.out.print("修改了index 0的元素: ");
    for(Integer i: my_vect){
      System.out.print(i + " ");
    }
    System.out.println("\n当前Vector的大小: " + my_vect.size());

    /* Stack实验区 */
    System.out.println("\n现在来看看Stack");
    Stack<Integer> my_stack = new Stack<>();
    showPush(my_stack, 8); // 底部
    showPush(my_stack, 9);
    showPush(my_stack, 6); // 当前顶部
    showStack(my_stack); // 8 9 6
    System.out.println("当前Stack为空吗: " + my_stack.empty()); // false
    System.out.println("从顶部开始数, 元素6的位置位于: " + my_stack.search(8)); // 3, 顶部从1开始数
    showPop(my_stack);
    showPush(my_stack, 2);
    showPush(my_stack, 5);
    showStack(my_stack);
    

  } // main函数结束 
  
}
