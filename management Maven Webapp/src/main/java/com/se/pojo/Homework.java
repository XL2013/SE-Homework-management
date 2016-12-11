package com.se.pojo;

public class Homework {
		private String  course_id;
		private String  homework_id;
		private String  homework_name;
		private String  description;
		private String upload_time;
		private String release_time;
		private double ratio;
		public String getCourse_id() {
			return course_id;
		}
		public void setCourse_id(String course_id) {
			this.course_id = course_id;
		}
		public String getHomework_id() {
			return homework_id;
		}
		public void setHomework_id(String homework_id) {
			this.homework_id = homework_id;
		}
		public String getHomework_name() {
			return homework_name;
		}
		public void setHomework_name(String homework_name) {
			this.homework_name = homework_name;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}

		public double getRatio() {
			return ratio;
		}
		public void setRatio(double ratio) {
			this.ratio = ratio;
		}
		public String getUpload_time() {
			return upload_time;
		}
		public void setUpload_time(String upload_time) {
			this.upload_time = upload_time;
		}
		public String getRelease_time() {
			return release_time;
		}
		public void setRelease_time(String release_time) {
			this.release_time = release_time;
		}
		
		
}
