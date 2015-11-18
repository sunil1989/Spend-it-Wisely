package com.spend.wisely.com.onbordinganim;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.spend.wisely.R;

public class SecondFragment extends Fragment   {
    TextView textView,textView2,textView3,textView4;
    ImageView imageView,imageView7;
    View v;
    private boolean isViewShown = false;
  static int pos;
   static SecondFragment f;



    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // Toast.makeText(getActivity(),"hide",Toast.LENGTH_LONG).show();
    }

    public void onStop(){
super.onStop();
       //Toast.makeText(getActivity(),"hide",Toast.LENGTH_LONG).show();
    }
    @Override
public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


    v = inflater.inflate(R.layout.second_frag, container, false);
   // view=v;
    TextView tv = (TextView) v.findViewById(R.id.tvFragSecond);
    tv.setText(getArguments().getString("msg"));
    textView = (TextView) v.findViewById(R.id.textView);
    textView2 = (TextView) v.findViewById(R.id.textView2);
    textView3 = (TextView) v.findViewById(R.id.textView3);
    textView4 = (TextView) v.findViewById(R.id.textView4);
    imageView=(ImageView) v.findViewById(R.id.imageView);
    imageView7=(ImageView) v.findViewById(R.id.imageView7);


    Typeface face= Typeface.createFromAsset(getActivity().getAssets(), "ptsans.ttf");
    textView3.setTypeface(face);
    textView4.setTypeface(face);

        if (isViewShown==true) {
            fetchData();

           // System.out.println("======after=========");
        }
        else{
           // System.out.println("======hide=========");
            imageView.setVisibility(View.GONE);
            imageView7.setVisibility(View.GONE);
            textView3.setVisibility(View.GONE);
            textView4.setVisibility(View.GONE);


        }

        if (!isViewShown) {
            System.out.println("======Show==onCreate=======");
        }
        else{

            System.out.println("======Hide==onCreate=======");
        }

        return v;
}



        /* pause code */

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (getView() != null) {
            isViewShown = true;
            System.out.println("======Show=========");

        } else {
            isViewShown = false;
            System.out.println("======Hide=========");
        }



        if (isVisibleToUser) {
            isViewShown = true;
            // fetchdata() contains logic to show data when page is selected mostly asynctask to fill the data
            if(pos==1){
            fetchData();

           // System.out.println("======Show=========");
            }
            else{

               // System.out.println("======Hide=========");
                imageView.setVisibility(View.GONE);
                imageView7.setVisibility(View.GONE);
                //textView.setVisibility(View.VISIBLE);
                // textView2.setVisibility(View.VISIBLE);
                textView3.setVisibility(View.GONE);
                textView4.setVisibility(View.GONE);
            }
        } else {
            isViewShown = false;

           // getView().setVisibility(View.GONE);
           // System.out.println("======Hide=========");

            f.onDetach();
        }
       /* if (isVisibleToUser) {



        }
        else {

            Toast.makeText(getActivity(),"hide",Toast.LENGTH_LONG).show();
        }*/




    }



    private void fetchData() {
        imageView.setVisibility(View.VISIBLE);
        imageView7.setVisibility(View.VISIBLE);
        //textView.setVisibility(View.VISIBLE);
        // textView2.setVisibility(View.VISIBLE);
        textView3.setVisibility(View.VISIBLE);
        textView4.setVisibility(View.VISIBLE);
        imageView.setAnimation(AnimationUtils.loadAnimation(getActivity(),
                R.anim.slide_out_left));
        imageView7.setAnimation(AnimationUtils.loadAnimation(getActivity(),
                R.anim.slide_out_left));
        textView.setAnimation(AnimationUtils.loadAnimation(getActivity(),
                R.anim.slide_out_left));
        textView2.setAnimation(AnimationUtils.loadAnimation(getActivity(),
                R.anim.slide_out_left));
        textView3.setAnimation(AnimationUtils.loadAnimation(getActivity(),
                R.anim.slide_out_left));
        textView4.setAnimation(AnimationUtils.loadAnimation(getActivity(),
                R.anim.slide_out_left));
        textView4.setText("See how much money you are loosing daily,\n" +
                "weekly/monthly/yearly (PRO)");
        textView3.setText("Detailed Reports");

        imageView.setImageResource(R.drawable.icon_tour_2);
        imageView7.setImageResource(R.drawable.tour_logo);

    }





   /* public void onStop() {
//        super.onPause();
        //Toast.makeText(getActivity(), "Second onStop", Toast.LENGTH_LONG).show();
        imageView.setVisibility(View.GONE);
        textView.setVisibility(View.GONE);
        textView2.setVisibility(View.GONE);
        textView3.setVisibility(View.GONE);
        textView4.setVisibility(View.GONE);
    }*/

   /* public void onDestroy() {
//        super.onPause();
        //Toast.makeText(getActivity(), "Second onDestroy", Toast.LENGTH_LONG).show();
        imageView.setVisibility(View.GONE);
        textView.setVisibility(View.GONE);
        textView2.setVisibility(View.GONE);
        textView3.setVisibility(View.GONE);
        textView4.setVisibility(View.GONE);
    }*/



    public static SecondFragment newInstance(String text,int i) {
    //Toast.makeText(applicationContext, "Hello", Toast.LENGTH_LONG).show();
     f = new SecondFragment();
    Bundle b = new Bundle();
    b.putString("msg", text);

    f.setArguments(b);
   pos =i;



    return f;
}



}