package com.diandian.ycdyus.moneymanager.ui;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.diandian.ycdyus.moneymanager.R;
import com.diandian.ycdyus.moneymanager.view.CheckSwitchButton;
import com.diandian.ycdyus.moneymanager.view.CheckSwitchButtonPeriod;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

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

    @AfterViews
    public void init() {
        getSupportActionBar().hide();

        switchState.setChecked(true);
        ivNewBuegetBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BudgetManagerActivity.this.finish();
            }
        });

        switchState.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
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

            }
        });
    }
}
