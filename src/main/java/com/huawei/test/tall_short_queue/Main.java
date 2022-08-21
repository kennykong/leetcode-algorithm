package com.huawei.test.tall_short_queue;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 高矮个子排队 <a
 * href="https://www.online1987.com/%e9%ab%98%e7%9f%ae%e4%b8%aa%e5%ad%90%e6%8e%92%e9%98%9f/">...</a>
 */
public class Main {

  public static void main(String[] args) {
    // https://blog.csdn.net/wd402922965/article/details/121356307
    // 使用双指针，当前指向和下一个进行比较，如果是偶数下标就判断是否大，奇数判断是否小
    //5,3,1,2,3
    //1,2,3,4,5
    Scanner sc = new Scanner(System.in);
    String s = sc.nextLine();
    String[] queueS = s.split(",");
    int[] queue = Arrays.stream(queueS).mapToInt(Integer::parseInt).toArray();
    int[] res = queue(queue);
    String[] resList = Arrays.stream(res).mapToObj(String::valueOf).toArray(String[]::new);
    String res1 = String.join(",", resList);
    System.out.println(res1);
  }

  public static int[] queue(int[] array) {
    int current = 0;
    int next;
    while (current < array.length - 1) {
      next = current + 1;
      if (current % 2 == 0) {
        if (array[current] < array[next]) {
          swap(array, current, next);
        }
      } else {
        if (array[current] > array[next]) {
          swap(array, current, next);
        }
      }
      current++;
    }
    return array;
  }

  public static void swap(int[] array, int i, int j) {
    int temp = array[i];
    array[i] = array[j];
    array[j] = temp;
  }
}
