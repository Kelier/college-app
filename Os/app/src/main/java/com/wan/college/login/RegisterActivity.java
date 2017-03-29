package com.wan.college.login;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.WindowCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.wan.college.R;
import com.wan.college.Tools.SharedTool;
import com.wan.college.activity.FooterActivity;
import com.wan.college.activity.GuideActivity;
import com.wan.college.model.CountryData;
import com.wan.college.network.CallbBackString;
import com.wan.college.network.Const;
import com.wan.college.network.HttpServer;
import com.wan.college.network.INetResult;
import com.wan.college.network.UserMsg;
import com.wan.college.ui.CustomToast;
import com.wan.college.utils.CountDownTimerUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import cn.pedant.SweetAlert.SweetAlertDialog;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

public class RegisterActivity extends AppCompatActivity implements INetResult<String>{
    private  Button btn_getCode_register;
    private  LinearLayout adress;
    private Spinner spinner_adress;
    private String str;
    private TextView tv_adress;
    private EditText number;
    private LinearLayout layout_checkCode;
    private LinearLayout layout_getCode;
    private Button next;
    private Button btn_ok_register;
    private EditText code;
    private TextView title;
    private TextView country_id;
    private LinearLayout deal;
    private String upwd;
    private LinearLayout layout_setPwd;
    private String uname;
    private static String APPKEY = "141c17df979bc";
    private static String APPSECRET = "97b2d4c52d8975c5d93664e740d89973";
    private boolean ready;
    private static final int RETRY_INTERVAL = 60; //设置一个倒计时时间
    private int time = RETRY_INTERVAL;
    private EditText et_pwd_register;
    private CountryData countryData;
    SweetAlertDialog pDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置窗口风格为顶部显示Actionbar
        supportRequestWindowFeature(WindowCompat.FEATURE_ACTION_BAR);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        setContentView(R.layout.activity_register);
        initView();
        setSpinnerAdapter();
        setAdressListener();
        setSpinnerListener();
        setOkListener();
        setGetCodeListener();
        initSDK();
        setNextListener();
    }
    //初始化控件
    private void initView() {
        et_pwd_register= (EditText) findViewById(R.id.et_pwd_register);
        spinner_adress= (Spinner) findViewById(R.id.spinner_adress);
        adress= (LinearLayout) findViewById(R.id.adress);
        spinner_adress= (Spinner) findViewById(R.id.spinner_adress);
        tv_adress= (TextView) findViewById(R.id.tv_adress);
        country_id= (TextView) findViewById(R.id.country_id);
        btn_getCode_register= (Button) findViewById(R.id.btn_getCode_register);
        number= (EditText) findViewById(R.id.et_phone_register);
        layout_checkCode= (LinearLayout) findViewById(R.id.ll_checkCode_register);
        layout_getCode= (LinearLayout) findViewById(R.id.ll_getCode_register);
        next= (Button) findViewById(R.id.next_register);
        btn_ok_register= (Button) findViewById(R.id.btn_ok_register);
        code= (EditText) findViewById(R.id.code_register);
        title= (TextView) findViewById(R.id.tv_title_register);
        deal= (LinearLayout) findViewById(R.id.ll_deal_register);
        layout_setPwd= (LinearLayout) findViewById(R.id.ll_setpwd_register);
        pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
    }
//设置密码界面，录入数据库信息并跳入主界面
    private void setOkListener() {
        btn_ok_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upwd=et_pwd_register.getText().toString().trim();
                if(upwd.length()<6){
                    Toast.makeText(RegisterActivity.this,"密码必须大于6位",Toast.LENGTH_SHORT).show();
                }else if(upwd.contains(" ")){
                    Toast.makeText(RegisterActivity.this,"密码含有非法字符",Toast.LENGTH_SHORT).show();
                }else {
                    HttpServer.postRegisterInfo(uname, upwd, new CallbBackString(RegisterActivity.this));
                }
            }
        });
    }



//设置注册界面地区选择Spinnner适配器
    private void setSpinnerAdapter() {
        countryData=new Gson().fromJson(Const.getCountryData(this),CountryData.class);
        final List<String> conntry=new ArrayList<>();
        for (int i=0;i<countryData.getList().size();i++) conntry.add(countryData.getList().get(i).getCname());
        ArrayAdapter<String> adapter=new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,conntry);
        adapter.setDropDownViewResource(R.layout.spinner_item);
        spinner_adress.setAdapter(adapter);
        spinner_adress.setPrompt("选择地区");
    }
    /*
    public boolean performClick() {
        Mydialog().show();
        return true;
    }
    Mydialog()为自定义dialog方法 屏蔽Spinner本身的dialog方法
    **/

    //设置接收验证码界面“下一步”按钮监听,显示设置密码界面
    private void setNextListener() {

        number.setText("");
        number.requestFocus();
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String country = "86";
                uname = number.getText().toString().trim();
                String uvcode = code.getText().toString().trim();
                if(uvcode==""||uvcode.isEmpty()){
                    CustomToast.showDone(RegisterActivity.this,"验证码不能为空");
                }else{
                    SMSSDK.submitVerificationCode(country, uname, uvcode);//此API是验证的时，请求验证码是否正确的函数

                }

            }
        });
    }
    //设置注册界面的“获取验证码”按钮监听，显示输入验证码界面
    private void setGetCodeListener() {

        number.setText("");
        number.requestFocus();

        btn_getCode_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String country = "86";
                uname = number.getText().toString().trim();
                boolean isPhone = Pattern.compile(Const.PHONE_PATTERN).matcher(uname).matches();
                if(uname.equals("")||uname.isEmpty()){
                    Toast.makeText(RegisterActivity.this, "手机号码不能为空", Toast.LENGTH_SHORT).show();
                }else if(isPhone){
                    SMSSDK.getVerificationCode(country,uname);

                    pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                    pDialog.setTitleText("Loading");
                    pDialog.setCancelable(false);
                    pDialog.show();
//                    SMSSDK.getVerificationCode(country,uname);
//                    CountDownTimerUtils mCountDownTimerUtils = new CountDownTimerUtils(btn_getCode_register, 60000, 1000);
//                    mCountDownTimerUtils.start();
                }else {
                    CustomToast.showDone(RegisterActivity.this,"请输入正确的手机号");
                }

            }

        });

    }

//设置注册界面地区选择Spinner的dialog弹出事件,将dialog中的值返回给TextView
    private void setSpinnerListener() {

        spinner_adress.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               // str= (String) spinner_adress.getSelectedItem();
                CustomToast.showDone(RegisterActivity.this,"？？？？");
                tv_adress.setText(countryData.getList().get(position).getCname());
                country_id.setText(countryData.getList().get(position).getCountry_id());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
               // tv_adress.setText(str);
            }
        });
    }
//为注册界面的伪Spinner设置dialog弹出
    private void setAdressListener() {
        adress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinner_adress.performClick();

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
                            Message message = new Message();
                            message.what = 2;
                            refreshViewhandler.sendMessage(message);
                        } else {
                            toast("验证失败");
                        }
                        break;
                    case SMSSDK.EVENT_GET_VERIFICATION_CODE:
                        if (result == SMSSDK.RESULT_COMPLETE) {

                            toast("验证码已发送，注意查收");                        //默认的智能验证是开启的,我已经在后台关闭
                            Message message = new Message();
                            message.what = 1;
                            refreshViewhandler.sendMessage(message);
                              // dotimer();
                        } else {
                            toast("获取验证码失败");
                        }
                        pDialog.dismiss();
                        break;
                }
            }
        };
        SMSSDK.registerEventHandler(eh); //注册短信回调监听接口
        //  Toast.makeText(RegisterActivity.this, "注册短信回调监听接口", Toast.LENGTH_SHORT).show();
        ready = true;
    }
    Handler refreshViewhandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            // 要做的事情
            refreshView(msg.what);
        }
    };
    public void refreshView(int i){
        if(i==1){
            layout_getCode.setVisibility(View.GONE);
            title.setVisibility(View.GONE);
            adress.setVisibility(View.GONE);
            layout_checkCode.setVisibility(View.VISIBLE);
            btn_getCode_register.setVisibility(View.GONE);
            deal.setVisibility(View.GONE);
        }
        if(i==2){
            layout_checkCode.setVisibility(View.GONE);
            layout_setPwd.setVisibility(View.VISIBLE);
        }
    }
    private void toast(final String str) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(RegisterActivity.this, str, Toast.LENGTH_SHORT).show();
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

    @Override
    public void getNetData(String data) {
        JSONObject jData= JSONObject.parseObject(data);
        switch (jData.getInteger("registerResult"))
        {
            case 200:
                UserMsg.uid=jData.getString("uid");
                registerSuccess();
                break;
            case 400:
                registerFailure();
                break;
            case 500:
                registerExist();
                break;
        }

    }

    private void registerExist() {
        CustomToast.showDone(RegisterActivity.this,"该手机号已被注册！");
        et_pwd_register.setText("");
        layout_getCode.setVisibility(View.VISIBLE);
        title.setVisibility(View.VISIBLE);
        adress.setVisibility(View.VISIBLE);
        btn_getCode_register.setVisibility(View.VISIBLE);
        deal.setVisibility(View.VISIBLE);
        layout_setPwd.setVisibility(View.GONE);
        number.setText("");
    }

    private void registerFailure() {
        CustomToast.showDone(RegisterActivity.this,"注册失败！");
        layout_getCode.setVisibility(View.VISIBLE);
        title.setVisibility(View.VISIBLE);
        adress.setVisibility(View.VISIBLE);
        btn_getCode_register.setVisibility(View.VISIBLE);
        deal.setVisibility(View.VISIBLE);
        layout_setPwd.setVisibility(View.GONE);
        number.setText("");
    }

    private void registerSuccess(){
        CustomToast.showDone(RegisterActivity.this,"注册成功！");
     //   SharedTool.recordUser(getApplicationContext(),uname, upwd);
        SharedTool.recordUid(this,UserMsg.uid);
        Intent intent = new Intent();
        intent.setClass(RegisterActivity.this, GuideActivity.class);
        startActivity(intent);
        finish();
    }
}
