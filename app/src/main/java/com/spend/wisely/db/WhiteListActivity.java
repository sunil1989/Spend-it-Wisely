package com.spend.wisely.db;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.spend.wisely.ApplicationAdapter;
import com.spend.wisely.R;

import java.util.ArrayList;

import it.sephiroth.android.library.widget.AdapterView;
import it.sephiroth.android.library.widget.HListView;

public class WhiteListActivity extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private Button addApp;
    HListView horizontallistview;
    ArrayList<Apps> list;
    Appsdb appdb;
    WhiteListAdapter listadaptor;
    TextView mEditApp, removeapp, titleEdit;
    boolean edit = false;
    String pkg = "";
    ImageView mProfile_back;
    RelativeLayout rliBackground;
    SharedPreferencesManager sharedPref = new SharedPreferencesManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_white_list);

        System.out.println("============pkg=========");
        rliBackground = (RelativeLayout) findViewById(R.id.rli);

        mProfile_back = (ImageView) findViewById(R.id.imageView3);
        mProfile_back.setOnClickListener(this);
        // mProfile_back.setVisibility(View.VISIBLE);
        Bundle extras = getIntent().getExtras();
        if (extras == null) {
        } else {
            appdb = new Appsdb(getApplicationContext());
            list = appdb.getInstallApp();
            pkg = extras.getString("pkg");
            System.out.println("============pkg=========" + pkg);


            if (list.size() > 0) {
                // Toast.makeText(getApplicationContext(),"List khali Nahi hai",Toast.LENGTH_LONG).show();
                for (int i = 0; i < list.size(); i++) {

                    String pakageName = list.get(i).getPackageName();

                    if (pakageName.equalsIgnoreCase(pkg)) {
                        finish();
                    } else {
                        // mProfile_back.setVisibility(View.GONE);
                    }


                }
            } else {
                // Toast.makeText(getApplicationContext(),"List khali hai",Toast.LENGTH_LONG).show();

                finish();
            }


        }


        horizontallistview = (HListView) findViewById(R.id.hListView1);
        horizontallistview.setOnItemClickListener(this);
        addApp = (Button) findViewById(R.id.button);
        addApp.setOnClickListener(this);

        mEditApp = (TextView) findViewById(R.id.edit_apps);
        mEditApp.setAlpha(0.5f);
        mEditApp.setOnClickListener(this);
        removeapp = (TextView) findViewById(R.id.remove_app);
        titleEdit = (TextView) findViewById(R.id.textView4);

    }


    public void onResume() {
        super.onResume();


        appdb = new Appsdb(getApplicationContext());
        list = appdb.getInstallApp();
        listadaptor = new WhiteListAdapter(WhiteListActivity.this,
                R.layout.horizontal_data_row, list);

        horizontallistview.setAdapter(listadaptor);

        String color_gradient = sharedPref.getBackground(this);

        if (!TextUtils.isEmpty(color_gradient)) {
            if (color_gradient.equals("0")) {
                rliBackground.setBackgroundResource(R.drawable.purple);
                addApp.setBackgroundColor(Color.parseColor("#FFC4075D"));
            }
            if (color_gradient.equals("1")) {
                rliBackground.setBackgroundResource(R.drawable.blue);
                addApp.setBackgroundColor(Color.parseColor("#0C3FAC"));
            }

            if (color_gradient.equals("2")) {
                rliBackground.setBackgroundResource(R.drawable.orange);
                addApp.setBackgroundColor(Color.parseColor("#FFFE4332"));
            }

            if (color_gradient.equals("3")) {
                rliBackground.setBackgroundResource(R.drawable.blue_green);
                addApp.setBackgroundColor(Color.parseColor("#009696"));
            }

            if (color_gradient.equals("4")) {
                rliBackground.setBackgroundResource(R.drawable.pink);
                addApp.setBackgroundColor(Color.parseColor("#DB1F5E"));
            }
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_white_list, menu);
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
            case R.id.button:
                if (edit) {
                    edit = false;
                    mEditApp.setVisibility(View.VISIBLE);
                    removeapp.setVisibility(View.GONE);
                    titleEdit.setText("Whitelist Apps");
                    addApp.setText("ADD APPS TO WHITELIST");
                    appdb = new Appsdb(getApplicationContext());
                    list = appdb.getInstallApp();
                    listadaptor = new WhiteListAdapter(WhiteListActivity.this,
                            R.layout.horizontal_data_row, list);

                    horizontallistview.setAdapter(listadaptor);
                } else {
                    Intent in = new Intent(this, Add_apps_Activity.class);
                    startActivity(in);
                }
                break;

            case R.id.edit_apps:
                mEditApp.setVisibility(View.GONE);
                edit = true;
                titleEdit.setText("Edit Whitelist Apps");
                addApp.setText("DONE");
                removeapp.setVisibility(View.VISIBLE);
                appdb = new Appsdb(getApplicationContext());
                list = appdb.getInstallApp();
                listadaptor = new WhiteListAdapter(WhiteListActivity.this,
                        R.layout.horizontal_data_row, list, true);

                horizontallistview.setAdapter(listadaptor);
                break;


            case R.id.imageView3:
                finish();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

        if (edit) {
            String id = list.get(position).getId();
            appdb.InstallDelete(id);
            appdb = new Appsdb(getApplicationContext());
            list = appdb.getInstallApp();
            listadaptor = new WhiteListAdapter(WhiteListActivity.this,
                    R.layout.horizontal_data_row, list, true);

            horizontallistview.setAdapter(listadaptor);
        }
    }


    @Override
    public void onBackPressed() {

    }
}
