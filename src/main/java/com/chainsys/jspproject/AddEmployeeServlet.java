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
 * Servlet implementation class AddEmployee
 */
@WebServlet("/AddEmployee")
public class AddEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddEmployeeServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//	response.getWriter().print("<h1> post called</h1>");
		PrintWriter out = response.getWriter();
			String source ="AddNewEmployee";
			String message ="<h1>Error while"+source+ "</h1>";
			Employee newemp = null;
			int result = 0;
			int empId = 0;
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
		
		}

}



