package com.example.daron.tictactoe;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


public class PlayActivity extends AppCompatActivity {
    public static final String USER_NAME = "name";
    private static TicTacToe TTTboard = new TicTacToe();
    private int currentState = TicTacToe.PLAYING;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        Intent intent = getIntent();
        String userNameText = intent.getStringExtra(USER_NAME);
        TextView welcomeUserView = findViewById(R.id.welcomeUser);
        String greeting = getString(R.string.greeting, userNameText);
        welcomeUserView.setText(greeting);
    }

    // Restarts the game by resetting everything to their initial values
    public void restartGame(View view) {
        // if the state is greater than 0 that means the game is over
        if (currentState > 0) {
            TTTboard.clearBoard();
            currentState = TicTacToe.PLAYING;
            this.clearCells();
            this.updateGameState(currentState);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putIntArray("board", TTTboard.getBoard());
        outState.putInt("gameState", currentState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        setBoard(savedInstanceState.getIntArray("board"));
        updateGameState(savedInstanceState.getInt("gameState"));
    }

    public void setBoard(int[] board) {
        for (int location = 0; location < 9; location++) {
            if (board[location] == ITicTacToe.CROSS) {
                TTTboard.setMove(ITicTacToe.CROSS, location);
                this.setImage(getButtonID(location), TicTacToe.CROSS);
            } else if (board[location] == ITicTacToe.NOUGHT) {
                TTTboard.setMove(ITicTacToe.NOUGHT, location);
                this.setImage(getButtonID(location), TicTacToe.NOUGHT);
            }
        }
    }

    // Updates the game status
    public void updateGameState(int currentState) {
        TextView gameStateView = (TextView) findViewById(R.id.gameState);
        if (currentState == ITicTacToe.CROSS_WON) {
            gameStateView.setText(R.string.userWon);
        } else if (currentState == ITicTacToe.NOUGHT_WON) {
            gameStateView.setText(R.string.computerWon);
        } else if (currentState == ITicTacToe.TIE) {
            gameStateView.setText(R.string.tie);
        } else {
            gameStateView.setText(R.string.gameState);
        }
    }

    // Clear the contents of the buttons
    public void clearCells() {
        int id;
        for (int x = 0; x < 9; x++) {
            id = this.getButtonID(x);
            this.setImage(id, ITicTacToe.EMPTY);
        }
    }

    // Sets the content of a button based on which button was pressed
    public void makeMove(View view) {
        int location = getLocation(view.getId());

        if (currentState == TicTacToe.PLAYING) {
            TTTboard.setMove(ITicTacToe.CROSS, location);
            boolean validMove = this.setImage(view.getId(), ITicTacToe.CROSS);
            currentState = TTTboard.checkForWinner();
            // We don't want the computer to move if the player has won already and vice-versa
            // Extra feature, the player has to make a valid move for the computer to move
            if (currentState == ITicTacToe.PLAYING && validMove) {
                int computerMove = TTTboard.getComputerMove();
                final int id = this.getButtonID(computerMove);
                TTTboard.setMove(ITicTacToe.NOUGHT, computerMove);
                // Extra feature: make it seems like the computer is thinking
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        setImage(id, TicTacToe.NOUGHT);
                    }
                }, 100);
                currentState = TTTboard.checkForWinner();
            }
            this.updateGameState(currentState);

        }
    }

    // Sets the button's content based on cell content and returns if a valid move was made
    public boolean setImage(int id, int player) {
        ImageButton button = findViewById(id);
        Bitmap bmp;
        String buttonTag = (String) button.getTag();
        boolean validMove;
        TextView turnView = (TextView) findViewById(R.id.turn);
        String turnString;
        if(buttonTag.equals("empty") && player == ITicTacToe.CROSS){
            bmp = BitmapFactory.decodeResource(getResources(), R.drawable.x);
            button.setImageBitmap(bmp);
            button.setTag("R.drawable.x");
            turnString = "O turn";
            turnView.setText(turnString);
            validMove = true;
        }
        else if(buttonTag.equals("empty") && player == ITicTacToe.NOUGHT){
            bmp = BitmapFactory.decodeResource(getResources(), R.drawable.o);
            button.setImageBitmap(bmp);
            button.setTag("R.drawable.o");
            turnString = "X turn";
            turnView.setText(turnString);
            validMove = true;
        }
        else if(player == ITicTacToe.EMPTY){
            button.setImageBitmap(null);
            button.setTag("empty");
            validMove = true;
        }else {
            Toast.makeText(this, "Invalid move, try again", Toast.LENGTH_SHORT).show();
            TTTboard.setMove(ITicTacToe.EMPTY, getLocation(id));
            validMove = false;
        }
        return validMove;
    }

    // Returns the location of a button based on the id
    public int getLocation(int id) {
        int location = -1;
        switch (id) {
            case R.id.button0:
                location = 0;
                break;
            case R.id.button1:
                location = 1;
                break;
            case R.id.button2:
                location = 2;
                break;
            case R.id.button3:
                location = 4;
                break;
            case R.id.button4:
                location = 4;
                break;
            case R.id.button5:
                location = 5;
                break;
            case R.id.button6:
                location = 6;
                break;
            case R.id.button7:
                location = 7;
                break;
            case R.id.button8:
                location = 8;
                break;
        }
        return location;
    }

    // Returns the id of the button based on the location
    public int getButtonID(int location) {
        int buttonID = -1;
        switch (location) {
            case 0:
                buttonID = R.id.button0;
                break;
            case 1:
                buttonID = R.id.button1;
                break;
            case 2:
                buttonID = R.id.button2;
                break;
            case 3:
                buttonID = R.id.button3;
                break;
            case 4:
                buttonID = R.id.button4;
                break;
            case 5:
                buttonID = R.id.button5;
                break;
            case 6:
                buttonID = R.id.button6;
                break;
            case 7:
                buttonID = R.id.button7;
                break;
            case 8:
                buttonID = R.id.button8;
                break;
        }
        return buttonID;
    }
}

