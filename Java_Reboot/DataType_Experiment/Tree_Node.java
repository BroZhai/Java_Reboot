package Java_Reboot.DataType_Experiment;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class Tree_Node {
  // 我们再来Java中玩一下 树, 尝试实现一个'二分查找树'

  public static void swap_node(myNode a, myNode b){
    myNode tmp_node = a;
    a = b;
    b = tmp_node;
  }

  public static void insert_node(List<myNode> nodeList, myNode newNode, myNode baseNode){
      int cur_index = nodeList.indexOf(baseNode);
      Optional<myNode> cur_left_node = Optional.ofNullable(nodeList.get(cur_index*2+1));
      myNode cur_right_node = nodeList.get(2*cur_index+2);
      if(newNode.value > baseNode.value){
        if(cur_right_node == null){
          nodeList.set(2*cur_index+2, newNode);
          return;
        }
        insert_node(nodeList, newNode, cur_right_node); 
      }else if(newNode.value < baseNode.value){
        if(cur_left_node == null){

        }
      }

    }

      
    }
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
    myNode node1 = new myNode(33);
    myNode node2 = new myNode(28);
    myNode node3 = new myNode(40);
    myNode node4 = new myNode(19);
    myNode node5 = new myNode(31);
    myNode node6 = new myNode(35);
    myNode node7 = new myNode(45);

    List<myNode> nodeList = List.of(node1); // 默认排序 
    // 最终期望排序 19, 28, 31, 33, 35, 40, 45


  } // main函数结束
}
