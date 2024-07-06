<%-- 
    Document   : register
    Created on : Feb 9, 2024, 12:59:03 AM
    Author     : VICTUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Registration</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-image: url('your-background-image-url.jpg');
            background-size: cover;
            background-position: center;
            margin: 0;
            padding: 0;
        }
        .container {
            max-width: 400px;
            margin: 50px auto;
            padding: 20px;
            background-color: rgba(255, 255, 255, 0.8);
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        h1 {
            color: #333;
            text-align: center;
        }
        input[type="text"],
        input[type="password"],
        input[type="number"] {
            width: calc(100% - 20px);
            padding: 10px;
            margin: 10px;
            border-radius: 5px;
            border: 1px solid #ccc;
            box-sizing: border-box;
        }
        input[type="text"]:focus,
        input[type="password"]:focus,
        input[type="number"]:focus {
            outline: none;
            border-color: #4CAF50;
        }
        input[type="submit"] {
            width: calc(100% - 20px);
            padding: 10px;
            margin: 10px;
            border-radius: 5px;
            border: none;
            background-color: #4CAF50;
            color: #fff;
            font-weight: bold;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: #45a049;
        }
        a {
            display: block;
            margin-top: 20px;
            text-decoration: none;
            color: #4CAF50;
            text-align: center;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>User Registration</h1>
    <form action="Registercheck" method="post">
        <input type="text" name="name" placeholder="Enter Full Name">
        <input type="text" name="email" placeholder="Enter Mail-ID">
        <input type="number" name="mobileno" placeholder="Enter No.">
        <input type="password" name="password" placeholder="Enter Password">
        <input type="text" name="nativecity" placeholder="Enter Native City">
        <input type="submit" value="Register">
    </form>
    <h2>Already a user ?</h2>
    <a href="login.jsp">LOGIN</a>
</div>
</body>
</html>
