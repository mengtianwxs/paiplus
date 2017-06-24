package com.example.draw;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import andmt.draw.AcSnakeBar;

public class acdraw_snake extends Activity {

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.lo_draw);


        Bundle bundle=getIntent().getExtras();
        float shangwan=Float.parseFloat(bundle.getString("shangwan"));
        float xiawan=Float.parseFloat(bundle.getString("xiawan"));
        float height=Float.parseFloat(bundle.getString("height"));
        float width=Float.parseFloat(bundle.getString("width"));
       // float x=Float.parseFloat(bundle.getString("x"));

      //  Toast.makeText(this,"x is "+x,Toast.LENGTH_LONG).show();


        AcSnakeBar acSnakeBar=new AcSnakeBar(this,shangwan,xiawan,width,height,true);
        acSnakeBar.startDraw();
        acSnakeBar.setBackgroundColor(getColor(R.color.acbgcolor));

        //acSnakeBar.move(100,100);
        setContentView(acSnakeBar);


    }
}
