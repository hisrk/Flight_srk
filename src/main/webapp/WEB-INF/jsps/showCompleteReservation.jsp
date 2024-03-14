<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Reservation</title>
</head>
<body>
    <h2>Flight Details</h2>
    Flight Number: ${flights.flightNumber} <br/>
    Operating Airlines: ${flights.operatingAirlines} <br/>
    Departure City: ${flights.departureCity} <br/>
    Arrival City: ${flights.arrivalCity} <br/>
    Date Of Departure: ${flights.dateOfDepartureStr} <br/>
    Estimated Departure Time: ${flights.estimatedDepartureTimeStr} <br/>


    <h2>Passenger Details</h2>
    <form action="confirmRegistration" method="post">
    <pre>
        First Name<input type="text" name="firstName"/>
        Last Name<input type="text" name="lastName"/>
        Middle Name<input type="text" name="middleName"/>
        Email<input type="text" name="email"/>
        Phone<input type="text" name="phone"/>
        <input type="hidden" name="id" value="${flights.id}"/>
    </pre>

    <h2>Enter the card details</h2>
        Card Number<input type="text" name="cardNumber"/><br/> <!-- Added name attribute -->
        CVV<input type="text" name="cvv"/><br/> <!-- Added name attribute -->
        Expiry Date<input type="text" name="expiryDate"/><br/> <!-- Added name attribute -->
        Amount<input type="text" name="amount"/><br/> <!-- Added name attribute -->
        <input type="Submit" value="Proceed"/>
    </form>
     <!-- Logout button -->
        <form action="logout" method="post">
            <input type="submit" value="Logout">
        </form>
</body>
</html>
