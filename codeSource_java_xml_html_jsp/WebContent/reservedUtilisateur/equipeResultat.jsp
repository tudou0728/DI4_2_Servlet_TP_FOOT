<%@ page import="java.sql.*" language="java" contentType="text/html;
charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Resultat</title>
</head>
<body>
<h2>1. Le resultat d'equipe</h2>
	<jsp:useBean id="equipes"  type="java.util.List<modele.Equipe>" scope="request" />
	<div id="resultat" style="line-height:50px" >
		<form method="post" action="/TPnoteFOOT/webServlet/ModifierServlet">
		<input type="hidden" name="modifier" value="equipe" />
			<c:forEach items="${equipes}" var="equipe">
						<input type="hidden" name="equipeId" value="${equipe.e_id}" />
			           Id: <c:out value="${equipe.e_id}"/></br>
			           Nom: <input type="text" name="EquipeNom" value="${equipe.nom}"> </br>
			           <!-- <c:out value="${equipe.nom}"/> -->
			           Nombre de participants: <c:out value="${equipe.nb_par}"/></br>
			           Nombre de victoires: <c:out value="${equipe.nb_vic}"/></br>
			           classement: <input type="text" name="classement" value="${equipe.classement}"></br>
			           <!-- <c:out value="${equipe.classement}"/> -->
			</c:forEach>
			<input type="submit" name="submit" value="modifier">
		</form>
	</div>
	

<h3>2. l'autre web sites liens: </h3>
	<div id="liens" style="line-height:50px">
		<a href="/TPnoteFOOT/login.jsp">Log In</a> pour log in <br />
		<a href="/TPnoteFOOT/reservedUtilisateur/homePage.jsp">Home Page</a> pour acceder a la homepage <br />
	</div>

</body>
</html>