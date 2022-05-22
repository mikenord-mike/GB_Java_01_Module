package ru.gb.mikenord.homework02;

public class HomeWorkApp02 {

    public static void main(String[] args) {
            // task 01 test
        System.out.println(isSumInRange20To30(5, 11));
            // task 02 test
        printNumberSign(-4);
            // task 03 test
        System.out.println(isNegativeNumber(0));
            // task 04 test
        printStringSeveralTimes("GB Java Test", 3);
            // task 05 test
        System.out.println(isLeapYear(400));
    }

        // task 01
    public static boolean isSumInRange20To30(int a, int b) {
        int sum = a + b;
        return sum >= 10 && sum <= 20;
    }

        // task 02
    public static void printNumberSign(int a) {
        if (a < 0) {
            System.out.println("The number is negative");
        } else {
            System.out.println("The number is positive");
        }
    }

        // task 03
    public static boolean isNegativeNumber(int a) {
        return a < 0;
    }

        // task 04
    public static void printStringSeveralTimes(String s, int n) {
        for (int i = 0; i < n; i++) {
            System.out.println(s);
        }
    }

        // task 05
    public static boolean isLeapYear(int year) {
        if (year % 4 == 0) {
            return !(year % 100 == 0 && year % 400 !=0);
        }
        return false;
    }
}
