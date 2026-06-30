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

public class PendingActivity extends Activity
{
    String sdesc;
	SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pending);
		String sdate=getIntent().getStringExtra("date");
		db=openOrCreateDatabase("CRIME COMPLAINT APPLICATION", MODE_PRIVATE,null);
		ImageView iv=(ImageView)findViewById(R.id.pendingcrimeviewimg);
		TextView pendingdate=(TextView)findViewById(R.id.pendingdate);
		TextView pendingplace=(TextView)findViewById(R.id.pendingplace);
		TextView pendingdesc=(TextView)findViewById(R.id.pendingdescription);
		Button pendingok=(Button)findViewById(R.id.pendingok);
		Button pendingfinish=(Button)findViewById(R.id.pendingfinish);
		Cursor c=db.rawQuery("select * from postcrime where date='"+sdate+"'",null);
		c.moveToFirst();
		if(c.getCount()>0)
		{
		    int i=c.getColumnIndex("image");
			String simg=c.getString(i);

			int j=c.getColumnIndex("date");
			String stdate=c.getString(j);
			pendingdate.setText(stdate);
			int k=c.getColumnIndex("place");
			String splace=c.getString(k);
			pendingplace.setText(splace);
			int l=c.getColumnIndex("description");
			sdesc=c.getString(l);
			pendingdesc.setText(sdesc);
		}
		pendingok.setOnClickListener(new OnClickListener()
		 {

			@Override
			public void onClick(View arg0)
			{

				Intent it=new Intent(PendingActivity.this,AdminActivity.class);
				startActivity(it);
			}
		});

		pendingfinish.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View arg0)
			{

				db.execSQL("update postcrime set status='solved' where description='"+sdesc+"'");
				Toast.makeText(getApplicationContext(), "This case is solved", Toast.LENGTH_SHORT).show();
				Intent it=new Intent(PendingActivity.this,AdminActivity.class);
				startActivity(it);
			}
		});
	}
}