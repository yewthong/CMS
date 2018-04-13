<%@page import="java.sql.*" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.List" %>
<%@page import="data.*" %>
<%@page import="java.io.PrintWriter" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Book Vessel</title>
</head>

<script>
	function fullornot(){
		var f = null;
		f = <%=request.getAttribute("full") %>
		if(f=="1"){
			alert("Item weight exceed the vessel available space");
		}
	}
	
	function emptycustomer(){
		var a = document.getElementById('cname').value;
		if(a=="0"){
			alert("Please select customer");
		} 
	} 
	
	function emptyvessel(){
		var a = document.getElementById('cvessel').value;
		if(a==0){
			alert("Please select vessel");
		} 
	} 
	
	

</script>

<body onload="fullornot();">
	
	<form action="AGBook" method="post">
		<table>
			<tr>
				<td>
					Vessel ID:
				</td>
				<td>
					<select name="vessel_ID" id="cvessel">
						<option value=0>Select Vessel</option>
						<%
						try{
						Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
						String connectionURL = "jdbc:sqlserver://ddaccmsserver.database.windows.net;databaseName=CMS;user=demouser;password=Demo@pass123;";
						Connection conn = DriverManager.getConnection(connectionURL);
						Statement stmt = conn.createStatement();
                        String sql = "SELECT S.ID FROM schedule S INNER JOIN schedule_space SS ON S.ID = SS.schedule_ID where "
                        		+"sailing_type='departure'AND convert(date,S.departure_date) > convert(date,GETDATE()) ORDER BY departure_date";
                        ResultSet rs = stmt.executeQuery(sql);
                        while(rs.next()){
						%>
							<option value="<%=rs.getInt(1) %>"><%=rs.getInt(1)%></option>
						<%
	                        }                        
						}catch(Exception ex){
							ex.printStackTrace();
						}
						%>
					</select>
				</td>
			</tr>
			<tr>
				<td>
					Customer Name:
				</td>
				<td>
					<!--  <select name="customer_name" id="cname" onchange="populate()">-->
					<select name="customer_name" id="cname" >
						<option value="0">Select customer</option>
						<%
						try{
							Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
							String connectionURL = "jdbc:sqlserver://ddaccmsserver.database.windows.net;databaseName=CMS;user=demouser;password=Demo@pass123;";
							Connection conn = DriverManager.getConnection(connectionURL);
							Statement stmt = conn.createStatement();
	                        String sql = "SELECT * FROM CUSTOMER";
	                        ResultSet rs = stmt.executeQuery(sql);
	                        while(rs.next()){
						%>
							<option value="<%=rs.getString("name") %>"><%=rs.getString("name")%></option>
						<%
	                        }                        
						}catch(Exception ex){
							ex.printStackTrace();
						}
						%>
				</td>
			</tr>
			
			<tr>
				<td>
					Item Name:
				</td>
				<td>
					<input type="text" name="item_name" size="30" required>
				</td>
			</tr>	
				
			<tr>
				<td>
					Item Quantity:
				</td>
				<td>
					<input type="number" name="item_quantity" size="30" required>
				</td>
			</tr>
			
			<tr>
				<td>
					Item Unit:
				</td>
				<td>
					<input type="text" name="item_unit" size="30" required>
				</td>
			</tr>				
			
			<tr>
				<td>
					Item Weight(KG):
				</td>
				<td>
					<input type="text" name="item_weight" size="30" required>
				</td>
			</tr>	
			
		
		</table>
		<p>
			<input type="submit" value="Submit" onclick="emptycustomer();emptyvessel();"> 
		</p>
	
	
	</form>



</body>
</html>