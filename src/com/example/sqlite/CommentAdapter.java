package com.example.sqlite;

import java.util.ArrayList;
import java.util.HashMap;


import android.R.layout;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CommentAdapter extends ArrayAdapter<CommentModel>{

	// VARIABLES
	public static final String TAG = "CommentAdapter";
	ArrayList<CommentModel> comments = new ArrayList<CommentModel>();
	Context context;
	HashMap<String, View> hashMap = new HashMap<String, View>();
	LayoutInflater layoutInflater;
	
	public CommentAdapter(Context context, int resource, ArrayList<CommentModel> data) {
		super(context, resource, data);
		// TODO Auto-generated constructor stub
		this.context = context;
		this.comments = data;
		this.layoutInflater = LayoutInflater.from(context);
		
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
//		return super.getView(position, convertView, parent);
		CommentModel comment = comments.get(position);
		
		if(comment == null) 
			return null;
		
		
		String key = String.valueOf(comment.getId()) + position;
		View v = hashMap.get(key);
		if(v == null){
			ViewHolder holder = new ViewHolder();
			v = layoutInflater.inflate(R.layout.template_itemlistview, parent, false);
			
			holder.textview = (TextView) v.findViewById(R.id.textView1);
			
			holder.textview.setText(comment.getComment() + " Note: " + comment.getNote());
			
			
			v.setTag(holder);
			hashMap.put(key, v);
			
		}
		
		
		return hashMap.get(key);
	}
	
	@Override
	public CommentModel getItem(int position) {
		// TODO Auto-generated method stub
		return comments.get(position);
	}
	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return comments.size();
	}
	
	@Override
	public void add(CommentModel comment) {
		// TODO Auto-generated method stub
		comments.add(comment);
	}
	
	public void addAll(ArrayList<CommentModel> listcomment ) {
		// TODO Auto-generated method stub
		comments.addAll(listcomment);
	}
	
	public class ViewHolder{
		TextView textview;
	}

}
