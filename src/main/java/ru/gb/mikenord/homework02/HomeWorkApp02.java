package ru.gb.mikenord.homework02;

public class HomeWorkApp02 {

    public static void main(String[] args) {
        System.out.println(sumCheck(5, 11));
    }

    public static boolean sumCheck(int a, int b) {
        int sum = a + b;
        return sum >= 10 && sum <= 20;
    }

}
