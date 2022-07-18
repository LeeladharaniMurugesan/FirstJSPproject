package com.chainsys.jspproject;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chainsys.jspproject.commonutil.ExceptionManager;
import com.chainsys.jspproject.commonutil.InvalidInputDataException;
import com.chainsys.jspproject.commonutil.Validator;
import com.chainsys.jspproject.dao.EmployeeDao;

/**
 * Servlet implementation class DeleteEmployeeServlet
 */
@WebServlet("/DeleteEmployeeServlet")
public class DeleteEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteEmployeeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String source ="DeleteEmployee";
		String message ="<h1>Error while"+source+ "</h1>";
		int result = 0;
		String id = null;
		try {
			id = request.getParameter("id");
			try {
				Validator.checkStringForParse(id);
			} catch (InvalidInputDataException err) {
				message += "Error in Employee id input </p>";
				String errorPage =ExceptionManager.handleException(err, source, message);
				out.print(errorPage);
			//	err.printStackTrace();
				return;
			}
			int empId = Integer.parseInt(id);
			try {
				Validator.CheckNumberForGreaterThanZero(empId);
			} catch (InvalidInputDataException err) {
				message += "Error in Employee id input </p>";
				String errorPage =ExceptionManager.handleException(err, source, message);
				out.print(errorPage);
			//	err.printStackTrace();
				return;
			}
//			result = EmployeeDao.deleteEmployee(empId);
//			System.out.println(result + " Deleted Successfully");
			result = EmployeeDao.deleteEmployee(empId);
	        request.setAttribute("delemp", result);
	        RequestDispatcher rd = request.getRequestDispatcher("/deleteemp.jsp");
	        rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//out.print("<div> Deleted Employee: " + result + "</div>");
		
	}

}

