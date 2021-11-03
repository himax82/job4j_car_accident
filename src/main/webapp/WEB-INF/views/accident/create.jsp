<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

    <title>Create accident</title>
</head>
<body>
<div class="container">

    <ul class="nav justify-content-end">
        <li class="nav-item"><a class="nav-link" href="<c:url value='/'/>">Главная  </a></li>
    </ul>

    <hr class="my-4">

    <div class="row">
        <div class="card" style="width: 100%">
            <div class="card-header text-center">
                <strong id="heading">Новый инцидент</strong>
            </div>
            <div class="card-body">
                <form action="<c:url value='/save'/>" method='POST'>
                    <div class="form-group">
                        <label>Название</label>
                        <input type="text" class="form-control" name="name" required>
                    </div>
                    <div class="form-group">
                        <label>Описание</label>
                        <input type="text" class="form-control" name="text" required>
                    </div>
                    <div class="form-group">
                        <label>Адрес</label>
                        <input type="text" class="form-control" name="address" required>
                    </div>
                    <button type="submit" class="btn btn-primary">Сохранить</button>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>