package com.huawei.test.taskplan;

import java.util.Comparator;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 高效的任务规划 <a
 * href="https://www.online1987.com/%e9%ab%98%e6%95%88%e7%9a%84%e4%bb%bb%e5%8a%a1%e8%a7%84%e5%88%92/">题目地址</a>
 */
public class Main2 {

  /**
   * 动态规划标准方式解
   */
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int m = sc.nextInt();
    for (int i = 0; i < m; i++) {
      //每组任务
      int n = sc.nextInt();
      TreeMap<Integer, Integer> tm = new TreeMap<>(Comparator.reverseOrder());
      for (int j = 0; j < n; j++) {
        int bB = sc.nextInt(); //设置时间
        int jJ = sc.nextInt(); //运行时间
        tm.put(jJ, bB);
      }
      System.out.println(calTime(tm));
    }
  }

  /**
   * 计算时间
   *
   * @param tm reverse treemap
   * @return time
   */
  static int calTime(TreeMap<Integer, Integer> tm) {

    //time[i] = max(time[i-1], timeRun[i]))
    //timeRun[i] = totalConfTime[i-1] + confTime[i] + runTime[i]

    int n = tm.size();
    int[] time = new int[n+1];
    time[0] = 0;

    AtomicInteger totalConfTime = new AtomicInteger();
    AtomicInteger index = new AtomicInteger();
    tm.forEach((key, value) -> {
      int i = index.get();
      int nthTime = totalConfTime.get() + key + value;
      time[i + 1] = Math.max(time[i], nthTime);
      totalConfTime.addAndGet(value);
      index.incrementAndGet();
    });

    return time[n];
  }
}

