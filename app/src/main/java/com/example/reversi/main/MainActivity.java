package com.example.reversi.main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.reversi.setup.Board;
import com.example.reversi.R;

public class MainActivity extends AppCompatActivity
{
    boolean blackTurn = true;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startGame();
    }

    public void startGame()
    {
        Board gameBoard = new Board(this);

        gameBoard.createBoard();
    }
}