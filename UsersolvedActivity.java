package com.example.alekya.adavancecrimecomplaint;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class UsersolvedActivity extends Activity {
	SQLiteDatabase db;
	String sdesc;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_usersolved);
		String sdate=getIntent().getStringExtra("date");
		db=openOrCreateDatabase("CRIME COMPLAINT APPLICATION", MODE_PRIVATE,null);
		ImageView iv=(ImageView)findViewById(R.id.usersolvedcrimeviewimg);
		TextView usersolveddate=(TextView)findViewById(R.id.usersolveddate);
		TextView usersolvedplace=(TextView)findViewById(R.id.usersolvedplace);
		TextView usersolveddesc=(TextView)findViewById(R.id.usersolveddescription);
		Button usersolvedok=(Button)findViewById(R.id.usersolvedok);

		Cursor c=db.rawQuery("select * from postcrime where date='"+sdate+"'",null);
		c.moveToFirst();
		if(c.getCount()>0){
			int i=c.getColumnIndex("image");
			String simg=c.getString(i);

			int j=c.getColumnIndex("date");
			String stdate=c.getString(j);
			usersolveddate.setText(stdate);
			int k=c.getColumnIndex("place");
			String splace=c.getString(k);
			usersolvedplace.setText(splace);
			int l=c.getColumnIndex("description");
			sdesc=c.getString(l);
			usersolveddesc.setText(sdesc);
		}
		usersolvedok.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
			    Intent it=new Intent(UsersolvedActivity.this,UserActivity.class);
				startActivity(it);
			}
		});


	}
}
