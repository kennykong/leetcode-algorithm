package com.huawei.test.taskplan;

import java.util.Scanner;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 高效的任务规划
 * <a
 * href="https://www.online1987.com/%e9%ab%98%e6%95%88%e7%9a%84%e4%bb%bb%e5%8a%a1%e8%a7%84%e5%88%92/">题目地址</a>
 */
public class Main {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int m = sc.nextInt();
    for (int i = 0; i < m; i++) {
      //每组任务
      int n = sc.nextInt();
      TreeMap<Integer, Integer> tm = new TreeMap<>();
      for (int j = 0; j < n; j++) {
        int bB = sc.nextInt();
        int jJ = sc.nextInt();
        tm.put(bB, jJ);
      }
      System.out.println(calTime(tm));
    }
  }

  static int calTime(TreeMap<Integer, Integer> tm) {

    AtomicInteger totalTime = new AtomicInteger();
    final int[] lastRunTime = {0};
    final int[] lastConfTime = {0};
    tm.forEach((k, v) -> {
      totalTime.addAndGet(k);
      lastRunTime[0] = Math.max(lastRunTime[0], v);
      lastConfTime[0] = k;

      totalTime.addAndGet(-1 * lastConfTime[0]);

    });
    totalTime.addAndGet(lastRunTime[0]);
    return totalTime.get();
  }
}

