<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Reservation Details</title>
</head>
<body>
    <h2>All Reservations</h2>
    <table border="1">
        <thead>
            <tr>
                <th>Reservation ID</th>
                <th>Flight Number</th>
                <th>Operating Airlines</th>
                <th>Departure City</th>
                <th>Arrival City</th>
                <th>Date Of Departure</th>
                <th>Estimated Departure Time</th>
                <th>Passenger Name</th>
                <th>Email</th>
                <th>Phone</th>
                <th>Checked-In Status</th>
                <th>Number of Bags</th>
                <th>Action</th> <!-- Add a new column for actions -->
            </tr>
        </thead>
        <tbody>
            <c:forEach var="reservation" items="${reservationDetailsList}">
                <tr>
                    <td>${reservation.id}</td>
                    <td>${reservation.flightNumber}</td>
                    <td>${reservation.operatingAirlines}</td>
                    <td>${reservation.departureCity}</td>
                    <td>${reservation.arrivalCity}</td>
                    <td>${reservation.dateOfDeparture}</td>
                    <td>${reservation.estimatedDepartureTime}</td>
                    <td>${reservation.passengerName}</td>
                    <td>${reservation.email}</td>
                    <td>${reservation.phone}</td>
                    <td>${reservation.checkedIn}</td>
                    <td>${reservation.numberOfBags}</td>
                    <td>
                        <form action="update" method="post">
                            <input type="hidden" name="id" value="${reservation.id}">
                            <label>Checked-In:</label>
                            <input type="checkbox" name="checkedIn" ${reservation.checkedIn ? 'checked' : ''}>
                            <br>
                            <label>Number of Bags:</label>
                            <input type="number" name="numberOfBags" value="${reservation.numberOfBags}">
                            <br>
                            <input type="submit" value="Update">
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <form action="logout" method="post">
            <input type="submit" value="Logout">
        </form>
</body>
</html>
