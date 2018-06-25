<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Insert title here</title>
</head>
<body>
	student_page new
<br/>

<c:out value="${user.firstName} ${user.lastName}"/>

<br/>

	<c:if test="${not empty userRole}">
		<c:out value="(${userRole.name})"/>
	</c:if>

<br/>

	<form id="list_courses" action="controller" >

		<a href="?status=0" >finished</a>
		<a href="?status=1" >progress</a>
		<a href="?status=2" >expected</a>

		<table id="courses">
			<thead>
				<tr>
					<td>№</td>
					<td>course</td>
					<td>mark</td>
					<td>status</td>
					<td>professorLastName</td>
				</tr>
			</thead>
			
			<c:set var="k" value="0" />
			<c:forEach var="item" items="${listCourses}">
				<c:set var="k" value="${k+1}" />
				<tr>
					<td><c:out value="${k}" /></td>
					<td>${item.courseName}</td>
					<td>${item.mark}</td>
					<td>${item.status}</td>
					<td>${item.professorLastName}</td>
					
				</tr>
			</c:forEach>
		</table>
	</form>

</body>
</html>