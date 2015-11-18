package com.spend.wisely;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends Activity {

    private ViewPager viewPager;
    private MyViewPagerAdapter myViewPagerAdapter;
    private ArrayList<Integer> listOfItems;

    private LinearLayout dotsLayout;
    private int dotsCount;
    private TextView[] dots;
    int prevPage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        setViewPagerItemsWithAdapter();
       setUiPageViewController();
    }

    private void setUiPageViewController() {
        dotsLayout = (LinearLayout)findViewById(R.id.viewPagerCountDots);
        dotsCount = myViewPagerAdapter.getCount();
       dots = new TextView[dotsCount];

       for (int i = 0; i < dotsCount; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(30);
            dots[i].setTextColor(getResources().getColor(android.R.color.darker_gray));
            dotsLayout.addView(dots[i]);
        }

        dots[0].setTextColor(getResources().getColor(R.color.green));
    }

    private void setViewPagerItemsWithAdapter() {
        myViewPagerAdapter = new MyViewPagerAdapter(listOfItems);
        viewPager.setAdapter(myViewPagerAdapter);
        viewPager.setCurrentItem(0);
        viewPager.setOnPageChangeListener(viewPagerPageChangeListener);
    }

    //	page change listener
    OnPageChangeListener viewPagerPageChangeListener = new OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
            for (int i = 0; i < dotsCount; i++) {
                dots[i].setTextColor(getResources().getColor(android.R.color.darker_gray));
            }
           dots[position].setTextColor(getResources().getColor(R.color.green));


         /*   // went back a page
            if (position-1 == prevPage) {
                Log.i("Swipped", "Swipped Left");
                Toast.makeText(getApplicationContext(),"Swipped Left",Toast.LENGTH_LONG).show();
            }
            // went up a page
            if (position+1 == prevPage) {
                Log.i("Swipped", "Swipped Right");
                Toast.makeText(getApplicationContext(),"Swipped Right",Toast.LENGTH_LONG).show();
            }
            prevPage = position;*/
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int position) {
           /* if (position-1 == prevPage) {
                Log.i("Swipped", "Swipped Left");
                Toast.makeText(getApplicationContext(),"Swipped Left",Toast.LENGTH_LONG).show();
            }
            // went up a page
            if (position+1 == prevPage) {
                Log.i("Swipped", "Swipped Right");
                Toast.makeText(getApplicationContext(),"Swipped Right",Toast.LENGTH_LONG).show();
            }
            prevPage = position;*/
        }
    };

    private void initViews() {

        getActionBar().hide();

        viewPager = (ViewPager)findViewById(R.id.viewPager);

        listOfItems = new ArrayList<Integer>();
        listOfItems.add(1);
        listOfItems.add(2);
        listOfItems.add(3);
        listOfItems.add(4);

    }

    //	adapter
    public class MyViewPagerAdapter extends PagerAdapter{

        private LayoutInflater layoutInflater;
        private ArrayList<Integer> items;

        public MyViewPagerAdapter(ArrayList<Integer> listOfItems) {
            this.items = listOfItems;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = layoutInflater.inflate(R.layout.view_pager_item, container,false);



          /*  RelativeLayout relativeLayout=(RelativeLayout)view.findViewById(R.id.RelativeLayout1);
            relativeLayout.setBackgroundResource(listOfItems.get(position));

            Button mbutton=(Button)view.findViewById(R.id.button);
             mbutton.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                   //  Toast.makeText(getApplicationContext(),"Hello",Toast.LENGTH_LONG).show();
                     Intent mainIntent = new Intent(MainActivity.this,HourlyRateActivity.class);
                     MainActivity.this.startActivity(mainIntent);

                 }
             });
            if(position==3){
                mbutton.setVisibility(View.VISIBLE);
            }*/

           TextView tView = (TextView)view.findViewById(R.id.PageNumber);

           tView.setText(listOfItems.get(position).toString());

            ((ViewPager) container).addView(view);

            return view;
        }

        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == ((View)obj);
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View)object;
            ((ViewPager) container).removeView(view);

            viewPager.removeView((View) object);
        }
    }
}