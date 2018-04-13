<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Register Agent</title>
	<jsp:include page="admin_menu.jsp"></jsp:include>
	<h2>Register Agent</h2>
	<p>Please fill in the details below.</p>
</head>
<body>

	<form action="ADRegisterAgent" method="post">
            <table>
                <tr>
                    <td>
                        Agent Name:
                    </td> 
                    <td>
                        <input type="text" name="agent_name" size="50" required>
                    </td>
                </tr>
                
                <tr>
                    <td>
                        IC/Passport:
                    </td> 
                    <td>
                        <input type="text" name="agent_icp" size="50" required>
                    </td>
                </tr>
                
                <tr>
                    <td>
                        Password:
                    </td> 
                    <td>
                        <input type="password" name="agent_password" size="50" required>
                    </td>
                </tr>
                
                <tr>
                    <td>
                        Gender:
                    </td> 
                    <td>
                        <input type="radio" name="agent_gender" size="20" value="M" checked="true">Male
                        <input type="radio" name="agent_gender" size="20" value="F">Female
                    </td>
                </tr>
                
                <tr>
                    <td>
                        Address:
                    </td> 
                    <td>
                        <input type="text" name="agent_address" size="50" required>
                    </td>
                </tr>
                
                <tr>
                    <td>
                        Phone Number:
                    </td> 
                    <td>
                        <input type="text" name="agent_phone" size="50" required>
                    </td>
                </tr>
                <tr>
                    <td>
                        Email Address:
                    </td> 
                    <td>
                        <input type="text" name="agent_email" size="50" required>
                    </td>
                </tr>

            </table>
            
            <p>
            	<input type="submit" value="Submit">
            </p>
	</form>
</body>
</html>