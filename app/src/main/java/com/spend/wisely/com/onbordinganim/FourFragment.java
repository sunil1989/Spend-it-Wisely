package com.spend.wisely.com.onbordinganim;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.spend.wisely.HourlyRateActivity;
import com.spend.wisely.R;

public class FourFragment extends Fragment implements View.OnClickListener {
    TextView textView,textView2,textView3,textView4;
    ImageView imageView,imageView9;
    Button mButton;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.four_frag, container, false);

        TextView tv = (TextView) v.findViewById(R.id.tvFragThirds);
        tv.setText(getArguments().getString("msg"));
        textView = (TextView) v.findViewById(R.id.textView);
        textView2 = (TextView) v.findViewById(R.id.textView2);
        textView3 = (TextView) v.findViewById(R.id.textView3);
        textView4 = (TextView) v.findViewById(R.id.textView4);
        imageView=(ImageView) v.findViewById(R.id.imageView);
        imageView9=(ImageView) v.findViewById(R.id.imageView9);
      /*  imageView.setAnimation(AnimationUtils.loadAnimation(getActivity(),
                R.anim.slide_out_left));
        textView.setAnimation(AnimationUtils.loadAnimation(getActivity(),
                R.anim.slide_out_left));
        textView2.setAnimation(AnimationUtils.loadAnimation(getActivity(),
                R.anim.slide_out_left));
        textView3.setAnimation(AnimationUtils.loadAnimation(getActivity(),
                R.anim.slide_out_left));
        textView4.setAnimation(AnimationUtils.loadAnimation(getActivity(),
                R.anim.slide_out_left));*/

        Typeface face= Typeface.createFromAsset(getActivity().getAssets(), "ptsans.ttf");
        textView3.setTypeface(face);
        textView4.setTypeface(face);
        mButton=(Button)v.findViewById(R.id.button);
        mButton.setOnClickListener(this);
        return v;
    }

    public static FourFragment newInstance(String text) {

        FourFragment f = new FourFragment();
        Bundle b = new Bundle();
        b.putString("msg", text);

        f.setArguments(b);

        return f;
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            // Log.v(TAG, "run now");
            // Animation animationFadeIn = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_view);
            // iv.startAnimation(animationFadeIn);
            // animationFadeIn.setFillAfter(true);
          //  Toast.makeText(getActivity(), "State four", Toast.LENGTH_LONG).show();
            imageView.setVisibility(View.VISIBLE);
            //textView.setVisibility(View.VISIBLE);
            //textView2.setVisibility(View.VISIBLE);
            textView3.setVisibility(View.VISIBLE);
            textView4.setVisibility(View.VISIBLE);
            imageView9.setVisibility(View.VISIBLE);
            imageView9.setAnimation(AnimationUtils.loadAnimation(getActivity(),
                    R.anim.slide_out_left));
            imageView.setAnimation(AnimationUtils.loadAnimation(getActivity(),
                    R.anim.slide_out_left));
            textView.setAnimation(AnimationUtils.loadAnimation(getActivity(),
                    R.anim.slide_out_left));
            textView2.setAnimation(AnimationUtils.loadAnimation(getActivity(),
                    R.anim.slide_out_left));
            textView3.setAnimation(AnimationUtils.loadAnimation(getActivity(),
                    R.anim.slide_out_left));
            textView4.setAnimation(AnimationUtils.loadAnimation(getActivity(),
                    R.anim.slide_out_left));


        }
        else {  }
    }
    @Override
    public void onClick(View v) {
        Intent mainIntent = new Intent(getActivity(),HourlyRateActivity.class);
         startActivity(mainIntent);
    }
}