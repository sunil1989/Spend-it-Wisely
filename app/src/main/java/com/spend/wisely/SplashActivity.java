package com.spend.wisely;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import com.spend.wisely.com.onbordinganim.Main1Activity;
import com.spend.wisely.db.HelloService;
import com.spend.wisely.db.SharedPreferencesManager;


public class SplashActivity extends Activity {

    /**
     * Duration of wait *
     */
    private final int SPLASH_DISPLAY_LENGTH = 1000;
    SharedPreferencesManager sharedPref = new SharedPreferencesManager();
    String tag;
    SharedPreferences prfs;
    String Astatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        tag = sharedPref.getTourtag(this);

        Intent i = new Intent(this, HelloService.class);
        startService(i);

        prfs = getSharedPreferences("spend_hour", Context.MODE_PRIVATE);
        Astatus = prfs.getString("text", "");


           /* New Handler to start the Menu-Activity
         * and close this Splash-Screen after some seconds.*/
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */

                if (tag != null && tag.equals("1")) {

                    if (!TextUtils.isEmpty(Astatus)) {
                        Intent mainIntent = new Intent(SplashActivity.this, DashBoardActivity.class);
                        SplashActivity.this.startActivity(mainIntent);
                    } else {
                        Intent mainIntent = new Intent(SplashActivity.this, HourlyRateActivity.class);
                        SplashActivity.this.startActivity(mainIntent);
                    }

                } else {
                    Intent mainIntent = new Intent(SplashActivity.this, Main1Activity.class);
                    SplashActivity.this.startActivity(mainIntent);
                    SplashActivity.this.finish();
                }
            }
        }, SPLASH_DISPLAY_LENGTH);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_splash, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
