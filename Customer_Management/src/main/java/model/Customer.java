package model;
import java.sql.*; 

public class Customer {
	//A common method to connect to the DB
	private Connection connect()
	{
		Connection con = null;
				
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			//Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/egcompdb", "root", "");
		}
				
		catch (Exception e)
		{
			e.printStackTrace();
		}
				
		return con;
	}
	
	//Insert consumer dtails to DB table
	public String insertCustomer(String CustomerName, String Address, String MobileNo, String Email, String AccNumber )
	{
		String output = "";
		try 
		{
			Connection con = connect();
				
			if (con == null)
			{
				return "Error while connecting to the database for inserting."; 
			}
				
			// create a prepared statement
			String query = " insert into customer (`CustomerId`,`CustomerName`,`Address`,`MobileNo`,`Email`, `AccNumber`)"
								+ " values (?, ?, ?, ?, ?, ?)";
				
			PreparedStatement preparedStmt = con.prepareStatement(query);
				
			// binding values
			
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, CustomerName);
			preparedStmt.setString(3, Address);
			preparedStmt.setString(4, MobileNo);
			preparedStmt.setString(5, Email);
			preparedStmt.setString(6, AccNumber);
				
			// execute the statement
			preparedStmt.execute();
			con.close();
			String newCustomer = readCustomer(); 
			output = "{\"status\":\"success\", \"data\": \"" + newCustomer + "\"}"; 
				
			
		}
			
		catch (Exception e)
		{
			output = "{\"status\":\"error\", \"data\": \"Error while inserting the customer.\"}"; 
			System.err.println(e.getMessage());
		}
			
		return output;
	}
	
	//Read consumer details
	public String readCustomer()
	{
		
		String output = ""; 
			
		try
		{
			Connection con = connect();
				
			if (con == null)
			{
				return "Error while connecting to the database for reading."; 
			}
			//prepare the html table to be display
			 output = "<table border='1'><tr>" +"<th>CustomerName</th><th>Address</th>"
					 +"<th>MobileNo</th>"+"<th>Email</th>"+"<th>AccNumber</th>"
			 + "<th>Update</th><th>Remove</th></tr>";	
				
			String query = "select * from customer";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
				
			
			
				
			// iterate through the rows in the result set
			while (rs.next()) {
				
				//CustomerId`,`CustomerName`,`Address`,`MobileNo`,`Email`, `AccNumber`
				String CustomerId=rs.getString("CustomerId");
				String CustomerName=rs.getString("CustomerName");
				String Address=rs.getString("Address");
				String MobileNo=rs.getString("MobileNo");
				String Email=rs.getString("Email");
				String AccNumber=rs.getString("AccNumber");
				
				
				 // Add into the html table
				output += "<tr><td><input id=\'hidCustomerIDUpdate\' name=\'hidCustomerIDUpdate\' type=\'hidden\' value=\'"
						+ CustomerId + "'>" + CustomerName + "</td>";
				 output += "<td>" + Address + "</td>"; 
				 output += "<td>" + MobileNo + "</td>";
				 output += "<td>" + Email + "</td>";
				 output += "<td>" + AccNumber + "</td>"; 
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' "
				+ "class='btnUpdate btn btn-secondary' data-customerid='" + CustomerId + "'></td>"
				+ "<td><input name='btnRemove' type='button' value='Remove' "
				+ "class='btnRemove btn btn-danger' data-customerid='" + CustomerId + "'></td></tr>"; 

					
			}
		
				
			con.close();
			output += "</table>"; 
		}
			
		catch (Exception e)
		{
			output = "Error while reading the customer."; 
			 System.err.println(e.getMessage()); 
		}
			
		return output;
	}
	
	//Update consumer details
	////CustomerId`,`CustomerName`,`Address`,`MobileNo`,`Email`, `AccNumber`
	public String updateCustomer(String CustomerId, String CustomerName, String Address, String MobileNo, String Email, String AccNumber) 
	{
		String output = "";
			
		try
		{
			Connection con = connect();
				
			if (con == null)
			{
				return "Error while connecting to the database for updating."; 
			}
				
			// create a prepared statement
			String query = "UPDATE customer SET CustomerName=?,Address=?,MobileNo=?,Email=?,AccNumber=? WHERE CustomerId=?";
				
			PreparedStatement preparedStmt = con.prepareStatement(query);
				
			// binding values
			preparedStmt.setString(1, CustomerId);
			preparedStmt.setString(2, CustomerName);
			preparedStmt.setString(3, Address);
			preparedStmt.setString(4, MobileNo);
			preparedStmt.setString(5, Email);
			preparedStmt.setString(6, AccNumber);
			
				
			// execute the statement
			preparedStmt.execute();
			con.close();
			String newCustomer = readCustomer();
			output = "{\"status\":\"success\", \"data\": \"" + 
					newCustomer + "\"}"; 
				
			
		}
			
		catch (Exception e)
		{
			output = "{\"status\":\"error\", \"data\": \"Error while updating the customer.\"}";
			System.err.println(e.getMessage());
		}
			
		return output;
	}
	//Delete consumer details
	public String deleteCustomer(String CustomerId)
	{
		String output = "";
		
		try
		{
			Connection con = connect();
			
			if (con == null)
			{
				return "Error while connecting to the database for deleting."; 
			}
			
			// create a prepared statement
			String query = "delete from customer where CustomerId=?";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setString(1, CustomerId);
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			
			String newCustomer  = readCustomer(); 
			 output = "{\"status\":\"success\", \"data\": \"" + 
					 newCustomer + "\"}"; 
		}
		
		catch (Exception e)
		{
			output = "{\"status\":\"error\", \"data\": \"Error while deleting the customer.\"}";
			System.err.println(e.getMessage());
		}
		
		return output;
	}

}
