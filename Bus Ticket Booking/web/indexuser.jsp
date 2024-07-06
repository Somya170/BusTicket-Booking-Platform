<!DOCTYPE html>
<html>
<head>
    <title>Bus ON Hands</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
            margin: 0;
            padding: 0;
        }
        .container {
            max-width: 600px;
            margin: 50px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        h1 {
            color: #333;
            text-align: center;
            margin-bottom: 20px;
        }
        form {
            text-align: center;
        }
        select {
            width: 100%;
            padding: 10px;
            margin: 10px 0;
            border-radius: 5px;
            border: 1px solid #ccc;
            box-sizing: border-box;
            appearance: none;
            background-color: #f9f9f9;
            font-size: 16px;
        }
        input[type="submit"] {
            width: 100%;
            padding: 10px;
            border: none;
            border-radius: 5px;
            background-color: #4CAF50;
            color: white;
            font-size: 16px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }
        input[type="submit"]:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Bus ON Hands</h1>
   <form action="Searchbus?from=<%= request.getParameter("from") %>&to=<%= request.getParameter("to") %>&email=<%= request.getParameter("email") %>" method="get">  <select name="from">
        <option value="indore">Indore</option>
        <!-- Add more options here if needed -->
    </select>
    <select name="to">
        <option value="Bhopal">Bhopal</option>
        <!-- Add more options here if needed -->
    </select>
   <input type="hidden" name="email" value="<%= request.getParameter("email") %>">
    <input type="submit" value="Search">
</form>

    </div>
</body>
</html>
