package elective.db.entity;

public class Category extends Entity {

	private static final long serialVersionUID = 1571316127629884065L;

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Category [name=" + name
				+ ", getId()=" + getId() + "]";
	}

	
	
}
