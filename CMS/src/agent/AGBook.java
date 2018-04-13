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
import data.BookingSpace;
import data.Currentuser;

/**
 * Servlet implementation class AGBook
 */
@WebServlet("/AGBook")
public class AGBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AGBook() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String customername,itemname,itemunit;
		int quantity,vesselid;
		float weight;
		String vesselid2;
		
		customername = request.getParameter("customer_name");
		itemname = request.getParameter("item_name"); 
		itemunit = request.getParameter("item_unit"); 
		
		quantity = Integer.parseInt(request.getParameter("item_quantity")); 
		vesselid2 = request.getParameter("vessel_ID"); 
		vesselid = Integer.parseInt(request.getParameter("vessel_ID")); 
		weight = Float.parseFloat(request.getParameter("item_weight"));
		
		
        HttpSession session = request.getSession(false);
        Currentuser cu = (Currentuser)session.getAttribute("user"); 
		
        
        
		try (PrintWriter out = response.getWriter()){
			
			try {
	            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	            String connectionURL = "jdbc:sqlserver://ddaccmsserver.database.windows.net;databaseName=CMS;user=demouser;password=Demo@pass123;";
	            Connection conn = DriverManager.getConnection(connectionURL);
	            
	            
	            //update schedule_space
	            //1.select from schedule_space
	            String spacesql = "SELECT * FROM schedule_space WHERE schedule_ID='" + vesselid + "'";            
	            
		        PreparedStatement spacepst = conn.prepareStatement(spacesql);
		        ResultSet rs = spacepst.executeQuery();	
		        List<BookingSpace> space = new ArrayList<BookingSpace>();
		        BookingSpace bs = new BookingSpace();
		        
		        while(rs.next()) {
		        	bs.setScheduleid(rs.getInt(1));
		        	bs.setTotalspace(rs.getFloat(3));
		        	bs.setAvaialblespace(rs.getFloat(4));
		        	bs.setOccupied_space(rs.getFloat(5));
		        	space.add(bs);
		        	
		        }   
		        
		        float oriavailablespace = bs.getAvaialblespace();
		        float total = oriavailablespace-weight;
		        
		        
		        if(!(customername.equals("0") || customername.equals(null))) {
		        	if(!(vesselid2.equals("0"))) {
		        		if(total <0) {
				        	request.setAttribute("full", "1");;
				        	request.getRequestDispatcher("ag_vesselinfo.jsp").include(request, response);
		        		}else {

		        			//2.update schedule_space
					        int id = bs.getScheduleid();
					        float newavailablespace = bs.getAvaialblespace() - weight;
					        float newoccupiedspace = bs.getOccupied_space() + weight;
					        
					        out.print(id);
					        out.print("getavailable space is " + bs.getAvaialblespace());
					        out.print("getoccupied space is " + bs.getOccupied_space());
					        out.print("gettotal space is " + bs.getTotalspace());
					        
					        
					        String spacesql2 = "UPDATE schedule_space SET available_space = '" + newavailablespace+"', occupied_space='" +newoccupiedspace
					        		+"' WHERE schedule_ID = " +id+ "";
					        PreparedStatement spacepst2 = conn.prepareStatement(spacesql2);
					        spacepst2.executeUpdate();
					        		
					          //update booking table
				            String bookingsql = "Insert into booking values (?,?,?,?,?,?,?)";            
				            PreparedStatement bookingpst =conn.prepareStatement(bookingsql);
				            bookingpst.setString(1, customername);
				            bookingpst.setString(2, itemname);
				            bookingpst.setInt(3, quantity);
				            bookingpst.setString(4, itemunit);
				            bookingpst.setFloat(5, weight);
				            bookingpst.setInt(6, vesselid);
				            bookingpst.setString(7, cu.getName());
				            bookingpst.execute();     
					        
				            //insert into item
				            String itemsql = "Insert into item values (?,?,?,?,?)";
				            PreparedStatement itempst =conn.prepareStatement(itemsql);
				            itempst.setString(1, customername);
				            itempst.setString(2, itemname);
				            itempst.setInt(3, quantity);
				            itempst.setString(4, itemunit);
				            itempst.setFloat(5, weight);
				            itempst.execute();   
				            
				            //close everything
				            bookingpst.close();
					        spacepst.close();
					        rs.close();
					        spacepst2.close();
				            itempst.close();
				            conn.close();
				            
							request.setAttribute("customername", customername);
							request.setAttribute("itemname", itemname);
							request.setAttribute("itemunit", itemunit);
							request.setAttribute("itemquantity", quantity);
							request.setAttribute("vesselid", vesselid);
							request.setAttribute("itemweight", weight);
							request.getRequestDispatcher("AGBookConfirmation").forward(request, response);		        			
		        			
		        		}//if (total<0)
		        	}else {
	        			request.setAttribute("emptyvessel", "1");;
			        	request.getRequestDispatcher("ag_vesselinfo.jsp").include(request, response);
		        	}//if (!(vesselid2.equals("0")))
		        }else {
		        	request.setAttribute("emptycustomer", "1");
		        	request.getRequestDispatcher("ag_vesselinfo.jsp").include(request, response);
		        }//if(!(customername.equals("0") || customername.equals(null))) 
				
			}catch(ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			
			
			//request.getRequestDispatcher("ag_bookconfirmation.jsp").forward(request, response);
			

			
			
		}//try printwriter
		
		doGet(request, response);
	}

}
