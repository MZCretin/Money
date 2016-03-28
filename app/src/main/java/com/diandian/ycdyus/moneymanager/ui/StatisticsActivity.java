package com.diandian.ycdyus.moneymanager.ui;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.diandian.ycdyus.moneymanager.R;
import com.diandian.ycdyus.moneymanager.adapter.FragmentViewPagerAdapter;
import com.diandian.ycdyus.moneymanager.fragment.BarChartFragment;
import com.diandian.ycdyus.moneymanager.fragment.PieChartFragment;
import com.diandian.ycdyus.moneymanager.fragment.ValueLineChartFragment;
import com.diandian.ycdyus.moneymanager.view.NoScrollViewPager;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

@EActivity(R.layout.activity_statistics)
public class StatisticsActivity extends AppCompatActivity {
    @ViewById
    ImageView ivTongjiBack;
    @ViewById
    TextView binghzunagtu;
    @ViewById
    TextView zhexiantu;
    @ViewById
    TextView tiaoxingtu;
    @ViewById
    NoScrollViewPager chartViewpager;
    private List<Fragment> list;
    private FragmentViewPagerAdapter adapter;

    @AfterViews
    public void init(){
        getSupportActionBar().hide();

        list = new ArrayList<>();
        list.add(new PieChartFragment());
        list.add(new ValueLineChartFragment());
        list.add(new BarChartFragment());
        chartViewpager.setNoScroll(true);
        adapter = new FragmentViewPagerAdapter(getSupportFragmentManager(), list);
        chartViewpager.setAdapter(adapter);


        ivTongjiBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StatisticsActivity.this.finish();
            }
        });

        binghzunagtu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chartViewpager.setCurrentItem(0);
                binghzunagtu.setTextColor(Color.parseColor("#63f0ff"));
                zhexiantu.setTextColor(Color.parseColor("#2f2f2f"));
                tiaoxingtu.setTextColor(Color.parseColor("#2f2f2f"));
            }
        });

        zhexiantu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chartViewpager.setCurrentItem(1);
                binghzunagtu.setTextColor(Color.parseColor("#2f2f2f"));
                zhexiantu.setTextColor(Color.parseColor("#63f0ff"));
                tiaoxingtu.setTextColor(Color.parseColor("#2f2f2f"));
            }
        });

        tiaoxingtu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chartViewpager.setCurrentItem(2);
                binghzunagtu.setTextColor(Color.parseColor("#2f2f2f"));
                zhexiantu.setTextColor(Color.parseColor("#2f2f2f"));
                tiaoxingtu.setTextColor(Color.parseColor("#63f0ff"));
            }
        });
    }

}
