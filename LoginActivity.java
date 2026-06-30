package com.example.alekya.adavancecrimecomplaint;
import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class LoginActivity extends Activity
{
SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	 {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		db=openOrCreateDatabase("CRIME COMPLAINT APPLICATION", MODE_PRIVATE,null);
		Button btlogin=(Button)findViewById(R.id.login);
		Button btsignup=(Button)findViewById(R.id.btsignup);
		final EditText etuser=(EditText)findViewById(R.id.etuser);
		final EditText etpass=(EditText)findViewById(R.id.etpass);
		btsignup.setOnClickListener(new OnClickListener()
		 {

			@Override
			public void onClick(View arg0)
			    {

				Intent it=new Intent(LoginActivity.this,SignupActivity.class);
				startActivity(it);
		    	}
		});
		btlogin.setOnClickListener(new OnClickListener()
		 {

			@Override
			public void onClick(View arg0)
			{
			    String suser=etuser.getText().toString();
				String spass=etpass.getText().toString();
				if(suser.equals(""))
				{
					Toast.makeText(getApplicationContext(), "Please enter User Name", Toast.LENGTH_SHORT).show();
				}
				else if (spass.equals(""))
				{
					Toast.makeText(getApplicationContext(), "Please enter Password", Toast.LENGTH_SHORT).show();
				}
				else if (suser.equals("Admin")&&spass.equals("12345"))
				{
					Intent it=new Intent(LoginActivity.this,AdminActivity.class);
					startActivity(it);
				}
				else
				    {
				Cursor c=db.rawQuery("select * from reg1 where username='"+suser+"' and password='"+spass+"'",null);
				c.moveToFirst();
				if(c.getCount()>0)
				{
					Toast.makeText(getApplicationContext(),"Login Succesful", Toast.LENGTH_SHORT).show();
					Intent it=new Intent(LoginActivity.this,UserActivity.class);
					startActivity(it);
				}
				else
				    {
					Toast.makeText(getApplicationContext(), "Please enter valid details",Toast.LENGTH_SHORT).show();

				}
				    }
				etuser.setText("");
				etpass.setText("");

		}
		});
	}
}
