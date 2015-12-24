package com.spend.wisely;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MonthAdapter extends BaseAdapter {

    // String [] result;
    Context context;
    //int [] imageId;
    ArrayList<Month> monthsList;
    private static LayoutInflater inflater = null;

    public MonthAdapter(Context context, ArrayList<Month> monthsList) {
        // TODO Auto-generated constructor stub
        // result=prgmNameList;
        this.context = context;
        // imageId=prgmImages;
        inflater = (LayoutInflater) context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.monthsList = monthsList;

    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return monthsList.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class Holder {
        TextView mMonthText;
        TextView mCostText;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder = new Holder();
        View rowView;

        rowView = inflater.inflate(R.layout.week_row, null);
        holder.mMonthText = (TextView) rowView.findViewById(R.id.day_name);
        holder.mCostText = (TextView) rowView.findViewById(R.id.money);

        holder.mMonthText.setText(monthsList.get(position).getMonthName());
        holder.mCostText.setText(monthsList.get(position).getMonthCost());

        /* rowView.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Toast.makeText(context, "You Clicked "+result[position], Toast.LENGTH_LONG).show();
            }
        });*/

        return rowView;
    }

} 