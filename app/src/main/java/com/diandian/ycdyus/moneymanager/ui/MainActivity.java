package com.diandian.ycdyus.moneymanager.ui;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.diandian.ycdyus.moneymanager.R;
import com.diandian.ycdyus.moneymanager.adapter.MainListViewAdapter;
import com.diandian.ycdyus.moneymanager.bean.ManagerBean;
import com.diandian.ycdyus.moneymanager.eventbus.LoginSuccess;
import com.diandian.ycdyus.moneymanager.eventbus.NotifyNewRecordBack;
import com.diandian.ycdyus.moneymanager.model.BudgetManagerModel;
import com.diandian.ycdyus.moneymanager.model.BudgetStateChangeModel;
import com.diandian.ycdyus.moneymanager.model.MainListViewModel;
import com.diandian.ycdyus.moneymanager.view.DragLayout;
import com.diandian.ycdyus.moneymanager.view.MyImageView;
import com.orhanobut.hawk.Hawk;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.listener.SaveListener;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity implements MyImageView.MyImageViewMoveEvent {
    @ViewById
    ImageView icon;
    @ViewById
    TextView loginRegster;
    @ViewById
    TextView income;
    @ViewById
    TextView expense;
    @ViewById
    TextView mymoney;
    @ViewById
    TextView synchronization;
    @ViewById
    TextView share;
    @ViewById
    ImageView ivNewRecordBack;
    @ViewById
    MyImageView myimageview;
    @ViewById
    ImageView ivMainFaces;
    @ViewById
    ListView mainListview;
    @ViewById
    DragLayout dl;
    @ViewById
    LinearLayout lv;
    @ViewById
    RelativeLayout guideHome;
    @ViewById
    ImageView ivIknow;
    private List<MainListViewModel> list;
    private MainListViewAdapter adapter;
    private int todayWeek;
    private double incomeCount;
    private double expenseCount;
    private int[] positionRes = new int[]{R.mipmap.d00, R.mipmap.d01, R.mipmap.d02, R.mipmap.d03, R.mipmap.d04, R.mipmap.d05, R.mipmap.d06, R.mipmap.d07, R.mipmap.d08, R.mipmap.d09,
            R.mipmap.d10, R.mipmap.d11, R.mipmap.d12, R.mipmap.d13, R.mipmap.d14, R.mipmap.d15, R.mipmap.d16, R.mipmap.d17, R.mipmap.d18, R.mipmap.d19,
            R.mipmap.d20, R.mipmap.d21, R.mipmap.d22, R.mipmap.d23, R.mipmap.d24, R.mipmap.d25, R.mipmap.d26, R.mipmap.d27, R.mipmap.d28, R.mipmap.d29,
            R.mipmap.d30, R.mipmap.d31, R.mipmap.d32, R.mipmap.d33, R.mipmap.d34, R.mipmap.d35, R.mipmap.d36, R.mipmap.d37, R.mipmap.d38, R.mipmap.d39,
            R.mipmap.d40, R.mipmap.d41, R.mipmap.d42, R.mipmap.d43, R.mipmap.d44, R.mipmap.d45, R.mipmap.d46, R.mipmap.d47, R.mipmap.d48, R.mipmap.d49,
            R.mipmap.d50, R.mipmap.d51, R.mipmap.d52, R.mipmap.d53, R.mipmap.d54, R.mipmap.d55, R.mipmap.d56, R.mipmap.d57, R.mipmap.d58, R.mipmap.d59,
            R.mipmap.d60, R.mipmap.d61, R.mipmap.d62, R.mipmap.d63, R.mipmap.d64, R.mipmap.d65, R.mipmap.d66, R.mipmap.d67, R.mipmap.d68, R.mipmap.d69,
            R.mipmap.d70, R.mipmap.d71, R.mipmap.d72, R.mipmap.d73, R.mipmap.d74, R.mipmap.d75, R.mipmap.d76, R.mipmap.d77, R.mipmap.d78, R.mipmap.d79,
            R.mipmap.d80, R.mipmap.d81, R.mipmap.d82, R.mipmap.d83, R.mipmap.d84, R.mipmap.d85, R.mipmap.d86, R.mipmap.d87, R.mipmap.d88, R.mipmap.d89,
            R.mipmap.d90, R.mipmap.d91, R.mipmap.d92, R.mipmap.d93, R.mipmap.d94, R.mipmap.d95, R.mipmap.d96, R.mipmap.d97, R.mipmap.d98, R.mipmap.d99,
    };
    private int currPosition;

    @AfterViews
    public void init() {
        getSupportActionBar().hide();
        list = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date(System.currentTimeMillis()));
        todayWeek = cal.get(Calendar.DAY_OF_WEEK) - 1;

        adapter = new MainListViewAdapter(this, list, R.layout.item_main_listview);
        mainListview.setAdapter(adapter);

        initView();

        initData();

        myimageview.setMoveEvent(this);

        ivNewRecordBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dl.open();
            }
        });

        boolean flag = false;
        if (Hawk.get("if_is_first") != null)
            flag = (Boolean) Hawk.get("if_is_first");
        if (flag == false) {
            Hawk.put("if_is_first", true);
            guideHome.setVisibility(View.VISIBLE);
            ivIknow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    guideHome.setVisibility(View.GONE);
                }
            });
        } else {
            guideHome.setVisibility(View.GONE);
        }

        addListener();
    }

    private void addListener() {
        loginRegster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LoginActivity_.class));
            }
        });

        synchronization.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("确认同步数据？将消耗数据流量");
                builder.setPositiveButton("壕", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        insert();
                    }
                });
                builder.setNegativeButton("罢了", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.create().show();
            }
        });

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareMsg("大哥记账", "推荐使用", "大家用了大哥记账都说好！你也去下载呗", null);
            }
        });
    }

    private void initView() {
        dl = (DragLayout) findViewById(R.id.dl);
        dl.setDragListener(new DragLayout.DragListener() {
            @Override
            public void onOpen() {

            }

            @Override
            public void onClose() {
            }

            @Override
            public void onDrag(float percent) {
            }
        });
    }


    private void initData() {
        List<ManagerBean> tempList = Hawk.get("main_list");
        list.clear();
        incomeCount = 0;
        expenseCount = 0;
        for (int i = todayWeek; i < todayWeek + 7; i++) {
            MainListViewModel model = new MainListViewModel();
            model.setWeek(i % 7);
            if (tempList != null) {
                List<ManagerBean> newList = new ArrayList<>();
                for (ManagerBean bean : tempList) {
                    if (bean.getWeek() == i % 7) {
                        newList.add(bean);

                        if (bean.getConsumerType() == ManagerBean.CUSTOMER_IN) {
                            incomeCount += bean.getCount();
                        } else {
                            expenseCount += bean.getCount();
                        }
                    }
                }
                model.setList(newList);
            }
            list.add(model);
        }

        income.setText("￥" + incomeCount);
        expense.setText("￥" + expenseCount);
        mymoney.setText("￥" + (incomeCount - expenseCount));

        BudgetManagerModel budgetMangerModel = Hawk.get("budget_manager");
        if (budgetMangerModel != null) {
            if (!budgetMangerModel.isBudgetStatus()) {
                //计算百分比
                currPosition = (int) (expenseCount * 100 / budgetMangerModel.getBudgetCount());
                myimageview.setImageResource(positionRes[currPosition]);
            }
        }
        adapter.notifyDataSetChanged();
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
        myimageview.setImageResource(positionRes[currPosition]);
    }

    @Override
    public void onCenterClickComplete(int direction) {
        switch (direction) {
            case 0:
                startActivity(new Intent(this, StatisticsActivity_.class));
                break;
            case 1:
                Intent intent = new Intent(this, NewRecordActivity_.class);
                intent.putExtra("type", 1);
                startActivity(intent);
                break;
            case 2:
                startActivity(new Intent(this, NewRecordActivity_.class));
                break;
            case 3:
                startActivity(new Intent(this, BudgetActivity_.class));
                break;
        }
        ivMainFaces.setVisibility(View.VISIBLE);
        myimageview.setImageResource(positionRes[currPosition]);
    }

    private void insert() {
        final ProgressDialog progressDialog = new ProgressDialog(
                MainActivity.this);
        //设置进度条风格，风格为圆形，旋转的
        progressDialog.setProgressStyle(
                ProgressDialog.STYLE_SPINNER);
        //设置ProgressDialog 提示信息
        progressDialog.setMessage("正在同步....");
        //设置ProgressDialog 标题图标
        progressDialog.setIcon(android.R.drawable.btn_star);
        //设置ProgressDialog 的进度条是否不明确
        progressDialog.setIndeterminate(false);
        //设置ProgressDialog 是否可以按退回按键取消
        progressDialog.setCancelable(false);
        // 让ProgressDialog显示
        progressDialog.show();
        List<BmobObject> lists = new ArrayList<BmobObject>();
        for (int i = 0; i < 3; i++) {
            MainListViewModel model = new MainListViewModel();
            model.setWeek(todayWeek);
            model.setShouru(incomeCount);
            model.setZhichu(expenseCount);
            model.setList(list.get(i).getList());
            lists.add(model);
        }
        new BmobObject().insertBatch(this, lists, new SaveListener() {
            @Override
            public void onSuccess() {
                // TODO Auto-generated method stub
                Toast.makeText(MainActivity.this, "批量添加成功", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(int code, String msg) {
                // TODO Auto-generated method stub
                Toast.makeText(MainActivity.this, "批量添加失败" + msg, Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
    }

    public void shareMsg(String activityTitle, String msgTitle, String msgText,
                         String imgPath) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        if (imgPath == null || imgPath.equals("")) {
            intent.setType("text/plain"); // 纯文本
        } else {
            File f = new File(imgPath);
            if (f != null && f.exists() && f.isFile()) {
                intent.setType("image/jpg");
                Uri u = Uri.fromFile(f);
                intent.putExtra(Intent.EXTRA_STREAM, u);
            }
        }
        intent.putExtra(Intent.EXTRA_SUBJECT, msgTitle);
        intent.putExtra(Intent.EXTRA_TEXT, msgText);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(Intent.createChooser(intent, activityTitle));
    }

    @Subscribe
    public void notifyNewRecordResult(NotifyNewRecordBack event) {
        initData();
    }

    @Subscribe
    public void notifyLoginSuccess(LoginSuccess event) {
        loginRegster.setText(event.getName());
        loginRegster.setEnabled(false);
        icon.setImageResource(R.mipmap.b00_2);
    }

    @Subscribe
    public void notifyBudgetStateChanged(BudgetStateChangeModel event) {
        initData();
    }

}