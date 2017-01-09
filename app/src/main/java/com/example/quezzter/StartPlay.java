package com.example.quezzter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StartPlay extends Activity {

	Button start;
	TextView txtbox;
	String userName;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start_play);
		txtbox = (TextView)findViewById(R.id.textView1);
		start = (Button)findViewById(R.id.startbutton1);
		userName =getIntent().getStringExtra("USER_NAME");
		txtbox.setText("Hi "+userName+", lets play a game. You need to answer all the questions."
				+ " Click on submit button to see your result ");
		
		start.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent it =new Intent(StartPlay.this , Quiz.class);
				it.putExtra("Correct", "0");
				it.putExtra("USER_NAME", userName);
				it.putExtra("Attempt", "False");
				startActivity(it);
			}
		});
	}


}
