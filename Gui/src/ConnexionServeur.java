import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
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
import javafx.util.Duration;

public class ConnexionServeur {
	// création de la racine
	private StackPane root;
	 public ConnexionServeur(StackPane root, Joueur joueur) {
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
		 TextField choixPort = new TextField("2000");
		 hboxPort.getChildren().addAll(textePort, choixPort);
		 
		 // bouton pour valider
		 Button boutonValider = new Button("Ok !");
		 boutonValider.getStyleClass().add("buttonStyle1");
		 boutonValider.setOnAction(value ->  {
			 String port = choixPort.getText();
			 String serveur = choixServeur.getText();
			 int portInt = Integer.parseInt(port);
			 
			 boolean connected = joueur.connectAuServeur(serveur,portInt);
			 if (connected) {
				 new Accueil(root,joueur);
			 } else {
				 String message = "La tentative de connexion sur le serveur " + serveur + " et sur le port " + port + " a échouée.";
				 Utils.createPopup(message); 
			 }
			 
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

		 
		// ajout animation
	    FadeTransition transitionImage = new FadeTransition(Duration.millis(3000), image);
	    transitionImage.setFromValue(0.01);
	    transitionImage.setToValue(1.0);
	    transitionImage.play();  
	    
		RotateTransition rotateAnimation = new RotateTransition(Duration.seconds(1), image); 
		rotateAnimation.setCycleCount(1); 
		rotateAnimation.setByAngle(360); 
		rotateAnimation.setInterpolator(Interpolator.LINEAR); 
		rotateAnimation.play(); 
		 
		 root.getChildren().addAll(pane);    
	}
}
