package com.leetcode.regularMatch_10;

public class Solution {

  Boolean[][] memo;

  public boolean isMatch(String s, String p) {
    int m = s.length();
    int n = p.length();

    memo = new Boolean[m][n];

    return dp(s, 0, p, 0);
  }


  boolean dp(String s, int i, String p, int j) {
    int m = s.length();
    int n = p.length();

    if (j == n) {
      return i == m;
    }
    if (i == m) {
      //*和字符成对出现
      if ((n - j) % 2 == 1) {
        return false;
      }
      //X*Y*Z*情况出现
      for (; j + 1 < n; j += 2) {
        if (p.charAt(j + 1) != '*') {
          return false;
        }
      }
      return true;
    }

    if (memo[i][j] != null) {
      return memo[i][j];
    }

    boolean res;

    if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.') {

      if (j < n - 1 && p.charAt(j + 1) == '*') {
        //有*匹配，可以匹配0次或多次
        res = dp(s, i, p, j + 2) || dp(s, i + 1, p, j);
      } else {
        //无通配符老老实实匹配一次
//          i++;
//          j++;
        res = dp(s, i + 1, p, j + 1);
      }

    } else {

      if (j < n - 1 && p.charAt(j + 1) == '*') {
        //有*匹配，只能匹配0次
        res = dp(s, i, p, j + 2);
      } else {
        //无通配符匹配失败
        res = false;
      }

    }
    //当前结果记录
    memo[i][j] = res;
    return res;
  }

  public static void main(String[] args) {
    Solution s = new Solution();
    boolean res = s.isMatch("ab", ".*");
    System.out.println(res);
  }
}
