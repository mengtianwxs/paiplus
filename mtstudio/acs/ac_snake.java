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
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.draw.R;
import com.example.draw.acdraw_snake;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.wandoujia.ads.sdk.Ads;



public class ac_snake extends ac_main{


    private TextView tv_dispalyseekbar;

    private String len_shangwan;
    private String len_xiawan;
    private String len_width;
    private String len_height;
    private String len_x;

    private EditText et_shangwan;
    private EditText et_xiawan;
    private EditText et_width;
    private EditText et_height;
    private EditText et_x;
////////////////////////////////////////////////////////ad




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       setContentView(R.layout.lo_snake);
       // LinearLayout ll_snake=
        //////////////////////////////////////////////////////////////////////
        //初始化操作
        slidingMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);


        initTextView();
        tv_snake.setTextColor(Color.parseColor(TextClickColor));


        //////////////////////////////////////////////////////////////////////


        new AsyncTask<Void, Void, Boolean>() {
            @Override
            protected Boolean doInBackground(Void... params) {
                try {

                    Ads.init(ac_snake.this, APP_ID, SECRET_KEY);
                    return true;
                } catch (Exception e) {
                   // Log.e("ads-sample", "error", e);
                    return false;
                }
            }

            @Override
            protected void onPostExecute(Boolean success) {
                final ViewGroup container = (ViewGroup) findViewById(R.id.ll_ads);

                if (success) {
                    /**
                     * pre load
                     */
                    Ads.preLoad(BANNER, Ads.AdFormat.banner);

                    /**
                     * add ad views
                     */
                    View bannerView = Ads.createBannerView(ac_snake.this, BANNER);
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












        //////////////////////////////////////////////////////////////////////







        et_shangwan=(EditText)findViewById(R.id.etsnake_shangwan);
        et_xiawan=(EditText)findViewById(R.id.etsnake_xiawan);
        et_width=(EditText)findViewById(R.id.etsnake_kuan);
        et_height=(EditText)findViewById(R.id.etsnake_gao);
        et_x = (EditText)findViewById(R.id.et_X);

        et_shangwan.setInputType(EditorInfo.TYPE_CLASS_PHONE);
        et_xiawan.setInputType(EditorInfo.TYPE_CLASS_PHONE);
        et_height.setInputType(EditorInfo.TYPE_CLASS_PHONE);
        et_width.setInputType(EditorInfo.TYPE_CLASS_PHONE);
        et_x.setInputType(EditorInfo.TYPE_CLASS_PHONE);


        et_shangwan.setText("");
        et_xiawan.setText("");
        et_height.setText("");
        et_width.setText("");
        et_x.setText("");


        et_shangwan.setFocusable(true);
        et_shangwan.requestFocus();

        //showInputMethod();


        final TextView tv_snakeinfo=(TextView)findViewById(R.id.tv_snakeinfo);
        tv_snakeinfo.setTextColor(Color.parseColor("#13b5b1"));
        tv_snakeinfo.setText("[@Point1]: "+0+"\n[@Point2]: "+0+"\n[@Point3]: "+0+"\n[@Point4]: "+0+"\n[@@Total]: 0");


        //////////////////////////////////////////////////////////////////////
        Button btn_clear=(Button)findViewById(R.id.btn_cleardata);
        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!et_shangwan.getText().toString().equals("") || !et_xiawan.getText().toString().equals("") || !et_height.getText().toString().equals("") || !et_width.getText().toString().equals("")) {

                et_shangwan.setText("");
                et_xiawan.setText("");
                et_height.setText("");
                et_width.setText("");
                et_x.setText("");

                    tv_snakeinfo.setText("[@Point1]: "+0+"\n[@Point2]: "+0+"\n[@Point3]: "+0+"\n[@Point4]: "+0+"\n[@@Total]: 0");
                    et_shangwan.setFocusable(true);
                et_shangwan.requestFocus();
                    showInputMethod();

                }else {
                    Toast.makeText(ac_snake.this,"all is null",Toast.LENGTH_SHORT).show();

                }

            }
        });
////////////////////////////////////////////////////////////////////////////////////////
        Button btn_calc=(Button)findViewById(R.id.btn_calcpai);
        btn_calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!et_shangwan.getText().toString().equals("") || !et_xiawan.getText().toString().equals("") || !et_height.getText().toString().equals("") || !et_width.getText().toString().equals("") ) {

                    float a, b, c;
                    float sw, xw, h, w, x;

                    sw = Float.parseFloat(et_shangwan.getText().toString());
                    xw = Float.parseFloat(et_xiawan.getText().toString());
                    h = Float.parseFloat(et_height.getText().toString());
                    w = Float.parseFloat(et_width.getText().toString());


                    a = h - sw - xw;
                    b = w;
                    c = (float) Math.sqrt(Double.parseDouble(Float.toString(a * a + b * b)));


                    if(et_x.getText().toString().equals("")){
                        x=0;
                    }else {
                        x = Float.parseFloat(et_x.getText().toString());
                    }
                  java.text.DecimalFormat decimalFormat = new java.text.DecimalFormat("0.00");

                    String p0=decimalFormat.format(0);
                    String p1 = decimalFormat.format(sw);
                    String p2=decimalFormat.format(sw+c+x);
                    String p3 = decimalFormat.format(sw + c +x + xw);
                    String t = decimalFormat.format(sw + c + x + xw);

                    //键盘隐藏
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(et_width.getWindowToken(), 0);
                    imm.hideSoftInputFromWindow(et_x.getWindowToken(), 0);
                    tv_snakeinfo.setText("[@Point1]: " + p0 + "\n[@Ponit2]: " + p1 + "\n[@Point3]: " + p2+ "\n[@Point4]: " + p3 + "\n[@@Total]: " + t);
                }else {
                    Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibrator.vibrate(400);
                    Toast.makeText(ac_snake.this,"et's value cannot null",Toast.LENGTH_SHORT).show();
                }
            }
        });

////////////////////////////////////////////////////////////////////////////////////////

        Button btn_draw=(Button)findViewById(R.id.btn_drawpai);
        btn_draw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                len_shangwan=et_shangwan.getText().toString();
                len_xiawan=et_xiawan.getText().toString();
                len_height= et_height.getText().toString();
                len_width=et_width.getText().toString();
                len_x=et_x.getText().toString();

                if(!len_shangwan.equals("") || !len_xiawan.equals("") || !len_height.equals("") || !len_width.equals("") ) {


                    Intent intent_draw = new Intent(ac_snake.this, acdraw_snake.class);
                    intent_draw.putExtra("shangwan", len_shangwan);
                    intent_draw.putExtra("xiawan", len_xiawan);
                    intent_draw.putExtra("height", len_height);
                    intent_draw.putExtra("width", len_width);



                    startActivity(intent_draw);

                }else{
                    Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibrator.vibrate(400);
                    Toast.makeText(ac_snake.this,"et's value cannot null",Toast.LENGTH_SHORT).show();
                }
            }
        });

////////////////////////////////////////////////////////////////////////////////////////

        Button btn_last = (Button) findViewById(R.id.btn_lastdata);
        btn_last.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                SQLiteDatabase db=dbsql.getReadableDatabase();
                Cursor c=db.query("tb_snake",null,null,null,null,null,null);

                if(c.getCount()>0) {
                    c.moveToFirst();
                    int ic = loop(c.getCount());


                    Toast.makeText(ac_snake.this, "Total : " +(ic+1)+" / "+ c.getCount(), Toast.LENGTH_SHORT).show();

                     c.move(ic);
                    String sw = c.getString(1);
                    String xw = c.getString(2);
                    String h = c.getString(3);
                    String w = c.getString(4);
                    String x = c.getString(5);
                    et_shangwan.setText(sw);
                    et_xiawan.setText(xw);
                    et_height.setText(h);
                    et_width.setText(w);
                    et_x.setText(x);
                }else {
                    getZDMethod();
                    Toast.makeText(ac_snake.this, "no datas !", Toast.LENGTH_SHORT).show();
                }



            }
        });
    }


    @Override
    public void onClick(View v) {
        //finish();
        super.onClick(v);

        if (v.getId() == tv_save.getId()) {


            len_shangwan=et_shangwan.getText().toString();
            len_xiawan=et_xiawan.getText().toString();
            len_height= et_height.getText().toString();
            len_width=et_width.getText().toString();
            len_x=et_x.getText().toString();
            if(!len_shangwan.equals("") || !len_xiawan.equals("") || !len_height.equals("") || !len_width.equals("") ) {
                if(len_x.equals("")){
                    len_x="0";
                }

                SQLiteDatabase db = dbsql.getWritableDatabase();
                String sql = "insert into tb_snake(len_sw,len_xw,len_height,len_width,len_x) values(" + len_shangwan + "," + len_xiawan + "," + len_height + "," + len_width + "," + len_x + ")";
                db.execSQL(sql);
                db.close();
                Toast.makeText(this,"insert snake pai success !",Toast.LENGTH_SHORT).show();
            }else {
                getZDMethod();
                Toast.makeText(ac_snake.this, "insert snake pai faild !", Toast.LENGTH_SHORT).show();
            }
        }


    }



}
