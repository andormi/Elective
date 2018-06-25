package elective.db.entity;

public class Course extends Entity {

	private static final long serialVersionUID = 6775155154310168429L;

	private String name;
	private int duration;
	private long categoryId;
	private int statusId;
	private long professorId;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}
	public int getStatusId() {
		return statusId;
	}
	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}
	public long getProfessorId() {
		return professorId;
	}
	public void setProfessorId(long professorId) {
		this.professorId = professorId;
	}
	@Override
	public String toString() {
		return "Course [name=" + name
				+ ", duration=" + duration
				+ ", categoryId=" + categoryId
				+ ", statusId=" + statusId
				+ ", professorId=" + professorId
				+ ", getId()=" + getId() + "]";
	}

	
	
}
