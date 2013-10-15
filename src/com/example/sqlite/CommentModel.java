package com.example.sqlite;

public class CommentModel {

	/**
	 * CONSTRUCTORS
	 */
	public CommentModel(){
		
	}
	public CommentModel(int id, String comment){
		this.id = id;
		this.comment = comment;
	}
	
	/**
	 * METHOD
	 */
	/**
	 * set id 
	 * @param id
	 */
	public void setId(int id){
		this.id = id;
	}
	/**
	 * get id
	 * @return
	 */
	public int getId(){
		return this.id;
	}
	
	/**
	 * set comment
	 * @param comment
	 */
	public void setComment(String comment){
		this.comment = comment;
	}
	/**
	 * get comment
	 * @return
	 */
	public String getComment(){
		return this.comment;
	}
	/**
	 * set note
	 * @param note
	 */
	public void setNote(String note){
		this.note = note;
	}
	
	/**
	 * get note
	 * @return
	 */
	public String getNote(){
		return this.note;
	}
	/**
	 * PROPERTIES
	 */
	
	private int id ;
	private String comment;
	private String note;
	// constant values
	public static String ID = "id";
	public static String COMMENT = "comments";
	public static String NOTE = "notes";
}
