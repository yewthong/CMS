<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin View and Add Destination</title>
</head>
<body>

<form action="ADaddlocation" method="post">

	<table>
		<tr>
			<td>
				Location Name:
			</td>
			<td>
				<input type="text" name="location_name" size="70" required>
			</td>
		</tr>
	</table>
	
	<p>
		<input type="submit" value="Submit">
	</p>


</form>

</body>
</html>