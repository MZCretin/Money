package com.diandian.ycdyus.moneymanager.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.diandian.ycdyus.moneymanager.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_budget)
public class BudgetActivity extends AppCompatActivity {
    @ViewById
    Button ivBudgetBudget;
    @ViewById
    Button ivBudgetTarget;
    @ViewById
    ImageView ivNewBuegetBack;

    @AfterViews
    public void init() {
        getSupportActionBar().hide();

        ivBudgetBudget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BudgetActivity.this, BudgetManagerActivity_.class));
            }
        });

        ivBudgetTarget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialog();
            }
        });

        ivNewBuegetBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BudgetActivity.this.finish();
            }
        });
    }

    private void showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setMessage("如果你能坚持记账一周，露米才能相信你有足够的毅力使用攒钱目标功能~！不然还是算了吧~嘿嘿！");
        builder.setTitle("提示");
        builder.setPositiveButton("切~！走着瞧~", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }
}
