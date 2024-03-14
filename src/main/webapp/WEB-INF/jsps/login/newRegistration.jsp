<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>New Registration</title>
</head>
<body>
    <h2>Register Here</h2>
    <form action="saveReg" method="post">
        <pre>
            First Name: <input type="text" name="firstName"/><br>
            Last Name: <input type="text" name="lastName"/><br>
            Email: <input type="text" name="email"/><br>
            Password: <input type="password" name="password"/><br>
            Role:
            <select name="role">
                <option value="admin">Admin</option>
                <option value="user">User</option>
            </select><br>
            <input type="submit" value="Save"/>
        </pre>
    </form>
</body>
</html>
