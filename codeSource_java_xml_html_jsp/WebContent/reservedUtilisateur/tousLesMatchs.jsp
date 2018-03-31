<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Tous les matchs</title>
</head>
<body>
<h2>1. Le resultat de tous les matchs</h2>
	<jsp:useBean id="footMatchs1"  type="java.util.List<modele.FootMatch>" scope="request" />
		<table border="1" width="500" cellpadding="0" cellspacing="1">
			<tr align="center">
				<td>m_id</td>
			   	<td>date</td>
			   	<td>ville</td>
			   	<td>stade</td>
			   	<td>equipe1</td>
			   	<td>buts d'equipe1</td>
			   	<td>equipe2</td>	   	
			   	<td>buts d'equipe2</td>
			</tr>
			<c:forEach items="${footMatchs1}" var="footmatch1">
				<tr align="center">
					<td><c:out value="${footmatch1.m_id}"/></td>
					<td><c:out value="${footmatch1.calendrier.date}"/></td>
					<td><c:out value="${footmatch1.calendrier.ville}"/> </td>
					<td><c:out value="${footmatch1.calendrier.stade}"/> </td>
					<td><c:out value="${footmatch1.equipe1.nom}"/></td>
					<td><c:out value="${footmatch1.res1}"/></td>
					<td><c:out value="${footmatch1.equipe2.nom}"/></td>
					<td><c:out value="${footmatch1.res2}"/></td>
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