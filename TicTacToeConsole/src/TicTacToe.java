public class TicTacToe implements ITicTacToe {

    // The game board and the game status
    private static final int ROWS = 3, COLS = 3; // number of rows and columns
    private int[][] board = new int[ROWS][COLS]; // game board in 2D array
    private int player;

    private SmarterAI ai = new SmarterAI(board);

    /**
     * clear board and set current player
     */
    public TicTacToe() {
        clearBoard();
    }

    @Override
    public void clearBoard() {
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                if (board[r][c] != EMPTY) {
                    board[r][c] = EMPTY;
                }
            }
        }
    }

    // As of now, if the player move is invalid, the player loses their turn.
    @Override
    public void setMove(int player, int location) {
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                int positionIn2DArray = (3 * r + c);
                if ((location == positionIn2DArray) && (board[r][c] != CROSS && board[r][c] != NOUGHT)) {
                    board[r][c] = player;
                    // Used to exit array early
                    r = 3;
                    c = 3;
                }
            }
        }
    }


    @Override
    public int getComputerMove() {
        return ai.getComputerMove();
    }

    @Override
    public int checkForWinner() {
        int currentState = PLAYING;
        int count;

        // Check rows for winner
        for (int r = 0; r < ROWS; r++) {
            count = 0;
            for (int c = 0; c < COLS; c++) {
                if (board[r][c] == CROSS) {
                    count += 1;
                } else if (board[r][c] == NOUGHT) {
                    count -= 1;
                }
            }
            if (count == 3) {
                currentState = CROSS_WON;
            } else if (count == -3) {
                currentState = NOUGHT_WON;
            }
        }

        // Check columns for winner
        for (int c = 0; c < COLS; c++) {
            count = 0;
            for (int r = 0; r < ROWS; r++) {
                if (board[r][c] == CROSS) {
                    count += 1;
                } else if (board[r][c] == NOUGHT) {
                    count -= 1;
                }
            }
            if (count == 3) {
                currentState = CROSS_WON;
            } else if (count == -3) {
                currentState = NOUGHT_WON;
            }
        }

        // Check first diagonal for winner
        count = 0;
        for (int r=0, c=0; r < 3; r++,c++) {
            if (board[r][c] == CROSS) {
                count += 1;
            } else if (board[r][c] == NOUGHT) {
                count -= 1;
            }
            if (count == 3) {
                currentState = CROSS_WON;
            } else if (count == -3) {
                currentState = NOUGHT_WON;
            }
        }

        // Check second diagonal for winner
        count = 0;
        for (int r = 0, c=2; r < 3; r++, c--) {
            if (board[r][c] == CROSS) {
                count += 1;
            } else if (board[r][c] == NOUGHT) {
                count -= 1;
            }
            if (count == 3) {
                currentState = CROSS_WON;
            } else if (count == -3) {
                currentState = NOUGHT_WON;
            }

        }

        // Don't check for a tie if there is a winner
        if (currentState != NOUGHT_WON && currentState != CROSS_WON) {
            // Check for a tie
            count = 0;
            for (int r = 0; r < ROWS; r++) {
                for (int c = 0; c < COLS; c++) {
                    if (board[r][c] != EMPTY) {
                        count += 1;
                    }
                }
                if (count == 9) {
                    currentState = TIE;
                }
            }
        }

        return currentState;
    }

    /**
     * Print the game board
     */
    public void printBoard() {
        for (int row = 0; row < ROWS; ++row) {
            for (int col = 0; col < COLS; ++col) {
                printCell(board[row][col]); // print each of the cells
                if (col != COLS - 1) {
                    System.out.print("|");   // print vertical partition
                }
            }
            System.out.println();
            if (row != ROWS - 1) {
                System.out.println("-----------"); // print horizontal partition
            }
        }
        System.out.println();
    }

    /**
     * Print a cell with the specified "content"
     *
     * @param content either CROSS, NOUGHT or EMPTY
     */
    public void printCell(int content) {
        switch (content) {
            case EMPTY:
                System.out.print("   ");
                break;
            case NOUGHT:
                System.out.print(" O ");
                break;
            case CROSS:
                System.out.print(" X ");
                break;
        }
    }
}
