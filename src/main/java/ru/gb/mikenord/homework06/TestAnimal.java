package ru.gb.mikenord.homework06;

public class TestAnimal {
    public static void main(String[] args) {
        Cat catKuzya = new Cat("Кузька");
        Dog dogDinka = new Dog("Динка");

        Cat cat02 = new Cat("Кот 02");
        Cat cat03 = new Cat("Кот 03");
        Cat cat04 = new Cat("Кот 04");
        Dog dog02 = new Dog("Собака 02");
        Dog dog03 = new Dog("Собака 03");
        Dog dog04 = new Dog("Собака 04");
        Dog dog05 = new Dog("Собака 05");
        Dog dog06 = new Dog("Собака 06");

        System.out.println("Всего животных - " + Animal.getAnimalCount() + ",");
        System.out.println("из них котов - " + Cat.getCatCount() + ",");
        System.out.println("при этом собак - " + Dog.getDogCount() + ".\n");

        System.out.println("Отправляем Кузьку бежать на -1 м. ");
        catKuzya.run(-1);
        System.out.println("Отправляем Кузьку бежать на 0 м. ");
        catKuzya.run(0);
        System.out.println("Отправляем Кузьку бежать на 50 м. ");
        catKuzya.run(50);
        System.out.println("Отправляем Кузьку бежать на 200 м. ");
        catKuzya.run(200);
        System.out.println("Отправляем Кузьку бежать на 300 м. ");
        catKuzya.run(300);
        System.out.println();
        System.out.println("Отправляем Кузьку плыть на 0 м. ");
        catKuzya.swim(0);
        System.out.println("Отправляем Кузьку плыть на 10 м. ");
        catKuzya.swim(10);
        System.out.println("Отправляем Кузьку плыть на 100 м. ");
        catKuzya.swim(100);
        System.out.println();

        System.out.println("Отправляем Динку бежать на -1 м. ");
        dogDinka.run(-1);
        System.out.println("Отправляем Динку бежать на 0 м. ");
        dogDinka.run(0);
        System.out.println("Отправляем Динку бежать на 50 м. ");
        dogDinka.run(50);
        System.out.println("Отправляем Динку бежать на 200 м. ");
        dogDinka.run(200);
        System.out.println("Отправляем Динку бежать на 500 м. ");
        dogDinka.run(500);
        System.out.println("Отправляем Динку бежать на 700 м. ");
        dogDinka.run(700);
        System.out.println();
        System.out.println("Отправляем Динку плыть на 0 м. ");
        dogDinka.swim(0);
        System.out.println("Отправляем Динку плыть на 5 м. ");
        dogDinka.swim(5);
        System.out.println("Отправляем Динку плыть на 10 м. ");
        dogDinka.swim(10);
        System.out.println("Отправляем Динку плыть на 100 м. ");
        dogDinka.swim(100);
    }
}
