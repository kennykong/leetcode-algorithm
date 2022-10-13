package com.leetcode.premute_46;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Solution {

  public List<List<Integer>> permute(int[] nums) {
    List<Integer> nums1 = Arrays.stream(nums).boxed().collect(Collectors.toCollection(ArrayList::new));
    backtrack(nums1, new ArrayList<>(), new ArrayList<>());
    return result;
  }

  List<List<Integer>> result = new ArrayList<>();

  void backtrack(List<Integer> nums, List<Integer> path, List<Integer> used) {

    if (path.size() == nums.size()) {
      result.add(new ArrayList<>(path));
      return;
    }

    for(int i=0; i<nums.size(); i++) {

      //做出选择
      Integer i1 = nums.get(i);
      if(used.contains(i1)) continue;

      used.add(i1);
      path.add(i1);
      backtrack(nums, path, used);
      //撤销选择
      used.remove(i1);
      path.remove(i1);
    }
  }

  public static void main(String[] args) {
    Solution s =new Solution();
    int[] p = new int[]{1,2,3};
    System.out.println(s.permute(p));
  }
}
