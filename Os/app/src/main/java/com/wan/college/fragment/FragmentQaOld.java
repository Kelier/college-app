//package com.wan.college.fragment;
//
//
//import android.content.Context;
//import android.os.Bundle;
//import android.support.v4.app.Fragment;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.ListView;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//
//import com.google.gson.Gson;
//import com.wan.college.R;
//import com.wan.college.model.QuestionAnswerData;
//import com.wan.college.network.INetResult;
//
//import org.w3c.dom.Text;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import butterknife.OnClick;
//
//
//public class FragmentQaOld extends Fragment implements View.OnClickListener,INetResult<String> {
//
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }
//    ListView listView;
//    TextView question_content;
//    TextView browse_count;
//    RelativeLayout question_newest;
//    RelativeLayout question_day;
//    RelativeLayout question_week;
//    QuestionAnswerData questionAnswerData;
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.content_footer_fragment_qa, container, false);
//        initView(view);
//        addListener();
//        initNewData();
//
//        return view;
//    }
//
//    private void initNewData() {
//        MyBaseAdapter mybaseadapter = new MyBaseAdapter(getActivity(), questionAnswerData);
//        listView.setAdapter(mybaseadapter);
//    }
//
//    private void addListener() {
//        question_day.setOnClickListener(this);
//        question_week.setOnClickListener(this);
//        question_newest.setOnClickListener(this);
//    }
//
//    private void initView(View view) {
//        listView = (ListView) view.findViewById(R.id.fragment_quesionlist_sub_listview);
////        question_day= (RelativeLayout) view.findViewById(R.id.question_day);
////        question_newest= (RelativeLayout) view.findViewById(R.id.question_newest);
////        question_week= (RelativeLayout) view.findViewById(R.id.question_week);
//    }
//    @Override
//    public void onClick(View view) {
//
//        switch(view.getId()){
//            case R.id.question_newest:
//
//                break;
//            case R.id.question_day:
//
//                break;
//            case R.id.question_week:
//
//                break;
//
//        }
//
//    }
//
//    @Override
//    public void getNetData(String data) {
//        questionAnswerData=new Gson().fromJson(data,QuestionAnswerData.class);
//
//    }
//
//    class MyBaseAdapter extends BaseAdapter {
//        Context context;
//        QuestionAnswerData data;
//
//        MyBaseAdapter(Context context, QuestionAnswerData data) {
//            this.context = context;
//            this.data = data;
//        }
//
//        @Override
//        public int getCount() {
//            return data.getPosts().size();
//        }
//        @Override
//        public Object getItem(int position) {
//            return data.getPosts().get(position);
//        }
//        @Override
//        public long getItemId(int position) {
//            return position;
//        }
//        @Override
//        public View getView(final int position, View convertView, ViewGroup parent) {
//            View view = LayoutInflater.from(context).inflate(R.layout.item_qa_question_list_view, null);
//
//
//            /*
//            TextView tv_name = (TextView) view.findViewById(R.id.item_major);
//            tv_name.setText(( data.get(position)).toString());
//            */
//
//            return view;
//        }
//    }
//
//}
