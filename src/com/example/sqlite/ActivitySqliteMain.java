package com.example.sqlite;

import java.util.ArrayList;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ListActivity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ActivitySqliteMain extends ListActivity implements OnClickListener{

	/**
	 * VARIABLES
	 */
	public static final String TAG = "ActivitySqliteMain";
	
	CommentDataSource commentDataSource;
	ArrayList<CommentModel> comments;
	//ArrayAdapter<CommentModel> adapter;
	EditText editText_InputComment;
	CommentAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_activity_sqlite_main);
		
		// get Control
		editText_InputComment = (EditText) findViewById(R.id.editText_InputComment);
		Button btn_InsertComment = (Button) findViewById(R.id.btn_InsertComment);
		Button btn_DeleteComment = (Button) findViewById(R.id.btn_DeleteComment);
		// set event for button
		btn_InsertComment.setOnClickListener(this);
		btn_DeleteComment.setOnClickListener(this);
		
		//get data from table
		commentDataSource = new CommentDataSource(this);
		commentDataSource.open();
		
		comments = new ArrayList<CommentModel>();
		comments = commentDataSource.getAllComment();
		
		// set adapter for listview
		
		//adapter = new ArrayAdapter<CommentModel>(this, android.R.layout.simple_list_item_1, comments);
		adapter = new CommentAdapter(this, R.layout.template_itemlistview, comments);
		setListAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_sqlite_main, menu);
		return true;
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		commentDataSource.open();
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		commentDataSource.close();
	}
	
	@SuppressLint("NewApi")
	@Override
	public void onClick(View v) {

		
		if(v.getId() == R.id.btn_InsertComment){
		
			// insert new comment
			String commentstr = editText_InputComment.getText().toString();
			// if it has inputed comment, it will be inserted to database
			if(!commentstr.isEmpty()){
				long insertId = commentDataSource.insertComment(commentstr);
				CommentModel comment = new CommentModel();
				comment = commentDataSource.getCommentById(insertId);
				
				//adapter.add(comment);
				comments.add(comment);
				adapter.notifyDataSetChanged();
			}
			else{
				Toast.makeText(getApplicationContext(), "Please input the comment", Toast.LENGTH_LONG).show();
			}
		}
		else if(v.getId() == R.id.btn_DeleteComment){
			// delete first comment
			if(getListAdapter().getCount() > 0){
				CommentModel comment = (CommentModel) getListAdapter().getItem(0);
				commentDataSource.deleteComment(comment);
				
				adapter.remove(comment);
				comments.remove(comment);
				adapter.notifyDataSetChanged();
			}
		}
	}

}
