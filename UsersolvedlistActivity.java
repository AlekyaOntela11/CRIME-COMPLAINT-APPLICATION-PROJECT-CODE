package com.example.alekya.adavancecrimecomplaint;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class UsersolvedlistActivity extends Activity {
	SQLiteDatabase db;
	ArrayList<String> al=new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_usersolvedlist);
		db=openOrCreateDatabase("CRIME COMPLAINT APPLICATION", MODE_PRIVATE,null);
		final ListView lv=(ListView)findViewById(R.id.lvusersolved);
		Cursor c=db.rawQuery("select * from postcrime where status='solved'",null);
		c.moveToFirst();
		if(c.getCount()>0){
			do{
			int i=c.getColumnIndex("date");
			String sdate=c.getString(i);
			al.add(sdate);
			}while(c.moveToNext());
			ArrayAdapter<String> ad=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,al);
			lv.setAdapter(ad);
		}
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {

				String date=lv.getItemAtPosition(arg2).toString();
				Intent it=new Intent(UsersolvedlistActivity.this,UsersolvedActivity.class);
				it.putExtra("date",date);
				startActivity(it);
			}
		});
	}
}