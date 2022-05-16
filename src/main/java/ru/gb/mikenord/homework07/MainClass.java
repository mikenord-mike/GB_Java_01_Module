package ru.gb.mikenord.homework07;

public class MainClass {
    public static void main(String[] args) throws Exception {
        Cat cat01 = new Cat("Filya", 10);
        Plate plate = new Plate("Синяя миска", 40);
        System.out.println(plate);
        System.out.println(cat01);
        cat01.eat(plate);
        System.out.println(cat01);
        System.out.println(plate + "\n");

        plate.putFood(75);
        System.out.println(plate);

        Cat[] cats = {
                new Cat("Filya", 10),
                new Cat("Barsik", 25),
                new Cat("Murzik", 12),
                new Cat("Kuzya", 9),
                new Cat("Ankhel", 13),
                new Cat("Pushok", 18),
                new Cat("Tchernysh", 11),
                new Cat("ANuPshelVonOtsyudaTchertLokhmatyj", 8),
        };

        System.out.println("\n  Коты сейчас будут есть");
        for (Cat cat : cats) {
            cat.eat(plate);
        }
        System.out.println("  Коты поели\n");

        for (Cat cat : cats) {
            System.out.println(cat);
        }

        System.out.println("\n" + plate);

            // check cat parameters
//        Cat cat02 = new Cat("Juju", -5);

    }
}
