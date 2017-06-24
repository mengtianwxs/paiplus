package com.example.draw;

import android.app.Activity;
import android.os.Bundle;

import andmt.draw.AcSnakeBar;

public class ac_draw extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.lo_draw);

        AcSnakeBar acSnakeBar=new AcSnakeBar(this,100,200,200,500,true);
        acSnakeBar.startDraw();
        setContentView(acSnakeBar);


    }
}
