package ru.gb.mikenord.homework02;

public class HomeWorkApp02 {

    public static void main(String[] args) {
        System.out.println(sumCheck(5, 11));
        signNumberPrint(-4);
        System.out.println(negativeNumberCheck(0));
        printSeveralTimes("GB Java Test", 10);
    }

    public static boolean sumCheck(int a, int b) {
        int sum = a + b;
        return sum >= 10 && sum <= 20;
    }

    public static void signNumberPrint(int a) {
        if (a < 0) {
            System.out.println("The number is negative");
        } else {
            System.out.println("The number is positive");
        }
    }

    public static boolean negativeNumberCheck(int a) {
        return a < 0;
    }

    public static void printSeveralTimes(String s, int n) {
        for (int i = 0; i < n; i++) {
            System.out.println(s);
        }
    }
}
