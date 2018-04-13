package general;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.Agentbookinginfo;
import data.Currentuser;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
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
		String name = request.getParameter("username");
		String password = request.getParameter("password");
		
		
		try (PrintWriter out = response.getWriter()){                
	        try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		        String connectionURL = "jdbc:sqlserver://ddaccmsserver.database.windows.net;databaseName=CMS;user=demouser;password=Demo@pass123;";
		        //String connectionURL = "jdbc:sqlserver://localhost:1433;databaseName=DDAC;user=crayon;password=12345;";
		        Connection conn = DriverManager.getConnection(connectionURL);
		        String sql = "Select * from logins where name ='" + name + "'" ;
		        PreparedStatement pst = conn.prepareStatement(sql);
		        ResultSet rs = pst.executeQuery();		
		        List<Currentuser> info = new ArrayList<Currentuser>();
	        	Currentuser cu = new Currentuser();
		        
		        while(rs.next()) {
		        	cu.setICPassport(rs.getString("ICPassport"));
		        	cu.setName(rs.getString("name"));
		        	cu.setPassword(rs.getString("password"));
		        	cu.setGender(rs.getString("gender").charAt(0));
                    cu.setAddress(rs.getString("address"));      
                    cu.setHp(rs.getString("phone_number"));
                    cu.setEmail(rs.getString("email_address"));
                    cu.setRole(rs.getString("role"));
                    info.add(cu);
                    
                    if(info.isEmpty()) {
                    	request.setAttribute("wrongusernamepassword", "2");
	                	request.getRequestDispatcher("login.jsp").forward(request, response);
                    }else{
                    	for(int i=0;i<info.size();i++) {
                    		//if password matches
                    		if(password.equals(cu.getPassword())) {
    		                    switch(cu.getRole()) {
    		                    //admin role
    		                    	case "admin":
    		                    		request.getRequestDispatcher("admin_menu.jsp").include(request, response);
    		                    		out.println("<br>");
    		                    		if(cu.getGender()=='M') {
    		                    			out.println("Welcome Mr. " +cu.getName() + ", You are currently login as Admin.");
    		                    		}else if(cu.getGender()=='F') {
    		                    			out.println("Welcome Ms. " +cu.getName() + ", You are currently login as Admin.");
    		                    		}
    		                    		break;
    		                    	
    		                    		//agent role
    		                    	case "agent": 
    		                    		request.getRequestDispatcher("agent_menu.jsp").include(request, response);
    		                    		out.println("<br>");
    		                    		if(cu.getGender()=='M') {
    		                    			out.println("Welcome Mr. " +cu.getName() + ", You are currently login as Agent.");
    		                    		}else if(cu.getGender()=='F') {
    		                    			out.println("Welcome Ms. " +cu.getName() + ", You are currently login as Agent.");
    		                    		}
    		                    		break;
    		                    }//switch
    		                    HttpSession session = request.getSession();
    		                    session.setAttribute("user", cu);    		                    
                    		}else {
    		                	request.setAttribute("wrongusernamepassword", "1");
    		                	request.getRequestDispatcher("login.jsp").forward(request, response);                    			
                    		}
                    	}
                    }//if info not empty
		        }
		        
	            rs.close();
	            pst.close();
	            conn.close();
			
	        
	        } catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}//try printwriter 
		
	}	

}
