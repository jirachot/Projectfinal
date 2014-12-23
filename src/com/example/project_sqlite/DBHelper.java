package com.example.project_sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {

	
	private static final int DB_VERSION = 1;
	private static final String DB_NAME = "project";
	//private static final String TABLE_NAME = "member";
	
	private static final String DB_CREATE = "" +
			"CREATE TABLE member (" +
            "id INTEGER PRIMARY KEY, " +
            "fname TEXT(100), " +
            "lname TEXT(100), " +
            "age INTEGER, "+
            "address TEXT(150), "+
            "blood TEXT(10), "+
            "weight TEXT(10), "+
            "height TEXT(10));";
	public DBHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
		
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(DB_CREATE);
		
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(DBHelper.class.getName(),
                "Upgread database version from version" + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
		
		db.execSQL("DROP TABLE IF EXISTS member");
		onCreate(db);
	}

}
