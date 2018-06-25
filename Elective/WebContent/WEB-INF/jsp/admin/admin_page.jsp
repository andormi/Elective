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
 	
			<input type="hidden" name="command" value="listOfAllCoursesCommand" />
 	</form>
 	
		<a href="?status=0" >finished</a>
		<a href="?status=1" >progress</a>
		<a href="?status=2" >expected</a>
		
		
	<form id="delete_courses" action="controller" >	
		<table id="courses">
			<thead>
				<tr>
					<td>№</td>
					<td>id</td>
					<td>course</td>
					<td>duration</td>
					<td>category</td>
					<td>status</td>
					<td>professor</td>
					<td>deleting</td>
				</tr>
			</thead>
			
			<c:set var="k" value="0" />
			<c:forEach var="course" items="${listOfAllCourses}">
				<c:set var="k" value="${k+1}" />
				<tr>
					<td><c:out value="${k}" /></td>
					<td>${course.id}</td>
					<td>${course.name}</td>
					<td>${course.duration}</td>
					<td>${course.categoryId}</td>
					<td>${course.statusId}</td>
					<td>${course.professorId}</td>
				
					<td><input type="checkbox" name="courseId" value="${course.id}" /></td>
					
				</tr>
					
			</c:forEach>
		
		</table>
		<br/>		

 		
			<input type="hidden" name="command" value="deleteCourse" />
			<input type="submit" value="удалить выбранные" />
		</form>	

<br/>


<a href="controller?command=logout">Logout</a>

</body>
</html>