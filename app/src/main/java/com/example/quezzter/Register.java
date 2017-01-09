package com.example.quezzter;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends Activity {

	EditText userName;
	EditText passWord;
	EditText confirmPassWord;
	Button registerScBtn;
	SharedPreferences sharedPrefr;
	Editor edit;
	int sizeOfShared=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		registerScBtn = (Button)findViewById(R.id.registerbutton1);
		userName = (EditText)findViewById(R.id.registereditText1);
		passWord = (EditText)findViewById(R.id.registereditText2);
		confirmPassWord = (EditText)findViewById(R.id.editText1);
		 sharedPrefr= getSharedPreferences("Register", 0);
		 sizeOfShared= sharedPrefr.getAll().size();

		 edit = sharedPrefr.edit();
		registerScBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {

				String uName = userName.getText().toString();
				String paWord = passWord.getText().toString();
				String confPassWord = confirmPassWord.getText().toString();
				if(uName.trim().length()==0){
					Toast.makeText(Register.this, "Username should not be empty", Toast.LENGTH_SHORT).show();
				}	
				else if(paWord.trim().length()==0){
					Toast.makeText(Register.this, "Password should not be empty", Toast.LENGTH_SHORT).show();
				}
				else if(!paWord.equals(confPassWord)){
					Toast.makeText(Register.this, "Password's did not match", Toast.LENGTH_SHORT).show();
				}
				else if(sharedPrefr.getString(uName, null)!=null){
					Toast.makeText(Register.this, "Username already exist ", Toast.LENGTH_SHORT).show();
				}
				else{
					edit.putString(uName, paWord);
					edit.commit();
					Intent it = new Intent(Register.this , StartPlay.class);
					it.putExtra("USER_NAME", uName);
					it.putExtra("Correct", "0");
					it.putExtra("Attempt", "False:");	
					startActivity(it);
					
				}
				userName.setText("");passWord.setText("");confirmPassWord.setText("");
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
}
