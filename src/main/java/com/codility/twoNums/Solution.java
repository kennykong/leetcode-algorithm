package com.codility.twoNums;

// you can also use imports, for example:
// import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

class Solution {

  public int solution(int[] A) {
    // write your code in Java SE 8
    int len = A.length;
    if (len < 2 || len > 100000) {
      throw new RuntimeException();
    }
    Map<Integer, List<List<Point>>> map = new HashMap<>();

    for (int i = 0; i < len - 1; i++) {
      Integer sum = A[i] + A[i + 1];
      List<List<Point>> pairs = map.get(sum);
      if (pairs == null) {
        pairs = new ArrayList<>();
        List<Point> pair = new ArrayList<>();
        Point p0 = new Point(i, A[i]);
        pair.add(p0);
        Point p1 = new Point(i + 1, A[i + 1]);
        pair.add(p1);
        pairs.add(pair);
        map.put(sum, pairs);
      } else {
        List<Point> pair = new ArrayList<>();
        Point p0 = new Point(i, A[i]);
        pair.add(p0);
        Point p1 = new Point(i + 1, A[i + 1]);
        pair.add(p1);
        if (checkPair(pair, pairs)) {
          pairs.add(pair);
        }
      }
    }

    List<Entry<Integer, List<List<Point>>>> l = map.entrySet().stream()
        .sorted((e1, e2) -> e2.getValue().size() - e1.getValue().size()).collect(
            Collectors.toList());
    return l.get(0).getValue().size();
  }

  //排重
  boolean checkPair(List<Point> pair1, List<List<Point>> pairs) {
    List<Point> pair0 = pairs.get(pairs.size() - 1);
    return pair0.get(1).pos != pair1.get(0).pos;
  }


  public static void main(String[] args) {
    Solution s = new Solution();
    //10, 1, 3, 1, 2, 2, 1, 0, 4
    //5, 3, 1, 3, 2, 3
    //9, 9, 9, 9, 9
    //1, 5, 2, 4, 3, 3
    int rst = s.solution(new int[]{1, 5, 2, 4, 3, 3});
    System.out.println(rst);
  }
}

class Point {

  int pos;
  int value;

  public Point(int pos, int value) {
    this.pos = pos;
    this.value = value;
  }
}
