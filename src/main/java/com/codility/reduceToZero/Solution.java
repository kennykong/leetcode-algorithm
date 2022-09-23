package com.codility.reduceToZero;

// you can also use imports, for example:
// import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");

public class Solution {

  public int solution(String S) {
    // write your code in Java SE 8
    System.out.println(S);
    if (S.length() < 1 || S.length() > 1000000) {
      throw new RuntimeException();
    }
    int x = Integer.parseInt(S, 2);
    System.out.println(x);

    int step = 0;
    while (x != 0) {
      if (x % 2 != 0) {
        x = x - 1;
        System.out.println("x1:" + x);
        step++;
      } else {
        x = x / 2;
        System.out.println("x2:" + x);
        step++;
      }
    }
    return step;
  }
}
