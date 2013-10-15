package com.example.sqlite;

import java.util.ArrayList;

import org.w3c.dom.Comment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class CommentDataSource {

	/**
	 * VARIABLES
	 */
	public static final String TAG = "CommentDataSource";
	MySqliteHelper dbHelper;
	SQLiteDatabase db;
	String [] allColumn = {MySqliteHelper.TABLE_COLUMN_ID, MySqliteHelper.TABLE_COLUMN_COMMENTS, MySqliteHelper.TABLE_COLUMN_NOTE};
	
	
	/**
	 * CONSTRUCTOR
	 */
	public CommentDataSource(Context context){
		dbHelper = new MySqliteHelper(context);
	}
	
	/**
	 * open
	 */
	public void open(){
		db = dbHelper.getWritableDatabase();
	}
	
	/**
	 * close
	 */
	public void close(){
		db.close();
	}
	
	/**
	 * insert new comment to table COMMENT
	 * @param comment
	 * @return
	 */
	public long  insertComment(String comment){
		ContentValues value = new ContentValues();
		value.put(MySqliteHelper.TABLE_COLUMN_COMMENTS, comment);
		// update
		value.put(MySqliteHelper.TABLE_COLUMN_NOTE, comment);
				 		
		return db.insert(MySqliteHelper.TABLE_NAME, null, value);
	}
	
	/**
	 * delete comment by id 
	 * @param id
	 */
	public void deleteComment(int id){
		
		db.delete(MySqliteHelper.TABLE_NAME, MySqliteHelper.TABLE_COLUMN_ID + "=" + id, null );
	}
	
	/**
	 * delete comment by comment object
	 * @param comment
	 */
	public void deleteComment(CommentModel comment){
		int commentId = comment.getId();
		deleteComment(commentId);
	}
	
	/**
	 * get All comment from table 
	 * 
	 * @return 
	 */
	public ArrayList<CommentModel> getAllComment(){
		
		ArrayList<CommentModel> arr = new ArrayList<CommentModel>();
		
		Cursor cursor = db.query(MySqliteHelper.TABLE_NAME, allColumn, null, null , null , null , null );
		
		if(cursor.getCount() > 0){
			while(cursor.moveToNext()){
				CommentModel comment = cursorToComment(cursor);
				Log.i(TAG, String.valueOf(comment.getId())+":"+ comment.getComment());
				arr.add(comment);
			}
		}
		cursor.close();
		return arr;
		
	}
	
	/**
	 * get comment by id from database
	 * @param id
	 * @return
	 */
	public CommentModel getCommentById(long id){
		CommentModel comment = new CommentModel();
		
		Cursor cursor = db.query(MySqliteHelper.TABLE_NAME, allColumn, MySqliteHelper.TABLE_COLUMN_ID + "=" + id, null, null, null, null);
		if(cursor.moveToFirst()){
			 comment.setId(cursor.getInt(0));
			 comment.setComment(cursor.getString(1));
			 comment.setNote(cursor.getString(2));
		}
		
		return comment;
		
	}
	/**
	 * get data from cursor
	 * 
	 * @param cursor
	 * @return
	 */
	public CommentModel cursorToComment(Cursor cursor){
		CommentModel comment = new CommentModel();
		
		comment.setId(cursor.getInt(0));
		comment.setComment(cursor.getString(1));
		comment.setNote(cursor.getString(2));
		
		return comment;
	}
}
