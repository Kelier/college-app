package com.wan.college.login;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.wan.college.R;
import com.wan.college.Tools.SharedTool;
import com.wan.college.activity.FooterActivity;
import com.wan.college.network.CallbBackString;
import com.wan.college.network.HttpServer;
import com.wan.college.network.INetResult;
import com.wan.college.network.InitConfig;
import com.wan.college.network.UserMsg;
import com.wan.college.ui.CustomToast;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;


/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity  implements INetResult<String>{

    Toolbar toolbar;
    TextView centerTtitle;
    ActionBar actionBar;
    EditText uname;
    EditText upwd;
    Button login_btn;
    Button register_btn;
    ImageView image_left_face;
    ImageView image_right_face;
    private Drawable mIconUser;
    private Drawable mIconPwd;
    int iconHeight;
    int iconWidth;
    int iconLeft;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initWindow();
        setView();
        isUnameAndUpwdNotEmpty();
        setListener();
    }
    private void initWindow(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintResource(R.color.color_theme);//通知栏所需颜色
        }
        centerTtitle = (TextView) findViewById(R.id.title);
        centerTtitle.setText(R.string.title_activity_login);//自定义标题
        toolbar = (Toolbar) findViewById(R.id.id_toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        if(actionBar!=null){
            //时间不足，有后登录模式改为先登录模式，取消箭头
            actionBar.setDisplayHomeAsUpEnabled(true);//设置返回箭头显示
        }
    }


    private void setView(){
        image_left_face=(ImageView) findViewById(R.id.image22);
        image_right_face=(ImageView) findViewById(R.id.image33);
        image_left_face.setImageResource(R.drawable.ic_22);
        image_right_face.setImageResource(R.drawable.ic_33);

        uname=(EditText) findViewById(R.id.username);
        upwd=(EditText) findViewById(R.id.password);

        login_btn=(Button) findViewById(R.id.login_btn);
        register_btn=(Button) findViewById(R.id.register_btn);

        uname.post(new Runnable() {
            @Override
            public void run() {
                /*
                iconHeight=(int)(upwd.getHeight()*0.6);
                iconWidth=iconHeight-20;
                iconLeft=(int)(iconHeight*0.3);
                mIconUser=getResources().getDrawable(R.drawable.ic_login_username_change);
                mIconPwd=getResources().getDrawable(R.drawable.ic_login_password_default);
                mIconUser.setBounds(iconLeft, 0, iconHeight, iconWidth);
                mIconPwd.setBounds(iconLeft, 0, iconHeight, iconWidth);
                uname.setCompoundDrawables(mIconUser, null, null,null );
                upwd.setCompoundDrawables(mIconPwd, null, null,null );
                */
            }
        });
    }
    private void setListener(){
        uname.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                isUnameAndUpwdNotEmpty();
//                if (hasFocus) {
//                    mIconUser=getResources().getDrawable(R.drawable.ic_login_username_change);
//                    mIconUser.setBounds(iconLeft, 0,iconHeight, iconWidth);
//                    uname.setCompoundDrawables(mIconUser, null, null,null );
//                }else{
//                    mIconUser=getResources().getDrawable(R.drawable.ic_login_username_default);
//                    mIconUser.setBounds(iconLeft, 0,iconHeight, iconWidth);
//                    uname.setCompoundDrawables(mIconUser, null, null,null );
//                }
            }
        });
        uname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                isUnameAndUpwdNotEmpty();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        upwd.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                isUnameAndUpwdNotEmpty();
                if (hasFocus) {
/*                image_left_face.setImageResource(R.drawable.ic_22_hide);
                  image_right_face.setImageResource(R.drawable.ic_33_hide);
                    mIconPwd=getResources().getDrawable(R.drawable.ic_login_password_change);
                    mIconPwd.setBounds(iconLeft, 0,iconHeight, iconWidth);
                    upwd.setCompoundDrawables(mIconPwd, null, null,null );
                }else{
                   image_left_face.setImageResource(R.drawable.ic_22);
                    image_right_face.setImageResource(R.drawable.ic_33);
                    mIconPwd=getResources().getDrawable(R.drawable.ic_login_password_default);
                    mIconPwd.setBounds(iconLeft, 0,iconHeight, iconWidth);
                    upwd.setCompoundDrawables(mIconPwd, null, null,null );
                    */
                }
            }
        });
        upwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                isUnameAndUpwdNotEmpty();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
//        login_btn.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if(event.getAction()==KeyEvent.ACTION_DOWN){
//                    login_btn.setBackgroundResource(R.drawable.loginbtn_down);
//                }else if(event.getAction()==KeyEvent.ACTION_UP){
//                    login_btn.setBackgroundResource(R.drawable.loginbtn);
//                }
//                return false;
//            }
//        });
        login_btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String username=uname.getText().toString();
                String password=upwd.getText().toString();
//                login_validate(username,password);
                HttpServer.postLogin(username,password,new CallbBackString(LoginActivity.this));
            }
        });
        register_btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent();
                intent.setClass(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

//        register_btn.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if(event.getAction()==KeyEvent.ACTION_DOWN){
//                    register_btn.setBackgroundResource(R.drawable.registerbtn_down);
//                }else if(event.getAction()==KeyEvent.ACTION_UP){
//                    register_btn.setBackgroundResource(R.drawable.registerbtn);
//                }
//                return false;
//            }
//        });
    }

//    private void login_validate(String uname,String upwd){
//        String url = InitConfig.baseUrl + "androidlogin";
//        RequestParams rp = new RequestParams(url);
//        rp.addBodyParameter("uname", uname);
//        rp.addBodyParameter("upwd", upwd);
//        x.http().post(rp, new Callback.CommonCallback<String>() {
//            @Override
//            public void onSuccess(String result) {
//                Toast.makeText(LoginActivity.this,result,Toast.LENGTH_LONG).show();
//                JSONObject res = JSON.parseObject(result);
//                if(res.getBoolean("login")){
//                    loginSuccess();
//
//                }
//            }
//            @Override
//            public void onError(Throwable ex, boolean isOnCallback) {
//                Toast.makeText(LoginActivity.this,"请求失败",Toast.LENGTH_LONG).show();
//                intent = new Intent();
//                intent.putExtra("login",true);
//                setResult(1, intent);
//                LoginActivity.this.finish();
//            }
//            @Override
//            public void onCancelled(CancelledException cex) {
//            }
//            @Override
//            public void onFinished() {
//            }
//        });
//    }

    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    private Boolean isUnameAndUpwdNotEmpty() {
        String username=uname.getText().toString();
        String password=upwd.getText().toString();
        if(password.contains(" ")){
            login_btn.getBackground().setAlpha(100);
            login_btn.setEnabled(false);
            Toast.makeText(LoginActivity.this,"密码错误，不能有空格",Toast.LENGTH_SHORT).show();
        }else if(username.trim().length()>0&&password.trim().length()>0){
            login_btn.getBackground().setAlpha(255);
            login_btn.setEnabled(true);
            return true;
        }else{
            login_btn.getBackground().setAlpha(100);
            login_btn.setEnabled(false);
        }
        return false;
    }



    protected void onStop() {
        super.onStop();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
       // toolbar.inflateMenu(R.menu.toolbar_menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:// 点击返回图标事件
                //////未连接服务器测试
                intent = new Intent();
                intent.putExtra("login",false);
                setResult(RESULT_OK, intent);
                //////////
                this.finish();
                break;
            case R.id.forgetPwd:// 点击忘记密码
                intent = new Intent();
                intent.setClass(LoginActivity.this, ForgetActivity.class);
                startActivity(intent);
                break;
            default:

        }
        return super.onOptionsItemSelected(item);
    }
    // 监听手机上的BACK键
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            intent = new Intent();
            intent.putExtra("login",false);
            setResult(RESULT_OK, intent);
            this.finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void getNetData(String data) {
        JSONObject jData = JSON.parseObject(data);
        if(jData.getBoolean("loginResult")){
            UserMsg.uid =jData.getString("uid");
            SharedTool.recordUid(this,jData.getString("uid"));
            intent = new Intent();
            intent.putExtra("login",true);
            setResult(0, intent);
            finish();
        }else {
            CustomToast.showDone(LoginActivity.this,"用户名或密码错误！");
        }
    }
}

