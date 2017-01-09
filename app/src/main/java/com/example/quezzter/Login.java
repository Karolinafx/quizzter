package com.example.quezzter;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends Activity {

	Button loginBtn ;
	Button registerBtn;
	EditText loginName;
	EditText loginPass;
	SharedPreferences sharedPreferences;
	Map<String, String> loginss = new HashMap<String, String>();
	String loginNamed ;
	String loginPassed;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		loginBtn = (Button)findViewById(R.id.button1);
		registerBtn = (Button)findViewById(R.id.button2);
		loginName = (EditText)findViewById(R.id.loginusereditText1);
		loginPass = (EditText)findViewById(R.id.loginpasswordeditText1);

		sharedPreferences = getSharedPreferences("Register", 0);
		loginBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {

                String uName = null;
                String uPassword =null;
    			loginNamed =loginName.getText().toString();
				loginPassed = loginPass.getText().toString();
				if(loginNamed.trim().length()==0){
					Toast.makeText(Login.this, "Username should not be empty", Toast.LENGTH_SHORT).show();
				}	
				else if(loginPassed.trim().length()==0){
					Toast.makeText(Login.this, "Password should not be empty", Toast.LENGTH_SHORT).show();
				}
				else if(sharedPreferences.getString(loginNamed, null)!=null)
				{
					if(loginPassed.equals(sharedPreferences.getString(loginNamed, null))){
						Intent it = new Intent(Login.this , StartPlay.class);
						it.putExtra("USER_NAME", loginNamed);
						it.putExtra("Correct", "0");
						it.putExtra("Attempt", "False:");	
						startActivity(it);
					}else{
						Toast.makeText(Login.this, "Password does not match ", Toast.LENGTH_SHORT).show();						
					}
				}
				else{
					Toast.makeText(Login.this, "User name "+loginNamed+" doesn't exist, Please register", Toast.LENGTH_SHORT).show();
				}
				loginName.setText("");
				loginPass.setText("");
          
			}
		});
		registerBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {

				Intent it = new Intent(Login.this , Register.class);
				 
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
}
