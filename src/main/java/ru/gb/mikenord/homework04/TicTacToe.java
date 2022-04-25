package ru.gb.mikenord.homework04;

public class TicTacToe {
    public static char[][] map;
    public static final int SIZE = 5;
    public static final int DOTS_TO_WIN = 4;
    public static final char DOT_EMPTY = '*';
    public static final char DOT_X = 'X';
    public static final char DOT_O = 'O';
    public static String winX_Sequence = "";
    public static String winO_Sequence = "";

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_PURPLE = "\u001B[35m";
    private static final String ANSI_CYAN = "\u001B[36m";
    private static final String ANSI_WHITE = "\u001B[37m";

    public static void main(String[] args) {
        initMap();
        setTestMap();
        printMap();
        System.out.println(checkMap());
    }

    public static void initMap() {
        map = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
                winX_Sequence += DOT_X;
                winO_Sequence += DOT_O;
            }
        }

    }

    public static void setTestMap() {
        map[0][0] = DOT_X;
        map[0][1] = DOT_X;
        map[1][0] = DOT_X;
        map[1][1] = DOT_X;
        map[1][3] = DOT_X;
        map[2][1] = DOT_X;
        map[2][2] = DOT_X;
        map[3][1] = DOT_X;
        //map[4][1] = DOT_X;

        map[0][2] = DOT_O;
        map[0][3] = DOT_O;
        map[0][4] = DOT_O;
        map[1][2] = DOT_O;
        map[2][0] = DOT_O;
        map[2][3] = DOT_O;
        map[3][0] = DOT_O;
        map[3][3] = DOT_O;
        map[4][3] = DOT_O;
    }

    public static int checkMap() {
        boolean win = false;
        boolean fullMap = true;
        for (int i = 0; i < SIZE; i++) {
            String currentView = checkVisibleFromXY(i, 0);
            currentView += checkVisibleFromXY(0, i);
            currentView += checkVisibleFromXY(i, SIZE - 1);
            win |= currentView.contains(winX_Sequence);
            win |= currentView.contains(winO_Sequence);
            fullMap &= currentView.contains(String.valueOf(DOT_EMPTY));
        }
        return win ? 1 : fullMap ? 2 : 0;
    }

    private static String checkVisibleFromXY(int x, int y) {

        return "";
    }


    public static void printMap() {
        String line = "    +";
        for (int i = 0; i < SIZE; i++) {
            line += "---+";
        }
        System.out.print("      ");
        for (int i = 1; i <= SIZE; i++) {
            System.out.print(i + "   ");
        }
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            System.out.println(line);
            System.out.print(" " + (i + 1) + "  |");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(getColorCode(map[i][j]) + " " + map[i][j] + ANSI_RESET + " |");
            }
            System.out.println();
        }
        System.out.println(line);
    }

    private static String getColorCode(char i) {
        switch (i) {
            case DOT_EMPTY:
                return ANSI_PURPLE;
            case DOT_X:
                return ANSI_CYAN;
            case DOT_O:
                return ANSI_YELLOW;
        }
        return null;
    }
}
