package com.example.alekya.adavancecrimecomplaint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btlogin=(Button)findViewById(R.id.buttonlogin);
        btlogin.setOnClickListener(new OnClickListener()
            {
     @Override

			public void onClick(View arg0)
			 {
         Intent it=new Intent(MainActivity.this,LoginActivity.class);
         startActivity(it);
             }
            });


    }
}

