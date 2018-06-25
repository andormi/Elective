package elective.db.enumentity;

public enum Status {
	FINISHED, PROGRESS, EXPECTED;
	
	public String getName() {
		return name().toLowerCase();
	}
}
