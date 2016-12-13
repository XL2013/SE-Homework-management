package com.se.pojo;

/**
 * 	status:{
 * 		0:"缺席"
 * 		1："已点到"
 * }
 */
public class StudentRollCall {
	
	private String course_id;
	private int roll_order;
	private int status;
	private String student_id;
	public String getCourse_id() {
		return course_id;
	}
	public void setCourse_id(String course_id) {
		this.course_id = course_id;
	}
	public int getRoll_order() {
		return roll_order;
	}
	public void setRoll_order(int roll_order) {
		this.roll_order = roll_order;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getStudent_id() {
		return student_id;
	}
	public void setStudent_id(String student_id) {
		this.student_id = student_id;
	}
	
	
}
