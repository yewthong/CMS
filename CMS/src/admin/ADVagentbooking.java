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

import data.Agentbookinginfo;
import data.BookingSpace;
import data.Currentuser;

/**
 * Servlet implementation class ADVagentbooking
 */
@WebServlet("/ADVagentbooking")
public class ADVagentbooking extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ADVagentbooking() {
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
		
		HttpSession session = request.getSession();
		Currentuser cu = (Currentuser)session.getAttribute("user");
		
		String name = request.getParameter("agent_name");
		
		try(PrintWriter out = response.getWriter()){
			
			request.getRequestDispatcher("ad_viewagentbooking.jsp").include(request, response);
			
            out.println("<table border=\"1\" width=\"100%\">\n"
                    + "            <tr>\n"
                    + "                <th>Agent Name</th>\n"
                    + "                <th>Item Name</th>\n"
                    + "                <th>Item Unit</th>\n"
                    + "                <th>Item Quantity</th>\n"
                    + "                <th>Vessel ID</th>\n"
                    + "                <th>Item Weight</th>\n"
                    + "                <th>Agent name</th>\n"
                    + "            </tr>");
            out.print("<br><br>");
            
            
            try {

	            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	            String connectionURL = "jdbc:sqlserver://ddaccmsserver.database.windows.net;databaseName=CMS;user=demouser;password=Demo@pass123;";
	            Connection conn = DriverManager.getConnection(connectionURL);
	            String sql = "SELECT * FROM booking WHERE agent_name='" + name + "'";            
	            
		        PreparedStatement pst = conn.prepareStatement(sql);
		        ResultSet rs = pst.executeQuery();	
		        List<Agentbookinginfo> info = new ArrayList<Agentbookinginfo>();
		        Agentbookinginfo abi = new Agentbookinginfo();

		        
		        while(rs.next()) {
		        	
		        	abi.setCusname(rs.getString(2));
		        	abi.setItemname(rs.getString(3));
		        	abi.setItemquantity(rs.getInt(4));
		        	abi.setItemunit(rs.getString(5));
		        	abi.setItemweight(rs.getFloat(6));
		        	abi.setVesselid(rs.getInt(7));
		        	abi.setAgentname(rs.getString(8));
		        	info.add(abi);
		        	
		        }   
		        
		        if(info.isEmpty()) {
		        	out.print("Agent " + name + " has no booking");
		        }else if(info.size()==1) {
		        	out.print("Agent " + name + " has 1 booking");
		        	
	            	for(int i=0;i<info.size();i++) {
                        out.println("<tr>\n"+ "<td>" + info.get(i).getCusname() + "</td>");
                        out.println("<td>" + info.get(i).getItemname() + "</td>");   
                        out.println("<td>" + info.get(i).getItemquantity() + "</td>");
                        out.println("<td>" + info.get(i).getItemunit() + "</td>");
                        out.println("<td>" + info.get(i).getItemweight() + "</td>");
                        out.println("<td>" + info.get(i).getVesselid() + "</td>");                 
                        out.println("<td>" + info.get(i).getAgentname() + "</td>\n"+"</tr>");
	            	}

		        }else if(info.size()>1) {
		        	out.print("Agent " + name + " has "+info.size() + " bookings");
		        	
	            	for(int i=0;i<info.size();i++) {
                        out.println("<tr>\n"+ "<td>" + info.get(i).getCusname() + "</td>");
                        out.println("<td>" + info.get(i).getItemname() + "</td>");   
                        out.println("<td>" + info.get(i).getItemquantity() + "</td>");
                        out.println("<td>" + info.get(i).getItemunit() + "</td>");
                        out.println("<td>" + info.get(i).getItemweight() + "</td>");
                        out.println("<td>" + info.get(i).getVesselid() + "</td>");                 
                        out.println("<td>" + info.get(i).getAgentname() + "</td>\n"+"</tr>");
	            	}
		        }
		        
		        rs.close();
		        pst.close();
	            conn.close();
				
            }catch(ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
	       
            
		}//printwriter
		
		
		doGet(request, response);
	}

}
