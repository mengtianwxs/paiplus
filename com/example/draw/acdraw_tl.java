package com.example.draw;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

import andmt.draw.AcLBar;

public class acdraw_tl extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.lo_tll);
        Bundle bundle=getIntent().getExtras();
        float xz=bundle.getFloat("xz");
        float yz = bundle.getFloat("yz");

        AcLBar acLBar = new AcLBar(this, true, yz, xz, 0);
       // acLBar.move(100, 100);
        acLBar.startDraw();
        acLBar.setBackgroundColor(Color.WHITE);
        setContentView(acLBar);

    }
}
