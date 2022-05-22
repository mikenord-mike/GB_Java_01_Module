package ru.gb.mikenord.homework07;

public class Plate {

    private int food;
    private final String plateName;

    public Plate(String plateName, int food) {
        this.plateName = plateName;
        this.food = food;
    }

    public boolean decreaseFood(int n) {
        if (food >= n && food > 0) {
            food -= n;
            return true;
        }
        return false;
    }

    public void putFood(int n) {
        food += n;
        System.out.println("В емкость " + plateName + " добавили " + n + " еды");
    }

    @Override
    public String toString() {
        return "Емкость " + plateName + " содержит " + food + " еды";
    }

}
