package ua.nure.shevchenko.Practice9;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * Servlet implementation class Calculator
 */
@WebServlet("/Task1")
public class Task1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ERROR_INPUT_MESSAGE = "Sorry, wrong input";

	static Logger log = Logger.getLogger(Task1.class);

	static {
		BasicConfigurator.configure();
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Task1() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {

			log.setLevel(Level.ALL);
			String x = (String) request.getParameter("x");
			String y = (String) request.getParameter("y");
			String toDo = (String) request.getParameter("toDo");

			log.info("Doing some math");
			if (isValidInput(x, y, toDo)) {
				Float xToFloat = Float.parseFloat(x);
				log.info("x = " + x);
				Float yToFloat = Float.parseFloat(y);
				log.info("y = " + x);
				Float res = Float.valueOf("0");

				log.info("toDo " + toDo);
				switch (toDo) {
				case ("Sum"):
					res = xToFloat + yToFloat;
					break;
				case ("Dif"):
					res = xToFloat - yToFloat;
					break;
				case ("Mul"):
					res = xToFloat * yToFloat;
					break;
				case ("Div"):
					res = xToFloat / yToFloat;
					break;
				}
				
				log.info("res = " + res);
				response.getWriter().println("<!DOCTYPE HTML>");
				response.getWriter()
						.println("<html><body><p>" + res.toString());
				response.getWriter()
						.println(
								"<input type=\"button\" value=\"GoBack\" onClick='location.href=\"index.html\"'>");
				response.getWriter().println("</p></body></html>");
			} else {
				response.getWriter().println("<!DOCTYPE HTML>");
				response.getWriter().println(
						"<html><body><p>" + ERROR_INPUT_MESSAGE);
				response.getWriter()
						.println(
								"<input type=\"button\" value=\"GoBack\" onClick='location.href=\"index.html\"'>");
				response.getWriter().println("</p></body></html>");
			}
		} catch (NullPointerException npe) {
			log.info("So sad we have NullPointerException");
			System.err.println(npe.getMessage());
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	private boolean isValidInput(String param1, String param2, String param3) {
		try {
			Float.parseFloat(param1);
			if (Float.parseFloat(param2) == Float.valueOf("0")
					&& param3 == "Div") {
				log.info("Wrong input");
				return false;
			}
		} catch (java.lang.NumberFormatException NFE) {
			return false;
		}
		log.info("Input is ok");
		return true;

	}

}
