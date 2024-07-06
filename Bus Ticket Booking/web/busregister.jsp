<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Agency Registration</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
            margin: 0;
            padding: 0;
        }
        form {
            background-color: #fff;
            max-width: 400px;
            margin: 50px auto;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        h1 {
            text-align: center;
            color: #333;
        }
        label {
            display: block;
            margin-bottom: 5px;
            color: #555;
        }
        input[type="text"] {
            width: 100%;
            padding: 8px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 3px;
            box-sizing: border-box;
        }
        input[type="submit"] {
            width: 100%;
            padding: 10px;
            background-color: #007bff;
            border: none;
            border-radius: 3px;
            color: #fff;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }
        input[type="submit"]:hover {
            background-color: #0056b3;
        }
        .login-link {
            text-align: center;
            margin-top: 20px;
        }
    </style>
</head>
<body>
    <form action="Submitt" method="post">
        <h1>Welcome to Bus Agency Registration</h1>
<p class="login-link">Already a user? <a href="loginbus.jsp">Click here</a></p>
        <label for="orgName">Organization Name:</label>
        <input type="text" id="orgName" name="organization_name">
        
        <label for="name">Name:</label>
        <input type="text" id="name" name="name">
        
        <label for="contact">Contact Number:</label>
        <input type="text" id="contact" name="contact_number">
        
        <label for="email">E-mail:</label>
        <input type="text" id="email" name="email">
        
        <label for="address">Address:</label>
        <input type="text" id="address" name="address">
        
        <label for="uname">Username:</label>
        <input type="text" id="uname" name="username">
        
        <label for="pass">Password:</label>
        <input type="text" id="pass" name="password">
        
        <label for="rno">Registration Number:</label>
        <input type="text" id="rno" name="registration_number">
        
        <input type="submit" value="SUBMIT">
    </form>
    
</body>
</html>
