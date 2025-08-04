package Java_Reboot.DataType_Experiment;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collector;
import java.util.stream.Stream;

public class Tree_Node {
  // 我们再来Java中玩一下 树, 尝试实现一个'二分查找树'

  public static void swap_node(List<myNode> nodeList, myNode a, myNode b){
    myNode tmp_node = a;
    int a_index = nodeList.indexOf(a);
    int b_index = nodeList.indexOf(b);
    nodeList.set(a_index, b);
    nodeList.set(b_index, tmp_node);
  }

  public static void insert_node(List<myNode> nodeList, myNode newNode, myNode baseNode){
      int cur_index = nodeList.indexOf(baseNode);
      Optional<myNode> cur_left_node = (cur_index*2 + 1 < nodeList.size() ? Optional.ofNullable(nodeList.get(cur_index*2+1)) : Optional.empty()); // 条件: 检查是否超界(node不存在)? node存在: node不存在
      // Tips: ? Optional.ofNullable(nodeList.get(cur_index*2+1)) : Optional.empty() 在括号内 和 括号外 判断都行
      Optional<myNode> cur_right_node = (cur_index*2 + 2 < nodeList.size() ? Optional.ofNullable(nodeList.get(cur_index*2+2)) : Optional.empty());

      // 和当前node进行比较
      if(newNode.value < baseNode.value){ // 小于当前节点
        cur_left_node.ifPresent((left_node) -> { // 左节点存在, 进行进一步的递归比较
          insert_node(nodeList, newNode, left_node);
          return;
        });
        // 当前节点的左节点为空, 直接赋值到左节点上
        baseNode.leftNode = newNode;
        nodeList.add(cur_index, newNode); // 在'指定索引 前' 追加元素
        return;
        

      }else if(newNode.value > baseNode.value){
        cur_right_node.ifPresent((right_Node) -> { // 右节点存在, 进一步递归比较
          insert_node(nodeList, newNode, right_Node);
          return;
        });
        // 当前右节点为空, 直接赋值
        baseNode.rightNode = newNode;
        nodeList.add(cur_index+1, newNode);
        return;
      }

    }

    public static void show_list(List<myNode> nodeList){
      System.out.print("当前Node_list中的元素顺序: ");
      nodeList.forEach((cur_node) -> {
        System.out.print(cur_node.value + " ");
      });
      System.out.println();
    }



  static class myNode{ // 自定义内部类 '树', 每个树包含 自己的构造函数, 值, 左叶子节点, 右叶子节点
      int value; // 值
      public myNode(int input_value){
        this.value = input_value;
      }
      myNode leftNode = null;
      myNode rightNode = null;
    }


  public static void main(String[] args) {
    myNode baseNode = new myNode(33);
    myNode node2 = new myNode(28);
    myNode node3 = new myNode(40);
    myNode node4 = new myNode(19);
    myNode node5 = new myNode(31);
    myNode node6 = new myNode(35);
    myNode node7 = new myNode(45);

    List<myNode> nodeList = new ArrayList<>(); // 默认排序 
    nodeList.add(baseNode); // 33
    insert_node(nodeList, node2, baseNode); // 28
    // System.out.println(nodeList.get(0).leftNode.value);
    insert_node(nodeList, node3, baseNode); // 40
    insert_node(nodeList, node4, baseNode); // 19
    insert_node(nodeList, node5, baseNode); // 31
    insert_node(nodeList, node6, baseNode); // 35
    insert_node(nodeList, node7, baseNode); // 45
    show_list(nodeList);
    // 最终期望排序 19, 28, 31, 33, 35, 40, 45
    
    System.out.print("请输入要查找的Node: ");
    Scanner user_input = new Scanner(System.in);
    Integer search_value = user_input.nextInt();
    System.out.println("输入的值为: " + search_value);
    // int node_index = nodeList.indexOf(new myNode(search_value)); // 这里因为比较的是'对象的值', 不能用indexOf() XD
    Optional<myNode> search_node = nodeList.stream().filter( (cur_node) -> {
      return cur_node.value == search_value;
    }).findFirst(); // 过滤完后 FindFirst()
    
    search_node.ifPresent( (serach_node) -> {
      System.out.println("取得" + search_node.get().value + "的信息");
      System.out.println("左节点: " + search_node.get().leftNode.value);
      System.out.println("右节点 : " + search_node.get().rightNode.value);
    } );
    

  } // main函数结束
}
