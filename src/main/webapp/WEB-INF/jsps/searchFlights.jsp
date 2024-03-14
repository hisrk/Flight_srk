<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Search Flights</title>
<!-- Include jQuery library -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<!-- Include jQuery UI library for date picker -->
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
    $(function() {
        // Initialize date picker for the departure date field
        $("#departureDate").datepicker({
            dateFormat: 'yy-mm-dd' // Format the date as 'YYYY-MM-DD'
        });
    });
</script>
</head>
<body>
    <h2>Find Flights</h2>
    <form action="findFlights" method="post">
        from: <input type="text" name="from"><br>
        to: <input type="text" name="to"><br>
        departure date: <input type="text" id="departureDate" name="departureDate"><br> <!-- Use date picker for departure date -->
        <input type="submit" value="search">
    </form>
     <!-- Logout button -->
        <form action="logout" method="post">
            <input type="submit" value="Logout">
        </form>
</body>
</html>
