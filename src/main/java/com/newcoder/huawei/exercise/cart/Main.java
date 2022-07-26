package com.newcoder.huawei.exercise.cart;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //读取参数
        //第一行， N （ N<32000 ）表示总钱数，m （m <60 ）为可购买的物品的个数。
        //从第 2 行到第 m+1 行，第 j 行给出了编号为 j-1 的物品的基本数据，
        // 每行有 3 个非负整数 v p q
        //
        //（其中 v 表示该物品的价格（ v<10000 ），
        // p 表示该物品的重要度（ 1 ~ 5 ），
        // q 表示该物品是主件还是附件。
        // 如果 q=0 ，表示该物品为主件，如果 q>0 ，表示该物品为附件，
        // q 是所属主件的编号）
        int nTotal = sc.nextInt();
        int m = sc.nextInt();

        List<Good> goods = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            int id = i + 1;
            int v = sc.nextInt();
            int p = sc.nextInt();
            int q = sc.nextInt();
            Good g = new Good(id, v, p, q);
            goods.add(g);
        }

        //添加附件到主件上
        for (Good g : goods) {
            if (!g.isMain()) {
                int mainId = g.getQ();
                addToMain(goods.get(mainId - 1), g);
            }
        }

        //创建dp数组，1维为物品个数，2维为总钱数
        int[][] dp = new int[m + 1][nTotal + 1];
        //遍历2维数组，剪枝
        for (int i = 1; i <= m; i++) {
            for (int j = 0; j <= nTotal; j++) {
                System.out.print("i=" + i + ",j=" + j);
                //5种情况，
                //1.不买任何物品，延续上一轮结果
                dp[i][j] = dp[i - 1][j];
                //2.买主件，获得这一轮循环的物品，这里goods的i-1和dp的i-1表示不同意义
                //i=1时，goods取第一个，dp表示0个物品
                Good g = goods.get(i - 1);
                if (!g.isMain()) {
                    System.out.print(",dp=" + dp[i][j] + ". ");
                    continue;
                }
                //剩余钱数大于0
                int remain = j - g.getV();
                if (remain >= 0) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][remain] + g.getMy());
                }
                //3.买主件，附件一
                Good sub1 = g.getG1();
                if (sub1 != null) {
                    int remain1 = remain - sub1.getV();
                    if (remain1 >= 0) {
                        dp[i][j] = Math.max(dp[i][j], dp[i - 1][remain1] + g.getMy() + sub1.getMy());
                    }
                }
                //4.买主件，附件二
                Good sub2 = g.getG2();
                if (sub2 != null) {
                    int remain2 = remain - sub2.getV();
                    if (remain2 >= 0) {
                        dp[i][j] = Math.max(dp[i][j], dp[i - 1][remain2] + g.getMy() + sub2.getMy());
                    }
                }
                //5.买主件，附件一，附件二
                if (sub1 != null && sub2 != null) {
                    int remain3 = remain - sub1.getV() - sub2.getV();
                    if (remain3 >= 0) {
                        dp[i][j] = Math.max(dp[i][j], dp[i - 1][remain3] + g.getMy() + sub1.getMy() + sub2.getMy());
                    }
                }
                System.out.print(",dp=" + dp[i][j] + ". ");
            }
            System.out.println();
        }
        System.out.println(dp[m][nTotal]);
    }

    static void addToMain(Good gMain, Good gSub) {
        if (gMain.getG1() == null) {
            gMain.setG1(gSub);
        } else {
            gMain.setG2(gSub);
        }
    }
}

class Good {
    public Good(int id, int v, int p, int q) {
        this.id = id;
        this.v = v;
        this.p = p;
        this.q = q;
        this.my = v * p;
        this.main = q == 0;
    }

    private final int id;
    //价格
    private final int v;
    //重要度
    private final int p;
    //所属主件的编号
    private final int q;
    //满意度
    private final int my;

    private final boolean main;

    //附件1
    private Good g1;

    //附件2
    private Good g2;

    public Good getG1() {
        return g1;
    }

    public void setG1(Good g1) {
        this.g1 = g1;
    }

    public Good getG2() {
        return g2;
    }

    public void setG2(Good g2) {
        this.g2 = g2;
    }

    public boolean isMain() {
        return main;
    }


    public int getId() {
        return id;
    }

    public int getV() {
        return v;
    }

    public int getP() {
        return p;
    }

    public int getQ() {
        return q;
    }

    public int getMy() {
        return my;
    }


}

