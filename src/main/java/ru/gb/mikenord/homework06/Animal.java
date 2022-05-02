package ru.gb.mikenord.homework06;

public abstract class Animal {
    private static int animalCount = 0;
    private String name;

    public Animal(String name) {
        this.name = name;
        animalCount++;
    }

    public static int getAnimalCount() {
        return animalCount;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public abstract void run(int length);

    public abstract void swim(int length);
}
