<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ajouter match</title>
</head>
<body>
	<h1>Ajouter un match</h1>
	<div id="ajouter" style="line-height:50px">
	<h2>1. ajouter match: </h2>	
		<form method="post" action="/TPnoteFOOT/webServlet/AjouterServlet">
			<p>Equipe1: <input type="text" name="nom"  /></p>
			<p>Equipe2: <input  type="text" name="buts"  /></p>
			<p>Date: <input type="date" name="role"  /></p>			
			<p>Ville: <input type="text" name="note"  /></p>
			<p>Stade: <input type="text" name="equipe"  /></p>
			<input type="submit" name="submit" value="Ajouter...">
		</form>	
	</div>

</body>
</html>