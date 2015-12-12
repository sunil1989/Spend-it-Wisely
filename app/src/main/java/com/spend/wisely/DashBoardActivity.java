package com.spend.wisely;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.spend.wisely.com.currency.AudioData;
import com.spend.wisely.com.currency.Horizontal_Adapter;
import com.spend.wisely.db.Apps;
import com.spend.wisely.db.Appsdb;
import com.spend.wisely.db.ProfileActivity;
import com.spend.wisely.db.SharedPreferencesManager;

import java.util.ArrayList;

import it.sephiroth.android.library.widget.HListView;


public class DashBoardActivity extends Activity implements ApplicationAdapter.customButtonListener {
    TextView mMonthtext, mEditText3;
    HListView horizontallistview;

    ArrayList<AudioData> horizontaldata;
    private ApplicationAdapter listadaptor = null;
    ArrayList<Apps> list;
    Appsdb appdb;
    String pkg = "";
    ImageView mPrfileImage, mSettingImage;
    TextView textView5;
    RelativeLayout rliBackground;
    SharedPreferencesManager sharedPref = new SharedPreferencesManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_dash_board);

        Bundle extras = getIntent().getExtras();
        if (extras == null) {
        } else {
            pkg = extras.getString("pkg");
            System.out.println("============pkg=========" + pkg);
            finish();
        }

        //Intent intent=getIntent();
        //String in=intent.getStringExtra("result");
        SharedPreferences prfs = getSharedPreferences("spend_hour", Context.MODE_PRIVATE);
        String in = prfs.getString("text", "");

        System.out.println("===========Result========" + in);
        Resources r = getResources();
        float dp = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, 72, r.getDisplayMetrics());

        System.out.println("=============dp========" + dp);


        horizontaldata = new ArrayList<>();
        horizontaldata.add(new AudioData(R.drawable.face_icon, "$ 280"));
        horizontaldata.add(new AudioData(R.drawable.twittr_icon, "$ 120"));
        horizontaldata.add(new AudioData(R.drawable.yahoo_icon, "$ 100"));
        horizontaldata.add(new AudioData(R.drawable.s_icon, "$ 52"));
        horizontaldata.add(new AudioData(R.drawable.whats_icon, "$ 48"));

        horizontallistview = (HListView) findViewById(R.id.hListView1);
        //Horizontal_Adapter adp = new Horizontal_Adapter(this, horizontaldata);
        // horizontallistview.setAdapter(adp);
        // horizontallistview.setOnItemClickListener(this);

        Typeface face = Typeface.createFromAsset(getAssets(), "ptsans.ttf");
        TextView textView4 = (TextView) findViewById(R.id.textView4);

        Typeface face1 = Typeface.createFromAsset(getAssets(), "PTC75F.ttf");

        System.out.println("====================fgfgfggfgf========change===");

        TextView textview1 = (TextView) findViewById(R.id.textview1);

        TextView textview2 = (TextView) findViewById(R.id.textview2);

        TextView textView6 = (TextView) findViewById(R.id.textView6);

        textView5 = (TextView) findViewById(R.id.textView5);
        rliBackground = (RelativeLayout) findViewById(R.id.rli);

        mPrfileImage = (ImageView) findViewById(R.id.imageView3);
        mPrfileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in = new Intent(DashBoardActivity.this, ProfileActivity.class);
                startActivity(in);
            }
        });

        mSettingImage = (ImageView) findViewById(R.id.imageView4);
        mSettingImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in = new Intent(DashBoardActivity.this, SettingsActivity.class);
                startActivity(in);
            }
        });


        textView4.setTypeface(face);
        textview1.setTypeface(face);
        textview2.setTypeface(face);
        textView6.setTypeface(face);
        textView5.setTypeface(face1);


        float resu = HourlyRateActivity.result;
        appdb = new Appsdb(getApplicationContext());
        list = appdb.getAllList();
        listadaptor = new ApplicationAdapter(DashBoardActivity.this,
                R.layout.horizontal_data_row, list, String.valueOf(resu));
        listadaptor.setCustomButtonListner(this);
        horizontallistview.setAdapter(listadaptor);
    }


    public void onResume() {
        super.onResume();
        String color_gradient = sharedPref.getBackground(this);

        if (!TextUtils.isEmpty(color_gradient)) {
            if (color_gradient.equals("0")) {
                rliBackground.setBackgroundResource(R.drawable.purple);
            }
            if (color_gradient.equals("1")) {
                rliBackground.setBackgroundResource(R.drawable.blue);
            }

            if (color_gradient.equals("2")) {
                rliBackground.setBackgroundResource(R.drawable.orange);
            }

            if (color_gradient.equals("3")) {
                rliBackground.setBackgroundResource(R.drawable.blue_green);
            }

            if (color_gradient.equals("4")) {
                rliBackground.setBackgroundResource(R.drawable.pink);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dash_board, menu);
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

    @Override


    public void onButtonClickListner(String total) {
        textView5.setText("$ " + total);
    }

    @Override
    public void onButtonMinusClickListner(String total) {

    }
}
