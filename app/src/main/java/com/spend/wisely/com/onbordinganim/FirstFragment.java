package com.spend.wisely.com.onbordinganim;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.spend.wisely.R;

public class FirstFragment extends Fragment {

    TextView textView,textView2,textView3,textView4;
     ImageView imageView;
     ImageView imageView6;
    View view;
    Activity activity;
    //static Activity main1Activity=getActivity();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.first_frag, container, false);
        TextView tv = (TextView) v.findViewById(R.id.tvFragFirst);
        textView = (TextView) v.findViewById(R.id.textView);
        textView2 = (TextView) v.findViewById(R.id.textView2);
        textView3 = (TextView) v.findViewById(R.id.textView3);
        textView4 = (TextView) v.findViewById(R.id.textView4);
        imageView=(ImageView) v.findViewById(R.id.imageView);
        imageView6=(ImageView) v.findViewById(R.id.imageView6);

        imageView.setAnimation(AnimationUtils.loadAnimation(getActivity(),
                R.anim.slide_out_left));

        imageView6.setAnimation(AnimationUtils.loadAnimation(getActivity(),
                R.anim.slide_out_left));
        textView.setAnimation(AnimationUtils.loadAnimation(getActivity(),
                R.anim.slide_out_left));
        textView2.setAnimation(AnimationUtils.loadAnimation(getActivity(),
                R.anim.slide_out_left));
        textView3.setAnimation(AnimationUtils.loadAnimation(getActivity(),
                R.anim.slide_out_left));
        textView4.setAnimation(AnimationUtils.loadAnimation(getActivity(),
                R.anim.slide_out_left));
        Typeface face= Typeface.createFromAsset(getActivity().getAssets(), "ptsans.ttf");
        textView3.setTypeface(face);
        textView4.setTypeface(face);


        tv.setText(getArguments().getString("msg"));

        view=v;

         activity = (Activity)view.getContext();

        return v;
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {




           // Log.v(TAG, "run now");
           // Animation animationFadeIn = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_view);
           // iv.startAnimation(animationFadeIn);
           // animationFadeIn.setFillAfter(true);
         //   Toast.makeText(getActivity(), "State First", Toast.LENGTH_LONG).show();

           // imageView.setAnimation(AnimationUtils.loadAnimation(getActivity(),
            //        R.anim.slide_out_left));
          /*  textView.setAnimation(AnimationUtils.loadAnimation(getActivity(),
                    R.anim.slide_out_left));
            textView2.setAnimation(AnimationUtils.loadAnimation(getActivity(),
                    R.anim.slide_out_left));
            textView3.setAnimation(AnimationUtils.loadAnimation(getActivity(),
                    R.anim.slide_out_left));
            textView4.setAnimation(AnimationUtils.loadAnimation(getActivity(),
                    R.anim.slide_out_left));*/
          //  imageView=(ImageView)view.findViewById(R.id.imageView);

           // Animation animationFadeIn = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_out_left);
           // imageView.startAnimation(animationFadeIn);
           // animationFadeIn.setFillAfter(true);
           // imageView.setAnimation(AnimationUtils.loadAnimation(getActivity(),
             //       R.anim.slide_out_left));

           /* textView.setAnimation(AnimationUtils.loadAnimation(getActivity(),
                    R.anim.slide_out_left));
            textView2.setAnimation(AnimationUtils.loadAnimation(getActivity(),
                    R.anim.slide_out_left));
            textView3.setAnimation(AnimationUtils.loadAnimation(getActivity(),
                    R.anim.slide_out_left));
            textView4.setAnimation(AnimationUtils.loadAnimation(getActivity(),
                    R.anim.slide_out_left));*/
        }
        else {  }
    }

    public static FirstFragment newInstance(String text) {

        FirstFragment f = new FirstFragment();
        Bundle b = new Bundle();
        b.putString("msg", text);

        f.setArguments(b);



        return f;
    }
}