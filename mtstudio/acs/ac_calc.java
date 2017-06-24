package mtstudio.acs;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.draw.R;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;



import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class ac_calc extends ac_main {
  private TextView tv_resault;

    private Button b_ekva;
    private Button b_tkgm;
    private Button b_lkgm;
    private Button b_cptp;
    private Button b_cplp;

    private Button b_leftdel;
    private Button b_leftkh;
    private Button b_rightkh;
    private Button b_00;
    private Button b_rightdel;

    private Button b_jia;
    private Button b_4;
    private Button b_9;
    private Button b_2;
    private Button b_Tc;

    private Button b_jian;
    private Button b_3;
    private Button b_5;
    private Button b_7;
    private Button b_clear;

    private Button b_cheng;
    private Button b_8;
    private Button b_1;
    private Button b_6;
    private Button b_R;

    private Button b_chu;
    private Button b_0;
    private Button b_dian;
    private Button b_equal;

    private String funcname="";
    private final static String FUN_EKVA="ekva";
    private final static String FUN_TKGM="tkgm";
    private final static String FUN_LKGM="lkgm";
    private final static String FUN_CPTP="cptp";
    private final static String FUN_CPLP="cplp";

    private boolean isFunMode=false;



    private WebView wv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lo_combine);

        /////////////////////////////////////////////////////////////////////
        slidingMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        initTextView();
        tv_calc.setTextColor(Color.parseColor("#ff6d00"));

        /////////////////////////////////////////////////////////////////////

        tv_resault = (TextView) findViewById(R.id.tv_result);
        tv_resault.setBackgroundResource(R.drawable.corner_view);
       tv_resault.setMovementMethod(ScrollingMovementMethod.getInstance());


        ////////////////////////////////////////////////////////////////////////////

        b_ekva=(Button)findViewById(R.id.b_ekva);
        b_tkgm=(Button)findViewById(R.id.b_tkgm);
        b_lkgm=(Button)findViewById(R.id.b_lkgm);
        b_cptp=(Button)findViewById(R.id.b_cptp);
        b_cplp=(Button)findViewById(R.id.b_cplp);

        b_leftdel=(Button)findViewById(R.id.b_leftdel);
        b_leftkh=(Button)findViewById(R.id.b_leftkh);
        b_rightkh=(Button)findViewById(R.id.b_rightkh);
        b_00=(Button)findViewById(R.id.b_00);
        b_rightdel=(Button)findViewById(R.id.b_rightdel);

        b_jia=(Button)findViewById(R.id.b_jia);
        b_4=(Button)findViewById(R.id.b_4);
        b_9=(Button)findViewById(R.id.b_9);
        b_2=(Button)findViewById(R.id.b_2);
        b_Tc=(Button)findViewById(R.id.b_Tc);

        b_jian=(Button)findViewById(R.id.b_jian);
        b_3=(Button)findViewById(R.id.b_3);
        b_5=(Button)findViewById(R.id.b_5);
        b_7=(Button)findViewById(R.id.b_7);
        b_clear=(Button)findViewById(R.id.b_clear);

        b_cheng=(Button)findViewById(R.id.b_cheng);
        b_8=(Button)findViewById(R.id.b_8);
        b_1=(Button)findViewById(R.id.b_1);
        b_6=(Button)findViewById(R.id.b_6);
        b_R=(Button)findViewById(R.id.b_R);

        b_chu=(Button)findViewById(R.id.b_chu);
        b_0=(Button)findViewById(R.id.b_0);
        b_dian=(Button)findViewById(R.id.b_dian);
        b_equal=(Button)findViewById(R.id.b_equal);
////////////////////////////////////////////////////////////////////////////
        b_ekva.setOnClickListener(this);
        b_tkgm.setOnClickListener(this);
        b_lkgm.setOnClickListener(this);
        b_cptp.setOnClickListener(this);
        b_cplp.setOnClickListener(this);

        b_leftdel.setOnClickListener(this);
        b_leftkh. setOnClickListener(this);
        b_rightkh.setOnClickListener(this);
        b_00.setOnClickListener(this);
        b_rightdel.setOnClickListener(this);

        b_jia.setOnClickListener(this);
        b_4.setOnClickListener(this);
        b_9.setOnClickListener(this);
        b_2.setOnClickListener(this);
        b_Tc.setOnClickListener(this);

        b_jian.setOnClickListener(this);
        b_3.setOnClickListener(this);
        b_5.setOnClickListener(this);
        b_7.setOnClickListener(this);
        b_clear.setOnClickListener(this);

        b_cheng.setOnClickListener(this);
        b_8.setOnClickListener(this);
        b_1.setOnClickListener(this);
        b_6.setOnClickListener(this);
        b_R.setOnClickListener(this);

        b_chu.setOnClickListener(this);
        b_0.setOnClickListener(this);
        b_dian.setOnClickListener(this);
        b_equal.setOnClickListener(this);

       ///////////////////////////////////////////////////////////////////


       // wv.loadUrl("javascprit:evalnam('3*5')");


Toast.makeText(this,"Sorror ,this function is building !",Toast.LENGTH_SHORT).show();


    }



    private void refreshView(String msg) {
        if(tv_resault.getText().toString().equals("0") && tv_resault.length()==1){
        tv_resault.setText("");
            tv_resault.append(msg);
            int offset=(tv_resault.getLineCount())*tv_resault.getLineHeight();
            if(offset>tv_resault.getHeight()){
                tv_resault.scrollTo(0,offset-tv_resault.getHeight()+40);
            }
        }else{
        tv_resault.append(msg);
        int offset=(tv_resault.getLineCount())*tv_resault.getLineHeight();
        if(offset>tv_resault.getHeight()){
            tv_resault.scrollTo(0,offset-tv_resault.getHeight()+40);
        }
        }
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
/*
        if(v.getId()==b_0.getId()){
            refreshView("0");
        } else if (v.getId() == b_1.getId()) {
            refreshView("1");
        } else if (v.getId() == b_2.getId()) {
            refreshView("2");
        } else if (v.getId() == b_leftdel.getId()) {
            String str=tv_resault.getText().toString();
            if(str.length()<=1){
                tv_resault.setText("0");
            }else {
            String newstr = str.substring(0, str.length() - 1);
            tv_resault.setText(newstr);
            }
        } else if (v.getId() == b_3.getId()) {
            refreshView("3");
        } else if (v.getId() == b_4.getId()) {
            refreshView("4");
        } else if (v.getId() == b_5.getId()) {

            refreshView("5");
        } else if (v.getId() == b_6.getId()) {
            refreshView("6");
        } else if (v.getId() == b_7.getId()) {
            refreshView("7");
        } else if (v.getId() == b_8.getId()) {
            refreshView("8");
        } else if (v.getId() == b_9.getId()) {
            refreshView("9");
        } else if (v.getId() == b_00.getId()) {
            refreshView("00");
        } else if (v.getId() == b_dian.getId()) {
            refreshView(".");
        } else if (v.getId() == b_jia.getId()) {
            refreshView("+");
        } else if (v.getId() == b_jian.getId()) {
            refreshView("-");
        } else if (v.getId() == b_cheng.getId()) {
            refreshView("*");
        } else if (v.getId() == b_chu.getId()) {
            refreshView("/");
        } else if (v.getId() == b_R.getId()) {
            tv_resault.setText("0");
        } else if (v.getId() == b_ekva.getId()) {
            refreshView("Ekva ");
            isFunMode=true;

        }


        else  if (v.getId() == b_equal.getId()) {



            refreshView("=\n ");
            float reaultnum=0;
            String resultstr = tv_resault.getText().toString();


            if(!isFunMode) {
                    //正常的四则运算模式+ - * /

                    int cout = getCount(resultstr, "=");
                    String rs[] = resultstr.split("=");
                    String laststr = (String) rs[cout - 1];
                    final String sublaststr = laststr.substring(laststr.indexOf(""), laststr.length());

                wv = (WebView) findViewById(R.id.wv);
                WebSettings webSettings=wv.getSettings();
                webSettings.setJavaScriptEnabled(true);
                wv.loadUrl("file:///android_asset/js.html");

                WebViewClient wvc=new WebViewClient(){
                    @Override
                    public void onPageFinished(WebView view, String url) {
                        super.onPageFinished(view, url);

                        wv.loadUrl("javascript:evalnum('"+sublaststr+"')");


                    }
                };





                    ////////////////////////////////////////////////////
                }
                else
                {
                //函数功能模式，首先设置isfuncmode=true,然后设置funcname的类型
                    if(funcname.equals("ekva")) {

                        isFunMode=false;
                        funcname = "";


                String ekva[] = resultstr.split("=");
                int coutek = getCount(resultstr, "=");
                String eklaststr = (String) ekva[coutek - 1];
                       // Toast.makeText(this,eklaststr,Toast.LENGTH_SHORT).show();
            String subeklaststr=eklaststr.substring(eklaststr.indexOf("a")+1,eklaststr.length());
                      // Toast.makeText(this,subeklaststr,Toast.LENGTH_SHORT).show();
//
                float ekvafloat = Float.parseFloat(subeklaststr);
                float ekvadata = (float) ekvafloat / (float) 0.6;

               reaultnum=cutFlot(ekvadata);
                        refreshView(""+reaultnum+"\n");


                }
            ////////////////////////////////////////////////////


            }
          }
        }

        
public float cutFlot(float f) {
    java.text.DecimalFormat decimalFormat = new java.text.DecimalFormat("0.000");
    String p=decimalFormat.format(f);
    return Float.parseFloat(p);
}

    public  int getCount(String str, String sub) {
        int index,count;
        index=0;
        count=0;

        while ((index = str.indexOf(sub, index)) != -1) {
            index=index+sub.length();
            count++;
        }
        return count;*/
    }



}
