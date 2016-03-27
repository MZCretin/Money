package com.diandian.ycdyus.moneymanager.ui;

import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.diandian.ycdyus.moneymanager.R;
import com.diandian.ycdyus.moneymanager.eventbus.LoginSuccess;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.greenrobot.eventbus.EventBus;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.SaveListener;

@EActivity(R.layout.activity_login)
public class LoginActivity extends AppCompatActivity {
    @ViewById
    ImageView loginState;
    @ViewById
    ImageView btnCheckState;
    @ViewById
    EditText edittextName;
    @ViewById
    EditText edittextPassword;
    @ViewById
    Button btnLogin;
    @ViewById
    ImageView skip;

    private String name;
    private String password;
    //false 登录状态 true 注册状态
    private boolean flag = true;

    @AfterViews
    public void init() {
        getSupportActionBar().hide();

        btnCheckState.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag) {
                    flag = false;
                    loginState.setImageResource(R.mipmap.m01_status_signup);
                    btnLogin.setBackgroundResource(R.mipmap.m06_button_signup);
                    btnCheckState.setImageResource(R.mipmap.m01_button_login);
                } else {
                    flag = true;
                    loginState.setImageResource(R.mipmap.m01_status_login);
                    btnLogin.setBackgroundResource(R.mipmap.m06_button_login);
                    btnCheckState.setImageResource(R.mipmap.m01_button_signin);
                }
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = edittextName.getText().toString();
                password = edittextPassword.getText().toString();
                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(LoginActivity.this, "用户名不能为空！", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(LoginActivity.this, "密码不能为空！", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.length() < 6) {
                    Toast.makeText(LoginActivity.this, "密码长度不能少于6", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!flag) {
                    register();
                }else{
                    login();
                }
            }
        });


    }

    private void login() {
        BmobUser bu2 = new BmobUser();
        bu2.setUsername(name);
        bu2.setPassword(password);
        bu2.login(this, new SaveListener() {
            @Override
            public void onSuccess() {
                Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                LoginActivity.this.finish();
                LoginSuccess loginSuccess = new LoginSuccess();
                loginSuccess.setName(name);
                EventBus.getDefault().post(loginSuccess);
            }
            @Override
            public void onFailure(int code, String msg) {
                Toast.makeText(LoginActivity.this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void register() {
        BmobUser bu = new BmobUser();
        bu.setUsername(name);
        bu.setPassword(password);
        bu.signUp(this, new SaveListener() {
            @Override
            public void onSuccess() {
                Toast.makeText(LoginActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                flag = true;
                loginState.setImageResource(R.mipmap.m01_status_login);
                btnLogin.setBackgroundResource(R.mipmap.m06_button_login);
                btnCheckState.setImageResource(R.mipmap.m01_button_signin);
            }

            @Override
            public void onFailure(int code, String msg) {
                if (code == 202) {
                    Toast.makeText(LoginActivity.this, "该用户已注册", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(LoginActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
