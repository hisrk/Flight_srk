<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Home Page</title>
<!-- Include jQuery library -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<!-- Include jQuery UI library for date picker -->
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<!-- Include jQuery UI datetime picker plugin -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-datetimepicker/2.5.20/jquery.datetimepicker.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-datetimepicker/2.5.20/jquery.datetimepicker.full.min.js"></script>
<script>
    $(function() {
        // Initialize date picker for the date of departure field
        $("#dateOfDeparture").datepicker({
            dateFormat: 'yy-mm-dd' // Format the date as 'YYYY-MM-DD'
        });

        // Initialize datetime picker for the estimated departure time field
        $("#estimatedDepartureTime").datetimepicker({
            format: 'Y-m-d H:i', // Format the datetime as 'YYYY-MM-DD HH:mm'
            step: 15 // Set time interval to 15 minutes
        });
    });
</script>
</head>
<body>
    <h2>Add New Flight Details</h2>
    <form action="saveFlight" method="post">
        Flight Number: <input type="text" name="flightNumber"><br>
        Operating Airlines: <input type="text" name="operatingAirlines"><br>
        Departure City: <input type="text" name="departureCity"><br>
        Arrival City: <input type="text" name="arrivalCity"><br>
        Date of Departure: <input type="text" id="dateOfDeparture" name="dateOfDepartureStr"><br> <!-- Use dateOfDepartureStr -->
        Estimated Departure Time: <input type="text" id="estimatedDepartureTime" name="estimatedDepartureTimeStr"><br> <!-- Use estimatedDepartureTimeStr -->
        <input type="submit" value="Save Flight">
    </form>
    <!-- Logout button -->
    <form action="logout" method="post">
        <input type="submit" value="Logout">
    </form>
   <form action="showReservations" method="get">
       <input type="submit" value="Show All Reservations">
   </form>



</body>
</html>
