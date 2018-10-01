import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;

public class FonctionsUtilisateur {

	private static final String FICHIERUTILISATEURS = "utilisateurs.csv";
	private static final String FICHIERCLASSEMENTG = "classementG.csv";
	private static final String FICHIERCLASSEMENTP = "classementP.csv";
	//ArrayList<Utilisateur> list;


	
	FonctionsUtilisateur() {
		System.out.println("Appel de la classe FonctionsCSV");
	}
	
	String[][] LireScoreG() {
		/**
		 * Fonction de lecture du fichier des classements globaux sur toutes les parties
		 */
		String[] scoresg = new String[10];
		String[][] scores = new String[10][2];
		String chaine = null;
		String[] chainebis = new String[2] ;
		boolean exist = Files.exists(Paths.get(FICHIERCLASSEMENTG)) ;
		FileReader file;
		BufferedReader buff ;
		int i;
		//On v�rifie que le fichier existe bien
		if(exist) {
			System.out.println("On a bien trouv� le fichier");
			try {
				file = new FileReader(FICHIERCLASSEMENTG);
				buff = new BufferedReader(file) ;
				for(i=0 ; i<10 ; i++) {
					try {
						//On lit la ligne du fichier dans chaine
						chaine = buff.readLine();
						//On stocke cette ligne dans un tableau interm�diaire
						scoresg[i] = chaine;
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
					try {
						buff.close();
						file.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
		else {
			System.out.println("Fichier non trouv�");
		}
		
		//Le tableau interm�diaire contient les lignes du fichier, ie. deux champs : pseudo et score total
		//On d�coupe les lignes et on stocke dans un tableau � deux colonnes
		for(int k = 0 ; k<10 ; k++) {
				chainebis = scoresg[k].split(";");
				scores[k][0] = chainebis[0];
				scores[k][1] = chainebis[1];
			}
		
		return scores ;
	}
	
	String[][] LireScoreP() {
		/**
		 * Fonction de lecture du ficher des classements globaux sur une seule partie
		 */
		String[] scoresp = new String[10];
		String[][] scores = new String[10][2];
		String chaine = null;
		String[] chainebis = new String[2] ;
		boolean exist = Files.exists(Paths.get(FICHIERCLASSEMENTP)) ;
		FileReader file;
		BufferedReader buff ;
		int i;
		//On v�rifie que le fichier existe bien
		if(exist) {
			System.out.println("On a bien trouv� le fichier");
			try {
				file = new FileReader(FICHIERCLASSEMENTP);
				buff = new BufferedReader(file) ;
				for(i=0 ; i<10 ; i++) {
					try {
						//On lit la ligne du fichier dans chaine
						chaine = buff.readLine();
						//On stocke cette ligne dans un tableau interm�diaire
						scoresp[i] = chaine;
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
					try {
						buff.close();
						file.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			System.out.println("Fichier non trouv�");
		}
		//Le tableau interm�diaire contient les lignes du fichier, ie. deux champs : pseudo et score total
		//On d�coupe les lignes et on stocke dans un tableau � deux colonnes
		for(int k = 0 ; k<10 ; k++) {
				chainebis = scoresp[k].split(";");
				scores[k][0] = chainebis[0];
				scores[k][1] = chainebis[1];
			}
		return scores ;
	}
	
	int MettreScoreP(String login, int score) {
		/**
		 * Fonction qui, suite � une partie, regarde le score global et si le nouveu score m�rite
		 * d'�tre dans le top 10, on l'inscrit
		 */
		int resultat = 2;
		int rang = 10;
		int i = 0 ;
		// 0=pb, 1=ajout�, 2=trop petit
		String [][] tab = LireScoreP() ;
		//On charge tout d'abord le fichier gr�ce � la fonction LireScoreP afin de ne pas avoir un fichier
		//� comparer mais un tableau

		while(Integer.parseInt(tab[i][1]) > score && i < 9) 
			i++;
		if(Integer.parseInt(tab[i][1]) > score && i == 9) {
			i=10;
		}
			
		rang = i ;
		//Si dans le top 10 il y a un score plus grand que le nouveau score on note le rang
		//C'est � ce rang que nous allons inscrire le nouveau score
		if(rang<10) {
			//Si le score m�rite d'�tre dans le classement on r��crit tout le fichier
			boolean exist = Files.exists(Paths.get(FICHIERCLASSEMENTP)) ;
			FileWriter file;
			BufferedWriter buff ;
			String chaine ;
			if(exist) {
				System.out.println("On a bien trouv� le fichier");
				try {
					file = new FileWriter(FICHIERCLASSEMENTP);
					buff = new BufferedWriter(file) ;
					for(int j=0 ; j<rang ; j++) {
						try {
							//On pr�pare la chaine � �crire dans le fichier
							chaine = tab[j][0] + ";" + tab[j][1] ;
							buff.write(chaine+"\n") ;
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					chaine = login + ";" + String.valueOf(score) ;
					buff.write(chaine+"\n") ;
					resultat = 1;
					for(int k=rang ; k<9 ; k++) {
							try {
								//On pr�pare la chaine � �crire dans le fichier
								chaine = tab[k][0] + ";" + tab[k][1] ;
								buff.write(chaine+"\n") ;
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						buff.close();
						file.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				} 
				else {
					System.out.println("Fichier non trouv�");
					resultat = 0;
				}
			}
		
		else {
			System.out.println("Le score n'est pas assez �lev� pour �tre dans le top 10");
			resultat = 2;
		}	
		return resultat;
	}
	
	int MettreScoreG(String login, int score) {
		/**
		 * Fonction qui, suite � une partie, regarde le score global et si le nouveu score m�rite
		 * d'�tre dans le top 10, on l'inscrit
		 */
		int resultat = 2;
		int rang = 10;
		int i = 0 ;
		// 0=pb, 1=ajout�, 2=trop petit
		String [][] tab = LireScoreG() ;
		//On charge tout d'abord le fichier gr�ce � la fonction LireScoreG afin de ne pas avoir un fichier
		//� comparer mais un tableau
		while(Integer.parseInt(tab[i][1]) > score && i < 9)
			i++;
		if(Integer.parseInt(tab[i][1]) > score && i == 9) {
			i=10;
		}
		rang = i ;
		//Si dans le top 10 il y a un score plus grand que le nouveau score on note le rang
		//C'est � ce rang que nous allons inscrire le nouveau score
		if(rang<10) {
			//Si le score m�rite d'�tre dans le classement on r��crit tout le fichier
			boolean exist = Files.exists(Paths.get(FICHIERCLASSEMENTG)) ;
			FileWriter file;
			BufferedWriter buff ;
			String chaine ;
			if(exist) {
				System.out.println("On a bien trouv� le fichier");
				try {
					file = new FileWriter(FICHIERCLASSEMENTG);
					buff = new BufferedWriter(file) ;
					for(int j=0 ; j<rang ; j++) {
						try {
							//On pr�pare la chaine � �crire dans le fichier
							chaine = tab[j][0] + ";" + tab[j][1] ;
							buff.write(chaine+"\n") ;
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					chaine = login + ";" + String.valueOf(score) ;
					buff.write(chaine+"\n") ;
					resultat = 1;
					for(int k=rang ; k<9 ; k++) {
							try {
								//On pr�pare la chaine � �crire dans le fichier
								chaine = tab[k][0] + ";" + tab[k][1] ;
								buff.write(chaine+"\n") ;
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						buff.close();
						file.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				} 
				else {
					System.out.println("Fichier non trouv�");
					resultat = 0;
				}
			}
		
		else {
			System.out.println("Le score n'est pas assez �lev� pour �tre dans le top 10");
			resultat = 2;
		}	
		return resultat;
	}
	
	String[] LireScore(String login, ArrayList<Utilisateur> list) {
		/**
		 * Fonction qui renvoie le tableau des 10 meilleurs scores du joueur
		 */
		String[] scores = new String[10];
		for(Utilisateur u : list ) {
			if(u.getName().equals(login)) {
				System.out.println("fonction LireScore(login)");
				System.out.println("on a trouv� le joueur "+login);
				for(int i = 0 ; i<10 ; i++) {
					scores[i] = String.valueOf(u.getScores()[i]);
				}
				return scores;
			}
		}
		System.out.println("On n'a pas trouv� le joueur "+login);
		return null;
	}
	
	String LireScoreTot(String login, ArrayList<Utilisateur> list) {
		/**
		 * fonction qui renvoie le score total du joueur
		 */
		for (Utilisateur u : list) {
			if (u.getName().equals(login)) {
				return String.valueOf(u.getScoreTot());
			}
		}
		return null;
	}
	
	int MettreScoreTot(String login, int score, ArrayList<Utilisateur> list) {
		/**
		 * Fonction qui ajoute le score d'un joueur a son score tot
		 */
		System.out.println("On est dans la fonction MettreScoreTot a joueur");
		int resultat = 0;
		for(Utilisateur u : list) {
			if(u.getName().equals(login)) {
				//On cherche le joueur
				System.out.println("on a trouv� le joueur "+login);
				int scoreTot = u.getScoreTot();
				u.setScoreTot(score + scoreTot);
			}
		}
		if(resultat == 0) {
			System.out.println("On n'a pas trouv� le joueur");
		}
		return resultat;
	}
	
	
	String ChercherUtilisateur(String login, ArrayList<Utilisateur> list) {
		/**
		 * Fonction qui recherche un utilisateur dans la liste avec son pseudo 
		 * et retourne son mdp si il existe d�j�, sinon retourne null
		 */
		String mdp = null;
		for(Utilisateur u : list) {
			if(u.getName().equals(login)) {
				System.out.println("On a trouv� l'utilisateur");
				mdp = u.getMdp();
			}
		}
		return mdp;
	}
	
	boolean AjouterUtilisateur(String login, String mdp, ArrayList<Utilisateur> list) {
		/**
		 * Fonction qui ajoute un utilisateur, si dans la liste il y  d�j� un utilisateur avec le m�me
		 * login, on retourne false
		 */
		String deja = ChercherUtilisateur(login, list);
		if(deja == null) {
			Utilisateur user = new Utilisateur(login,mdp);
			list.add(user);
			System.out.println("Ajout de l'utilisateur " + login + " - mdp : " + mdp);
			return true;
		}
		else {
			System.out.println("Cet utilisateur existe d�j�");
			return false;
		}
	}
	
	int MettreScore(String login, int score, ArrayList<Utilisateur> list) {
		/**
		 * Fonction qui ajoute le score d'un joueur dans son top 10 si le score le m�rite
		 * 0 = pb, 1 = ajout�, 2 = score trop petit
		 */
		System.out.println("On est dans la fonction MettreScore � un joueur");
		int resultat = 0;
		int rang = 10;
		int j = 0 ;
		int[] scores = new int[10] ;
		for(Utilisateur u : list) {
			if(u.getName().equals(login)) {
				//On cherche le joueur
				System.out.println("on a trouv� le joueur "+login);
				scores = u.getScores();
				while(scores[j]>score && j < 9) {
					//On test si le nouveau score est plus grand qu'un des scores enregistr�s
					//On note le rang o� doit s'ins�rer le nouveau score
					j ++;
				}
				if(scores[j]>score && j == 9) {
					j=10;
				}
				rang = j;
				System.out.println("Rang : " + rang);
				if(rang<10) {
					resultat = 1 ;
					//on d�place les scores de 1 rang pour ceux qui sont inf�rieurs au nouveau score
					for(int i = 9; i>rang ; i--) {
						scores[i] = scores[i-1] ;
						System.out.println("score rang " + i + " : " + scores[i]);
					}
					scores[rang] = score ;
					u.setScores(scores);
				}
				else {
					System.out.println("Le score est trop petit pour entrer dans le top 10");
					resultat = 2;
				}	
			}
		}
		if(resultat == 0) {
			System.out.println("On n'a pas trouv� le joueur");
		}
		return resultat ;
	}
	
	void ChargerTableauUsers(ArrayList<Utilisateur> list) {
		/**
		 * Fonction qui charge le fichier des identifints, mdp et les 10 meilleurs scores
		 * dans la collection de Utilisateurs
		 * Fonction utilis�e au lancement du jeu
		 */
		System.out.println("On est dans la fonction ChargerTableauUsers");
		//list = new ArrayList<Utilisateur>();
		boolean exist = Files.exists(Paths.get(FICHIERUTILISATEURS)) ;
		FileReader file;
		BufferedReader buff ;
		String chaine = null;
		String[] chainebis = new String[2];
		int[] scores = new int[10] ;
		int scoreTot;
		if(exist) {
			//On lit par groupe de 11 lignes qui correspondent � un utilisateur
			try {
				file = new FileReader(FICHIERUTILISATEURS);
				buff = new BufferedReader(file) ;
				try {
					while((chaine = buff.readLine()) != null) {
						for(int i = 0; i<10 ; i++) {
							scores[i] = Integer.parseInt(buff.readLine());
							chainebis = chaine.split(";");
						}
						scoreTot = Integer.parseInt(buff.readLine());
						Utilisateur user = new Utilisateur(chainebis[0], chainebis[1]) ;
						user.setScores(scores);
						user.setScoreTot(scoreTot);
						list.add(user);
					}
					buff.close();
					file.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
			System.out.println("Fichier Utilisateurs non trouv�");
		
	}
	
	
	void EcrireFichierUtilisateurs(ArrayList<Utilisateur> list) {
		/**
		 * Fonction qui r��crit le fichier utilisateurs quand on ferme le serveur
		 * On r��crit totalement le fichier
		 */
		String chaine ;
		boolean exist = Files.exists(Paths.get(FICHIERUTILISATEURS)) ;
		FileWriter file ;
		BufferedWriter buff;
		int[] scores ;
		if(exist) {
			try {
				file = new FileWriter(FICHIERUTILISATEURS);
				buff = new BufferedWriter(file) ;
				
				for(Utilisateur u : list) {
					chaine = u.getName() + ";" + u.getMdp()+"\n";
					buff.write(chaine);
					scores = u.getScores();
					for(int i = 0; i<10; i++) {
						buff.write(String.valueOf(scores[i])+"\n");
					}
					buff.write(String.valueOf(u.getScoreTot()) +"\n");
				}
				
				buff.close();
				file.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
			System.out.println("Fichier non trouv�");	
	}
	
	
	//----------------------FONCTIONS DE TESTS------------------------------------------------------
	void LireTab(ArrayList<Utilisateur> list) {
		/**
		 * Fonction pour tester
		 */
		String[][] tab = LireScoreG();
		for(int i = 0 ; i < 10 ; i ++ ) {
			System.out.println(i + " - " + tab[i][0] + " - score : " + tab[i][1]);
		}
		
		ChargerTableauUsers(list);
		AfficherList(list);
		String[] tab1 = LireScore("toto5", list);
		System.out.println("joueur : toto5");
		for(int i = 0 ; i<10 ; i++ ) {
			System.out.println(i+1 + " - " + tab1[i]);
		}
	}
	
	void AfficherUser(String login, ArrayList<Utilisateur> list) {
		String mdp = ChercherUtilisateur(login, list);
		System.out.println("Identifiant : " + login + " -  mdp : " + mdp);
	}
	
	void AfficherList(ArrayList<Utilisateur> list) {
		int[] scores = new int[10];
		int indice = 0;
		ChargerTableauUsers(list);
		int taille = list.size();
		System.out.println("taille de la liste : " + taille);
		for(Utilisateur u : list) {
			System.out.println("Pseudo : " + u.getName() + " - mdp : " + u.getMdp());
			scores = u.getScores();
			for(int i = 0 ; i<10 ; i++) {
				indice = i+1;
				System.out.println("Score " + indice + " : " + scores[i]);
			}
		}
	}
	void AfficherList2(ArrayList<Utilisateur> list) {
		int[] scores = new int[10];
		int indice = 0;
		int taille = list.size();
		System.out.println("taille de la liste : " + taille);
		for(Utilisateur u : list) {
			System.out.println("Pseudo : " + u.getName() + " - mdp : " + u.getMdp());
			scores = u.getScores();
			for(int i = 0 ; i<10 ; i++) {
				indice = i+1;
				System.out.println("Score " + indice + " : " + scores[i]);
			}
		}
	}
}
