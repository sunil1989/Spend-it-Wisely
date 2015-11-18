package com.spend.wisely.com.onbordinganim;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.spend.wisely.R;

public class Main1Activity extends FragmentActivity implements ViewPager.OnPageChangeListener {
    //private TextView[] dots;
    private ImageView[] dots;
    private LinearLayout dotsLayout;
    private int dotsCount;
    ViewPager pager;
    MyPagerAdapter myPagerAdapter;
    int prevPage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main1);

         pager = (ViewPager) findViewById(R.id.viewPager);

        myPagerAdapter=new MyPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(myPagerAdapter);
        //pager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        pager.setCurrentItem(0);
        pager.setOnPageChangeListener(this);
        setUiPageViewController();

        pager.setPageTransformer(false, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(View page, float position) {
                // do transformation here
                if(position==0){

                    FirstFragment.newInstance("FirstFragment, Instance 1");
                    //Toast.makeText(getApplicationContext(),"Hello",Toast.LENGTH_LONG).show();
                }
                if(position==1){
                    SecondFragment.newInstance("SecondFragment, Instance 1",1);
                    //Toast.makeText(getApplicationContext(),"Hello1",Toast.LENGTH_LONG).show();
                }
                if(position==2){
                    ThirdFragment.newInstance("ThirdFragment, Instance 1");
                    // Toast.makeText(getApplicationContext(),"Hello2",Toast.LENGTH_LONG).show();
                }
                if(position==3){
                    FourFragment.newInstance("FourFragment, Default");
                    //Toast.makeText(getApplicationContext(),"Hello3",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void setUiPageViewController() {
        dotsLayout = (LinearLayout)findViewById(R.id.viewPagerCountDots);
        dotsCount = myPagerAdapter.getCount();
        dots = new ImageView[dotsCount];

        for (int i = 0; i < dotsCount; i++) {
            dots[i] = new ImageView(this);
            dots[i].setPadding(10,0,0,0);
           // dots[i].setText(Html.fromHtml("&#8226;"));
           // dots[i].setText("•");
           // dots[i].setTextSize(50);
            dots[i].setImageResource(R.drawable.tour_position_inactive);
          //  dots[i].setTextColor(getResources().getColor(android.R.color.darker_gray));
            dotsLayout.addView(dots[i]);
        }
        dots[0].setImageResource(R.drawable.tour_position_active);
       // dots[0].setTextColor(getResources().getColor(R.color.White));
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

       /* if(position==0){


            FirstFragment.newInstance("FirstFragment, Instance 1");
            //Toast.makeText(getApplicationContext(),"Hello",Toast.LENGTH_LONG).show();
        }
        if(position==1){
            SecondFragment.newInstance("SecondFragment, Instance 1");
            //Toast.makeText(getApplicationContext(),"Hello1",Toast.LENGTH_LONG).show();
        }
        if(position==2){
            ThirdFragment.newInstance("ThirdFragment, Instance 1");
            // Toast.makeText(getApplicationContext(),"Hello2",Toast.LENGTH_LONG).show();
        }
        if(position==3){
            FourFragment.newInstance("FourFragment, Default");
            //Toast.makeText(getApplicationContext(),"Hello3",Toast.LENGTH_LONG).show();
        }
*/
    }

    @Override
    public void onPageSelected(int position) {

        if (position-1 == prevPage) {
            Log.i("Swipped", "Swipped Left");
           // Toast.makeText(getApplicationContext(), "Swipped Left"+position, Toast.LENGTH_LONG).show();
        }
        // went up a page
        if (position+1 == prevPage) {
            Log.i("Swipped", "Swipped Right");
           // Toast.makeText(getApplicationContext(),"Swipped Right"+position,Toast.LENGTH_LONG).show();
        }
        prevPage = position;

       if(position==0){

            FirstFragment.newInstance("FirstFragment, Instance 1");
           // Toast.makeText(getApplicationContext(),"Hello",Toast.LENGTH_LONG).show();
        }
        if(position==1){
            SecondFragment.newInstance("SecondFragment, Instance 1",1);
           // Toast.makeText(getApplicationContext(),"Hello1",Toast.LENGTH_LONG).show();
        }
        if(position==2){
            ThirdFragment.newInstance("ThirdFragment, Instance 1");
           // Toast.makeText(getApplicationContext(),"Hello2",Toast.LENGTH_LONG).show();
        }
        if(position==3) {
            FourFragment.newInstance("FourFragment, Default");
            //Toast.makeText(getApplicationContext(),"Hello3",Toast.LENGTH_LONG).show();
        }


        for (int i = 0; i < dotsCount; i++) {
            //dots[i].setTextColor(getResources().getColor(android.R.color.darker_gray));
            dots[i].setImageResource(R.drawable.tour_position_inactive);
        }
       // dots[position].setTextColor(getResources().getColor(R.color.white));

        dots[position].setImageResource(R.drawable.tour_position_active);
        if(position==3){
            dotsLayout.setVisibility(View.GONE);
        }
        else{
            dotsLayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private class MyPagerAdapter extends FragmentStatePagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public int getItemPosition (Object object)
        {
            return POSITION_NONE;
        }
        @Override
        public Fragment getItem(int pos) {
            switch(pos) {

                case 0: return FirstFragment.newInstance("FirstFragment, Instance 1");
                case 1: return SecondFragment.newInstance("SecondFragment, Instance 1",1);
                case 2: return ThirdFragment.newInstance("ThirdFragment, Instance 1");
                case 3: return FourFragment.newInstance("FourFragment, Instance 2");
               // case 4: return FourFragment.newInstance("FourFragment, Instance 2");
                default:  return null;
            }
        }



        @Override
        public int getCount() {
            return 4;
        }



        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
           // View view = (View)object;
           // ((ViewPager) container).removeView(view);

            //pager.removeView((View) object);
        }
    }



}