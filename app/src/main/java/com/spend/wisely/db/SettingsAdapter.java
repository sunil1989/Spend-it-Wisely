package com.spend.wisely.db;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.spend.wisely.R;

import java.util.List;

public class SettingsAdapter extends ArrayAdapter<Settings> {
    private List<Settings> appsList = null;
    private Context context;
    private PackageManager packageManager;
    String result;

    public SettingsAdapter(Context context, int textViewResourceId,
                           List<Settings> appsList) {
        super(context, textViewResourceId, appsList);
        this.context = context;
        this.appsList = appsList;
        packageManager = context.getPackageManager();

    }

    @Override
    public int getCount() {
        return ((null != appsList) ? appsList.size() : 0);
    }

    @Override
    public Settings getItem(int position) {
        return ((null != appsList) ? appsList.get(position) : null);
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
            view = layoutInflater.inflate(R.layout.settings_row, null);
        }

        Settings applicationInfo = appsList.get(position);
        if (null != applicationInfo) {
            //TextView appName = (TextView) view.findViewById(R.id.textView9);
            TextView setting_name = (TextView) view.findViewById(R.id.textView9);
            //ImageView iconview = (ImageView) view.findViewById(R.id.icon_img);

            setting_name.setText(applicationInfo.getName());

        }
        return view;
    }
};