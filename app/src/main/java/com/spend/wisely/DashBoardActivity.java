package com.spend.wisely;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.spend.wisely.com.currency.AudioData;
import com.spend.wisely.com.currency.Horizontal_Adapter;

import java.util.ArrayList;

import it.sephiroth.android.library.widget.HListView;


public class DashBoardActivity extends Activity {
    TextView mMonthtext,mEditText3;
    HListView horizontallistview;

    ArrayList<AudioData>horizontaldata;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_dash_board);


        Resources r = getResources();
        float dp = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, 72, r.getDisplayMetrics());

        System.out.println("=============dp========"+dp);

        horizontaldata=new ArrayList<>();
        horizontaldata.add(new AudioData(R.drawable.face_icon,"$ 280"));
        horizontaldata.add(new AudioData(R.drawable.twittr_icon,"$ 120"));
        horizontaldata.add(new AudioData(R.drawable.yahoo_icon,"$ 100"));
        horizontaldata.add(new AudioData(R.drawable.s_icon,"$ 52"));
        horizontaldata.add(new AudioData(R.drawable.whats_icon,"$ 48"));

        horizontallistview = (HListView) findViewById(R.id.hListView1);
        Horizontal_Adapter adp = new Horizontal_Adapter(this, horizontaldata);
        horizontallistview.setAdapter(adp);
       // horizontallistview.setOnItemClickListener(this);

        Typeface face= Typeface.createFromAsset(getAssets(), "ptsans.ttf");
        TextView textView4 = (TextView) findViewById(R.id.textView4);

        Typeface face1= Typeface.createFromAsset(getAssets(), "PTC75F.ttf");

        System.out.println("============================change===");

        TextView textview1 = (TextView) findViewById(R.id.textview1);

        TextView textview2 = (TextView) findViewById(R.id.textview2);

        TextView textView6 = (TextView) findViewById(R.id.textView6);

        TextView textView5 = (TextView) findViewById(R.id.textView5);

        textView4.setTypeface(face);
        textview1.setTypeface(face);
        textview2.setTypeface(face);
        textView6.setTypeface(face);
        textView5.setTypeface(face1);



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
}
