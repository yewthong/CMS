package agent;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.AvailableSail;
import data.Currentuser;
import data.SailSpace;
import data.Schedule;

/**
 * Servlet implementation class AGViewschedule
 */
@WebServlet("/AGViewschedule")
public class AGViewschedule extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AGViewschedule() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession(false);
		Currentuser cu = (Currentuser)session.getAttribute("user");
		
		try(PrintWriter out = response.getWriter()){
			
			request.getRequestDispatcher("agent_menu.jsp").include(request, response);
            out.println("<table border=\"1\" width=\"100%\">\n"
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
	            	out.print("No sailing schdedule");
	            }else if(schedule.size()==1){
	            	out.print("1 saling schedule found");
	            	for(int i=0;i<schedule.size();i++) {
                        out.println("<tr>\n"+ "<td>" + schedule.get(i).getID() + "</td>");
                        out.println("<td>" + schedule.get(i).getDeparture_date() + "</td>");
                        out.println("<td>" + schedule.get(i).getDeparture_day() + "</td>");
                        out.println("<td>" + schedule.get(i).getArrival_date() + "</td>");
                        out.println("<td>" + schedule.get(i).getArrival_day() + "</td>");
                        out.println("<td>" + schedule.get(i).getDeparture_time() + "</td>");
                        out.println("<td>" + schedule.get(i).getArrival_time() + "</td>");
                        out.println("<td>" + schedule.get(i).getDeparture_location() + "</td>");
                        out.println("<td>" + schedule.get(i).getArrival_location() + "</td>");
                        out.println("<td>" + schedule.get(i).getSailing_space() + "</td>");
                        out.println("<td>" + schedule.get(i).getAvailable_space() + "</td>");                        
                        out.println("<td>" + schedule.get(i).getOccupied_space() + "</td>\n"+"</tr>");

	            	}
	            	
	            	out.print("<br>");
	    			request.getRequestDispatcher("ag_bookvessel.jsp").include(request, response);
	            	
	            }else if(schedule.size()>1){
	            	out.print(schedule.size() + " sailing schedules found");
	            	for(int i=0;i<schedule.size();i++) {
		            	
	                        out.println("<tr>\n"+ "<td>" + schedule.get(i).getID() + "</td>");
	                        out.println("<td>" + schedule.get(i).getDeparture_date() + "</td>");
	                        out.println("<td>" + schedule.get(i).getDeparture_day() + "</td>");
	                        out.println("<td>" + schedule.get(i).getArrival_date() + "</td>");
	                        out.println("<td>" + schedule.get(i).getArrival_day() + "</td>");
	                        out.println("<td>" + schedule.get(i).getDeparture_time() + "</td>");
	                        out.println("<td>" + schedule.get(i).getArrival_time() + "</td>");
	                        out.println("<td>" + schedule.get(i).getDeparture_location() + "</td>");
	                        out.println("<td>" + schedule.get(i).getArrival_location() + "</td>");
	                        out.println("<td>" + schedule.get(i).getSailing_space() + "</td>");
	                        out.println("<td>" + schedule.get(i).getAvailable_space() + "</td>");                        
	                        out.println("<td>" + schedule.get(i).getOccupied_space() + "</td>\n"+"</tr>");
		            	
	            	}//end for 
	            	
	            	out.print("<br>");
	    			request.getRequestDispatcher("ag_bookvessel.jsp").include(request, response);
	            	
	            }//end else
	            
	            conn.close();
	            pst.close();
	            rs.close();
		        
				
	            
	            
	            
				
			}catch(ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			
//			out.print("<br>");
//			request.getRequestDispatcher("ag_bookvessel.jsp").include(request, response);
			
		}//try printwriter
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
