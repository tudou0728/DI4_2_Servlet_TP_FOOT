<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>resultat de joueurs</title>
</head>
<body>

<h2>1. Les informations de joueur</h2>
	<jsp:useBean id="joueurs"  type="java.util.List<modele.Joueur>" scope="request" />
	<div id="resultat" style="line-height:50px" >
		<form method="post" action="/TPnoteFOOT/webServlet/ModifierServlet">
		<input type="hidden" name="modifier" value="joueur" />
			<c:forEach items="${joueurs}" var="joueur">
						<input type="hidden" name="joueurId" value="${joueur.j_id}" />
			           <p> Id: <c:out value="${joueur.j_id}"/></p>
			           <p>Nom: <input type="text" name="joueurNom" value="${joueur.nom}"></p>
			           <p>buts: <input type="text" name="joueurButs" value="${joueur.buts}"></p>
			           <p>role: <input type="text" name="joueurRole" value="${joueur.role}"></p>
			           <p>note: <input type="text" name="joueurNote" value="${joueur.note}"></p>
			           <p>equipe de joueur: <input type="text" name="joueurEquipe" value="${joueur.equipe.nom}"></p>
			</c:forEach>
			<input type="submit" name="submit" value="modifier">
		</form>	
	</div>

<h2>2. Tous les matchs de joueurs</h2>
	<jsp:useBean id="jouerMatchs"  type="java.util.List<modele.JouerMatch>" scope="request" />
		<table border="1" width="600" cellpadding="0" cellspacing="1">
			<tr align="center" width="600">
				<td>Id de joueur</td>
			   	<td>Nom de joueur</td>
			   	<td>equipe de joueur</td>
			   	<td>match Id</td>
			   	<td>ville</td>
			   	<td>date</td>
			   	<td>Equipe1</td>
			   	<td>buts d'equipe1</td>
			   	<td>Equipe2</td>
			   	<td>buts d'Equipe2</td>
			</tr>
			<c:forEach items="${jouerMatchs}" var="jouerMatch">
				<tr align="center" width="600">
					<td><c:out value="${jouerMatch.joueur.j_id}"/></td>
					<td><c:out value="${jouerMatch.joueur.nom}"/></td>
					<td><c:out value="${jouerMatch.joueur.equipe.nom}"/></td>
					<td><c:out value="${jouerMatch.jM_id}"/></td>
					<td><c:out value="${jouerMatch.footMatch.calendrier.ville}"/></td>
					<td><c:out value="${jouerMatch.footMatch.calendrier.date}"/></td>
					<td><c:out value="${jouerMatch.footMatch.equipe1.nom}"/></td>
					<td><c:out value="${jouerMatch.footMatch.res1}"/></td>
					<td><c:out value="${jouerMatch.footMatch.equipe2.nom}"/></td>
					<td><c:out value="${jouerMatch.footMatch.res2}"/></td>
				</tr>
			</c:forEach>
		</table>

<h2>3. Supprimer de joueur</h2>
	<div id="sResultat" style="line-height:50px" >
		<form method="post" action="/TPnoteFOOT/webServlet/SupprimerServlet">
		<input type="hidden" name="supprimer" value="joueur" />
			<c:forEach items="${joueurs}" var="joueur">
					<input type="hidden" name="joueurId" value="${joueur.j_id}" />
			           <p> Id: <c:out value="${joueur.j_id}"/></p>
			           <p>Nom: <c:out  value="${joueur.nom}"/></p>
			           <p>buts: <c:out value="${joueur.buts}"/></p>
			           <p>role: <c:out value="${joueur.role}"/></p>
			           <p>note: <c:out value="${joueur.note}"/></p>
			           <p>equipe de joueur: <c:out value="${joueur.equipe.nom}"/></p>
			</c:forEach>
			<input type="submit" name="submit" value="supprimer">
		</form>	
	</div>
		
<h3>4. l'autre web sites liens: </h3>
	<div id="liens" style="line-height:50px">
		<a href="/TPnoteFOOT/login.jsp">Log In</a> pour log in <br />
		<a href="/TPnoteFOOT/reservedUtilisateur/homePage.jsp">Home Page</a> pour acceder a la homepage <br />
	</div>

</body>
</html>