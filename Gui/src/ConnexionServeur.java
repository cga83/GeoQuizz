import javax.swing.JOptionPane;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class ConnexionServeur {
	// création de la racine
	private StackPane root;
	 public ConnexionServeur(StackPane root) {
		 this.root = root;
		 root.getChildren().clear();
		 construireConnexionServeur(joueur);
	 }
	 
	 // construction des objets que l'on va afficher
	 void construireConnexionServeur(Joueur joueur)  
	 {
		 // creation d'un borderPane auquel on va ajouter les objets
		 BorderPane pane = new BorderPane();
				 
		 // définition des objets graphiques
		 // ajout de texte dans une vbox
		 VBox vboxPrincipal = new VBox();
		 //vboxPrincipal.setAlignment(Pos.CENTER);
		// vboxPrincipal.setSpacing(30);
		 Text texteBienvenue = new Text("Bienvenue !");
		 texteBienvenue.setFont(Font.font ("Lato", 50));
		 texteBienvenue.setTextAlignment(TextAlignment.CENTER);
		 texteBienvenue.setFill(Color.rgb(255,170,170));
		 Text texteChoixServeur = new Text("Choisis ton serveur et ton port.");
		 texteChoixServeur.setFont(Font.font ("Verdana", 20));
		 texteChoixServeur.setTextAlignment(TextAlignment.CENTER);
		 texteChoixServeur.setFill(Color.WHITE);
		 vboxPrincipal.getChildren().addAll(texteBienvenue, texteChoixServeur);
		 
		 // ajout d'une image
		 ImageView image = new ImageView(new Image("happyWorld.png"));
		 
		 // choix du serveur et du port
		 HBox hboxServeur = new HBox();
		 hboxServeur.setAlignment(Pos.CENTER);
		 Text texteServeur = new Text("serveur : ");
		 texteServeur.setFill(Color.WHITE);
		 TextField choixServeur = new TextField("localhost");
		 hboxServeur.getChildren().addAll(texteServeur, choixServeur);
		 HBox hboxPort = new HBox();
		 hboxPort.setAlignment(Pos.CENTER);
		 Text textePort = new Text("port :      ");
		 textePort.setFill(Color.WHITE);
		 TextField choixPort = new TextField("10081");
		 hboxPort.getChildren().addAll(textePort, choixPort);
		 
		 // bouton pour valider
		 Button boutonValider = new Button("Ok !");
		 boutonValider.getStyleClass().add("buttonStyle1");
		 boutonValider.setOnAction(value ->  {
			 String port = choixPort.getText();
			 String serveur = choixServeur.getText();
			 int portInt = Integer.parseInt(port);
			 String message = "Tentative de connexion sur le serveur " + serveur + " et sur le port " + port + ".";
			 JOptionPane.showMessageDialog(null, message);
			 
			 //joueur.connectAuServeur(serveur,portInt);
			 //new Accueil(root);
		 });
		 
		 VBox vboxChoix = new VBox();
		 vboxChoix.getChildren().addAll(hboxServeur, hboxPort, boutonValider);
		 
		 // on positionne les objets
		 vboxPrincipal.setAlignment(Pos.CENTER);
		 vboxPrincipal.setSpacing(15);
		 pane.setTop(vboxPrincipal);
		 vboxChoix.setAlignment(Pos.CENTER);
		 vboxChoix.setSpacing(25);
		 pane.setBottom(vboxChoix);
		 pane.setCenter(image);
		 
		 root.getChildren().addAll(pane);    
	}
}
