<%@ page import="java.sql.*" language="java" contentType="text/html;
charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Modifier la BD</title>
</head>
<body>
	<h2>1. Le resultat d'equipe</h2>
	<jsp:useBean id="equipe"  class="modele.Equipe" scope="request" />
	<div id="resultat" style="line-height:50px" >
				<p>Id: <jsp:getProperty name="equipe" property="e_id"/></p>
				<p>Name: <jsp:getProperty name="equipe" property="nom"/></p>
				<p>Nombre de participants: <jsp:getProperty name="equipe" property="nb_par"/></p>
				<p>Nombre de victoires: <jsp:getProperty name="equipe" property="nb_vic"/></p>
				<p>classment: <jsp:getProperty name="equipe" property="classement"/></p>
			</form>	
	</div>

<h3>2. l'autre web sites liens: </h3>
	<div id="liens" style="line-height:50px">
		<a href="/TPnoteFOOT/login.jsp">Log In</a> pour log in <br />
		<a href="/TPnoteFOOT/reservedUtilisateur/homePage.jsp">Home Page</a> pour acceder a la homepage <br />
	</div>

</body>
</html>