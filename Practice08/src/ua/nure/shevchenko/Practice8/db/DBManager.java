package ua.nure.shevchenko.Practice8.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ua.nure.shevchenko.Practice8.db.entity.Group;
import ua.nure.shevchenko.Practice8.db.entity.User;
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

	public void insertUser(User newUser) {
		Connection con = null;
		try {
			con = DriverManager.getConnection(Constants.CONNECTION_URL);
			PreparedStatement pstm = con.prepareStatement(Constants.SQL_ADD_USER, Statement.RETURN_GENERATED_KEYS);
			int k = 1;
			pstm.setString(k++, newUser.getName());
			pstm.executeUpdate();
			ResultSet rs = pstm.getGeneratedKeys();
			if (rs.next()) {
				newUser.setId(rs.getInt(1));
			}
			rs.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			close(con);
		}
	}

	public List<User> findAllUsers() {
		Connection con = null;
		List<User> res = new ArrayList<User>();
		try {
			con = DriverManager.getConnection(Constants.CONNECTION_URL);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(Constants.SQL_FIND_ALL_USERS);
			while (rs.next()) {
				User temp = User.createUser(rs.getString(2));
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

	public void insertGroup(Group newGroup) {
		Connection con = null;
		try {
			con = DriverManager.getConnection(Constants.CONNECTION_URL);
			PreparedStatement pstm = con.prepareStatement(Constants.SQL_ADD_GROUP, Statement.RETURN_GENERATED_KEYS);
			int k = 1;
			pstm.setString(k++, newGroup.getName());
			pstm.executeUpdate();
			ResultSet rs = pstm.getGeneratedKeys();
			if (rs.next()) {
				newGroup.setId(rs.getInt(1));
			}
			rs.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			close(con);
		}
	}

	public List<Group> findAllGroups() {
		Connection con = null;
		List<Group> res = new ArrayList<Group>();
		try {
			con = DriverManager.getConnection(Constants.CONNECTION_URL);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(Constants.SQL_FIND_ALL_GROUPS);
			while (rs.next()) {
				Group temp = Group.createGroup(rs.getString(2));
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

	public User getUser(int id) {
		Connection con = null;
		User res = null;
		try {
			con = DriverManager.getConnection(Constants.CONNECTION_URL);
			PreparedStatement pstm = con.prepareStatement(Constants.SQL_FIND_USER_BY_ID,
					Statement.RETURN_GENERATED_KEYS);
			int k = 1;
			pstm.setInt(k++, id);
			ResultSet rs = pstm.executeQuery();
			if (rs.next()) {
				res = User.createUser(rs.getString(2));
				res.setId(rs.getInt(1));
			}
			rs.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			close(con);
		}
		return res;
	}
	
	public User getUser(String name) {
		Connection con = null;
		User res = null;
		try {
			con = DriverManager.getConnection(Constants.CONNECTION_URL);
			PreparedStatement pstm = con.prepareStatement(Constants.SQL_FIND_USER_BY_NAME,
					Statement.RETURN_GENERATED_KEYS);
			int k = 1;
			pstm.setString(k++, name);
			ResultSet rs = pstm.executeQuery();
			if (rs.next()) {
				res = User.createUser(rs.getString(2));
				res.setId(rs.getInt(1));
			}
			rs.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			close(con);
		}
		return res;
	}
	

	public Group getGroup(int id) {
		Connection con = null;
		Group res = null;
		try {
			con = DriverManager.getConnection(Constants.CONNECTION_URL);
			PreparedStatement pstm = con.prepareStatement(Constants.SQL_FIND_GROUP_BY_ID);
			int k = 1;
			pstm.setInt(k++, id);
			ResultSet rs = pstm.executeQuery();
			if (rs.next()) {
				res = Group.createGroup(rs.getString(2));
				res.setId(rs.getInt(1));
			}
			rs.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			close(con);
		}
		return res;
	}


	public Group getGroup(String name) {
		Connection con = null;
		Group res = null;
		try {
			con = DriverManager.getConnection(Constants.CONNECTION_URL);
			PreparedStatement pstm = con.prepareStatement(Constants.SQL_FIND_GROUP_BY_NAME);
			int k = 1;
			pstm.setString(k++, name);
			ResultSet rs = pstm.executeQuery();
			if (rs.next()) {
				res = Group.createGroup(rs.getString(2));
				res.setId(rs.getInt(1));
			}
			rs.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			close(con);
		}
		return res;
	}

	public boolean setGroupsForUser(User user, Group... teams) {
		Connection con = null;
		boolean res = false;
		try {
			con = DriverManager.getConnection(Constants.CONNECTION_URL);
			con.setAutoCommit(false);
			for (Group x : teams) {
				PreparedStatement pstm = con.prepareStatement(Constants.SQL_ADD_USER_GROUP);
				int k = 1;
				pstm.setInt(k++, user.getId());
				pstm.setInt(k++, x.getId());
				pstm.executeUpdate();
			}
			con.commit();
			res = true;
		} catch (SQLException e) {
			rollback(con);
			System.err.println(e.getMessage());
		} finally {
			close(con);
		}
		return res;
	}

	public List<String> getUserGroups(User user) {
		Connection con = null;
		List<String> res = new ArrayList<String>();
		try {
			con = DriverManager.getConnection(Constants.CONNECTION_URL);
			PreparedStatement pstm = con.prepareStatement(Constants.SQL_FIND_GROUPS_OF_USER);
			int k = 1;
			pstm.setInt(k++, user.getId());
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				Group temp = getGroup(rs.getInt(1));
				res.add(temp.getName());
			}
			rs.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			close(con);
		}
		return res;
	}

	public void deleteGroup(Group team) {
		Connection con = null;
		try {
			con = DriverManager.getConnection(Constants.CONNECTION_URL);
			PreparedStatement pstm = con.prepareStatement(Constants.SQL_DELETE_GROUP);
			int k = 1;
			pstm.setInt(k++, team.getId());
			pstm.executeUpdate();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			close(con);
		}
	}

	public void updateGroup(Group team) {
		Connection con = null;
		try {
			con = DriverManager.getConnection(Constants.CONNECTION_URL);
			PreparedStatement pstm = con.prepareStatement(Constants.SQL_UPDATE_GROUP);
			int k = 1;
			pstm.setString(k++, team.getName());
			pstm.setInt(k++, team.getId());
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
