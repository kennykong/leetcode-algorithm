package com.leetcode.btreeMaxPathSum_124;


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.ArrayList;
import java.util.List;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

 class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
 }
public class Solution {

  int ans = Integer.MIN_VALUE;
  List<Integer> maxPath = new ArrayList<>();

  public int maxPathSum(TreeNode root) {
    if (root == null) { return 0; }
    oneSideMax(root,"-");
    System.out.println(maxPath);
    return ans;
  }

  int oneSideMax(TreeNode root, String sp) {
    if (root == null) {
      return 0;
    }
    maxPath.add(root.val);
    System.out.println(sp+"root:"+root.val);
    int left = Math.max(0, oneSideMax(root.left, sp+"-"));
    System.out.println(sp+"left:"+left);
    int right = Math.max(0, oneSideMax(root.right, sp+"-"));
    System.out.println(sp+"right:"+right);
    System.out.println(sp+"ans before:"+ans);
    int pathSum = left + right + root.val;
    System.out.println(sp+"pathSum:"+pathSum);
    if(pathSum <= ans) {
      maxPath.remove((Integer) root.val);
    }
    ans = Math.max(ans, pathSum);
    System.out.println(sp+"ans after:"+ans);
    int rt = Math.max(left, right) + root.val;
    System.out.println(sp+"rt:"+rt);
    return rt;
  }
}
//leetcode submit region end(Prohibit modification and deletion)

