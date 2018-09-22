import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FonctionsCSV {

	//private static final String FICHIERUTILISATEURS = "utilisateurs.csv";
	private static final String FICHIERCLASSEMENTG = "classementG.csv";
	private static final String FICHIERCLASSEMENTP = "classementP.csv";


	
	FonctionsCSV() {
		System.out.println("Appel de la classe FonctionsCSV");
	}
	
	String[][] LireScoreG() {
		/**
		 * Fonction de lecture du fichier des classements globaux sur toutes les parties
		 */
		String[] scoresg = new String[11];
		String[][] scores = new String[11][2];
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
				for(i=0 ; i<11 ; i++) {
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
		for(int k = 0 ; k<11 ; k++) {
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
		String[] scoresp = new String[11];
		String[][] scores = new String[11][2];
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
				for(i=0 ; i<11 ; i++) {
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
		for(int k = 0 ; k<11 ; k++) {
				chainebis = scoresp[k].split(";");
				scores[k][0] = chainebis[0];
				scores[k][1] = chainebis[1];
			}
		
		return scores ;
	}
	
	String[] LireScore(String login) {
		/**
		 * Fonction de lecture des meilleurs scores d'un joueur
		 */
		String[] scoresg = new String[10];
		String chaine = null;
		String nom = login + ".csv";
		boolean exist = Files.exists(Paths.get(nom)) ;
		FileReader file;
		BufferedReader buff ;
		int i;
		//On vérifie que le fichier existe bien
		if(exist) {
			System.out.println("On a bien trouvé le fichier du user");
			try {
				file = new FileReader(nom);
				buff = new BufferedReader(file) ;
				for(i=0 ; i<10 ; i++) {
					try {
						//On lit la ligne du fichier dans chaine
						chaine = buff.readLine();
						//On stocke cette ligne dans un tableau
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
		return scoresg ;
	}
	
	void LireTab() {
		/**
		 * Fonction pour tester
		 */
		String[][] tab = LireScoreG();
		for(int i = 0 ; i<11 ; i++ ) {
			System.out.println(i + " - " + tab[i][0] + " - score : " + tab[i][1]);
		}
		String[] tab1 = LireScore("titi");
		System.out.println("joueur : titi");
		for(int i = 0 ; i<10 ; i++ ) {
			System.out.println(i+1 + " - " + tab1[i]);
		}
	}
}
