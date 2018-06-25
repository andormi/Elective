package elective.db.bean;

import elective.db.entity.Entity;

public class StudentCourseBean extends Entity {

	private static final long serialVersionUID = 7621688437012648970L;

	private Long courseId;
	private String courseName;
	private String mark;
	private String status;
	private String professorLastName;
	
	public long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}
	
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getMark() {
		return mark;
	}
	public void setMark(String mark) {
		this.mark = mark;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getProfessorLastName() {
		return professorLastName;
	}
	public void setProfessorLastName(String professorLastName) {
		this.professorLastName = professorLastName;
	}
	
	@Override
	public String toString() {
		return "StudentCourseBean [courseName=" + courseName
				+ ", mark=" + mark
				+ ", status=" + status
				+ ", professorLastName=" + professorLastName
				+ ", getId()=" + getId() + "]";
	}
	
}
