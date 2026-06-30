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
import android.widget.EditText;
import android.widget.Toast;

public class SignupActivity extends Activity {
	SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_signup);
		db=openOrCreateDatabase("CRIME COMPLAINT APPLICATION", MODE_PRIVATE,null);
		final EditText etuser=(EditText)findViewById(R.id.username);
		final EditText etmob=(EditText)findViewById(R.id.mobile);
		final EditText etemail=(EditText)findViewById(R.id.email);
		final EditText etpassword=(EditText)findViewById(R.id.password);
        final EditText etaddress=(EditText)findViewById(R.id.address);
		Button btsignin=(Button)findViewById(R.id.signup);
		db.execSQL("create table if not exists reg1(username varchar(30),mobile varchar(10),email varchar(30),password varchar(15),address varchar(30))");
		btsignin.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				String username=etuser.getText().toString();
				String mobile=etmob.getText().toString();
				String email=etemail.getText().toString();
				String password=etpassword.getText().toString();
                String address=etaddress.getText().toString();
				if(username.equals("")){
					Toast.makeText(getApplicationContext(), "Please enter User Name",Toast.LENGTH_SHORT).show();
				}
				else if (mobile.equals("")) {
					Toast.makeText(getApplicationContext(), "Please enter Mobile Number",Toast.LENGTH_SHORT).show();
				}
				else if (email.equals("")) {
					Toast.makeText(getApplicationContext(), "Please enter Email ID",Toast.LENGTH_SHORT).show();
				}
				else if (password.equals("")) {
					Toast.makeText(getApplicationContext(), "Please enter password",Toast.LENGTH_SHORT).show();
				}
                else if (address.equals("")) {
                    Toast.makeText(getApplicationContext(), "Please enter address",Toast.LENGTH_SHORT).show();
                }
				else{
					db.execSQL("insert into reg1 values('"+username+"','"+mobile+"','"+email+"','"+password+"','"+address+"')");
					Toast.makeText(getApplicationContext(), "Sign Up Successful!!! ",Toast.LENGTH_SHORT).show();
					Intent it=new Intent(SignupActivity.this,LoginActivity.class);
					startActivity(it);
				}
			}
		});

	}
}

