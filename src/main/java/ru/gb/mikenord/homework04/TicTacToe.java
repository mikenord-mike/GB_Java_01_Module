package ru.gb.mikenord.homework04;

public class TicTacToe {
    public static char[][] map;
    public static final int SIZE = 5;
    public static final int DOTS_TO_WIN = 4;
    public static final char DOT_EMPTY = '*';
    public static final char DOT_X = 'X';
    public static final char DOT_O = 'O';

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
        System.out.println(isWin());
    }

    public static void initMap() {
        map = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
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

    public static boolean isWin() {
        boolean result = false;
        for (int i = 0; i < SIZE - DOTS_TO_WIN + 1; i++) {
            for (int j = 0; j < SIZE - DOTS_TO_WIN + 1; j++) {
                result |= isWin_DotsToWinLength(i, j);
            }
        }
        return result;
    }

    //  the shift was required due to the condition of task 3:
    //              DOTS_TO_WIN < SIZE
    public static boolean isWin_DotsToWinLength(int shiftX, int shiftY) {
        boolean diagLeft = map[shiftX][shiftY] != DOT_EMPTY;
        boolean diagRigth = map[shiftX][DOTS_TO_WIN - 1 + shiftY] != DOT_EMPTY;

        for (int i = 0; i < DOTS_TO_WIN; i++) {
            if (i > 0 && (diagLeft || diagRigth)) {
                diagLeft &= map[i + shiftX][i + shiftY] == map[i - 1 + shiftX][i - 1 + shiftY];
                diagRigth &= map[i + shiftX][DOTS_TO_WIN - 1 - i + shiftY] == map[i - 1 + shiftX][DOTS_TO_WIN - i + shiftY];
            }
            boolean col = map[i + shiftX][shiftY] != DOT_EMPTY;
            col &= map[i + shiftX][shiftY] == map[i + shiftX][DOTS_TO_WIN - 1 + shiftY];
            boolean row = map[shiftX][i + shiftY] != DOT_EMPTY;
            row &= map[shiftX][i + shiftY] == map[DOTS_TO_WIN - 1 + shiftX][i + shiftY];
            if (col || row) {
                for (int j = 1; j < DOTS_TO_WIN - 1; j++) {
                    col &= map[i + shiftX][j + shiftY] == map[i + shiftX][j - 1 + shiftY];
                    row &= map[j + shiftX][i + shiftY] == map[j - 1 + shiftX][i + shiftY];
                }
            }
            if (col || row) {
                return true;
            }
        }
        return diagLeft || diagRigth;
    }

    //  if it were always DOTS_TO_WIN = SIZE, then instead of "isWin() + isWin_DotsToWinLength(int shiftX, int shiftY)"
    //  it would be...
    //
    //    public static boolean isWin() {
    //        boolean diagLeft = map[0][0] != DOT_EMPTY;
    //        boolean diagRigth = map[0][SIZE - 1] != DOT_EMPTY;
    //
    //        for (int i = 0; i < SIZE; i++) {
    //            if (i > 0 && (diagLeft || diagRigth)) {
    //                diagLeft &= map[i][i] == map[i - 1][i - 1];
    //                diagRigth &= map[i][SIZE - 1 - i] == map[i - 1][SIZE - i];
    //            }
    //            boolean col = map[i][0] != DOT_EMPTY;
    //            col &= map[i][0] == map[i][SIZE - 1];
    //            boolean row = map[0][i] != DOT_EMPTY;
    //            row &= map[0][i] == map[SIZE - 1][i];
    //            if (col || row) {
    //                for (int j = 1; j < SIZE - 1; j++) {
    //                    col &= map[i][j] == map[i][j - 1];
    //                    row &= map[j][i] == map[j - 1][i];
    //                }
    //            }
    //            if (col || row) {
    //                return true;
    //            }
    //        }
    //        return diagLeft || diagRigth;
    //    }

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
