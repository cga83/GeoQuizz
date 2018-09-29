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

public class FonctionsCSV {

	private static final String FICHIERUTILISATEURS = "utilisateurs.csv";
	private static final String FICHIERCLASSEMENTG = "classementG.csv";
	private static final String FICHIERCLASSEMENTP = "classementP.csv";
	private static final String COMMUNESDEPARTEMENTS = "eucircos_regions_departements_circonscriptions_communes_gps.csv" ;
	private static final int TAILLE = 36840 ;
	ArrayList<Utilisateur> list;


	
	FonctionsCSV() {
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
		//On vérifie que le fichier existe bien
		if(exist) {
			System.out.println("On a bien trouvé le fichier");
			try {
				file = new FileReader(FICHIERCLASSEMENTG);
				buff = new BufferedReader(file) ;
				for(i=0 ; i<10 ; i++) {
					try {
						//On lit la ligne du fichier dans chaine
						chaine = buff.readLine();
						//On stocke cette ligne dans un tableau intermédiaire
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
			System.out.println("Fichier non trouvé");
		}
		
		//Le tableau intermédiaire contient les lignes du fichier, ie. deux champs : pseudo et score total
		//On découpe les lignes et on stocke dans un tableau à deux colonnes
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
		//On vérifie que le fichier existe bien
		if(exist) {
			System.out.println("On a bien trouvé le fichier");
			try {
				file = new FileReader(FICHIERCLASSEMENTP);
				buff = new BufferedReader(file) ;
				for(i=0 ; i<10 ; i++) {
					try {
						//On lit la ligne du fichier dans chaine
						chaine = buff.readLine();
						//On stocke cette ligne dans un tableau intermédiaire
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
			System.out.println("Fichier non trouvé");
		}
		//Le tableau intermédiaire contient les lignes du fichier, ie. deux champs : pseudo et score total
		//On découpe les lignes et on stocke dans un tableau à deux colonnes
		for(int k = 0 ; k<10 ; k++) {
				chainebis = scoresp[k].split(";");
				scores[k][0] = chainebis[0];
				scores[k][1] = chainebis[1];
			}
		return scores ;
	}
	
	int MettreScoreP(String login, int score) {
		/**
		 * Fonction qui, suite à une partie, regarde le score global et si le nouveu score mérite
		 * d'être dans le top 10, on l'inscrit
		 */
		int resultat = 2;
		int rang = 10;
		// 0=pb, 1=ajouté, 2=trop petit
		String [][] tab = LireScoreP() ;
		//On charge tout d'abord le fichier grâce à la fonction LireScoreP afin de ne pas avoir un fichier
		//à comparer mais un tableau
		for (int i = 0 ; i<10 ; i++) {
			if(Integer.parseInt(tab[i][1]) > score) {
				rang = i;
				//Si dans le top 10 il y a un score plus grand que le nouveau score on note le rang
				//C'est à ce rang que nous allons inscrire le nouveau score
			}
		}
		if(rang<10) {
			//Si le score mérite d'être dans le classement on réécrit tout le fichier
			boolean exist = Files.exists(Paths.get(FICHIERCLASSEMENTP)) ;
			FileWriter file;
			BufferedWriter buff ;
			String chaine ;
			if(exist) {
				System.out.println("On a bien trouvé le fichier");
				try {
					file = new FileWriter(FICHIERCLASSEMENTP);
					buff = new BufferedWriter(file) ;
					for(int i=0 ; i<rang ; i++) {
						try {
							//On prépare la chaine à écrire dans le fichier
							chaine = tab[i][0] + ";" + tab[i][1] ;
							buff.write(chaine+"\n") ;
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					chaine = login + ";" + String.valueOf(score) ;
					buff.write(chaine+"\n") ;
					resultat = 1;
					for(int i=rang ; i<10 ; i++) {
							try {
								//On prépare la chaine à écrire dans le fichier
								chaine = tab[i][0] + ";" + tab[i][1] ;
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
					System.out.println("Fichier non trouvé");
					resultat = 0;
				}
			}
		
		else {
			System.out.println("Le score n'est pas assez élevé pour être dans le top 10");
			resultat = 2;
		}	
		return resultat;
	}
	
	
	int[] LireScore(String login) {
		/**
		 * Fonction qui renvoie le tableau des 10 meilleurs scores du joueur
		 */
		for(Utilisateur u : list ) {
			if(u.getName().equals(login)) {
				System.out.println("fonction LireScore(login)");
				System.out.println("on a trouvé le joueur "+login);
				return u.getScores();
			}
		}
		System.out.println("On n'a pas trouvé le joueur "+login);
		return null;
	}
	
	
	String ChercherUtilisateur(String login) {
		/**
		 * Fonction qui recherche un utilisateur dans la liste avec son pseudo 
		 * et retourne son mdp si il existe déjà, sinon retourne null
		 */
		String mdp = null;
		for(Utilisateur u : list) {
			if(u.getName().equals(login)) {
				System.out.println("On a trouvé l'utilisateur");
				mdp = u.getMdp();
			}
		}
		return mdp;
	}
	
	boolean AjouterUtilisateur(String login, String mdp) {
		/**
		 * Fonction qui ajoute un utilisateur, si dans la liste il y  déjà un utilisateur avec le même
		 * login, on retourne false
		 */
		String deja = ChercherUtilisateur(login);
		if(deja == null) {
			Utilisateur user = new Utilisateur(login,mdp);
			list.add(user);
			System.out.println("Ajout de l'utilisateur " + login + " - mdp : " + mdp);
			return true;
		}
		else {
			System.out.println("Cet utilisateur existe déjà");
			return false;
		}
	}
	
	int MettreScore(String login, int score) {
		/**
		 * Fonction qui ajoute le score d'un joueur dans son top 10 si le score le mérite
		 * 0 = pb, 1 = ajouté, 2 = score trop petit
		 */
		System.out.println("On est dans la fonction MettreScore à un joueur");
		int resultat = 0;
		int rang = 10;
		int j = 0 ;
		int[] scores = new int[10] ;
		for(Utilisateur u : list) {
			if(u.getName().equals(login)) {
				//On cherche le joueur
				System.out.println("on a trouvé le joueur "+login);
				scores = u.getScores();
				while(scores[j]>score) {
					//On test si le nouveau score est plus grand qu'un des scores enregistrés
					//On note le rang où doit s'insérer le nouveau score
					j ++;
				}
				rang = j;
				System.out.println("Rang : " + rang);
				if(rang<10) {
					resultat = 1 ;
					//on déplace les scores de 1 rang pour ceux qui sont inférieurs au nouveau score
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
			System.out.println("On n'a pas trouvé le joueur");
		}
		return resultat ;
	}
	
	void ChargerTableauUsers() {
		/**
		 * Fonction qui charge le fichier des identifints, mdp et les 10 meilleurs scores
		 * dans la collection de Utilisateurs
		 * Fonction utilisée au lancement du jeu
		 */
		System.out.println("On est dans la fonction ChargerTableauUsers");
		list = new ArrayList<Utilisateur>();
		boolean exist = Files.exists(Paths.get(FICHIERUTILISATEURS)) ;
		FileReader file;
		BufferedReader buff ;
		String chaine = null;
		String[] chainebis = new String[2];
		int[] scores = new int[10] ;
		if(exist) {
			//On lit par groupe de 11 lignes qui correspondent à un utilisateur
			try {
				file = new FileReader(FICHIERUTILISATEURS);
				buff = new BufferedReader(file) ;
				try {
					while((chaine = buff.readLine()) != null) {
						for(int i = 0; i<10 ; i++) {
							scores[i] = Integer.parseInt(buff.readLine());
							chainebis = chaine.split(";");
						}
						Utilisateur user = new Utilisateur(chainebis[0], chainebis[1]) ;
						user.setScores(scores);
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
			System.out.println("Fichier Utilisateurs non trouvé");
		
	}
	
	
	void EcrireFichierUtilisateurs() {
		/**
		 * Fonction qui réécrit le fichier utilisateurs quand on ferme le serveur
		 * On réécrit totalement le fichier
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
				}
				
				buff.close();
				file.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
			System.out.println("Fichier non trouvé");	
	}
	
	
	//----------------------FONCTIONS DE TESTS------------------------------------------------------
	void LireTab() {
		/**
		 * Fonction pour tester
		 */
		String[][] tab = LireScoreG();
		for(int i = 0 ; i<11 ; i++ ) {
			System.out.println(i + " - " + tab[i][0] + " - score : " + tab[i][1]);
		}
		
		ChargerTableauUsers();
		AfficherList();
		int[] tab1 = LireScore("toto5");
		System.out.println("joueur : toto5");
		for(int i = 0 ; i<10 ; i++ ) {
			System.out.println(i+1 + " - " + tab1[i]);
		}
	}
	
	void AfficherUser(String login) {
		String mdp = ChercherUtilisateur(login);
		System.out.println("Identifiant : " + login + " -  mdp : " + mdp);
	}
	
	void AfficherList() {
		int[] scores = new int[10];
		int indice = 0;
		ChargerTableauUsers();
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
	void AfficherList2() {
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
	
	
	//---------------------------------FONCTIONS CSV-----------------------------------------------
	
	
	String DepartementRandom() {
		String departement = null;
		FileReader file ;
		BufferedReader buff ;
		String chaine = null;
		int i = 0 ;
		String[] tab;
		boolean  exist = Files.exists(Paths.get(COMMUNESDEPARTEMENTS)) ;
		
		if(exist) {
			try {
				file = new FileReader(COMMUNESDEPARTEMENTS);
				buff = new BufferedReader(file) ;
				//On tire au hasard la ligne dans le fichier que l'on va conserver
				int valeur = (int) (Math.random() * ( TAILLE )) ;
				System.out.println("taille : " + TAILLE);
				System.out.println("numéro tiré au hasard : " + valeur);
				while(i<valeur) {
					try {
						chaine = buff.readLine();
						i++;
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				try {
					chaine = buff.readLine();
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
			System.out.println("Le fichier n'existe pas");
		System.out.println("chaine lue : " + chaine);
		tab = chaine.split(";") ;
		departement = tab[5];
		return departement ;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
