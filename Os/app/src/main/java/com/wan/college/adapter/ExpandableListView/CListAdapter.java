package com.wan.college.adapter.ExpandableListView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wan.college.R;
import com.wan.college.entity.ChildEntity;

import java.util.ArrayList;


/**
 * 
 * @author Apathy、恒
 * 
 *         子类子类列表的适配器
 * 
 * */
public class CListAdapter extends BaseAdapter {

	private Context mContext;
	private ArrayList<ChildEntity> mChilds;

	public CListAdapter(Context context, ArrayList<ChildEntity> childs) {
		this.mContext = context;
		this.mChilds = childs;
	}

	@Override
	public int getCount() {
		return mChilds != null ? mChilds.size() : 0;
	}

	@Override
	public Object getItem(int position) {
		if ((getCount() > 0) && (position > 0 && position < mChilds.size())) {
			return mChilds.get(position);
		}
		return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Holder holder = null;
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.item_elist_child_child, null);
			holder = new Holder(convertView);
			convertView.setTag(holder);
		} else {
			holder = (Holder) convertView.getTag();
		}
		holder.update(mChilds.get(position).getGroupName());
		return convertView;
	}

	class Holder {

		private TextView tv;

		public Holder(View v) {
			tv = (TextView) v.findViewById(R.id.childChildTV);
		}

		public void update(String text) {
			tv.setText(text);
		}
	}
}
