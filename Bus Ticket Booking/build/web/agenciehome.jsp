<!DOCTYPE html>
<html>
<head>
    <title>Bus Management System</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            text-align: center;
        }
        .container {
            margin-top: 50px;
        }
        h1 {
            color: #333;
        }
        .button {
            background-color: #4CAF50;
            color: white;
            padding: 14px 20px;
            margin: 8px 0;
            border: none;
            cursor: pointer;
            border-radius: 4px;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
            transition: background-color 0.3s ease;
            width: 200px;
        }
        .button:hover {
            background-color: #45a049;
        }
        .button-container {
            margin-bottom: 20px;
        }
    </style>
    
</head>

<body>
    <h1>Welcome, <%= request.getParameter("name") %></h1>
    <%
        String email = request.getParameter("email");
    %>

    <div class="container">
        <h1>Bus Management System</h1>
        <div class="button-container">
            <a href="addbus.jsp?email=<%= email %>" class="button">Add Buses</a>
        </div>
        <div class="button-container">
            <a href="Modifybus?email=<%= email %>" class="button">Modify/Delete Buses</a>
        </div>
        <div class="button-container">
            <a href="#" class="button">Show Ongoing Buses</a>
        </div>
        <div class="button-container">
            <a href="Showallbusses?email=<%= email %>" class="button">Show All Buses</a>
        </div>
    </div>
</body>
</html>
