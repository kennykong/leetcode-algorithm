package com.newcoder.huawei.io_test;

import java.math.BigDecimal;
import java.util.Scanner;

public class Main11 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String a =sc.next();
        BigDecimal a1 = new BigDecimal(a);
        String b = sc.next();
        BigDecimal b1 = new BigDecimal(b);
        BigDecimal c = a1.add(b1);
        System.out.println(c);
    }

}
