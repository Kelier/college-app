package com.wan.college.guide;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.wan.college.R;
import com.wan.college.Tools.SharedTool;
import com.wan.college.activity.GuideActivity;
import com.wan.college.fragment.MainFragmentPagerActivity;



/**
 * @author 万文杰 2016-12-28
 */

public class ParallaxActivity extends Activity {

    ImageView iv_man;
    ParallaxContainer parallaxContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parallax);
        initWindow();
        SharedTool.saveAppVersion(this);
        /**
         * 动画支持11以上sdk,11以下默认不显示动画
         * 若需要支持11以下动画,也可导入https://github.com/JakeWharton/NineOldAndroids
         */
        if (Build.VERSION.SDK_INT > 10) {
            iv_man = (ImageView) findViewById(R.id.iv_man);
            parallaxContainer = (ParallaxContainer) findViewById(R.id.parallax_container);

            if (parallaxContainer != null) {
                parallaxContainer.setImage(iv_man);
                parallaxContainer.setLooping(false);

                iv_man.setVisibility(View.VISIBLE);
                parallaxContainer.setupChildren(getLayoutInflater(),
                        R.layout.view_intro_1, R.layout.view_intro_2,
                        R.layout.view_intro_3, R.layout.view_intro_4,
                        R.layout.view_intro_5, R.layout.view_login);
            }
        }
        else{
            setContentView(R.layout.view_login);
        }


        Button login= (Button) findViewById(R.id.login_btn);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*必须先登录才能查看内容模式
                if (spf.getBoolean("login", false)){
                    intent = new Intent();
                    intent.setClass(ParallaxActivity.this, MainFragmentPagerActivity.class);
                    intent.putExtra("uname", spf.getString("uname", ""));
                    intent.putExtra("upwd", spf.getString("upwd", ""));
                }
                else{
                    intent.setClass(ParallaxActivity.this, LoginActivity.class);
                }
                */
                Intent intent = new Intent();
                intent.setClass(ParallaxActivity.this, GuideActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade,
                        R.anim.hold);
                finish();
            }
        });

    }
    //沉浸式布局
    private void initWindow() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedTool.saveAppVersion(this);
    }
}
