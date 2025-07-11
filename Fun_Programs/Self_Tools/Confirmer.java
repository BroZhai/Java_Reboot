package Fun_Programs.Self_Tools;

import java.util.regex.Pattern;
import java.util.regex.Matcher;


// 之前写FileTool的时候自己摸出来的一些比较通用的方法, 这里单独打一个包
public class Confirmer {
  // 校验 Y y N n 输入合法性
    public static boolean validate_yes_no(String user_answer) {
      String input_content = user_answer.trim();
      Pattern yn_RE = Pattern.compile("^[YyNn]{1}$");
      Matcher yn_matcher = yn_RE.matcher(input_content);
      return yn_matcher.matches();
    }

   // 将用户输入的 Y y N n 转换为boolean进行返回
  public static boolean get_yes_no(String user_input) {
    char[] yn_arr = user_input.toCharArray();
    return ((yn_arr[0] == 'Y') || (yn_arr[0] == 'y')); // Y y 返回true, N n 为false (需先走一遍上面的validate_yes_no来确保'输入有效')
  }


}
