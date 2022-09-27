package com.codility.competion.treePath;

// you can also use imports, for example:
// import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;


public class Solution {

  public int solution(int[] A, int[] B) {

    // write your code in Java SE 8
    if (A.length == 0 || B.length == 0) {
      return 0;
    }

    Tree t = new Tree();
    t.value = A[0];
    buildTree(A, B, t);

//    Map<Integer, List<List<Integer>>> pathGroups = new HashMap<>();
//    List<Integer> path = new ArrayList<>();
//    path.add(A[0]);
//    getTreePath(t, path, pathGroups);

    //穷举各个点两两组合
    Map<Integer, List<List<Integer>>> pointDistanceGroups = new HashMap<>();
    List<Integer> allPoints = getAllPoints(A, B);
    for (int i = 0; i < allPoints.size(); i++) {
      for (int j = i + 1; j < allPoints.size(); j++) {
        int p1 = allPoints.get(i);
        int p2 = allPoints.get(j);
        int distance = twoPointDistance(p1, p2, t);
        List<Integer> p2p = new ArrayList<>();
        p2p.add(p1);
        p2p.add(p2);
        List<List<Integer>> group = pointDistanceGroups.get(distance);
        if (group == null) {
          group = new ArrayList<>();
          group.add(p2p);
          pointDistanceGroups.put(distance, group);
        } else {
          group.add(p2p);
        }
      }
    }

    return getOddLinkPaths(pointDistanceGroups);
  }

  List<Integer> getAllPoints(int[] a, int[] b) {
    List<Integer> allPoints = new ArrayList<>();
    for (int i = 0; i < a.length; i++) {
      int a1 = a[i];
      int b1 = b[i];
      if (!allPoints.contains(a1)) {
        allPoints.add(a1);
      }
      if (!allPoints.contains(b1)) {
        allPoints.add(b1);
      }
    }
    return allPoints;
  }

  int twoPointDistance(int a, int b, Tree t) {

    System.out.println("a,b: " + a + "," + b);

    LinkedList<Integer> pathA = new LinkedList<>();
    toPointPath(a, t, pathA);
    LinkedList<Integer> pathB = new LinkedList<>();
    toPointPath(b, t, pathB);

    ArrayList<Integer> pathA1 = linkedListToArray(pathA);
    System.out.println("pathA1:" + pathA1);
    ArrayList<Integer> pathB1 = linkedListToArray(pathB);
    System.out.println("pathB1:" + pathB1);

    int maxCommonSize = Math.min(pathA1.size(), pathB1.size());

    List<Integer> commonPath = new ArrayList<>();
    for (int i = 0; i < maxCommonSize; i++) {
      if (Objects.equals(pathA1.get(i), pathB1.get(i))) {
        commonPath.add(pathA1.get(i));
      }
    }
    System.out.println("commonPath:" + commonPath);
    //去除最后一个节点，这是ab的最近公共祖先节点
    if (commonPath.size() > 0) {
      commonPath.remove(commonPath.size() - 1);
    }

    pathA1.removeAll(commonPath);
    System.out.println("removed pathA1:" + pathA1);

    pathB1.removeAll(commonPath);
    System.out.println("removed pathB1:" + pathB1);

    int size = pathA1.size() + pathB1.size() - 2;
    System.out.println("distance: " + size);
    System.out.println();
    return size;

  }

  ArrayList<Integer> linkedListToArray(LinkedList<Integer> l) {
    ArrayList<Integer> list = new ArrayList<>();
    while (l.size() > 0) {
      list.add(l.pollLast());
    }
    return list;
  }

  boolean toPointPath(int a, Tree t, LinkedList<Integer> path) {
    path.push(t.value);
    if (a == t.value) {
      return true;
    }

    List<Tree> children = t.children;
    if (children != null) {
      for (Tree t1 : children) {
        if (toPointPath(a, t1, path)) {
          return true;
        }
      }
    }

    path.pop();
    return false;
  }

  void buildTree(int[] A, int[] B, Tree tree) {
    int len1 = A.length;
    int len2 = B.length;
    if (len1 != len2) {
      throw new RuntimeException();
    }

    List<List<Integer>> pairs = new ArrayList<>();
    for (int i = 0; i < len1; i++) {
      Integer a = A[i];
      Integer b = B[i];
      List<Integer> pair = new ArrayList<>();
      pair.add(a);
      pair.add(b);
      pairs.add(pair);
    }
    buildTree1(A[0], pairs, tree);
  }

  void buildTree1(int parent, List<List<Integer>> pairs, Tree tree) {
    //pair用完，树构造完毕。
    if (pairs.isEmpty()) {
      return;
    }

    //构造好这一层的树
    List<List<Integer>> findPairs = searchForNode(parent, pairs);
    List<Integer> childrenL = getChildrenFromPairs(parent, findPairs);

    //定位到这一层的树（不用）
//    Tree tree1 = searchTreeForNode(parent, tree);

    //给这一层树加孩子
    if (tree.children == null) {
      tree.children = new ArrayList<>();
    }
    List<Tree> subTree = listToTrees(childrenL);
    tree.children.addAll(subTree);

    //将这一层树加到整棵树上(不用)

    //去除已生成树的pair
    List<List<Integer>> pairs1 = removeThisPair(parent, childrenL, pairs);

    //构造下一层树
    for (Tree t : subTree) {
      buildTree1(t.value, pairs1, t);
    }

  }

  List<Integer> getChildrenFromPairs(Integer parent, List<List<Integer>> findPairs) {
    List<Integer> child1s = new ArrayList<>();
    for (List<Integer> pair : findPairs) {
      Integer parent1 = pair.get(0);
//      System.out.println(
//          "parent1==parent:" + (Objects.equals(parent1, parent)) + ",parent1:" + parent1
//              + ",parent:" + parent);
      int child1 = pair.get(1);
      child1s.add(child1);
    }
    return child1s;
  }

  List<List<Integer>> removeThisPair(int parent, List<Integer> childrenL,
      List<List<Integer>> pairs) {
    return pairs.stream()
        .filter(x -> childrenL.stream().noneMatch(y -> x.contains(parent) && x.contains(y)))
        .collect(Collectors.toList());
  }

  List<Tree> listToTrees(List<Integer> children) {
    List<Tree> childrenTree = new ArrayList<>();
    for (Integer i : children) {
      Tree t = new Tree();
      t.value = i;
      childrenTree.add(t);
    }
    return childrenTree;
  }

  //Map<Integer, List<List<Integer>>> pathMap
  void getTreePath(Tree tree, List<Integer> path, Map<Integer, List<List<Integer>>> pathMap) {
    //满足结束条件
    List<Tree> childrenList = tree.children;
    if (childrenList == null || childrenList.isEmpty()) {
      int pathLong = path.size();
      List<List<Integer>> pathGroups = pathMap.get(pathLong);
      if (pathGroups == null) {
        pathGroups = new ArrayList<>();
        pathGroups.add(path);
        pathMap.put(pathLong, pathGroups);
      } else {
        pathGroups.add(path);
      }
      return;
    }

    for (Tree t : childrenList) {
      List<Integer> cPath = new ArrayList<>(path);
      cPath.add(t.value);
      getTreePath(t, cPath, pathMap);
    }

  }

  List<List<Integer>> searchForNode(int parent, List<List<Integer>> pairs) {
    List<List<Integer>> findPairs = pairs.stream().peek(x -> {
      Integer secondValue = x.get(1);
      if (secondValue == parent) {
        x.remove(1);
        x.add(0, secondValue);
      }
    }).filter(x -> x.get(0) == parent).collect(Collectors.toList());
    return findPairs;
  }

  @Deprecated
  Tree searchTreeForNode(int value, Tree tree) {
    if (tree.value == value) {
      return tree;
    } else {
      List<Tree> children = tree.children;
      if (children != null) {
        for (Tree t : children) {
          searchTreeForNode(value, t);
        }
      }
    }
    return null;
  }

  int getOddLinkPaths(Map<Integer, List<List<Integer>>> pathGroups) {
    return pathGroups.entrySet().stream().filter(x -> x.getKey() % 2 == 1)
        .mapToInt(x -> x.getValue().size()).sum();
  }

  public static void main(String[] args) {
    Solution s = new Solution();
    int[] a = new int[]{0, 4, 4, 2, 7, 6, 3};
    int[] b = new int[]{3, 5, 1, 3, 4, 3, 4};
//    int[] a = new int[]{0};
//    int[] b = new int[]{3};
    System.out.println(s.solution(a, b));
  }
}

class Tree {

  Integer value;

  List<Tree> children;

}
