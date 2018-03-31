<%@ page import="java.sql.*" language="java" contentType="text/html;
charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Foot Home Page</title>
</head>
<body>
<h1>Welcome Foot Home Page</h1>
	<a href="/TPnoteFOOT/login.jsp">Log In</a> pour log in <br />
	
	<div id="affichier" style="line-height:50px">
	<h2>1. Visualiser tous les informations: </h2>
	
		<form method="post" action="/TPnoteFOOT/webServlet/AffichierTousServlet">
			<p><input type="radio" name="affichier" value="equipes" />---tous les equipes</p>
			<p><input type="radio" name="affichier" value="joueurs" />---tous les joueurs</p>
			<p><input type="radio" name="affichier" value="matchs1" />---tous les resultats des matchs</p>			
			<p><input type="radio" name="affichier" value="matchs2" />---tous les matchs a venir</p>
			<input type="submit" name="submit" value="Rechercher...">
		</form>	
	</div>
	
	<div id="rechercherEquipe" style="line-height:50px">
	<h2>2. Rechercher l'equipe: </h2>
	
		<form method="post" action="/TPnoteFOOT/webServlet/RechercherServlet">
			<input type="hidden" name="select" value="equipe" />
			<p>(1). Entrer le nom d'equipe: <input type="text" name="EquipeNom" >
			<input type="submit" name="submit" value="Rechercher...">
		</form>	
	</div>
	
	<div id="rechercherJoueur" style="line-height:50px">
	<h2>3. Rechercher les joueurs: </h2>
	
		<form method="post" action="/TPnoteFOOT/webServlet/RechercherServlet">
			<input type="hidden" name="select" value="joueur" />
			<p>(1). Entrer le nom de joueur: <input type="text" name="joueurNom" >
			<input type="submit" name="submit" value="Rechercher...">
		</form>	
	</div>
	
	<div id="rechercherMatch" style="line-height:50px">
	<h2>4. Rechercher les matchs: </h2>	
		<form method="post" action="/TPnoteFOOT/webServlet/RechercherServlet">
			<input type="hidden" name="select" value="match" />
			<p>(1). Entrer la date de match: 
			<p>jour (JJ): <input type="text" name="jour" ></p>
			<p>mois (MM): <input type="text" name="mois" ></p>
			<p>annee (AAAA): <input type="text" name="annee" ></p>
			<input type="submit" name="submit" value="Rechercher...">
		</form>	
	</div>
	
	<h3>5. Ajouter: </h3>
	<form method="post" action="/TPnoteFOOT/webServlet/SelectServlet">
			<p><input type="radio" name="ajouter" value="equipe">equipe</p>
			<p><input type="radio" name="ajouter" value="joueur">joueur</p>
			<p><input type="radio" name="ajouter" value="match">match</p>
			<input type="submit" name="submit" value="ajouter">
		</form>	

</body>
</html>