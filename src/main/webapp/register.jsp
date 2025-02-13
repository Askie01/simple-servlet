<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration form</title>
</head>
<body>

<h1>Register account</h1>
<form method="post" action="/register">
    <label for="email">Email: </label>
    <input type="email" id="email" name="email">
    <br>

    <label for="name">Name: </label>
    <input type="text" id="name" name="name">
    <br>

    <label for="age">Age: </label>
    <input type="number" id="age" name="age">
    <br>

    <label for="password">Password: </label>
    <input type="password" id="password" name="password">
    <br>

    <label for="confirm-password">Confirm password: </label>
    <input type="password" id="confirm-password" name="confirm-password">
    <br>

    <button type="submit">Create account</button>
    <br>
</form>
</body>
</html>
