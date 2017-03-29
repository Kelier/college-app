package com.wan.college.activity.my;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.google.gson.Gson;
import com.wan.college.R;
import com.wan.college.model.FollowResultData;
import com.wan.college.model.User;
import com.wan.college.network.CallbBackString;
import com.wan.college.network.Const;
import com.wan.college.network.HttpServer;
import com.wan.college.network.INetResult;
import com.wan.college.network.InitConfig;
import com.wan.college.network.UserMsg;
import com.wan.college.ui.CustomToast;
import com.wan.college.ui.RoundImageView;
import com.wan.college.ui.ScoreDialog;
import com.wan.college.ui.popupwindow.PopupWheelSelector;
import com.wan.college.ui.popupwindow.PopupWindowSelect;

import org.xutils.image.ImageOptions;
import org.xutils.x;


public class PersonInfoActivity extends AppCompatActivity implements View.OnClickListener, INetResult<String> {
    private Activity activity;
    private InitConfig application;
    private RoundImageView userIcon;
    RelativeLayout item_camera;
    RelativeLayout item_nickname;
    RelativeLayout item_gender;
    RelativeLayout item_score;
    RelativeLayout item_subject;
    RelativeLayout item_province;
    LinearLayout back_btn;
    int post_state;
    //版本比较：是否是4.4及以上版本
    final boolean mIsKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
    // 启动Activity请求码
    private static final int CODE_CAMERA_REQUEST = 1;
    private static final int CODE_GALLERY_REQUEST = 0;
    private static final int CODE_RESULT_REQUEST = 2;
    // 权限请求码
    private static final int PERMISSION_REQUEST_CAMERA = 0x100;
    private static final int PERMISSION_REQUEST_GALLERY = 0x101;

    private TextView my_gender;
    private TextView my_subject;
    private TextView my_score;
    private TextView my_nickname;
    private TextView my_province;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_info);
        initView();
        initListener();
        initData();
    }

    private void initView() {
        activity = this;
        back_btn = (LinearLayout) findViewById(R.id.back_btn);
        my_gender = (TextView) findViewById(R.id.my_gender);
        my_subject = (TextView) findViewById(R.id.my_subject);
        my_score = (TextView) findViewById(R.id.my_score);
        my_nickname = (TextView) findViewById(R.id.my_nickname);
        my_province= (TextView) findViewById(R.id.my_province);
        application = (InitConfig) getApplication();
        userIcon = (RoundImageView) findViewById(R.id.my_icon);
        item_camera = (RelativeLayout) findViewById(R.id.item_camera);
        item_nickname = (RelativeLayout) findViewById(R.id.item_nickname);
        item_gender = (RelativeLayout) findViewById(R.id.item_gender);
        item_score= (RelativeLayout) findViewById(R.id.item_score);
        item_subject= (RelativeLayout) findViewById(R.id.item_subject);
        item_province= (RelativeLayout) findViewById(R.id.item_province);
    }

    private void initListener() {
        back_btn.setOnClickListener(this);
        item_camera.setOnClickListener(this);
        item_nickname.setOnClickListener(this);
        item_gender.setOnClickListener(this);
        item_score.setOnClickListener(this);
        item_subject.setOnClickListener(this);
        item_province.setOnClickListener(this);
    }

    private void initData(){
        if(UserMsg.gender==null){
            my_gender.setText("未知");
        }else if(UserMsg.gender.equals("0")){
            my_gender.setText("男");
        }else if(UserMsg.gender.equals("1")){
            my_gender.setText("女");
        }
        if(UserMsg.subject!=null&&UserMsg.subject.equals("2")){
            my_subject.setText("理科");
        }else{
            my_subject.setText("文科");
        }
        my_score.setText(UserMsg.score);
        my_nickname.setText(UserMsg.nickname);
        my_province.setText(UserMsg.province);
        // 判断是否存在用户设置过的头像文件
        if (new File(application.getUserPhoto()).exists()) {
            userIcon.setImageBitmap(BitmapFactory.decodeFile(application.getUserPhoto()));
        } else {
            // 本地没有头像
            // 如果当前用户已经登录，需要到服务器上下载头像，显示
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
            x.image().bind(userIcon,UserMsg.avatar,options);
        }
    }
    @Override
    public void getNetData(String data) {
        FollowResultData followResultData=new Gson().fromJson(data,FollowResultData.class);
        if(followResultData!=null&&followResultData.getStatus()==200){
            UserMsg.nickname=my_nickname.getText().toString();
            UserMsg.subject=my_subject.getText().toString();
            UserMsg.province=my_province.getText().toString();
            UserMsg.score=my_score.getText().toString();
            if(my_gender.getText().equals("男"))
            {
                UserMsg.gender=String.valueOf(0);
            }else
            {
                UserMsg.gender=String.valueOf(1);
            }
            if(my_subject.getText().equals("理科"))
            {
                UserMsg.subject=String.valueOf(2);
            }else{
                UserMsg.subject=String.valueOf(1);
            }
            initData();
            CustomToast.showDone(PersonInfoActivity.this,"设置成功");
        }else{
            CustomToast.showDone(PersonInfoActivity.this,"上传失败");
        }
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_btn:
                setResult(RESULT_OK, new Intent());
                finish();
                break;
            case R.id.item_province:
                new PopupWheelSelector(this, Const.getProvinceData(this), UserMsg.province,new PopupWheelSelector.PopOnClickListener() {
                    @Override
                    public void getPosition(int position, String province) {
//                        Toast.makeText(PersonInfoActivity.this,"position"+position+province,Toast.LENGTH_SHORT).show();
                        my_province.setText(province);
                     //   HttpServer.postPersonInfoProvince(new CallbBackString(PersonInfoActivity.this),province);
                        HttpServer.updateUserInfo(new CallbBackString(PersonInfoActivity.this),"province",province);
                    }
                }).showAtLocation(findViewById(R.id.person_info),
                        Gravity.BOTTOM, 0, 0);
                /*
                final  String[] sdata={"必读", "政策", "专业", "院校"};
                new PopupListSelect(PersonInfoActivity.this, sdata, new PopupListSelect.PopOnClickListener() {
                    @Override
                    public void getPosition(int position) {
                        Toast.makeText(PersonInfoActivity.this, sdata[position], Toast.LENGTH_LONG).show();
                    }
                }, new PopupListSelect.PopOnDismissListener() {
                    @Override
                    public void onDismiss() {

                    }
                }).showAtLocation(findViewById(R.id.person_info),
                        Gravity.BOTTOM, 0, 0);*/
                break;
            case R.id.item_subject:
                showkebiepopWinView();
                break;
            case R.id.item_score:
                new ScoreDialog(PersonInfoActivity.this, R.style.dialog, new ScoreDialog.OnDialogButtonClickListener() {
                    @Override
                    public void okButtonClick(int myScore) {
                        if(myScore>100&&myScore<=750) {
                            my_score.setText(String.valueOf(myScore));
                          //  HttpServer.postPersonInfoScore(new CallbBackString(PersonInfoActivity.this), String.valueOf(myScore));
                            HttpServer.updateUserInfo(new CallbBackString(PersonInfoActivity.this),"score",String.valueOf(myScore));
                            broadCast(String.valueOf(myScore));
                        }else{
                            CustomToast.showDone(PersonInfoActivity.this,"请输入正确的分数");
                        }
                    }
                }).show();
                break;
            case R.id.item_camera:
                showPopupWindowView();
                break;
            case R.id.item_nickname:
                inputTitleDialog();
                break;
            case R.id.item_gender:
                showsexpopWinView();
                break;
        }
    }
    // 监听手机上的BACK键
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Log.i("test","个人信息页面返回");
            setResult(RESULT_OK, new Intent());
            this.finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    private void broadCast(String myScore) {
        //发送广播给主页分数
        Intent intent = new Intent("android.intent.action.CART_BROADCAST1");
        LocalBroadcastManager.getInstance(PersonInfoActivity.this).sendBroadcast(intent);
        intent.putExtra("score",String.valueOf(myScore));
        PersonInfoActivity.this.sendBroadcast(intent);
        //Toast.makeText(PersonInfoActivity.this, "发送广播成功", Toast.LENGTH_SHORT).show();
    }

    private void inputTitleDialog() {
        final EditText inputServer = new EditText(this);
        inputServer.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        inputServer.setFocusable(true);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("您的姓名").setIcon(
                R.mipmap.mypage_concerned_ques_icon).setView(inputServer).setNegativeButton(
                "取消", null);
        inputServer.setText(UserMsg.nickname);
        builder.setPositiveButton("保存",
                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        String inputName = inputServer.getText().toString();
                        my_nickname.setText(inputName);
                     //   HttpServer.postPersonInfoName(new CallbBackString(PersonInfoActivity.this),inputName);
                        HttpServer.updateUserInfo(new CallbBackString(PersonInfoActivity.this),"nickname",inputName);
                    }
                });
        builder.show();
    }

    /**
     * pop弹出框设置
     */
    private void showsexpopWinView() {

         new PopupWindowSelect(PersonInfoActivity.this, R.layout.pop_choose_gender, R.id.radio_gender, new PopupWindowSelect.PopOnClickListener() {
            @Override
            public void getId(int id) {
                switch (id) {
                    case R.id.man:
                        my_gender.setText("男");
                    //    HttpServer.postPersonInfoGender(new CallbBackString(PersonInfoActivity.this),String.valueOf(0));
                        HttpServer.updateUserInfo(new CallbBackString(PersonInfoActivity.this),"gender","0");
                        break;
                    case R.id.woman:
                        my_gender.setText("女");
                    //    HttpServer.postPersonInfoGender(new CallbBackString(PersonInfoActivity.this),String.valueOf(1));
                        HttpServer.updateUserInfo(new CallbBackString(PersonInfoActivity.this),"gender","1");
                        break;
                    default:
                        break;
                }
            }
        }, new PopupWindowSelect.PopOnDismissListener() {
            @Override
            public void onDismiss() {
                //写销毁后的操作
            }
        }).showAtLocation(findViewById(R.id.person_info),
                Gravity.BOTTOM, 0, 0);
    }

    private void showkebiepopWinView() {
        new PopupWindowSelect(PersonInfoActivity.this, R.layout.pop_choose_subject, R.id.radio_subject, new PopupWindowSelect.PopOnClickListener() {
            @Override
            public void getId(int id) {
                switch (id) {
                    case R.id.subject_like:
                        my_subject.setText("理科");
//                        Toast.makeText(PersonInfoActivity.this,"理科",Toast.LENGTH_LONG).show();
                    //    HttpServer.postPersonInfoSubject(new CallbBackString(PersonInfoActivity.this),String.valueOf(2));
                        HttpServer.updateUserInfo(new CallbBackString(PersonInfoActivity.this),"subject","2");
                        break;
                    case R.id.subject_wenke:
                        my_subject.setText("文科");
//                        Toast.makeText(PersonInfoActivity.this,"文科",Toast.LENGTH_LONG).show();
                    //    HttpServer.postPersonInfoSubject(new CallbBackString(PersonInfoActivity.this),String.valueOf(1));
                        HttpServer.updateUserInfo(new CallbBackString(PersonInfoActivity.this),"subject","1");
                        break;
                    default:
                        break;
                }
            }


        }, new PopupWindowSelect.PopOnDismissListener() {
            @Override
            public void onDismiss() {

            }
        }).showAtLocation(findViewById(R.id.person_info),
                Gravity.BOTTOM, 0, 0);
    }


    /**
     * 初始化并显示头像选择弹窗
     */
    public void showPopupWindowView() {

        new PopupWindowSelect(PersonInfoActivity.this, R.layout.pop_choose_photo, R.id.radio_photo, new PopupWindowSelect.PopOnClickListener() {
            @Override
            public void getId(int id) {
                switch (id) {
                    case R.id.popup_btn_camera:
                        try {
                            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                                choseHeadImageFromCameraCapture();
                            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                String CAMERA_PERMISSION = Manifest.permission.CAMERA;
                                if (ContextCompat.checkSelfPermission(activity, CAMERA_PERMISSION) == PackageManager.PERMISSION_GRANTED) {
                                    //授予权限了
                                    choseHeadImageFromCameraCapture();
                                } else {
                                    if (ActivityCompat.shouldShowRequestPermissionRationale(activity, CAMERA_PERMISSION)) {
                                        //还没有完全禁止
                                        ActivityCompat.requestPermissions(activity, new String[]{CAMERA_PERMISSION}, PERMISSION_REQUEST_CAMERA);
                                    } else {
                                        //完全禁止了
                                        showPermissionDialog();
                                    }
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case R.id.popup_btn_gallery:
                        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                            choseHeadImageFromGallery();
                        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            String EXTERNAL_PERMISSION = Manifest.permission.WRITE_EXTERNAL_STORAGE;
                            if (ContextCompat.checkSelfPermission(activity, EXTERNAL_PERMISSION) == PackageManager.PERMISSION_GRANTED) {
                                //授予权限了
                                choseHeadImageFromGallery();
                            } else {
                                if (ActivityCompat.shouldShowRequestPermissionRationale(activity, EXTERNAL_PERMISSION)) {
                                    //还没有完全禁止
                                    ActivityCompat.requestPermissions(activity, new String[]{EXTERNAL_PERMISSION}, PERMISSION_REQUEST_GALLERY);
                                } else {
                                    //完全禁止了
                                    showPermissionDialog();
                                }
                            }
                        }
                        break;
                    case R.id.popup_btn_cancel:
                       // Toast.makeText(PersonInfoActivity.this,"xiangce",Toast.LENGTH_LONG).show();
                        break;
                    default:
                        break;
                }
            }
        }, new PopupWindowSelect.PopOnDismissListener() {
            @Override
            public void onDismiss() {

            }
        }).showAtLocation(findViewById(R.id.person_info),
                Gravity.BOTTOM, 0, 0);
    }

    /**
     * 如果使用了startActivityForResult，则被启动的Activity回传数据后自动回掉onActivityResult方法
     *
     * @param requestCode
     * @param resultCode
     * @param intent
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        // 用户没有进行有效的设置操作，返回
        if (resultCode == Activity.RESULT_CANCELED) {
            return;
        }
        switch (requestCode) {
            case CODE_GALLERY_REQUEST:
                //选择相册返回值
                cropRawPhoto(intent.getData());
                break;

            case CODE_CAMERA_REQUEST:
                if (hasSdcard()) {
//                    File tempFile = new File(application.getTempPhoto());
//                    if(!tempFile.exists())
//                        //创建文件
//                        try {
//                            tempFile.createNewFile();
//                        } catch (IOException e) {
//                            Toast.makeText(getApplication(), "图片保存失败!", Toast.LENGTH_LONG).show();
//                        }
//                    cropRawPhoto(Uri.fromFile(tempFile));
                    cropRawPhoto(Uri.fromFile(new File(application.getTempPhoto())));
                } else {
                    Toast.makeText(getApplication(), "没有SDCard!", Toast.LENGTH_LONG).show();
                }
                break;

            case CODE_RESULT_REQUEST:
                if (intent != null) {
                    //将Uri图片转换为Bitmap
                    try {
                        Bitmap bitmap2 = BitmapFactory.decodeStream(getContentResolver().openInputStream(uritempFile));
                        setImageToHeadView(bitmap2);
                    } catch (FileNotFoundException e) {
                        Toast.makeText(getApplication(), "图片剪切失败!", Toast.LENGTH_LONG)
                                .show();
                        e.printStackTrace();
                    }
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, intent);
    }

    //响应权限请求事件的返回
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSION_REQUEST_CAMERA:   // 拍照权限的请求码
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    choseHeadImageFromCameraCapture();
                } else {
                    showPermissionDialog();
                }
                break;
            case PERMISSION_REQUEST_GALLERY:   // 使用相册权限的请求码
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    choseHeadImageFromGallery();
                } else {
                    showPermissionDialog();
                }
                break;

        }
    }

    // 完全禁止时弹框
    private static final String PACKAGE_URL_SCHEME = "package:";

    private void showPermissionDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        builder.setTitle("帮助");
        builder.setMessage("当前应用缺少必要权限。请点击\"设置\"-打开所需权限。");
        // 拒绝, 退出应用
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                setResult(PERMISSIONS_DENIED);

            }
        });
        builder.setPositiveButton("设置", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                intent.setData(Uri.parse(PACKAGE_URL_SCHEME + getPackageName()));
                startActivity(intent);
            }
        });
        builder.setCancelable(false);
        builder.show();
    }

    // 从本地相册选取图片作为头像
    private void choseHeadImageFromGallery() {
        Intent intentFromGallery = new Intent();
        // 设置文件类型
        intentFromGallery.setType("image/*");
        intentFromGallery.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intentFromGallery, CODE_GALLERY_REQUEST);
    }

    // 启动手机相机拍摄照片作为头像
    private void choseHeadImageFromCameraCapture() {
        Intent intentFromCapture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        // 判断存储卡是否可用，存储照片文件

        intentFromCapture.putExtra(MediaStore.EXTRA_OUTPUT, Uri
                .fromFile(new File(application.getTempPhoto())));


        startActivityForResult(intentFromCapture, CODE_CAMERA_REQUEST);
    }

    /**
     * 裁剪原始的图片
     */
    Uri uritempFile;

    public void cropRawPhoto(Uri uri) {

        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");

        // 设置裁剪
        intent.putExtra("crop", "true");

        // aspectX , aspectY :宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);

        /*// outputX , outputY : 裁剪图片宽高
        intent.putExtra("outputX", output_X);
        intent.putExtra("outputY", output_Y);
        intent.putExtra("return-data", true);*/
        /**
         * 此方法返回的图片只能是小图片（sumsang测试为高宽160px的图片）
         * 故将图片保存在Uri中，调用时将Uri转换为Bitmap，此方法还可解决miui系统不能return data的问题
         */
        //intent.putExtra("return-data", true);

        //uritempFile为Uri类变量，实例化uritempFile
        uritempFile = Uri.fromFile(new File(application.getTempPhoto()));
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uritempFile);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        startActivityForResult(intent, CODE_RESULT_REQUEST);
    }

    /**
     * 提取保存裁剪之后的图片数据，并设置头像部分的View
     */
    private void setImageToHeadView(Bitmap bitmap) {
        if (bitmap != null) {
            userIcon.setImageBitmap(bitmap);

            try {
                saveFile(bitmap, application.getUserPhoto());
            } catch (IOException e) {
                e.printStackTrace();
            }
            //////////////////////
//            参数bitmap使用设置的头像
//                    将Bitmap转换为File或者OutputStream
//                    上传到服务器
            //////////////////////

        }
    }

    /**
     * 将Bitmap转换成文件
     * 保存文件
     *
     * @param bm
     * @param fileName
     * @throws IOException
     */
    public static File saveFile(Bitmap bm, String path, String fileName) throws IOException {
        File dirFile = new File(path);
        if (!dirFile.exists()) {
            dirFile.mkdir();
        }
        File myCaptureFile = new File(path, fileName);
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(myCaptureFile));
        bm.compress(Bitmap.CompressFormat.JPEG, 80, bos);
        bos.flush();
        bos.close();
        return myCaptureFile;
    }

    /**
     * 将Bitmap转换成文件
     * 保存文件
     *
     * @param bm
     * @param fileName
     * @throws IOException
     */
    public static File saveFile(Bitmap bm, String fileName) throws IOException {
        File myCaptureFile = new File(fileName);
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(myCaptureFile));
        bm.compress(Bitmap.CompressFormat.JPEG, 80, bos);
        bos.flush();
        bos.close();
        return myCaptureFile;
    }

    /**
     * 检查设备是否存在SDCard的工具方法
     */
    public static boolean hasSdcard() {
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            // 有存储的SDCard
            return true;
        } else {
            return false;
        }
    }
}
