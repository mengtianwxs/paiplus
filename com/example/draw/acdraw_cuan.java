package com.example.draw;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import andmt.draw.AcShipBar;
import mtstudio.acs.ac_cuan;

/**
 * Created by mengtianwxs on 2017/5/12.
 */

public class acdraw_cuan extends Activity {
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Toast.makeText(acdraw_cuan.this,"cuan draw",Toast.LENGTH_SHORT).show();
        Bundle bundle=getIntent().getExtras();
        float sw=bundle.getFloat("sw");
        float zw=bundle.getFloat("zw");
        float xw=bundle.getFloat("xw");
        float w1=bundle.getFloat("w1");
        float w2=bundle.getFloat("w2");
        float H=bundle.getFloat("H");
        float h1=bundle.getFloat("h1");
        AcShipBar acShipBar=new AcShipBar(this,sw,zw,xw,w1,w2,H,h1,true);
        acShipBar.startDraw();
        acShipBar.setBackgroundColor(Color.WHITE);
        //acShipBar.move(100,100);
        setContentView(acShipBar);

    }
}
