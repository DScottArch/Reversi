package com.example.reversi;

import android.view.View;
import android.widget.Button;

public class PlayerBlack implements View.OnClickListener
{
    Board gameBoard;

    public PlayerBlack(Board gameBoard)
    {
        this.gameBoard = gameBoard;

    }

    @Override
    public void onClick(View view)
    {

    }
}
