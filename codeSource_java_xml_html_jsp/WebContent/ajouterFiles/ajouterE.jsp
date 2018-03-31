<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ajouter equipe</title>
</head>
<body>
	<h1>Ajouter une equipe</h1>
	<div id="ajouter" style="line-height:50px">
	<h2>1. ajouter equipe: </h2>	
		<form method="post" action="/TPnoteFOOT/webServlet/AjouterServlet">
		<input type="hidden" name="ajouter" value="equipe" />
			<p>Nom (character): <input type="text" name="nom"  /></p>
			<p>Classement: <input type="number" name="classement"  /></p>
			<p>Nombre de participants (int): <input type="number" name="nbP"  /></p>			
			<p>Nombre de victoires (int): <input type="number" name="nbV"  /></p>
			<input type="submit" name="submit" value="Ajouter...">
		</form>	
	</div>
</body>
</html>