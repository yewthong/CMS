<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!--  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">-->
        <link rel="stylesheet" href="css/css/bootstrap.min.css">
        <script src="css/js/bootstrap.min.css"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
		<!--  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>-->
        
</head>

        <Script>
        function login(){
            var a = null;
            a = <%=request.getAttribute("wrongusernamepassword")%>
            if(a == "1"){
            	alert("Wrong Password");
            }else if(a == "2"){
            	alert("Wrong Username");
            }        }
        </Script>



<body onload="login();">
		<nav class="navbar navbar-inverse">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span> 
                    </button>
                    <a class="navbar-brand">Container Management System</a>
                </div>
            </div>
        </nav>
        
    <div class="jumbotron" >
			<div style="background-color:white; color:black; padding:20px;">
		
		    <h2>Maersk Group </h2>
	            <p>Maersk Line is the global container division and the largest operating unit of the 
	            A.P. Moller Maersk Group, a Danish business conglomerate. It is the world's largest container shipping 
	            company having customers through 374 offices in 116 countries. It employs approximately 7,000 sea
	            farers and approximately 25,000 land-based people. Maersk Line operates over 600 vessels and has a 
	            capacity of 2.6 million TEU. The company was founded in 1928.</p>
		</div>
	</div>

        <form action="Login" method="post">
            <table>
                <tr>
                    <td>
                        User Name:
                    </td> 
                    <td>
                        <input type="text" name="username" size="30" required>
                    </td>
                </tr>
                <tr>
                    <td>
                        Password:
                    </td> 
                    <td>
                        <input type="password" name="password" size="30" required>
                    </td>
                </tr>
            </table>
            <p>
                <input type="submit" value="Submit">
            </p>
        </form>     
</body>
</html>