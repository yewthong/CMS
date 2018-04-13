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
import data.SailSpace;

/**
 * Servlet implementation class ADVArrivalSailSpace
 */
@WebServlet("/ADVArrivalSailSpace")
public class ADVArrivalSailSpace extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ADVArrivalSailSpace() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession(false);
		Currentuser cu = (Currentuser)session.getAttribute("who");
		
		try(PrintWriter out = response.getWriter()){
			
//			request.getRequestDispatcher("admin_menu.jsp").include(request, response);
//			request.getRequestDispatcher("ad_viewspacetitle.jsp").include(request, response);
	        out.println("<!DOCTYPE html>");
	        out.println("<html><head>");
	        out.println("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
	        out.println("<title>" + "View Sailing Space" + "</title></head>");
			request.getRequestDispatcher("admin_menu.jsp").include(request, response);
			out.println("<h2>View Arrival Sailing Space</h2>");
			
			request.getRequestDispatcher("ad_sailtype_space.jsp").include(request, response);
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
                    + "                <th>Total Sailing Space</th>\n"
                    + "                <th>Available Sailing Space</th>\n"
                    + "                <th>Occupied Sailing Space</th>\n"
                    + "            </tr>");
            
            
            
            try {
	            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	            String connectionURL = "jdbc:sqlserver://ddaccmsserver.database.windows.net;databaseName=CMS;user=demouser;password=Demo@pass123;";
	            Connection conn = DriverManager.getConnection(connectionURL);
	            //String sql = "SELECT departure_date,departure_day,arrival_date,arrival_day,departure_time,arrival_time,departure_location,"
	            //		+ "arrival_location, sailing_space FROM schedule where sailing_type='departure'"; 
	            String sql = "SELECT S.departure_date,S.departure_day,S.arrival_date,S.arrival_day,S.departure_time,S.arrival_time,"
	            		+ "S.departure_location,S. arrival_location,S.sailing_space,SS.available_space,SS.occupied_space FROM schedule S "
	            		+ "INNER JOIN schedule_space SS ON S.ID = SS.schedule_ID where sailing_type='arrival'";
	            
		        PreparedStatement pst = conn.prepareStatement(sql);
		        ResultSet rs = pst.executeQuery();	
		        List<SailSpace> sailspace = new ArrayList<SailSpace>();
		        
	            while(rs.next()) {
	            	
	            	SailSpace ss = new SailSpace();
	            	ss.setDeparture_date(rs.getString(1));
	            	ss.setDeparture_day(rs.getString(2));           	
	            	ss.setArrival_date(rs.getString(3));
	            	ss.setArrival_day(rs.getString(4));
	            	ss.setDeparture_time(rs.getString(5));            	
	            	ss.setArrival_time(rs.getString(6));	            	
	            	ss.setDeparture_location(rs.getString(7));
	            	ss.setArrival_location(rs.getString(8));
	            	ss.setSailing_space(rs.getFloat(9));
	            	ss.setAvailable_space(rs.getFloat(10));
	            	ss.setOccupied_space(rs.getFloat(11));
	            	sailspace.add(ss);

	            }
		            if(sailspace.isEmpty()) {
		            	out.print("No sailing schdedule");
		            }else if(sailspace.size()==1){
		            	out.print("1 saling schedule found");
		            	for(int i=0;i<sailspace.size();i++) {
	                        out.println("<tr>\n"+ "<td>" + sailspace.get(i).getDeparture_date() + "</td>");
	                        out.println("<td>" + sailspace.get(i).getDeparture_day() + "</td>");
	                        out.println("<td>" + sailspace.get(i).getArrival_date() + "</td>");
	                        out.println("<td>" + sailspace.get(i).getArrival_day() + "</td>");
	                        out.println("<td>" + sailspace.get(i).getDeparture_time() + "</td>");
	                        out.println("<td>" + sailspace.get(i).getArrival_time() + "</td>");
	                        out.println("<td>" + sailspace.get(i).getDeparture_location() + "</td>");
	                        out.println("<td>" + sailspace.get(i).getArrival_location() + "</td>");
	                        out.println("<td>" + sailspace.get(i).getSailing_space() + "</td>");
	                        out.println("<td>" + sailspace.get(i).getAvailable_space() + "</td>");                        
	                        out.println("<td>" + sailspace.get(i).getOccupied_space() + "</td>\n"+"</tr>");
	                        
		            	}
		            }else if(sailspace.size()>1){
		            	out.print(sailspace.size() + " sailing schedules found");
		            	for(int i=0;i<sailspace.size();i++) {
	                        out.println("<tr>\n"+ "<td>" + sailspace.get(i).getDeparture_date() + "</td>");
	                        out.println("<td>" + sailspace.get(i).getDeparture_day() + "</td>");
	                        out.println("<td>" + sailspace.get(i).getArrival_date() + "</td>");
	                        out.println("<td>" + sailspace.get(i).getArrival_day() + "</td>");
	                        out.println("<td>" + sailspace.get(i).getDeparture_time() + "</td>");
	                        out.println("<td>" + sailspace.get(i).getArrival_time() + "</td>");
	                        out.println("<td>" + sailspace.get(i).getDeparture_location() + "</td>");
	                        out.println("<td>" + sailspace.get(i).getArrival_location() + "</td>");
	                        out.println("<td>" + sailspace.get(i).getSailing_space() + "</td>");
	                        out.println("<td>" + sailspace.get(i).getAvailable_space() + "</td>");                        
	                        out.println("<td>" + sailspace.get(i).getOccupied_space() + "</td>\n"+"</tr>");
		            	}//end for 
		            }//end else
		            
		            conn.close();
		            pst.close();
		            rs.close();
		            
			}catch(ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
            
		}//try printwriter out
		
		
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
