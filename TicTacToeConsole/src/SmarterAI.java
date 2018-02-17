import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SmarterAI {
    private static final int ROWS = 3, COLS = 3; // number of rows and columns
    private int[][] board;
    private boolean centerAvailable;

    public SmarterAI(int[][] board) {
        this.board = board;
    }

    // Recomposed checkWin function that tries to win against the player
    // the ai doesn't always make the winning move which is weird
    public int possibleWinningMoves() {
        int count = 0; // used to check for a possible moves
        int positionIn2DArray = -1; // used to find the move on the board
        int computerMove = positionIn2DArray; // the final move that this method returns
        boolean foundAMove = false;
        while (!foundAMove) {
            // Check rows for moves
            for (int r = 0; r < ROWS; r++) {
                count = 0;
                for (int c = 0; c < COLS; c++) {
                    if (board[r][c] == ITicTacToe.CROSS) {
                        count += 1;
                    } else if (board[r][c] == ITicTacToe.NOUGHT) {
                        count -= 1;
                    }
                    // Used to keep track of which cell is available for a move
                    if (board[r][c] == ITicTacToe.EMPTY) {
                        positionIn2DArray = (3 * r + c);
                    }
                }
                // this conditional finds the place where the user can win and blocks it
                if (count == 2) {
                    computerMove = positionIn2DArray;
                    foundAMove = true;
                }
                // having this conditional after the one above makes sure that the move to make the ai win takes precedence
                // over blocking the user
                if (count == -2) {
                    computerMove = positionIn2DArray;
                    foundAMove = true;
                }
            }
            // If it finds a move, don't check for a move
            if (!foundAMove) {
                // Check columns for moves
                for (int c = 0; c < COLS; c++) {
                    count = 0;
                    for (int r = 0; r < ROWS; r++) {
                        if (board[r][c] == ITicTacToe.CROSS) {
                            count += 1;
                        } else if (board[r][c] == ITicTacToe.NOUGHT) {
                            count -= 1;
                        }
                        if (board[r][c] == ITicTacToe.EMPTY) {
                            positionIn2DArray = (3 * r + c);
                        }
                    }
                    if (count == 2) {
                        computerMove = positionIn2DArray;
                        foundAMove = true;
                    }
                    if (count == -2) {
                        computerMove = positionIn2DArray;
                        foundAMove = true;
                    }
                }
            }
            if (!foundAMove) {
                // Check first diagonal for moves
                count = 0;
                for (int r = 0, c = 0; r < 3; r++, c++) {
                    if (board[r][c] == ITicTacToe.CROSS) {
                        count += 1;
                    }
                    if (board[r][c] == ITicTacToe.NOUGHT) {
                        count -= 1;
                    }
                    if (board[r][c] == ITicTacToe.EMPTY) {
                        positionIn2DArray = (3 * r + c);
                    }
                    if (count == 2) {
                        computerMove = positionIn2DArray;
                        foundAMove = true;
                    }
                    if (count == -2) {
                        computerMove = positionIn2DArray;
                        foundAMove = true;
                    }
                }
            }
            if (!foundAMove) {
                count = 0;
                for (int r = 0, c = 2; r < 3; r++, c--) {
                    if (board[r][c] == ITicTacToe.CROSS) {
                        count += 1;
                    }
                    if (board[r][c] == ITicTacToe.NOUGHT) {
                        count -= 1;
                    }
                    if (board[r][c] == ITicTacToe.EMPTY) {
                        positionIn2DArray = (3 * r + c);
                    }
                    if (count == 2) {
                        computerMove = positionIn2DArray;
                        foundAMove = true;
                    }
                    if (count == -2) {
                        computerMove = positionIn2DArray;
                        foundAMove = true;
                    }
                }
            }
            if(computerMove==-1){
                // kind of like a little hack even though no move was found so we avoid an infinite loop
                foundAMove=true;
            }
        }
        System.out.println(computerMove);
        return computerMove;
    }

    // Computer moves to a empty slot starting from top left
    public int startFromTopLeft() {
        int positionIn2DArray = 0;
        int computerMove;

        // finds the first available space starting from top left
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                if (board[r][c] == ITicTacToe.EMPTY) {
                    positionIn2DArray = (3 * r + c);
                    // Used to exit array early
                    r = 4;
                    c = 4;
                }
            }
        }
        computerMove = positionIn2DArray;
        return computerMove;
    }

    //All available computer moves
    public List<Integer> availableMoves() {
        int positionIn2DArray;
        List<Integer> possibleMoves = new ArrayList<>();
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                if (board[r][c] == ITicTacToe.EMPTY) {
                    positionIn2DArray = (3 * r + c);
                    // Always take the center if it is available
                    if (positionIn2DArray == 4) {
                        centerAvailable = true;
                    }
                    possibleMoves.add(positionIn2DArray);
                }
            }
        }
        return possibleMoves;
    }


    // Use this function to generate the move
    public int getComputerMove() {
        int computerMove;
        List<Integer> availableMoves = availableMoves();
        int aimove = possibleWinningMoves();
        Random r = new Random();
        if (centerAvailable) {
            computerMove = 4;
            centerAvailable = false;
            System.out.println("Took the center");
        } else if (aimove == -1) {
            // returns a semi-random move
            computerMove = availableMoves.get(r.nextInt(availableMoves.size()));
            System.out.println("random move to " + computerMove);
        } else {
            // returns the ai move
            computerMove = aimove;
            System.out.println("AI move to " + computerMove);
        }
        return computerMove;
    }
}