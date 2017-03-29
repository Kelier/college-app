package com.wan.college.fragment;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.CardView;
import android.text.StaticLayout;
import android.text.format.Time;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.wan.college.R;
import com.wan.college.activity.my.InterestedCollegeActivity;
import com.wan.college.activity.my.PersonFeedActivity;
import com.wan.college.activity.my.PersonInfoActivity;
import com.wan.college.activity.my.PersonProActivity;
import com.wan.college.login.LoginActivity;
import com.wan.college.model.TestModel;
import com.wan.college.model.User;
import com.wan.college.model.UserMsgData;
import com.wan.college.model.UserMsgsData;
import com.wan.college.network.CallbBackString;
import com.wan.college.network.HttpServer;
import com.wan.college.network.INetResult;
import com.wan.college.network.InitConfig;
import com.wan.college.network.UserMsg;
import com.wan.college.ui.CustomToast;
import com.wan.college.ui.RoundImageView;
import com.wan.college.utils.SQLiteDBUtils;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;


public class FragmentMy extends Fragment implements View.OnClickListener,INetResult<String> {

    InitConfig application;
    RoundImageView my_avatar;
    ImageView imageview;
    ImageView image_gender;
    TextView my_nickname;
    TextView my_subject;
    TextView my_province;
    TextView my_score;
    TextView day_num;
    LinearLayout item_score;
    LinearLayout item_subject;
    LinearLayout item_province;
    CardView card_ques;
    CardView card_school;
    CardView card_pro;
    CardView card_feed;
    CardView card_setting;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_footer_fragment_my, container, false);

        initView(view);
        initListener();
        initReceiver();
        initData();
        getDate();
        if(UserMsg.uid!=null){
            Log.i("uid",UserMsg.uid);
            HttpServer.getUserMsgs(new CallbBackString(this));
        }
        return view;
    }

    private void getDate() {
        Calendar c = new GregorianCalendar();
        c.set(Calendar.MONTH , 5);
        c.set(Calendar.DAY_OF_MONTH , 7);
        long gkTimes = c.getTimeInMillis() ;
        long nowTimes = System.currentTimeMillis() ;
        if(gkTimes - nowTimes > 0) {
            long diff = c.getTimeInMillis() - nowTimes;
            long days = diff / (1000 * 60 * 60 * 24);
            day_num.setText(String.valueOf(Math.abs(days)));
        } else {
            c.add(Calendar.YEAR , 1);
            long diff = c.getTimeInMillis() - nowTimes;
            long days = diff / (1000 * 60 * 60 * 24);
            day_num.setText(String.valueOf(Math.abs(days)));
        }


//        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//        try
//        {
////            Time t=new Time(); // or Time t=new Time("GMT+8"); 加上Time Zone资料。
////            t.setToNow(); // 取得系统时间。
////            int year = t.year;
////            int month = t.month+1;
////            int day = t.monthDay;
////            Date d1=df.parse(year+"-"+month+"-"+day);
////            Date d2 = df.parse(year+"-"+6+"-"+7);
////            long diff = d1.getTime() - d2.getTime();
////            long days = diff / (1000 * 60 * 60 * 24);
//
////            System.out.println(""+days+"天"+hours+"小时"+minutes+"分");
////            Log.e("年份",String.valueOf(year));
////            Log.e("月份",String.valueOf(month));
////            Log.e("日期",String.valueOf(day));
////            CustomToast.showDone(getActivity(),String.valueOf(Math.abs(days)));
//        }
//        catch (Exception e)
//        {
//            Toast.makeText(getActivity(),"数据请求失败",Toast.LENGTH_LONG).show();
//        }
    }
    public void getdd(){
        HttpServer.getUserMsgs(new CallbBackString(this));
    }
    private void initReceiver() {
        //接收首页（输入分数）广播
        LocalBroadcastManager broadcastManager = LocalBroadcastManager.getInstance(getActivity());
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.CART_BROADCAST");//建议把它写一个公共的变量
        BroadcastReceiver mItemViewListClickReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                int requestCode=intent.getExtras().getInt("requestCode");
                switch(requestCode){
                    case 1: my_score.setText(UserMsg.score);break;
                    case 2:getdd();
                        break;
                }


            }
        };
        broadcastManager.registerReceiver(mItemViewListClickReceiver, intentFilter);
    }

    private void initView(View view) {
        application = (InitConfig) getActivity().getApplication();
        my_nickname = (TextView) view.findViewById(R.id.my_nickname);
        my_subject = (TextView) view.findViewById(R.id.my_subject);
        my_province = (TextView) view.findViewById(R.id.my_province);
        my_score = (TextView) view.findViewById(R.id.my_score);
        day_num= (TextView) view.findViewById(R.id.day_num);
        my_avatar = (RoundImageView) view.findViewById(R.id.my_avatar);
        imageview = (ImageView) view.findViewById(R.id.imageview);
        item_score = (LinearLayout) view.findViewById(R.id.item_score);
        item_subject = (LinearLayout) view.findViewById(R.id.item_subject);
        item_province = (LinearLayout) view.findViewById(R.id.item_province);
        image_gender = (ImageView) view.findViewById(R.id.image_gender);
        /*
        if (application.getUserPhoto() != null) {
            me_icon.setImageBitmap(BitmapFactory.decodeFile(application.getUserPhoto()));
        }
        */
        card_ques = (CardView) view.findViewById(R.id.card_me_ques);
        card_school = (CardView) view.findViewById(R.id.card_me_school);
        card_pro = (CardView) view.findViewById(R.id.card_me_pro);
        card_feed = (CardView) view.findViewById(R.id.card_me_feed);
        card_setting = (CardView) view.findViewById(R.id.card_me_setting);
    }

    private void initListener() {
        my_nickname.setOnClickListener(this);
        imageview.setOnClickListener(this);
        item_score.setOnClickListener(this);
        item_subject.setOnClickListener(this);
        item_province.setOnClickListener(this);
        card_ques.setOnClickListener(this);
        card_school.setOnClickListener(this);
        card_pro.setOnClickListener(this);
        card_feed.setOnClickListener(this);
        card_setting.setOnClickListener(this);
    }

    private void initData() {
        //性别图标刷新
        image_gender.setWillNotDraw(true);
        if(UserMsg.gender ==null){
            image_gender.setImageResource(R.drawable.sex_unknow_icon);
        }else if (UserMsg.gender.equals("1")) {
            image_gender.setImageResource(R.drawable.mypage_sexgril_icon);
        } else if (UserMsg.gender.equals("0")) {
            image_gender.setImageResource(R.drawable.mypage_sexboy_icon);
        }
        if(UserMsg.nickname!=null&&UserMsg.nickname.length()>0)my_nickname.setText(UserMsg.nickname);
        my_province.setText(UserMsg.province.replace("省", "").replace("市", "").replace("自治区", "").replace("维吾尔", "").replace("壮族", "").replace("回族", ""));
        my_score.setText(UserMsg.score);
        if (UserMsg.subject.equals("2")) my_subject.setText("理科");
        else my_subject.setText("文科");
        // 设置加载图片的参数
        ImageOptions options = new ImageOptions.Builder()
                // 是否忽略GIF格式的图片
                .setIgnoreGif(false)
                // 图片缩放模式
                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                // 下载中显示的图片
                .setLoadingDrawableId(R.mipmap.mypage_headbg_img)
                // 下载失败显示的图片
                .setFailureDrawableId(R.mipmap.mypage_headbg_img)
                // 得到ImageOptions对象
                .build();
        if(UserMsg.avatar!=null)x.image().bind(my_avatar, InitConfig.baseUrl+UserMsg.avatar, options);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.card_me_ques:
                startActivityForResult(new Intent(getActivity(), LoginActivity.class),1);
                break;
            case R.id.card_me_school:
                if(UserMsg.uid!=null)startActivity(new Intent(getActivity(), InterestedCollegeActivity.class));
                else {
                    Intent intent =new Intent();
                    intent.setClass(getActivity(), LoginActivity.class);
                    startActivityForResult(intent,0);
                }
                break;
            case R.id.card_me_pro:
                if(UserMsg.uid!=null)startActivity(new Intent(getActivity(), PersonProActivity.class));
                else  {
                    Intent intent =new Intent();
                    intent.setClass(getActivity(), LoginActivity.class);
                    startActivityForResult(intent,0);
                }
                break;
            case R.id.card_me_feed:
                startActivity(new Intent(getActivity(), PersonFeedActivity.class));
                break;
            case R.id.card_me_setting:
                if(UserMsg.uid!=null)startActivityForResult(new Intent(getActivity(), PersonInfoActivity.class),1);
                else {
                    Intent intent =new Intent();
                    intent.setClass(getActivity(), LoginActivity.class);
                    startActivityForResult(intent,0);
                }
                break;
        }
    }
    @Override
    public void getNetData(String data) {
        Log.i("test",data);
        UserMsgsData userMsgsData = new Gson().fromJson(data, UserMsgsData.class);
        if(userMsgsData!=null){
            if(userMsgsData.getNickname()!=null)UserMsg.nickname = userMsgsData.getNickname();
            if(userMsgsData.getScore()!=null)UserMsg.score = userMsgsData.getScore();
            if(userMsgsData.getProvince()!=null)UserMsg.province = userMsgsData.getProvince();
            if(userMsgsData.getSubject()!=null)UserMsg.subject = userMsgsData.getSubject();
            if(userMsgsData.getGender()!=null)UserMsg.gender = userMsgsData.getGender();
            if(userMsgsData.getAvatar()!=null)UserMsg.avatar = userMsgsData.getAvatar();
            initData();
            saveUserInfoBySqlite();
        }
        /*
        UserMsgData userMsgData = new Gson().fromJson(data, UserMsgData.class);
        UserMsg.nickname = userMsgData.getName();
        UserMsg.score = String.valueOf(userMsgData.getScore());
        UserMsg.province = userMsgData.getProvince();
        UserMsg.subject = String.valueOf(userMsgData.getSubject());
        UserMsg.gender = String.valueOf(userMsgData.getSex());
        UserMsg.avatar = userMsgData.getUser_icon().getUrl();
        */

    }

    public void saveUserInfoBySqlite(){
        Log.i("sqlite","我的页面缓存本地数据");
        User user = new User(UserMsg.uid, UserMsg.nickname, UserMsg.score, UserMsg.province, UserMsg.subject, UserMsg.avatar,UserMsg.gender);
        SQLiteDBUtils sqLiteDBUtils = new SQLiteDBUtils(getActivity(), User.class);//实例化对象
        sqLiteDBUtils.openDataBase();//打开数据库
        sqLiteDBUtils.deleteData("uid",UserMsg.uid);//删除之前数据
        sqLiteDBUtils.insertData(user);//增加数据
     //   sqLiteDBUtils.updateData("uid",user);
    //    List<User> us=sqLiteDBUtils.queryData("uid",UserMsg.uid);
        List<User> us=sqLiteDBUtils.queryData("uid",UserMsg.uid);

        if(us!=null&&us.size()>0){
            for (User u:us) {
                Log.i("SQ",u.getScore()+" "+u.getAvatar()+" "+u.getGender()+" "+u.getNickname()+" "+u.getProvince()+" "+u.getUid());
            }
        }
        sqLiteDBUtils.closeDataBase();
        Log.i("sqlite","缓存本地数据完毕");
    }
    // 登录页面回调方法
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {


        // 根据上面发送过去的请求吗来区别
        switch (requestCode) {
            case 0:
                Log.i("test","登录页面回调成功");
                Boolean isLogin = data.getBooleanExtra("login",false);
                if(UserMsg.uid!=null&&isLogin){
                    HttpServer.getUserMsgs(new CallbBackString(this));
                }
                break;
            case 1:
                Log.i("test","个人信息页面回调成功");
                initData();
                saveUserInfoBySqlite();
                break;
            default:
                break;
        }
    }

}




