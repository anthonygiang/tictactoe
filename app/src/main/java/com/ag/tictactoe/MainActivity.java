package com.ag.tictactoe;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.ag.tictactoe.controller.GameController;
import com.ag.tictactoe.view.GameView;

/**
 * Runs a Tic Tac Toe game against a person or AI.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GameView gameView = new GameView(this);
        GameController gameController = new GameController(gameView);
    }

}
