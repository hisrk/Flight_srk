<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Final Booking Details</title>
</head>
<body>
	Your booking is confirmed & your booking id is:${reservationId}

	 <!-- Logout button -->
        <form action="logout" method="post">
            <input type="submit" value="Logout">
        </form>
</body>
</html>