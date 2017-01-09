package com.example.quezzter;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends Activity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		writeOnSavedFile();
		writemarksFile();
		new Handler().postDelayed(new Runnable() {
			
			@Override
			public void run() {
				
			Intent it = new Intent(MainActivity.this , Login.class);
			startActivity(it);
			finish();
			}
		}, 2000);
	}

	private void writeOnSavedFile(){
		String listClick;
		try {
			FileOutputStream out = openFileOutput("question.txt", Context.MODE_PRIVATE);
			 String str = "";
			out.write(str.getBytes());
			out.close();
	        
		} catch (Exception e) {

		}
	}

	private void writemarksFile(){

		try {
			FileOutputStream out = openFileOutput("marks.txt", Context.MODE_PRIVATE);
			String str = "0";
			out.write(str.getBytes());
			out.close();
	        
		} catch (Exception e) {
		}
	}
}
