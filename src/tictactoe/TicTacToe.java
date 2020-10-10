package tictactoe;
import java.util.Scanner;
public class TicTacToe {
    static Scanner scanner = new Scanner(System.in);
    static char[][] cells = new char[3][3];

    public static void fillArray(char[][] cells) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cells[i][j] = '_';
            }
        }
    }

    public static void outField() {
        System.out.println("--------- ");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(cells[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("--------- ");
    }

    public static boolean checkField() {
        int x = 0;
        int y = 0;
        int empty = 0;
        boolean isX = false;
        boolean isY = false;
        boolean isNormal = true;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (cells[i][j] == 'X') {
                    x++;
                }
                if (cells[i][j] == 'O') {
                    y++;
                }
                if (cells[i][j] == '_') {
                    empty++;
                }
                for (int k = -1; k < 2; k++) {
                    for (int l = -1; l < 2; l++) {
                        if (k * 2 + l != 0 && i + k  * 2>= 0 && j + l * 2>= 0 && i + k * 2 < 3 && j + l * 2 < 3) {
                            if (cells[i][j] == cells[i + k][j + l] && cells[i][j] == cells[i + k * 2][j + l * 2]) {
                                if (cells[i][j] == 'X') {
                                    isX = true;
                                }
                                if (cells[i][j] == 'O') {
                                    isY = true;
                                }
                            }
                        }
                    }
                    if (isX || isY) {
                        break;
                    }
                }
            }
        }

        if (x - 1 > y || y - 1 > x) {
            isNormal = false;
        }

        String res = "";
        if (empty > 0 && !isX && !isY) {
            return true;
        }
        if (empty == 0 && !isX && !isY) {
            res = "Draw";
        }
        if (isX) {
            res = "X wins";
        }
        if (isY) {
            res = "O wins";
        }
        outField();
        System.out.println(res);
        return false;
    }

    public static char getPattern() {
        int o = 0;
        int x = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (cells[i][j] == 'X') {
                    x++;
                }
                if (cells[i][j] == 'O') {
                    o++;
                }
            }
        }

        if (x <= o) {
            return 'X';
        } else {
            return 'O';
        }
    }

    public static void checkInput() {
        boolean inputGood = false;
        int i = 0;
        int j = 0;
        while (!inputGood) {
            System.out.println("Enter the coordinates: ");
            String one = scanner.next();
            String two = scanner.next();
            String[] words = {one, two};
            boolean isDigit = false;
            for (int k = 0; k < 2; k++) {
                for (int l = 0; l < words[k].length(); l++) {
                    if (!Character.isDigit(words[k].charAt(l))) {
                        isDigit = true;
                    }
                }
            }

            if (isDigit) {
                System.out.println("You should enter numbers!");
                continue;
            }

            i = Integer.parseInt(one);
            j = Integer.parseInt(two);

            if (i > 0 && i < 4 && j > 0 && j < 4 ) {
                if (cells[3 - j][i - 1] == '_') {
                    inputGood = true;
                } else {
                    System.out.println("This cell is occupied! Choose another one!");
                }

            } else {
                System.out.println("Coordinates should be from 1 to 3!");
            }
        }
        cells[3 - j][i - 1] = getPattern();

    }

    public static void main(String[] args) {
        fillArray(cells);
        boolean game = true;
        while (game) {
            outField();
            checkInput();
            game = checkField();
        }

    }
}