import javax.swing.JOptionPane;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class ConnexionServeur {
	// création de la racine
	private StackPane root;
	 public ConnexionServeur(StackPane root) {
		 this.root = root;
		 root.getChildren().clear();
		 construireConnexionServeur();
	 }
	 
	 // construction des objets que l'on va afficher
	 void construireConnexionServeur()  
	 {
		 // définition des objets graphiques
		 // ajout de texte dans une vbox
		 VBox vboxPrincipal = new VBox();
		 vboxPrincipal.setAlignment(Pos.CENTER);
		 vboxPrincipal.setSpacing(30);
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
			 String message = "Tentative de connexion sur le serveur " + serveur + " et sur le port " + port + ".";
			 JOptionPane.showMessageDialog(null, message);
			 new Accueil(root);
		 });
		 
		 vboxPrincipal.getChildren().addAll(image, hboxServeur, hboxPort, boutonValider);
		 
		 root.getChildren().addAll(vboxPrincipal);    
	}
}
