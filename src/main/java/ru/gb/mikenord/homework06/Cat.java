package ru.gb.mikenord.homework06;

public class Cat extends Animal {
    public static int catCount = 0;

    public Cat(String name) {
        super(name);
        catCount++;
    }

    public static int getCatCount() {
        return catCount;
    }

    @Override
    public void run(int length) {
        String result;
        if (length<=0) {
            result= " остался на месте.";
        } else if (length > 200) {
            result= " пробежал 200 м.";
        } else {
            result=String.format(" пробежал %d м.", length);
        }
        System.out.println(this.getName() + result);
    }

    @Override
    public void swim(int length) {
        System.out.println(this.getName() +" остался на месте, " + this.getName() + " не умеет плавать.");
    }
}
