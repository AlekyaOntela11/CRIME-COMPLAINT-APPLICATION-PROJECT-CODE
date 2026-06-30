package com.example.alekya.adavancecrimecomplaint;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.TextView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class AdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
       Button viewcrime=(Button)findViewById(R.id.btviewreport);
		Button viewsolved=(Button)findViewById(R.id.btsolved);
		Button viewpending=(Button)findViewById(R.id.btpnding);
		Button viewfeedback=(Button)findViewById(R.id.btfeedback);
		Button home=(Button)findViewById(R.id.bthome);
		viewcrime.setOnClickListener(new OnClickListener()
		 {

			@Override
			public void onClick(View arg0)
			 {
			     Intent it=new Intent(AdminActivity.this,ViewcrimereportsActivity.class);
				startActivity(it);
			 }
		});


		viewsolved.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
			    Intent it=new Intent(AdminActivity.this,SolvedlistActivity.class);
				startActivity(it);
			}
		});
		viewpending.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
			    Intent it=new Intent(AdminActivity.this,PendinglistActivity.class);
				startActivity(it);
			}
		});
		home.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
			    Intent it=new Intent(AdminActivity.this,MainActivity.class);
				startActivity(it);
			}
		});
    }
}