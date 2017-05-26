package ua.nure.shevchenko.Practice10.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ua.nure.shevchenko.Practice10.db.entity.Role;
import ua.nure.shevchenko.Practice10.db.entity.User;
import ua.nure.shevchenko.util.Constants;

public class DBManager {

	static DBManager instance;

	private DBManager() {
	}

	public static DBManager getInstance() {
		if (instance == null) {
			instance = new DBManager();
		}
		return instance;
	}

	public List<User> findAllUsers() {
		Connection con = null;
		List<User> res = new ArrayList<User>();
		try {
			try {
				DriverManager.registerDriver(new org.apache.derby.jdbc.EmbeddedDriver ());
			    con = DriverManager.getConnection(Constants.CONNECTION_URL);
			} catch (SQLException e) {
			    throw new RuntimeException(e);
			}
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(Constants.SQL_FIND_ALL_USERS);
			while (rs.next()) {
				User temp = new User();
				temp.setLogin(rs.getString(2));
				temp.setPassword(rs.getString(3));
				temp.setName(rs.getString(4));
				temp.setRoleId(rs.getInt(5));
				temp.setId(rs.getInt(1));
				res.add(temp);
			}
			rs.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			close(con);
		}
		return res;
	}

	public List<Role> findAllRoles() {
		Connection con = null;
		List<Role> res = new ArrayList<Role>();
		try {
			con = DriverManager.getConnection(Constants.CONNECTION_URL);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(Constants.SQL_FIND_ALL_ROLES);
			while (rs.next()) {
				Role temp = new Role();
				temp.setName(rs.getString(2));
				temp.setId(rs.getInt(1));
				res.add(temp);
			}
			rs.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			close(con);
		}
		return res;
	}

	public void updateUser(User user) {
		Connection con = null;
		try {
			con = DriverManager.getConnection(Constants.CONNECTION_URL);
			PreparedStatement pstm = con
					.prepareStatement(Constants.SQL_UPDATE_GROUP);
			int k = 1;
			pstm.setString(k++, user.getName());
			pstm.setInt(k++, user.getId());
			pstm.executeUpdate();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			close(con);
		}
	}

	public static void rollback(Connection con) {
		try {
			con.rollback();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}

	public static void close(Connection con) {
		try {
			con.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}
}
