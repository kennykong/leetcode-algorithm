package com.leetcode.solveNQueens_51;

import java.util.ArrayList;
import java.util.List;

public class Solution1 {

  public List<List<String>> res = new ArrayList<>();

  /* 输入棋盘边长 n，返回所有合法的放置 */
  List<List<String>> solveNQueens(int n) {
    // '.' 表示空，'Q' 表示皇后，初始化空棋盘。
    char[][] board = initBoard(n);
    backtrack(board, 0);
    return res;
  }

  char[][] initBoard(int n) {
    char[][] board = new char[n][n];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        board[i][j] = '.';
      }
    }
    return board;
  }

  // 路径：board 中小于 row 的那些行都已经成功放置了皇后
  // 选择列表：第 row 行的所有列都是放置皇后的选择
  // 结束条件：row 超过 board 的最后一行
  void backtrack(char[][] board, int row) {
    // 触发结束条件
    if (row == board.length) {
      res.add(boardToList(board));
      return;
    }

    int n = board[row].length;
    for (int col = 0; col < n; col++) {
      // 排除不合法选择
      if (!isValid(board, row, col)) {
        continue;
      }
      // 做选择
      board[row][col] = 'Q';
      // 进入下一行决策
      backtrack(board, row + 1);
      // 撤销选择
      board[row][col] = '.';
    }
  }

  List<String> boardToList(char[][] board) {
    List<String> list = new ArrayList<>();
    for (int i = 0; i < board.length; i++) {
      String line = new String(board[i]);
      list.add(line);
    }
    return list;
  }

  /* 是否可以在 board[row][col] 放置皇后？*/
  boolean isValid(char[][] board, int row, int col) {
    int n = board.length;
    // 检查列是否有皇后互相冲突
    for (int i = 0; i <= row; i++) {
      if (board[i][col] == 'Q') {
        return false;
      }
    }
    // 检查右上方是否有皇后互相冲突
    for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
      if (board[i][j] == 'Q') {
        return false;
      }
    }
    // 检查左上方是否有皇后互相冲突
    for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
      if (board[i][j] == 'Q') {
        return false;
      }
    }
    return true;
  }

  public static void main(String[] args) {
    Solution s = new Solution();
    List<List<String>> rst = s.solveNQueens(4);
    System.out.println(rst);
  }
}
