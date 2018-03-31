<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>resultat de modifier les matchs</title>
</head>
<body>
<h2>1. Le resultat de match</h2>
	<jsp:useBean id="footMatch"  class="modele.FootMatch" scope="request" />
	<div id="resultat" style="line-height:50px" >
		<p>Id: <jsp:getProperty name="footMatch" property="m_id"/></p>
		<p>date: <c:out value="${footMatch.calendrier.date}"/></p>
		<p>ville: <c:out value="${footMatch.calendrier.ville}"/></p>
		<p>stade: <c:out value="${footMatch.calendrier.stade}"/></p>
		<p>Equipe1: <c:out value="${footMatch.equipe1.nom}"/></p>
		<p>buts d'Equipe1:<jsp:getProperty name="footMatch" property="res1"/></p>
		<p>Equipe2: <c:out value="${footMatch.equipe2.nom}"/></p>
		<p>buts d'Equipe2:<jsp:getProperty name="footMatch" property="res2"/></p>		
	</div>

<h3>2. l'autre web sites liens: </h3>
	<div id="liens" style="line-height:50px">
		<a href="/TPnoteFOOT/login.jsp">Log In</a> pour log in <br />
		<a href="/TPnoteFOOT/reservedUtilisateur/homePage.jsp">Home Page</a> pour acceder a la homepage <br />
	</div>

</body>
</html>