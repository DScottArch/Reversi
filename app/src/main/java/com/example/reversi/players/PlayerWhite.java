package com.example.reversi.players;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;

import com.example.reversi.setup.Board;
import com.example.reversi.R;

import java.util.ArrayList;

public class PlayerWhite
{
    Board gameBoard;
    Activity activity;
    Drawable sameSquare;
    Drawable opSquare;
    Drawable empty;

    public PlayerWhite(Board gameBoard, Activity activity, Drawable empty, Drawable opSquare, Drawable sameSquare)
    {
        this.gameBoard = gameBoard;
        this.activity = activity;
        this.sameSquare = sameSquare;
        this.opSquare = opSquare;
        this.empty = empty;
    }

    public boolean playTurn(int id)
    {
        boolean isLegalMove = false;

        Log.d("PlayerBlack", "playTurn() called");

        ArrayList<View> squaresToFlip = new ArrayList();

        boolean connectedSquares = false;

        int selectedID = gameBoard.buttonList.get(id).getId();

        int belowID = selectedID + 8;

        //check below squares
        Log.d("buttonListID", ":" + gameBoard.buttonList.get(id).getId());
        if(belowID < 64)
        {
            connectedSquares = gameBoard.buttonList.get(belowID).getBackground().equals(opSquare);

            Log.d("connectedSquares", String.valueOf(connectedSquares));
            if(connectedSquares)
            {
                while(belowID + 8 < 64)
                {
                    Log.d("tempID", String.valueOf(belowID));

                    connectedSquares = !gameBoard.buttonList.get(belowID).getBackground().equals(opSquare);

                    Log.d("connectedSquaresWhileLoop", String.valueOf(!connectedSquares));

                    Drawable background = gameBoard.buttonList.get(belowID).getBackground();

                    if(!connectedSquares)
                    {
                        squaresToFlip.add(gameBoard.buttonList.get(belowID));
                        int i = 0;
                        Log.d("squaresToFlipCount", String.valueOf(++i));
                    }
                    else
                    {
                        if(background == sameSquare)
                        {
                            Log.d("isSameSquare", ":" + String.valueOf(background == sameSquare));
                            gameBoard.buttonList.get(selectedID).setBackground(sameSquare);
                            isLegalMove = true;
                            for(View button : squaresToFlip)
                            {
                                flipButton(button);
                            }
                        }
                        break;
                    }
                    belowID += 8;
                }
            }
        }

        Log.d("isLegalMove", String.valueOf(isLegalMove));

        return isLegalMove;
    }

    public void flipButton(View view)
    {
        if(view.getBackground().equals(opSquare))
        {
            view.setBackground(sameSquare);
        }
        else if(view.getBackground().equals(sameSquare))
        {
            view.setBackground(opSquare);
        }
    }
}
