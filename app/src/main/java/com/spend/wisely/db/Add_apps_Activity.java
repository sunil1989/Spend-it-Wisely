package com.spend.wisely.db;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.spend.wisely.ApplicationAdapter;
import com.spend.wisely.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Add_apps_Activity extends Activity implements AdapterView.OnItemClickListener, View.OnClickListener {
    private PackageManager packageManager = null;
    private List<ApplicationInfo> applist = null;
    private List<String> addlist = null;
    private InstallappAdapter listadaptor = null;
    private ListView mlist;
    private Button mDone;
    private EditText mEditSearh;
    Appsdb appdb;
    ImageView mProfile_back;
    RelativeLayout rliBackground;
    SharedPreferencesManager sharedPref = new SharedPreferencesManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_add_apps);
        appdb = new Appsdb(getApplicationContext());
        rliBackground = (RelativeLayout) findViewById(R.id.rli);
        mProfile_back = (ImageView) findViewById(R.id.imageView3);
        mProfile_back.setOnClickListener(this);
        mDone = (Button) findViewById(R.id.button);
        mDone.setOnClickListener(this);
        mlist = (ListView) findViewById(R.id.list);
        mEditSearh = (EditText) findViewById(R.id.inputSearch);
        packageManager = getPackageManager();
        applist = new ArrayList<ApplicationInfo>();
        addlist = new ArrayList<String>();
        applist = checkForLaunchIntent(packageManager.getInstalledApplications(PackageManager.GET_META_DATA));
        listadaptor = new InstallappAdapter(Add_apps_Activity.this,
                R.layout.install_app_row, applist, addlist);
        mlist.setAdapter(listadaptor);
        mlist.setOnItemClickListener(this);


        mEditSearh.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                String text = mEditSearh.getText().toString().toLowerCase(Locale.getDefault());
                listadaptor.filter(text);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    public void onResume() {
        super.onResume();
        String color_gradient = sharedPref.getBackground(this);

        if (!TextUtils.isEmpty(color_gradient)) {
            if (color_gradient.equals("0")) {
                rliBackground.setBackgroundResource(R.drawable.purple);
                mEditSearh.setBackgroundColor(Color.parseColor("#FFC4075D"));
                mDone.setBackgroundColor(Color.parseColor("#FFC4075D"));

            }
            if (color_gradient.equals("1")) {
                rliBackground.setBackgroundResource(R.drawable.blue);
                mEditSearh.setBackgroundColor(Color.parseColor("#0C3FAC"));
                mDone.setBackgroundColor(Color.parseColor("#0C3FAC"));
            }

            if (color_gradient.equals("2")) {
                rliBackground.setBackgroundResource(R.drawable.orange);
                mEditSearh.setBackgroundColor(Color.parseColor("#FFFE4332"));
                mDone.setBackgroundColor(Color.parseColor("#FFFE4332"));
            }

            if (color_gradient.equals("3")) {
                rliBackground.setBackgroundResource(R.drawable.blue_green);
                mEditSearh.setBackgroundColor(Color.parseColor("#FF10C0BC"));
                mDone.setBackgroundColor(Color.parseColor("#FF10C0BC"));
            }

            if (color_gradient.equals("4")) {
                rliBackground.setBackgroundResource(R.drawable.pink);
                mEditSearh.setBackgroundColor(Color.parseColor("#FFEF2765"));
                mDone.setBackgroundColor(Color.parseColor("#FFEF2765"));
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_apps_, menu);
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


    private List<ApplicationInfo> checkForLaunchIntent(List<ApplicationInfo> list) {
        ArrayList<ApplicationInfo> applist = new ArrayList<ApplicationInfo>();
        for (ApplicationInfo info : list) {
            try {
                if (null != packageManager.getLaunchIntentForPackage(info.packageName)) {
                    applist.add(info);
                    addlist.add("ADD");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return applist;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        // Toast.makeText(getApplicationContext(),""+position,Toast.LENGTH_LONG).show();


        String packgeName = applist.get(position).packageName;

        addlist.add(position, "ADDED");
        listadaptor.notifyDataSetChanged();
        // Button added= (Button)view.findViewById(R.id.add);

        // added.setText("Added");
        System.out.println("========Add app Package Name==========" + packgeName);
        appdb.Install_app(packgeName);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.button:
                //Intent intent=new Intent(Add_apps_Activity.this,WhiteListActivity.class);
                //startActivity(intent);
                finish();
                break;

            case R.id.imageView3:
                finish();
                break;
        }
    }
}
