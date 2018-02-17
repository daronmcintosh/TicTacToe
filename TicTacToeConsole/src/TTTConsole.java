import java.util.Scanner;
public class TTTConsole {

    private static Scanner in = new Scanner(System.in); // the input Scanner

    private static TicTacToe TTTboard = new TicTacToe();

    /**
     * The entry main method (the program starts here)
     */
    public static void main(String[] args) {

        int currentState = TicTacToe.PLAYING;
        String userInput = "game has begun";

//	   //game loop
//	   do {
//         TTTboard.printBoard();
//         // Print message if game-over
//         currentState = TTTboard.checkForWinner();
//         /**
//          * get player input here and call setMove(). user should input a number between 0-8
//          */
//         System.out.println("Please enter a number between 0-8:");
//         userInput = in.nextLine();
//         // First player is always the user and is represented by the cross
//         TTTboard.setMove(ITicTacToe.CROSS, Integer.parseInt(userInput));
//           // We don't want the computer to move if the player has won already and vice-versa
//           if (currentState == ITicTacToe.PLAYING) {
//               TTTboard.setMove(ITicTacToe.NOUGHT, TTTboard.getComputerMove());
//               currentState = TTTboard.checkForWinner();
//           }
//         if (currentState == ITicTacToe.CROSS_WON) {
//            System.out.println("'X' won! Bye!");
//         } else if (currentState == ITicTacToe.NOUGHT_WON) {
//            System.out.println("'O' won! Bye!");
//         } else if (currentState == ITicTacToe.TIE) {
//            System.out.println("It's a TIE! Bye!");
//         }
//      } while ((currentState == ITicTacToe.PLAYING) && (!userInput.equals("q"))); // repeat if not game-over


        // updated game loop
        TTTboard.printBoard(); // initial empty board
        while ((currentState == ITicTacToe.PLAYING) && (!userInput.equals("q"))) {
            System.out.println("Please enter a number between 0-8:");
            userInput = in.nextLine();
            TTTboard.setMove(ITicTacToe.CROSS, Integer.parseInt(userInput));
            currentState = TTTboard.checkForWinner();

            // We don't want the computer to move if the player has won already and vice-versa
            if (currentState == ITicTacToe.PLAYING) {
                TTTboard.setMove(ITicTacToe.NOUGHT, TTTboard.getComputerMove());
                currentState = TTTboard.checkForWinner();
            }
            TTTboard.printBoard();

            if (currentState == ITicTacToe.CROSS_WON) {
                System.out.println("'X' won! Bye!");
            } else if (currentState == ITicTacToe.NOUGHT_WON) {
                System.out.println("'O' won! Bye!");
            } else if (currentState == ITicTacToe.TIE) {
                System.out.println("It's a TIE! Bye!");
            }

        }
    }


}