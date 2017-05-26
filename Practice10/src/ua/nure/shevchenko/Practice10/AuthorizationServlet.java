package ua.nure.shevchenko.Practice10;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.shevchenko.Practice10.db.DBManager;
import ua.nure.shevchenko.Practice10.db.entity.Role;
import ua.nure.shevchenko.Practice10.db.entity.User;

/**
 * Servlet implementation class AuthorizationServlet
 */
@WebServlet("/AuthorizationServlet")
public class AuthorizationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AuthorizationServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		DBManager db = DBManager.getInstance();
		List<User> users = db.findAllUsers();
		String login = (String) request.getParameter("login");
		String password = (String) request.getParameter("password");
		boolean found = false;
		for (User user : users) {
			if (user.getLogin() == login && user.getPassword() == password) {
				found = true;
				request.getSession().setAttribute("user", user);
				List<Role> roles = db.findAllRoles();
				for (Role role : roles) {
					if (user.getRoleId() == role.getId()) {
						request.getSession().setAttribute("role",
								role.getName());
						break;
					}
				}

				break;
			}
		}
		if (found) {
			response.sendRedirect("Part3.jsp");
		} else {
			response.sendRedirect("Authorize.jsp");
		}

	}

}
