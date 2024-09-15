<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Todo List</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="#">Todo App</a>
    <div class="collapse navbar-collapse">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="todos/new">Add Todo</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="logout">Logout</a>
            </li>
        </ul>
    </div>
</nav>
<div class="container mt-5">
    <h2 class="text-center">Todo List</h2>
    <table class="table table-bordered">
        <thead>
            <tr>
                <th>ID</th>
                <th>Description</th>
                <th>Target Date</th>
                <th>Status</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="todo" items="${todos}">
                <tr>
                    <td>${todo.id}</td>
                    <td>${todo.description}</td>
                    <td>${todo.targetDate}</td>
                    <td>${todo.status ? 'Completed' : 'Pending'}</td>
                    <td>
                        <a class="btn btn-warning btn-sm" href="todos/edit/${todo.id}">Edit</a>
                        <a class="btn btn-danger btn-sm" href="todos/delete/${todo.id}">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
