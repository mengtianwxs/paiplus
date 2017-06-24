package mtstudio.appintros;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import com.example.draw.R;
import com.example.draw.ac_start;
import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;

import mtstudio.acs.ac_snake;

/**
 * Created by mt on 6/20/17.
 */
public class Appintros extends AppIntro {
    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        TextView tv=(TextView)findViewById(R.id.tvappinfo);
//        tv.setMovementMethod(new ScrollingMovementMethod());



       addSlide(SampleSlide.newInstance(R.layout.lo_slidetwo));
       // addSlide(AppIntroFragment.newInstance("","little tip! app向右滑动会出现菜单功能",R.drawable.app,Color.parseColor("#f1f1f1")));
        addSlide(SampleSlide.newInstance(R.layout.lo_slidethree));
        addSlide(SampleSlide.newInstance(R.layout.lo_slidefour));
        addSlide(SampleSlide.newInstance(R.layout.lo_slideone));

        // setFadeAnimation();
        setDoneText("结束");


        // Override bar/separator color
        setBarColor(Color.parseColor("#009a61"));
        setSeparatorColor(Color.parseColor("#ffffff"));

        // SHOW or HIDE the statusbar
        showStatusBar(true);

        // Edit the color of the nav bar on Lollipop+ devices

       // setFadeAnimation();
        showSkipButton(false);
        //setProgressIndicator();
        //askForPermissions(new String[]{Manifest.permission.CAMERA}, 4);

    }





    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        //finish();
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);


        Intent intent = new Intent(Appintros.this, ac_start.class);
        startActivity(intent);
        finish();

//
//        Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//
//                Intent intent = new Intent(Appintros.this, ac_snake.class);
//                startActivity(intent);
//                finish();
//
//
//            }
//        }, 2500);

    }


    @Override
    public void onSlideChanged(@Nullable Fragment oldFragment, @Nullable Fragment newFragment) {
        super.onSlideChanged(oldFragment, newFragment);
        // finish();

    }
}
