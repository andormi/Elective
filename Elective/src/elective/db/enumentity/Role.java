package elective.db.enumentity;

import elective.db.entity.User;

public enum Role {
	ADMIN, LECTURER, STUDENT;
	
	public static Role getRole(User user) {
		int roleId = user.getRoleId();
		return Role.values()[roleId];
	}
	
	public String getName() {
		return name().toLowerCase();
	}
	
	
}
