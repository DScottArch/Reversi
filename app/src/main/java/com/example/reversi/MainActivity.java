package com.example.reversi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.List;

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