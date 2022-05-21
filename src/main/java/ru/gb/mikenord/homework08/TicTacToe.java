package ru.gb.mikenord.homework08;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToe {
    static char[][] map;
    static int SIZE;
    static int DOTS_TO_WIN;
    static final char DOT_EMPTY = '*';
    static final char DOT_CHECKED = '+';
    static final char DOT_X = 'X';
    static final char DOT_O = 'O';
    static String winX_Sequence = "";
    static String winO_Sequence = "";

    static JFrame frame;
    static JPanel mailLabelPanel;
    static JPanel startBtnAndEndLabelPanel;
    static JLabel mainLabel;
    static JPanel gameBoardPanel;
    static JButton[][] boardBtn;
    static int screenV;
    static int screenH;
    static final int WINDOW_INIT_WIDTH = 500;
    static final int WINDOW_INIT_HEIGHT = 500;
    static final int WINDOW_BOARD_WIDTH = 375;
    static final int WINDOW_BOARD_HEIGHT = 500;
    static final Font FONT_SIZE_15 = new Font("TimesRoman", Font.PLAIN, 15);
    static final Font FONT_SIZE_20 = new Font("TimesRoman", Font.PLAIN, 20);
    static final Font FONT_SIZE_20_BOLD = new Font("TimesRoman", Font.BOLD, 20);
    static final Font FONT_SIZE_25 = new Font("TimesRoman", Font.PLAIN, 25);
    static final Font FONT_SIZE_35 = new Font("TimesRoman", Font.PLAIN, 35);
    static final Font FONT_END_LABEL = new Font("TimesRoman", Font.BOLD, 35);
    static Font FONT_BOARD_BUTTON;
    static int xTurn, yTurn;
    static boolean turn_X = true; //    true - if human turn

    public static void main(String[] args) {
        startGameGUI();
    }

    static void startGameGUI() {

        Dimension sSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenV = sSize.height;
        screenH = sSize.width;

        // Creating a main frame
        frame = new JFrame("Крестики-нолики");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setBounds(screenH / 2 - WINDOW_INIT_WIDTH / 2, screenV / 2 - WINDOW_INIT_HEIGHT / 2,
                WINDOW_INIT_WIDTH, WINDOW_INIT_HEIGHT);

        // Creating a top panel and top label

        mailLabelPanel = new JPanel();
        mailLabelPanel.setLayout(new GridLayout(2, 1));
        mainLabel = new JLabel("Выберите размер поля и длину выигрышной последовательности:",
                SwingConstants.CENTER);
        mainLabel.setFont(FONT_SIZE_15);
        mailLabelPanel.add(mainLabel);
        mailLabelPanel.add(new JLabel(" "));

        //  Creating a start panel
        JPanel startPanel = new JPanel();
        startPanel.setLayout(new GridLayout(1, 2, 60, 50));

        // Creating a panel for selecting the field size
        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(new GridLayout(6, 1, 20, 20));
        JLabel fieldLabel = new JLabel("Размер поля", SwingConstants.CENTER);
        fieldLabel.setFont(FONT_SIZE_20_BOLD);
        fieldPanel.add(fieldLabel);

        //  Creating a group of toggle buttons to select the field size
        JToggleButton[] fieldBtn = new JToggleButton[5];
        ButtonGroup fieldGroup = new ButtonGroup();
        for (int i = 0; i < 5; i++) {
            fieldBtn[i] = new JToggleButton((i + 3) + " x " + (i + 3));
            fieldBtn[i].setName(String.valueOf(i));
            fieldBtn[i].setFont(FONT_SIZE_35);
            fieldGroup.add(fieldBtn[i]);
            fieldPanel.add(fieldBtn[i]);
        }
        startPanel.add(fieldPanel);

        //  Creating a panel to select the length of the winning sequence
        JPanel winPanel = new JPanel();
        winPanel.setLayout(new GridLayout(6, 1, 20, 20));
        JLabel winLabel = new JLabel("Победа при :", SwingConstants.CENTER);
        winLabel.setFont(FONT_SIZE_20_BOLD);
        winPanel.add(winLabel);

        //  Creating a group of toggle buttons to select the winning sequence
        JToggleButton[] winBtn = new JToggleButton[5];
        ButtonGroup winGroup = new ButtonGroup();
        StringBuilder winSeq = new StringBuilder("XXX");
        for (int i = 0; i < 5; i++) {
            winBtn[i] = new JToggleButton(winSeq + "  (" + (i + 3) + ")");
            winBtn[i].setName(String.valueOf(i));
            winBtn[i].setFont(FONT_SIZE_25);
            winBtn[i].setAlignmentX(Component.RIGHT_ALIGNMENT);
            winGroup.add(winBtn[i]);
            winPanel.add(winBtn[i]);
            winSeq.append("X");
        }
        startPanel.add(winPanel);

        //  Listener for field size selection toggle button events
        ActionListener fieldBtnListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JToggleButton btn = (JToggleButton) e.getSource();
                int fieldIndex = Integer.parseInt(btn.getName());
                for (int i = 0; i < 5; i++) {
                    winBtn[i].setVisible(i <= fieldIndex);
                }
                SIZE = fieldIndex + 3;
                winBtn[fieldIndex].doClick();
            }
        };
        //  Listener for winning sequence selection toggle button events
        ActionListener winBtnListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JToggleButton btn = (JToggleButton) e.getSource();
                DOTS_TO_WIN = Integer.parseInt(btn.getName()) + 3;
            }
        };

        //  Adding a listener to the field size and sequence selection toggle buttons
        for (int i = 0; i < 5; i++) {
            winBtn[i].addActionListener(winBtnListener);
            fieldBtn[i].addActionListener(fieldBtnListener);
        }

        //  Start button and the panel for it
        startBtnAndEndLabelPanel = new JPanel();
        startBtnAndEndLabelPanel.setLayout(new GridLayout(2, 1));
        startBtnAndEndLabelPanel.add(new JLabel(" "));

        JButton startGameBtn = new JButton("Старт игры");
        startGameBtn.setFont(FONT_SIZE_25);
        startBtnAndEndLabelPanel.add(startGameBtn);

        //  Listener for start button
        //      - disabling the start panel
        //      - creating a game panel and an array of buttons
        //      - replacing the start button with a label for the end of the game
        //      - reshaping the window for the game process
        ActionListener startBtnListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                initMap();
                frame.remove(startPanel);
                frame.remove(startGameBtn);
                //  Метка для окончания игры
                JLabel endLabel = new JLabel("", SwingConstants.CENTER);
                endLabel.setFont(FONT_END_LABEL);

                gameBoardPanel = new JPanel();
                gameBoardPanel.setLayout(new GridLayout(SIZE, SIZE, 10, 10));
                boardBtn = new JButton[SIZE][SIZE];
                //  Setting the font size for buttons depending on the field size
                int tmpFontSize = 0;
                switch (SIZE) {
                    case 3:
                        tmpFontSize = 95;
                        break;
                    case 4:
                        tmpFontSize = 58;
                        break;
                    case 5:
                        tmpFontSize = 38;
                        break;
                    case 6:
                        tmpFontSize = 21;
                        break;
                    case 7:
                        tmpFontSize = 10;
                }
                FONT_BOARD_BUTTON = new Font("TimesRoman", Font.BOLD, tmpFontSize);

                for (int i = 0; i < SIZE; i++) {
                    for (int j = 0; j < SIZE; j++) {
                        boardBtn[i][j] = new JButton("");
                        boardBtn[i][j].setFont(FONT_BOARD_BUTTON);
                        boardBtn[i][j].setFocusPainted(false);
                        boardBtn[i][j].setName(i + "-" + j);
                        gameBoardPanel.add(boardBtn[i][j]);
                    }
                }

                //  Listener for an array of buttons
                //      - calculates the coordinates of the clicked button
                //      - starts calculating the turn
                ActionListener boardBtnListener = new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JButton tmpBtn = (JButton) e.getSource();
                        String tmpStr = tmpBtn.getName();
                        xTurn = Integer.parseInt(tmpStr.split("-")[0]);
                        yTurn = Integer.parseInt(tmpStr.split("-")[1]);
                        boardBtn[xTurn][yTurn].setText(turn_X ? "X" : "O");
                        boardBtn[xTurn][yTurn].setEnabled(false);
                        map[xTurn][yTurn] = turn_X ? DOT_X : DOT_O;

                        int checkTurn;
                        checkTurn = checkMap();
                        if (checkTurn == 1) {
                            endLabel.setForeground(turn_X ? Color.ORANGE : Color.RED);
                            endLabel.setText(turn_X ? "Вы победили!" : "Вы проиграли!");
                        } else if (checkTurn == 2) {
                            endLabel.setForeground(Color.DARK_GRAY);
                            endLabel.setText("-- ничья --");
                        }
                        turn_X = !turn_X;
                        if (!turn_X) {
                            aiTurn();
                        }
                    }
                };

                for (int i = 0; i < SIZE; i++) {
                    for (int j = 0; j < SIZE; j++) {
                        boardBtn[i][j].addActionListener(boardBtnListener);
                    }
                }

                //  Preparing to display the game panel
                frame.setVisible(false);

                mainLabel.setText("поле " + SIZE + " x " + SIZE + ", выигрыш при " + DOTS_TO_WIN + " подряд");
                mainLabel.setFont(FONT_SIZE_20);

                frame.add(BorderLayout.CENTER, gameBoardPanel);

                //  Replacing the start button with a label for messages about the end of the game
                startBtnAndEndLabelPanel.remove(startGameBtn);
                startBtnAndEndLabelPanel.add(endLabel);

                frame.setBounds(screenH / 2 - WINDOW_BOARD_WIDTH / 2, screenV / 2 - WINDOW_BOARD_HEIGHT / 2,
                        WINDOW_BOARD_WIDTH, WINDOW_BOARD_HEIGHT);
                frame.setVisible(true);
            }
        };

        startGameBtn.addActionListener(startBtnListener);

        //  Preparing to display the start panel
        frame.add(BorderLayout.NORTH, mailLabelPanel);
        frame.add(BorderLayout.CENTER, startPanel);
        frame.add(BorderLayout.SOUTH, startBtnAndEndLabelPanel);

        fieldBtn[0].doClick();
        frame.setVisible(true);
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
        boardBtn[x][y].doClick();
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
}