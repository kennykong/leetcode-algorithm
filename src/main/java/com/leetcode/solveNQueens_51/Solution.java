package com.leetcode.solveNQueens_51;


import java.util.ArrayList;
import java.util.List;

public class Solution {

  public List<List<String>> solveNQueens(int n) {
    backtrack(n, new ArrayList<>());
    return results;
  }

  List<List<String>> results = new ArrayList<>();

  void backtrack(int nLines, List<String> path) {
    if (nLines == path.size()) {
      results.add(new ArrayList<>(path));
      return;
    }

    for (int i = 0; i < nLines; i++) {

      //做出选择
      //i 是 col， path的size是row

      if (isSafe(i, path)) {
        //这行加入
        String line = buildLine(nLines, i);
        path.add(line);
        backtrack(nLines, path);
        //撤销选择
        path.remove(line);
      }
    }

  }

  String buildLine(int n, int queenPos) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < n; i++) {
      if (i == queenPos) {
        sb.append('Q');
      } else {
        sb.append('.');
      }
    }
    return sb.toString();
  }

  boolean isSafe(int pos, List<String> path) {
    if (path == null || path.isEmpty()) {
      return true;
    }
    //上
    if (upNotSafe(pos, path)) {
      return false;
    }
    //上左
    if (upLeftNotSafe(pos, path)) {
      return false;
    }
    //上右
    if (upRightNotSafe(pos, path)) {
      return false;
    }

    return true;
  }

  boolean upNotSafe(int pos, List<String> path) {
    int lastPos = path.size() - 1;
    if (lastPos >= 0) {
      for (int i = lastPos; i >= 0; i--) {
        if (path.get(i).charAt(pos) == 'Q') {
          return true;
        }
      }
    }
    return false;
  }

  boolean upLeftNotSafe(int pos, List<String> path) {
    int row = path.size() - 1;
    int col = pos - 1;
    if (row >= 0 && col >= 0) {
      for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
        if (path.get(i).charAt(j) == 'Q') {
          return true;
        }
      }
    }
    return false;
  }

  boolean upRightNotSafe(int pos, List<String> path) {
    int row = path.size() - 1;
    int col = pos + 1;
    int colMax = path.get(0).length() - 1;
    if (row >= 0 && col <= colMax) {
      for (int i = row, j = col; i >= 0 && j <= colMax; i--, j++) {
        if (path.get(i).charAt(j) == 'Q') {
          return true;
        }
      }
    }
    return false;
  }

  public static void main(String[] args) {
    Solution s = new Solution();
    List<List<String>> rst = s.solveNQueens(4);
    System.out.println(rst);
  }
}
