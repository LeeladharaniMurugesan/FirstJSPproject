package com.chainsys.jspproject;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chainsys.jspproject.commonutil.InvalidInputDataException;
import com.chainsys.jspproject.commonutil.ExceptionManager;
import com.chainsys.jspproject.commonutil.LogManager;
import com.chainsys.jspproject.commonutil.HTMLHelper;
import com.chainsys.jspproject.commonutil.Validator;
import com.chainsys.jspproject.dao.EmployeeDao;
import com.chainsys.jspproject.pojo.Employee;

/**
 * Servlet implementation class Employees
 */
@WebServlet("/Employees")
public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EmployeeServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		  List<Employee> allEmployees = EmployeeDao.getAllEmployee();
			request.setAttribute("emplist", allEmployees);
			RequestDispatcher rd = request.getRequestDispatcher("/viewemp.jsp");
			rd.forward(request, response);
			
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		String submitValue = request.getParameter("submit");
		System.out.println(submitValue);
		if (submitValue.equals("UPDATE")) {
			doPut(request, response);
		} else if (submitValue.equals("DELETE")) {
			doDelete(request, response);
		}else if (submitValue.equals("VIEW")) {
					doGet(request, response);
		} else if (submitValue.equals("ADD")) {
			String source ="AddNewEmployee";
			String message ="<h1>Error while"+source+ "</h1>";
			Employee newemp = null;
			int result = 0;
			int empId = 0;
//			String testname = null;
			try {
				newemp = new Employee();
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
				empId = Integer.parseInt(id);
				try {
					Validator.CheckNumberForGreaterThanZero(empId);
				} catch (InvalidInputDataException err) {
					message += "Error in Employee id input </p>";
					String errorPage =ExceptionManager.handleException(err, source, message);
					out.print(errorPage);
				//	err.printStackTrace();
					return;
				}
				newemp.setEmployee_id(empId);
//------------------------------------------------------------------------------
				String fname = request.getParameter("fname");
//				testname = fname;
				try {
					Validator.checkStringOnly(fname);
				} catch (InvalidInputDataException e) {
					message += "Error in Employee firstname input </p>";
					String errorPage =ExceptionManager.handleException(e, source, message);
					out.print(errorPage);
				//	err.printStackTrace();
					return;
				}
				try {
					Validator.checklengthOfString(fname);
				} catch (InvalidInputDataException err) {
					message += "Error in Employee firstname input </p>";
					String errorPage =ExceptionManager.handleException(err, source, message);
					out.print(errorPage);
				//	err.printStackTrace();
					return;
				}
				newemp.setFirst_name(fname);
//-----------------------------------
				String lname = request.getParameter("lname");
				// testname = fname;
				try {
					Validator.checkStringOnly(lname);
				} catch (InvalidInputDataException err) {
					message += "Error in Employee lastname input </p>";
					String errorPage =ExceptionManager.handleException(err, source, message);
					out.print(errorPage);
				//	err.printStackTrace();
					return;
				}
				try {
					Validator.checklengthOfString(lname);
				} catch (InvalidInputDataException err) {
					message += "Error in Employee lastname input </p>";
					String errorPage =ExceptionManager.handleException(err, source, message);
					out.print(errorPage);
				//	err.printStackTrace();
					return;
					
				}
				newemp.setLast_name(lname);
//----------------------------------------------------------			
				String email = request.getParameter("email");
				try {
					Validator.checkingMail(email);
				} catch (InvalidInputDataException e) {
					message += "Error in Employee email input </p>";
					String errorPage =ExceptionManager.handleException(e, source, message);
					out.print(errorPage);
				//	err.printStackTrace();
					return;
				}
				newemp.setEmail(email);
//------------------------------------------------------------			
				SimpleDateFormat hire_dateFormate = new SimpleDateFormat("dd/MM/yyyy");
				String hd = request.getParameter("hdate");
				// Date hire_date=hire_dateFormate.parse(emp_HireDate);

				try {
					Validator.checkDateFormat(hd);
				} catch (InvalidInputDataException e) {
					message += "Error in Employee hirdate input </p>";
					String errorPage =ExceptionManager.handleException(e, source, message);
					out.print(errorPage);
				//	err.printStackTrace();
					return;
				}
				Date newDate = null;
				newDate = hire_dateFormate.parse(hd);

				newemp.setHire_date(newDate);
//--------------------------------------------------------------
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
//--------------------------------------------------------------			
				String sal = request.getParameter("salary");
				float salParse = Float.parseFloat(sal);
				try {
					Validator.Checkfees(salParse);
				} catch (InvalidInputDataException err) {
					message += "Error in Employee salary input </p>";
					String errorPage =ExceptionManager.handleException(err, source, message);
					out.print(errorPage);
				//	err.printStackTrace();
					return;
				}
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
//				result = EmployeeDao.insertEmployee(newemp);
			} catch (Exception e) {
				message += "Error in Employee id input </p>";
				String errorPage =ExceptionManager.handleException(e, source, message);
				out.print(errorPage);
			//	err.printStackTrace();
				return;
			}
//			out.println("<div> Add New Employee: " + result + "</div>");
//			System.out.println("1 Added Successfully");
			result = EmployeeDao.insertEmployee(newemp);
            request.setAttribute("addemp", result);
            RequestDispatcher rd = request.getRequestDispatcher("/addemployee.jsp");
            rd.forward(request, response);
			// + new Employee()); -> from the servlet send only
			// object are illegal.
		} else {
			out.print("<h1> SELECT VALID CHOICE </h1>");
		}

	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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