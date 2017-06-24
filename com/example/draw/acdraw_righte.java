package com.example.draw;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

import andmt.draw.AcLeftElephantBar;
import andmt.draw.AcRightElephantBar;

public class acdraw_righte extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.lo_rightee);

        Bundle bundle=getIntent().getExtras();
        float sh=bundle.getFloat("sh");
        float w=bundle.getFloat("w");
        float h=bundle.getFloat("h");
        float xh=bundle.getFloat("xh");
        float bh=bundle.getFloat("bh");

        AcRightElephantBar acRightElephantBar = new AcRightElephantBar(this, sh, h, w, xh, bh, true);

       acRightElephantBar.startDraw();
        //acRightElephantBar.move(100, 100);
        acRightElephantBar.setBackgroundColor(Color.WHITE);
        setContentView(acRightElephantBar);

    }
}
