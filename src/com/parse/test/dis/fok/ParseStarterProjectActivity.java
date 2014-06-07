package com.parse.test.dis.fok;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParsePush;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import com.parse.test.dis.fok.R;

public class ParseStarterProjectActivity extends Activity implements OnClickListener {

	final String firstParseObjName="FirstParse";
	
	TextView tvAnswerFetch;
	TextView tvAnswerFind;
	Button btnPush;
	EditText edNotify;

	
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		ParseAnalytics.trackAppOpened(getIntent());
		
		tvAnswerFetch=(TextView) findViewById(R.id.tvAnswerFetch);
		tvAnswerFind=(TextView) findViewById(R.id.tvAnswerFind);
		edNotify=(EditText) findViewById(R.id.edNotify);
		btnPush=(Button) findViewById(R.id.btnPush);
		btnPush.setOnClickListener(this);
		
		
		
	
	}

	@Override
	public void onClick(View v) {
		
		try {
			
			final ParseObject firstParseObj;
			firstParseObj= new ParseObject(firstParseObjName);
			
			String notify=edNotify.getText().toString();
			firstParseObj.put("name", notify);
			
			final String currentObjId =firstParseObj.getObjectId();

		
			firstParseObj.saveInBackground(new SaveCallback() {
				@Override
				public void done(ParseException e) {
					if(e==null){
						Toast.makeText(ParseStarterProjectActivity.this,"notify saved, id: "+currentObjId, Toast.LENGTH_SHORT).show();

						ParseQuery<ParseObject> query= ParseQuery.getQuery(firstParseObjName);

						query.findInBackground(new FindCallback<ParseObject>() {
							
							@Override
							public void done(List<ParseObject> objects, ParseException e) {
								
								if (e==null) {
									ParseObject lastParseObj=objects.get(objects.size()-1);
									String answer=lastParseObj.getString("name");
									tvAnswerFind.setText("Find: "+answer+" id: "+lastParseObj.getObjectId());
								}else{
									e.printStackTrace();
									tvAnswerFind.setText("find answer failed");
								}
							}
						});
						
						
						firstParseObj.fetchInBackground(new GetCallback<ParseObject>() {
							  public void done(ParseObject object, ParseException e) {
							    if (e == null) {
							   
									String answer=object.getString("name");
									tvAnswerFetch.setText("Fetch: "+answer+" id: "+object.getObjectId());
								}else{
									e.printStackTrace();
									tvAnswerFetch.setText("Fetchanswer failed");
							    }
							  }
							});
						
						/*query.getInBackground(currentObjId, new GetCallback<ParseObject>() {
							
							@Override
							public void done(ParseObject object, ParseException e) {
								if (e==null) {
									
									String answer=object.getString("name");
									tvAnswer.setText(answer);
								}else{
									e.printStackTrace();
									tvAnswer.setText("answer failed");
								}
							}
						});	*/
					}else{
						Toast.makeText(ParseStarterProjectActivity.this,"notify failed", Toast.LENGTH_SHORT).show();
					}
					
				}
			});	
			
			//ParsePush parsePush= new ParsePush();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			edNotify.setText("");
		}
	
		
	
		
	}
}
