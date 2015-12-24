package com.spend.wisely;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.spend.wisely.com.onbordinganim.Main1Activity;
import com.spend.wisely.db.AppColorAdapter;
import com.spend.wisely.db.AppColorSchemeActvity;
import com.spend.wisely.db.HelpusActivity;
import com.spend.wisely.db.Settings;
import com.spend.wisely.db.SettingsAdapter;
import com.spend.wisely.db.SharedPreferencesManager;
import com.spend.wisely.db.WhiteListActivity;

import java.util.ArrayList;


public class SettingsActivity extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener {

    ArrayList<Settings> setting = new ArrayList<Settings>();
    private SettingsAdapter settingsAdapter = null;
    private ListView mListview;
    private ImageView mImg;
    RelativeLayout rliBackground;
    SharedPreferencesManager sharedPref = new SharedPreferencesManager();
    Button mUnlockButton;
    TextView textView4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_settings);

        setting.add(new Settings("WHITELIST APPS"));
        setting.add(new Settings("HELP US"));
        setting.add(new Settings("APP COLOR SCHEME"));
        setting.add(new Settings("LINK ACCOUNTS"));
        setting.add(new Settings("ABOUT"));
        setting.add(new Settings("FEEDBACK"));
        setting.add(new Settings("TOUR"));

        mListview = (ListView) findViewById(R.id.list);

        settingsAdapter = new SettingsAdapter(SettingsActivity.this,
                R.layout.horizontal_data_row, setting);
        mListview.setAdapter(settingsAdapter);

        rliBackground = (RelativeLayout) findViewById(R.id.rli);

        mImg = (ImageView) findViewById(R.id.imageView3);
        mImg.setOnClickListener(this);

        mListview.setOnItemClickListener(this);

        mUnlockButton = (Button) findViewById(R.id.unlock_full);

        textView4 = (TextView) findViewById(R.id.textView4);

        Typeface face = Typeface.createFromAsset(getAssets(), "ptsans.ttf");
        textView4.setTypeface(face);

    }

    public void onResume() {
        super.onResume();
        String color_gradient = sharedPref.getBackground(this);

        if (!TextUtils.isEmpty(color_gradient)) {
            if (color_gradient.equals("0")) {
                rliBackground.setBackgroundResource(R.drawable.purple);
                mUnlockButton.setBackgroundColor(Color.parseColor("#FFC4075D"));

            }
            if (color_gradient.equals("1")) {
                rliBackground.setBackgroundResource(R.drawable.blue);
                mUnlockButton.setBackgroundColor(Color.parseColor("#0C3FAC"));
            }

            if (color_gradient.equals("2")) {
                rliBackground.setBackgroundResource(R.drawable.orange);
                mUnlockButton.setBackgroundColor(Color.parseColor("#FFFE4332"));
            }

            if (color_gradient.equals("3")) {
                rliBackground.setBackgroundResource(R.drawable.blue_green);
                mUnlockButton.setBackgroundColor(Color.parseColor("#009696"));
            }

            if (color_gradient.equals("4")) {
                rliBackground.setBackgroundResource(R.drawable.pink);
                mUnlockButton.setBackgroundColor(Color.parseColor("#DB1F5E"));

            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_settings, menu);
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
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.imageView3:

                finish();
                break;
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        if (position == 2) {
            Intent in = new Intent(this, AppColorSchemeActvity.class);
            startActivity(in);
        }

        if (position == 0) {
            Intent in = new Intent(this, WhiteListActivity.class);
            startActivity(in);
        }
        if (position == 1) {
            Intent in = new Intent(this, HelpusActivity.class);
            startActivity(in);
        }

        if (position == 6) {
            Intent mainIntent = new Intent(SettingsActivity.this, Main1Activity.class);
            SettingsActivity.this.startActivity(mainIntent);
        }

    }
}
