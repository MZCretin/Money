package com.diandian.ycdyus.moneymanager.ui;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.diandian.ycdyus.moneymanager.R;
import com.diandian.ycdyus.moneymanager.model.BudgetManagerModel;
import com.diandian.ycdyus.moneymanager.model.BudgetStateChangeModel;
import com.diandian.ycdyus.moneymanager.view.CheckSwitchButton;
import com.diandian.ycdyus.moneymanager.view.CheckSwitchButtonPeriod;
import com.orhanobut.hawk.Hawk;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.greenrobot.eventbus.EventBus;

@EActivity(R.layout.activity_budget_manager)
public class BudgetManagerActivity extends AppCompatActivity {
    @ViewById
    ImageView ivNewBuegetBack;
    @ViewById
    CheckSwitchButton switchState;
    @ViewById
    CheckSwitchButtonPeriod switchPeriod;
    @ViewById
    TextView tvBudgetJine;
    @ViewById
    LinearLayout llBudgetContainer;
    private BudgetManagerModel model;
    private EditText editText;

    @AfterViews
    public void init() {
        getSupportActionBar().hide();

        model = Hawk.get("budget_manager");

        if (model == null) {
            model = new BudgetManagerModel();
        }

        switchState.setChecked(model.isBudgetStatus());
        switchPeriod.setChecked(model.isBudgetPeriod());
        tvBudgetJine.setText("￥" + model.getBudgetCount());

        if(model.isBudgetStatus()==false){
            llBudgetContainer.setVisibility(View.VISIBLE);
        }else{
            llBudgetContainer.setVisibility(View.GONE);
        }

        ivNewBuegetBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BudgetManagerActivity.this.finish();
            }
        });

        switchState.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                model.setBudgetCount(Float.parseFloat(tvBudgetJine.getText().toString().replace("￥", "")));
                model.setBudgetStatus(isChecked);
                Hawk.put("budget_manager", model);
                EventBus.getDefault().post(new BudgetStateChangeModel());
                if (!isChecked) {
                    llBudgetContainer.setVisibility(View.VISIBLE);
                } else {
                    llBudgetContainer.setVisibility(View.GONE);
                }
            }
        });
        switchPeriod.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                model.setBudgetCount(Float.parseFloat(tvBudgetJine.getText().toString().replace("￥", "")));
                model.setBudgetPeriod(isChecked);
                Hawk.put("budget_manager", model);
                EventBus.getDefault().post(new BudgetStateChangeModel());
            }
        });
        tvBudgetJine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText = new EditText(BudgetManagerActivity.this);
                editText.setInputType(EditorInfo.TYPE_CLASS_NUMBER);
                new AlertDialog.Builder(BudgetManagerActivity.this).setTitle("请输入预算金额:").setIcon(
                        android.R.drawable.ic_dialog_info).setView(
                        editText).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        model.setBudgetCount(Float.parseFloat(editText.getText().toString()));
                        tvBudgetJine.setText("￥" + editText.getText().toString());
                        Hawk.put("budget_manager", model);
                        EventBus.getDefault().post(new BudgetStateChangeModel());
                    }
                })
                        .setNegativeButton("取消", null).show();
            }
        });
    }

}
