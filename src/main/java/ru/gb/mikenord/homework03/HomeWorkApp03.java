package ru.gb.mikenord.homework03;

import java.util.Arrays;

public class HomeWorkApp03 {
    public static void main(String[] args) {

        // task 01
        int[] a = {0, 1, 0, 1, 1, 0, 0, 0, 1, 0, 1, 1, 0, 0, 1};
        for (int i = 0; i < a.length; i++) {
            a[i] = a[i] == 0 ? 1 : 0;
        }
        System.out.println("t01 " + Arrays.toString(a));

        // task 02
        int[] b = new int[100];
        for (int i = 0; i < b.length; i++) {
            b[i] = i + 1;
        }
        System.out.println("t02 " + Arrays.toString(b));

        // task 03
        int[] c = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        for (int i = 0; i < c.length; i++) {
            if (c[i] < 6) {
                c[i] *= 2;
            }
        }
        System.out.println("t03 " + Arrays.toString(c));

        // task 04
        int[][] d = new int[11][11];
        for (int i = 0; i < d.length; i++) {
            d[i][i] = 1;
            d[d.length - i - 1][i] = 1;
        }
        System.out.println("t04 " + Arrays.deepToString(d));

        // task 05 check
        System.out.println("t05 " + Arrays.toString(fillArray(90, 654)));

    }

    // task 05
    public static int[] fillArray(int len, int initialValue) {
        int[] result = new int[len];
        Arrays.fill(result, initialValue);
        return result;
    }
}
