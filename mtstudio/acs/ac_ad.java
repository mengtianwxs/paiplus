package mtstudio.acs;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.draw.R;
import com.wandoujia.ads.sdk.Ads;






public class ac_ad extends Activity {
    /////////////////////////////////////////
    private static final String APP_ID = "100051508";
    private static final String SECRET_KEY = "559033926f9bd9d309547a69fb1b4e92";
    private static final String BANNER = "901d0511f66ff8bb901e4f7c339beaa6";
    /////////////////////////////////////////////
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lo_ad);
        new AsyncTask<Void, Void, Boolean>() {
            @Override
            protected Boolean doInBackground(Void... params) {
                try {

                    Ads.init(ac_ad.this, APP_ID, SECRET_KEY);
                    return true;
                } catch (Exception e) {
                    Log.e("ads-sample", "error", e);
                    return false;
                }
            }

            @Override
            protected void onPostExecute(Boolean success) {
                final ViewGroup container = (ViewGroup) findViewById(R.id.banner_container);

                if (success) {
                    /**
                     * pre load
                     */
                    Ads.preLoad(BANNER, Ads.AdFormat.banner);


                    /**
                     * add ad views
                     */
                    View bannerView = Ads.createBannerView(ac_ad.this, BANNER);
                    container.addView(bannerView, new ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT
                    ));

//                    Button btI = new Button(MainActivity.this);
//                    btI.setText("interstitial");
//                    btI.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            Ads.showInterstitial(MainActivity.this, INTERSTITIAL);
//                        }
//                    });
//                    container.addView(btI);
//
//                    Button btW = new Button(MainActivity.this);
//                    btW.setText("app wall");
//                    btW.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            Ads.showAppWall(MainActivity.this, APP_WALL);
//                        }
//                    });
//                    container.addView(btW);
                } else {
                    TextView errorMsg = new TextView(ac_ad.this);
                    errorMsg.setText("init failed");
                    container.addView(errorMsg);
                }
            }
        }.execute();




    }
}
