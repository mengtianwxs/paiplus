package mtstudio.acs;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.draw.R;
import com.example.draw.acdraw_cuan;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.wandoujia.ads.sdk.Ads;

public class ac_cuan extends ac_main  {

    private EditText et_sw;
    private EditText et_zw;
    private EditText et_xw;
    private EditText et_w1;
    private EditText et_w2;
    private EditText et_H;
    private EditText et_h1;
    private EditText et_zw1;
    private EditText et_zw2;

    private String str_zw;
    private String str_sw;
    private String str_xw;
    private String str_w1;
    private String str_w2;
    private String str_H;
    private String str_h1;
    private String str_zw1;
    private String str_zw2;

    private TextView tv_info;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lo_cuan);



        //////////////////////////////////////////////////////////////////////
        new AsyncTask<Void, Void, Boolean>() {
            @Override
            protected Boolean doInBackground(Void... params) {
                try {

                    Ads.init(ac_cuan.this, APP_ID, SECRET_KEY);
                    return true;
                } catch (Exception e) {
                   // Log.e("ads-sample", "error", e);
                    return false;
                }
            }

            @Override
            protected void onPostExecute(Boolean success) {
                final ViewGroup container = (ViewGroup) findViewById(R.id.ll_ad_cuan);

                if (success) {
                    /**
                     * pre load
                     */
                    Ads.preLoad(BANNER, Ads.AdFormat.banner);

                    /**
                     * add ad views
                     */
                    View bannerView = Ads.createBannerView(ac_cuan.this, BANNER);
                    container.addView(bannerView, new ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT
                    ));

//
                } else {

                }
            }
        }.execute();

        //////////////////////////////////////////////////////////////////////

        /////////////////////////////////////////////////////////////////////
        slidingMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);


       initTextView();
        tv_cuan.setTextColor(Color.parseColor(TextClickColor));

        /////////////////////////////////////////////////////////////////////

        et_sw = (EditText) findViewById(R.id.etcuan_shangwan);
        et_zw = (EditText) findViewById(R.id.etcuan_zhongwan);
        et_xw = (EditText) findViewById(R.id.etcuan_xiawan);
        et_w1 = (EditText) findViewById(R.id.etcuan_toukuan);
        et_w2 = (EditText) findViewById(R.id.etcuan_weikuan);
        et_H = (EditText) findViewById(R.id.etcuan_H);
        et_h1=(EditText)findViewById(R.id.etcuan_h1);
        et_zw1=(EditText)findViewById(R.id.etzw1);
        et_zw2 = (EditText) findViewById(R.id.etzw2);


        str_sw=et_sw.getText().toString();
        str_zw=et_zw.getText().toString();
        str_xw=et_xw.getText().toString();
        str_w1=et_w1.getText().toString();
        str_w2=et_w2.getText().toString();
        str_H=et_H.getText().toString();
        str_h1=et_h1.getText().toString();
        str_zw1=et_zw1.getText().toString();
        str_zw2=et_zw2.getText().toString();
        ///////////////////////////////////////////////////////
        et_sw .setText("");
        et_zw .setText("");
        et_xw .setText("");
        et_w1 .setText("");
        et_w2 .setText("");
        et_H .setText("");
        et_h1.setText("");
        et_zw1.setText("");
        et_zw2 .setText("");

        et_sw.setFocusable(true);
        et_sw.requestFocus();
        showInputMethod();
        ///////////////////////////////////////////////////////
        tv_info=(TextView)findViewById(R.id.tv_cuaninfo);
        tv_info.setTextColor(Color.parseColor("#13b5b1"));
        tv_info.setText("[@Point1]: 0\n[@Point2]: 0\n[@Point3]: 0\n[@Point4]: 0\n[@Point5]: 0\n[@Point6]: 0\n[@@Total]: 0");
        ///////////////////////////////////////////////////////
        et_sw .setInputType(EditorInfo.TYPE_CLASS_PHONE);
        et_zw .setInputType(EditorInfo.TYPE_CLASS_PHONE);
        et_xw .setInputType(EditorInfo.TYPE_CLASS_PHONE);
        et_w1 .setInputType(EditorInfo.TYPE_CLASS_PHONE);
        et_w2 .setInputType(EditorInfo.TYPE_CLASS_PHONE);
        et_H .setInputType(EditorInfo.TYPE_CLASS_PHONE);
        et_h1.setInputType(EditorInfo.TYPE_CLASS_PHONE);
        et_zw1.setInputType(EditorInfo.TYPE_CLASS_PHONE);
        et_zw2 .setInputType(EditorInfo.TYPE_CLASS_PHONE);
//////////////////////////////////////////////////////////////////////////////////////////////
        Button btn_clear = (Button) findViewById(R.id.btn_cleardata);
        Button btn_last = (Button) findViewById(R.id.btn_lastdata);
        Button btn_calc = (Button) findViewById(R.id.btn_calcpai);
        Button btn_draw = (Button) findViewById(R.id.btn_drawpai);

        ////////////////////////////////////////////////////////////////////////////////////
        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_sw .setText("");
                et_zw .setText("");
                et_xw .setText("");
                et_w1 .setText("");
                et_w2 .setText("");
                et_H .setText("");
                et_h1.setText("");
                et_zw1.setText("");
                et_zw2 .setText("");
                tv_info.setText("[@Point1]: 0\n[@Point2]: 0\n[@Point3]: 0\n[@Point4]: 0\n[@Point5]: 0\n[@Point6]: 0\n[@@Total]: 0");

                et_sw.setFocusable(true);
                et_sw.requestFocus();
                showInputMethod();
            }
        });




        ////////////////////////////////////////////////////////////////////////////////////
        btn_calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str_sw=et_sw.getText().toString();
                str_zw=et_zw.getText().toString();
                str_xw=et_xw.getText().toString();
                str_w1=et_w1.getText().toString();
                str_w2=et_w2.getText().toString();
                str_H=et_H.getText().toString();
                str_h1=et_h1.getText().toString();
                str_zw1=et_zw1.getText().toString();
                str_zw2=et_zw2.getText().toString();
                if(!str_sw.equals("") && !str_zw.equals("") && !str_xw.equals("") && !str_H.equals("") && !str_h1.equals("") && !str_w1.equals("") && !str_w2.equals(""))
                {
                    if(str_zw1.equals(""))
                    {
                        str_zw1="0";
                    }
                    if(str_zw2.equals("")){
                        str_zw2="0";
                    }

                    float a,b,c,d,e,h2;
                    float l_sw,l_zw,l_xw,l_h1,l_h,l_w1,l_w2,l_zw1,l_zw2;
                    l_sw = Float.parseFloat(str_sw);
                    l_xw = Float.parseFloat(str_xw);
                    l_zw = Float.parseFloat(str_zw);
                    l_h1 = Float.parseFloat(str_h1);
                    l_h = Float.parseFloat(str_H);
                    l_w1 = Float.parseFloat(str_w1);
                    l_w2 = Float.parseFloat(str_w2);
                    l_zw1 = Float.parseFloat(str_zw1);
                    l_zw2 = Float.parseFloat(str_zw2);
                    h2=l_h-l_sw-l_zw-l_h1-l_xw;

                    a=l_sw;
                    b=(float) Math.sqrt(Double.parseDouble(Float.toString(l_h1*l_h1+l_w1*l_w1)))+l_zw1;
                    c=l_zw;
                    d=(float) Math.sqrt(Double.parseDouble(Float.toString(h2*h2+l_w2*l_w2)))+l_zw2;
                    e=l_xw;

                    java.text.DecimalFormat decimalFormat = new java.text.DecimalFormat("0.00");


                    String p1 = decimalFormat.format(0.00);
                    String p2=decimalFormat.format(a);
                    String p3 = decimalFormat.format(a+b);
                    String p4 = decimalFormat.format(a+b+c);
                    String p5 = decimalFormat.format(a+b+c+d);
                    String p6 = decimalFormat.format(a+b+c+d+e);
                    String t=p6;

                    tv_info.setText("[@Point1]: "+p1+"\n[@Point2]: "+p2+"\n[@Point3]: "+p3+"\n[@Point4]: "+p4+"\n[@Point5]: "+p5+"\n[@Point6]: "+p6+"\n[@@Total]: "+t);



                }else {
                    Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibrator.vibrate(400);
                    Toast.makeText(ac_cuan.this,"value cannot null",Toast.LENGTH_SHORT).show();
                }

            }
        });
///////////////////////////////////////////////////////////////////////////////////
        btn_draw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                str_sw=et_sw.getText().toString();
                str_zw=et_zw.getText().toString();
                str_xw=et_xw.getText().toString();
                str_w1=et_w1.getText().toString();
                str_w2=et_w2.getText().toString();
                str_H=et_H.getText().toString();
                str_h1=et_h1.getText().toString();

                if(!str_sw.equals("") && !str_zw.equals("") && !str_xw.equals("") && !str_H.equals("") && !str_h1.equals("") && !str_w1.equals("") && !str_w2.equals(""))
                {
                float l_sw,l_zw,l_xw,l_h1,l_h,l_w1,l_w2;
                l_sw = Float.parseFloat(str_sw);
                l_xw = Float.parseFloat(str_xw);
                l_zw = Float.parseFloat(str_zw);
                l_h1 = Float.parseFloat(str_h1);
                l_h = Float.parseFloat(str_H);
                l_w1 = Float.parseFloat(str_w1);
                l_w2 = Float.parseFloat(str_w2);


                Intent intent = new Intent(ac_cuan.this, acdraw_cuan.class);
                intent.putExtra("sw",l_sw);
                intent.putExtra("zw",l_zw);
                intent.putExtra("xw",l_xw);
                intent.putExtra("H",l_h);
                intent.putExtra("h1",l_h1);
                intent.putExtra("w1",l_w1);
                intent.putExtra("w2",l_w2);
                startActivity(intent);
                }else
                {
                    Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibrator.vibrate(400);
                    Toast.makeText(ac_cuan.this,"cannot null",Toast.LENGTH_SHORT).show();
                }

            }
        });
///////////////////////////////////////////////////////////////////////////////////
        btn_last.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SQLiteDatabase db=dbsql.getReadableDatabase();
                Cursor c=db.query("tb_cuan",null,null,null,null,null,null);

                if(c.getCount()>0) {
                    c.moveToFirst();
                    int ic = loop(c.getCount());


                    Toast.makeText(ac_cuan.this, "Total : " +(ic+1)+" / "+ c.getCount(), Toast.LENGTH_SHORT).show();

                    c.move(ic);
                    String c1 = c.getString(1);
                    String c2=c.getString(2);
                    String c3 = c.getString(3);
                    String c4= c.getString(4);
                    String c5= c.getString(5);
                    String c6= c.getString(6);
                    String c7= c.getString(7);
                    String c8= c.getString(8);
                    String c9= c.getString(9);
                    et_sw .setText(c1);
                    et_zw .setText(c2);
                    et_xw .setText(c3);
                    et_w1 .setText(c4);
                    et_w2 .setText(c5);
                    et_H .setText(c6);
                    et_h1.setText(c7);
                    et_zw1.setText(c8);
                    et_zw2 .setText(c9);
                }else {
                    getZDMethod();
                    Toast.makeText(ac_cuan.this, "no datas !", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }

    @Override
    public void onClick(View v) {
       // finish();
        super.onClick(v);
        if (v.getId() == tv_save.getId()) {


            str_sw=et_sw.getText().toString();
            str_zw=et_zw.getText().toString();
            str_xw=et_xw.getText().toString();
            str_w1=et_w1.getText().toString();
            str_w2=et_w2.getText().toString();
            str_H=et_H.getText().toString();
            str_h1=et_h1.getText().toString();
            str_zw1=et_zw1.getText().toString();
            str_zw2=et_zw2.getText().toString();
            if(!str_sw.equals("") && !str_zw.equals("") && !str_xw.equals("") && !str_H.equals("") && !str_h1.equals("") && !str_w1.equals("") && !str_w2.equals(""))
            {

                if(str_zw1.equals("")){
                    str_zw1="0";
                }
                if(str_zw2.equals("")){
                    str_zw2="0";
                }

                SQLiteDatabase db = dbsql.getWritableDatabase();
                String sql = "insert into tb_cuan(c1,c2,c3,c4,c5,c6,c7,c8,c9) values(" + str_sw + "," + str_zw + "," + str_xw + ","+ str_w1 + ","+ str_w2 + ","+ str_H + ","+ str_h1 + "," + str_zw1 + "," + str_zw2 + ")";
                db.execSQL(sql);
                db.close();
                Toast.makeText(this,"insert cuan pai success !",Toast.LENGTH_SHORT).show();
            }
            else {
                getZDMethod();
                Toast.makeText(ac_cuan.this, "insert cuan pai faild !", Toast.LENGTH_SHORT).show();
            }
        }



    }
}
