package com.example.draw;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

import andmt.draw.AcUBar;

public class acdraw_tu extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.lo_tuu);

        Bundle bundle=getIntent().getExtras();
        float ua=bundle.getFloat("ua");
        float ub = bundle.getFloat("ub");
        float uc = bundle.getFloat("uc");

        AcUBar acUBar = new AcUBar(this, ua, ub, uc, true);
        //acUBar.move(100, 100);

        acUBar.startDraw();
        acUBar.setBackgroundColor(Color.WHITE);
        setContentView(acUBar);

    }
}
