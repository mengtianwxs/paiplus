package mtstudio.acs;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.draw.R;
import com.example.draw.acdraw_tu;
import com.example.draw.acdraw_tz;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.wandoujia.ads.sdk.Ads;

public class ac_tz extends ac_main {
    private EditText et_zl;
    private EditText et_zb;
    private EditText et_zr;
    private EditText et_zx;

    private TextView tv_tzinfo;

    private String str_zl;
    private String str_zb;
    private String str_zr;
    private String str_zx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lo_tz);



        //////////////////////////////////////////////////////////////////////
        new AsyncTask<Void, Void, Boolean>() {
            @Override
            protected Boolean doInBackground(Void... params) {
                try {

                    Ads.init(ac_tz.this, APP_ID, SECRET_KEY);
                    return true;
                } catch (Exception e) {
                   // Log.e("ads-sample", "error", e);
                    return false;
                }
            }

            @Override
            protected void onPostExecute(Boolean success) {
                final ViewGroup container = (ViewGroup) findViewById(R.id.ll_ads_tz);

                if (success) {
                    /**
                     * pre load
                     */
                    Ads.preLoad(BANNER, Ads.AdFormat.banner);

                    /**
                     * add ad views
                     */
                    View bannerView = Ads.createBannerView(ac_tz.this, BANNER);
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

        //初始化操作
        slidingMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);


        initTextView();
        tv_TZ.setTextColor(Color.parseColor(TextClickColor));

        //////////////////////////////////////////////////////////////////////

        et_zl=(EditText)findViewById(R.id.et_zleft);
        et_zr=(EditText)findViewById(R.id.et_zright);
        et_zb=(EditText)findViewById(R.id.et_zbottom);
        et_zx=(EditText)findViewById(R.id.et_zX);

        tv_tzinfo = (TextView) findViewById(R.id.tv_tzinfo);
        tv_tzinfo.setTextColor(Color.parseColor("#13b5b1"));
        tv_tzinfo.setText("[@Point1]: "+0+"\n[@Point2]: "+0+"\n[@Point3]: "+0+"\n[@Point4]: "+0+"\n[@@Total]: 0");


        et_zl.setInputType(EditorInfo.TYPE_CLASS_PHONE);
        et_zr.setInputType(EditorInfo.TYPE_CLASS_PHONE);
        et_zb.setInputType(EditorInfo.TYPE_CLASS_PHONE);
        et_zx.setInputType(EditorInfo.TYPE_CLASS_PHONE);

        et_zl.setFocusable(true);
        et_zl.requestFocus();
        //打开键盘默认在父类中
        showInputMethod();


        /////////////////////////////////////////////////////////////////

        Button btn_clear = (Button) findViewById(R.id.btn_cleardata);
        Button btn_last = (Button) findViewById(R.id.btn_lastdata);
        Button btn_calc = (Button) findViewById(R.id.btn_calcpai);
        Button btn_draw = (Button) findViewById(R.id.btn_drawpai);

        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_zl.setText("");
                et_zr.setText("");
                et_zb.setText("");
                et_zx.setText("");
                tv_tzinfo.setText("[@Point1]: "+0+"\n[@Point2]: "+0+"\n[@Point3]: "+0+"\n[@Point4]: "+0+"\n[@@Total]: 0");

                et_zl.setFocusable(true);
                et_zl.requestFocus();
                showInputMethod();
            }
        });
/////////////////////////////////////////////////////////////////////////////////
        btn_calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getValueForEditText();
                if (str_zl.equals("") && str_zr.equals("") && str_zb.equals("")) {
                    Toast.makeText(ac_tz.this,"value cannot null",Toast.LENGTH_SHORT).show();
                }else {
                    float a,b,c,x;
                    a=Float.parseFloat(str_zl);
                    b = Float.parseFloat(str_zb);
                    c = Float.parseFloat(str_zr);
                    x = Float.parseFloat(str_zx);

                    java.text.DecimalFormat decimalFormat = new java.text.DecimalFormat("0.00");

                    String p1=decimalFormat.format(0);
                    String p2=decimalFormat.format(a);
                    String p3=decimalFormat.format(a+b+x);
                    String p4=decimalFormat.format(a+b+x+c);


                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(et_zx.getWindowToken(), 0);
                    imm.hideSoftInputFromInputMethod(et_zr.getWindowToken(),0);

                    tv_tzinfo.setText("[@Point1]: "+p1+"\n[@Point2]: "+p2+"\n[@Point3]: "+p3+"\n[@Point4]: "+p4+"\n[@@Total]: "+p4);
                }
            }
        });
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        btn_draw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getValueForEditText();
                if (str_zl.equals("") && str_zr.equals("") && str_zb.equals("")) {
                    Toast.makeText(ac_tz.this,"value cannot null",Toast.LENGTH_SHORT).show();
                }else {

                    float za,zb,zc;
                    za=Float.parseFloat(str_zl);
                    zb = Float.parseFloat(str_zb);
                    zc = Float.parseFloat(str_zr);

                    Intent intent = new Intent(ac_tz.this, acdraw_tz.class);
                    intent.putExtra("za", za);
                    intent.putExtra("zb", zb);
                    intent.putExtra("zc", zc);
                    startActivity(intent);

                }

            }
        });
////////////////////////////////////////////////////////////////

        btn_last.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db=dbsql.getReadableDatabase();
                Cursor c=db.query("tb_tz",null,null,null,null,null,null);

                if(c.getCount()>0) {
                    c.moveToFirst();
                    int ic = loop(c.getCount());


                    Toast.makeText(ac_tz.this, "Total : " +(ic+1)+" / "+ c.getCount(), Toast.LENGTH_SHORT).show();

                    c.move(ic);
                    String ul = c.getString(1);
                    String ub = c.getString(2);
                    String ur = c.getString(3);
                    String ux = c.getString(4);


                    et_zl.setText(ul);
                    et_zr.setText(ur);
                    et_zb.setText(ub);
                    et_zx.setText(ux);

                }else {
                    getZDMethod();
                    Toast.makeText(ac_tz.this, "no datas !", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    private void getValueForEditText() {
        str_zl=et_zl.getText().toString();
        str_zr=et_zr.getText().toString();
        str_zx=et_zx.getText().toString();
        str_zb=et_zb.getText().toString();

        if (str_zx.equals("")) {
            str_zx = "0";
        }
    }

    @Override
    public void onClick(View v) {
      //  finish();
        super.onClick(v);

        if (v.getId() == tv_save.getId()) {

            str_zl=et_zl.getText().toString();
            str_zr=et_zr.getText().toString();
            str_zx=et_zx.getText().toString();
            str_zb=et_zb.getText().toString();


            if (!str_zl.equals("") && !str_zr.equals("") && !str_zb.equals("")) {
                if (str_zx.equals("")) {
                    str_zx = "0";
                }

                SQLiteDatabase db = dbsql.getWritableDatabase();
                String sql = "insert into tb_tz(tz1,tz2,tz3,tz4) values("  + str_zl + "," + str_zb + "," + str_zr + "," + str_zx + ")";
                db.execSQL(sql);
                db.close();
                Toast.makeText(this,"insert tz pai success !",Toast.LENGTH_SHORT).show();
            }else {
                getZDMethod();
                Toast.makeText(this, "insert tz pai faild !", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
