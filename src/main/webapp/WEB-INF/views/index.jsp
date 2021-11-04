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
<div class="container pt-3">
    <div class="row">
        <div class="card" style="width: 100%">
            <div class="card-header">
                Нарушения ПДД
            </div>
            <div class="card-body">
                <a href="<c:url value='/create'/>">Добавить инцидент</a>
                <table class="table">
                    <thead>
                    <tr><th scope="col"></th>
                        <th scope="col">Тип происшествия</th>
                        <th scope="col">Наименование</th>
                        <th scope="col">Описание</th>
                        <th scope="col">Адрес</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${accidents}" var="accident">
                        <tr>
                            <td>
                                <a href='<c:url value="/update?id=${accident.id}"/>'>
                                    <i class="fa fa-edit mr-3">Изменить</i>
                                </a>
                            </td>
                            <td><c:out value="${accident.type.name}"/></td>
                            <td><c:out value="${accident.name}"/></td>
                            <td><c:out value="${accident.text}"/></td>
                            <td><c:out value="${accident.address}"/></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
</body>
</html>
