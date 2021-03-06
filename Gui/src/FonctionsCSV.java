import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FonctionsCSV {

	//System.setProperty("file.encoding","UTF-8");
	private static final String COMMUNESDEPARTEMENTS = "regions_departements.csv" ;
	private static final int TAILLEFICHIERCOMMUNES = 36840 ;
	private static final String PAYSCAPITALES = "capitales2.csv";
	private static final int TAILLECAPITALES = 47 ;
	
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
				int valeur = (int) (Math.random() * ( TAILLEFICHIERCOMMUNES )) ;
				try {
					while(i<valeur) {
						chaine = buff.readLine();
						i++;
					}
					chaine = buff.readLine();
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
			System.out.println("Le fichier n'existe pas");
		tab = chaine.split(";") ;
		departement = tab[5];
		return departement ;
	}
	
	String NumeroDepRandom() {
		String numero ;
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
				int valeur = (int) (Math.random() * ( TAILLEFICHIERCOMMUNES )) ;
				try {
					while(i<valeur) {
						chaine = buff.readLine();
						i++;
					}
					chaine = buff.readLine();
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
			System.out.println("Le fichier n'existe pas");
		tab = chaine.split(";") ;
		numero = tab[4];
		return numero ;
		
	}
	
	String RegionRandom() {
		String resultat;
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
				int valeur = (int) (Math.random() * ( TAILLEFICHIERCOMMUNES )) ;
				try {
					while(i<valeur) {
						chaine = buff.readLine();
						i++;
					}
					chaine = buff.readLine();
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
			System.out.println("Le fichier n'existe pas");
		tab = chaine.split(";") ;
		resultat = tab[2];
		return resultat;
	}

	String CheflieuRandom() {
		String resultat;
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
				int valeur = (int) (Math.random() * ( TAILLEFICHIERCOMMUNES )) ;
				try {
					while(i<valeur) {
						chaine = buff.readLine();
						i++;
					}
					chaine = buff.readLine();
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
			System.out.println("Le fichier n'existe pas");
		tab = chaine.split(";") ;
		resultat = tab[3];
		return resultat;
	}

	String PaysRandom() {
		String resultat ;
		FileReader file ;
		BufferedReader buff ;
		String chaine = null;
		int i = 0 ;
		int indice = (int) (Math.random() * ( 4 )) ; ;
		String[] tab;
		boolean  exist = Files.exists(Paths.get(PAYSCAPITALES)) ;
		if(exist) {
			try {
				file = new FileReader(PAYSCAPITALES);
				buff = new BufferedReader(file) ;
				//On tire au hasard la ligne dans le fichier que l'on va conserver
				int valeur = (int) (Math.random() * ( TAILLECAPITALES )) ;
				try {
					while(i<valeur) {
						chaine = buff.readLine();
						i++;
					}
					chaine = buff.readLine();
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
			System.out.println("Le fichier n'existe pas");
		tab = chaine.split(";") ;
		resultat = tab[2*indice];
		return resultat;
	}

	String CapitaleRandom() {
		String resultat ;
		FileReader file ;
		BufferedReader buff ;
		String chaine = null;
		int i = 0 ;
		int indice = (int) (Math.random() * ( 4 )) ; ;
		String[] tab;
		boolean  exist = Files.exists(Paths.get(PAYSCAPITALES)) ;
		if(exist) {
			try {
				file = new FileReader(PAYSCAPITALES);
				buff = new BufferedReader(file) ;
				//On tire au hasard la ligne dans le fichier que l'on va conserver
				int valeur = (int) (Math.random() * ( TAILLECAPITALES )) ;
				try {
					while(i<valeur) {
						chaine = buff.readLine();
						i++;
					}
					chaine = buff.readLine();
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
			System.out.println("Le fichier n'existe pas");
		tab = chaine.split(";") ;
		resultat = tab[2*indice+1];
		return resultat;
	}

	String[] CoupleDepartementNumero() {
		String resultat[] = new String[2];
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
				int valeur = (int) (Math.random() * ( TAILLEFICHIERCOMMUNES )) ;
				try {
					while(i<valeur) {
						chaine = buff.readLine();
						i++;
					}
					chaine = buff.readLine();
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
			System.out.println("Le fichier n'existe pas");
		tab = chaine.split(";") ;
		resultat[0] = tab[5];
		resultat[1] = tab[4];
		return resultat;
	}
	
	String[] CoupleRegionCheflieu() {
		String resultat[] = new String[2];
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
				int valeur = (int) (Math.random() * ( TAILLEFICHIERCOMMUNES )) ;
				try {
					while(i<valeur) {
						chaine = buff.readLine();
						i++;
					}
					chaine = buff.readLine();
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
			System.out.println("Le fichier n'existe pas");
		tab = chaine.split(";") ;
		resultat[0] = tab[2];
		resultat[1] = tab[3];
		return resultat;
	}
	
	String[] CoupleRegionDepartement() {
		String resultat[] = new String[2];
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
				int valeur = (int) (Math.random() * ( TAILLEFICHIERCOMMUNES )) ;
				try {
					while(i<valeur) {
						chaine = buff.readLine();
						i++;
					}
					chaine = buff.readLine();
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
			System.out.println("Le fichier n'existe pas");
		tab = chaine.split(";") ;
		resultat[0] = tab[2];
		resultat[1] = tab[5];
		return resultat;
	}
	
	String[] CouplePaysCapitale() {
		String resultat[] = new String[2];
		FileReader file ;
		BufferedReader buff ;
		String chaine = null;
		int i = 0 ;
		int indice = (int) (Math.random() * ( 4 )) ; ;
		String[] tab;
		boolean  exist = Files.exists(Paths.get(PAYSCAPITALES)) ;
		if(exist) {
			try {
				file = new FileReader(PAYSCAPITALES);
				buff = new BufferedReader(file) ;
				//On tire au hasard la ligne dans le fichier que l'on va conserver
				int valeur = (int) (Math.random() * ( TAILLECAPITALES )) ;

				try {
					while(i<valeur) {
						chaine = buff.readLine();
						i++;
					}
					chaine = buff.readLine();
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
			System.out.println("Le fichier n'existe pas");
		tab = chaine.split(";") ;
		resultat[0] = tab[2*indice];
		resultat[1] = tab[2*indice+1];
		return resultat;
	}
	
}
