package com.diandian.ycdyus.moneymanager.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.voicerecognition.android.ui.BaiduASRDigitalDialog;
import com.baidu.voicerecognition.android.ui.DialogRecognitionListener;
import com.diandian.ycdyus.moneymanager.R;
import com.diandian.ycdyus.moneymanager.adapter.NewRecordGridViewAdapter;
import com.diandian.ycdyus.moneymanager.adapter.NewRecordViewPagerAdapter;
import com.diandian.ycdyus.moneymanager.bean.ManagerBean;
import com.diandian.ycdyus.moneymanager.eventbus.NotifyNewRecordBack;
import com.diandian.ycdyus.moneymanager.model.NewRecordGridViewModel;
import com.diandian.ycdyus.moneymanager.view.NoScroolGridView;
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

@EActivity(R.layout.activity_new_record)
public class NewRecordActivity extends AppCompatActivity implements View.OnClickListener {
    @ViewById
    ImageView ivNewRecordBack;
    @ViewById
    ViewPager vpNewRecord;
    @ViewById
    ImageView ivNewRecordState;
    @ViewById
    EditText edNewRecordBeizhu;
    @ViewById
    TextView tvInoutResult;
    @ViewById
    ImageView ivInputDelete;
    @ViewById
    ImageView ivInputIv7;
    @ViewById
    ImageView ivInputIv4;
    @ViewById
    ImageView ivInputIv1;
    @ViewById
    ImageView ivInputIv8;
    @ViewById
    ImageView ivInputIv5;
    @ViewById
    ImageView ivInputIv2;
    @ViewById
    ImageView ivInputIv9;
    @ViewById
    ImageView ivInputIv6;
    @ViewById
    ImageView ivInputIv3;
    @ViewById
    ImageView ivInputAdd;
    @ViewById
    ImageView ivInputPoint;
    @ViewById
    ImageView ivInputIv0;
    @ViewById
    ImageView ivInputOk;
    private NewRecordViewPagerAdapter adapter;

    private List<View> list;
    private NewRecordGridViewModel currSelectedModel = null;
    private String result = "0";
    private int currSecondViewGroupPosition;
    private List<NewRecordGridViewModel> gvList2;
    private NewRecordGridViewAdapter gvAdapter2;
    private List<NewRecordGridViewModel> gvList1 = new ArrayList<>();
    private NewRecordGridViewAdapter gvAdapter1;

    private int type;

    @AfterViews
    public void init() {
        getSupportActionBar().hide();
        initViewPagerData();

        type = getIntent().getIntExtra("type",0);

        if(type!=0){
            speak();
        }

        vpNewRecord.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0)
                    ivNewRecordState.setImageResource(R.mipmap.c_c38);
                if (position == 1)
                    ivNewRecordState.setImageResource(R.mipmap.c_c39);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        initViewListener();

        ivNewRecordBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewRecordActivity.this.finish();
            }
        });
    }

    private void speak() {
        // 识别结果
        final String recognition_result = "";
        // 参数，其中apiKey和secretKey为必须配置参数，其他根据实际需要配置
        Bundle params = new Bundle();
        // 配置apkKey
        params.putString(BaiduASRDigitalDialog.PARAM_API_KEY, "RHDVE77KKUGjoBpgKHqC8sKs");
        // 配置secretKey
        params.putString(BaiduASRDigitalDialog.PARAM_SECRET_KEY, "a6bdd452672d15d630d6384e3a4369a5");
        // 创建百度语音识别对话框
        BaiduASRDigitalDialog mDialog = new BaiduASRDigitalDialog(this, params);
        // 设置对话框回调监听器
        mDialog.setDialogRecognitionListener(new DialogRecognitionListener(){
            // 识别结果处理函数
            public void onResults(Bundle arg0) {
                ArrayList<String> rs = arg0 != null ? arg0
                        .getStringArrayList(RESULTS_RECOGNITION) : null;
                if (rs != null && rs.size() > 0) {
                    edNewRecordBeizhu.setText(rs.get(0));
                }
            }
        });
        // 显示对话框
        mDialog.show();
    }

    private void initViewListener() {
        ivInputIv0.setOnClickListener(this);
        ivInputIv1.setOnClickListener(this);
        ivInputIv2.setOnClickListener(this);
        ivInputIv3.setOnClickListener(this);
        ivInputIv4.setOnClickListener(this);
        ivInputIv5.setOnClickListener(this);
        ivInputIv6.setOnClickListener(this);
        ivInputIv7.setOnClickListener(this);
        ivInputIv8.setOnClickListener(this);
        ivInputIv9.setOnClickListener(this);
        ivInputAdd.setOnClickListener(this);
        ivInputPoint.setOnClickListener(this);
        ivInputDelete.setOnClickListener(this);
        ivInputOk.setOnClickListener(this);
        tvInoutResult.setText("￥" + result);
    }

    //初始化ViewPager的数据
    private void initViewPagerData() {
        list = new ArrayList<>();
        //第一页数据
        View view1 = getLayoutInflater().inflate(R.layout.layout_gridview_viewpager, null);
        final NoScroolGridView gridView1 = (NoScroolGridView) view1.findViewById(R.id.gridview_viewpager);
        gvList1.add(new NewRecordGridViewModel("购物娱乐", R.mipmap.ic_handcart));
        gvList1.add(new NewRecordGridViewModel("交通出行", R.mipmap.ic_bus));
        gvList1.add(new NewRecordGridViewModel("饮食", R.mipmap.ic_bowl));
        gvList1.add(new NewRecordGridViewModel("日常生活", R.mipmap.ic_home));
        gvList1.add(new NewRecordGridViewModel("收入", R.mipmap.ic_moneybag));
        gvList1.add(new NewRecordGridViewModel("其他", R.mipmap.ic_other));
        gvAdapter1 = new NewRecordGridViewAdapter(this, gvList1, R.layout.item_new_record_gridview);
        gridView1.setAdapter(gvAdapter1);
        list.add(view1);
        //第二页数据
        View view2 = getLayoutInflater().inflate(R.layout.layout_gridview_viewpager, null);
        final NoScroolGridView gridView2 = (NoScroolGridView) view2.findViewById(R.id.gridview_viewpager);
        gvList2 = Hawk.get("newrecord_list");
        if(gvList2 == null){
            gvList2 = new ArrayList<>();
            gvList2.add(new NewRecordGridViewModel("添加自定义", R.mipmap.ic_plus));
            gvList2.add(new NewRecordGridViewModel("添加自定义", R.mipmap.ic_plus));
            gvList2.add(new NewRecordGridViewModel("添加自定义", R.mipmap.ic_plus));
            gvList2.add(new NewRecordGridViewModel("添加自定义", R.mipmap.ic_plus));
            gvList2.add(new NewRecordGridViewModel("添加自定义", R.mipmap.ic_plus));
            gvList2.add(new NewRecordGridViewModel("添加自定义", R.mipmap.ic_plus));
        }
        gvAdapter2 = new NewRecordGridViewAdapter(this, gvList2, R.layout.item_new_record_gridview);
        gridView2.setAdapter(gvAdapter2);
        list.add(view2);
        adapter = new NewRecordViewPagerAdapter(list);
        vpNewRecord.setAdapter(adapter);

        //添加监听事件
        gridView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //对样式进行设置
                setSelectedStyle(gvList1, position);
            }
        });
        gridView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(gvList2.get(position).getDes().equals("添加自定义")){
                    startActivity(new Intent(NewRecordActivity.this,AddTypeActivity_.class));
                    currSecondViewGroupPosition = position;
                }else{
                    setSelectedStyle(gvList2,position);
                }
            }
        });
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

    private void setSelectedStyle(List<NewRecordGridViewModel> gvList, int position) {
        for (NewRecordGridViewModel model : gvList1) {
            model.setSelected(false);
        }
        for (NewRecordGridViewModel model : gvList2) {
            model.setSelected(false);
        }
        gvList.get(position).setSelected(true);
        gvAdapter1.notifyDataSetChanged();
        gvAdapter2.notifyDataSetChanged();
        currSelectedModel = gvList.get(position);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_input_iv0:
                if (result.startsWith("0")) {
                    if (result.length() != 1)
                        result += "0";
                }else{
                    result += "0";
                }
                break;
            case R.id.iv_input_iv1:
                if (result.equals("0")) {
                    result = "1";
                } else {
                    result += "1";
                }
                break;
            case R.id.iv_input_iv2:
                if (result.equals("0")) {
                    result = "2";
                } else {
                    result += "2";
                }
                break;
            case R.id.iv_input_iv3:
                if (result.equals("0")) {
                    result = "3";
                } else {
                    result += "3";
                }
                break;
            case R.id.iv_input_iv4:
                if (result.equals("0")) {
                    result = "4";
                } else {
                    result += "4";
                }
                break;
            case R.id.iv_input_iv5:
                if (result.equals("0")) {
                    result = "5";
                } else {
                    result += "5";
                }
                break;
            case R.id.iv_input_iv6:
                if (result.equals("0")) {
                    result = "6";
                } else {
                    result += "6";
                }
                break;
            case R.id.iv_input_iv7:
                if (result.equals("0")) {
                    result = "7";
                } else {
                    result += "7";
                }
                break;
            case R.id.iv_input_iv8:
                if (result.equals("0")) {
                    result = "8";
                } else {
                    result += "8";
                }
                break;
            case R.id.iv_input_iv9:
                if (result.equals("0")) {
                    result = "9";
                } else {
                    result += "9";
                }
                break;
            case R.id.iv_input_point:
                if (!result.contains(".")) {
                    result += ".";
                }
                break;
            case R.id.iv_input_delete:
                if (result.length() == 1) {
                    result = "0";
                } else {
                    result = result.substring(0, result.length() - 1);
                }
                break;
            case R.id.iv_input_ok:
                if(currSelectedModel==null){
                    Toast.makeText(NewRecordActivity.this, "请选择一个消费类别！", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(Double.parseDouble(result)==0){
                    Toast.makeText(NewRecordActivity.this, "请输入金额！", Toast.LENGTH_SHORT).show();
                    return;
                }
                List<ManagerBean> list = Hawk.get("main_list");
                if(list == null){
                    list = new ArrayList<>();
                }
                ManagerBean bean = new ManagerBean();
                long time = System.currentTimeMillis();
                bean.setInstrument(edNewRecordBeizhu.getText().toString());
                bean.setType(currSelectedModel.getDes());
                bean.setCount(Double.parseDouble(result));
                bean.setResId(currSelectedModel.getResId());
                bean.setTime(time+"");
                if(currSelectedModel.getDes().equals("收入")){
                    bean.setConsumerType(ManagerBean.CUSTOMER_IN);
                }else{
                    bean.setConsumerType(ManagerBean.CUSTOMER_OUT);
                }
                Calendar cal = Calendar.getInstance();
                cal.setTime(new Date(time));
                bean.setWeek(cal.get(Calendar.DAY_OF_WEEK) - 1);
                list.add(bean);
                bean.setMonth(cal.get(Calendar.MONTH));
                Hawk.put("main_list", list);
                EventBus.getDefault().post(new NotifyNewRecordBack());
                this.finish();
                break;
        }
        tvInoutResult.setText("￥" + result);
    }

    @Subscribe
    public void upData(NewRecordGridViewModel event){
        gvList2.set(currSecondViewGroupPosition,event);
        gvAdapter2.notifyDataSetChanged();
        Hawk.put("newrecord_list",gvList2);
    }
}
