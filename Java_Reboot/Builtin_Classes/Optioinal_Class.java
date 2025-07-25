package Java_Reboot.Builtin_Classes;

import java.util.Optional; // 导入Optional包

public class Optioinal_Class {
  // 我们来了解一下 Optioanl类是干啥的, 本质上就是一个'数据篮子', 表示数据'可能为空'而不返回错误
  public static void main(String[] args) {
    // 1. 创建一个Optional对象
    Optional<String> must_have_value = Optional.of("Content"); // 使用.of()直接创建一个'有数据'的Optional对象
    Optional<String> maybe_have_value = Optional.ofNullable(null); // 创建一个 '可能有数据'的 Optional对象 (这里传的null 表示没有数据)
    Optional<String> no_value = Optional.empty(); // 单纯构造一个 '无数据'的Optional对象

    // 2. 检查Optional对象中的'值'是否存在
    System.out.println("must_have_value 中有值吗? " + must_have_value.isPresent());
    must_have_value.ifPresent(System.out::println); // 值存在, 直接进行打印输出
    
    System.out.println("maybe_have_value 中有值吗? " + maybe_have_value.isPresent());
    maybe_have_value.ifPresentOrElse( (value) -> System.out.println("maybe_have_value中的值为: " + maybe_have_value.get()), () -> {System.out.println("maybe_have_value是一个空Optional对象!");});

    System.out.println("no_value 中有值吗? " + no_value.isPresent());
    no_value.ifPresentOrElse( (value) -> System.out.println("no_value中的值为: " + maybe_have_value.get()), () -> {System.out.println("no_value是一个空Optional对象!");});
  }
}
