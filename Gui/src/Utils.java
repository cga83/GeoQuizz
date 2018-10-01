import java.util.Random;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
import javafx.stage.Popup;
import javafx.util.Duration;

// fonctions utilitaires
public class Utils {
	public static Node returnToPreviousPage(Object actualPage, StackPane root, Joueur joueur) {
		 HBox hboxMenu = new HBox();
		 hboxMenu.setAlignment(Pos.TOP_RIGHT);
		 Button boutonRetour = new Button();
		 boutonRetour.setOnAction(value ->  {
			 runPreviousPage(actualPage, root, joueur);
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
	
	public static Node createMenu(Object actualPage, StackPane root, Joueur joueur) {
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
		 Node hboxMenuPrecent = returnToPreviousPage(actualPage, root, joueur);
		 Node hboxMenuExit = exitApp();
		 
		 // positionnement du menu par rapport au titre
		 BorderPane menu = new BorderPane();
		 menu.setCenter(hboxTitre);
		 menu.setRight(hboxMenuPrecent);
		 menu.setLeft(hboxMenuExit);
		 
		 return menu;
	}
	
	private static void runPreviousPage(Object actualPage, StackPane root, Joueur joueur) { // TO DO : le login sera stocké dans la classe joueur
		if (actualPage instanceof Accueil)
			new ConnexionServeur(root, joueur);
		if (actualPage instanceof ConnexionOuInscription)
			new Accueil(root, joueur);	
		if (actualPage instanceof PageJoueur)
			// TO DO : classe joueur -> fonction déco 
			new Accueil(root, joueur);
		if (actualPage instanceof DemarrerJeu)
			new PageJoueur(root, joueur);
		if (actualPage instanceof PageRegles)
			new DemarrerJeu(root, joueur);
		if (actualPage instanceof PageClassement)
			new PageJoueur(root, joueur);
		if (actualPage instanceof PageClassementMeilleurScore)
			new PageJoueur(root, joueur);
		if (actualPage instanceof PageScores)
			new PageJoueur(root, joueur);
		if (actualPage instanceof LancerQuestion)
			new DemarrerJeu(root, joueur);
		if (actualPage instanceof PartieFinie)
			new DemarrerJeu(root, joueur);
		
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
	
	public static void createPopup(String message) {
		  Popup popup = new Popup();
		  HBox hboxPopup = new HBox();
		  Text textLabel = new Text(message);
		  hboxPopup.getChildren().add(textLabel);
		  hboxPopup.setStyle("-fx-background-color: rgb(255,170,170); -fx-border-color:black; -fx-font-size: 12; -fx-padding: 15;");
		  popup.setAutoHide(true);
		  popup.setAutoFix(true);
		  // Calculate popup placement coordinates.
		  popup.getContent().addAll(hboxPopup);
		  popup.show(Main.getStage());
	}
	
	public static void disarmButtons(Button button1, Button button2, Button button3, Button button4) {
		button1.setDisable(true);
		button2.setDisable(true);
		button3.setDisable(true);
		button4.setDisable(true);
	}
	
	public static void checkResponse(StackPane root, Joueur joueur, int round, int score, int nbquestion, Button buttonClicked, Button button2, Button button3, Button button4, String bonneReponse) {
			// si c'est la bonne reponse alors on l'indique en vert sinon en rouge
			if (buttonClicked.getText().equals(bonneReponse)) {
				buttonClicked.setStyle("-fx-background-color: green;");
				// on désactive les boutons
				Utils.disarmButtons(buttonClicked, button2, button3, button4);
				// score augmente
				score++;
			} else if (!buttonClicked.getText().equals(bonneReponse)) {
				buttonClicked.setStyle("-fx-background-color: red;");
				// on désactive les boutons
				Utils.disarmButtons(buttonClicked, button2, button3, button4);
			}
			// on passe à la question suivante si on a pas fait toutes les questions
			if (round < nbquestion) {
				final int scoreFinal = score;
				// TO DO : supprimer ça et à chaque fois qu'on lance une nouvelle question prendre une question aléatoirement
				String[] questionReponsesRound2 = { "Département 13 ?", "Haute Corse", "Var", "Seine St Denis","Bouches du Rhones" };
				Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.5),
					a -> new LancerQuestion(root, joueur, questionReponsesRound2, round + 1, scoreFinal)));
				timeline.play();
			} else {
				final int scoreFinal = score;
				joueur.giveScoreToserver(scoreFinal);
				Timeline timeline = new Timeline(
						new KeyFrame(Duration.seconds(0.5), a -> new PartieFinie(root, joueur, scoreFinal)));
				timeline.play();
			}
	}
}
