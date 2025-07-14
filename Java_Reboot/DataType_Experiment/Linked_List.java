package Java_Reboot.DataType_Experiment;

// 链表相关
import java.util.LinkedList; // 
import java.util.List; // List为ArrayList和LinkedList的父类, 测试addALl()方法用

// 其他工具包
import java.util.Arrays;



public class Linked_List <E> {  
  
  public static <E> void main(String[] args) {
    LinkedList<E> my_list = new LinkedList<E>();
    my_list.add((E) "baka"); 
    Integer a = 99;
    my_list.add((E) a);
    if(my_list.offerFirst((E)"content before Baka")){ // 往'链表头'追加内容, 
      System.out.println("成功往baka前面添加元素");
    }
    
    List basic_alphabet_list = Arrays.asList("a","b","c");
    my_list.addAll(basic_alphabet_list); // 追加List对象至链表尾部

    System.out.println("当前链表中的内容为: ");
    for(E i: my_list){
      System.out.println(i);
    }

    if(my_list.removeFirst()!=null){
      System.out.println("成功移除了链表的第一个元素");
    }

    // my_list.clear();

    System.out.println("当前链表的大小为: " + my_list.size()); // 6 --> 5

    System.out.println("当前链表中index为2的元素是 " + my_list.get(2)); // a
    System.out.println("当前的首部元素为: " + my_list.getFirst()); // baka
    System.out.println("链表是否包含'c'元素? " + my_list.contains("c")); // true

    my_list.offer((E)"end"); // 这是'栈'的操作, 在栈底追加元素, 等效于add()

    System.out.println("链表最后展示: " +  my_list.toString());
    Object[] my_array = my_list.toArray();
    System.out.println("转成Object[]: " + Arrays.toString(my_array));
  }
}
