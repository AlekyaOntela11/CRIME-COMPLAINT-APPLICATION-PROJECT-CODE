package com.example.alekya.adavancecrimecomplaint;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class UserActivity extends Activity
{
@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user);
		Button btpostacrime=(Button)findViewById(R.id.btpostacrime);
		Button btsolved=(Button)findViewById(R.id.btsolvedcrimes);
		Button userhome=(Button)findViewById(R.id.userhome);
		btpostacrime.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				Intent it=new Intent(UserActivity.this,PostcrimeActivity.class);
				startActivity(it);
			}
		});
		btsolved.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				Intent it=new Intent(UserActivity.this,UsersolvedlistActivity.class);
				startActivity(it);
			}
		});

		userhome.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				Intent it=new Intent(UserActivity.this,MainActivity.class);
				startActivity(it);
			}
		});
	}
}


