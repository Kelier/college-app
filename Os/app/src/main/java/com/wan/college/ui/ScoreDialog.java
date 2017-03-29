package com.wan.college.ui;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.provider.ContactsContract;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.wan.college.R;

/**
 * Created by 万文杰 on 2017/1/9.
 */

public class ScoreDialog extends Dialog implements DialogInterface.OnKeyListener,DialogInterface.OnCancelListener{
    Context mContext;
    EditText score_et;
    ImageView score_confirm;
    InputMethodManager imm;
    private Window window = null;
    private OnDialogButtonClickListener buttonClickListner;
    int myScore;
    public ScoreDialog(Context context) {
        super(context);
    }

    public ScoreDialog(Context context, int themeResId) {
        super(context,themeResId);
    }
    public ScoreDialog(Context context, int themeResId , OnDialogButtonClickListener listener) {
        super(context,themeResId);
        this.mContext = context;
        this.buttonClickListner = listener;
        //TODO codind here
        initView();
        initListener();
        WindowAnimation();

    }

    public void initView(){
        this.setContentView(R.layout.view_dialog_score_edit);
        score_et = (EditText) this.findViewById(R.id.score_input);
        score_confirm= (ImageView) this.findViewById(R.id.view_dialog_score_confirm);
    }
    public void initListener(){
        this.setOnShowListener(new OnShowListener() {
            public void onShow(DialogInterface dialog) {
                imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(score_et, InputMethodManager.SHOW_IMPLICIT);
            }
        });
        onCancel(this);
        score_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(score_et.getText().length()>0){
                    myScore=Integer.parseInt(score_et.getText().toString());
                    buttonClickListner.okButtonClick(myScore);
                    dismiss();
                }else{
                    Toast.makeText(mContext,"请输入成绩",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    //实现回调功能
    public interface OnDialogButtonClickListener {
        public void okButtonClick(int myScore);
       // public void cancelButtonClick();

    }
    /*
    public void setOnButtonClickListener(OnDialogButtonClickListener listener) {
        this.buttonClickListner = listener;
    }
*/
    protected ScoreDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);

    }


    //设置窗口显示
    public void windowDeploy(int x, int y){
     //   window = getWindow(); //得到对话框
    //    window.setWindowAnimations(R.style.mystyle); //设置窗口弹出动画
        //window.setBackgroundDrawableResource(R.color.vifrification); //设置对话框背景为透明
        //window.setGravity(Gravity.BOTTOM);  //此处可以设置dialog显示的位置
     //   WindowManager.LayoutParams wl = window.getAttributes();
        //根据x，y坐标设置窗口需要显示的位置
      //  wl.x = x; //x小于0左移，大于0右移
      //  wl.y = y; //y小于0上移，大于0下移
//        wl.alpha = 0.6f; //设置透明度
//        wl.gravity = Gravity.BOTTOM; //设置重力
       // window.setAttributes(wl);
    }


    public void WindowAnimation(){
/*
         * 将对话框的大小按屏幕大小的百分比设置
         */
        Window window = this.getWindow() ;
        /*        WindowManager m = getActivity().getWindowManager();
                Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
                WindowManager.LayoutParams p = window.getAttributes(); // 获取对话框当前的参数值
           //     p.height = (int) (d.getHeight() * 0.6); // 改变的是dialog框在屏幕中的位置而不是大小
           //     p.width = (int) (d.getWidth() * 0.65); // 宽度设置为屏幕的0.65
                window.setAttributes(p);
*/
        //    window.setGravity(Gravity.BOTTOM);  //此处可以设置dialog显示的位置
        window.setWindowAnimations(R.style.mystyle);  //添加动画
    }


    @Override
    public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
        return false;
    }
/*
    @Override
    public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {

        boolean click=false;
        if(i==KeyEvent.KEYCODE_BACK){
            click=true;
        //    Log.e("keymsg", "back key");
        }
        return click;

    }
*/

    @Override
    public void onCancel(DialogInterface dialogInterface) {


    }

    @Override
    protected void onStop() {

      //  imm.hideSoftInputFromWindow(score_et.getWindowToken(),InputMethodManager.HIDE_IMPLICIT_ONLY);
     //   Toast.makeText(mContext,""+imm.isActive(score_et),Toast.LENGTH_LONG).show();
       imm.toggleSoftInput(0,InputMethodManager.HIDE_NOT_ALWAYS);
     //   imm.hideSoftInputFromInputMethod(score_et.getWindowToken(),InputMethodManager.HIDE_IMPLICIT_ONLY);

           //     imm.hideSoftInputFromWindow(score_et.getWindowToken(),InputMethodManager.HIDE_IMPLICIT_ONLY);

    }
}
