package com.diandian.ycdyus.moneymanager.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.diandian.ycdyus.moneymanager.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.io.File;

import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.listener.UploadFileListener;

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
        builder.setMessage("如果你能坚持记账一周，大哥才能相信你有足够的毅力使用攒钱目标功能~！不然还是算了吧~嘿嘿！");
        builder.setTitle("提示");
        builder.setPositiveButton("切~！走着瞧~", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String picPath = "sdcard/rollerkid/1458191946091.jpg";
                final BmobFile bmobFile = new BmobFile(new File(picPath));
                bmobFile.uploadblock(BudgetActivity.this, new UploadFileListener() {

                    @Override
                    public void onSuccess() {
                        // TODO Auto-generated method stub
                        //bmobFile.getUrl()---返回的上传文件的地址（不带域名）
                        //bmobFile.getFileUrl(context)--返回的上传文件的完整地址（带域名）
                        Toast.makeText(BudgetActivity.this, "上传文件成功:" + bmobFile.getFileUrl(BudgetActivity.this), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onProgress(Integer value) {
                        // TODO Auto-generated method stub
                        // 返回的上传进度（百分比）
                    }

                    @Override
                    public void onFailure(int code, String msg) {
                        // TODO Auto-generated method stub
                        Toast.makeText(BudgetActivity.this, "上传文件失败"+msg, Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.dismiss();
            }
        });
        builder.create().show();
    }
}
