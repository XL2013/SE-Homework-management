package com.se.pojo;

/**
 * 课程表
 * 每一位属性对应课程表的每一列
 * @since 2016/11/11
 * @author zzs
 */
public class Course {
		private String course_id;
		private String course_name;
		private String teacher_id;
		private String description;
		public String getCourse_id() {
			return course_id;
		}
		public void setCourse_id(String course_id) {
			this.course_id = course_id;
		}
		public String getCourse_name() {
			return course_name;
		}
		public void setCourse_name(String course_name) {
			this.course_name = course_name;
		}
		public String getTeacher_id() {
			return teacher_id;
		}
		public void setTeacher_id(String teacher_id) {
			this.teacher_id = teacher_id;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		
}
