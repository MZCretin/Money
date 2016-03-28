package com.diandian.ycdyus.moneymanager.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.diandian.ycdyus.moneymanager.R;
import com.diandian.ycdyus.moneymanager.adapter.GuideViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;


public class GuideActivity extends BaseActivity {
    private ViewPager viewPager;
    private ImageView imageView;
    private GuideViewPagerAdapter adapter;
    private int[] res = new int[]{R.mipmap.start_1,R.mipmap.start_2,R.mipmap.start_3,R.mipmap.start_4};
    private int[] spot = new int[]{R.mipmap.start_dot_1,R.mipmap.start_dot_2,R.mipmap.start_dot_3,R.mipmap.start_dot_4};
    private List<View> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        getSupportActionBar().hide();

        viewPager = (ViewPager) findViewById(R.id.guide_viewpager);
        imageView = (ImageView) findViewById(R.id.spot);

        list = new ArrayList<>();
        for (int i = 0; i < res.length; i++) {
            ImageView image = new ImageView(this);
            image.setImageResource(res[i]);
            image.setScaleType(ImageView.ScaleType.FIT_XY);
            list.add(image);

            if(i==res.length-1){
                image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(GuideActivity.this,MainActivity_.class));
                        GuideActivity.this.finish();
                    }
                });
            }
        }
        adapter = new GuideViewPagerAdapter(list);
        viewPager.setAdapter(adapter);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                imageView.setImageResource(spot[position]);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
