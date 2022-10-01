
public class TikTakToe {

    // ---------------------------
    // INSTANCE VARIABLES
    // ---------------------------
    private char[][] board;
    private int numDropped;
    private char playerSymbol;
    private boolean over;
    private char[] player;
    private int winnerIndex;
    private int mode;

    // ---------------------------
    // CONSTRUCTOR
    // ---------------------------
    public TikTakToe(int mode) {
        this.mode = mode;
        board = new char[3][3];
        for (int i = 0; i <= 2; i++) {
            for (int j = 0; j <= 2; j++) {
                board[i][j] = '-';
            }
        }
        numDropped = 0;
        player = new char[2];
        winnerIndex = -1;
    }

    // ---------------------------
    // METHODS
    // ---------------------------
    public int getNumDropped() {
        return numDropped;
    }

    public char[][] getBoard() {
        return board;
    }

    public char getPlayerSymbol() {
        return playerSymbol;
    }

    public boolean isInBounds(int row, int col) {
        if (row > 2 || col > 2 || row < 0 || col < 0) {
            return false;
        }
        return true;
    }

    public void setPlayerSymbols(char playerOne, char playerTwo) {
        player[0] = playerOne;
        player[1] = playerTwo;
    }

    public char getPlayerSymbol(int player) {
        return this.player[player];
    }

    public int getMode() {
        return mode;
    }

    public void takeTurn(int player, int row, int col, boolean computer) {
        if (board[row - 1][col - 1] != '-') {
            throw new IllegalStateException("Not a valid spot");
        }
        if (!isInBounds(row - 1, col - 1)) {
            throw new IndexOutOfBoundsException("Not in bounds");
        }
        if (over) {
            throw new IllegalStateException("Game is over");
        }
        if (player != 1 && player != 0) {
            throw new IllegalArgumentException("Not a player");
        }
        board[row - 1][col - 1] = getPlayerSymbol(player);
        numDropped++;
        clearScreen();
        printBoard();
        if (isOver(board)) {
            printFinalScreen();
        }
        if (mode == 1 && computer == false) {
            computerTurn();
        }
    }

    public void computerTurn() {
        int comCol = -1;
        int comRow = -1;
        boolean validTurn = false;
        char[][] fakeBoard = new char[3][3];
        for (int i = 20; i > 0; i--) {
            // System.out.print(i);
            if (isOver(board)) {
                break;
            }
            validTurn = false;
            fakeBoard = board;
            while (!validTurn) {
                comRow = (int) (Math.random() * 3);
                comCol = (int) (Math.random() * 3);
                if (board[comRow][comCol] == '-') {
                    validTurn = true;
                }
            }
            fakeBoard[comRow][comCol] = getPlayerSymbol(1);
            if (isOver(fakeBoard)) {
                // System.out.println(comRow + " " + comCol);
                board[comRow][comCol] = '-';
                takeTurn(1, comRow + 1, comCol + 1, true);
                return;
            }
            fakeBoard[comRow][comCol] = '-';
            // System.out.println(comRow + "-" + comCol);
        }
        // ------------------------------------------------------------------
        for (int i = 20; i > 0; i--) {
            // System.out.print(i + ".");
            if (isOver(board)) {
                break;
            }
            validTurn = false;
            fakeBoard = board;
            while (!validTurn) {
                comRow = (int) (Math.random() * 3);
                comCol = (int) (Math.random() * 3);
                if (board[comRow][comCol] == '-') {
                    validTurn = true;
                }
            }
            fakeBoard[comRow][comCol] = getPlayerSymbol(0);
            if (isOver(fakeBoard)) {
                // System.out.println(comRow + " " + comCol);
                board[comRow][comCol] = '-';
                takeTurn(1, comRow + 1, comCol + 1, true);
                return;
            }
            fakeBoard[comRow][comCol] = '-';
            // System.out.println(comRow + "-" + comCol);
        }
        takeTurn(1, comRow + 1, comCol + 1, true);
    }

    public boolean isOver(char[][] board) {
        for (int j = 1; j >= 0; j--) {
            for (int i = 2; i >= 0; i--) {
                if (board[i][0] == getPlayerSymbol(j) && board[i][1] == getPlayerSymbol(j)
                        && board[i][2] == getPlayerSymbol(j)) {
                    winnerIndex = j;
                    return true;
                }
            }
        }
        for (int j = 1; j >= 0; j--) {
            for (int i = 2; i >= 0; i--) {
                if (board[0][i] == getPlayerSymbol(j) && board[1][i] == getPlayerSymbol(j)
                        && board[2][i] == getPlayerSymbol(j)) {
                    winnerIndex = j;
                    return true;
                }
            }
        }
        for (int j = 1; j >= 0; j--) {
            if (board[2][2] == getPlayerSymbol(j) && board[1][1] == getPlayerSymbol(j)
                    && board[0][0] == getPlayerSymbol(j)) {
                winnerIndex = j;
                return true;
            }
        }
        for (int j = 1; j >= 0; j--) {
            if (board[2][0] == getPlayerSymbol(j) && board[1][1] == getPlayerSymbol(j)
                    && board[0][2] == getPlayerSymbol(j)) {
                winnerIndex = j;
                return true;
            }
        }
        if (numDropped == 9) {
            winnerIndex = 3;
            return true;
        }
        return false;
    }

    public void printBoard() {
        System.out.println("   1     2     3");
        System.out.println("      |     |     ");
        System.out.println("1  " + board[0][0] + "  |  " + board[0][1] + "  |  " + board[0][2] + "  ");
        System.out.println(" _____|_____|_____");
        System.out.println("      |     |     ");
        System.out.println("2  " + board[1][0] + "  |  " + board[1][1] + "  |  " + board[1][2] + "  ");
        System.out.println(" _____|_____|_____");
        System.out.println("      |     |     ");
        System.out.println("3  " + board[2][0] + "  |  " + board[2][1] + "  |  " + board[2][2] + "  ");
        System.out.println("      |     |     \n");
    }

    public static void clearScreen() {

        System.out.print("\033[H\033[2J");

        System.out.flush();

    }

    public void printFinalScreen() {
        if (winnerIndex == 3) {
            System.out.println("TIED GAME!");
            System.exit(0);
        } else {
            System.out.println(getPlayerSymbol(winnerIndex) + " Won The Game!");
            System.exit(0);
        }
    }
}
