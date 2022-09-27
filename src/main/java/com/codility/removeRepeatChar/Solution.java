package com.codility.removeRepeatChar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 消除字符串中重复的字母，每一个字母有一个代价，要求消除重复，而且代价最小。
 */
public class Solution {

  public int solution(String S, int[] C) {
    // write your code in Java SE 8
    int len = S.length();
    if (len != C.length || len > 100000) {
      throw new RuntimeException();
    }
    Map<String, List<Integer>> reptMap = new HashMap<>();
    char lastChar = S.charAt(0);
    String lastReptChar = "";
    Integer lastReptCharPos = 0;
    boolean reptStat = false;
    for (int i = 1; i < len; i++) {
      char curChar = S.charAt(i);

      if (reptStat) {
        //在重复处理流程中
        if (lastChar == curChar) {
          lastReptChar = String.valueOf(curChar);
          String key = lastReptCharPos + lastReptChar;
          List<Integer> reptList = reptMap.get(key);
          reptList.add(C[i]);
//          reptMap.put(key, reptList);
          reptStat = true;
        } else {
          reptStat = false;
        }
      } else {
        //第一次发现有重复
        if (lastChar == curChar) {
          lastReptCharPos++;
          lastReptChar = String.valueOf(curChar);
          List<Integer> reptList = new ArrayList<>();
          reptList.add(C[i - 1]);
          reptList.add(C[i]);
          reptMap.put(lastReptCharPos + lastReptChar, reptList);
          reptStat = true;
        } else {
          reptStat = false;
        }
      }
      lastChar = curChar;
    }

    int total = reptMap.entrySet().stream().mapToInt(value -> deleteScores(value.getValue())).sum();

    return total;
  }

  int deleteScores(List<Integer> reptList) {

    List<Integer> sortedList = reptList.stream().sorted().collect(Collectors.toList());
    if (sortedList.size() > 1) {
      sortedList.remove(sortedList.size() - 1);
    }
    int score = sortedList.stream().mapToInt(Integer::intValue).sum();
    return score;
  }


  public static void main(String[] args) {
    Solution s = new Solution();
    int rst = s.solution("aaabbbccc", new int[]{3, 2, 1, 3, 2, 1, 1, 2, 3});
    System.out.println(rst);
  }
}
