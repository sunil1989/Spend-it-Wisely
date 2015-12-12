package com.spend.wisely.db;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;


import com.spend.wisely.R;

import java.util.List;

public class AppColorAdapter extends ArrayAdapter<AppColor> {
    private List<AppColor> app_color = null;
    private Context context;
    private PackageManager packageManager;
    SharedPreferencesManager sharedPref = new SharedPreferencesManager();
    String result;

    public AppColorAdapter(Context context, int textViewResourceId,
                           List<AppColor> app_color) {
        super(context, textViewResourceId, app_color);
        this.context = context;
        this.app_color = app_color;
        packageManager = context.getPackageManager();

    }

    @Override
    public int getCount() {
        return ((null != app_color) ? app_color.size() : 0);
    }

    @Override
    public AppColor getItem(int position) {
        return ((null != app_color) ? app_color.get(position) : null);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (null == view) {
            LayoutInflater layoutInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.color_scheme_row, null);
        }

        AppColor applicationInfo = app_color.get(position);
        if (null != applicationInfo) {
            //TextView appName = (TextView) view.findViewById(R.id.textView9);
            TextView color_scheme = (TextView) view.findViewById(R.id.textView9);
            //ImageView iconview = (ImageView) view.findViewById(R.id.icon_img);

            color_scheme.setText(applicationInfo.getName());

            LinearLayout li = (LinearLayout) view.findViewById(R.id.back_ground_layout);
            li.setBackgroundResource(applicationInfo.getColor_row());

            RadioButton radiobtton = (RadioButton) view.findViewById(R.id.radio_button);

            boolean istag = applicationInfo.isTag();

            String tag = sharedPref.getBackground(context);

            System.out.println("==========App adapter============" + tag);
            if (!TextUtils.isEmpty(tag)) {

                if (tag.equals(String.valueOf(position))) {
                    radiobtton.setVisibility(View.VISIBLE);
                } else {
                    radiobtton.setVisibility(View.GONE);
                }

               /* if(tag.equals("0")){
                    radiobtton.setVisibility(View.VISIBLE);
                }

                if(tag.equals("1")){
                    radiobtton.setVisibility(View.VISIBLE);
                }

                if(tag.equals("2")){
                    radiobtton.setVisibility(View.VISIBLE);
                }

                if(tag.equals("3")){
                    radiobtton.setVisibility(View.VISIBLE);
                }
                if(tag.equals("4")){
                    radiobtton.setVisibility(View.VISIBLE);
                }*/


            } else {
                if (istag == true) {
                    radiobtton.setVisibility(View.VISIBLE);
                    //radiobtton.setBackgroundResource(R.drawable.selected_color_scheme);
                    // radiobtton.setChecked(applicationInfo.isTag());
                } else {
                    // radiobtton.setChecked(applicationInfo.isTag());
                    radiobtton.setVisibility(View.GONE);
                }

            }


        }
        return view;
    }
};