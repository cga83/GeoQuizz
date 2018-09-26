import java.util.Random;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

// fonctions utilitaires
public class Utils {
	public static Node returnToPreviousPage(Object actualPage, StackPane root, String login) {
		 HBox hboxMenu = new HBox();
		 hboxMenu.setAlignment(Pos.TOP_RIGHT);
		 Button boutonRetour = new Button();
		 boutonRetour.setOnAction(value ->  {
			 runPreviousPage(actualPage, root, login);
		 });
		 boutonRetour.getStyleClass().add("buttonMenu");
		 hboxMenu.getChildren().add(boutonRetour);
		 
		 return hboxMenu;
	}
	
	public static Node exitApp() {
		 HBox hboxMenu = new HBox();
		 hboxMenu.setAlignment(Pos.TOP_LEFT);
		 Button boutonExit = new Button();
		 boutonExit.setOnAction(value ->  {
		       Platform.exit();
		       System.exit(0);
		 });
		 boutonExit.getStyleClass().add("buttonExit");
		 hboxMenu.getChildren().add(boutonExit);
		 
		 return hboxMenu;
	}
	
	public static Node createMenu(Object actualPage, StackPane root) {
		 // ajout du titre "GeoQuiz" dans une HBox
		 // création et positionnement de la HBox
		 HBox hboxTitre = new HBox();
		 hboxTitre.setAlignment(Pos.TOP_CENTER); 
		 // Création de la première partie du texte
		 Text titreGeo = new Text();
		 titreGeo.setFont(Font.font ("Lato", 50));
		 titreGeo.setFill(Color.rgb(255,170,170));
		 titreGeo.setText("GEO");
		 titreGeo.setRotate(-12);
		 // Création de la deuxième partie du texte
		 Text titreQuiz = new Text();
		 titreQuiz.setFont(Font.font ("Verdana", 50));
		 titreQuiz.setFill(Color.WHITE);
		 titreQuiz.setText("Quiz");
		 titreQuiz.setRotate(12);
		 // Ajout du texte à la Hbox
		 hboxTitre.getChildren().addAll(titreGeo, titreQuiz);
		 
		 String login;
		 // TO DO : get login from joueur
		 login = "titi"; 
		 // bouton retour
		 Node hboxMenuPrecent = returnToPreviousPage(actualPage, root, login);
		 Node hboxMenuExit = exitApp();
		 
		 // positionnement du menu par rapport au titre
		 BorderPane menu = new BorderPane();
		 menu.setCenter(hboxTitre);
		 menu.setRight(hboxMenuPrecent);
		 menu.setLeft(hboxMenuExit);
		 
		 return menu;
	}
	
	private static void runPreviousPage(Object actualPage, StackPane root, String login) { // TO DO : le login sera stocké dans la classe joueur
		if (actualPage instanceof Accueil)
			new ConnexionServeur(root);
		if (actualPage instanceof ConnexionOuInscription)
			new Accueil(root);	
		if (actualPage instanceof PageJoueur)
			// TO DO : classe joueur -> fonction déco 
			new Accueil(root);
		if (actualPage instanceof DemarrerJeu)
			new PageJoueur(root, login);
		if (actualPage instanceof PageRegles)
			new DemarrerJeu(root, login);
		if (actualPage instanceof PageClassement)
			new PageJoueur(root, login);
		if (actualPage instanceof PageClassementMeilleurScore)
			new PageJoueur(root, login);
		if (actualPage instanceof PageScores)
			new PageJoueur(root, login);
		if (actualPage instanceof LancerQuestion)
			new DemarrerJeu(root, login);
		
	}
	
	public static String[] shuffleArray(String[] array)
	{
	    int index;
	    String temp;
	    Random random = new Random();
	    for (int i = array.length - 1; i > 0; i--)
	    {
	        index = random.nextInt(i + 1);
	        temp = array[index];
	        array[index] = array[i];
	        array[i] = temp;
	    }
	    return array;
	}
}
