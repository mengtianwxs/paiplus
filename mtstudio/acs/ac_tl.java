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
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.wandoujia.ads.sdk.Ads;

public class ac_tl extends ac_main {

    private EditText et_xz;
    private EditText et_yz;
    private EditText et_bl;

    private TextView tv_tlinfo;

    private String str_xz;
    private String str_yz;
    private String str_bl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lo_tl);



        //////////////////////////////////////////////////////////////////////
        new AsyncTask<Void, Void, Boolean>() {
            @Override
            protected Boolean doInBackground(Void... params) {
                try {

                    Ads.init(ac_tl.this, APP_ID, SECRET_KEY);
                    return true;
                } catch (Exception e) {
                   // Log.e("ads-sample", "error", e);
                    return false;
                }
            }

            @Override
            protected void onPostExecute(Boolean success) {
                final ViewGroup container = (ViewGroup) findViewById(R.id.ll_ads_tl);

                if (success) {
                    /**
                     * pre load
                     */
                    Ads.preLoad(BANNER, Ads.AdFormat.banner);

                    /**
                     * add ad views
                     */
                    View bannerView = Ads.createBannerView(ac_tl.this, BANNER);
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
        tv_TL.setTextColor(Color.parseColor(TextClickColor));

        //////////////////////////////////////////////////////////////////////

        et_xz=(EditText)findViewById(R.id.et_zbX);
        et_yz=(EditText)findViewById(R.id.et_zbY);
        et_bl=(EditText)findViewById(R.id.et_X);

        tv_tlinfo = (TextView) findViewById(R.id.tv_tlinfo);
        tv_tlinfo.setTextColor(Color.parseColor("#13b5b1"));
        tv_tlinfo.setText("[@Point1]: "+0+"\n[@Point2]: "+0+"\n[@Point3]: "+0+"\n[@@Total]: 0");


        et_xz.setInputType(EditorInfo.TYPE_CLASS_PHONE);
        et_yz.setInputType(EditorInfo.TYPE_CLASS_PHONE);
        et_bl.setInputType(EditorInfo.TYPE_CLASS_PHONE);

        et_yz.setFocusable(true);
        et_yz.requestFocus();
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
                et_xz.setText("");
                et_yz.setText("");
                et_bl.setText("");
                tv_tlinfo.setText("[@Point1]: "+0+"\n[@Point2]: "+0+"\n[@Point3]: "+0+"\n[@@Total]: 0");

                et_yz.setFocusable(true);
                et_yz.requestFocus();
                showInputMethod();
            }
        });
/////////////////////////////////////////////////////////////////////////////////
        btn_calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getValueForEditText();
                if (str_yz.equals("") && str_xz.equals("")) {
                    Toast.makeText(ac_tl.this,"value cannot null",Toast.LENGTH_SHORT).show();
                }else {
                    float a,b,x;
                    a=Float.parseFloat(str_yz);
                    b = Float.parseFloat(str_xz);
                    x = Float.parseFloat(str_bl);

                    java.text.DecimalFormat decimalFormat = new java.text.DecimalFormat("0.00");

                    String p1=decimalFormat.format(0);
                    String p2=decimalFormat.format(a);
                    String p3=decimalFormat.format(a+b+x);

                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(et_bl.getWindowToken(), 0);
                    imm.hideSoftInputFromInputMethod(et_xz.getWindowToken(),0);

                    tv_tlinfo.setText("[@Point1]: "+p1+"\n[@Point2]: "+p2+"\n[@Point3]: "+p3+"\n[@@Total]: "+p3);
                }
            }
        });
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        btn_draw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getValueForEditText();
                if (str_yz.equals("") && str_xz.equals("")) {
                    Toast.makeText(ac_tl.this,"value cannot null",Toast.LENGTH_SHORT).show();
                }else {

                    float xz,yz;
                    xz=Float.parseFloat(str_xz);
                    yz = Float.parseFloat(str_yz);

                    Intent intent = new Intent(ac_tl.this, acdraw_tl.class);
                    intent.putExtra("xz", xz);
                    intent.putExtra("yz", yz);
                    startActivity(intent);

                }

            }
        });

        btn_last.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db=dbsql.getReadableDatabase();
                Cursor c=db.query("tb_tl",null,null,null,null,null,null);

                if(c.getCount()>0) {
                    c.moveToFirst();
                    int ic = loop(c.getCount());


                    Toast.makeText(ac_tl.this, "Total : " +(ic+1)+" / "+ c.getCount(), Toast.LENGTH_SHORT).show();

                    c.move(ic);
                    String yz = c.getString(1);
                    String xz = c.getString(2);
                    String bl = c.getString(3);

                    et_xz.setText(xz);
                    et_yz.setText(yz);
                    et_bl.setText(bl);
                }else {
                    getZDMethod();
                    Toast.makeText(ac_tl.this, "no datas !", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void getValueForEditText(){
        str_bl=et_bl.getText().toString();
        str_xz=et_xz.getText().toString();
        str_yz=et_yz.getText().toString();
        if(str_bl.equals("")){
            str_bl = "0";
        }

    }

    @Override
    public void onClick(View v) {
        //finish();
        super.onClick(v);

        if (v.getId() == tv_save.getId()) {


            str_bl=et_bl.getText().toString();
            str_xz=et_xz.getText().toString();
            str_yz=et_yz.getText().toString();

            if (!str_yz.equals("") && !str_xz.equals("")) {
                if(str_bl.equals("")){
                    str_bl = "0";
                }

                SQLiteDatabase db = dbsql.getWritableDatabase();
                String sql = "insert into tb_tl(tl1,tl2,tl3) values("  + str_yz + "," + str_xz + "," + str_bl + ")";
                db.execSQL(sql);
                db.close();
                Toast.makeText(this,"insert tl pai success !",Toast.LENGTH_SHORT).show();
            }else {
                getZDMethod();
                Toast.makeText(this, "insert tl pai faild !", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
