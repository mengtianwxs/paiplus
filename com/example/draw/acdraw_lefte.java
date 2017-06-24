package com.example.draw;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

import andmt.draw.AcLeftElephantBar;

/**
 * Created by mengtianwxs on 2017/5/12.
 */

public class acdraw_lefte extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle=getIntent().getExtras();
        float sh=bundle.getFloat("sh");
        float w=bundle.getFloat("w");
        float h=bundle.getFloat("h");
        float xh=bundle.getFloat("xh");
        float bh=bundle.getFloat("bh");

        AcLeftElephantBar acLeftElephantBar = new AcLeftElephantBar(this, sh, h, w, xh, bh, true);
        acLeftElephantBar.startDraw();
        //acLeftElephantBar.move(100,100);
acLeftElephantBar.setBackgroundColor(Color.WHITE);
        setContentView(acLeftElephantBar);



    }
}
