package model;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CustomerAPI
 */
@WebServlet("/CustomerAPI")
public class CustomerAPI extends HttpServlet {
	Customer customerObj = new Customer();
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerAPI() {
        super();
        
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String output = customerObj.insertCustomer(
				request.getParameter("CustomerName"), 
				request.getParameter("Address"),
		        request.getParameter("MobileNo"),
		        request.getParameter("Email"),
		        request.getParameter("AccNumber"));		  
				response.getWriter().write(output);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	
	private static Map getParasMap(HttpServletRequest request) 
	{ 
	 Map<String, String> map = new HashMap<String, String>(); 
	try
	 { 
	 Scanner scanner = new Scanner(request.getInputStream(), "UTF-8"); 
	 String queryString = scanner.hasNext() ? 
	 scanner.useDelimiter("\\A").next() : ""; 
	 scanner.close(); 
	 String[] params = queryString.split("&"); 
	 for (String param : params) 
	 { 
	 String[] p = param.split("="); 
	 map.put(p[0], p[1]); 
	 } 
	 } 
	catch (Exception e) 
	 { 
	 } 
	return map; 
	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Map paras = getParasMap(request); 
		 String output = customerObj.updateCustomer(
		 paras.get("hidCustomerIDSave").toString(), 
		 paras.get("CustomerName").toString(), 
		 paras.get("Address").toString(), 
		 paras.get("MobileNo").toString(), 
		 paras.get("Email").toString(),
		 paras.get("AccNumber").toString()); 
		response.getWriter().write(output);
	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Map paras = getParasMap(request); 
		 String output = customerObj.deleteCustomer(paras.get("CustomerId").toString()); 
		response.getWriter().write(output);
	}

}
