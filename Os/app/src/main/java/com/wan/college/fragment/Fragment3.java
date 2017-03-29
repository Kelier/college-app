package com.wan.college.fragment;



import com.wan.college.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;




public class Fragment3 extends Fragment {
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.f3, null);
		setupView(view);
		addListener();
		return view;
	}

	private void addListener() {
	}

	private void setupView(View view) {
	}

}
