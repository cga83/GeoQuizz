import javax.swing.JOptionPane;

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
	public static Node returnToPreviousPage(Object actualPage, StackPane root) {
		 HBox hboxMenu = new HBox();
		 hboxMenu.setAlignment(Pos.TOP_RIGHT);
		 Button boutonRetour = new Button();
		 boutonRetour.setOnAction(value ->  {
			 JOptionPane.showMessageDialog(null, "retour désiré");
			 runPreviousPage(actualPage, root);
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
			 JOptionPane.showMessageDialog(null, "exit");
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
		 
		 // bouton retour
		 Node hboxMenuPrecent = returnToPreviousPage(actualPage, root);
		 Node hboxMenuExit = exitApp();
		 
		 // positionnement du menu par rapport au titre
		 BorderPane menu = new BorderPane();
		 menu.setCenter(hboxTitre);
		 menu.setRight(hboxMenuPrecent);
		 menu.setLeft(hboxMenuExit);
		 
		 return menu;
	}
	
	
	private static void runPreviousPage(Object actualPage, StackPane root) {
		if (actualPage instanceof Accueil)
			new ConnexionServeur(root);
		if (actualPage instanceof ConnexionOuInscription)
			new Accueil(root);
	}
}
