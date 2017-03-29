package com.wan.college.activity.discovery;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.wan.college.R;
import com.wan.college.Tools.SharedTool;
import com.wan.college.network.INetResult;
import com.wan.college.network.UserMsg;
import com.wan.college.ui.CustomToast;

public class DiscoverySearchActivity extends AppCompatActivity implements INetResult {

    TextView btn_back;
    TextView tv_history_search;
    RelativeLayout space_search;
    EditText edit_search;
    Button btn_clear;
    GridView gv_history;
    TextView selection = null;
    String college_str;
    String[] items_history;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discovery_search);
        initView();
        initListener();
        initData();
    }

    private void initData() {
        //获取SP历史记录数据
        college_str= SharedTool.getCollegeStr(this);
        if(college_str==null||college_str.isEmpty()){
            items_history=("北京大学;复旦大学;北京师范大学;天津工业大学;华东师范大学;上海戏剧学院;西安交通大学;太原理工大学;武汉大学;").split(";");
            gv_history.setAdapter( new MyAdapter(this, items_history));
        }else{
            tv_history_search.setText("历史搜索");
            btn_clear.setVisibility(View.VISIBLE);
            items_history=college_str.split(";");
            gv_history.setAdapter( new MyAdapter(this, items_history));
        }
    }


    private void initView() {
        btn_back= (TextView) findViewById(R.id.btn_cancel);
        space_search= (RelativeLayout) findViewById(R.id.space_search);
        edit_search= (EditText) findViewById(R.id.edit_search);
        btn_clear= (Button) findViewById(R.id.btn_clear);
        gv_history= (GridView) findViewById(R.id.gv_history);
        tv_history_search= (TextView) findViewById(R.id.tv_history_search);

    }
    private void initListener() {
        //返回键监听
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //关闭软键盘
                InputMethodManager imm = (InputMethodManager)DiscoverySearchActivity.this.getSystemService(DiscoverySearchActivity.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(edit_search.getWindowToken(), 0);
                finish();
            }
        });
        //触摸空白搜索框获取EditText焦点
        space_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getEditorFocus();
            }
        });
        //GridView  Item点击返回页面
        gv_history.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                SharedTool.setCollegeStr(DiscoverySearchActivity.this,items_history[i]+";"+college_str);

                UserMsg.college=items_history[i];
                UserMsg.risk=4;
                startActivity(new Intent(DiscoverySearchActivity.this,CollegeListActivity.class));
                DiscoverySearchActivity.this.finish();
            }
        });
        //监听编辑框软键盘完成事件
        edit_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if (actionId == EditorInfo.IME_ACTION_SEND
                        || actionId == EditorInfo.IME_ACTION_DONE
                        || (event != null && KeyEvent.KEYCODE_ENTER == event.getKeyCode() && KeyEvent.ACTION_DOWN == event.getAction())) {
                    InputMethodManager imm = (InputMethodManager)DiscoverySearchActivity.this.getSystemService(DiscoverySearchActivity.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(edit_search.getWindowToken(), 0);
                    String college=edit_search.getText().toString();
                    if(college.trim().length()>0){
                    //将编辑框中String存入SP
                        Boolean isNotExist=true;
                        for (String str:items_history
                             ) {
                            if(str.equals(college)){
                                isNotExist=false;
                                break;
                            }
                        }
                        if(isNotExist){
                            SharedTool.setCollegeStr(DiscoverySearchActivity.this,college+";"+college_str);
                        }
                        UserMsg.college=college;
                        UserMsg.risk=4;
                        startActivity(new Intent(DiscoverySearchActivity.this,CollegeListActivity.class));
                        DiscoverySearchActivity.this.finish();
                    }else{
                        CustomToast.showErrorToast(DiscoverySearchActivity.this,"学校不能为空！");
                    }
                }
                return false;
            }
        });
        //清空搜索历史
        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_clear.setVisibility(View.GONE);
                tv_history_search.setText("热门搜索");
                edit_search.setText("");
                getEditorFocus();
                college_str="";
                SharedTool.setCollegeStr(DiscoverySearchActivity.this,college_str);
                initData();
            }
        });

        //GridView的Item监听
        gv_history.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView arg0, View arg1, int arg2, long arg3) {
//                selection.setText(items[arg2]);
            }

            @Override
            public void onNothingSelected(AdapterView arg0) {
                selection.setText("");
            }

        });
    }

    private void getEditorFocus() {
        edit_search.setFocusableInTouchMode(true);
        edit_search.setFocusable(true);
        edit_search.requestFocus();
        //打开软键盘
        InputMethodManager imm = (InputMethodManager)DiscoverySearchActivity.this.getSystemService(DiscoverySearchActivity.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    @Override
    public void getNetData(Object data) {

    }
    /**
     * 点击空白位置 隐藏软键盘↓
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(null != this.getCurrentFocus()){
            InputMethodManager mInputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            return mInputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
        }
        return super.onTouchEvent(event);
    }



    //编写自定义的adapter，继承ArrayAdapter↓
    private class MyAdapter extends BaseAdapter {
        private Context context;
        private String[] theItems;

        MyAdapter( Context context, String[] items){

            this.context = context;
            theItems = items;
        }

        @Override
        public int getCount() {
            return theItems.length;
        }

        @Override
        public Object getItem(int i) {
            return theItems[i];
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        //重写getView()，对每个单元的内容以及UI格式进行描述↓
        public View   getView  (int position, View  convertView, ViewGroup  parent){
            View view = LayoutInflater.from(context).inflate(R.layout.item_search_college, null);
            TextView label = (TextView)view.findViewById(R.id.text_search_item);
            if(position>=0){
                if(theItems[position].isEmpty()){
                    label.setVisibility(View.GONE);
                }else {
                    label.setText(theItems[position]);
                }
            }
            return view;
        }
    }

}
