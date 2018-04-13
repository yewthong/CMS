<%@page import="java.sql.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<jsp:include page="admin_menu.jsp"></jsp:include>
	<title>View agent booking</title>
	<h2>View Agent Booking</h2>
	<p2>Select the agent name to view the agent booking</p2>
	</head>
	
	<body>
		<form action="ADVagentbooking" method="post">
			<table>
				<tr>
					<td>
						Agent Name:
					</td>
					<td>
					
						<select name="agent_name" required>
							<option value="0">Select agent</option>
							<%
							try{
								Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
								String connectionURL = "jdbc:sqlserver://ddaccmsserver.database.windows.net;databaseName=CMS;user=demouser;password=Demo@pass123;";
								Connection conn = DriverManager.getConnection(connectionURL);
								Statement stmt = conn.createStatement();
		                        String sql = "SELECT name FROM logins WHERE role='agent'";
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
						
						</select>
					</td>
				</tr>
			</table>
			
			<p>
				<input type="submit" value="Submit">
			</p>
		
		</form>
	</body>
</html>