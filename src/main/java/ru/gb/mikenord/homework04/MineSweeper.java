package ru.gb.mikenord.homework04;

import java.util.Random;
import java.util.Scanner;

public class MineSweeper {
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_BLACK = "\u001B[30m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_BRIGHT_RED = "\u001B[91m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_PURPLE = "\u001B[35m";
    private static final String ANSI_CYAN = "\u001B[36m";
    private static final String ANSI_WHITE = "\u001B[37m";

    private static final int WIDTH = 10;
    private static final int HEIGHT = 10;
    private static final int MINE_COUNT = 25;

    private static final int MINE = 1000;
    private static final int EMPTY = 0;
    private static final int CELL_OPEN = 1;
    private static final int CELL_CLOSE = 0;
    private static final int CELL_FLAG = -1;

    public static void main(String[] args) {
        boolean isWin = play();
        if (isWin) {
            System.out.println("Поздравляю!\nВы выиграли!");
        } else {
            System.out.println("БАБАХ!!!\nВы проиграли!");
        }
    }

    public static boolean play() {
        int[][] board = generateBoard();
        int[][] moves = new int[HEIGHT][WIDTH];
        boolean isPassMove;
        boolean win;
        do {
            isPassMove = move(board, moves);
            win = isWin(moves);
        } while (isPassMove && !win);

        //  print of the winning field
        if (isPassMove) {
            printBoard(board, moves);
        }
        return isPassMove;
    }

    private static boolean isWin(int[][] moves) {
        int openCell = 0;
        for (int[] lines : moves) {
            for (int cell : lines) {
                if (cell == CELL_OPEN) {
                    openCell++;
                }
            }
        }
        return openCell == HEIGHT * WIDTH - MINE_COUNT;
    }

    private static boolean move(int[][] board, int[][] moves) {
        Scanner scanner = new Scanner(System.in);
        printBoard(board, moves);
        while (true) {
            System.out.print("Ваш ход (строка, столбец, флаг, например А1*): ");
            String s = scanner.nextLine().toUpperCase(); // приводим к верхнему регистру
            int row = s.charAt(0) - 'A';
            int line = s.charAt(1) - '0';
            if (row >= 0 && row < HEIGHT && line >= 0 && line < WIDTH) {
                if (s.endsWith("*")) {
                    moves[line][row] = CELL_FLAG;
                    return true;
                }
                if (isMine(board[line][row])) {
                    System.out.println("Мина " + (char) ('A' + row) + line + " взорвалась!!!");
                    return false;
                }
                //  when re-opening an open non-empty cell - imitation of a double click in the original version...
                //  all closed cells around open, except those marked with a flag
                if (moves[line][row] == CELL_OPEN) {
                    for (int i = line - 1; i <= line + 1; i++) {
                        for (int j = row - 1; j <= row + 1; j++) {
                            if (i >= 0 && i < HEIGHT && j >= 0 && j < WIDTH && moves[i][j] != CELL_FLAG) {
                                moves[i][j] = CELL_OPEN;
                                if (isMine(board[i][j])) {
                                    System.out.println("Мина " + (char) ('A' + j) + i + " взорвалась!!!");
                                    return false;
                                }
                                if (board[i][j] == EMPTY) {
                                    openEmptyFields(board, moves, i, j);
                                }
                            }
                        }
                    }
                } else {
                    moves[line][row] = CELL_OPEN;
                    if (board[line][row] == EMPTY) {
                        openEmptyFields(board, moves, line, row);
                    }
                }
                return true;
            }
            System.out.println("Неправильный ввод");
        }
    }

    //  clusters of empty cells are checked and opened by recursion
    private static void openEmptyFields(int[][] board, int[][] moves, int x, int y) {
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (i >= 0 && i < HEIGHT && j >= 0 && j < WIDTH && moves[i][j] != CELL_OPEN) {
                    moves[i][j] = CELL_OPEN;
                    if (board[i][j] == EMPTY) {
                        openEmptyFields(board, moves, i, j);
                    }
                }
            }
        }
    }

    private static void printBoard(int[][] board, int[][] moves) {
        System.out.print("    ");
        for (char i = 'A'; i < 'A' + WIDTH; i++) {
            System.out.print(" " + i);
        }
        System.out.println();
        for (int i = 0; i < HEIGHT; i++) {
            System.out.printf("%3d ", i);
            for (int j = 0; j < WIDTH; j++) {
                if (moves[i][j] == CELL_CLOSE) {
                    System.out.print("[]");
                    continue;
                }
                if (moves[i][j] == CELL_FLAG) {
                    System.out.print(ANSI_BRIGHT_RED + " P" + ANSI_RESET);
                    continue;
                }
                String cellColor = getColorCode(board[i][j]);
                System.out.print(cellColor);
                if (board[i][j] == EMPTY) {
                    System.out.print(" .");
                } else if (isMine(board[i][j])) {
                    System.out.print(" *");
                } else {
                    System.out.printf("%2d", board[i][j]);
                }
                System.out.print(ANSI_RESET);
            }
            System.out.println();
        }
    }

    private static String getColorCode(final int i) {
        switch (i) {
            case EMPTY:
                return ANSI_WHITE;
            case MINE:
                return ANSI_PURPLE;
            case 1:
                return ANSI_BLUE;
            case 2:
                return ANSI_GREEN;
            case 3:
                return ANSI_RED;
            case 4:
                return ANSI_CYAN;
            case 5:
                return ANSI_YELLOW;
        }
        return null;
    }

    //  in this case, processing of all cells is not required to count the number of visible mines:
    private static int[][] generateBoard() {
        int[][] board = new int[HEIGHT][WIDTH];
        int mines = MINE_COUNT;
        final Random random = new Random();
        while (mines > 0) {
            int x = random.nextInt(HEIGHT), y = random.nextInt(WIDTH);
            if (isMine(board[x][y])) {
                continue;
            }
            board[x][y] = MINE;
            for (int i = x - 1; i <= x + 1; i++) {
                for (int j = y - 1; j <= y + 1; j++) {
                    if (i >= 0 && i < HEIGHT && j >= 0 && j < WIDTH && !isMine(board[i][j])) {
                        board[i][j]++;
                    }
                }
            }
            mines--;
        }
        return board;
    }

    private static boolean isMine(int i) {
        return i == MINE;
    }
}
