package com.thellog.laravelapiandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.thellog.laravelapiandroid.Adapter.ViewPagerAdapter;

public class OnboardActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private Button btnLeft, btnRight;
    private ViewPagerAdapter adapter;
    private LinearLayout dotsLayout;
    private TextView[] dots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboard);
    init();
    }

    private  void  init(){
        viewPager = findViewById(R.id.view_pager);
        btnLeft = findViewById(R.id.btnLeft);
        btnRight = findViewById(R.id.btnRight);
        dotsLayout = findViewById(R.id.dotsLayout);
        adapter = new ViewPagerAdapter(this);
        addDots(0);
        viewPager.addOnPageChangeListener(listener);
        viewPager.setAdapter(adapter);

        btnRight.setOnClickListener(v->{
            //if button text is next ve vill go to next page of viewpager
            if(btnRight.getText().toString().equals("Next")){
                viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
            }
            else{
                //else its finish ve vill start Auth activity
                startActivity(new Intent(OnboardActivity.this , LoginActivity.class));
                finish();
            }
        });

        btnLeft.setOnClickListener(v->{
            //if btn skip clicked then ve go to page 3
            viewPager.setCurrentItem(viewPager.getCurrentItem()+2);
        });
    }
    //method to create dots from html code
    private void addDots(int position){
        dotsLayout.removeAllViews();
        dots = new TextView[3];
        for(int i = 0 ; i < dots.length ; i++ )
        {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(getResources().getColor(R.color.colorPink));
            dotsLayout.addView(dots[i]);
        }

        //ok now lets changes the selected dot color

        if (dots.length>0)
        {
            dots[position].setTextColor(getResources().getColor(R.color.colorRed));
        }
    }

    private ViewPager.OnPageChangeListener listener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDots(position);
            if (position == 0) {
                btnLeft.setVisibility(View.VISIBLE);
                btnLeft.setEnabled(true);
                btnRight.setText("Next");
            } else if (position == 1) {
                btnLeft.setVisibility(View.GONE);
                btnLeft.setEnabled(false);
                btnRight.setText("Next");
            } else {
                btnLeft.setVisibility(View.GONE);
                btnLeft.setEnabled(false);
                btnRight.setText("Finish");
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

}