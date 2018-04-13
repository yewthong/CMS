package agent;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.*;

import data.Currentuser;

/**
 * Servlet implementation class AGRegisterCustomer
 */
@WebServlet("/AGRegisterCustomer")
public class AGRegisterCustomer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AGRegisterCustomer() {
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
		
        String name = request.getParameter("customer_name");
        String icp = request.getParameter("customer_icp");
        String gender = request.getParameter("customer_gender");
        String address = request.getParameter("customer_address");
        String hp    = request.getParameter("customer_hp");
        String email = request.getParameter("customer_email");      
		
		try(PrintWriter out = response.getWriter()){
			
	        HttpSession session = request.getSession(false);
	        Currentuser su = (Currentuser)session.getAttribute("user"); 		
	        
	        
	        try {
	            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	            String connectionURL = "jdbc:sqlserver://ddaccmsserver.database.windows.net;databaseName=CMS;user=demouser;password=Demo@pass123;";
	            Connection conn = DriverManager.getConnection(connectionURL);
	            String sql = "Insert into customer values (?,?,?,?,?,?)";            
	            PreparedStatement pst =conn.prepareStatement(sql);
	            pst.setString(1, icp);
	            pst.setString(2, name);
	            pst.setString(3, String.valueOf(gender));
	            pst.setString(4, address);
	            pst.setString(5, hp);
	            pst.setString(6, email);
	            pst.execute();          
	            
	            request.getRequestDispatcher("agent_menu.jsp").include(request, response);
	                    out.print("<br>");
	                    out.print("Success, customer "  + name +" had been registered.");
	                    
	            pst.close();
	            conn.close();	        	
	        }catch(ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}
		
		doGet(request, response);
	}

}
