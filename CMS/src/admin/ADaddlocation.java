package admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.Currentuser;

/**
 * Servlet implementation class ADaddlocation
 */
@WebServlet("/ADaddlocation")
public class ADaddlocation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ADaddlocation() {
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
		
		HttpSession session = request.getSession();
		Currentuser cu = (Currentuser)session.getAttribute("user");
		
		String locationname = request.getParameter("location_name");
		
		try(PrintWriter out = response.getWriter()){
			try {
	            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	            String connectionURL = "jdbc:sqlserver://ddaccmsserver.database.windows.net;databaseName=CMS;user=demouser;password=Demo@pass123;";
	            Connection conn = DriverManager.getConnection(connectionURL);
	            String sql = "INSERT INTO shipping_location values (?)";
	            PreparedStatement pst =conn.prepareStatement(sql);
	            pst.setString(1, locationname);
	            pst.execute();
	            
	            
	            pst.close();
	            conn.close();
	            
	            request.getRequestDispatcher("admin_menu.jsp").include(request, response);
	            out.print("Success, shipping destination "  + locationname +" had been added.");
	            
			}catch(ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			
			
		}
		
		
		
		
		doGet(request, response);
	}

}
