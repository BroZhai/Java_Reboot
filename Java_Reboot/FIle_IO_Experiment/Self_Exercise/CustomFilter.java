package Java_Reboot.FIle_IO_Experiment.Self_Exercise;

import java.io.File; // File 类
import java.io.FilenameFilter; // FilenameFilter 过滤器接口, 要重写accept()方法; 

// 实现FilenameFilter类 (基于'文件过滤器模版' 创建自定义的 '文件过滤类'), 构造时要传入'文件后缀'
public class CustomFilter implements FilenameFilter{
    private String suffix;
    public CustomFilter(String file_suffix){
      this.suffix = file_suffix;
    }
    @Override
    public boolean accept(File path, String filename){
      boolean filter_result = filename.endsWith(this.suffix);
      return filter_result;
    }
  }
