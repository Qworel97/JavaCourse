package ua.nure.shevchenko.Practice9;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.Charset;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Task2
 */
@WebServlet("/Task2")
public class Task2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Task2() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		response.getWriter().println("<!DOCTYPE HTML>");
		response.getWriter().println("<html><body><p>  Hello," + name + "!"); 
		response.getWriter().println("<input type=\"button\" value=\"GoBack\" onClick='location.href=\"task2.html\"'>"); 
		response.getWriter().println("</p></body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
