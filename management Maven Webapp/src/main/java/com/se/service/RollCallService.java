package com.se.service;

public interface RollCallService {
	
	int getCourseRollCallTotals(String course_id);
	int getAbsenceTimes(String student_id,String course_id);
	int getStudentRollCallTotals(String course_id,String student_id);
	
	void addRollCallSetting(String course_id,int total);
}
