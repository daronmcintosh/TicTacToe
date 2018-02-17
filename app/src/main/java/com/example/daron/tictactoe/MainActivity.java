// Use this for the splash screen
package com.example.daron.tictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sendUsername(View view){
        EditText userNameView = findViewById(R.id.nameInput);
        String userNameText = userNameView.getText().toString();
        Intent startGame = new Intent(this, PlayActivity.class);
        startGame.putExtra(PlayActivity.USER_NAME, userNameText);
        startActivity(startGame);
    }

}
