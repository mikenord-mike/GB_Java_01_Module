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

        // task 06
        // Not a very precise task condition - the array type is not specified. I solved it for integer type
        int[] e = {5, 6, 9, 1, 456, -79, 4, 3, 2, 7};
        int min = 0, max = 0;
        for (int i = 0; i < e.length; i++) {
            if (i == 0) {
                min = e[i];
                max = e[i];
            } else {
                min = min > e[i] ? e[i] : min;
                max = max < e[i] ? e[i] : max;
            }
        }
        System.out.printf("min = %d, max = %d\n", min, max);

        // task 07 check
        System.out.println(sumRightLeftArrayPartCheck(new int[]{-12, 1, 1, 2, -8}));
    }

    // task 05
    public static int[] fillArray(int len, int initialValue) {
        int[] result = new int[len];
        Arrays.fill(result, initialValue);
        return result;
    }

    // task 07
    public static boolean sumRightLeftArrayPartCheck(int[] f) {
        for (int i = 1; i < f.length; i++) {
            int sumLeft = 0, sumRight = 0;
            for (int j = 0; j < i; j++) {
                sumLeft += f[j];
            }
            for (int j = i; j < f.length; j++) {
                sumRight += f[j];
            }
            if (sumLeft == sumRight) {
                return true;
            }
        }
        return false;
    }
}
