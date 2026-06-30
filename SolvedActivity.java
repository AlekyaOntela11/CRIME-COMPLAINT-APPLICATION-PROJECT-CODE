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

public class SolvedActivity extends Activity {
	SQLiteDatabase db;
	String sdesc;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_solved);
		String sdate=getIntent().getStringExtra("date");
		db=openOrCreateDatabase("CRIME COMPLAINT APPLICATION", MODE_PRIVATE,null);
		ImageView iv=(ImageView)findViewById(R.id.solvedcrimeviewimg);
		TextView solveddate=(TextView)findViewById(R.id.solveddate);
		TextView solvedplace=(TextView)findViewById(R.id.solvedplace);
		TextView solveddesc=(TextView)findViewById(R.id.solveddescription);
		Button solvedok=(Button)findViewById(R.id.solvedok);
		Button solvedinprocess=(Button)findViewById(R.id.solvedinprocess);
		Cursor c=db.rawQuery("select * from postcrime where date='"+sdate+"'",null);
		c.moveToFirst();
		if(c.getCount()>0){
			int i=c.getColumnIndex("image");
			String simg=c.getString(i);

			int j=c.getColumnIndex("date");
			String stdate=c.getString(j);
			solveddate.setText(stdate);
			int k=c.getColumnIndex("place");
			String splace=c.getString(k);
			solvedplace.setText(splace);
			int l=c.getColumnIndex("description");
			sdesc=c.getString(l);
			solveddesc.setText(sdesc);
		}
		solvedok.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
			    Intent it=new Intent(SolvedActivity.this,AdminActivity.class);
				startActivity(it);
			}
		});

		solvedinprocess.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
			    db.execSQL("update postcrime set status='pending' where description='"+sdesc+"'");
				Toast.makeText(getApplicationContext(), "This case is still in process", Toast.LENGTH_SHORT).show();
				Intent it=new Intent(SolvedActivity.this,AdminActivity.class);
				startActivity(it);
			}
		});
	}
}
