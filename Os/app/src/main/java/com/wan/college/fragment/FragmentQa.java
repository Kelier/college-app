package com.wan.college.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.wan.college.R;
import com.wan.college.ui.MajorListView;

import java.util.ArrayList;
import java.util.List;


public class FragmentQa extends Fragment implements View.OnClickListener{


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    ListView listView;
    List<String> data;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_footer_fragment_qa, container, false);
        listView = (ListView) view.findViewById(R.id.fragment_quesionlist_sub_listview);
        data=new ArrayList<>();
        data.add("wssss");
        data.add("wssss");
        data.add("wssss");
        data.add("wssss");
        data.add("wssss");
        data.add("wssss");data.add("wssss");data.add("wssss");data.add("wssss");
        data.add("wssss");data.add("wssss");data.add("wssss");

        MyBaseAdapter mybaseadapter = new MyBaseAdapter(getActivity(), data);
        listView.setAdapter(mybaseadapter);
        return view;
    }
    private void initView(View view) {


    }
    @Override
    public void onClick(View view) {
/*
        switch(view.getId()){
            case R.id.imageview:
                startActivity(new Intent(getActivity(), PersonInfoActivity.class));
                break;

        }
        */
    }
    class MyBaseAdapter extends BaseAdapter {
        Context context;
        List<String> data;

        MyBaseAdapter(Context context, List<String> data) {
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
            View view = LayoutInflater.from(context).inflate(R.layout.item_qa_question_list_view, null);
            /*
            TextView tv_name = (TextView) view.findViewById(R.id.item_major);
            tv_name.setText(( data.get(position)).toString());
            */

            return view;
        }
    }

}
