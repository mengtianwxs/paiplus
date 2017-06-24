package com.example.draw;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import mtstudio.acs.ac_snake;
import mtstudio.appintros.Appintros;

public class ac_start extends Activity {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private int m=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lo_start);

        TextView tv = (TextView) findViewById(R.id.tv_start0);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/fzjl.ttf");
        tv.setTypeface(typeface);



      //  m = getPrefs.getInt("firstnum", 0);

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                //  Initialize SharedPreferences
                SharedPreferences getPrefs = PreferenceManager
                        .getDefaultSharedPreferences(getBaseContext());

                //  Create a new boolean and preference and set it to true
                boolean isFirstStart = getPrefs.getBoolean("firstStart", true);
                m = getPrefs.getInt("firstnum", 0);

                //  If the activity has never started before...
                if (isFirstStart) {

                    //  Launch app intro
                    Intent intent = new Intent(ac_start.this, Appintros.class);
                    startActivity(intent);




                    //  Make a new preferences editor
                    SharedPreferences.Editor e = getPrefs.edit();

                    //  Edit preference to make it false because we don't want this to run again
                    e.putBoolean("firstStart", false);
                    e.putInt("firstnum", 1);


                    //  Apply changes
                    e.apply();
                }
            }
        });

// Start the thread
       t.start();

//        Intent intent = new Intent(ac_start.this, Appintros.class);
//        startActivity(intent);



        SharedPreferences getPrefs = PreferenceManager
                .getDefaultSharedPreferences(getBaseContext());
        m = getPrefs.getInt("firstnum", 0);

        if (m == 1) {


            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    Intent intent = new Intent(ac_start.this, ac_snake.class);
                    startActivity(intent);
                    finish();
                }
            }, 2500);

//设置新字体


        }

    }




    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}