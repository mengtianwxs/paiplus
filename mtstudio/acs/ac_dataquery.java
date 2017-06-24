package mtstudio.acs;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.draw.R;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

public class ac_dataquery extends ac_main {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lo_dataquery);
        //Toast.makeText(this,"Sorror ,this function is building !",Toast.LENGTH_SHORT).show();

        //初始化操作
        slidingMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);


        initTextView();
        tv_dataquery.setTextColor(Color.parseColor("#ff6d00"));


        //////////////////////////////////////////////////////////////////////

        TextView tv_query = (TextView)findViewById(R.id.tvqudata);
        tv_query.setBackgroundResource(R.drawable.corner_viewquerydata);
        tv_query.setMovementMethod(ScrollingMovementMethod.getInstance());


    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
    }
}
