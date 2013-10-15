package com.example.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySqliteHelper extends SQLiteOpenHelper{

	// Variables
	public static final String 	TAG = "MySqliteHelper";
	public static final String DATABASE_NAME = "Comments.db";
	public static final String  TABLE_NAME = "Comment";
	public static final String 	TABLE_COLUMN_ID = "id";
	public static final String  TABLE_COLUMN_COMMENTS = "comments";
	public static final String TABLE_COLUMN_NOTE = "notes";
	
	public static final int VERSION = 2;
	
	// database creation sql command 
	public static final String CREATE_TABLE = "create table " + TABLE_NAME + "( "
												+ TABLE_COLUMN_ID + " integer primary key autoincrement, " 
												+ TABLE_COLUMN_COMMENTS + " text not null);";
	
	public MySqliteHelper(Context context) {
		super(context, DATABASE_NAME, null, VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(CREATE_TABLE);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		//db.execSQL("ALTER TABLE " + TABLE_NAME + " ADD " + TABLE_COLUMN_NOTE + " text" );
		db.execSQL("ALTER TABLE " + TABLE_NAME + " ADD " + TABLE_COLUMN_NOTE + " text null");
		//onCreate(db);
	}

}
