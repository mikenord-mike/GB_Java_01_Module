package ru.gb.mikenord.homework07;

public class Cat {

    static final String ANSI_RESET = "\u001B[0m";
    static final String ANSI_RED = "\u001B[31m";
    static final String ANSI_GREEN = "\u001B[32m";
    // default access modifier is deliberately selected

    private final String name;

    private boolean satiety;
    private final int appetite;

    public Cat(String name, int appetite) throws Exception {
        if (name.length() == 0 || appetite <= 0) {
            throw new Exception("Illegal parameters of the Cat");
        }
        this.name = name;
        this.appetite = appetite;
        this.satiety = false;
    }

    public void eat(Plate p) {
        if (!satiety) {
            satiety = p.decreaseFood(appetite);
        }
    }

    public boolean isSatiety() {
        return satiety;
    }

    @Override
    public String toString() {
        return "Кот " + name + " с аппетитом " + appetite +
                " сейчас " + (satiety ?
                ANSI_GREEN + "сыт" + ANSI_RESET :
                ANSI_RED + "голоден" + ANSI_RESET);
    }
}
