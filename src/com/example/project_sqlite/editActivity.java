package com.example.project_sqlite;

import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class editActivity extends Activity {

	private EditText edtfname, edtlname, edtage, edtaddress, edtblood, edtweight, edtheight;
	private Button btnedit;
	
	private int id;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit);
		
		edtfname = (EditText) findViewById(R.id.edtFname);
		edtlname = (EditText) findViewById(R.id.edtLname);
		edtage = (EditText) findViewById(R.id.edtAge1);
		edtaddress = (EditText) findViewById(R.id.edtAddress1);
		edtblood = (EditText) findViewById(R.id.edtBlood1);
		edtweight = (EditText) findViewById(R.id.edtWeight1);
		edtheight = (EditText) findViewById(R.id.edtHeight1);
		btnedit = (Button) findViewById(R.id.btnEdit);
		
		this.id = getIntent().getExtras().getInt("keyid");
		edtfname.setText(getIntent().getExtras().getString("keyfname"));
		edtlname.setText(getIntent().getExtras().getString("keylname"));
		edtage.setText(""+getIntent().getExtras().getInt("keyage"));
		edtaddress.setText(getIntent().getExtras().getString("keyaddress"));
		edtblood.setText(getIntent().getExtras().getString("keybloodgroup"));
		edtweight.setText(getIntent().getExtras().getString("keyweight"));
		edtheight.setText(getIntent().getExtras().getString("keyheight"));
		
		btnedit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent i = new Intent();
				
				setResult(RESULT_OK, i);
				
				i.putExtra("keyid", id);
				i.putExtra("keyfname", edtfname.getText().toString());
				i.putExtra("keylname", edtlname.getText().toString());
				i.putExtra("keyage", Integer.parseInt(edtage.getText().toString()));
				i.putExtra("keyaddress", edtaddress.getText().toString());
				i.putExtra("keybloodgroup", edtblood.getText().toString());
				i.putExtra("keyweight", edtweight.getText().toString());
				i.putExtra("keyheight", edtheight.getText().toString());
				
				finish();
			}
		});
	}
}
