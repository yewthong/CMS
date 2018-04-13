package admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.Currentuser;

/**
 * Servlet implementation class ADRegisterAgent
 */
@WebServlet("/ADRegisterAgent")
public class ADRegisterAgent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ADRegisterAgent() {
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
	
		HttpSession session = request.getSession(false);
		Currentuser cu = (Currentuser)session.getAttribute("user");
		
        String name = request.getParameter("agent_name");
        String icp = request.getParameter("agent_icp");
        String password = request.getParameter("agent_password");
        String gender = request.getParameter("agent_gender");
        String address = request.getParameter("agent_address");
        String hp    = request.getParameter("agent_phone");
        String email = request.getParameter("agent_email");      
        String role  = "agent";
		
		
		try(PrintWriter out = response.getWriter()){
			

			
			try {
	            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	            String connectionURL = "jdbc:sqlserver://ddaccmsserver.database.windows.net;databaseName=CMS;user=demouser;password=Demo@pass123;";
	            Connection conn = DriverManager.getConnection(connectionURL);
	            String sql = "Insert into logins values (?,?,?,?,?,?,?,?)";            
	            PreparedStatement pst =conn.prepareStatement(sql);
	            pst.setString(1, icp);
	            pst.setString(2, name);
	            pst.setString(3, password);
	            pst.setString(4, gender);
	            pst.setString(5, address);
	            pst.setString(6, hp);
	            pst.setString(7, email);
	            pst.setString(8, role);
	            pst.execute();    
	            
	            request.getRequestDispatcher("admin_menu.jsp").include(request, response);
	            out.print("<br>");
	            out.print("Registration success, agent " + name + " had been registered.");
	            
	            pst.close();
	            conn.close();
	            
	            
			}catch(ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}
		doGet(request, response);
	}

}
