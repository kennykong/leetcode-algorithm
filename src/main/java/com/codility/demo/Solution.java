package com.codility.demo;

import java.util.Arrays;

/**
 * This is a demo task.
 * <p>
 * Write a function:
 * <p>
 * class Solution { public int solution(int[] A); }
 * <p>
 * that, given an array A of N integers, returns the smallest positive integer (greater than 0) that
 * does not occur in A.
 * <p>
 * For example, given A = [1, 3, 6, 4, 1, 2], the function should return 5.
 * <p>
 * Given A = [1, 2, 3], the function should return 4.
 * <p>
 * Given A = [−1, −3], the function should return 1.
 * <p>
 * Write an efficient algorithm for the following assumptions:
 * <p>
 * N is an integer within the range [1..100,000]; each element of array A is an integer within the
 * range [−1,000,000..1,000,000]. Copyright 2009–2022 by Codility Limited. All Rights Reserved.
 * Unauthorized copying, publication or disclosure prohibited.
 */
public class Solution {

  public int solution(int[] A) {
    // write your code in Java SE 8
    int i = 1;
    for (; i <= 1000000; i++) {
      int finalI = i;
      if (Arrays.stream(A).noneMatch(e -> e == finalI)) {
        break;
      }
    }
    return i;
  }
}