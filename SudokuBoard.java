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
            board[r][c] = num;
        } else {
            System.out.println("Invalid Move");
        }
    }

    public boolean isValid(int num, int r, int c) {
        // check row
        for (int i = 0; i < 9; i++) {
            if (board[r][i] == num) {
                return false;
            }
        }
        // check col
        for (int i = 0; i < 9; i++) {
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

    // public int[][] generatedBoard() {
    //     int[][] board = new int[boardSize][boardSize];
    //     for (int r=0; r < boardSize; r++) {
    //         for (int c=0; c < boardSize; c++) {
    //             double seventy = Math.random();
    //             if (seventy >= .7) {
    //                 int rand = (int) (Math.random() *9)+1;
    //                 if (isValid(rand, r, c)) {
    //                     board[r][c] = rand;
    //                 } else if (rand > 4){
    //                     rand--;
    //                     if (isValid(rand, r, c)) {
    //                         board[r][c] = rand;
    //                     } else {
    //                         board[r][c] = 0;
    //                     }
    //                 } else if (rand<5) {
    //                     rand++;
    //                     if (isValid(rand, r, c)) {
    //                         board[r][c] = rand;
    //                     } else {
    //                         board[r][c] = 0;
    //                     }
    //                 } else {
    //                     board[r][c] = 0;
    //                 }
    //             } else {
    //                 board[r][c] = 0;
    //             }
    //         }
    //     }
    //     this.board = board;
    //     return board;
    // }

    // public int[][] generateBoardattempttwo() {
    //     int[][] board = new int[boardSize][boardSize];
    //     for (int r = 0; r < boardSize; r++) {
    //         for (int c = 0; c < boardSize; c++) {
    //             int randomNum = (int) (Math.random()*9) +1;
    //             for (int num = randomNum; num <= 9; num ++) {
    //                 if (isValid(num, r, c)) {
    //                     board[r][c] = num;
    //                     break;
    //                 }
    //             }
    //             if (board[r][c] == 0) {
    //                 for (int num = randomNum; num <= 1; num--) {
    //                     if (isValid(num, r, c)) {
    //                         board[r][c] = num;
    //                         break;
    //                     }
    //                 }
    //             }
    //         }
    //     }
    //     this.board = board;
    //     return board;
    // }

    public void checkBoard() {
        int[][] board = new int[boardSize][boardSize]; 
        for (int r=0; r < boardSize; r++) {
            for (int c=0; c < boardSize; c++) {
                if (!isValid(board[r][c], r, c)) {
                    board[r][c] = 0;
                }
            }
        }
        this.board = board;
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
        int[][] board =
         {{0, 0, 0, 0, 0, 0, 0, 0, 0},
          {0, 0, 0, 0, 0, 0, 0, 0, 0},
          {0, 0, 0, 0, 0, 0, 0, 0, 0},
          {0, 0, 0, 0, 0, 0, 0, 0, 0},
          {0, 0, 0, 0, 0, 0, 0, 0, 0},
          {0, 0, 0, 0, 0, 0, 0, 0, 0},
          {0, 0, 0, 0, 0, 0, 0, 0, 0},
          {0, 0, 0, 0, 0, 0, 0, 0, 0},
          {0, 0, 0, 0, 0, 0, 0, 0, 0}};
        SudokuBoard a = new SudokuBoard(board);
        // board = a.generateBoardattempttwo();
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