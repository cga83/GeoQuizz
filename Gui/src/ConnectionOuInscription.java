// TO DO : récupérer un argument "I" ou "C" et en fonction afficher un titre différent et traiter la suite différement

import javax.swing.JOptionPane;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class ConnectionOuInscription extends Application {
	// création de la racine
	StackPane root;
 
	 // lancement de l'application
	 public void start(Stage primaryStage) {
		 construireScene(primaryStage);
	 }
	 
	 // construction des objets que l'on va afficher
	 void construireScene(Stage scenePrincipale)  
	 {
		 // définition de la fenêtre
		 int largeur = 500;
		 int hauteur = 800;
		 
		 root = new StackPane();
		
		 // ajout d'une feuille css
		 root.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
		
		 // ajout de la scène principale dans laquelle vont être contenus les éléments
		 Scene scene = new Scene(root, largeur, hauteur);
		 scenePrincipale.getIcons().add(new Image("logo.png"));
		 scenePrincipale.setTitle("GeoQuiz");
		 scenePrincipale.setScene(scene);
	 
		 // définition des objets graphiques
		 // ajout de texte dans une vbox
		 VBox vboxPrincipal = new VBox();
		 vboxPrincipal.setAlignment(Pos.CENTER);
		 vboxPrincipal.setSpacing(30);
		 Text titre = new Text("Connecte-toi !");
		 titre.setFont(Font.font ("Lato", 50));
		 titre.setTextAlignment(TextAlignment.CENTER);
		 titre.setFill(Color.rgb(255,170,170));
		 vboxPrincipal.getChildren().add(titre);

		 // choix du serveur et du port
		 HBox hboxPseudo = new HBox();
		 hboxPseudo.setAlignment(Pos.CENTER);
		 Text textePseudo = new Text("pseudo :          ");
		 textePseudo.setFill(Color.WHITE);
		 TextField choixPseudo = new TextField();
		 hboxPseudo.getChildren().addAll(textePseudo, choixPseudo);
		 HBox hboxMdp = new HBox();
		 hboxMdp.setAlignment(Pos.CENTER);
		 Text texteMdp = new Text("mot de passe : ");
		 texteMdp.setFill(Color.WHITE);
		 TextField choixMdp = new TextField();
		 hboxMdp.getChildren().addAll(texteMdp, choixMdp);
		 
		 // bouton pour valider
		 Button boutonValider = new Button("Ok !");
		 boutonValider.getStyleClass().add("button");
		 boutonValider.setOnAction(value ->  {
			 String mdp = choixMdp.getText();
			 String pseudo = choixPseudo.getText();
			 String message = "Tentative de connexion de " + pseudo + " avec le mdp " + mdp + ".";
			JOptionPane.showMessageDialog(null, message);
		 });
		 
		 vboxPrincipal.getChildren().addAll(hboxPseudo, hboxMdp, boutonValider);
		 
		 root.getChildren().addAll(vboxPrincipal);
		 
	
		// ajout de tous les objets à la racine (qui est reliée à primaryStage)
	    root.getChildren().addAll();
	 
	    //afficher la scène
	    scenePrincipale.show();      
	 }
	   
	 // lancement
	 public static void main(String[] args) {
		 launch(args);
	 }
}
