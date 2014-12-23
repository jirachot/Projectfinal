package com.example.project_sqlite;

import java.util.ArrayList;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class indexdoc extends Activity {

	private EditText edtfname, edtlname, edtage, edtadd, edtblood, edtwei, edthei;
	private Button btnadd;
	private ListView listmember;
	
	private ArrayList<MemberData> listData = new ArrayList<MemberData>();
	
	private DBHelper dbhelper;
	private SQLiteDatabase database;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.indexdoc);
		
		edtfname = (EditText) findViewById(R.id.edtfname);
		edtlname = (EditText) findViewById(R.id.edtlname);
		edtage = (EditText) findViewById(R.id.edtAge);
		edtadd = (EditText) findViewById(R.id.edtAddress);
		edtblood = (EditText) findViewById(R.id.edtblood);
		edtwei = (EditText) findViewById(R.id.edtweight);
		edthei = (EditText) findViewById(R.id.edtheight);
		btnadd = (Button) findViewById(R.id.btnEdit);
		listmember = (ListView) findViewById(R.id.listMember);
		
		btnadd.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				addMember();
				
			}
		});
		
		dbhelper = new DBHelper(this);
		database = dbhelper.getWritableDatabase();
		showlist();
	}
	public void editmember(int id, String fname, String lname, int age, String address, String bloodgroup, String weight, String height){
		ContentValues values = new ContentValues();
		values.put("id", id);
		values.put("fname", fname);
		values.put("lname", lname);
		values.put("age", age);
		values.put("address", address);
		values.put("blood", bloodgroup);
		values.put("weight", weight);
		values.put("height", height);
		
		database.update("member", values, "id = ?", new String[]{""+id});
		showlist();
	}
	public void deletemember(int id){
		database.delete("member", "id = " + id, null);
		Toast.makeText(this, "Delete Data Id" + id + "Complete", Toast.LENGTH_SHORT).show();
		showlist();
	}
	private void getMember(){
		//ทำการ query ข้อมูลจากตาราง ใส่ใน Cursor
		Cursor cursor = database.query(true, "member", new String[] {
			"id", "fname", "lname", "age", "address", "blood", "weight", "height"}, null,
			null, null, null, null, null);
		
		if(cursor != null){
			cursor.moveToFirst();
			
			listData.clear();
			
			if(cursor.getCount() >0){
				do{
					int id = cursor.getInt(cursor.getColumnIndex("id"));
					String fname = cursor.getString(cursor.getColumnIndex("fname"));
					String lname = cursor.getString(cursor.getColumnIndex("lname"));
					int age = cursor.getInt(cursor.getColumnIndex("age"));
					//String status = cursor.getString(cursor.getColumnIndex("status"));
					String address = cursor.getString(cursor.getColumnIndex("address"));
					String bloodgroup = cursor.getString(cursor.getColumnIndex("blood"));
					String weight = cursor.getString(cursor.getColumnIndex("weight"));
					String height = cursor.getString(cursor.getColumnIndex("height"));
					
					listData.add(new MemberData(id, fname, lname, age, address, bloodgroup, weight, height));
				}while (cursor.moveToNext());
			}
		}
	}
	
	private void addMember(){
		ContentValues values = new ContentValues();
		values.put("fname", edtfname.getText().toString());
		values.put("lname", edtlname.getText().toString());
		values.put("age", edtage.getText().toString());
		//values.put("status", "member");
		values.put("address", edtadd.getText().toString());
		values.put("blood", edtblood.getText().toString());
		values.put("weight", edtwei.getText().toString());
		values.put("height", edthei.getText().toString());
		
		database.insert("member", null, values);
		Toast.makeText(this, "Add Data Complete", Toast.LENGTH_SHORT).show();
		
		edtfname.setText("");
		edtlname.setText("");
		edtage.setText("");
		edtadd.setText("");
		edtblood.setText("");
		edtwei.setText("");
		edthei.setText("");
		
		showlist();
	}
	
	public void showlist(){
		getMember();
		
		listmember.setAdapter(new AdapterListViewData(this, listData));
	}
	
	public void showEdit(int id, String fname, String lname, int age, String address, String bloodgroup, String weight, String height){
		Intent i = new Intent(this, editActivity.class);
		
		i.putExtra("keyid", id);
		i.putExtra("keyfname", fname);
		i.putExtra("keylname", lname);
		i.putExtra("keyage", age);
		i.putExtra("keyaddress", address);
		i.putExtra("keybloodgroup", bloodgroup);
		i.putExtra("keyweight", weight);
		i.putExtra("keyheight", height);
		
		startActivityForResult(i, 1);
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent intent){
		if(requestCode == 1 && resultCode == RESULT_OK){
			
			int id = intent.getExtras().getInt("keyid");
			String fname = intent.getExtras().getString("keyfname");
			String lname = intent.getExtras().getString("keylname");
			int age = intent.getExtras().getInt("keyage");
			String address = intent.getExtras().getString("keyaddress");
			String bloodgroup = intent.getExtras().getString("keybloodgroup");
			String weight = intent.getExtras().getString("keyweight");
			String height = intent.getExtras().getString("keyheight");
			
			editmember(id, fname, lname, age, address, bloodgroup, weight, height);	
		}
	}
}

