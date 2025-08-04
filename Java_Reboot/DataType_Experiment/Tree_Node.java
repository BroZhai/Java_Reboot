package Java_Reboot.DataType_Experiment;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Tree_Node {
  // 我们再来Java中玩一下 树, 尝试实现一个'二分查找树'

  public static void swap_node(List<myNode> nodeList, myNode a, myNode b){
    myNode tmp_node = a;
    int a_index = nodeList.indexOf(a);
    int b_index = nodeList.indexOf(b);
    nodeList.set(a_index, b);
    nodeList.set(b_index, tmp_node);
  }

  public static void insert_node(myNode root, myNode newNode){
    if (newNode.value < root.value) {
      if(root.leftNode != null){
        insert_node(root.leftNode, newNode);
      }else{
        root.leftNode = newNode;
        System.out.println(newNode.value + " 插入到了 " + root.value + " 的左侧");
        return;
      }
    } else if(newNode.value > root.value){
      if(root.rightNode != null){
        insert_node(root.rightNode, newNode);
      }else{
        root.rightNode = newNode;
        System.out.println(newNode.value + " 插入到了 " + root.value + " 的右侧");
        return;
      }
    }
    
  }

  /*   public static void insert_node(List<myNode> nodeList, myNode newNode, myNode baseNode){
      int cur_index = nodeList.indexOf(baseNode);
      Optional<myNode> cur_left_node = (cur_index*2 + 1 < nodeList.size() ? Optional.ofNullable(nodeList.get(cur_index*2+1)) : Optional.empty()); // 条件: 检查是否超界(node不存在)? node存在: node不存在
      // Tips: ? Optional.ofNullable(nodeList.get(cur_index*2+1)) : Optional.empty() 在括号内 和 括号外 判断都行
      Optional<myNode> cur_right_node = (cur_index*2 + 2 < nodeList.size() ? Optional.ofNullable(nodeList.get(cur_index*2+2)) : Optional.empty());

      // 和当前node进行比较
      if(newNode.value < baseNode.value){ // 小于当前节点
        cur_left_node.ifPresent((left_node) -> { // 左节点存在, 进行进一步的递归比较
          System.out.println("进入了左递归!");
          insert_node(nodeList, newNode, left_node);
          return;
        });
        // 当前节点的左节点为空, 直接赋值到左节点上
        baseNode.leftNode = newNode;
        nodeList.add(cur_index, newNode); // 在'指定索引 前' 追加元素
        return;
        

      }else if(newNode.value > baseNode.value){
        cur_right_node.ifPresent((right_Node) -> { // 右节点存在, 进一步递归比较
          System.out.println("进入了右递归");
          insert_node(nodeList, newNode, right_Node);
          return;
        });
        // 当前右节点为空, 直接赋值
        baseNode.rightNode = newNode;
        nodeList.add(cur_index+1, newNode);
        return;
      }

    }
      */


    public static void traverse_tree(List<myNode> result_list, myNode root){
      if(root == null){
        return; // 反复迭代遍历到了 最根的null, 此时应该终止遍历
      }
      traverse_tree(result_list, root.leftNode); // 当前元素先遍历左树
      result_list.add(root);
      traverse_tree(result_list, root.rightNode); // 左树完成再去看右树 (右树中又有可能有左树分岔, 优先又走左树遍历去了, 直到最后遇到左树根节点的null才终止, 然后逐层网上看'走过node'的右树)
    }



  static class myNode{ // 自定义内部类 '树', 每个树包含 自己的构造函数, 值, 左叶子节点, 右叶子节点
      int value; // 值
      public myNode(int input_value){
        this.value = input_value;
      }
      myNode leftNode = null;
      myNode rightNode = null;

      // 比较两个node对象的'值'是否一致
      @Override
      public boolean equals(Object o){
        if (this == o) return true;
        if(o==null || getClass() != o.getClass()) return false; // 比较类型为空 或 类型不匹配
        myNode comapre_node = (myNode) o;
        return this.value == comapre_node.value; 
      }
    }


  public static void main(String[] args) {
    myNode baseNode = new myNode(33);
    myNode node2 = new myNode(28);
    myNode node3 = new myNode(40);
    myNode node4 = new myNode(19);
    myNode node5 = new myNode(31);
    myNode node6 = new myNode(35);
    myNode node7 = new myNode(45);

    List<myNode> result_list = new ArrayList<>(); // 展示结果的List
    insert_node(baseNode, node2);
    insert_node(baseNode, node3);
    insert_node(baseNode, node4);
    insert_node(baseNode, node5);
    insert_node(baseNode, node6);
    insert_node(baseNode, node7);
    insert_node(baseNode, new myNode(44)); // 插入一个新的测试值, 应当插入45的左侧, 但右侧仍为空


    // 最终期望排序 19, 28, 31, 33, 35, 40, 45
    traverse_tree(result_list, baseNode);

    System.out.print("请输入要查找的Node: ");
    Scanner user_input = new Scanner(System.in);
    Integer search_value = user_input.nextInt();
    System.out.println("输入的值为: " + search_value);
    // int node_index = nodeList.indexOf(new myNode(search_value)); // 这里因为比较的是'对象的值', 不能用indexOf() XD
    Optional<myNode> search_node = result_list.stream().filter( (cur_node) -> {
      return cur_node.value == search_value;
    }).findFirst(); // 过滤完后 FindFirst()
    
    search_node.ifPresent( (serach_node) -> {
      System.out.println("\n取得" + serach_node.value + "的信息");
      // 遍历到'根节点'时, 注意处理'子节点为null'的问题 (注意内部括号运算的优先级! 先'求值', 再拼凑输出)
      System.out.println("左节点: " + (serach_node.leftNode == null ? "为空" : serach_node.leftNode.value));
      System.out.println("右节点: " + (serach_node.rightNode == null ? "为空" : serach_node.rightNode.value));
      return;
    } );
    

  } // main函数结束
}
