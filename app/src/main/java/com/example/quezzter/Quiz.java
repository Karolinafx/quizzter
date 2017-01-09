package com.example.quezzter;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Quiz extends Activity {

	ArrayList<String>questionList = new ArrayList<String>();
	TextView quizBrief;
	ListView listView;
	Button submitBtn;
	String userName;
	String str;
	int  marksCount = 0;
	int totalMark =0;
	String attempt ;
	int totalNumQuestion;
	public boolean isEnabled(int position){

		return false;
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		try {
			super.onCreate(savedInstanceState);			
			setContentView(R.layout.activity_quiz);
			submitBtn = (Button)findViewById(R.id.QuizFinalSubBtn);
			quizBrief = (TextView)findViewById(R.id.quiztextView1);
			userName = getIntent().getStringExtra("USER_NAME");
			marksCount =Integer.parseInt(getIntent().getStringExtra("Correct"));
			if(marksCount==1)
				writemarksFile();
			attempt = getIntent().getStringExtra("Attempt");
			if(attempt.contains("True"))
			{
				String[] attemp = attempt.split(":");
				int num = Integer.parseInt(attemp[1]);
			writeOnSavedFile(num+1);	
			}
		readJsonFile();	
		
		quizBrief.setText("Hi "+userName +", click on the question you want to answer.");
		listView = (ListView)findViewById(R.id.question_list);
		ArrayAdapter<String> adpter = new ArrayAdapter<String>(this, 
		         R.layout.question_listfile, questionList);
		listView.setAdapter(adpter);	
		 str =readOnSavedFile();
		 String[] splitStr = str.split(",");
		 submitBtn.setVisibility(View.GONE);
		 if(splitStr.length == totalNumQuestion){
			 submitBtn.setVisibility(View.VISIBLE);
		 }
			String marksCount  = readmarksFile();
			totalMark = Integer.parseInt(marksCount);
			submitBtn.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {

					Intent it =new Intent(Quiz.this , Result.class);
					it.putExtra("Marks", ""+totalMark);

					it.putExtra("USER_NAME", userName);
					it.putExtra("Total", ""+totalNumQuestion);
					startActivity(it);
				}
			});
		listView.setOnItemClickListener(new OnItemClickListener()
		 {
		 @Override
		 public void onItemClick(AdapterView<?> parent, View view,
		 int position, long id) {

			 boolean clicked =false;
			 String[] spltArr= {};
			 if(str!=null && str.length()>0){
					 spltArr = str.trim().split(",");
					for (int i = 0; i < spltArr.length; i++) {
						if(Integer.parseInt(spltArr[i])-1==position)
							clicked =true;
					}}
			 if(!clicked){
			 Intent it = new Intent(Quiz.this,Play.class);
			 it.putExtra("USER_NAME", userName);
			 it.putExtra("TotalMark", ""+totalMark);
			 it.putExtra("Index", ""+position);
			 int cout = spltArr.length;
			 cout++;
			 it.putExtra("Count", ""+(cout));
			 startActivity(it);

			 }else{
				 Toast.makeText(Quiz.this, "You already answered this question", Toast.LENGTH_SHORT).show();
			 }
		 }
		 });
		 
		 
	} catch (Exception e) {

	}
	}

	  private void disableClick(int num) {

	            View v = listView.getChildAt(num);
	            v.setEnabled(false);
	        
	    }
	  
	private void readJsonFile()
	{
		 String json = null;
		try {
			InputStream is = getAssets().open("jsondata/questiondetail.json");
           int size = is.available();
           byte[] buffer = new byte[size];
           is.read(buffer);
           is.close();
           json = new String(buffer, "UTF-8");
           JSONObject jsObj = new JSONObject(json);
           JSONArray jsArr = jsObj.getJSONArray("questionDetails");
           totalNumQuestion = jsArr.length();
           for (int i = 0; i < jsArr.length(); i++) {
				JSONObject paper = jsArr.getJSONObject(i);
        	   questionList.add((i+1)+" : "+paper.getString("question"));
			}
		} catch (Exception e) {

		}
	}

	private String readOnSavedFile(){
		String listClick="";
		try {
			FileInputStream fin = openFileInput("question.txt");
			int c;
			 listClick="";
			while( (c = fin.read()) != -1){
				listClick = listClick + Character.toString((char)c);
			}
	        
		} catch (Exception e) {

			listClick = "";
		}
		return listClick;
	}
	private void writeOnSavedFile(int num){
		String listClick;

		String str =readOnSavedFile();
		try {
			FileOutputStream out = openFileOutput("question.txt", Context.MODE_PRIVATE);
			 str = str+num+",";
			out.write(str.getBytes());
			out.close();
	        
		} catch (Exception e) {

		}
	}
	private String readmarksFile(){
		String listClick="0";

		try {
			FileInputStream fin = openFileInput("marks.txt");
			int c;
			 listClick="";
			while( (c = fin.read()) != -1){
				listClick = listClick + Character.toString((char)c);
			}

		} catch (Exception e) {
			listClick = "0";
		}
		return listClick;
	}
	private void writemarksFile(){

		String str =readmarksFile();
		try {
			FileOutputStream out = openFileOutput("marks.txt", Context.MODE_PRIVATE);
			 int num = Integer.parseInt(str);
			 num++;
			 str = ""+num;
			 totalMark =num;

			out.write(str.getBytes());
			out.close();
	        
		} catch (Exception e) {

		}
	}
	@Override
	public void onBackPressed(){
		Toast.makeText(Quiz.this, "You cant go back", Toast.LENGTH_SHORT).show();	

	}
}
