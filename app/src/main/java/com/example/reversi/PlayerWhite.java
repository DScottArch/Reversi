package com.example.reversi;

import android.view.View;
import android.widget.Button;

public class PlayerWhite implements View.OnClickListener
{
    Board gameBoard;

    public PlayerWhite(Board gameBoard)
    {
        this.gameBoard = gameBoard;
    }

    @Override
    public void onClick(View view)
    {
    }
}
