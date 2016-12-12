package com.se.pojo;

/**
 * status:{
 * 		0:未提交,
 * 		1：已提交,
 * 		2：已批改
 * }
 * @author zzs
 *
 */
public class TeamHomework {
		private String team_id;
		private String homework_id;
		private String submit_time;
		private String submitter;
		private int status;
		private int grade;
		private String correctInfo;
		private String student_comment;
		public String getTeam_id() {
			return team_id;
		}
		public void setTeam_id(String team_id) {
			this.team_id = team_id;
		}
		public String getHomework_id() {
			return homework_id;
		}
		public void setHomework_id(String homework_id) {
			this.homework_id = homework_id;
		}
		public String getSubmit_time() {
			return submit_time;
		}
		public void setSubmit_time(String submit_time) {
			this.submit_time = submit_time;
		}
		public String getSubmitter() {
			return submitter;
		}
		public void setSubmitter(String submitter) {
			this.submitter = submitter;
		}
		public int getStatus() {
			return status;
		}
		public void setStatus(int status) {
			this.status = status;
		}
		public int getGrade() {
			return grade;
		}
		public void setGrade(int grade) {
			this.grade = grade;
		}
		public String getCorrectInfo() {
			return correctInfo;
		}
		public void setCorrectInfo(String correctInfo) {
			this.correctInfo = correctInfo;
		}
	
		public String getStudent_comment() {
			return student_comment;
		}
		public void setStudent_comment(String student_comment) {
			this.student_comment = student_comment;
		}
		
		
}
