package com.wan.college.fragment;

import java.util.ArrayList;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wan.college.R;
import com.wan.college.Tools.SharedTool;
import com.wan.college.adapter.MainFragmentPagerAdapter;
import com.wan.college.login.LoginActivity;
import com.wan.college.menu.ResideMenu;
import com.wan.college.menu.ResideMenuInfo;
import com.wan.college.menu.ResideMenuItem;
import com.wan.college.activity.UserMessageActivity;
import com.wan.college.network.InitConfig;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;


public class MainFragmentPagerActivity extends FragmentActivity implements
		View.OnClickListener {

	private ResideMenu resideMenu;
	private ResideMenuItem itemHuiyuan;
	private ResideMenuItem itemQianbao;
	private ResideMenuItem itemZhuangban;
	private ResideMenuItem itemShoucang;
	private ResideMenuItem itemXiangce;
	private ResideMenuItem itemFile;
	private ResideMenuInfo info;

	private boolean is_closed = false;
	private long mExitTime;

	ViewPager viewpager;
	Fragment1 f1=null;
	Fragment2 f2=null;
	Fragment3 f3=null;
	Fragment4 f4=null;
	TextView top_bar_title;
	ImageView top_bar_search;
	Button[] btnArray;
	TextView[] TextViewArray;
	int[] BackgroundResource_unselected;
	int[] BackgroundResource_selected;
	MainFragmentPagerAdapter adapter;
	ArrayList<Fragment> fragmentdatas;
	private int currentPageIndex = 0;
	ImageButton leftMenu;
	String uname;
	String upwd;
	Boolean loginState=false;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.mainfragmentpager);
		//fragment适配器设置
		viewpager = (ViewPager)this.findViewById(R.id.viewPager);

		/////////////////////////////////////////////////////////////////////////////////

	/*	LayoutInflater flater = LayoutInflater.from(this);
		View v = flater.inflate(R.layout.top_bar, null);*/
		////////////////////
		//初始设置
		initWindow();
		setupView();
		setUpMenu();
		setListener();
		setButtonColor();
		loginUser();
		//////////////////////

	}
	//沉浸式布局
	private void initWindow() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
			getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
		}
	}

/*点击底部导航按钮，选中变色判断*/
	private void setButtonColor() {
		for (int i = 0; i < btnArray.length; i++) {
			if (i == currentPageIndex) {
				btnArray[i].setBackgroundResource(BackgroundResource_selected[i]);
				TextViewArray[i].setTextColor(Color.parseColor("#489cfa"));
			} else {
				btnArray[i].setBackgroundResource(BackgroundResource_unselected[i]);
				TextViewArray[i].setTextColor(Color.parseColor("#82858b"));
			}
		}
	}

	private void setListener() {

		resideMenu.addMenuInfo(info);
		itemHuiyuan.setOnClickListener(this);
		itemQianbao.setOnClickListener(this);
		itemZhuangban.setOnClickListener(this);
		itemShoucang.setOnClickListener(this);
		itemXiangce.setOnClickListener(this);
		itemFile.setOnClickListener(this);


		info.setOnClickListener(this);

		leftMenu.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
			}
		});
		//搜索监听
		top_bar_search.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				Intent intent=new Intent();
				intent.setClass(MainFragmentPagerActivity.this,SwiperRefresh.class);
				startActivity(intent);
				overridePendingTransition(R.anim.push_left_in,
						R.anim.push_left_out);
				return false;
			}
		});


		//禁止页面滑动，进行拦截
		viewpager.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				return true;
			}
		});
		//这是页面可滑动时，滑动与按钮的状态改变
		viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			@Override
			public void onPageScrollStateChanged(int index) {
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageSelected(int pageIndex) {
				currentPageIndex = pageIndex;
				setButtonColor();
			}
		});

		for (Button btn : btnArray) {
			btn.setOnClickListener(this);
		}
	}

	private void setupView() {
		//viewpager加载页面
		fragmentdatas = new ArrayList<>();
		fragmentdatas.add(f1 = new Fragment1());
		fragmentdatas.add(f2 = new Fragment2());
		fragmentdatas.add(f3 = new Fragment3());
		fragmentdatas.add(f4 = new Fragment4());
		adapter = new MainFragmentPagerAdapter(getSupportFragmentManager(), fragmentdatas);
		adapter.notifyDataSetChanged();
		viewpager.setAdapter(adapter);
		//滑动页面默认加载第一页
		viewpager.setCurrentItem(currentPageIndex,false);//false为不需要切换过渡时间

		//按钮UI
		btnArray=new Button[]{
				(Button)findViewById(R.id.btnFriendList),
				(Button)findViewById(R.id.btnGroupChat),
				(Button)findViewById(R.id.btnTopicList)
		};

		BackgroundResource_unselected=new int[]{R.drawable.icon_home_nor,R.drawable.icon_find_nor,R.drawable.icon_help_nor};
		BackgroundResource_selected=new int[]{R.drawable.icon_home_pre,R.drawable.icon_find_pre,R.drawable.icon_help_pre};

		TextViewArray=new TextView[]{
				(TextView)findViewById(R.id.message_text),
				(TextView)findViewById(R.id.contacts_text),
				(TextView)findViewById(R.id.helps_text)
		};
		//actionbar
		top_bar_title=(TextView)findViewById(R.id.top_bar_title);
		top_bar_search=(ImageView)findViewById(R.id.top_bar_search);
	}


	@SuppressWarnings("deprecation")
	private void setUpMenu() {

		leftMenu=(ImageButton)findViewById(R.id.leftMenu);
		// attach to current activity;
		resideMenu = new ResideMenu(this);
		resideMenu.setBackground(R.drawable.menu_background);
		resideMenu.attachToActivity(this);
		resideMenu.setMenuListener(menuListener);

		// valid scale factor is between 0.0f and 1.0f. leftmenu'width is
		// 150dip.
		resideMenu.setScaleValue(0.55f);
		// 禁止使用右侧菜单
		resideMenu.setDirectionDisable(ResideMenu.DIRECTION_RIGHT);

		// create menu items;
		itemHuiyuan = new ResideMenuItem(this, R.drawable.lzf, "开通会员");
		itemQianbao = new ResideMenuItem(this, R.drawable.lze, "我的钱包");
		itemZhuangban = new ResideMenuItem(this, R.drawable.lzc, "个性装扮");
		itemShoucang = new ResideMenuItem(this, R.drawable.lzg, "我的收藏");
		itemXiangce = new ResideMenuItem(this, R.drawable.lzd, "我的相册");
		itemFile = new ResideMenuItem(this, R.drawable.ktn, "我的文件");

		resideMenu.addMenuItem(itemHuiyuan, ResideMenu.DIRECTION_LEFT);
		resideMenu.addMenuItem(itemQianbao, ResideMenu.DIRECTION_LEFT);
		resideMenu.addMenuItem(itemZhuangban, ResideMenu.DIRECTION_LEFT);
		resideMenu.addMenuItem(itemShoucang, ResideMenu.DIRECTION_LEFT);
		resideMenu.addMenuItem(itemXiangce, ResideMenu.DIRECTION_LEFT);
		resideMenu.addMenuItem(itemFile, ResideMenu.DIRECTION_LEFT);

		info = new ResideMenuInfo(this, R.drawable.head_default, "开心鬼",
				"32 级");

	}


	@Override
	public void onClick(View v) {
		try {
			if(v.getId()>-1){
				top_bar_search.setVisibility(View.GONE);
				switch (v.getId()) {
					case R.id.btnFriendList:
						currentPageIndex = 0;
						top_bar_title.setText("资讯");
						break;
					case R.id.btnGroupChat:
						currentPageIndex = 1;
						top_bar_title.setText("发现");
						top_bar_search.setVisibility(View.VISIBLE);
						break;
					case R.id.btnTopicList:
						currentPageIndex = 2;
						top_bar_title.setText("辅助");
						break;
				}
				viewpager.setCurrentItem(currentPageIndex,false);
			}else{
			//判断侧拉点击事件
			Intent intent = new Intent();
			if (v == itemHuiyuan) {
				intent.putExtra("flog", "开通会员界面");
				intent.setClass(getApplicationContext(), SettingActivity.class);
			} else if (v == itemQianbao) {
				intent.putExtra("flog", "QQ钱包界面");
				intent.setClass(getApplicationContext(), SettingActivity.class);
			} else if (v == itemZhuangban) {
				intent.putExtra("flog", "个性装扮界面");
				intent.setClass(getApplicationContext(), SettingActivity.class);
			} else if (v == itemShoucang) {
				intent.putExtra("flog", "我的收藏界面");
				intent.setClass(getApplicationContext(), SettingActivity.class);
			} else if (v == itemXiangce) {
				intent.putExtra("flog", "我的相册界面");
				intent.setClass(getApplicationContext(), SettingActivity.class);
			} else if (v == itemFile) {
				intent.putExtra("flog", "我的文件界面");
				intent.setClass(getApplicationContext(), SettingActivity.class);
			} else if (v == info) {
				intent.putExtra("flog", "我的个人信息界面");
				intent.setClass(getApplicationContext(), UserMessageActivity.class);
			}
			if(intent.getClass()!=null){
				if(!loginState){
					intent.setClass(getApplicationContext(), LoginActivity.class);
				}
				startActivityForResult(intent, 0);
				//startActivity(intent);
				//getStringExtra("flog")==null
			}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private ResideMenu.OnMenuListener menuListener = new ResideMenu.OnMenuListener() {
		@Override
		public void openMenu() {
			is_closed = false;
			leftMenu.setVisibility(View.GONE);
		}
		@Override
		public void closeMenu() {
			is_closed = true;
			leftMenu.setVisibility(View.VISIBLE);
		}
	};

	// What good method is to access resideMenu？
	public ResideMenu getResideMenu() {
		return resideMenu;
	}

	private void loginUser(){
		uname= SharedTool.getUname(this);
		upwd=SharedTool.getUpwd(this);
		if (uname==null||upwd==null){
			info.setTitle("未登录");
			info.setDengJi("无");
			loginState=false;
		}else{
			login_validate(uname,upwd);
		}
	}
	private void login_validate(String uname,String upwd){
		String url = InitConfig.baseUrl + "androidlogin";
		RequestParams rp = new RequestParams(url);
		rp.addBodyParameter("uname", uname);
		rp.addBodyParameter("upwd", upwd);
		x.http().post(rp, new Callback.CommonCallback<String>() {
			@Override
			public void onSuccess(String result) {
				Toast.makeText(MainFragmentPagerActivity.this,result,Toast.LENGTH_LONG).show();

				JSONObject res = JSON.parseObject(result);
				if(res.getBoolean("login")){
					loginState=true;
					hander.sendEmptyMessage(0);
				}
			}
			@Override
			public void onError(Throwable ex, boolean isOnCallback) {
				Toast.makeText(MainFragmentPagerActivity.this,"请求失败",Toast.LENGTH_LONG).show();
				info.setTitle("未登录");
				info.setDengJi("无");
				loginState=false;//测试
			}
			@Override
			public void onCancelled(CancelledException cex) {
			}
			@Override
			public void onFinished() {
			}
		});
	}
	//接收登录页面返回验证信息
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(data!=null){
		loginState=data.getBooleanExtra("login",false);
		if(loginState){
			hander.sendEmptyMessage(0);
		}else{
			info.setTitle("未登录");
			info.setDengJi("无");
		}
		}
	}

	private Handler hander = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			switch(msg.what){
				case 0:
					getUserMsg();
					break;
				default:
					//do something
					break;
			}
		}
	};

	private void getUserMsg(){
		info.setTitle("万文杰");
		info.setDengJi("100级");
	}


	// 监听手机上的BACK键
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			// 判断菜单是否关闭
			if (is_closed) {
				// 判断两次点击的时间间隔（默认设置为2秒）
				if ((System.currentTimeMillis() - mExitTime) > 3000) {
					Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
					mExitTime = System.currentTimeMillis();
				} else {
					mExitTime=0;
					finish();
					System.exit(0);
				}
			} else {
				resideMenu.closeMenu();
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}




