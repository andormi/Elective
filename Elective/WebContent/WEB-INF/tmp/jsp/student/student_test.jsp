<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
NOTstudent

<%-- String str = "room";--%>
<%-- boolean bool = (str == "room"); --%>
<br>

${userRole}
<br>
${fname}
<br>
${lname}
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
					<td>duration</td>
					<td>category</td>
					<td>status</td>
					<td>professor</td>
				</tr>
			</thead>
			
			<c:set var="k" value="0" />
			<c:forEach var="item" items="${listOfAllCourses}">
				<c:set var="k" value="${k+1}" />
				<tr>
					<td><c:out value="${k}" /></td>
					<td>${item.name}</td>
					<td>${item.duration}</td>
					<td>${item.categoryId}</td>
					<td>${item.statusId}</td>
					<td>${item.professorId}</td>
					
				</tr>
			</c:forEach>
		</table>
	</form>


<br/>


<a href="controller?command=logout">Logout</a>

</body>
</html>