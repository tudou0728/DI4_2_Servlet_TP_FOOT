<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h2>1. Les match a venir</h2>
	<jsp:useBean id="footMatchs2"  type="java.util.List<modele.FootMatch>" scope="request" />
		<table border="1" width="500" cellpadding="0" cellspacing="1">
			<tr align="center">
				<td>m_id</td>
			   	<td>date</td>
			   	<td>ville</td>
			   	<td>stade</td>
			   	<td>Equipe1</td>
			   	<td>Equipe2</td>	   	
			</tr>
			<c:forEach items="${footMatchs2}" var="footmatch2">
				<tr align="center">
					<td><c:out value="${footmatch2.m_id}"/></td>
					<td><c:out value="${footmatch2.calendrier.date}"/></td>
					<td><c:out value="${footmatch2.calendrier.ville}"/> </td>
					<td><c:out value="${footmatch2.calendrier.stade}"/> </td>
					<td><c:out value="${footmatch2.equipe1.nom}"/></td>
					<td><c:out value="${footmatch2.equipe2.nom}"/></td>
				</tr>
			</c:forEach>
		</table>

<h3>2. l'autre web sites liens: </h3>
	<div id="liens" style="line-height:50px">
		<a href="/TPnoteFOOT/login.jsp">Log In</a> pour log in <br />
		<a href="/TPnoteFOOT/reservedUtilisateur/homePage.jsp">Home Page</a> pour acceder a la homepage <br />
	</div>
	
</body>
</html>