package com.example.draw;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

import andmt.draw.AcZBar;

public class acdraw_tz extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.lo_tzz);

        Bundle bundle=getIntent().getExtras();
        float za=bundle.getFloat("za");
        float zb = bundle.getFloat("zb");
        float zc = bundle.getFloat("zc");

        AcZBar acZBar = new AcZBar(this, za, zb, zc, true);
        //acZBar.move(100, 100);
        acZBar.startDraw();
        acZBar.setBackgroundColor(Color.WHITE);
        setContentView(acZBar);
    }
}
