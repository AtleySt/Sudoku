import java.util.Scanner;
import java.util.Arrays;

public class SudokuBoard {
    private int[][] board;
    final int[][] BOARDCOPY;
    private int boardSize;
    private int boxSize;

    public SudokuBoard(int[][] board) {
        for (int r=0; r < boardSize; r++) {
            for (int c=0; c < boardSize; c++) {
                double seventy = Math.random();
                if (seventy >= .7) {
                    int rand = (int) (Math.random() *9)+1;
                    if (isValid(rand, r, c)) {
                        board[r][c] = rand;
                    } else if (rand > 4){
                        rand--;
                        if (isValid(rand, r, c)) {
                            board[r][c] = rand;
                        }
                    } else if (rand<5) {
                        rand++;
                        if (isValid(rand, r, c)) {
                            board[r][c] = rand;
                        }
                    } else {
                        board[r][c] = 0;
                    }
                } else {
                    board[r][c] = 0;
                }
            }
        }
        this.board = board;
        this.boardSize = 9;
        this.boxSize = (int) Math.sqrt(boardSize);
        this.BOARDCOPY = board;
    }

    public void makeMove(int num, int r, int c) {
        if (isValid(num, r, c)) {
            board[r][c] += num;
        } else {
            System.out.println("Invalid Move");
        }
    }

    public boolean isValid(int num, int r, int c) {
        // check row
        for (int i = 0; i < boardSize; i++) {
            if (board[r][i] == num) {
                return false;
            }
        }
        // check col
        for (int i = 0; i < boardSize; i++) {
            if (board[i][c] == num) {
                return false;
            }
        }
        // check box
        int boxRow = r - r % boxSize;
        int boxCol = c - c % boxSize;
        for (int i = boxRow; i < boxRow + boxSize; i++) {
            for (int j = boxCol; j < boxCol + boxSize; j++) {
                if (board[i][j] == num) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isWin(int[][] board) {
        for (int r = 0; r < boardSize; r++) {
            for (int c = 0; c < boardSize; c++) {
                if (board[r][c] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public void resetBoard() {
        board = BOARDCOPY;
    }
    
    public void printBoard() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    // public int increment(int randomNumber, int a, int b) {
    //     for (int i = randomNumber; i <= 9; i++) { // Loop up till random number gets to 9
    //         if (isValid(randomNumber, a, b)) { // Check valid every loop
    //             break; // Stop if it is valid
    //         } else {
    //             continue; // Continue if not
    //         }
    //     }
    //     return randomNumber;
    // }

    // public int decrement(int randomNumber, int a, int b) {
    //     for (int i = randomNumber; i <= 0; i++) { // Loop till random number gets to 0
    //         if (isValid(randomNumber, a, b)) { // Check valid every loop
    //             break; // Stop if it is valid
    //         } else {
    //             continue; // Continue if not
    //         }
    //     }
    //     return randomNumber;
    // }

    // public void generateBoard() {
    //     String test = "{ ";
    //     int[][] board = new int[boardSize][boardSize];

    //     for (int a = 0; a < boardSize; a++) { // Make board filled with zeros
    //         for (int b = 0; b < boardSize; b++) {
    //             board[a][b] = 0;
    //         }
    //     }

    //     for (int a = 0; a < boardSize; a++) {
    //         for (int b = 0; b < boardSize; b++) {
    //             int randomNumber = (int) (Math.random() *9) +1;
    //             if (!isValid(randomNumber, a, b)) { // If random number is not valid
    //                 if (randomNumber >= 5) { // Greater than 4
    //                     randomNumber = increment(randomNumber, a, b); // Increment up to 5-9
    //                     randomNumber = 4; // set to be lower than original
    //                     randomNumber = decrement(randomNumber, a, b); // Decrement from 4-0
    //                 }
    //                 if (randomNumber < 5) { // Less than 5
    //                     randomNumber = decrement(randomNumber, a, b); // Decrement from 4-0
    //                     randomNumber = 5; // set to be lower than original
    //                     randomNumber = increment(randomNumber, a, b); // Increment up to 5-9
    //                 }
    //             }
    //             board[a][b] = randomNumber;
    //             test+=board[a][b] + ", ";
    //         }
    //     }
    //     System.out.println(Arrays.asList(test));
    // }

    public int[][] generatedBoard() {
        int[][] board = new int[boardSize][boardSize];
        for (int r=0; r < boardSize; r++) {
            for (int c=0; c < boardSize; c++) {
                double seventy = Math.random();
                if (seventy >= .7) {
                    int rand = (int) (Math.random() *9)+1;
                    if (isValid(rand, r, c)) {
                        board[r][c] = rand;
                    } else if (rand > 4){
                        rand--;
                        if (isValid(rand, r, c)) {
                            board[r][c] = rand;
                        }
                    } else if (rand<5) {
                        rand++;
                        if (isValid(rand, r, c)) {
                            board[r][c] = rand;
                        }
                    } else {
                        board[r][c] = 0;
                    }
                } else {
                    board[r][c] = 0;
                }
            }
        }
        this.board = board;
        return board;
    }

    public String toString() {
        String res = "";
        res += " C 1 2 3   4 5 6   7 8 9";
        res += "\n"+"R"+"\n";
        for (int r = 0; r < boardSize; r++) {
            res += (r+1)+"  ";
            for (int c = 0; c < boardSize; c++) {
                if (board[r][c] == 0) {
                    res+= "  ";
                } else {
                    res += board[r][c] + " ";
                }
                if (c == 2 || c == 5) {
                    res += "| ";
                }
            }
            res += "\n";
            if (r == 2 || r == 5) {
                res += "   ------|-------|------";
                res += "\n";
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] board = null;
        SudokuBoard a = new SudokuBoard(board);
        Scanner s = new Scanner(System.in);
        while (!a.isWin(board)) {
            System.out.println(a.toString());
            System.out.println();
            System.out.println("Enter the row you would like to play (1-9)");
            int row = s.nextInt();
            System.out.println("Enter the column you would like to play (1-9)");
            int col = s.nextInt();
            System.out.println("What number would you like to place here");
            int num = s.nextInt();
            a.makeMove(num, row-1, col-1);
        }
        if (a.isWin(board)) {
            System.out.println(a.toString());
            System.out.println("You win!!");
            System.out.println("play again? (1 = yes   2 = no)");
            int again = s.nextInt();
            if (again == 2) {
                s.close();
            } else if (again == 1) {
                a.resetBoard();
            }
        }
    }
}