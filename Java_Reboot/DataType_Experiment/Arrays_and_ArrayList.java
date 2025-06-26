package Java_Reboot.DataType_Experiment;
import java.util.Arrays; // 导入Arrays类
import java.util.Collections;
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
    System.out.println("将其转换为'字符串'进行输出: " + Arrays.toString(num_arr) + ", 哇哦竟然自带方括号"); // Arrays.toString() 一维数组转字符串
    System.out.println("再来看看二维数组的deepToSting(): " + Arrays.deepToString(num_2d_arr)); // Arrays.deepToString() 二维数组转字符串

    /* Arrays.sort() 不支持二维数组的排序 XD */

    // Arrays.sort(num_2d_arr);
    /*System.out.println("\n对num_2d_arr用Arrays.sort()进行排序得: ");
    for(int[] cur_arr: num_2d_arr){ 
      for(int cur_element: cur_arr){ 
        System.out.print(cur_element + " ");
      }
    }*/

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

    System.out.println("\n");

    Integer[] big_Integers = {114, 514, 191, 981, 0};
    // 注: 此处的参数'big_Ingegers'要是'对象'  (e.g. Integer[], String[]), 像int[]这些'字面量'一律只会被视为'一种元素'
    ArrayList<Integer> big_ArrayList = new ArrayList<>(Arrays.asList(big_Integers)); 
    System.out.println("big_Integers的长度为: " + big_Integers.length);
    System.out.println("从big_Integers通过Arrays.asList()转成的big_Arraylist的长度是: "+ big_ArrayList.size());
    big_ArrayList.add(511); // 往转成的ArrayList中追加一个'新元素'
    System.out.print("往big_Arraylist中追加了一个新元素 511: ");
    for(Integer cur_int : big_ArrayList){
      System.out.print(cur_int+" ");
    }
    System.out.println("\n可以看到big_Integers成功转成了ArrayList\n");

    // Arrays.equals(数组1, 数组2), 判断这两个数组是否'完全相等' (包括内容 & 顺序)
    int[] left = {2,3,1};
    int[] right = {2,1,3};
    System.out.println("left[] 和 right[]数组是equals()的吗? " + Arrays.equals(left, right)); // false
    int[] empty = new int[5]; // 一会儿要fill()这个, 现在这里提前声明大小
    System.out.println("现在empty中的内容为: " + Arrays.toString(empty));
    Arrays.fill(empty, 6);
    System.out.println("全部填充.fill()内容后为: " + Arrays.toString(empty));
    Arrays.fill(empty, 1,4, 8); // 指定fill范围 [1,4) 
    System.out.println("选择填充下标1-3: " + Arrays.toString(empty));

    int[] empty_enlarge = Arrays.copyOf(empty, 10); // 使用.copyOf复制数组值, 并指定'新大小' 10,
    System.out.println(".copyOf()了empty[]的内容, 指定新数组empty_enlarge大小为10: "+Arrays.toString(empty_enlarge)); // 可以看到后面多了5个0


    /* ArrayList实验区 */
    System.out.println("\n现在是ArrayList时间");
    ArrayList<Integer> alist = new ArrayList<>(); // news: alist卖掉了 (悲
    alist.add(3); alist.add(2); alist.add(1); // 追加元素
    System.out.println("alist当前的内容为: " + alist.toString()); //toString()转字符串
    ArrayList<Integer> blist = new ArrayList<>();
    blist.add(6); blist.add(8); blist.add(6);
    System.out.println("blist当前的内容为: " + blist.toString());
    alist.addAll(blist);
    System.out.println("alist.addAll()拼接blist的结果为: " +alist.toString());
    System.out.println("alist当前的长度为: " + alist.size());
    alist.set(4, 9); // 替换下标4的元素内容为 9 (8 -> 9)
    System.out.println("替换了alist下标为4的内容: " + alist.toString()); 
    System.out.println("alist现在是否包含0? " + alist.contains(0)); // false
    System.out.println("alist中'6'出现的最后一次的下标: " + alist.lastIndexOf(6)); // 5
    alist.removeAll(blist); // alist移除blist中'包含'的元素, 用法和上面的addAll()一致
    System.out.println("移除了alist中与blist重叠的元素: "+alist.toString()); // 6不见了
    alist.clear(); // 清空了
    System.out.println("使用.clear()清空了alist的内容: "+ alist.toString());
    System.out.println("现在alist isEmpty吗? " + alist.isEmpty()); // true
    System.out.println("取出blist下标0-1的元素查看: "+blist.subList(0,2).toString());
    System.out.println("\n");
    
    // 动态ArrayList转成静态Arrays类, .toArray(指定'大对象'数组[数组大小]))
    Integer[] b_arr = blist.toArray(new Integer[blist.size()]); 
    // 此时 b_arr 就是静态数组了, 我们要用Arrays的'静态方法'对其进行操作
    System.out.println("试一下blist转成Arrays: " + Arrays.toString(b_arr) + " 当前的blist类型为: " + b_arr.getClass().getName()); // 成功转成Arrays ([Integer 即是 Integer[] )
    ArrayList<Integer> blist_copy = (ArrayList<Integer>)blist.clone(); // 黄色叹号告知 编译器'这么直接转OK吗'? (OK的)
    blist_copy.add(10);
    System.out.println("现在通过.clone()方法创建了一个blist_copy, 里面多加了一个元素'10'");
    System.out.println("blist的内容: " + blist.toString());
    System.out.println("blist_copy的内容: " + blist_copy.toString());

  }

}