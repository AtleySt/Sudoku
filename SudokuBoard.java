import java.util.Scanner;

public class SudokuBoard {
    private int[][] board;
    private int boardSize;
    private int boxSize;

    public SudokuBoard(int[][] board) {
        this.board = board;
        this.boardSize = board.length;
        this.boxSize = (int) Math.sqrt(boardSize);
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
    
    public void printBoard() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
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
        int[][] board = {
            {0, 0, 0, 0, 8, 0, 0, 7, 0},
            {0, 4, 0, 2, 0, 7, 0, 0, 0},
            {0, 0, 7, 0, 0, 3, 5, 4, 9},
            {2, 0, 0, 0, 0, 0, 3, 0, 0},
            {0, 0, 6, 0, 3, 0, 0, 0, 0},
            {7, 0, 0, 0, 9, 6, 0, 8, 0},
            {3, 0, 0, 5, 0, 1, 0, 2, 8},
            {1, 8, 0, 4, 0, 0, 0, 6, 3},
            {0, 6, 0, 0, 0, 0, 4, 0, 0}};
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
        s.close();
    }
}