package com.spend.wisely;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.spend.wisely.calendar.CalendarAdapter;
import com.spend.wisely.calendar.Utility;
import com.spend.wisely.calendar.WeeklyAdapter;
import com.spend.wisely.calendar.Weeks;
import com.spend.wisely.com.currency.AudioData;
import com.spend.wisely.com.currency.Horizontal_Adapter;
import com.spend.wisely.db.Apps;
import com.spend.wisely.db.Appsdb;
import com.spend.wisely.db.ProfileActivity;
import com.spend.wisely.db.SharedPreferencesManager;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import it.sephiroth.android.library.widget.HListView;


public class DashBoardActivity extends Activity implements ApplicationAdapter.customButtonListener, View.OnClickListener {


    public GregorianCalendar month, itemmonth;// calendar instances.

    public CalendarAdapter adapter;// adapter instance
    public Handler handler;// for grabbing some event values for showing the dot
    // marker.
    public ArrayList<String> items; // container to store calendar items which
    // needs showing the event marker
    ArrayList<String> event;
    LinearLayout rLayout;
    ArrayList<String> date;
    ArrayList<String> desc;


    Calendar c, c1;
    SimpleDateFormat df, df1;
    String formattedDate, formattedDate1;
    TextView mMonthtext, mEditText3;
    HListView horizontallistview, weeklistView;

    ArrayList<AudioData> horizontaldata;
    private ApplicationAdapter listadaptor = null;
    private WeeklyAdapter weekadaptor = null;
    ArrayList<Apps> list;
    Appsdb appdb;
    String pkg = "";
    ImageView mPrfileImage, mSettingImage, imageView5;
    TextView textView5;
    RelativeLayout rliBackground;
    SharedPreferencesManager sharedPref = new SharedPreferencesManager();
    TextView textView6;
    TextView textview2;
    TextView textview12;
    TextView textview1;
    TextView title;
    ArrayList<Weeks> weeks = new ArrayList<Weeks>();
    LinearLayout headar;
    int WeekNumber;
    String mMonth, mYear;
    String printDate;
    String printDate2;
    boolean flag = true;
    boolean flag1 = true;
    RelativeLayout relativeLayout;
    TextView unlock_full;
    TextView textview23;
    GridView gridview;
    ArrayList<Month> monthlist = new ArrayList<Month>();
    MonthAdapter monthAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_dash_board);

        headar = (LinearLayout) findViewById(R.id.header);
        month = (GregorianCalendar) GregorianCalendar.getInstance();
        itemmonth = (GregorianCalendar) month.clone();
        adapter = new CalendarAdapter(this, month);
        handler = new Handler();
        handler.post(calendarUpdater);

        weeks.add(new Weeks("MON", "200"));
        weeks.add(new Weeks("TUE", "500"));
        weeks.add(new Weeks("WED", "600"));
        weeks.add(new Weeks("THU", "700"));
        weeks.add(new Weeks("FRI", "800"));
        weeks.add(new Weeks("SAT", "300"));
        weeks.add(new Weeks("SUN", "500"));


        monthlist.add(new Month("JAN", "200"));
        monthlist.add(new Month("FEB", "500"));
        monthlist.add(new Month("MAR", "600"));
        monthlist.add(new Month("APR", "700"));
        monthlist.add(new Month("MAY", "800"));
        monthlist.add(new Month("JUN", "900"));
        monthlist.add(new Month("JUL", "400"));
        monthlist.add(new Month("AUG", "300"));
        monthlist.add(new Month("SEP", "500"));
        monthlist.add(new Month("OCT", "200"));
        monthlist.add(new Month("NOV", "100"));
        monthlist.add(new Month("DEC", "700"));

        Bundle extras = getIntent().getExtras();
        if (extras == null) {
        } else {
            pkg = extras.getString("pkg");
            System.out.println("============pkg=========" + pkg);
            finish();
        }


        c = Calendar.getInstance();

        //  WeekNumber=c.get(Calendar.WEEK_OF_YEAR);

        c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        SimpleDateFormat mDF = new SimpleDateFormat("MMM dd");
        printDate = mDF.format(c.getTime());
        c.add(Calendar.DAY_OF_MONTH, 6);
        SimpleDateFormat mDF1 = new SimpleDateFormat("dd");
        printDate2 = mDF1.format(c.getTime());

        SimpleDateFormat mDF2 = new SimpleDateFormat("MMMM");
        mMonth = mDF2.format(c.getTime());

        SimpleDateFormat mDF3 = new SimpleDateFormat("yyyy");
        mYear = mDF3.format(c.getTime());
        //  c1 = Calendar.getInstance();
        // c1.add(Calendar.DATE,7);
        // System.out.println("Current time => " + c.getTime());

        //df = new SimpleDateFormat("MMM dd - MM");

       /* df = new SimpleDateFormat("MMM dd");
        formattedDate = df.format(c.getTime());

        df1 = new SimpleDateFormat("dd");
        formattedDate1 = df1.format(c1.getTime());*/
        //textview.setText(formattedDate);
        unlock_full = (TextView) findViewById(R.id.unlock_full);
        textview12 = (TextView) findViewById(R.id.textview12);
        textview12.setOnClickListener(this);
        textview12.setAlpha(0.5f);
        textview1 = (TextView) findViewById(R.id.textview1);
        title = (TextView) findViewById(R.id.title);
        textview23 = (TextView) findViewById(R.id.textview23);
        textview23.setOnClickListener(this);
        //title.setText(formattedDate+" "+formattedDate1);
        title.setText(printDate + "-" + printDate2);
        //title.setText(android.text.format.DateFormat.format("MMMM yyyy", month));
        //title.setText(android.text.format.DateFormat.format("MMM dd - MM", month));
        RelativeLayout previous = (RelativeLayout) findViewById(R.id.previous);

        previous.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                setPreviousMonth();
                // refreshCalendar();
            }
        });

        RelativeLayout next = (RelativeLayout) findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                setNextMonth();
                //  refreshCalendar();

            }
        });


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
        weeklistView = (HListView) findViewById(R.id.hListView2);


        //Horizontal_Adapter adp = new Horizontal_Adapter(this, horizontaldata);
        // horizontallistview.setAdapter(adp);
        // horizontallistview.setOnItemClickListener(this);

        Typeface face = Typeface.createFromAsset(getAssets(), "ptsans.ttf");
        TextView textView4 = (TextView) findViewById(R.id.textView4);

        Typeface face1 = Typeface.createFromAsset(getAssets(), "PTC75F.ttf");

        System.out.println("====================fgfgfggfgf========change===");

        TextView textview1 = (TextView) findViewById(R.id.textview1);
        textview2 = (TextView) findViewById(R.id.textview2);
        textview2.setAlpha(0.5f);

        textview2.setOnClickListener(this);
        //  textview2.setAlpha(0.5f);

        textView6 = (TextView) findViewById(R.id.textView6);
        textView6.setAlpha(0.5f);
        textView5 = (TextView) findViewById(R.id.textView5);
        rliBackground = (RelativeLayout) findViewById(R.id.rli);
        relativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout);
        imageView5 = (ImageView) findViewById(R.id.imageView5);
        gridview = (GridView) findViewById(R.id.gridview);
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

        //for(int i=0; i<)

        listadaptor = new ApplicationAdapter(DashBoardActivity.this,
                R.layout.horizontal_data_row, list, String.valueOf(resu));
        listadaptor.setCustomButtonListner(this);
        horizontallistview.setAdapter(listadaptor);


        weekadaptor = new WeeklyAdapter(DashBoardActivity.this,
                R.layout.week_row, weeks);

        weeklistView.setAdapter(weekadaptor);


        monthAdapter = new MonthAdapter(DashBoardActivity.this, monthlist);
        gridview.setAdapter(monthAdapter);

        setDate();
    }


    public void refreshCalendar() {
        TextView title = (TextView) findViewById(R.id.title);

        adapter.refreshDays();
        adapter.notifyDataSetChanged();
        handler.post(calendarUpdater); // generate some calendar items


        System.out.println("=========Month=============" + month);
        title.setText(android.text.format.DateFormat.format("MMM dd -MM", month));
        // title.setText(android.text.format.DateFormat.format("MMMM yyyy", month));
    }

    public Runnable calendarUpdater = new Runnable() {

        @Override
        public void run() {
            //   items.clear();

            // Print dates of the current week
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
            String itemvalue;
            event = Utility.readCalendarEvent(DashBoardActivity.this);
            Log.d("=====Event====", event.toString());
            Log.d("=====Date ARRAY====", Utility.startDates.toString());

            for (int i = 0; i < Utility.startDates.size(); i++) {
                itemvalue = df.format(itemmonth.getTime());
                itemmonth.add(GregorianCalendar.DATE, 1);
                // items.add(Utility.startDates.get(i).toString());
            }
            // adapter.setItems(items);
            adapter.notifyDataSetChanged();
        }
    };


    protected void setPreviousMonth() {

       /* c.add(Calendar.DATE, -7);
        formattedDate = df.format(c.getTime());

        c1.add(Calendar.DATE, -7);
        formattedDate1 = df1.format(c1.getTime());
        Log.v("PREVIOUS DATE : ", formattedDate);
       // title.setText(formattedDate);
        title.setText(formattedDate+" "+formattedDate1);*/

        WeekNumber--;
        String ss = getNextWeek(WeekNumber);
        System.out.println("ss " + ss);
        title.setText(ss);
      /*  if (month.get(GregorianCalendar.MONTH) == month
                .getActualMinimum(GregorianCalendar.MONTH)) {
            month.set((month.get(GregorianCalendar.YEAR) - 1),
                    month.getActualMaximum(GregorianCalendar.MONTH), 1);
        } else {
            month.set(GregorianCalendar.MONTH,
                    month.get(GregorianCalendar.MONTH) - 1);
        }*/

    }


    protected void setNextMonth() {

      /*  c.add(Calendar.DATE, 7);
        formattedDate = df.format(c.getTime());

        c1.add(Calendar.DATE, 7);
        formattedDate1 = df1.format(c1.getTime());

        Log.v("NEXT DATE : ", formattedDate);
        //title.setText(formattedDate);
        title.setText(formattedDate+" "+formattedDate1);*/


        WeekNumber++;
        String ss = getNextWeek(WeekNumber);
        System.out.println("ss " + ss);
        title.setText(ss);
       /* if (month.get(GregorianCalendar.MONTH) == month
                .getActualMaximum(GregorianCalendar.MONTH)) {

            month.set((month.get(GregorianCalendar.YEAR) + 1),
                    month.getActualMinimum(GregorianCalendar.MONTH), 1);
        } else {
            Toast.makeText(getApplicationContext(),"Hello",Toast.LENGTH_LONG).show();
           month.set(GregorianCalendar.MONTH,
                    month.get(GregorianCalendar.MONTH) + 1,month.get(GregorianCalendar.DAY_OF_MONTH) + 1);

             *//* month.set(GregorianCalendar.MONTH,
                  month.get(GregorianCalendar.DAY_OF_MONTH));*//*
        }*/

    }

    private String getNextWeek(int weekFromToday) {
        System.out.println("Pass Wee " + weekFromToday);
        Calendar mCalendar = Calendar.getInstance();
        mCalendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

        mCalendar.set(Calendar.WEEK_OF_YEAR,
                mCalendar.get(Calendar.WEEK_OF_YEAR) + weekFromToday);

        SimpleDateFormat mDF = new SimpleDateFormat("MMM dd");
        String printDate = mDF.format(mCalendar.getTime());
        System.out.println(printDate);

        //gestureEvent.setText(reportDate);
        mCalendar.add(Calendar.DAY_OF_MONTH, 6);
        SimpleDateFormat mDF1 = new SimpleDateFormat("dd");
        String printDate2 = mDF1.format(mCalendar.getTime());
        System.out.println(printDate + " >> " + printDate2);

        return printDate + "-" + printDate2;
    }


    private void setDate() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        SimpleDateFormat mDF = new SimpleDateFormat("dd");
        String printDate = mDF.format(c.getTime());
        c.add(Calendar.DAY_OF_MONTH, 6);
        SimpleDateFormat mDF1 = new SimpleDateFormat("dd MMM");
        String printDate2 = mDF1.format(c.getTime());

        textview2.setText(printDate + "-" + printDate2);
      /*  long date = System.currentTimeMillis();

       // SimpleDateFormat sdf = new SimpleDateFormat("MMM MM dd, yyyy h:mm a");
        SimpleDateFormat sdf = new SimpleDateFormat("dd - MM MMM");
        String dateString = sdf.format(date);
        textview2.setText(dateString);*/
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

        textView5.setText("$ " + String.format("%.0f", Float.parseFloat(total)));
        //textView5.setText("$ " + total);
    }

    @Override
    public void onButtonMinusClickListner(String total) {

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.textview2:

                if (flag) {


                    headar.setVisibility(View.VISIBLE);
                    weeklistView.setVisibility(View.VISIBLE);
                    textview2.setText(mMonth);
                    textview1.setText(printDate + "-" + printDate2);
                    textview1.setTextColor(Color.WHITE);

                    textview12.setVisibility(View.VISIBLE);
                    textview12.setAlpha(0.5f);

                    Toast.makeText(getApplicationContext(), "Hello1", Toast.LENGTH_LONG).show();
                    flag = false;
                    flag1 = true;


                } else {
                    flag1 = false;
                    flag = true;
                    headar.setVisibility(View.GONE);
                    horizontallistview.setVisibility(View.GONE);
                    weeklistView.setVisibility(View.GONE);
                    imageView5.setVisibility(View.GONE);
                    relativeLayout.setVisibility(View.GONE);
                    unlock_full.setVisibility(View.VISIBLE);
                    textview12.setText(printDate + "-" + printDate2);
                    textview1.setText(mMonth);
                    textview2.setText(mYear);
                    textview23.setVisibility(View.VISIBLE);
                    textview23.setText(mYear);
                    textview23.setAlpha(0.5f);
                    textview2.setVisibility(View.GONE);

                    Toast.makeText(getApplicationContext(), "Hello2", Toast.LENGTH_LONG).show();
                }
                break;

            case R.id.textview12:

                if (flag1) {
                    flag = true;
                    flag1 = false;
                    headar.setVisibility(View.GONE);
                    weeklistView.setVisibility(View.GONE);
                    textview2.setText(printDate + "-" + printDate2);
                    textview2.setAlpha(0.5f);
                    textview1.setText("TODAY");
                    textview1.setTextColor(Color.WHITE);
                    //textview1.setAlpha(0.5f);

                    textview12.setVisibility(View.GONE);
                    textview12.setAlpha(0.5f);

                    Toast.makeText(getApplicationContext(), "Hello3", Toast.LENGTH_LONG).show();
                } else {
                    flag1 = true;
                    flag = true;

                    textview12.setText("TODAY");
                    headar.setVisibility(View.VISIBLE);
                    horizontallistview.setVisibility(View.VISIBLE);
                    weeklistView.setVisibility(View.VISIBLE);
                    imageView5.setVisibility(View.VISIBLE);
                    relativeLayout.setVisibility(View.VISIBLE);
                    unlock_full.setVisibility(View.GONE);
                    textview1.setText(printDate + "-" + printDate2);
                    textview2.setText(mMonth);
                    Toast.makeText(getApplicationContext(), "Hello4", Toast.LENGTH_LONG).show();
                    textview23.setVisibility(View.GONE);
                    textview2.setVisibility(View.VISIBLE);
                }
                break;

            case R.id.textview23:
                relativeLayout.setVisibility(View.VISIBLE);
                gridview.setVisibility(View.VISIBLE);
                weeklistView.setVisibility(View.GONE);
                unlock_full.setVisibility(View.GONE);
                headar.setVisibility(View.VISIBLE);
                horizontallistview.setVisibility(View.VISIBLE);
                imageView5.setVisibility(View.VISIBLE);
                break;


        }
    }
}
