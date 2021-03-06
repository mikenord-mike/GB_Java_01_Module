package ru.gb.mikenord.homework04;

import java.util.Scanner;

public class TicTacToe {
    public static char[][] map;
    public static final int SIZE = 5;
    public static final int DOTS_TO_WIN = 4;
    public static final char DOT_EMPTY = '*';
    public static final char DOT_CHECKED = '+';
    public static final char DOT_X = 'X';
    public static final char DOT_O = 'O';
    public static String winX_Sequence = "";
    public static String winO_Sequence = "";

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_CYAN = "\u001B[36m";
    private static final String ANSI_WHITE = "\u001B[37m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_BRIGHT_RED = "\u001B[91m";
    private static final String ANSI_BRIGHT_YELLOW = "\u001B[93m";
    private static final String ANSI_BRIGHT_CYAN = "\u001B[96m";

    public static void main(String[] args) {
        initMap();
//        setTestMap();
        printMap();
        while (true) {
            int checkTurn;
            humanTurn();
            printMap();
            checkTurn = checkMap();
            if (checkTurn == 1) {
                System.out.println(ANSI_BRIGHT_CYAN + "Победил человек" + ANSI_RESET);
                break;
            } else if (checkTurn == 2) {
                System.out.println(ANSI_BLUE + "Ничья" + ANSI_RESET);
                break;
            }
            aiTurn();
            printMap();
            checkTurn = checkMap();
            if (checkTurn == 1) {
                System.out.println(ANSI_BRIGHT_YELLOW + "Победил Квазиискуственный Интеллект" + ANSI_RESET);
                break;
            } else if (checkTurn == 2) {
                System.out.println(ANSI_BLUE + "Ничья" + ANSI_RESET);
                break;
            }
        }
        System.out.println("Игра закончена");
    }

    public static void initMap() {
        map = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
            }
            // initialization of winning sequences
            if (i < DOTS_TO_WIN) {
                winX_Sequence += DOT_X;
                winO_Sequence += DOT_O;
            }
        }
    }

    private static void humanTurn() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Введите координаты X в формате А1): ");
            String s = scanner.nextLine().toUpperCase();
            int y = s.charAt(0) - 'A';
            int x = s.charAt(1) - '1';
            if (x >= 0 && x < SIZE && y >= 0 && y < SIZE) {
                if (map[x][y] == DOT_EMPTY) {
                    map[x][y] = DOT_X;
                    break;
                } else if (map[x][y] == DOT_X) {
                    System.out.println(ANSI_BRIGHT_RED + "Нельзя ходить повторно в одну и ту же клетку" + ANSI_RESET);
                } else {
                    System.out.println(ANSI_BRIGHT_RED + "Нальзя ходить в ячейку, занятую соперником" + ANSI_RESET);
                }
            } else {
                System.out.println(ANSI_BRIGHT_RED + "Неправильный ввод, проверьте координаты" + ANSI_RESET);
            }
        }
    }

    public static void aiTurn() {
        int x = 0, y = 0, maxWeight = 0;

        //  empty cells are weighted according to the number of visible crosses and the maximum sequences
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY) {
                    /*  array for saving values of current empty cell:
                        ai[0] - total number of visible crosses;
                        ai[1] - visible crosses in the row;
                        ai[2] - visible crosses in the row;
                        ai[3] - visible crosses in the forward diagonal (optional);
                        ai[4] - visible crosses in the reverse diagonal (optional);
                        ai[5] - maximum sequence of crosses among visible;
                        ai[6] - sum of a minimum distances to the nearest crosses;
                        ai[7] - maximum sequence of zeroes among visible;
                        ai[8] - maximum number of visible zeros in any direction;
                    */
                    int[] ai = new int[9];
                    ai[6] = SIZE - 1;
                    String currentView = checkVisibleFromXY(i, j, true);

                    //  number of visible crosses
                    ai[0] = countInString(currentView, DOT_X);

                    //   number of visible crosses in each direction, maximum zeros in any directions
                    //   and count minimal distance to cross
                    String[] views = currentView.split(" ");

                    ai[1] = countInString(views[0], DOT_X);
                    if (ai[1] > 0) {
                        int tmpDist = minDistanceToX(views[0]);
                        if (ai[6] > tmpDist && tmpDist > 0) {
                            ai[6] = tmpDist;
                        }
                    }

                    ai[2] = countInString(views[1], DOT_X);
                    if (ai[2] > 0) {
                        int tmpDist = minDistanceToX(views[1]);
                        if (ai[6] > tmpDist && tmpDist > 0) {
                            ai[6] = tmpDist;
                        }
                    }

                    ai[8] = countInString(views[0], DOT_O);
                    int tmp_O_Max = countInString(views[1], DOT_O);
                    if (tmp_O_Max > ai[8]) {
                        ai[8] = tmp_O_Max;
                    }

                    if (views.length > 2) {
                        ai[3] = countInString(views[2], DOT_X);
                        if (ai[3] > 0) {
                            int tmpDist = minDistanceToX(views[2]);
                            if (ai[6] > tmpDist && tmpDist > 0) {
                                ai[6] = tmpDist;
                            }
                        }
                        tmp_O_Max = countInString(views[2], DOT_O);
                        if (tmp_O_Max > ai[8]) {
                            ai[8] = tmp_O_Max;
                        }
                    }
                    if (views.length > 3) {
                        ai[4] = countInString(views[3], DOT_X);
                        if (ai[4] > 0) {
                            int tmpDist = minDistanceToX(views[3]);
                            if (ai[6] > tmpDist && tmpDist > 0) {
                                ai[6] = tmpDist;
                            }
                        }
                        tmp_O_Max = countInString(views[3], DOT_O);
                        if (tmp_O_Max > ai[8]) {
                            ai[8] = tmp_O_Max;
                        }
                    }

                    //  maximum sequence of visible crosses and zeroes
                    for (int k = DOTS_TO_WIN - 1; k > 1; k--) {
                        String current_X_Sequence = winX_Sequence.substring(0, k);
                        if (ai[5] == 0 && currentView.contains(current_X_Sequence)) {
                            ai[5] = k;
                        }
                        String current_O_Sequence = winO_Sequence.substring(0, k);
                        if (ai[7] == 0 && currentView.contains(current_O_Sequence)) {
                            ai[7] = k;
                        }
                    }

                    //  weight distribution by parameters
                    int currentWeight = 0;
                    currentWeight += ai[0] * ai[0];                         // n*n for total number of crosses
                    currentWeight += ai[1] == ai[0] ? ai[1] * 2 : ai[1];    // 2:1 if all crosses in one direction
                    currentWeight += ai[2] == ai[0] ? ai[2] * 2 : ai[2];    // 2:1 if all crosses in one direction
                    currentWeight += ai[3] == ai[0] ? ai[3] * 2 : ai[3];    // 2:1 if all crosses in one direction
                    currentWeight += ai[4] == ai[0] ? ai[4] * 2 : ai[4];    // 2:1 if all crosses in one direction
                    currentWeight += views.length * views.length;           // + n*n if non-empty directions > 1
                    currentWeight += ai[5] * 2;                             // 2:1 for maximum sequence;

                    //  add weight for the minimum distance to the nearest crosses
                    currentWeight += (SIZE - ai[6]) * SIZE * SIZE;

                    // add weight n*n if all visible crosses in one sequence
                    if (ai[5] == ai[0]) {
                        currentWeight += ai[5] * ai[5];
                    }

                    int preWinner = DOTS_TO_WIN - 1;
                    /*  if one of the directions contains a "X" pre-win situation (DOTS_TO_WIN - 1),
                        then the priority of the cell is very high
                    */
                    if (ai[1] == preWinner || ai[2] == preWinner || ai[3] == preWinner || ai[4] == preWinner) {
                        currentWeight *= SIZE * SIZE * SIZE;
                    }

                    /*  but if one of the directions contains a "O" pre-win situation (DOTS_TO_WIN - 1),
                        then the priority of the cell is the highest
                    */
                    if (ai[7] == preWinner || ai[8] == preWinner) {
                        currentWeight = Integer.MAX_VALUE;
                    }

                    if (currentWeight > maxWeight) {
                        maxWeight = currentWeight;
                        x = i;
                        y = j;
                    }
                }
            }
        }
        map[x][y] = DOT_O;
        System.out.println("Компьютер походил в точку " + (char) ('A' + y) + (x + 1));
    }

    public static int countInString(String s, char item) {
        int result = 0;
        for (int k = 0; k < s.length(); k++) {
            if (s.charAt(k) == item) {
                result++;
            }
        }
        return result;
    }

    public static int minDistanceToX(String view) {
        if (!view.contains("" + DOT_X)) {
            return 0;
        }

        int checked = view.indexOf(DOT_CHECKED);
        int result = view.length();
        for (int i = 0; i < view.length(); i++) {
            if (view.charAt(i) == DOT_X) {
                int tmpDist = Math.abs(checked - i);
                if (result > tmpDist) {
                    result = tmpDist;
                }
            }
        }
        return result;
    }

    public static int checkMap() {
        boolean win = false;
        boolean fullMap = true;

        //  it is enough to check 3 sides only - n * 3 instead of n * n
        for (int i = 0; i < SIZE; i++) {

            // passing through the cells of the first column
            String currentView = checkVisibleFromXY(i, 0, false);

            // passing through the cells of the first row
            currentView += " " + checkVisibleFromXY(0, i, false);

            // passing through the cells of the last column
            currentView += " " + checkVisibleFromXY(i, SIZE - 1, false);

            win |= currentView.contains(winX_Sequence);
            win |= currentView.contains(winO_Sequence);
            fullMap &= countInString(currentView, DOT_EMPTY) == 0;
        }
        return win ? 1 : fullMap ? 2 : 0;
    }

    private static String checkVisibleFromXY(int x, int y, boolean isAiTurn) {
        // string is made up of all characters in visible directions separated by a space
        // if AI turn - DOT_CHECKED - mark of the checked cell (x, y)
        String result = "";

        //  row char set
        char[] row = map[x].clone();
        if (isAiTurn) {
            row[y] = DOT_CHECKED;
        }
        result += new String(row);

        //  col char set
        char[] col = new char[SIZE];
        for (int i = 0; i < SIZE; i++) {
            col[i] = map[i][y];
        }
        if (isAiTurn) {
            col[x] = DOT_CHECKED;
        }
        result += " " + new String(col);

        // forward diagonal char set
        int diagonalLength = SIZE - Math.abs(x - y);
        if (diagonalLength >= DOTS_TO_WIN) {
            char[] diagonal = new char[diagonalLength];
            int deltaX = x > y ? x - y : 0;
            int deltaY = x < y ? y - x : 0;
            for (int i = 0; i < diagonalLength; i++) {
                diagonal[i] = map[i + deltaX][i + deltaY];
                if (isAiTurn && (i + deltaX) == x && (i + deltaY) == y) {
                    diagonal[i] = DOT_CHECKED;
                }
            }
            result += " " + new String(diagonal);
        }

        //  reverse diagonal char set
        diagonalLength = SIZE - Math.abs(SIZE - (x + y) - 1);
        if (diagonalLength >= DOTS_TO_WIN) {
            char[] diagonal = new char[diagonalLength];
            int delta = x + y - (SIZE - 1);
            int deltaX = delta > 0 ? delta : 0;
            int deltaY = delta < 0 ? delta : 0;
            for (int i = 0; i < diagonalLength; i++) {
                int tmpX = i + deltaX;
                int tmpY = SIZE - 1 - i + deltaY;
                diagonal[i] = map[tmpX][tmpY];
                if (isAiTurn && tmpX == x && tmpY == y) {
                    diagonal[i] = DOT_CHECKED;
                }
            }
            result += " " + new String(diagonal);
        }

        return result;
    }

    public static void printMap() {
        String line = "    +";
        for (int i = 0; i < SIZE; i++) {
            line += "---+";
        }
        System.out.print("\n   ");
        for (char i = 'A'; i < 'A' + SIZE; i++) {
            System.out.print("   " + i);
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
                return ANSI_WHITE;
            case DOT_X:
                return ANSI_CYAN;
            case DOT_O:
                return ANSI_YELLOW;
        }
        return null;
    }
}
