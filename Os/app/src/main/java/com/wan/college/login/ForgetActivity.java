package com.wan.college.login;

import android.os.Bundle;
import android.support.v4.view.WindowCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.wan.college.R;
import com.wan.college.network.Const;
import com.wan.college.ui.CustomToast;
import com.wan.college.utils.CountDownTimerUtils;

import java.util.regex.Pattern;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

/**
 * Created by 万文杰 on 2016/12/27.
 */

public class ForgetActivity extends AppCompatActivity{
    // 从短信SDK应用后台注册得到的APPKEY、APPSECRET  仅供测试使用
    private static String APPKEY = "141c17df979bc";
    private static String APPSECRET = "97b2d4c52d8975c5d93664e740d89973";
    private boolean ready;
    private static final int RETRY_INTERVAL = 60; //设置一个倒计时时间
    private int time = RETRY_INTERVAL;
    private EditText number;
    private EditText code;
    private EditText newpwd;
    private LinearLayout layout_replacePwd;
    private Button btn_getCode;
    private LinearLayout layout_checkCode;
    private Button next;
    private Button btn_ok_forget;
    private LinearLayout layout_getCode;
    private String upwd;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置窗口风格为顶部显示Actionbar
        supportRequestWindowFeature(WindowCompat.FEATURE_ACTION_BAR);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        setContentView(R.layout.activity_forget);
//        initSDK();//短信SDK初始化
        initView();
        setGetCodeListener();
        setNextListener();
        setConfirmListener();
    }

    private void setConfirmListener() {
        btn_ok_forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                upwd=newpwd.getText().toString().trim();
                if(upwd.length()<6){
                    CustomToast.showDone(ForgetActivity.this,"密码必须大于六位");
                }else {
                    CustomToast.showDone(ForgetActivity.this,"修改成功");
                    finish();
                }
            }
        });
    }

    private void initView() {
        btn_ok_forget= (Button) findViewById(R.id.btn_ok_forget);
        newpwd= (EditText) findViewById(R.id.newpwd);
        code = (EditText)findViewById(R.id.et_code_forget);
        layout_replacePwd = (LinearLayout)findViewById(R.id.rpwd);
        btn_getCode = (Button)findViewById(R.id.btn_getCode_forget);
        layout_checkCode = (LinearLayout)findViewById(R.id.ll_checkCode_forget);
        next = (Button)findViewById(R.id.btn_next_forget);
        number = (EditText)findViewById(R.id.et_phone_forget);
        layout_getCode = (LinearLayout)findViewById(R.id.ll_getCode_forget);

    }

    private void setNextListener() {

        number.setText("");
        number.requestFocus();
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String country = "86";
                String uname = number.getText().toString().trim();
                String uvcode = code.getText().toString().trim();
                if(uvcode==null||uvcode.isEmpty()){
                    CustomToast.showDone(ForgetActivity.this,"验证码不能为空");
                }else {
//                SMSSDK.submitVerificationCode(country, uname, uvcode);//此API是验证的时，请求验证码是否正确的函数
                layout_checkCode.setVisibility(View.INVISIBLE);
                layout_replacePwd.setVisibility(View.VISIBLE);
                btn_getCode.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    private void setGetCodeListener() {

        number.setText(" ");
        number.requestFocus();

        btn_getCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String country = "86";
                String uname = number.getText().toString().trim();
                boolean isPhone = Pattern.compile(Const.PHONE_PATTERN).matcher(uname).matches();
                if(uname.equals("")){
                    Toast.makeText(ForgetActivity.this, "手机号不能为空", Toast.LENGTH_SHORT).show();
                }else if(isPhone){
//                    SMSSDK.getVerificationCode(country,uname);
                    layout_getCode.setVisibility(View.INVISIBLE);
                    layout_checkCode.setVisibility(View.VISIBLE);
//                    SMSSDK.getVerificationCode(country,uname);
//                    CountDownTimerUtils mCountDownTimerUtils = new CountDownTimerUtils(btn_getCode, 60000, 1000);
//                    mCountDownTimerUtils.start();
                }else {
                    CustomToast.showDone(ForgetActivity.this,"请输入正确的手机号");
                }

            }

        });

    }

    private void initSDK() {
        // 初始化短信SDK
        SMSSDK.initSDK(this, APPKEY, APPSECRET);
        EventHandler eh = new EventHandler() {
            @Override
            public void afterEvent(int event, int result, Object data) {

                switch (event) {
                    case SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE:
                        if (result == SMSSDK.RESULT_COMPLETE) {
                            toast("验证成功");
                        } else {
                            toast("验证失败");
                        }
                        break;
                    case SMSSDK.EVENT_GET_VERIFICATION_CODE:
                        if (result == SMSSDK.RESULT_COMPLETE) {

                            toast("验证码已发送，注意查收");                        //默认的智能验证是开启的,我已经在后台关闭
                         //   dotimer();
                        } else {
                            toast("获取验证码失败");
                        }
                        break;
                }
            }
        };
        SMSSDK.registerEventHandler(eh); //注册短信回调监听接口
        //  Toast.makeText(RegisterActivity.this, "注册短信回调监听接口", Toast.LENGTH_SHORT).show();
        ready = true;
    }

    private void toast(final String str) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(ForgetActivity.this, str, Toast.LENGTH_SHORT).show();
            }
        });
    }
    protected void onDestroy() {
        if (ready) {
            // 销毁回调监听接口
            SMSSDK.unregisterAllEventHandler();
        }
        super.onDestroy();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:// 点击返回图标事件
                this.finish();

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
