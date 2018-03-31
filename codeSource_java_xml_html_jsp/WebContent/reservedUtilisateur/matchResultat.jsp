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
<h2>1. Les informations de matchs</h2>
	<jsp:useBean id="footMatchs"  type="java.util.List<modele.Joueur>" scope="request" />
	<div id="resultat" style="line-height:50px" >
		
			<c:forEach items="${footMatchs}" var="footMatch">
			<h4>id: <c:out value="${footMatch.m_id}"/></h4>
			<form method="post" action="/TPnoteFOOT/webServlet/ModifierServlet">
				<input type="hidden" name="modifier" value="match" />
			   <input type="hidden" name="matchId" value="${footMatch.m_id}" />			   
			   <p>date: <input type="text" name="matchDate" value="${footMatch.calendrier.date}"></p>
			   <p>ville: <input type="text" name="matchVille" value="${footMatch.calendrier.ville}"></p>
		       <p>stade: <input type="text" name="matchStade" value="${footMatch.calendrier.stade}"></p>
	           <p>Equipe1: <input type="text" name="matchEquipe1" value="${footMatch.equipe1.nom}"></p>
	           <p>buts d'equipe1 (si NULL, c'est a dire le match a venir): <input type="text" name="matchNote1" value="${footMatch.res1}"></p>
	           <p>Equipe2: <input type="text" name="matchEquipe2" value="${footMatch.equipe2.nom}"></p>
	           <p>buts d'equipe2 (si NULL, c'est a dire le match a venir): <input type="text" name="matchNote2" value="${footMatch.res2}"></p>
				<input type="submit" name="submit" value="modifier">
			</form>	
			</c:forEach>			
	</div>

<h2>2. Supprimer de match</h2>
	<div id="sResultat" style="line-height:50px" >
		<c:forEach items="${footMatchs}" var="footMatch">
			<h4>id: <c:out value="${footMatch.m_id}"/></h4>
			<form method="post" action="/TPnoteFOOT/webServlet/SupprimerServlet">
				<input type="hidden" name="supprimer" value="match" />
			   <input type="hidden" name="matchId" value="${footMatch.m_id}" />			   
				   <p>date: <c:out value="${footMatch.calendrier.date}"/></p>
				   <p>ville: <c:out value="${footMatch.calendrier.ville}"/></p>
			       <p>stade: <c:out value="${footMatch.calendrier.stade}"/></p>
		           <p>Equipe1: <c:out value="${footMatch.equipe1.nom}"/></p>
		           <p>buts d'equipe1 (si NULL, c'est a dire le match a venir): <c:out value="${footMatch.res1}"/></p>
		           <p>Equipe2: <c:out value="${footMatch.equipe2.nom}"/></p>
		           <p>buts d'equipe2 (si NULL, c'est a dire le match a venir): <c:out value="${footMatch.res2}"/></p>
				<input type="submit" name="submit" value="supprimer">
			</form>	
			</c:forEach>
	</div>
	
<h3>3. l'autre web sites liens: </h3>
	<div id="liens" style="line-height:50px">
		<a href="/TPnoteFOOT/login.jsp">Log In</a> pour log in <br />
		<a href="/TPnoteFOOT/reservedUtilisateur/homePage.jsp">Home Page</a> pour acceder a la homepage <br />
	</div>


</body>
</html>