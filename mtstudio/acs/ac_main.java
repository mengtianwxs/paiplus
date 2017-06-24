package mtstudio.acs;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import com.example.draw.R;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

import mtstudio.sql.DBSql;

public class ac_main extends Activity implements View.OnClickListener {

    public int screen_width = 0;
    public int screen_height = 0;
    public ArrayList<TextView> tvsets;
    private Iterator it;
    public SlidingMenu slidingMenu;

    public TextView tv_snake ;
    public TextView tv_cuan ;
    public TextView tv_leftE ;
    public TextView tv_rightE ;
    public TextView tv_TU;
    public TextView tv_TL ;
    public TextView tv_TZ ;
    public TextView tv_calc;
    public TextView tv_save ;
    public TextView tv_paidata ;
    public TextView tv_dataquery;
    public TextView tv_gyct;

    public static final String TextClickColor = "#009a61";

    /////////////////////////////////////////
    public static final String APP_ID = "100051508";
    public static final String SECRET_KEY = "559033926f9bd9d309547a69fb1b4e92";
    public static final String BANNER = "901d0511f66ff8bb901e4f7c339beaa6";
    /////////////////////////////////////////////


    public static final int tag_snake=0;
    public static final int tag_cuan=1;
    public static final int tag_lefte=2;
    public static final int tag_righte=3;
    public static final int tag_tl=4;
    public static final int tag_tu=5;
    public static final int tag_tz=6;

    public DBSql dbsql=null;
    private int i=0;
    private BroadcastReceiver receiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lo_main);

        /////////////////////////////////////////////////////////
       dbsql=new DBSql(ac_main.this,DBSql.dbname,null,DBSql.vers);

        //////////////////////////////////////////////////////////


        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        screen_width = displayMetrics.widthPixels;
        screen_height = displayMetrics.heightPixels;

        slidingMenu = new SlidingMenu(this);
        slidingMenu.setMode(SlidingMenu.LEFT);
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        slidingMenu.setBehindWidth(screen_width * 3 / 5);
        slidingMenu.setMenu(R.layout.lo_leftmenu);
        slidingMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);



        tv_snake = (TextView) findViewById(R.id.tv_snake);
        tv_cuan = (TextView) findViewById(R.id.tv_cuan);
        tv_leftE = (TextView) findViewById(R.id.tv_leftElephant);
        tv_rightE = (TextView) findViewById(R.id.tv_rightElephant);
        tv_TU = (TextView) findViewById(R.id.tv_zhijiaoUshape);
        tv_TL = (TextView) findViewById(R.id.tv_zhijiaoLshape);
        tv_TZ = (TextView) findViewById(R.id.tv_zhijiaoZshape);
        tv_calc= (TextView) findViewById(R.id.tv_calcdata);
        tv_save = (TextView) findViewById(R.id.tv_savedata);
        tv_paidata = (TextView) findViewById(R.id.tv_paidianliubiao);
        tv_dataquery = (TextView) findViewById(R.id.tv_dataquery);
        tv_gyct = (TextView) findViewById(R.id.tv_gyct);

        tv_cuan.setOnClickListener(this);
        tv_snake.setOnClickListener(this);
        tv_leftE.setOnClickListener(this);
        tv_rightE.setOnClickListener(this);
        tv_TU.setOnClickListener(this);
        tv_TL.setOnClickListener(this);
        tv_TZ.setOnClickListener(this);
        tv_calc.setOnClickListener(this);
        tv_save.setOnClickListener(this);
        tv_paidata.setOnClickListener(this);
        tv_dataquery.setOnClickListener(this);
        tv_gyct.setOnClickListener(this);



        tvsets=new ArrayList<TextView>();
        tvsets.add(tv_snake);
        tvsets.add(tv_cuan);
        tvsets.add(tv_leftE);
        tvsets.add(tv_rightE);
        tvsets.add(tv_TU);
        tvsets.add(tv_TL);
        tvsets.add(tv_TZ);
        tvsets.add(tv_calc);
        tvsets.add(tv_paidata);
        tvsets.add(tv_save);
        tvsets.add(tv_dataquery);
        tvsets.add(tv_gyct);
        //////////////////////////////////////////////////////////////////////////////////////////////
        //计算
        final TextView tv_colck1 = (TextView) findViewById(R.id.tv_colck1);
        final TextView tv_clock2 = (TextView) findViewById(R.id.tv_colck2);
        SimpleDateFormat sdf1 = new SimpleDateFormat("HH");
        final String date1 = sdf1.format(new Date());
        SimpleDateFormat sdf2=new SimpleDateFormat(" :  mm");
        final String date2 = sdf2.format(new Date());
        tv_colck1.setText(date1);
        tv_clock2.setText(date2);

        receiver=new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action=intent.getAction();
                if (action.equals(Intent.ACTION_TIME_TICK)) {

                    SimpleDateFormat sdf1 = new SimpleDateFormat("HH");
                    final String date1 = sdf1.format(new Date());
                    SimpleDateFormat sdf2=new SimpleDateFormat(" :  mm");
                    final String date2 = sdf2.format(new Date());
                    tv_colck1.setText(date1);
                    tv_clock2.setText(date2);
                    //Toast.makeText(ac_main.this,"oo",Toast.LENGTH_SHORT).show();
                }
            }
        };

        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_TIME_TICK);
        registerReceiver(receiver, filter);



//showInputMethod();

    }


    public void getZDMethod(){
        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(400);
    }

public void showInputMethod() {
    Timer timer=new Timer();
    timer.schedule(new TimerTask() {
        @Override
        public void run() {
            InputMethodManager im = (InputMethodManager)getSystemService (Context.INPUT_METHOD_SERVICE);
            im.toggleSoftInput(0,InputMethodManager.SHOW_FORCED);
        }
    },200);
}

    public void initTextView() {

        it=tvsets.iterator();

        while (it.hasNext()) {
            TextView tv = (TextView) it.next();
            tv.setTextColor(Color.parseColor("#000000"));
           // tv.setClickable(true);


//            if(tv.getId()==tag){
//                tv.setTextColor(Color.RED);
//                //Toast.makeText(this, "this is click", Toast.LENGTH_LONG).show();
//            }
        }
    }
    public  int loop(int _step)
    {

        i=(i+(_step-1))%_step;
        return _step-(i+1);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == tv_snake.getId()){
            Intent intent_snake=new Intent(ac_main.this,ac_snake.class);
            startActivity(intent_snake);
            //Toast.makeText(this,"click snake",Toast.LENGTH_LONG).show();



        }else if(v.getId()==tv_cuan.getId())
        {
            Intent intent_cuan=new Intent(ac_main.this,ac_cuan.class);
            startActivity(intent_cuan);
           // Toast.makeText(this,"click cuan",Toast.LENGTH_LONG).show();

        }
        else if(v.getId()==tv_leftE.getId())
        {
            Intent intent_lefte=new Intent(ac_main.this,ac_leftE.class);
            startActivity(intent_lefte);
            //Toast.makeText(this,"click cuan",Toast.LENGTH_LONG).show();

        }
        else if(v.getId()==tv_rightE.getId())
        {
            Intent intent_righte=new Intent(ac_main.this,ac_rightE.class);
            startActivity(intent_righte);
            //Toast.makeText(this,"click cuan",Toast.LENGTH_LONG).show();

        }
        else if(v.getId()==tv_TL.getId())
        {
            Intent intent_tl=new Intent(ac_main.this,ac_tl.class);
            startActivity(intent_tl);
            //Toast.makeText(this,"click cuan",Toast.LENGTH_LONG).show();

        }
        else if(v.getId()==tv_TZ.getId())
        {
            Intent intent_tz=new Intent(ac_main.this,ac_tz.class);
            startActivity(intent_tz);
//            Toast.makeText(this,"click cuan",Toast.LENGTH_LONG).show();

        }
        else if(v.getId()==tv_TU.getId())
        {
            Intent intent_tu=new Intent(ac_main.this,ac_tu.class);
            startActivity(intent_tu);
//            Toast.makeText(this,"click cuan",Toast.LENGTH_LONG).show();

        }
        else if(v.getId()==tv_save.getId())
        {
//            Intent intent_save=new Intent(ac_main.this,ac_save.class);
//            startActivity(intent_save);
//            Toast.makeText(this,"click cuan",Toast.LENGTH_LONG).show();
           // Toast.makeText(this,"Sorror ,this function is building !",Toast.LENGTH_SHORT).show();


        }
        else if(v.getId()==tv_calc.getId())
        {
           Intent intent_calc=new Intent(ac_main.this,ac_calc.class);
           startActivity(intent_calc);
          // Toast.makeText(this,"click cuan",Toast.LENGTH_LONG).show();
            //Toast.makeText(this,"Sorror ,this function is building !",Toast.LENGTH_SHORT).show();


        }
        else if(v.getId()==tv_dataquery.getId())
        {
            //Intent intent_dataquery=new Intent(ac_main.this,ac_dataquery.class);
           // startActivity(intent_dataquery);
           // Toast.makeText(this,"click cuan",Toast.LENGTH_LONG).show();
            Toast.makeText(this,"Sorror ,this function is building !",Toast.LENGTH_SHORT).show();


        }
        else if(v.getId()==tv_paidata.getId())
        {
            Intent intent_data=new Intent(ac_main.this,ac_paidata.class);
            startActivity(intent_data);
//            Toast.makeText(this,"click cuan",Toast.LENGTH_LONG).show();

        } else if (v.getId() == tv_gyct.getId()) {
            Intent intent_gyct=new Intent(ac_main.this,ac_paigaoya.class);
            startActivity(intent_gyct);
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }
}
