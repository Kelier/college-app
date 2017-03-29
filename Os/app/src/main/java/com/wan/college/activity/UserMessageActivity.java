package com.wan.college.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.wan.college.R;

public class UserMessageActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_usermessage);

		TextView text = (TextView) findViewById(R.id.textView);
		Button btn= (Button) findViewById(R.id.button);
		btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				SharedPreferences spf = getSharedPreferences("login_info", MODE_PRIVATE);
				SharedPreferences.Editor editor = spf.edit();
				editor.putString("uname","" );
				editor.putString("upwd","" );
				editor.putBoolean("login", false);
				// editor.clear();
				editor.apply();
				Intent intent = new Intent();
				intent.putExtra("login",false);
				setResult(1, intent);
				UserMessageActivity.this.finish();
			}
		});
		Intent receive = getIntent();
		String flog = receive.getStringExtra("flog");

		text.setText(flog);

	}
}
