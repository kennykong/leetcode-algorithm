package com.codility.countriesCount;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * 性能不合格（上下左右）
 * 改（右下左上）没用
 */
public class Solution {

  public int solution(int[][] A) {
    // write your code in Java SE 8
    Set<Point> usedPoint = new HashSet<>();
    Map<String, List<Point>> countryMap = new HashMap<>();
    int rowLen = A.length;
    int countries = 0;
    for (int i = 0; i < rowLen; i++) {
      int colLen = A[i].length;
      for (int j = 0; j < colLen; j++) {
        List<Point> nearPoints = new ArrayList<>();
        Point currentPoint = new Point(i, j, A[i][j]);
        if (!usedPoint.contains(currentPoint)) {
          findNearPoints(rowLen - 1, colLen - 1, currentPoint, A, usedPoint, nearPoints);
          countries++;
          countryMap.put("" + countries, nearPoints);
        }
      }
    }
    return countryMap.size();
  }

  void findNearPoints(int rowLimit, int colLimit, Point currPoint, int[][] allPoint,
      Set<Point> usedPoint, List<Point> nearPoints) {

    System.out.println("p:"+currPoint.toString());

    usedPoint.add(currPoint);

    int x = currPoint.x;

    int y = currPoint.y;

    int v = currPoint.value;


    //右
    int yRight = currPoint.y + 1;
    if (yRight <= colLimit) {
      Point pRight = new Point(x, yRight, allPoint[x][yRight]);
      if (!usedPoint.contains(pRight)) {
        if (v == pRight.value) {
          nearPoints.add(pRight);
          findNearPoints(rowLimit, colLimit, pRight, allPoint, usedPoint, nearPoints);
        }
      }
    }

    //下
    int xDown = currPoint.x + 1;
    if (xDown <= rowLimit) {
      Point pDown = new Point(xDown, y, allPoint[xDown][y]);
      if (!usedPoint.contains(pDown)) {
        if (v == pDown.value) {
          nearPoints.add(pDown);
          findNearPoints(rowLimit, colLimit, pDown, allPoint, usedPoint, nearPoints);
        }
      }
    }

    //左
    int yLeft = currPoint.y - 1;
    if (yLeft >= 0) {
      Point pLeft = new Point(x, yLeft, allPoint[x][yLeft]);
      if (!usedPoint.contains(pLeft)) {
        if (v == pLeft.value) {
          nearPoints.add(pLeft);
          findNearPoints(rowLimit, colLimit, pLeft, allPoint, usedPoint, nearPoints);
        }
      }
    }

    //上
    int xUp = currPoint.x - 1;
    if (xUp >= 0) {
      Point pUp = new Point(xUp, y, allPoint[xUp][y]);
      if (!usedPoint.contains(pUp)) {
        if (v == pUp.value) {
          nearPoints.add(pUp);
          findNearPoints(rowLimit, colLimit, pUp, allPoint, usedPoint, nearPoints);
        }
      }
    }

  }
}

class Point {

  public Point() {
  }

  public Point(int x, int y, int value) {
    this.x = x;
    this.y = y;
    this.value = value;
  }

  int x;
  int y;
  int value;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Point point = (Point) o;
    return x == point.x && y == point.y && value == point.value;
  }

  @Override
  public int hashCode() {
    return Objects.hash(x, y, value);
  }

  @Override
  public String toString() {
    return "Point{" +
        "x=" + x +
        ", y=" + y +
        ", value=" + value +
        '}';
  }

  public static void main(String[] args) {
    Solution s = new Solution();
    int[][] a = new int[][]{{5, 4, 4}, {4, 3, 4}, {3, 2, 4}, {2, 2, 2}, {3, 3, 4}, {1, 4, 4},
        {4, 1, 1}};
    int r = s.solution(a);
    System.out.println(r);
  }

}
