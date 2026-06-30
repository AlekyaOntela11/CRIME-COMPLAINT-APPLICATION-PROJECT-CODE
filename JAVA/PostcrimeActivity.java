package com.example.alekya.adavancecrimecomplaint;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class PostcrimeActivity extends Activity {
	SQLiteDatabase db;
	ImageView iv;
	int i=1,j=2;
	CharSequence[] items={
			"Camera","Choose from Gallery"
	};
	Button date;
	final int k=100;
	int year,month,day;
	String s2,str,selectType;
	Bitmap b;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_postcrime);
		db=openOrCreateDatabase("CRIME COMPLAINT APPLICATION", MODE_PRIVATE, null);
		db.execSQL("create table if not exists postcrime(image varchar,date varchar(10),place varchar(20),description varchar(300),status varchar(20))");

		iv=(ImageView)findViewById(R.id.crimeimg);
		final EditText etplace=(EditText)findViewById(R.id.etplace);
		final EditText etdescription=(EditText)findViewById(R.id.etdescription);
	    date=(Button)findViewById(R.id.btdate);
	    Calendar c=Calendar.getInstance();
        year=c.get(Calendar.YEAR);
        month=c.get(Calendar.MONTH);
        day=c.get(Calendar.DAY_OF_MONTH);

        SimpleDateFormat s=new SimpleDateFormat("dd-MM-yyyy");
        Date d=new Date();
        String s1=s.format(d);
        date.setText(s1);


	    Button post=(Button)findViewById(R.id.postc);


	    post.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				String sdate=date.getText().toString();
				String splace=etplace.getText().toString();
				String sdes=etdescription.getText().toString();
				String sstatus="crime";
				if(selectType.equals("Camera"))
				{
					Toast.makeText(getApplicationContext(), " Camera @@@@@@@@@@@@@@@@@   "+b, 100).show();
				db.execSQL("insert into postcrime values('"+b+"','"+sdate+"','"+splace+"','"+sdes+"','"+sstatus+"')");
				}
				else if(selectType.equals("Gallery"))
				{
					Toast.makeText(getApplicationContext(), " Gallery @@@@@@@@@@@@@@@@@@@@@@@@@@@@   "+str, 100).show();
					db.execSQL("insert into postcrime values('"+str+"','"+sdate+"','"+splace+"','"+sdes+"','"+sstatus+"')");
				}

				Toast.makeText(getApplicationContext(), "Thanks for the information!!",Toast.LENGTH_SHORT).show();
				Intent it=new Intent(PostcrimeActivity.this,PostcrimeActivity.class);
				startActivity(it);
			}
		});
	    date.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0)
			{
				showDialog(k);

			}
			});

	    iv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				AlertDialog.Builder ab=new AlertDialog.Builder(PostcrimeActivity.this);
				ab.setIcon(R.drawable.ic_launcher);
				ab.setTitle("Open from..");
				ab.setItems(items, new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface arg0, int arg1) {

					if(items[arg1].equals("Camera")){
						selectType="Camera";
						Intent it=new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
						startActivityForResult(it, i);
					}
					else if (items[arg1].equals("Choose from Gallery")) {
						selectType="Gallery";
						Intent it=new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
						startActivityForResult(it, j);
					}
					}
				});
				ab.create();
				ab.show();
			}
		});
    }
   @Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {

	super.onActivityResult(requestCode, resultCode, data);
	if(requestCode==i){
		if(resultCode==RESULT_OK){
			b=(Bitmap)data.getExtras().get("data");
			iv.setImageBitmap(b);
		}
	}
	if(requestCode==j)
	{
		if(resultCode==RESULT_OK){
			Uri u=data.getData();
			String[] s={
					MediaStore.Images.Media.DATA
			};
			Cursor c=getContentResolver().query(u, s, null, null, null);
			c.moveToFirst();
			if(c.getCount()>0){
				int k=c.getColumnIndex(s[0]);
				str=c.getString(k);
				iv.setImageBitmap(BitmapFactory.decodeFile(str));
			}
		}

	}


	}
   @Override
   @Deprecated
   protected Dialog onCreateDialog(int id) {

   	switch(id){
   	case k:
   		return new DatePickerDialog(this, datepickerdialog, year, month, day);

   	}
   	return super.onCreateDialog(id);
   }
   DatePickerDialog.OnDateSetListener datepickerdialog=new DatePickerDialog.OnDateSetListener() {

		@Override
		public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

			StringBuilder sb=new StringBuilder();
			sb.append(dayOfMonth).append("-").append(month+1).append("-").append(year);
			s2=sb.toString();
			date.setText(s2);


		}


	};

}



