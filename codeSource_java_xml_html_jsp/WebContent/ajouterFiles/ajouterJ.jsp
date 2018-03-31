<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ajouter joueur</title>
</head>
<body>
<h1>Ajouter un joueur</h1>
	
	<div id="ajouter" style="line-height:50px">
		<jsp:useBean id="equipes"  type="java.util.List<modele.Equipe>" scope="request" />
	<h2>1. ajouter joueur: </h2>	
		<form method="post" action="/TPnoteFOOT/webServlet/AjouterServlet">
		<input type="hidden" name="ajouter" value="joueur" />
			<p>Nom (character): <input type="text" name="nom"  /></p>
			<p>Buts (int): <input  type="number" name="buts"  /></p>
			<p>Role (character): <input type="text" name="role"  /></p>			
			<p>Note (double): <input onkeyup="this.value=this.value.replace(/[^\d.]/g,'')" onafterpaste="this.value=this.value.replace(/[^\d.]/g,'')" type="text" name="note"  /></p>
			<p>Equipe (character): 
				<select name="equipe">
				<c:forEach items="${equipes}" var="equipe">
				  <option value="${equipe.nom}">${equipe.nom}</option>
				 </c:forEach>
				</select>
			</p>
			<input type="submit" name="submit" value="Ajouter...">
		</form>	
	</div>

</body>
</html>