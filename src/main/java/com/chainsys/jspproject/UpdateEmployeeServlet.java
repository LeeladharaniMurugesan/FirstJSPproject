package com.chainsys.jspproject;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import com.chainsys.jspproject.pojo.Employee;

/**
 * Servlet implementation class UpdateEmployee
 */
@WebServlet("/UpdateEmployee")
public class UpdateEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateEmployeeServlet() {
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
		String source ="UpdateEmployee";
		String message ="<h1>Error while"+source+ "</h1>";
		Employee newemp = new Employee();
		int result = 0;
		try {

			String id = request.getParameter("id");
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
			}catch (InvalidInputDataException err) {
				message += "Error in Employee id input </p>";
				String errorPage =ExceptionManager.handleException(err, source, message);
				out.print(errorPage);
			//	err.printStackTrace();
				return;
			}
			newemp.setEmployee_id(empId);
//--------------------------------
			String fname = request.getParameter("fname");
			try {
				Validator.checkStringOnly(fname);
			} catch (InvalidInputDataException err) {
				message += "Error in Employee fname input </p>";
				String errorPage =ExceptionManager.handleException(err, source, message);
				out.print(errorPage);
			//	err.printStackTrace();
				return;
			}
			try {
				Validator.checklengthOfString(fname);
			} catch (InvalidInputDataException err) {
				message += "Error in Employee fname input </p>";
				String errorPage =ExceptionManager.handleException(err, source, message);
				out.print(errorPage);
			//	err.printStackTrace();
				return;
			}
			newemp.setFirst_name(fname);
//-----------------------------------
			String lname = request.getParameter("lname");
			try {
				Validator.checkStringOnly(lname);
			} catch (InvalidInputDataException err) {
				message += "Error in Employee lname input </p>";
				String errorPage =ExceptionManager.handleException(err, source, message);
				out.print(errorPage);
			//	err.printStackTrace();
				return;
			}
			try {
				Validator.checklengthOfString(lname);
			} catch (InvalidInputDataException err) {
				message += "Error in Employee lname input </p>";
				String errorPage =ExceptionManager.handleException(err, source, message);
				out.print(errorPage);
			//	err.printStackTrace();
				return;
			}
			newemp.setLast_name(lname);
//----------------------------------			
			String email = request.getParameter("email");
			try {
				Validator.checkingMail(email);
			} catch (InvalidInputDataException err) {
				message += "Error in Employee email input </p>";
				String errorPage =ExceptionManager.handleException(err, source, message);
				out.print(errorPage);
			//	err.printStackTrace();
				return;
			}
			newemp.setEmail(email);
//--------------------------------------			
			SimpleDateFormat hire_dateFormate = new SimpleDateFormat("dd/MM/yyyy");
			String emp_HireDate = request.getParameter("hdate");
			// Date hire_date=hire_dateFormate.parse(emp_HireDate);

			try {
				Validator.checkDateFormat(emp_HireDate);
			} catch (InvalidInputDataException err) {
				message += "Error in Employee hdate input </p>";
				String errorPage =ExceptionManager.handleException(err, source, message);
				out.print(errorPage);
			//	err.printStackTrace();
				return;
			}
			Date newDate = null;
				newDate = hire_dateFormate.parse(emp_HireDate);
			newemp.setHire_date(newDate);
//----------------------------------------
			String jobId = request.getParameter("jobid");
			try {
				Validator.checkjobid(jobId);
			} catch (InvalidInputDataException err) {
				message += "Error in Employee jobid input </p>";
				String errorPage =ExceptionManager.handleException(err, source, message);
				out.print(errorPage);
			//	err.printStackTrace();
				return;
			}
			newemp.setJob_id(jobId);
//---------------------------------------			
			String sal = request.getParameter("salary");
			try {
				Validator.checkStringForParse(sal);
			} catch (InvalidInputDataException err) {
				message += "Error in Employee salary input </p>";
				String errorPage =ExceptionManager.handleException(err, source, message);
				out.print(errorPage);
			//	err.printStackTrace();
				return;
			}
			float salParse = Float.parseFloat(sal);
			try {
				Validator.checkSalary(salParse);
			} catch (InvalidInputDataException err) {
				message += "Error in Employee salary input </p>";
				String errorPage =ExceptionManager.handleException(err, source, message);
				out.print(errorPage);
			//	err.printStackTrace();
				return;
			}
			newemp.setSalary(salParse);
//----------------------------------------------	
//			result = EmployeeDao.updateEmployee(newemp);
//			System.out.println(result + " Updated Successfully");
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		// out.println("<div> Update Employee: " + result + "</div>");
		result = EmployeeDao.updateEmployee(newemp);
        request.setAttribute("updateemp", result);
        RequestDispatcher rd = request.getRequestDispatcher("/updateemployee.jsp");
        rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
}

