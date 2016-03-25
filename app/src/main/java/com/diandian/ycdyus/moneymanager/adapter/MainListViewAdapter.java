package com.diandian.ycdyus.moneymanager.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.diandian.ycdyus.moneymanager.R;
import com.diandian.ycdyus.moneymanager.bean.ManagerBean;
import com.diandian.ycdyus.moneymanager.model.MainListViewModel;

import java.util.List;

/**
 * Created by ycdyus on 2016/2/29.
 */
public class MainListViewAdapter extends CommonAdapter<MainListViewModel> {
    public MainListViewAdapter(Context context, List<MainListViewModel> mDatas, int itemLayoutId) {
        super(context, mDatas, itemLayoutId);
    }

    @Override
    public void convert(ViewHolders holder, MainListViewModel item) {
        double customerIn = 0;
        double customerOut = 0;
        int week = item.getWeek();
        final boolean flag = true;
        ImageView ivWeek = holder.getView(R.id.iv_item_weeks_icon);
        TextView tvSumCountIn = holder.getView(R.id.tv_item_money_in);
        TextView tvSumCountOut = holder.getView(R.id.tv_item_money_out);
        final RelativeLayout relaContainer = holder.getView(R.id.head_container);
        int leftRes = R.mipmap.j07;
        switch (week) {
            case 0:
                ivWeek.setImageResource(R.mipmap.e07);
                leftRes = R.mipmap.j07;
                break;
            case 1:
                ivWeek.setImageResource(R.mipmap.e01);
                leftRes = R.mipmap.j01;
                break;
            case 2:
                ivWeek.setImageResource(R.mipmap.e02);
                leftRes = R.mipmap.j02;
                break;
            case 3:
                ivWeek.setImageResource(R.mipmap.e03);
                leftRes = R.mipmap.j03;
                break;
            case 4:
                ivWeek.setImageResource(R.mipmap.e04);
                leftRes = R.mipmap.j04;
                break;
            case 5:
                ivWeek.setImageResource(R.mipmap.e05);
                leftRes = R.mipmap.j05;
                break;
            case 6:
                ivWeek.setImageResource(R.mipmap.e06);
                leftRes = R.mipmap.j06;
                break;
        }
        final LinearLayout linContainer = holder.getView(R.id.lin_main_container);
        linContainer.setVisibility(View.GONE);
        relaContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag){
                    if(linContainer.getVisibility()==View.VISIBLE){
                        linContainer.setVisibility(View.GONE);
                    }else if(linContainer.getVisibility()==View.GONE){
                        linContainer.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
        linContainer.removeAllViews();
        if (item.getList() != null)
            for (ManagerBean model : item.getList()) {
                View view = ((Activity) mContext).getLayoutInflater().inflate(R.layout.item_main_listview_container_item, null);
                ImageView ivLeft = (ImageView) view.findViewById(R.id.iv_item_item_left);
                ImageView ivIcon = (ImageView) view.findViewById(R.id.iv_item_item_icon);
                TextView tvType = (TextView) view.findViewById(R.id.tv_item_item_type);
                TextView tvCount = (TextView) view.findViewById(R.id.tv_item_item_count);
                ivLeft.setImageResource(leftRes);
                ivIcon.setImageResource(model.getResId());
                tvType.setText(model.getType());
                if(model.getConsumerType()==ManagerBean.CUSTOMER_IN){
                    customerIn += model.getCount();
                    tvCount.setText("+￥" + model.getCount());
                }else{
                    customerOut += model.getCount();
                    tvCount.setText("-￥" + model.getCount());
                }
                linContainer.addView(view);
            }
        tvSumCountIn.setText("￥"+customerIn);
        tvSumCountOut.setText("￥" + customerOut);
    }
}
