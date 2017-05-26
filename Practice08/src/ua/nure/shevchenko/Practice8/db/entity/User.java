package ua.nure.shevchenko.Practice8.db.entity;

public class User {

	private int id;
	private String name;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public static User createUser(String name) {
		User user = new User();
		user.setName(name);
		return user;
	}

	@Override
	public String toString() {
		return "User [name=" + name + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
