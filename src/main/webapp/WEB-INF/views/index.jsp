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
    <title>Accident</title>
</head>
<body>
Hello : Accident
<div class="container pt-3">
    <div class="row">
        Hello : ${user}
    </div>

    <div class="row">
        <div class="card" style="width: 100%">
            <div class="card-header">
                Пользователи
                    <tbody>
                    <c:forEach items="${names}" var="name">
                        <tr>
                            <td>
                                <c:out value="${name}"/>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
            </div>
    </div>
</div>
</body>
</html>
