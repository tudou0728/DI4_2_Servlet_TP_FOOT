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
<h2>1. Le resultat de joueur</h2>
	<jsp:useBean id="joueur"  class="modele.Joueur" scope="request" />
	<div id="resultat" style="line-height:50px" >
		<p>Id: <jsp:getProperty name="joueur" property="j_id"/></p>
		<p>nom: <jsp:getProperty name="joueur" property="nom"/></p>
		<p>buts: <jsp:getProperty name="joueur" property="buts"/></p>
		<p>role: <jsp:getProperty name="joueur" property="role"/></p>
		<p>note: <jsp:getProperty name="joueur" property="note"/></p>
		<p>Equipe:<c:out value="${joueur.equipe.nom}"/></p>	
	</div>

<h3>2. l'autre web sites liens: </h3>
	<div id="liens" style="line-height:50px">
		<a href="/TPnoteFOOT/login.jsp">Log In</a> pour log in <br />
		<a href="/TPnoteFOOT/reservedUtilisateur/homePage.jsp">Home Page</a> pour acceder a la homepage <br />
	</div>

</body>
</html>