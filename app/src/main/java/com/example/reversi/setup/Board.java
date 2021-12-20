package com.example.reversi.setup;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.reversi.R;
import com.example.reversi.players.PlayerBlack;
import com.example.reversi.players.PlayerWhite;

import java.util.ArrayList;
import java.util.List;



public class Board implements View.OnClickListener
{
    public List<Button> buttonList;
    private LinearLayout horizontalLayout;
    private Activity activity;
    boolean isBlacksTurn = true;
    Drawable emptySquareDrawable;
    Drawable blackSquareDrawable;
    Drawable whiteSquareDrawable;
    PlayerBlack playerBlack;
    PlayerWhite playerWhite;

    public Board(Activity activity)
    {
        this.activity = activity;
    }

    public void createBoard()
    {
        buttonList = new ArrayList<>();

        LinearLayout verticalLayout = activity.findViewById(R.id.verticalLayout);

        Display display = activity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int screenW = size.x;
        int screenH = size.y;

        int squareHW = screenW / 8;

        emptySquareDrawable = activity.getDrawable(R.drawable.empty_square);
        blackSquareDrawable = activity.getDrawable(R.drawable.black_square);
        whiteSquareDrawable = activity.getDrawable(R.drawable.white_square);

        playerBlack = new PlayerBlack(this, activity, emptySquareDrawable, blackSquareDrawable, whiteSquareDrawable);
        playerWhite = new PlayerWhite(this, activity, emptySquareDrawable, blackSquareDrawable, whiteSquareDrawable);

        int count = 0;


        for(int column = 0; column < 8; column++)
        {
            horizontalLayout = new LinearLayout(activity.getApplicationContext());
            horizontalLayout.setId('h' + column);
            horizontalLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));


            for(int row = 0; row < 8; row++)
            {
                Button btnTag = new Button(activity.getApplicationContext());
                if((column == 3 && row == 3) || (column == 4 && row == 4))
                {
                    btnTag.setBackground(whiteSquareDrawable);
                }
                else if((column == 4 && row == 3) || (column == 3 && row == 4))
                {
                    btnTag.setBackground(blackSquareDrawable);
                }
                else
                {
                    btnTag.setBackground(emptySquareDrawable);
                }
                btnTag.setLayoutParams(new LinearLayout.LayoutParams(squareHW, squareHW));
                btnTag.setId(count);
                horizontalLayout.addView(btnTag);
                buttonList.add(btnTag);

                count++;
                //Log.d("btnTagID", String.valueOf(btnTag.getId()));
            }

            verticalLayout.addView(horizontalLayout);
        }

        for(Button button : buttonList)
        {
            button.setOnClickListener(this);
        }

        Log.d("board", 0 + "  " + 1 + "  " + 2 + "  " +
                3 + "  " + 4 + "  " + 5 + "  " + 6 + "  " + 7);

        Log.d("board", 8 + "  " + 9 + "  " + 10 + " " +
                11 + " " + 12 + " " + 13 + " " + 14 + " " + 15);

        for(int i = 16; i < 64; i = i + 8)
        {
            Log.d("board", i + " " + (i + 1) + " " + (i + 2) + " " +
                    (i + 3) + " " + (i + 4) + " " + (i + 5) + " " + (i + 6) + " " + (i + 7));
        }
    }

    @Override
    public void onClick(View view)
    {
        Log.d("isBlacksTurn", String.valueOf(isBlacksTurn));

        if(isBlacksTurn)
        {
            playerBlack.playTurn(view.getId());
            isBlacksTurn = false;
            Log.d("board", "onClick() called");
        }
        else if(!isBlacksTurn)
        {
            playerWhite.playTurn(view.getId());
            isBlacksTurn = true;
        }
    }


}