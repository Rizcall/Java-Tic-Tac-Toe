import java.util.Scanner;

public class Main {
    private char[][] board;
    private char currentPlayer;

    public Main() {
        board = new char[3][3];
        currentPlayer = 'X';
        initializeBoard();
    }

    // Initialize the board with empty cells
    private void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
    }

    // Print the board
    public void printBoard() {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    // Check if the board is full
    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }

    // Check if someone has won the game
    private boolean checkWin() {
        return (checkRows() || checkColumns() || checkDiagonals());
    }

    private boolean checkRows() {
        for (int i = 0; i < 3; i++) {
            if (checkRowCol(board[i][0], board[i][1], board[i][2])) {
                return true;
            }
        }
        return false;
    }

    private boolean checkColumns() {
        for (int i = 0; i < 3; i++) {
            if (checkRowCol(board[0][i], board[1][i], board[2][i])) {
                return true;
            }
        }
        return false;
    }

    private boolean checkDiagonals() {
        return (checkRowCol(board[0][0], board[1][1], board[2][2]) ||
                checkRowCol(board[0][2], board[1][1], board[2][0]));
    }

    private boolean checkRowCol(char c1, char c2, char c3) {
        return ((c1 != '-') && (c1 == c2) && (c2 == c3));
    }

    // Switch the current player
    private void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    // Place a move on the board
    public void placeMove(int row, int col) {
        if (row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == '-') {
            board[row][col] = currentPlayer;
            switchPlayer();
        } else {
            System.out.println("Invalid move. Please try again.");
        }
    }

    // Main method to run the game
    public static void main(String[] args) {
        Main game = new Main();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Tic Tac Toe!");
        game.printBoard();

        while (!game.checkWin() && !game.isBoardFull()) {
            System.out.print("Player " + game.currentPlayer + ", enter your move (row[1-3] column[1-3]): ");
            int row = scanner.nextInt() - 1;
            int col = scanner.nextInt() - 1;
            game.placeMove(row, col);
            game.printBoard();
        }

        if (game.checkWin()) {
            System.out.println("Player " + game.currentPlayer + " wins!");
        } else {
            System.out.println("It's a draw!");
        }
        scanner.close();
    }
}
