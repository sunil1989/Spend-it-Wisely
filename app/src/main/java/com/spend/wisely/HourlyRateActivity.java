package com.spend.wisely;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.spend.wisely.com.currency.Currency;
import com.spend.wisely.com.currency.CurrencyAdapter;

import java.util.ArrayList;
import java.util.StringTokenizer;


public class HourlyRateActivity extends Activity implements AdapterView.OnItemSelectedListener, View.OnClickListener, View.OnKeyListener {
    CurrencyAdapter adapter;
    private Spinner mSpinner;
    ArrayList<Currency>mCurrency;
    EditText mEditText1,mEditText2;
    Button mButton;
    float result;
    TextView mMonthtext,mEditText3;
    TextView mHourweek;
    String months;
    String months_split,hour_split;
    InputMethodManager imm;
    String sign;
    boolean ab=false;
    String signss;
    TextView  textView10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_hourly_rate);
        String rs = getResources().getString(R.string.Rs);
        mCurrency=new ArrayList<Currency>();
       /* mCurrency.add(new Currency("USD","$▼"));
        mCurrency.add(new Currency("EUR","€▼"));
        mCurrency.add(new Currency("GBP","£▼"));
        mCurrency.add(new Currency("CAD","$▼"));
        mCurrency.add(new Currency("AUD","$▼"));
        mCurrency.add(new Currency("INR",rs+"▼"));*/

        mCurrency.add(new Currency("USD","$","▼"));
        mCurrency.add(new Currency("EUR","€","▼"));
        mCurrency.add(new Currency("GBP","£","▼"));
        mCurrency.add(new Currency("CAD","$","▼"));
        mCurrency.add(new Currency("AUD","$","▼"));
        mCurrency.add(new Currency("INR",rs,"▼"));


        inIt();



    }

    private void inIt() {
        mButton=(Button)findViewById(R.id.button2);
        mButton.setOnClickListener(this);
        mEditText1=(EditText)findViewById(R.id.editText1);
        mEditText2=(EditText)findViewById(R.id.editText2);
        mEditText3=(TextView)findViewById(R.id.editText3);
        mMonthtext=(TextView)findViewById(R.id.textView7);
        mHourweek=(TextView)findViewById(R.id.textView8);
        TextView textView3 = (TextView) findViewById(R.id.textView);
        TextView textView4 = (TextView) findViewById(R.id.textView2);
        TextView textView5 = (TextView) findViewById(R.id.textView3);
        textView10 = (TextView) findViewById(R.id. textView10);

        Typeface face= Typeface.createFromAsset(getAssets(), "ptsans.ttf");
        textView3.setTypeface(face);
        textView4.setTypeface(face);
        mEditText1.setTypeface(face);
        mEditText2.setTypeface(face);
        textView5.setTypeface(face);

        mEditText1.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                if(!TextUtils.isEmpty(mEditText1.getText().toString())){

                months=   mEditText1.getText().toString();

              /*  mMonthtext.setVisibility(View.VISIBLE);
                mMonthtext.setPadding(0,0,100,0);
                //mMonthtext.setText("/month");
                }
                else{
                    mMonthtext.setVisibility(View.GONE);
                    mEditText1.setHint("Your Monthly Income");
                }*/
            }
            }



            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            public void afterTextChanged(Editable s) {

            }
        });



       mEditText1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
              if(!TextUtils.isEmpty(mEditText1.getText().toString())) {
                        StringTokenizer st = new StringTokenizer(months, "/");
                         months_split = st.nextToken();
                         mEditText1.setText(months_split);

                     /*  *//**//* StringTokenizer st = new StringTokenizer(hour_split, " ");
                        hour_split = st.nextToken();
                        mEditText2.setText(hour_split);*//**//**/
                    }

                /*   if(!TextUtils.isEmpty(mEditText1.getText().toString())) {
                        StringTokenizer st = new StringTokenizer(months, "/");
                        months_split = st.nextToken();
                        mEditText1.setText(months_split);
                        mMonthtext.setVisibility(View.VISIBLE);
                    }*/

                    //Toast.makeText(getApplicationContext(), "got the focus", Toast.LENGTH_LONG).show();
                }else {
                   // Toast.makeText(getApplicationContext(), "lost the focus", Toast.LENGTH_LONG).show();
                }




            }
        });

        mEditText2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){

                    mEditText1.setText(months+" /month");
                   if(!TextUtils.isEmpty(mEditText1.getText().toString())) {
                        StringTokenizer st = new StringTokenizer(months, "/");
                        months_split = st.nextToken();
                      //  mEditText1.setText(months_split);
                        //mMonthtext.setVisibility(View.GONE);

/*
                        StringTokenizer st = new StringTokenizer(hour_split, " ");
                         hour_split = st.nextToken();
                         mEditText2.setText(hour_split);*/
                    }
                    else{

                   }

                  //  mEditText2.setHint("");
                  //  mHourweek.setVisibility(View.VISIBLE);*/
                 // Toast.makeText(getApplicationContext(), "got the focus", Toast.LENGTH_LONG).show();
                }else {

                   /* if(!TextUtils.isEmpty(mEditText1.getText().toString())) {
                        StringTokenizer st = new StringTokenizer(months, "/");
                         months_split = st.nextToken();
                         mEditText1.setText(months);

                       *//* StringTokenizer st = new StringTokenizer(hour_split, " ");
                        hour_split = st.nextToken();
                        mEditText2.setText(hour_split);*//*
                    }*/
                   /* if(ab==true){

                    }else{
                    mHourweek.setVisibility(View.GONE);
                    mEditText2.setText(hour_split+" hours/week");
                  // Toast.makeText(getApplicationContext(), "lost the focus", Toast.LENGTH_LONG).show();
                }*/
                }
            }
        });



        mEditText2.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {



                hour_split=mEditText2.getText().toString();


               // mMonthtext.setVisibility(View.GONE);
                StringTokenizer st = new StringTokenizer(months, "/");
                months_split = st.nextToken();
                System.out.println("=========months_split===="+months_split);

               if(!TextUtils.isEmpty(mEditText1.getText().toString())){

                    try{
                        result=Float.parseFloat(months_split)/Float.parseFloat(mEditText2.getText().toString());


                        System.out.println("=============result====="+result);
                        mEditText3.setText(signss +" "+ String.format("%.0f",result)+" /h");
                        textView10.setVisibility(View.GONE);
                        mButton.setVisibility(View.VISIBLE);
                    }catch (NumberFormatException e){
                        e.printStackTrace();
                       // mEditText3.setText("");
                    }



                }
             /*   if(!s.equals("") )
                { //do your work here }
                }
*/
            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            public void afterTextChanged(Editable s) {

            }
        });

        mEditText2.setOnKeyListener(this);


        mSpinner=(Spinner) findViewById(R.id.spinner1);

         adapter = new CurrencyAdapter(this,
                 R.layout.currency_row,
                mCurrency);


        mSpinner.setAdapter(adapter);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        mSpinner.setOnItemSelectedListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_hourly_rate, menu);
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
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

      // mSpinner.setSelection(adapter.getPosition(mCurrency.get(position).getCurrencyName().toString()));

        sign=mCurrency.get(position).getCurrecnySign();
        StringTokenizer st = new StringTokenizer(sign, "▼");
        signss= st.nextToken();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {
        Intent in =new Intent(this,DashBoardActivity.class);
        startActivity(in);
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)
        {

              hour_split =mEditText2.getText().toString();
            StringTokenizer st = new StringTokenizer(hour_split, " ");
               hour_split =st.nextToken();
               mEditText2.setText(hour_split+" hours/week");

                //  mEditText1.setText(months_split);
                //mMonthtext.setVisibility(View.GONE);
          /*  mHourweek.setVisibility(View.GONE);
            ab=true;
            String s= mEditText2.getText().toString();
            StringTokenizer st = new StringTokenizer(s, " ");
            String ms= st.nextToken();
            System.out.println("===ms==========="+ms);
            mEditText2.setText(ms+" hours/week");*/
           /* StringTokenizer st = new StringTokenizer(hour_split, "/");
            months_split = st.nextToken();
            mEditText2.setText(hour_split);*/
            // code to hide the soft keyboard
            //Toast.makeText(getApplicationContext(),"Hell0",Toast.LENGTH_LONG).show();
            imm = (InputMethodManager) getSystemService(
                    Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(mEditText2.getApplicationWindowToken(), 0);
        }
        return false;
    }
}
