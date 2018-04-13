package agent;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.Currentuser;

/**
 * Servlet implementation class AGBookConfirmation
 */
@WebServlet("/AGBookConfirmation")
public class AGBookConfirmation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AGBookConfirmation() {
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
		    out.println("<title>" + "Book Vessel" + "</title></head>");
			request.getRequestDispatcher("agent_menu.jsp").include(request, response);
			out.println("<h2>Book Confirmation</h2>");
			
			out.print("You have successfully made the booking. Details are as below.");
            out.println("<table border=\"1\" width=\"100%\">\n"
                    + "            <tr>\n"
                    + "                <th>Customer Name</th>\n"
                    + "                <th>Item Name</th>\n"
                    + "                <th>Item Unit</th>\n"
                    + "                <th>Item Quantity</th>\n"
                    + "                <th>Vessel ID</th>\n"
                    + "                <th>Item Weight</th>\n"
                    + "            </tr>");
            
            
            out.println("<tr>\n"+ "<td>" + request.getAttribute("customername") + "</td>");
            out.println("<td>" + request.getAttribute("itemname") + "</td>");
            out.println("<td>" + request.getAttribute("itemunit") + "</td>");
            out.println("<td>" + request.getAttribute("itemquantity") + "</td>");
            out.println("<td>" + request.getAttribute("vesselid") + "</td>");                     
            out.println("<td>" + request.getAttribute("itemweight") + "</td>\n"+"</tr>");
            
            
		}//printwriter
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
