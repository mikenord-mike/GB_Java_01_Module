package ru.gb.mikenord.homework03;

import java.util.Arrays;

public class HomeWorkApp03 {
    public static void main(String[] args) {

        // task 01
        int[] a = {0, 1, 0, 1, 1, 0, 0, 0, 1, 0, 1, 1, 0, 0, 1};
        for (int i = 0; i < a.length; i++) {
            a[i] = a[i] == 0 ? 1 : 0;
        }
        System.out.println(Arrays.toString(a));

        // task 02
        int[] b = new int[100];
        for (int i = 0; i < b.length; i++) {
            b[i] = i + 1;
        }
        System.out.println(Arrays.toString(b));
    }

}
