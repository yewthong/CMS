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
import data.LocationData;

/**
 * Servlet implementation class ADViewLocation
 */
@WebServlet("/ADViewLocation")
public class ADViewLocation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ADViewLocation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		Currentuser cu = (Currentuser)session.getAttribute("user");
		
		try(PrintWriter out = response.getWriter()){

	        out.println("<!DOCTYPE html>");
	        out.println("<html><head>");
	        out.println("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
	        out.println("<title>" + "Destination" + "</title></head>");
			request.getRequestDispatcher("admin_menu.jsp").include(request, response);
			out.println("<h2>View Location</h2>");
//			request.getRequestDispatcher("ad_locationtitle.jsp").include(request, response);
			
            out.println("<table border=\"1\" width=\"100%\">\n"
                    + "            <tr>\n"
                    + "                <th>Location</th>\n"
                    + "            </tr>");
            
            try {
	            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	            String connectionURL = "jdbc:sqlserver://ddaccmsserver.database.windows.net;databaseName=CMS;user=demouser;password=Demo@pass123;";
	            Connection conn = DriverManager.getConnection(connectionURL);
	            String sql = "SELECT location_name FROM shipping_location";
	            
		        PreparedStatement pst = conn.prepareStatement(sql);
		        ResultSet rs = pst.executeQuery();	
		        List<LocationData> location = new ArrayList<LocationData>();
		        
		        
	            while(rs.next()) {
	            	LocationData ld = new LocationData();
	            	ld.setLocation(rs.getString(1));
	            	location.add(ld);

	            }
	            


	            if(!(location.size() == 0)){
		            	out.print(location.size() + " sailing schedules found");
		            	for(int i=0;i<location.size();i++) {                    
	                        out.println("<tr>\n"+  "<td>" + location.get(i).getLocation() + "</td>\n"+"</tr>");
		            	}//end for 
		            }//end else
		            
		            conn.close();
		            pst.close();
		            rs.close();
		            
		            request.getRequestDispatcher("ad_destination.jsp").include(request, response);
		            
		            
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
