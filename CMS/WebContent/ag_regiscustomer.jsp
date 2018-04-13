<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Register Customer</title>
	<jsp:include page="agent_menu.jsp"></jsp:include>
	<h2>Register Customer</h2>
	<p>Please fill in the details below.</p>
</head>
<body>

<form action="AGRegisterCustomer" method="post">
	<table>
		<tr>
			<td>
				Customer Name :
			</td>
			<td>
				<input type="text" name="customer_name" size="30" required/>
			</td>
		</tr>
		<tr>
			<td>
				IC/Passport :
			</td>
			<td>
				<input type="text" name="customer_icp" size="30" required/>
			</td>
		</tr>
		<tr>
			<td>
				Gender :
			</td>
			<td>
				<input type="radio" name="customer_gender" value='M' checked="checked">Male
				<input type="radio" name="customer_gender" value='F' >Female
			</td>
		</tr>
		<tr>
			<td>
				Address :
			</td>
			<td>
				<input type="text" name="customer_address" size="30" required/>
			</td>
		</tr>		
		<tr>
			<td>
				Phone number :
			</td>
			<td>
				<input type="text" name="customer_hp" size="30" required/>
			</td>
		</tr>
		<tr>
			<td>
				Email Address :
			</td>
			<td>
				<input type="text" name="customer_email" size="30" required/>
			</td>
		</tr>		
	</table>
	<p>
		<input type="submit" value="submit">
	</p>
</form>

</body>
</html>