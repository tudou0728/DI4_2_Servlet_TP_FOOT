<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ajouter match</title>
</head>
<body>
	<h1>Ajouter un match</h1>
	<div id="ajouter" style="line-height:50px">
	<jsp:useBean id="equipes"  type="java.util.List<modele.Equipe>" scope="request" />
	<h2>1. ajouter match: </h2>	
		<form method="post" action="/TPnoteFOOT/webServlet/AjouterServlet">
		<input type="hidden" name="ajouter" value="match" />
			<p>Equipe1: 
				<select name="equipe1">
					<c:forEach items="${equipes}" var="equipe">
					  <option value="${equipe.nom}">${equipe.nom}</option>
					 </c:forEach>
				</select>
			</p>
			<p>Equipe2: 
				<select name="equipe2">
					<c:forEach items="${equipes}" var="equipe">
					  <option value="${equipe.nom}">${equipe.nom}</option>
					 </c:forEach>
				</select>
			</p>
			<p>Date: <input type="date" name="date"  /></p>			
			<p>Ville: <input type="text" name="ville"  /></p>
			<p>Stade: <input type="text" name="stade"  /></p>
			<input type="submit" name="submit" value="Ajouter...">
		</form>	
	</div>

</body>
</html>