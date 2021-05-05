package InputParameters;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.GenericServlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import daostuff.Department;
import daostuff.DepartmentDAOImpl;
import daostuff.exceptions.DepartmentAlreadyException;
import daostuff.exceptions.DepartmentModifyAlreadyException;
import daostuff.exceptions.DepartmentNotFoundException;

@SuppressWarnings("serial")
public class servletParameters extends GenericServlet {
	
    DepartmentDAOImpl ddi = new DepartmentDAOImpl();
    
    public void DepartmentServlet() {
        
        System.out.println("Department Servlet..ctor....");
    }

	public void init(ServletConfig config) throws ServletException {
        System.out.println("Department init()...");
	}

	
	public void destroy() {
        System.out.println("Department Servlet...destroy()....");
	}

	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException
	{
		System.out.println("Department Servlet...service()....");
		
		String buttonValue = request.getParameter("submit");
		PrintWriter pw = response.getWriter();
		pw.println("Button clicked value is "+buttonValue);
		
		if(buttonValue.equals("Add Dept"))
		{
			int dno = Integer.parseInt(request.getParameter("v_deptno")); 
			String dnm = request.getParameter("v_dname");
			String dloc = request.getParameter("v_loc");
		
			Department deptObj = new Department(); 
			deptObj.setDepartmentNumber(dno);
			deptObj.setDepartmentName(dnm);
			deptObj.setDepartmentLocation(dloc); 
		  try {
			ddi.addDepartment(deptObj);
			pw.println("<h4>Department is added to the database</h4>");
		  }
		  catch(DepartmentAlreadyException e) {
			  pw.println("<h4>Department is not added to the database" + e.getMessage() +"</h4>");
		  }
		  
		}
		else if (buttonValue.equals("Find Dept"))
		{
			try {
				int dno = Integer.parseInt(request.getParameter("v_deptno"));
				Department deptObj = ddi.findDepartment(dno);
				pw.println("<table border=5 cellpadding=10 cellspacing=10>");
				pw.println("<tr>");
					pw.println("<td>"+deptObj.getDepartmentNumber()+"</td>");
					pw.println("<td>"+deptObj.getDepartmentName()+"</td>");
					pw.println("<td>"+deptObj.getDepartmentLocation()+"</td>");
				pw.println("</tr>");
				pw.println("</table>");
				
			}
			catch(DepartmentNotFoundException e) {
				pw.println("<h4>Department not found" + e.getMessage() +"</h4>");
			}
			
		}
		else if(buttonValue.equals("Find All Depts")) 
		{
			pw.println("<table border=1 cellspacing=10 cellpadding=10>");
			pw.println("<th>");		pw.println("DEPT NO");	pw.println("</th>");
			pw.println("<th>");		pw.println("DEPT NAME");pw.println("</th>");
			pw.println("<th>");		pw.println("LOCATION"); pw.println("</th>");
			List<Department> deptList = ddi.findDepartments();
			Iterator<Department> deptIter = deptList.iterator();
			
				while(deptIter.hasNext()) {
					Department deptObj = deptIter.next();
					pw.println("<tr>");
					
							pw.println("<td>");
								pw.println(deptObj.getDepartmentNumber());
							pw.println("</td>");
					
							pw.println("<td>");
								pw.println(deptObj.getDepartmentName());
							pw.println("</td>");

							pw.println("<td>");
								pw.println(deptObj.getDepartmentLocation());
							pw.println("</td>");
							
					pw.println("</tr>");
				}
			pw.println("</table>");
		}
		else if (buttonValue.equals("Modify Dept"))
		{
			int dno = Integer.parseInt(request.getParameter("v_deptno")); //to read html variable name's values 
			String dnm = request.getParameter("v_dname");
			String dloc = request.getParameter("v_loc");
			Department deptObj = new Department(); //blank one
			deptObj.setDepartmentNumber(dno);
			deptObj.setDepartmentName(dnm);
			deptObj.setDepartmentLocation(dloc);
			if(deptObj.equals(null))
				pw.println("<h4>Department is not found in  database </h4>");
			else {
				try {
					ddi.modifyDepartment(deptObj);
					pw.println("<h4> Department is modified</h4>");
				}catch(DepartmentModifyAlreadyException e)
				{
					  pw.println("<h4>Department is not found in  database" + e.getMessage() +"</h4>");
				}
			}
			 
		}
		else if (buttonValue.equals("Delete Dept"))
		{
			int dno = Integer.parseInt(request.getParameter("v_deptno")); //to read html variable name's values 
			
			Department deptObj = new Department(); //blank one
			deptObj.setDepartmentNumber(dno);
			try {
				ddi.removeDepartment(deptObj);
				pw.println("<h4> Department is deleted</h4>");
			}
			catch(DepartmentNotFoundException e)
			{
				  pw.println("<h4>Department is not found in  database" + e.getMessage() +"</h4>");
			}
			
			
		}
		
		pw.println("<a href='http://localhost:8080/ServletWithParameters/'> Back to Welcome </a>");
			
		
	}

}
