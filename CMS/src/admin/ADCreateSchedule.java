package admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.Currentuser;

/**
 * Servlet implementation class ADCreateSchedule
 */
@WebServlet("/ADCreateSchedule")
public class ADCreateSchedule extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ADCreateSchedule() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession(false);
		Currentuser cu = (Currentuser)session.getAttribute("user");
		
		String departuredate = request.getParameter("departure_date");
		String depatureday = request.getParameter("departure_day");
		String arrivaldate = request.getParameter("arrival_date");
		String arrivalday = request.getParameter("arrival_day");
		String departuretime = request.getParameter("dtime");
		String arrivaltime = request.getParameter("atime");
		String departurelocation = request.getParameter("dlocation");
		String arrivallocation = request.getParameter("alocation");
		float space = Float.parseFloat(request.getParameter("sail_space"));
		String type = request.getParameter("sail_type");
		
		int id;
		float space2;
		
		
		try(PrintWriter out = response.getWriter()){
			try{
	            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	            String connectionURL = "jdbc:sqlserver://ddaccmsserver.database.windows.net;databaseName=CMS;user=demouser;password=Demo@pass123;";
	            Connection conn = DriverManager.getConnection(connectionURL);
	            
	            
	            if(!(departurelocation.equals("0"))) {
	            	if(arrivallocation.equals("0")) {
	            		request.setAttribute("locationerror", "2");
	            		request.getRequestDispatcher("ad_createsailschedule.jsp").forward(request, response);
	            	}
	            	
		            String sql = "Insert into schedule values (?,?,?,?,?,?,?,?,?,?)";            
		            PreparedStatement pst =conn.prepareStatement(sql);
		            pst.setString(1, departuredate);
		            pst.setString(2, depatureday);
		            pst.setString(3, arrivaldate);
		            pst.setString(4, arrivalday);
		            pst.setString(5, departuretime);
		            pst.setString(6, arrivaltime);
		            pst.setString(7, departurelocation);
		            pst.setString(8, arrivallocation);
		            pst.setFloat(9, space);
		            pst.setString(10, type);
		            pst.execute();
		            
		            String sql2 = "SELECT * FROM schedule WHERE departure_date='" + departuredate+ "' AND departure_day='" +depatureday+
		            		"' AND arrival_date='" +arrivaldate+ "' AND arrival_day='" +arrivalday+ "' AND departure_time='" +departuretime+
		            		"' AND arrival_time='" +arrivaltime+ "'AND departure_location='" +departurelocation+ "' AND arrival_location='" 
		            		+arrivallocation+ "' AND sailing_space=" +space+ " AND sailing_type='" + type+ "';";
			        PreparedStatement pst2 = conn.prepareStatement(sql2);
			        ResultSet rs = pst2.executeQuery();		
			        
			        while(rs.next()) {
			        	id=rs.getInt(1);
			        	space2 = rs.getInt(10);
			        	String sql3 = "INSERT INTO schedule_space VALUES (?,?,?,?)";
			        	PreparedStatement pst3 = conn.prepareStatement(sql3);
			        	pst3.setInt(1,id);
			        	pst3.setFloat(2,space2);
			        	pst3.setFloat(3,space2);
			        	pst3.setInt(4,0);
			        	pst3.execute();
			        	pst3.close();
			        }
		            pst.close();
		            pst2.close();
		            conn.close();
		            
		            
		            request.getRequestDispatcher("admin_menu.jsp").include(request, response);
		            out.print("<br>");
		            out.print("Schedule Updated.");
	
	            	
	            }else {
            		request.setAttribute("locationerror", "1");
            		request.getRequestDispatcher("ad_createsailschedule.jsp").forward(request, response);
	            }
			}catch(ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}
		doGet(request, response);
	}

}
