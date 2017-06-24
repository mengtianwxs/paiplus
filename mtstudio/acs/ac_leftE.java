package mtstudio.acs;

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
import com.example.draw.acdraw_lefte;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.wandoujia.ads.sdk.Ads;

public class ac_leftE extends ac_main {
   private EditText et_xtlen;
   private EditText et_xklen;
    private EditText et_xslen;
    private EditText et_xwlen;
    private EditText et_h;
    private EditText et_x;

    private String str_xtlen;
    private String str_xklen;
    private String str_xslen;
    private String str_xwlen;
    private String str_h;
    private String str_x;

    private TextView tv_info;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lo_lefte);



        //////////////////////////////////////////////////////////////////////
        new AsyncTask<Void, Void, Boolean>() {
            @Override
            protected Boolean doInBackground(Void... params) {
                try {

                    Ads.init(ac_leftE.this, APP_ID, SECRET_KEY);
                    return true;
                } catch (Exception e) {
                   // Log.e("ads-sample", "error", e);
                    return false;
                }
            }

            @Override
            protected void onPostExecute(Boolean success) {
                final ViewGroup container = (ViewGroup) findViewById(R.id.ll_ads_lefte);

                if (success) {
                    /**
                     * pre load
                     */
                    Ads.preLoad(BANNER, Ads.AdFormat.banner);

                    /**
                     * add ad views
                     */
                    View bannerView = Ads.createBannerView(ac_leftE.this, BANNER);
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
        tv_leftE.setTextColor(Color.parseColor(TextClickColor));

        /////////////////////////////////////////////////////////////////////
        tv_info=(TextView)findViewById(R.id.tv_lefteinfo);
        tv_info.setTextColor(Color.parseColor("#13b5b1"));

        tv_info.setText("[@Point1]: "+0+"\n[@Point2]: "+0+"\n[@Point3]: "+0+"\n[@Point4]: "+0+"\n[@Point5]: 0"+"\n[@@Total]: 0");
        /////////////////////////////////////////////////////////////////////

        et_xtlen = (EditText) findViewById(R.id.et_lextlen);
        et_xklen = (EditText) findViewById(R.id.et_lextkuandu);
        et_xslen = (EditText) findViewById(R.id.et_lexslen);
        et_xwlen = (EditText) findViewById(R.id.et_lexwlen);
        et_h = (EditText) findViewById(R.id.et_ledxhight);
        et_x = (EditText) findViewById(R.id.et_lezw);

        et_xtlen.setFocusable(true);
        et_xtlen.requestFocus();
        showInputMethod();

        et_xtlen.setInputType(EditorInfo.TYPE_CLASS_PHONE);
        et_xklen .setInputType(EditorInfo.TYPE_CLASS_PHONE);
        et_xslen .setInputType(EditorInfo.TYPE_CLASS_PHONE);
        et_xwlen .setInputType(EditorInfo.TYPE_CLASS_PHONE);
        et_h .setInputType(EditorInfo.TYPE_CLASS_PHONE);
        et_x.setInputType(EditorInfo.TYPE_CLASS_PHONE);

///////////////////////////////////////////////////////////////////////////////
        Button btn_clear = (Button) findViewById(R.id.btn_cleardata);
        Button btn_calc = (Button) findViewById(R.id.btn_calcpai);
        Button btn_last = (Button) findViewById(R.id.btn_lastdata);
        Button btn_draw = (Button) findViewById(R.id.btn_drawpai);
        /////////////////////////////////////////////////////////////////////////////

        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_xtlen.setText("");
                et_xklen.setText("");
                et_xslen.setText("");
                et_xwlen.setText("");
                et_h.setText("");
                et_x.setText("");
                tv_info.setText("[@Point1]: "+0+"\n[@Point2]: "+0+"\n[@Point3]: "+0+"\n[@Point4]: "+0+"\n[@Point5]: 0"+"\n[@@Total]: 0");

                et_xtlen.setFocusable(true);
                et_xtlen.requestFocus();
                showInputMethod();
            }
        });
        /////////////////////////////////////////////////////////////////////////////
        btn_calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getValueForEditText();

                if(!str_xklen.equals("") && !str_xtlen.equals("") && !str_xslen.equals("") && !str_xwlen.equals("") && !str_h.equals("")){
                    float a,b,c,d,h1,w,H,x;
                    a=Float.parseFloat(str_xtlen);
                    H=Float.parseFloat(str_h);
                    c = Float.parseFloat(str_xslen);
                    h1=H-a-c;
                    w=Float.parseFloat(str_xklen);
                    b=(float) Math.sqrt(Double.parseDouble(Float.toString(w*w+h1*h1)));
                    d=Float.parseFloat(str_xwlen);
                    x=Float.parseFloat(str_x);

                    java.text.DecimalFormat decimalFormat = new java.text.DecimalFormat("0.00");

                    String p0=decimalFormat.format(0);
                    String p1 = decimalFormat.format(a);
                    String p2=decimalFormat.format(a+b+x);
                    String p3 = decimalFormat.format(a+b+c+x);
                    String p4 = decimalFormat.format(a+b+c+d+x);
                    String t = decimalFormat.format(a+b+c+d+x);

                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(et_x.getWindowToken(), 0);
                    imm.hideSoftInputFromInputMethod(et_h.getWindowToken(),0);

                    tv_info.setText("[@Point1]: " + p0 + "\n[@Ponit2]: " + p1 + "\n[@Point3]: " + p2+ "\n[@Point4]: " + p3 + "\n[@Point5]: " + p4+ "\n[@@Total]: " + t);

                }
                else
                {
                    Toast.makeText(ac_leftE.this,"cannot null" ,Toast.LENGTH_SHORT).show();
                }
            }
        });
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        btn_draw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getValueForEditText();

                if(!str_xklen.equals("") && !str_xtlen.equals("") && !str_xslen.equals("") && !str_xwlen.equals("") && !str_h.equals("")) {
                    float a1,c1,d1,w1,H1;

                    a1=Float.parseFloat(str_xtlen);
                    H1=Float.parseFloat(str_h);
                    c1 = Float.parseFloat(str_xslen);
                    w1=Float.parseFloat(str_xklen);
                    d1=Float.parseFloat(str_xwlen);

                    Intent intent = new Intent(ac_leftE.this, acdraw_lefte.class);
                    intent.putExtra("sh",a1);
                    intent.putExtra("h",H1);
                    intent.putExtra("xh",c1);
                    intent.putExtra("bh",d1);
                    intent.putExtra("w",w1);
                    startActivity(intent);
//
                }else {
                    Toast.makeText(ac_leftE.this,"cannot null" ,Toast.LENGTH_SHORT).show();

                }


            }
        });

/////////////////////////////////////////////////////////////////////////////////////////////////

        btn_last.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SQLiteDatabase db=dbsql.getReadableDatabase();
                Cursor c=db.query("tb_lefte",null,null,null,null,null,null);

                if(c.getCount()>0) {
                    c.moveToFirst();
                    int ic = loop(c.getCount());


                    Toast.makeText(ac_leftE.this, "Total : " +(ic+1)+" / "+ c.getCount(), Toast.LENGTH_SHORT).show();

                    c.move(ic);
                    String le1 = c.getString(1);
                    String le2 = c.getString(2);
                    String le3 = c.getString(3);
                    String le4 = c.getString(4);
                    String le5 = c.getString(5);
                    String le6 = c.getString(6);
                    et_xtlen.setText(le1);
                    et_xklen.setText(le2);
                    et_xslen.setText(le3);
                    et_xwlen.setText(le4);
                    et_h.setText(le5);
                    et_x.setText(le6);
                }else {
                    getZDMethod();
                    Toast.makeText(ac_leftE.this, "no datas !", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private void getValueForEditText(){
        str_xtlen=et_xtlen.getText().toString();
       str_xklen=et_xklen.getText().toString();
        str_xslen=et_xslen.getText().toString();
         str_xwlen=et_xwlen.getText().toString();
         str_h=et_h.getText().toString();
          str_x=et_x.getText().toString();
        if(str_x.equals("")){
            str_x="0";
        }

    }


    @Override
    public void onClick(View v) {
        //finish();
        super.onClick(v);

        if (v.getId() == tv_save.getId()) {


            str_xtlen=et_xtlen.getText().toString();
            str_xklen=et_xklen.getText().toString();
            str_xslen=et_xslen.getText().toString();
            str_xwlen=et_xwlen.getText().toString();
            str_h=et_h.getText().toString();
            str_x=et_x.getText().toString();
            if(!str_xklen.equals("") && !str_xtlen.equals("") && !str_xslen.equals("") && !str_xwlen.equals("") && !str_h.equals("")){
                if(str_x.equals("")){
                    str_x="0";
                }

                SQLiteDatabase db = dbsql.getWritableDatabase();
                String sql = "insert into tb_lefte(le1,le2,le3,le4,le5,le6) values(" + str_xtlen + "," + str_xklen + ","+ str_xslen + "," + str_xwlen + "," + str_h + "," + str_x + ")";
                db.execSQL(sql);
                db.close();
                Toast.makeText(this,"insert lefte pai success !",Toast.LENGTH_SHORT).show();
            }else {
                getZDMethod();
                Toast.makeText(this, "insert lefte pai faild !", Toast.LENGTH_SHORT).show();
            }
        }

    }
}
