package com.example.reversi;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.HashMap;

public class ReversiButton extends androidx.appcompat.widget.AppCompatButton
{
    private int xCoor;
    private int yCoor;

    public ReversiButton(@NonNull Context context)
    {
        super(context);
    }

    public ReversiButton(@NonNull Context context, int xCoor, int yCoor)
    {
        super(context);
        this.xCoor = xCoor;
        this.yCoor = yCoor;
    }

    public int getXCoor()
    {
        return xCoor;
    }

    public void setXCoor(int xCoor)
    {
        this.xCoor = xCoor;
    }

    public int getYCoor()
    {
        return yCoor;
    }

    public void setYCoor(int yCoor)
    {
        this.yCoor = yCoor;
    }

    public void setXYcoor(int xCoor, int yCoor)
    {
        this.xCoor = xCoor;
        this.yCoor = yCoor;
    }

    public String printCoor()
    {
        return ("x: " + xCoor + ", y: " + yCoor);
    }
}
