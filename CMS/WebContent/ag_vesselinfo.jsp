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
<title>Insert title here</title>
</head>

<script>


	function selectcustomer(){
		var f = null;
		f = <%=request.getAttribute("emptycustomer") %>
		if(f=="1"){
			alert("Please select customer");
		}
	}
	
	function selectvessel(){
		var f = null;
		f = <%=request.getAttribute("emptyvessel") %>
		if(f=="1"){
			alert("Please select vessel");
		}
	}

</script>

<body>

<%
try(PrintWriter a = response.getWriter()){
	
	request.getRequestDispatcher("agent_menu.jsp").include(request, response);
	request.getRequestDispatcher("hometitle.jsp").include(request, response);
	
    a.println("<table border=\"1\" width=\"100%\">\n"
            + "            <tr>\n"
            + "                <th>Sail ID</th>\n"
            + "                <th>Departure Date</th>\n"
            + "                <th>Departure Day</th>\n"
            + "                <th>Arrival Date</th>\n"
            + "                <th>Arrival Day</th>\n"
            + "                <th>Departure Time</th>\n"
            + "                <th>Arrival Time</th>\n"
            + "                <th>Departure Location</th>\n"             
            + "                <th>Arrival Location</th>\n"
            + "                <th>Total Sailing Space</th>\n"
            + "                <th>Available Sailing Space</th>\n"
            + "                <th>Occupied Sailing Space</th>\n"
            + "            </tr>");
    
	
	try{
		
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String connectionURL = "jdbc:sqlserver://ddaccmsserver.database.windows.net;databaseName=CMS;user=demouser;password=Demo@pass123;";
        Connection conn = DriverManager.getConnection(connectionURL);
        String sql = "SELECT S.ID, CONVERT(date,S.departure_date),S.departure_day,S.arrival_date,S.arrival_day,S.departure_time,S.arrival_time,"
        		+ "S.departure_location,S. arrival_location,S.sailing_space,SS.available_space,SS.occupied_space FROM schedule S "
        		+ "INNER JOIN schedule_space SS ON S.ID = SS.schedule_ID where "
        		+ "sailing_type='departure'AND S.departure_date > convert(date,GETDATE()) ORDER BY departure_date";
        
        PreparedStatement pst = conn.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();	
        List<AvailableSail> schedule = new ArrayList<AvailableSail>();
        
        while(rs.next()) {
        	AvailableSail as = new AvailableSail();
        	as.setID(rs.getInt(1));
        	as.setDeparture_date(rs.getString(2));
        	as.setDeparture_day(rs.getString(3));           	
        	as.setArrival_date(rs.getString(4));
        	as.setArrival_day(rs.getString(5));
        	as.setDeparture_time(rs.getString(6));            	
        	as.setArrival_time(rs.getString(7));	            	
        	as.setDeparture_location(rs.getString(8));
        	as.setArrival_location(rs.getString(9));
        	as.setSailing_space(rs.getFloat(10));
        	as.setAvailable_space(rs.getFloat(11));
        	as.setOccupied_space(rs.getFloat(12));
        	schedule.add(as);
        }   
        
        if(schedule.isEmpty()) {
        	a.print("No sailing schdedule");
        }else if(schedule.size()==1){
        	a.print("1 saling schedule found");
        	for(int i=0;i<schedule.size();i++) {
                a.println("<tr>\n"+ "<td>" + schedule.get(i).getID() + "</td>");
                a.println("<td>" + schedule.get(i).getDeparture_date() + "</td>");
                a.println("<td>" + schedule.get(i).getDeparture_day() + "</td>");
                a.println("<td>" + schedule.get(i).getArrival_date() + "</td>");
                a.println("<td>" + schedule.get(i).getArrival_day() + "</td>");
                a.println("<td>" + schedule.get(i).getDeparture_time() + "</td>");
                a.println("<td>" + schedule.get(i).getArrival_time() + "</td>");
                a.println("<td>" + schedule.get(i).getDeparture_location() + "</td>");
                a.println("<td>" + schedule.get(i).getArrival_location() + "</td>");
                a.println("<td>" + schedule.get(i).getSailing_space() + "</td>");
                a.println("<td>" + schedule.get(i).getAvailable_space() + "</td>");                        
                a.println("<td>" + schedule.get(i).getOccupied_space() + "</td>\n"+"</tr>");
                
        	}//end for
        	
        	out.print("<br><br>");
			request.getRequestDispatcher("ag_bookvessel.jsp").include(request, response);
			
        }else if(schedule.size()>1){
        	a.print(schedule.size() + " sailing schedules found");
        	for(int i=0;i<schedule.size();i++) {
            	
                    a.println("<tr>\n"+ "<td>" + schedule.get(i).getID() + "</td>");
                    a.println("<td>" + schedule.get(i).getDeparture_date() + "</td>");
                    a.println("<td>" + schedule.get(i).getDeparture_day() + "</td>");
                    a.println("<td>" + schedule.get(i).getArrival_date() + "</td>");
                    a.println("<td>" + schedule.get(i).getArrival_day() + "</td>");
                    a.println("<td>" + schedule.get(i).getDeparture_time() + "</td>");
                    a.println("<td>" + schedule.get(i).getArrival_time() + "</td>");
                    a.println("<td>" + schedule.get(i).getDeparture_location() + "</td>");
                    a.println("<td>" + schedule.get(i).getArrival_location() + "</td>");
                    a.println("<td>" + schedule.get(i).getSailing_space() + "</td>");
                    a.println("<td>" + schedule.get(i).getAvailable_space() + "</td>");                        
                    a.println("<td>" + schedule.get(i).getOccupied_space() + "</td>\n"+"</tr>");
            	
        	}//end for 
        	out.print("<br><br>");
			request.getRequestDispatcher("ag_bookvessel.jsp").include(request, response);
        }//end else
        
        conn.close();
        pst.close();
        rs.close();
        
		
		
	}catch(ClassNotFoundException | SQLException e) {
		e.printStackTrace();
	}
	

	
}//try printwriter



%>

</body>
</html>