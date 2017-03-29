package com.wan.college.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wan.college.R;
import com.wan.college.model.ChildArray;
import com.wan.college.model.Question;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by John Yan on 1/12/2017.
 */

public class ExpandableAdapter extends BaseExpandableListAdapter {

    private List<Question> questionArray;
    private Context mcontext;
    private Boolean isShow = false;
    int gp, cp;

    public ExpandableAdapter(List<Question> questionArray, Context context) {

        this.questionArray = questionArray;
        this.mcontext = context;
    }


    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (mcontext == null)
            Log.e("tip", "null");
        GroupHolder holder = null;
        Question question = questionArray.get(groupPosition);
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mcontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.paper_question_view, null);
            holder = new GroupHolder();
            holder.question_index = (TextView) convertView.findViewById(R.id.question_index);
            holder.question_content = (TextView) convertView.findViewById(R.id.question_content);
            holder.answered_flag = (ImageView) convertView.findViewById(R.id.answered_flag);
            holder.question_select_tag = convertView.findViewById(R.id.question_select_tag);
            convertView.setTag(holder);
        } else {
            holder = (GroupHolder) convertView.getTag();
        }

        holder.question_index.setText(questionArray.get(groupPosition).getQuestion_id());
        holder.question_content.setText(questionArray.get(groupPosition).getQuestion_content());
        if (question.isAnswered()) {
            holder.answered_flag.setImageResource(R.drawable.answerpage_answer_icon);
            holder.answered_flag.setVisibility(View.VISIBLE);
        } else {
            holder.answered_flag.setImageResource(R.drawable.answerpage_unanswer_icon);
            if (isShow) holder.answered_flag.setVisibility(View.VISIBLE);
            else holder.answered_flag.setVisibility(View.INVISIBLE);
        }
        if (isExpanded) holder.question_select_tag.setVisibility(View.VISIBLE);
        else holder.question_select_tag.setVisibility(View.GONE);
        return convertView;
    }


    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        ChildHolder holder = null;
        Question question = questionArray.get(groupPosition);
        gp = groupPosition;
        cp = childPosition;
        if (convertView == null) {
            holder = new ChildHolder();
            convertView = LayoutInflater.from(mcontext).inflate(R.layout.item_quesion_answer_list_view, null);
            holder.answer_index = (TextView) convertView.findViewById(R.id.answer_index);
            holder.answer_content = (TextView) convertView.findViewById(R.id.answer_content);
            convertView.setTag(holder);
        } else {
            holder = (ChildHolder) convertView.getTag();
        }
//  System.out.println("第" + groupPosition + "行" + "," + "第" + cp + "项");
        if (question.getAnswer_id().get(childPosition) != null && question.getIs_answer_id() != null && question.getIs_answer_id().equals(question.getAnswer_id().get(childPosition))) {
            holder.answer_index.setTextColor(mcontext.getResources().getColor(R.color.orange_pressed_color));
            holder.answer_content.setTextColor(mcontext.getResources().getColor(R.color.orange_pressed_color));
        } else {
            holder.answer_index.setTextColor(mcontext.getResources().getColor(R.color.wallet_listitem_text_color));
            holder.answer_content.setTextColor(mcontext.getResources().getColor(R.color.wallet_listitem_text_color));
        }
        holder.answer_index.setText(question.getAnswer_id().get(childPosition) + "、");
        holder.answer_content.setText(question.getAnswer_content().get(childPosition));
        return convertView;
    }


    class GroupHolder {
        TextView question_index;
        TextView question_content;
        ImageView answered_flag;
        View question_select_tag;
    }

    class ChildHolder {
        TextView answer_index;
        TextView answer_content;
    }


    public void setGroupIconAndChildAnswer(int groupPosition, int childPosition) {
        questionArray.get(groupPosition).setAnswered(true);
        questionArray.get(groupPosition).setIs_answer_id(questionArray.get(groupPosition).getAnswer_id().get(childPosition));
    }

    public Boolean isGroupAnswered(int groupPosition) {
        return questionArray.get(groupPosition).isAnswered();
    }

    public void showAllVisible() {
        isShow = true;
    }

    @Override
    public int getGroupCount() {
        //  return groupArray.size();
        return questionArray.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        //   return childArray.get(groupPosition).getAnswer().length;
        return questionArray.get(groupPosition).getAnswer_id().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        // return groupArray.get(groupPosition);
        return questionArray.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        // return childArray.get(groupPosition).getAnswer();
        return questionArray.get(groupPosition).getAnswer_content();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }


    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

}
