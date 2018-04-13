
<%@page import="java.sql.*" %>


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<jsp:include page="admin_menu.jsp"></jsp:include>
	<title>Create Sail Schedule</title>
	<h2>Create Sail Schedule</h2>
	<p>Please fill in the information below</p>
</head>

<script>
	function departureday(){
	    var d = new Date(document.getElementById('dd').value);
	    var n = d.getDay()
	    if(n==0){
	    	var a = "Sunday";
	    }else if(n==1){
	    	var a = "Monday";
	    }else if(n==2){
	    	var a = "Tuesday";
	    }else if(n==3){
	    	var a = "Wednesday";
	    }else if(n==4){
	    	var a = "Thursday";
	    }else if(n==5){
	    	var a = "Friday";
	    }else if(n==6){
	    	var a = "Saturday";
	    }
	    document.getElementById('dday').value = a;

	    
	}
	
	function arrivalday(){
	    var d = new Date(document.getElementById('ad').value);
	    var n = d.getDay()
	    if(n==0){
	    	var a = "Sunday";
	    }else if(n==1){
	    	var a = "Monday";
	    }else if(n==2){
	    	var a = "Tuesday";
	    }else if(n==3){
	    	var a = "Wednesday";
	    }else if(n==4){
	    	var a = "Thursday";
	    }else if(n==5){
	    	var a = "Friday";
	    }else if(n==6){
	    	var a = "Saturday";
	    }
	    document.getElementById('aday').value = a;

	    
	}
	
	function depaturelocationcheck(){
		var a = document.getElementById('deplocation').value;
		if(a=="0"){
			alert("Please select departure location");
		}
	}
	
	function arrivallocationcheck(){
		var a = document.getElementById('arrlocation').value;
		if(a=="0"){
			alert("Please select arrival location");
		}
	}
	
	function samelocation(){
		var a = document.getElementById('deplocation').value;
		var b = document.getElementById('arrlocation').value;
		
		if(a==b){
			alert("Departure location cannot be same as arrival location");
			document.getElementById('arrlocation').value='0';
		}
	}
	
	

</script>

<body>
	<form action="ADCreateSchedule" method="post">
		<table>
			<tr>
				<td>
					Departure Date: 
				</td>
				<td>
					<input type="date" name="departure_date" onchange="departureday()" id="dd" size="30" required>
				</td>
			</tr>
			
			<tr>
				<td>
					Departure Day:
				</td>
				<td>
					<input type="text" name="departure_day" id = "dday" size="30" readonly>
				</td>
			</tr>
			
			<tr>
				<td>
					Arrival Date:
				</td>
				<td>
				    <input type="date" name="arrival_date" onchange="arrivalday()" id="ad" size="30" required>
				</td>
			</tr>
			
			<tr>
				<td>
					Arrival Day: 
				</td>
				<td>
				    <input type="text" name="arrival_day"  id="aday" size="30" readonly>
				</td>
			</tr>			
			
			<tr>
				<td>
					Departure Time: 
				</td>
				<td>
					<input type="time" name="dtime" size="30" required>
				</td>
			</tr>
			
			<tr>
				<td>
					Arrival Time: 
				</td>
				<td>
					<input type="time" name="atime" size="30" required>
				</td>
			</tr>
			<tr>
				<td>
					Departure Location:
				</td>
				<td>
					<select name="dlocation" id="deplocation">
						<option value=0>Select Departure Location</option>
						<%
						try{
						Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
						String connectionURL = "jdbc:sqlserver://ddaccmsserver.database.windows.net;databaseName=CMS;user=demouser;password=Demo@pass123;";
						Connection conn = DriverManager.getConnection(connectionURL);
						Statement stmt = conn.createStatement();
                        String sql = "SELECT * FROM shipping_location";
                        ResultSet rs = stmt.executeQuery(sql);
                        while(rs.next()){
						%>
							<option value="<%=rs.getString(2) %>"><%=rs.getString(2)%></option>
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
					Arrival Location:
				</td>
				<td>
					<select name="alocation" id="arrlocation" onchange="samelocation()">
						<option value=0>Select Arrival Location</option>
						<%
						try{
						Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
						String connectionURL = "jdbc:sqlserver://ddaccmsserver.database.windows.net;databaseName=CMS;user=demouser;password=Demo@pass123;";
						Connection conn = DriverManager.getConnection(connectionURL);
						Statement stmt = conn.createStatement();
                        String sql = "SELECT * FROM shipping_location";
                        ResultSet rs = stmt.executeQuery(sql);
                        while(rs.next()){
						%>
							<option value="<%=rs.getString(2) %>"><%=rs.getString(2)%></option>
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
					Sailing Space:
				</td>
				<td>
					<select name="sail_space" required>
						<option value="100">100 KG</option>
						<option value="200">200 KG</option>
						<option value="300">300 KG</option>
						<option value="400">400 KG</option>
						<option value="500">500KG</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>
					Schedule Type:
				</td>
				<td>
					<input type="radio" name="sail_type" value="departure" checked="checked">Departure
					<input type="radio" name="sail_type" value="arrival">Arrival
				</td>
			</tr>
		</table>
		<p>
			<input type="submit" value="Submit" onclick="depaturelocationcheck();arrivallocationcheck();">
		</p>
	</form>
</body>
</html>