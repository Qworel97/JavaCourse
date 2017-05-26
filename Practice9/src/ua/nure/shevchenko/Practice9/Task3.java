package ua.nure.shevchenko.Practice9;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Task2
 */
@WebServlet("/Task3")
public class Task3 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private HashMap<String, Integer> results = new HashMap();
	private HashMap<String, List<String>> users = new HashMap();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Task3() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String list = getServletContext().getInitParameter("list");
		response.getWriter().println("<!DOCTYPE HTML>");
		response.getWriter().println(
				"<html><body><form action=\"Task3\" method=\"POST\">");
		for (String x : list.split(" ")) {
			response.getWriter().println(
					"<input type = \"radio\" name = \"Q2\" value=\"" + x
							+ "\">" + x + "<br>");
		}
		response.getWriter().println(
				"<input type=\"text\" name=\"name\" size=\"40\"><br>");
		response.getWriter().println(
				"<input type=\"submit\" value=\"SendMessage\"><br>");
		response.getWriter().println("</form></body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String res = (String) request.getParameter("Q2");
		String name = (String) request.getParameter("name");
		try {
			String tempName = (String) request.getSession().getAttribute(name);
			if (tempName == null) {
				request.getSession().setAttribute(name, name);
				String list = getServletContext().getInitParameter("list");
				for (String x : list.split(" ")) {
					if (x.equals(res)) {
						if (!results.containsKey(x)) {
							results.put(x, 1);
							if (!users.containsKey(x)) {
								List<String> temp = new ArrayList<String>();
								temp.add(name);
								users.put(x, temp);
							} else {
								List<String> temp = users.get(x);
								temp.add(name);
								users.put(x, temp);
							}
						} else {
							results.put(x, results.get(x) + 1);
							if (!users.containsKey(x)) {
								List<String> temp = new ArrayList<String>();
								temp.add(name);
								users.put(x, temp);
							} else {
								List<String> temp = users.get(x);
								temp.add(name);
								users.put(x, temp);
							}
						}
					}
				}
				response.getWriter().println("<!DOCTYPE HTML>");
				response.getWriter().println("<html><body><p>  ");
				for (String x : list.split(" ")) {
					response.getWriter().println(x + " " + results.get(x));
					for (String y : users.get(x)) {
						response.getWriter().println(y + " ");
					}
					response.getWriter().println("<br>");

				}
				response.getWriter()
				.println(
						"<input type=\"button\" value=\"GoBack\" onClick='location.href=\"Task3\"'>");
				response.getWriter().println("</p></body></html>");

			} else {
				response.getWriter().println("<!DOCTYPE HTML>");
				response.getWriter().println("<html><body><p> Sorry");
				response.getWriter()
						.println(
								"<input type=\"button\" value=\"GoBack\" onClick='location.href=\"Task3\"'>");
				response.getWriter().println("</p></body></html>");
			}
		} catch (NullPointerException npe) {

		}

	}

}
