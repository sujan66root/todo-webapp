<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Todo Form</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="#">Todo App</a>
    <div class="collapse navbar-collapse">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="todos">Todo List</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="logout">Logout</a>
            </li>
        </ul>
    </div>
</nav>
<div class="container mt-5">
    <h2 class="text-center">${todo.id == 0 ? 'Add New Todo' : 'Edit Todo'}</h2>
    <form action="${todo.id == 0 ? '/todos/new' : '/todos/edit'}" method="post">
        <div class="form-group">
            <label for="description">Description:</label>
            <input type="text" class="form-control" id="description" name="description" value="${todo.description}" required>
        </div>
        <div class="form-group">
            <label for="targetDate">Target Date:</label>
            <input type="date" class="form-control" id="targetDate" name="targetDate" value="${todo.targetDate}" required>
        </div>
        <div class="form-group">
            <label for="status">Status:</label>
            <select class="form-control" id="status" name="status" required>
                <option value="true" ${todo.status ? 'selected' : ''}>Completed</option>
                <option value="false" ${!todo.status ? 'selected' : ''}>Pending</option>
            </select>
        </div>
        <input type="hidden" name="id" value="${todo.id}">
        <button type="submit" class="btn btn-primary">${todo.id == 0 ? 'Add Todo' : 'Update Todo'}</button>
    </form>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
