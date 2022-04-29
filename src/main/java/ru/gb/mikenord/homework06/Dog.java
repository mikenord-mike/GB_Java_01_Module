package ru.gb.mikenord.homework06;

public class Dog extends Animal {
    public static int dogCount = 0;

    public Dog(String name) {
        super(name);
        dogCount++;
    }

    public static int getDogCount() {
        return dogCount;
    }

    @Override
    public void run(int length) {
        String result;
        if (length<=0) {
            result= " осталась на месте.";
        } else if (length > 500) {
            result= " пробежала 500 м.";
        } else {
            result=String.format(" пробежала %d м.", length);
        }
        System.out.println(this.getName() + result);
    }

    @Override
    public void swim(int length) {
        String result;
        if (length<=0) {
            result= " осталась на месте.";
        } else if (length > 10) {
            result= " проплыла 10 м.";
        } else {
            result=String.format(" проплыла %d м.", length);
        }
        System.out.println(this.getName() + result);
    }
}
