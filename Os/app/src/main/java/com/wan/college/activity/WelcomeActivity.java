package com.wan.college.activity;

import com.wan.college.R;
import com.wan.college.Tools.SharedTool;
import com.wan.college.fragment.MainFragmentPagerActivity;
import com.wan.college.guide.ParallaxActivity;
import com.wan.college.model.User;
import com.wan.college.network.UserMsg;
import com.wan.college.utils.SQLiteDBUtils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import java.util.List;


public class WelcomeActivity extends Activity {
	Intent intent;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		final Window window = getWindow();// 获取当前的窗体对象
		window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);// 隐藏了状态栏
		requestWindowFeature(Window.FEATURE_NO_TITLE);// 隐藏了标题栏
		setContentView(R.layout.welcome);
		welcomeUI();
	}

	private void welcomeUI()
	{
		new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				try
				{	Message message = new Message();
					getUserData.sendMessage(message);
					Thread.sleep(2000);
					startActivity(intent);
					overridePendingTransition(R.anim.fade,
							R.anim.hold);
					WelcomeActivity.this.finish();
					//welHandler.sendMessage(message);
				} catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}
		}).start();
	}

	Handler welHandler = new Handler()
	{
		@Override
		public void handleMessage(Message msg)
		{
			welcomeFunction();
		}
	};
	Handler getUserData = new Handler()
	{
		@Override
		public void handleMessage(Message msg)
		{
			vertifySp();
		}
	};

	public void vertifySp(){
		intent = new Intent();
		if(SharedTool.isFirst(this)){
			SharedTool.saveAppVersion(this);
			intent.setClass(WelcomeActivity.this, ParallaxActivity.class);
		}else{
			String uid=SharedTool.getUid(this);//是否登录过
			if(uid==null){//如果没登录过，保存记录过的临时数据
				UserMsg.score=SharedTool.getScore(this);
				UserMsg.province=SharedTool.getProvince(this);
				UserMsg.subject=SharedTool.getSubject(this);
				intent.setClass(WelcomeActivity.this, FooterActivity.class);
			}else{
				SQLiteDBUtils sqLiteDBUtils = new SQLiteDBUtils(this, User.class);//实例化对象
				sqLiteDBUtils.openDataBase();//打开数据库
				List<User> user=sqLiteDBUtils.queryData("uid",uid);
				sqLiteDBUtils.closeDataBase();
				if(user!=null&&user.size()>0){
					Log.i("sqlite","本地数据库内有此用户数据");
					Log.i("sqlite.Score",user.get(0).getScore());
					Log.i("sqlite.Subject",user.get(0).getSubject());
					Log.i("sqlite.Province",user.get(0).getProvince());
					Log.i("sqlite.Nickname",user.get(0).getNickname());
					Log.i("sqlite.Gender",user.get(0).getGender());
					Log.i("sqlite.Avatar",user.get(0).getAvatar());
				//	Log.i("sqlite.Uid",user.get(0).getUid());
					UserMsg.uid=uid;
				//	UserMsg.uid=user.get(0).getUid();
					UserMsg.score=user.get(0).getScore();
					UserMsg.subject=user.get(0).getSubject();
					UserMsg.province=user.get(0).getProvince();
					UserMsg.nickname=user.get(0).getNickname();
					UserMsg.gender=user.get(0).getGender();
					UserMsg.avatar=user.get(0).getAvatar();
					intent.setClass(WelcomeActivity.this, FooterActivity.class);
				}else {
					Log.i("sqlite","本地数据库内没找到用户数据");
					Log.i("sqlite","临时uid:"+uid);
					UserMsg.uid=uid;
					UserMsg.score=SharedTool.getScore(this);
					UserMsg.province=SharedTool.getProvince(this);
					UserMsg.subject=SharedTool.getSubject(this);
					intent.setClass(WelcomeActivity.this, FooterActivity.class);
					//intent.setClass(WelcomeActivity.this, GuideActivity.class);
				}
			}
		}
	}

	public void welcomeFunction ()
	{
		//Intent intent = new Intent();
		/*必须先登录后进入主页的模式检测
		if(version==null||!version.equals(getVersionName())){
			intent.setClass(WelcomeActivity.this, ParallaxActivity.class);
		}else if(spf.getBoolean("login",false)){
			intent.setClass(WelcomeActivity.this, MainFragmentPagerActivity.class);
		}else{
			intent.setClass(WelcomeActivity.this, LoginActivity.class);
		}
		*/
		if(SharedTool.isFirst(this)){
			intent.setClass(WelcomeActivity.this, ParallaxActivity.class);
		}else{
			intent.setClass(WelcomeActivity.this, MainFragmentPagerActivity.class);
		}
		startActivity(intent);
		overridePendingTransition(R.anim.fade,
				R.anim.hold);
		this.finish();
	}


	//获取其他应用版本名
	public static String getAppVersionName(Context context) {
		String versionName = "";
		int versioncode;
		try {
// ---get the package info---
			PackageManager pm = context.getPackageManager();
			PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
			versionName = pi.versionName;
			versioncode = pi.versionCode;
			if (versionName == null || versionName.length() <= 0) {
				return"";
			}
		} catch (Exception e) {
		//	Log.e("VersionInfo", "Exception", e);
		}
		return versionName;
	}
	//--------------------------------------------------------------------------

}