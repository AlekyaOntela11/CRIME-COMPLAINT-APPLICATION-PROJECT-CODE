package com.example.alekya.adavancecrimecomplaint;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CrimereportActivity extends Activity
{
SQLiteDatabase db;
	String sdesc;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_crimereport);
		String date=getIntent().getStringExtra("date");
		db=openOrCreateDatabase("CRIME COMPLAINT APPLICATION", MODE_PRIVATE,null);
		ImageView iv=(ImageView)findViewById(R.id.crimeviewimg);
		TextView tvdate=(TextView)findViewById(R.id.viewdate);
		TextView tvplace=(TextView)findViewById(R.id.viewplace);
		final TextView tvdesc=(TextView)findViewById(R.id.viewdescription);
		Button finished=(Button)findViewById(R.id.finished);
		Button inprocess=(Button)findViewById(R.id.inprocess);
		Cursor c=db.rawQuery("select * from postcrime where status='crime'",null);
		c.moveToFirst();
		if(c.getCount()>0){


			int i=c.getColumnIndex("image");
			String simg=c.getString(i);
			iv.setImageBitmap(BitmapFactory.decodeFile(simg));
			Toast.makeText(getApplicationContext(), simg, 100).show();

			int j=c.getColumnIndex("date");
			String sdate=c.getString(j);
			tvdate.setText(sdate);
			int k=c.getColumnIndex("place");
			String splace=c.getString(k);
			tvplace.setText(splace);
			int l=c.getColumnIndex("description");
			sdesc=c.getString(l);
			tvdesc.setText(sdesc);




		}
		finished.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
			    db.execSQL("update postcrime set status='solved' where description='"+sdesc+"'");
				Intent it=new Intent(CrimereportActivity.this,AdminActivity.class);
				startActivity(it);
			}
		});

		inprocess.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
			    db.execSQL("update postcrime set status='pending' where description='"+sdesc+"'");
				Intent it=new Intent(CrimereportActivity.this,AdminActivity.class);
				startActivity(it);
			}
		});
	}
}



