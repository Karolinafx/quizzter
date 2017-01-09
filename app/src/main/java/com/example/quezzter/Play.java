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
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Play extends Activity {

	TextView userName;
	TextView qusetion;
	Button skipBtn;
	Button submitBtn;
	Button optionBtnA;
	Button optionBtnB;
	Button optionBtnC;
	Button optionBtnD;
    ArrayList<Dataholder> dataObjectList = new ArrayList<Dataholder>();
    

    int indexVal = 0;
    int marks = 0;
    String user;
    int count=0;
    int markscount=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_play);
		user = getIntent().getStringExtra("USER_NAME");
		count =Integer.parseInt(getIntent().getStringExtra("Count"));
		markscount = Integer.parseInt(getIntent().getStringExtra("TotalMark"));
		String inde = getIntent().getStringExtra("Index");
		userName = (TextView)findViewById(R.id.playerName);
		userName.setText("Welcome "+user);
		indexVal = Integer.parseInt(inde);
		qusetion = (TextView)findViewById(R.id.questionTV);
		skipBtn = (Button)findViewById(R.id.playerSkip);
		submitBtn = (Button)findViewById(R.id.playersubmitbutton);
		optionBtnA = (Button)findViewById(R.id.optionABtn);
		optionBtnB = (Button)findViewById(R.id.optionBBtn);
		optionBtnC = (Button)findViewById(R.id.optionCBtn);
		optionBtnD = (Button)findViewById(R.id.optionDBtn);
		dataObjectList.clear();
		readJsonFile();

		displayQuestion(indexVal);
		
		
		submitBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {

				Intent it =new Intent(Play.this , Quiz.class);
				it.putExtra("Correct", "0");
				it.putExtra("USER_NAME", user);
				it.putExtra("Attempt", "False");
				startActivity(it);
			}
		});
		skipBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {

				if(dataObjectList.get(indexVal).getCorrectAnswer().equals("A"))
				{
				Toast.makeText(Play.this, "Answer is "+dataObjectList.get(indexVal).getCorrectAnswer() +" : "+dataObjectList.get(indexVal).getOptionA(), Toast.LENGTH_SHORT).show();					
				}
				else if(dataObjectList.get(indexVal).getCorrectAnswer().equals("B"))
				{
				Toast.makeText(Play.this, "Answer is "+dataObjectList.get(indexVal).getCorrectAnswer() +" : "+dataObjectList.get(indexVal).getOptionB(), Toast.LENGTH_SHORT).show();					
				}
				else if(dataObjectList.get(indexVal).getCorrectAnswer().equals("C"))
				{
				Toast.makeText(Play.this, "Answer is "+dataObjectList.get(indexVal).getCorrectAnswer() +" : "+dataObjectList.get(indexVal).getOptionC(), Toast.LENGTH_SHORT).show();					
				}
				else if(dataObjectList.get(indexVal).getCorrectAnswer().equals("D"))
				{
				Toast.makeText(Play.this, "Answer is "+dataObjectList.get(indexVal).getCorrectAnswer() +" : "+dataObjectList.get(indexVal).getOptionD(), Toast.LENGTH_SHORT).show();					
				}
				Intent it =new Intent(Play.this , Quiz.class);
				it.putExtra("Correct", "0");
				it.putExtra("USER_NAME", user);
				it.putExtra("Attempt", "True:"+indexVal);
				startActivity(it);
				
			}
		});
		optionBtnA.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {

					Intent it =new Intent(Play.this , Quiz.class);
					it.putExtra("Correct", "0");	
					if(dataObjectList.get(indexVal).getCorrectAnswer().equals("A"))
					{
						marks ++;
						it.putExtra("Correct", "1");	
					}
					it.putExtra("Attempt", "True:"+indexVal);
					it.putExtra("USER_NAME", user);
					startActivity(it);

			}
		});
		optionBtnB.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {

					Intent it =new Intent(Play.this , Quiz.class);
					it.putExtra("Correct", "0");	
					if(dataObjectList.get(indexVal).getCorrectAnswer().equals("B"))
					{
						marks ++;
						it.putExtra("Correct", "1");	
					}
					it.putExtra("USER_NAME", user);
					it.putExtra("Attempt", "True:"+indexVal);
					startActivity(it);
			}
		});
		optionBtnC.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {

					Intent it =new Intent(Play.this , Quiz.class);
					it.putExtra("Correct", "0");	
					if(dataObjectList.get(indexVal).getCorrectAnswer().equals("C"))
					{
						marks ++;
						it.putExtra("Correct", "1");	
					}
					it.putExtra("USER_NAME", user);
					it.putExtra("Attempt", "True:"+indexVal);
					startActivity(it);
					
			}
		});
		optionBtnD.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {

					Intent it =new Intent(Play.this , Quiz.class);
					it.putExtra("Correct", "0");	
					if(dataObjectList.get(indexVal).getCorrectAnswer().equals("D"))
					{
						marks ++;
						it.putExtra("Correct", "1");	
					}
					it.putExtra("USER_NAME", user);
					it.putExtra("Attempt", "True:"+indexVal);
					startActivity(it);
			}
		});
	}

	private void displayQuestion(int indexValue){
		if(dataObjectList.size()>0 ){

			if(indexValue < dataObjectList.size()){
			qusetion.setText(dataObjectList.get(indexValue).getQuestion());
			optionBtnA.setText(dataObjectList.get(indexValue).getOptionA());
			optionBtnB.setText(dataObjectList.get(indexValue).getOptionB());
			optionBtnC.setText(dataObjectList.get(indexValue).getOptionC());
			optionBtnD.setText(dataObjectList.get(indexValue).getOptionD());
			}else{
			}
			
		}else{
			qusetion.setText("There is no question ");
		}
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
           for (int i = 0; i < jsArr.length(); i++) {
				JSONObject paper = jsArr.getJSONObject(i);
				Dataholder dataObj = new Dataholder();
				dataObj.setQuestion(paper.getString("question"));
				dataObj.setOptionA(paper.getString("OptionA"));
				dataObj.setOptionB(paper.getString("OptionB"));
				dataObj.setOptionC(paper.getString("OptionC"));
				dataObj.setOptionD(paper.getString("OptionD"));
				dataObj.setCorrectAnswer(paper.getString("answer"));
				dataObjectList.add(dataObj);
			}

		} catch (Exception e) {

		}
	}

	@Override
	public void onBackPressed(){
		Toast.makeText(Play.this, "You cant go back", Toast.LENGTH_SHORT).show();	

	}
}
