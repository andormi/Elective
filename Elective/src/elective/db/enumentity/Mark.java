package elective.db.enumentity;

public enum Mark {
	A(5), B(4), C(3), D(2);
	
	int value;
	
	Mark(){
	}
	
	Mark(int value) {
		this.value = value;
	}
	
//	public void setValue()
	
	public int getValue() {
		return value;
	}

}
