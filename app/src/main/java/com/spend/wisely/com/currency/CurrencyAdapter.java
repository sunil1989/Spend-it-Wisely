package com.spend.wisely.com.currency;

import android.app.Activity;
import android.graphics.Color;
import android.text.Html;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.spend.wisely.R;

import java.util.ArrayList;

/**
 * Created by Sunil on 10/30/2015.
 */
public class CurrencyAdapter extends ArrayAdapter<Currency> {

    private Activity context;
    ArrayList<Currency> deer;
    TextView name;
    public CurrencyAdapter(Activity context, int resource, ArrayList<Currency> deer) {

        super(context, resource, deer);
        this.context = context;
        this.deer = deer;

    }
    public int getCount(){
        return deer.size();
    }

    public long getItemId(int position){
        return position;
    }

    public Currency getItem(int position){
        return deer.get(position);
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView label = new TextView(context);
        //label.setText(deer.get(position).getCurrecnySign());
        label.setTextSize(30);
        label.setTextColor(Color.WHITE);
        label.setGravity(Gravity.CENTER);

        label.setText(Html.fromHtml(deer.get(position).getCurrecnySign()+ "<small>" + deer.get(position).getCurrencydropdown() + "</small>"));
       /* TextView label1 = new TextView(context);
        label1.setText(deer.get(position).getCurrencydropdown());
        label1.setTextSize(15);
        label1.setTextColor(Color.WHITE);
        label1.setGravity(Gravity.CENTER);*/
        if(parent!=null&&(parent instanceof ListView)){
            ListView lv=null;
            try{
                lv=(ListView)parent;
            }catch(Exception e){}
            if(lv!=null){
               // lv.setDivider(drawable);
                lv.setDividerHeight(0);
            }
        }
        //ImageView profile = (ImageView) row.findViewById(R.id.profile);
        // profile.setBackgroundResource(current.getResourceId());

        //name = (TextView) row.findViewById(R.id.spinnerText);
       // name.setText(current.getCurrencyName().toString());
        return label;
    }

    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {

        View row = convertView;

        if (row == null) {

            LayoutInflater inflater = context.getLayoutInflater();
            row = inflater.inflate(R.layout.currency_row, parent, false);

        }

        if(parent!=null&&(parent instanceof ListView)){
            ListView lv=null;
            try{
                lv=(ListView)parent;
            }catch(Exception e){}
            if(lv!=null){
                // lv.setDivider(drawable);
                lv.setDividerHeight(0);
            }
        }
        Currency current = deer.get(position);

        //ImageView profile = (ImageView) row.findViewById(R.id.profile);
       // profile.setBackgroundResource(current.getResourceId());

         name = (TextView) row.findViewById(R.id.spinnerText);
        name.setText(current.getCurrencyName().toString());

        return row;

    }



}