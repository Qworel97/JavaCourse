package ua.nure.shevchenko.util;

public class Constants {
	public final static String CONNECTION_URL = "jdbc:derby://localhost:1527/st4db;create=true;user=test;password=test";

	public final static String SQL_ADD_USER = "INSERT INTO users VALUES (DEFAULT,?)";
	public final static String SQL_ADD_GROUP = "INSERT INTO groups VALUES (DEFAULT,?)";
	public final static String SQL_ADD_USER_GROUP = "INSERT INTO users_groups VALUES (DEFAULT,?,?)";

	public final static String SQL_FIND_USER_BY_NAME = "SELECT * FROM users WHERE name=?";
	public final static String SQL_FIND_USER_BY_ID = "SELECT * FROM users WHERE id=?";
	public final static String SQL_FIND_GROUP_BY_NAME = "SELECT * FROM groups WHERE name=?";
	public final static String SQL_FIND_GROUP_BY_ID = "SELECT * FROM groups WHERE id=?";
	public final static String SQL_FIND_GROUPS_OF_USER = "SELECT group_name FROM users_groups WHERE user_name=?";
	public final static String SQL_FIND_ALL_USERS = "SELECT * FROM users";
	public final static String SQL_FIND_ALL_GROUPS = "SELECT * FROM groups";
	public final static String SQL_FIND_ALL_ROLES = "SELECT * FROM roles";
	
	public final static String SQL_UPDATE_USER = "UPDATE users SET name=? WHERE id=?";
	public final static String SQL_UPDATE_GROUP = "UPDATE groups SET name=? WHERE id=?";
	
	public final static String SQL_DELETE_GROUP = "DELETE FROM groups WHERE id=?";

}