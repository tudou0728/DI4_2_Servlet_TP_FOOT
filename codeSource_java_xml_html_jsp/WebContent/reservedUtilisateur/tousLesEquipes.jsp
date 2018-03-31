<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Tous les equipes</title>
</head>
<body>
<h2>1. Le resultat de tous les equipes</h2>
	<jsp:useBean id="equipes"  type="java.util.List<modele.Equipe>" scope="request" />
		<table border="1" width="500" cellpadding="0" cellspacing="1">
			<tr align="center">
				<td>Id</td>
			   	<td>Name</td>
			   	<td>Nombre de participants</td>
			   	<td>Nombre de victoires</td>
			   	<td>classment</td>
			</tr>
			<c:forEach items="${equipes}" var="equipe">
				<tr align="center">
					<td><c:out value="${equipe.e_id}"/></td>
					<td><c:out value="${equipe.nom}"/></td>
					<td><c:out value="${equipe.nb_par}"/> </td>
					<td><c:out value="${equipe.nb_vic}"/> </td>
					<td><c:out value="${equipe.classement}"/></td>
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