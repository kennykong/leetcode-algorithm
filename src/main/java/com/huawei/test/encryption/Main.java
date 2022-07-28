package com.huawei.test.encryption;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * 敏感字段加密
 * <a
 * href="https://www.online1987.com/%e6%95%8f%e6%84%9f%e5%ad%97%e6%ae%b5%e5%8a%a0%e5%af%86/">题目地址</a>
 */
public class Main {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    String line = sc.next();
    //1.捕获双引号中的内容
    //2.把_替换成-
    //3.再用split
    //4.替换成想要的***
    //5.拼接
    //6.还原-
    String regex = "\"\\w*\"";
    List<String> matches = match(line, regex).stream()
        .filter(x -> x.contains("_"))
        .collect(Collectors.toList());
    List<String> matches1 = matches.stream()
        .map(x -> x.replaceAll("_", "-"))
        .collect(Collectors.toList());
    String line1 = line;
    for (int i = 0; i < matches.size(); i++) {
      String s = matches.get(i);
      String t = matches1.get(i);
      line1 = line1.replace(s, t);
    }

    String[] commands = line1.split("_");
    if (n > commands.length) {
      System.out.println("ERROR");
    }
    commands[n] = "******";
    List<String> commands1 = Arrays.stream(commands)
        .filter(x -> (!(x == null || x.equals(""))))
        .collect(Collectors.toList());
    String rst = String.join("_", commands1);
    String rst1 = rst.replaceAll("-", "_");
    System.out.println(rst1);
  }

  static List<String> match(String line, String regex) {
    Pattern p = Pattern.compile(regex);
    Matcher m = p.matcher(line);
    List<String> l = new ArrayList<>();
    while (m.find()) {
      l.add(m.group());
    }
    return l;
  }
}
