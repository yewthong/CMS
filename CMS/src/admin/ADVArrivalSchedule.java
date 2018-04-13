package admin;

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

import data.Currentuser;
import data.Schedule;

/**
 * Servlet implementation class ADVArrivalSchedule
 */
@WebServlet("/ADVArrivalSchedule")
public class ADVArrivalSchedule extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ADVArrivalSchedule() {
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
	        out.println("<!DOCTYPE html>");
	        out.println("<html><head>");
	        out.println("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
	        out.println("<title>" + "View Sailing Schedule" + "</title></head>");
			request.getRequestDispatcher("admin_menu.jsp").include(request, response);
			out.println("<h2>View Arrival Sailing Schedule</h2>");
//			request.getRequestDispatcher("ad_viewsailscheduletitle.jsp").include(request, response);
			
			request.getRequestDispatcher("ad_sailtype.jsp").include(request, response);
			out.print("<br>");
		
            out.println("<table border=\"1\" width=\"100%\">\n"
                    + "            <tr>\n"
                    + "                <th>Departure Date</th>\n"
                    + "                <th>Departure Day</th>\n"
                    + "                <th>Arrival Date</th>\n"
                    + "                <th>Arrival Day</th>\n"
                    + "                <th>Departure Time</th>\n"
                    + "                <th>Arrival Time</th>\n"
                    + "                <th>Departure Location</th>\n"             
                    + "                <th>Arrival Location</th>\n"
                    + "                <th>Sailing Space</th>\n"
                    + "            </tr>");
			
            
            
			try {
	            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	            String connectionURL = "jdbc:sqlserver://ddaccmsserver.database.windows.net;databaseName=CMS;user=demouser;password=Demo@pass123;";
	            Connection conn = DriverManager.getConnection(connectionURL);
	            String sql = "SELECT departure_date,departure_day,arrival_date,arrival_day,departure_time,arrival_time,departure_location,"
	            		+ "arrival_location, sailing_space FROM schedule where sailing_type='arrival'";
	            
		        PreparedStatement pst = conn.prepareStatement(sql);
		        ResultSet rs = pst.executeQuery();	
		        List<Schedule> schedule = new ArrayList<Schedule>();
		        
	            while(rs.next()) {
	            	Schedule sc = new Schedule();
	            	sc.setDeparture_date(rs.getString(1));
	            	sc.setDeparture_day(rs.getString(2));
	            	sc.setArrival_date(rs.getString(3));
	            	sc.setArrival_day(rs.getString(4));
	            	sc.setDeparture_time(rs.getString(5));       	
	            	sc.setArrival_time(rs.getString(6));
	            	sc.setDeparture_location(rs.getString(7));           	
	            	sc.setArrival_location(rs.getString(8));
	            	sc.setSailing_space(rs.getFloat(9));
	            	schedule.add(sc);
	            }
	            
		            if(schedule.isEmpty()) {
		            	out.print("No sailing schdedule");
		            }else if(schedule.size()==1){
		            	out.print("1 saling schedule found");
		            	for(int i=0;i<schedule.size();i++) {
	                        out.println("<tr>\n"+ "<td>" + schedule.get(i).getDeparture_date() + "</td>");
	                        out.println("<td>" + schedule.get(i).getDeparture_day() + "</td>");
	                        out.println("<td>" + schedule.get(i).getArrival_date() + "</td>");
	                        out.println("<td>" + schedule.get(i).getArrival_day() + "</td>");
	                        out.println("<td>" + schedule.get(i).getDeparture_time() + "</td>");
	                        out.println("<td>" + schedule.get(i).getArrival_time() + "</td>");
	                        out.println("<td>" + schedule.get(i).getDeparture_location() + "</td>");
	                        out.println("<td>" + schedule.get(i).getArrival_location() + "</td>");
	                        out.println("<td>" + schedule.get(i).getSailing_space() + "</td>\n"+"</tr>");
		            	}
		            }else if(schedule.size()>1){
		            	out.print(schedule.size() + " sailing schedules found");
		            	for(int i=0;i<schedule.size();i++) {
	                        out.println("<tr>\n"+ "<td>" + schedule.get(i).getDeparture_date() + "</td>");
	                        out.println("<td>" + schedule.get(i).getDeparture_day() + "</td>");
	                        out.println("<td>" + schedule.get(i).getArrival_date() + "</td>");
	                        out.println("<td>" + schedule.get(i).getArrival_day() + "</td>");
	                        out.println("<td>" + schedule.get(i).getDeparture_time() + "</td>");
	                        out.println("<td>" + schedule.get(i).getArrival_time() + "</td>");
	                        out.println("<td>" + schedule.get(i).getDeparture_location() + "</td>");
	                        out.println("<td>" + schedule.get(i).getArrival_location() + "</td>");
	                        out.println("<td>" + schedule.get(i).getSailing_space() + "</td>\n"+"</tr>");
		            	}//end for 
		            }//end else
		            
		            conn.close();
		            pst.close();
		            rs.close();
		            
			}catch(ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
