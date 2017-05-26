package ua.nure.shevchenko.Practice10;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.Session;

/**
 * Servlet implementation class Part3Parser
 */
@WebServlet("/Part3Parser")
public class Part3Parser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Part3Parser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		List<String> names = new ArrayList<String>();
		session.setAttribute("names", names);
		response.sendRedirect("Part3.jsp");		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		List<String> names;
		names = (ArrayList<String>)session.getAttribute("names");
		if (names == null){
			names = new ArrayList<String>();
		}
		String newName = (String)request.getParameter("name");
		names.add(newName);
		session.setAttribute("names", names);
		response.sendRedirect("Part3.jsp");		
	}

}
