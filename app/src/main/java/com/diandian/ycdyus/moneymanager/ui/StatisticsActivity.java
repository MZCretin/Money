package com.diandian.ycdyus.moneymanager.ui;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.diandian.ycdyus.moneymanager.R;
import com.diandian.ycdyus.moneymanager.adapter.FragmentViewPagerAdapter;
import com.diandian.ycdyus.moneymanager.fragment.BarChartFragment;
import com.diandian.ycdyus.moneymanager.fragment.PieChartFragment;
import com.diandian.ycdyus.moneymanager.fragment.ValueLineChartFragment;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

@EActivity(R.layout.activity_statistics)
public class StatisticsActivity extends AppCompatActivity {
    private List<Fragment> list;
    private FragmentViewPagerAdapter adapter;

    @ViewById
    ViewPager chartViewpager;

    @AfterViews
    public void init(){
        list = new ArrayList<>();
        list.add(new PieChartFragment());
        list.add(new BarChartFragment());
        list.add(new ValueLineChartFragment());
        adapter = new FragmentViewPagerAdapter(getSupportFragmentManager(), list);
        chartViewpager.setAdapter(adapter);
    }

}
