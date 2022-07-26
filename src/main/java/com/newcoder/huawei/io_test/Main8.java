package com.newcoder.huawei.io_test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main8 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        List<String> list = new ArrayList<>();
        List<String> list1;
        for (int i = 0; i < n; i++) {
            String c = sc.next();
            list.add(c);
        }
        list1 = list.stream().sorted().collect(Collectors.toList());
        System.out.println(String.join(" ", list1));
    }

}
