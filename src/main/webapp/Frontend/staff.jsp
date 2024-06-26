<%@page import="com.demy.Entites.EmployeeEntity"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="sessionvalidator.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

<style>

.header .logo img{
    width: 100%;
    height: 7vh;
    top: 0;
    left: 0;
}

#services
{
margin-top: -90px;
}
.service {
    padding: 32px;
    background-color: #fff;
    box-shadow: var(--shadow);
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.5); /* horizontal-offset vertical-offset blur-radius color */
    border: 1px solid white;
    border-radius:10px;
    margin: 10px;
    height: 40vh;
}

.service h5 {
    margin-bottom: 14px;
}

.service img {
    width: 90px;
}
table,td,a
{
font-size: 120%;
    text-transform:none;

}

</style>
</head>
<body>

   <%@ include file="employeeheader.jsp" %>
   
   
 <section id="home" class="home">
        <h2>Home / Staff</h2>
    </section>

<br>
<br>
<br>

<div class="container-fluid">
    <h2 class="text-center">Staff Details&nbsp;&nbsp;&nbsp;<a href="./addEmployee" class="btn btn-primary">Add Employee</a></h2>
    <span style="color: green; text-align: center; margin:0px 30px;">${msg}</span><br><br><br>

    <table class="table table-striped">
        <thead class="thead-dark">
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Email</th>
                <th>Role</th>
                <th>Priority</th>
                <th>Edit</th>
                <th>Delete</th>
            </tr>
        </thead>
        <tbody>
                <c:forEach var="employee" items="${employees}">
    <tr>
        <td>${employee.id}</td>
        <td>${employee.name}</td>
        <td>${employee.email}</td>
        <td>${employee.role}</td>
        <td>
            <c:choose>
                <c:when test="${employee.priority == 1}">
                    High
                </c:when>
                <c:when test="${employee.priority == 2}">
                    Medium
                </c:when>
                <c:when test="${employee.priority == 3}">
                    Low
                </c:when>
                <c:otherwise>
                    Unknown
                </c:otherwise>
            </c:choose>
        </td>   

        <c:choose>
            <c:when test="${sessionScope.loggedInEmployee.priority == 1}">
                <td>
                    <a href="/edit?id=${employee.id}" class="btn btn-primary">Edit</a>
                </td>
                <td>
                    <c:choose>
                        <c:when test="${sessionScope.loggedInEmployee.id == employee.id}">
                            <button class="btn btn-danger" disabled>Delete</button>
                        </c:when>                        <c:otherwise>
                            <a href="/delete?id=${employee.id}" class="btn btn-danger">Delete</a>
                        </c:otherwise>
                    </c:choose>
                </td>
            </c:when>
            <c:otherwise>
                <c:choose>
                    <c:when test="${sessionScope.loggedInEmployee.id == employee.id}">
                        <td>
                            <a href="/edit?id=${employee.id}" class="btn btn-primary">Edit</a>
                        </td>
                        <td>
                            <button class="btn btn-danger" disabled>Delete</button>
                        </td>
                    </c:when>
                    
                                <c:otherwise>
                                <td>
                            <button class="btn btn-primary" disabled>Edit</button>
                        </td>
                        <td>
                            <button class="btn btn-danger" disabled>Delete</button>
                        </td>
                    </c:otherwise>
                   
                </c:choose>
            </c:otherwise>
        </c:choose>
    </tr>
</c:forEach>

        </tbody>
    </table>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>