package com.diandian.ycdyus.moneymanager.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.diandian.ycdyus.moneymanager.R;
import com.diandian.ycdyus.moneymanager.adapter.MainListViewAdapter;
import com.diandian.ycdyus.moneymanager.bean.ManagerBean;
import com.diandian.ycdyus.moneymanager.eventbus.NotifyNewRecordBack;
import com.diandian.ycdyus.moneymanager.model.MainListViewModel;
import com.diandian.ycdyus.moneymanager.view.MyImageView;
import com.orhanobut.hawk.Hawk;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity implements MyImageView.MyImageViewMoveEvent {
    @ViewById
    ListView mainListview;
    @ViewById
    MyImageView myimageview;
    @ViewById
    ImageView ivMainFaces;
    private List<MainListViewModel> list;
    private MainListViewAdapter adapter;
    private int todayWeek;

    @AfterViews
    public void init() {
        getSupportActionBar().hide();
        list = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date(System.currentTimeMillis()));
        todayWeek = cal.get(Calendar.DAY_OF_WEEK) - 1;

        adapter = new MainListViewAdapter(this, list, R.layout.item_main_listview);
        mainListview.setAdapter(adapter);

        initData();


//        mainListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                startActivity(new Intent(MainActivity.this, NewRecordActivity_.class));
//            }
//        });
        myimageview.setMoveEvent(this);

        testRegister();
    }

    private void initData() {
        List<ManagerBean> tempList = Hawk.get("main_list");
        list.clear();
        for (int i = todayWeek; i < todayWeek + 7; i++) {
            MainListViewModel model = new MainListViewModel();
            model.setWeek(i % 7);
            if (tempList != null) {
                List<ManagerBean> newList = new ArrayList<>();
                for (ManagerBean bean : tempList) {
                    if (bean.getWeek() == i % 7) {
                        newList.add(bean);
                    }
                }
                model.setList(newList);
            }
            list.add(model);
        }
        adapter.notifyDataSetChanged();
    }

    private void testRegister() {
//        BmobUser user = new BmobUser();
//        user.setUsername("sendi");
//        user.setPassword("123456");
//        user.setEmail("sendi@163.com");
////注意：不能用save方法进行注册
//        user.signUp(this, new SaveListener() {
//            @Override
//            public void onSuccess() {
//                // TODO Auto-generated method stub
//                Toast.makeText(MainActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
//            }
//            @Override
//            public void onFailure(int code, String msg) {
//                // TODO Auto-generated method stub
//                Toast.makeText(MainActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    @Override
    public void onMoveEvent(int direction) {
        if (direction == MyImageView.DIRECTION_RIGHT) {
            myimageview.setImageResource(R.mipmap.d102);
        } else if (direction == MyImageView.DIRECTION_LEFT) {
            myimageview.setImageResource(R.mipmap.d103);
        } else if (direction == MyImageView.DIRECTION_UP) {
            myimageview.setImageResource(R.mipmap.d104);
        } else if (direction == MyImageView.DIRECTION_DOWN) {
            myimageview.setImageResource(R.mipmap.d105);
        } else if (direction == MyImageView.DIRECTION_CENTER) {
            myimageview.setImageResource(R.mipmap.d101);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onCenterClick() {
        ivMainFaces.setVisibility(View.GONE);
        myimageview.setImageResource(R.mipmap.d101);
    }

    @Override
    public void onCenterClickComplete(int direction) {
        switch (direction) {
            case 0:
                Log.e("HHHHHH", "UP");
                startActivity(new Intent(this, BudgetActivity_.class));
                break;
            case 1:
                Log.e("HHHHHH", "LEFT");
//                startActivity(new Intent(this, NewRecordActivity_.class));
                break;
            case 2:
                Log.e("HHHHHH", "RIGHT");
                startActivity(new Intent(this, NewRecordActivity_.class));
                break;
            case 3:
                Log.e("HHHHHH", "DOWM");
                startActivity(new Intent(this, BudgetActivity_.class));
                break;
        }
        ivMainFaces.setVisibility(View.VISIBLE);
        myimageview.setImageResource(R.mipmap.d00);
    }

    @Subscribe
    public void notifyNewRecordResult(NotifyNewRecordBack event) {
        initData();
    }
}