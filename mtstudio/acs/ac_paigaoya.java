package mtstudio.acs;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;

import com.example.draw.R;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

public class ac_paigaoya extends ac_main {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lo_paigaoya);


        //////////////////////////////////////////////////////////////////////
        //初始化操作
        slidingMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);


        initTextView();
        tv_gyct.setTextColor(Color.parseColor("#ff6d00"));

        //////////////////////////////////////////////////////////////////////

        WebView webView1 = (WebView) findViewById(R.id.wv_dlq);
        webView1.loadUrl("file:///android_asset/dlqselect.html");

        webView1.setFocusable(true);
        webView1.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(webView1.getWindowToken(), 0);


    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
    }
}
