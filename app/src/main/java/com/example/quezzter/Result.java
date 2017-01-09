package com.example.quezzter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Result extends Activity {

	TextView displ;
	TextView result;
	Button retry ;
	String user;
	String mark;
	String total;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);	
		displ = (TextView)findViewById(R.id.resulttextView1);
		result = (TextView)findViewById(R.id.resultdis);
		retry= (Button)findViewById(R.id.replay);
		user = getIntent().getStringExtra("USER_NAME");
		mark =getIntent().getStringExtra("Marks");
		total = getIntent().getStringExtra("Total");

		
		displ.setText(user +", you have answered all the questions!");
		result.setText("Your score is "+mark +"/"+total);
		retry.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {

				Intent it = new Intent(Result.this,MainActivity.class);
				startActivity(it);
			}
			});
	}

	@Override
	public void onBackPressed(){
		android.os.Process.killProcess(android.os.Process.myPid());
		super.onDestroy();
		finish();
		System.exit(0);
	}

	public  void Replay(){
		Intent it = new Intent(this,MainActivity.class);
		startActivity(it);
	}
}
