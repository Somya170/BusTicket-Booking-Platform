<!DOCTYPE html>
<html>
<head>
    <title>Add Bus</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            text-align: center;
            margin: 0;
            padding: 0;
        }
        .container {
            max-width: 500px;
            margin: 50px auto;
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            padding: 30px;
            box-sizing: border-box;
        }
        h1 {
            color: #333;
            margin-bottom: 20px;
        }
        form {
            margin-top: 20px;
        }
        label {
            display: block;
            margin-bottom: 10px;
            font-weight: bold;
            color: #555;
        }
        input[type="text"],
        input[type="time"],
        input[type="number"],
        select {
            width: 100%;
            padding: 12px;
            margin-bottom: 20px;
            border-radius: 5px;
            border: 1px solid #ccc;
            box-sizing: border-box;
            font-size: 16px;
            transition: border-color 0.3s ease;
        }
        input[type="text"]:focus,
        input[type="time"]:focus,
        input[type="number"]:focus,
        select:focus {
            border-color: #4CAF50;
        }
        input[type="submit"] {
            width: 100%;
            padding: 12px;
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
        <h1>Add Bus</h1>
        <form action="Addbus" method="post">
            <label for="busNumber">Bus Number:</label>
            <input type="text" id="busNumber" name="busNumber" required>
            
            <label for="busName">Bus Name:</label>
            <input type="text" id="busName" name="busName" required>
            
            <label for="source">Source:</label>
            <input type="text" id="source" name="source" required>
            
            <label for="destination">Destination:</label>
            <input type="text" id="destination" name="destination" required>
            
            <label for="departureTime">Departure Time:</label>
            <input type="time" id="departureTime" name="departureTime" required>
            
            <label for="arrivalTime">Arrival Time:</label>
            <input type="time" id="arrivalTime" name="arrivalTime" required>
            
            <label for="fare">Fare:</label>
            <input type="text" id="fare" name="fare" required>
            
            <label for="seats">Total Seats (Max 20):</label>
            <input type="number" id="seats" name="seats" min="1" max="20" required>
            
            <label for="busType">Bus Type:</label>
            <select id="busType" name="busType" required>
                <option value="Sleeper">Sleeper</option>
                <option value="Seater">Seater</option>
            </select>
            
            <input type="hidden" name="email" value="${param.email}">
            
            <input type="submit" value="Add Bus">
        </form>
    </div>
</body>
</html>
