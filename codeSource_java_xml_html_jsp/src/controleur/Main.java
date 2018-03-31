package controleur;

public class Main { 
 
  public static void main(String[] args) {
	  
	 ConnBD connBD=new ConnBD();
	 connBD.connecter();
	 //on peut tester sur ici.

	 connBD.fermer();
  
  } 
} 