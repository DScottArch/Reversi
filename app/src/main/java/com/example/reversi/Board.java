package com.example.reversi;

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

        int count = 0;

        emptySquareDrawable = activity.getDrawable(R.drawable.empty_square);
        blackSquareDrawable = activity.getDrawable(R.drawable.black_square);
        whiteSquareDrawable = activity.getDrawable(R.drawable.white_square);

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
            if(checkLegalMove(view))
            {
                isBlacksTurn = false;
            }
        }
        else if(!isBlacksTurn)
        {
            if(checkLegalMove(view))
            {
                isBlacksTurn = true;
            }
        }
    }

    public boolean checkLegalMove(View view)
    {
        boolean isLegalMove = false;
//        if(activity.findViewById(buttonList.get((int)view.getId() + 8).getId()).getBackground().equals(whiteSquareDrawable))
//        {
//            buttonList.get(16).setBackgroundResource(R.drawable.black_square);
//        }
        ArrayList<View> squaresToFlip = new ArrayList();

        boolean connectedSquares = false;

        int selectedID = view.getId();

        int belowID = selectedID + 8;

        //check below squares
        if(activity.findViewById(belowID) != null)
        {
            if(isBlacksTurn)
            {
                connectedSquares = activity.findViewById(belowID).getBackground().equals(whiteSquareDrawable);
            }
            else
            {
                connectedSquares = activity.findViewById(belowID).getBackground().equals(blackSquareDrawable);
            }

            if(connectedSquares)
            {
                while((belowID + 8 < 64) && activity.findViewById(belowID + 8) != null)
                {
                    Log.d("tempID", String.valueOf(belowID));

                    if(isBlacksTurn)
                    {
                        connectedSquares = !activity.findViewById(belowID).getBackground().equals(whiteSquareDrawable);
                    }
                    else
                    {
                        connectedSquares = !activity.findViewById(belowID).getBackground().equals(blackSquareDrawable);
                    }

                    Drawable background = activity.findViewById(belowID).getBackground();

                    if(!connectedSquares)
                    {
                        squaresToFlip.add(activity.findViewById(belowID));
                    }
                    else
                    {
                        if(isBlacksTurn && background == blackSquareDrawable)
                        {
                            view.setBackground(blackSquareDrawable);
                            isLegalMove = true;
                            for(View button : squaresToFlip)
                            {
                                flipButton(button);
                            }
                        }
                        else if(!isBlacksTurn && background == whiteSquareDrawable)
                        {
                            view.setBackground(whiteSquareDrawable);
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

//        activity.findViewById(buttonList.get(16).getId()).setBackgroundResource(R.drawable.black_square);
        Log.d("isLegalMove", String.valueOf(isLegalMove));

        return isLegalMove;
    }

    public void flipButton(View view)
    {
        if(view.getBackground().equals(whiteSquareDrawable))
        {
            view.setBackground(blackSquareDrawable);
        }
        else if(view.getBackground().equals(blackSquareDrawable))
        {
            view.setBackground(whiteSquareDrawable);
        }
    }
}