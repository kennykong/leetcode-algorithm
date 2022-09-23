package com.codility.binaryGap;

// you can also use imports, for example:
// import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");

import java.util.Stack;

public class Solution {

  public int solution(int N) {
    // write your code in Java SE 8
    if (N < 1 || N > 2147483647) {
      throw new RuntimeException();
    }
    String s = Integer.toString(N, 2);
    System.out.println(s);
    int len = s.length();
    char lastChar = s.charAt(0);
    Stack<String> stack = new Stack<>();
//    int binGaps = 0;
    //10000010001
    int gapLong = 0;
    int maxGap = 0;
    for (int i = 1; i < len; i++) {
      char curChar = s.charAt(i);
      if (curChar == '0') {
        if (curChar != lastChar) {
          stack.push("10");
        }
        if(stack.size()!=0) {
          gapLong++;
        }

      }
      if (curChar == '1') {
        if (curChar != lastChar) {
          if (stack.size() > 0) {
            String a = stack.peek();
            if (a.equals("10")) {
              stack.pop();
              maxGap = Math.max(gapLong, maxGap);
              gapLong = 0;
//              binGaps++;
            }
          }
        }
      }
      lastChar = curChar;
    }
    return maxGap;
  }

  public static void main(String[] args) {
    Solution s = new Solution();
    int a = s.solution(1041);
    System.out.println(a);
  }

}
