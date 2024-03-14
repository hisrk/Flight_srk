<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="UTF-8">
<title>Flight Details</title>
</head>
<body>
    <h2>Flight Details</h2>
    <table border="1">
        <tr>
            <th>Flight Number</th>
            <th>Operating Airlines</th>
            <th>Departure City</th>
            <th>Arrival City</th>
            <th>Date of Departure</th>
            <th>Estimated Departure Time</th>
            <th>Delete Flight<th>
        </tr>
        <c:forEach items="${flights}" var="Flights">

        		<tr>
        		   <td>${Flights.flightNumber}</td>
        			<td>${Flights.operatingAirlines}</td>
        			<td>${Flights.departureCity}</td>
        			<td>${Flights.arrivalCity}</td>
        			<td>${Flights.dateOfDepartureStr}</td>
        			<td>${Flights.estimatedDepartureTimeStr}</td>
        			<td><a href="delete?flightId=${Flights.getId()}">Delete</a>
        			<td><a href="showCompleteReservation?flightId=${Flights.getId()}">Book Flight</a>
        		</tr>

        </c:forEach>

    </table>
     <!-- Logout button -->
        <form action="logout" method="post">
            <input type="submit" value="Logout">
        </form>
</body>
</html>
