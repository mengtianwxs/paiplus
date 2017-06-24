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
import com.example.draw.acdraw_tl;
import com.example.draw.acdraw_tu;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.wandoujia.ads.sdk.Ads;

public class ac_tu extends ac_main {

    private EditText et_ul;
    private EditText et_ub;
    private EditText et_ur;
    private EditText et_ux;

    private TextView tv_tuinfo;

    private String str_ul;
    private String str_ub;
    private String str_ur;
    private String str_ux;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lo_tu);



        //////////////////////////////////////////////////////////////////////
        new AsyncTask<Void, Void, Boolean>() {
            @Override
            protected Boolean doInBackground(Void... params) {
                try {

                    Ads.init(ac_tu.this, APP_ID, SECRET_KEY);
                    return true;
                } catch (Exception e) {
                   // Log.e("ads-sample", "error", e);
                    return false;
                }
            }

            @Override
            protected void onPostExecute(Boolean success) {
                final ViewGroup container = (ViewGroup) findViewById(R.id.ll_ads_tu);

                if (success) {
                    /**
                     * pre load
                     */
                    Ads.preLoad(BANNER, Ads.AdFormat.banner);

                    /**
                     * add ad views
                     */
                    View bannerView = Ads.createBannerView(ac_tu.this, BANNER);
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
        tv_TU.setTextColor(Color.parseColor(TextClickColor));

        //////////////////////////////////////////////////////////////////////

        et_ul=(EditText)findViewById(R.id.et_uleft);
        et_ur=(EditText)findViewById(R.id.et_uright);
        et_ub=(EditText)findViewById(R.id.et_ubottom);
        et_ux=(EditText)findViewById(R.id.et_uX);

        tv_tuinfo = (TextView) findViewById(R.id.tv_tuinfo);
        tv_tuinfo.setTextColor(Color.parseColor("#13b5b1"));
        tv_tuinfo.setText("[@Point1]: "+0+"\n[@Point2]: "+0+"\n[@Point3]: "+0+"\n[@Point4]: "+0+"\n[@@Total]: 0");


        et_ul.setInputType(EditorInfo.TYPE_CLASS_PHONE);
        et_ur.setInputType(EditorInfo.TYPE_CLASS_PHONE);
        et_ub.setInputType(EditorInfo.TYPE_CLASS_PHONE);
        et_ux.setInputType(EditorInfo.TYPE_CLASS_PHONE);

        et_ul.setFocusable(true);
        et_ul.requestFocus();
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
                et_ul.setText("");
                et_ur.setText("");
                et_ub.setText("");
                et_ux.setText("");
                tv_tuinfo.setText("[@Point1]: "+0+"\n[@Point2]: "+0+"\n[@Point3]: "+0+"\n[@Point4]: "+0+"\n[@@Total]: 0");

                et_ul.setFocusable(true);
                et_ul.requestFocus();
                showInputMethod();
            }
        });
/////////////////////////////////////////////////////////////////////////////////
        btn_calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getValueForEditText();
                if (str_ul.equals("") && str_ur.equals("") && str_ub.equals("")) {
                    Toast.makeText(ac_tu.this,"value cannot null",Toast.LENGTH_SHORT).show();
                }else {
                    float a,b,c,x;
                    a=Float.parseFloat(str_ul);
                    b = Float.parseFloat(str_ub);
                    c = Float.parseFloat(str_ur);
                    x = Float.parseFloat(str_ux);

                    java.text.DecimalFormat decimalFormat = new java.text.DecimalFormat("0.00");

                    String p1=decimalFormat.format(0);
                    String p2=decimalFormat.format(a);
                    String p3=decimalFormat.format(a+b+x);
                    String p4=decimalFormat.format(a+b+x+c);


                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(et_ux.getWindowToken(), 0);
                    imm.hideSoftInputFromInputMethod(et_ur.getWindowToken(),0);

                    tv_tuinfo.setText("[@Point1]: "+p1+"\n[@Point2]: "+p2+"\n[@Point3]: "+p3+"\n[@Point4]: "+p4+"\n[@@Total]: "+p4);
                }
            }
        });
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        btn_draw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getValueForEditText();
                if (str_ul.equals("") && str_ur.equals("") && str_ub.equals("")) {
                    Toast.makeText(ac_tu.this,"value cannot null",Toast.LENGTH_SHORT).show();
                }else {

                    float ua,ub,uc;
                    ua=Float.parseFloat(str_ul);
                    ub = Float.parseFloat(str_ub);
                    uc = Float.parseFloat(str_ur);

                    Intent intent = new Intent(ac_tu.this, acdraw_tu.class);
                    intent.putExtra("ua", ua);
                    intent.putExtra("ub", ub);
                    intent.putExtra("uc", uc);
                    startActivity(intent);

                }

            }
        });
//////////////////////////////////////////////////////////////////////////////////////////////
        btn_last.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db=dbsql.getReadableDatabase();
                Cursor c=db.query("tb_tu",null,null,null,null,null,null);

                if(c.getCount()>0) {
                    c.moveToFirst();
                    int ic = loop(c.getCount());


                    Toast.makeText(ac_tu.this, "Total : " +(ic+1)+" / "+ c.getCount(), Toast.LENGTH_SHORT).show();

                    c.move(ic);
                    String ul = c.getString(1);
                    String ub = c.getString(2);
                    String ur = c.getString(3);
                    String ux = c.getString(4);


                    et_ul.setText(ul);
                    et_ur.setText(ur);
                    et_ub.setText(ub);
                    et_ux.setText(ux);

                }else {
                    getZDMethod();
                    Toast.makeText(ac_tu.this, "no datas !", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private void getValueForEditText() {
        str_ul=et_ul.getText().toString();
        str_ur=et_ur.getText().toString();
        str_ux=et_ux.getText().toString();
        str_ub=et_ub.getText().toString();

        if (str_ux.equals("")) {
            str_ux = "0";
        }
    }

    @Override
    public void onClick(View v) {
        //finish();
        super.onClick(v);

        if (v.getId() == tv_save.getId()) {


            str_ul=et_ul.getText().toString();
            str_ur=et_ur.getText().toString();
            str_ux=et_ux.getText().toString();
            str_ub=et_ub.getText().toString();


            if (!str_ul.equals("") && !str_ur.equals("") && !str_ub.equals("")) {
                if (str_ux.equals("")) {
                    str_ux = "0";
                }

                SQLiteDatabase db = dbsql.getWritableDatabase();
                String sql = "insert into tb_tu(tu1,tu2,tu3,tu4) values("  + str_ul + "," + str_ub + "," + str_ur + "," + str_ux + ")";
                db.execSQL(sql);
                db.close();
                Toast.makeText(this,"insert tu pai success !",Toast.LENGTH_SHORT).show();
            }else {
                getZDMethod();
                Toast.makeText(this, "insert tu pai faild !", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
