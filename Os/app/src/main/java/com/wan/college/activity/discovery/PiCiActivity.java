package com.wan.college.activity.discovery;

import android.content.Context;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.wan.college.R;
import com.wan.college.model.Pici;
import com.wan.college.model.PiciData;
import com.wan.college.network.CallbBackString;
import com.wan.college.network.HttpServer;
import com.wan.college.network.INetResult;
import com.wan.college.network.UserMsg;
import com.wan.college.ui.CustomToast;
import com.wan.college.ui.MajorListView;
import com.wan.college.utils.JsonUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class PiCiActivity extends AppCompatActivity implements INetResult<String> {
    MajorListView listView;
    MajorListView listView2;
    ListView listview_college_province;
    FrameLayout view_province_container;
    List<Pici> data;
    List<Pici> data2;
    List<String> province_data;
    MyBaseAdapter mybaseadapter;
    MyBaseAdapter mybaseadapter2;
    ProvinceAdapter provinceadapter;
    StringBuilder stringBuilder;
    LinearLayout select_province;
    TextView province;
    ImageView nav_screen_arrow;
    LinearLayout btn_back;
    RelativeLayout loading;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pi_ci);
        initView();
        initData();
        addLinstener();
    }

    private void addLinstener() {
        select_province.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                visibilityChange();
            }
        });
        view_province_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                visibilityChange();
            }
        });

        //返回键监听
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        //省份列表监听
        listview_college_province.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                province.setText(province_data.get(i));
                HttpServer.getPici(new CallbBackString(PiCiActivity.this),province_data.get(i));
                visibilityChange();
                loading.setVisibility(View.VISIBLE);
            }
        });
    }

    private void initData() {
        stringBuilder = JsonUtils.getJson(this, "area.json");
        JsonObject returnData = new JsonParser().parse(stringBuilder.toString()).getAsJsonObject();
        JsonArray jsonArray = returnData.get("province").getAsJsonArray();
        for (int i = 0; i < jsonArray.size(); i++) {
            province_data.add(jsonArray.get(i).getAsString());
        }
        //   Toast.makeText(PiCiActivity.this,jsonArray.get(0).toString(),Toast.LENGTH_LONG).show();
        /*=======第一次加载本地用户地区========*/
        province.setText(UserMsg.province);
        HttpServer.getPici(new CallbBackString(this), UserMsg.province);
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void initView() {
        listView = (MajorListView) findViewById(R.id.fragment_college_sciencebatchfragment_sciencelist);
        listView2 = (MajorListView) findViewById(R.id.fragment_college_sciencebatchfragment_artslist);
        listview_college_province = (ListView) findViewById(R.id.listview_college_province);
        btn_back = (LinearLayout) findViewById(R.id.back_btn);
        view_province_container = (FrameLayout) findViewById(R.id.view_province_container);
        select_province = (LinearLayout) findViewById(R.id.select_province);
        nav_screen_arrow = (ImageView) findViewById(R.id.nav_screen_arrow);
        province = (TextView) findViewById(R.id.province);
        loading = (RelativeLayout) findViewById(R.id.loading);
        province_data = new ArrayList<>();
    }

    public void visibilityChange(){
    if (view_province_container.getVisibility() == View.GONE) {
        view_province_container.setVisibility(View.VISIBLE);
        nav_screen_arrow.setBackground(getResources().getDrawable(R.drawable.nav_screening_up_normal));
    } else {
        view_province_container.setVisibility(View.GONE);
        nav_screen_arrow.setBackground(getResources().getDrawable(R.drawable.nav_screening_down_normal));
    }
}


    @Override
    public void getNetData(String rdata) {
        data = new ArrayList<>();
        data2 = new ArrayList<>();
        String year;
        String first;
        String second;
        String third;
        PiciData piciData = new Gson().fromJson(rdata, PiciData.class);
        for (int i = 0; i < piciData.getLi_pici().size(); i++) {
            PiciData.LiPiciBean liPiciBean = piciData.getLi_pici().get(i);
            year = String.valueOf(liPiciBean.getYear());
            if ((first = liPiciBean.getLines().get(0).toString()).equals("-1")) first = "—";
            if ((second = liPiciBean.getLines().get(1).toString()).equals("-1")) second = "—";
            if ((third = liPiciBean.getLines().get(2).toString()).equals("-1")) third = "—";
            data.add(new Pici(year, first, second, third));
            PiciData.WenPiciBean wenPiciBean = piciData.getWen_pici().get(i);
            year = String.valueOf(wenPiciBean.getYear());
            if ((first = wenPiciBean.getLines().get(0).toString()).equals("-1")) first = "—";
            if ((second = wenPiciBean.getLines().get(1).toString()).equals("-1")) second = "—";
            if ((third = wenPiciBean.getLines().get(2).toString()).equals("-1")) third = "—";
            data2.add(new Pici(year, first, second, third));
        }
        mybaseadapter = new MyBaseAdapter(this, data);
        mybaseadapter2 = new MyBaseAdapter(this, data2);
        provinceadapter = new ProvinceAdapter(this, province_data);
        listView.setAdapter(mybaseadapter);
        listView2.setAdapter(mybaseadapter2);
        listview_college_province.setAdapter(provinceadapter);
        loading.setVisibility(View.GONE);
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("PiCi Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }

    class MyBaseAdapter extends BaseAdapter {
        Context context;
        List<Pici> data;

        MyBaseAdapter(Context context, List<Pici> data) {
            this.context = context;
            this.data = data;
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_pici_list, parent, false);
            LinearLayout pici_bg = (LinearLayout) view.findViewById(R.id.pici_bg);
            TextView item_pici_list_year = (TextView) view.findViewById(R.id.item_pici_list_year);
            TextView item_pici_list_first = (TextView) view.findViewById(R.id.item_pici_list_first);
            TextView item_pici_list_second = (TextView) view.findViewById(R.id.item_pici_list_second);
            TextView item_pici_list_third = (TextView) view.findViewById(R.id.item_pici_list_third);
            item_pici_list_year.setText(data.get(position).getYear());
            item_pici_list_first.setText(data.get(position).getFirst_line());
            item_pici_list_second.setText(data.get(position).getSecond_line());
            item_pici_list_third.setText(data.get(position).getThird_line());
            if (position == 0) {
                item_pici_list_year.setTextColor(getResources().getColor(R.color.color_theme));
                item_pici_list_first.setTextColor(getResources().getColor(R.color.color_theme));
                item_pici_list_second.setTextColor(getResources().getColor(R.color.color_theme));
                item_pici_list_third.setTextColor(getResources().getColor(R.color.color_theme));
            }
            if (position % 2 == 0) {
                pici_bg.setBackgroundColor(getResources().getColor(R.color.white));
            }
            return view;
        }
    }

    class ProvinceAdapter extends BaseAdapter {
        Context context;
        List<String> data;

        ProvinceAdapter(Context context, List<String> data) {
            this.context = context;
            this.data = data;
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final View view = LayoutInflater.from(context).inflate(R.layout.item_major_list, parent, false);
            TextView item_major = (TextView) view.findViewById(R.id.item_major);
            if (data.get(position).length() > 0) {
                item_major.setText(data.get(position));
                if (data.get(position).equals(province.getText()))
                    item_major.setTextColor(getResources().getColor(R.color.color_theme));
            }
//            item_major.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    /*
//                    String str=null;
//                    try {
//                        str=URLEncoder.encode(data.get(position),"UTF-8");
//                    }catch (UnsupportedEncodingException ex) {
//                        ex.printStackTrace();
//                     }*/
//                    province.setText(data.get(position));
//                    nav_screen_arrow.setBackground(getResources().getDrawable(R.drawable.nav_screening_down_normal));
//                    if (view_province_container.getVisibility() == View.GONE)
//                        view_province_container.setVisibility(View.VISIBLE);
//                    else view_province_container.setVisibility(View.GONE);
//                    loading.setVisibility(View.VISIBLE);
//                    HttpServer.getPici(new CallbBackString(PiCiActivity.this), data.get(position));
//
//                    //   Toast.makeText(v.getContext(),data.get(position),Toast.LENGTH_SHORT).show();
//                }
//            });
            return view;
        }
    }
}
