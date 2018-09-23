// pour le debug
import javax.swing.JOptionPane;

// import javafx
import javafx.animation.FadeTransition;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;
     
// classe accueil = interface utilisateur pour la connexion et/ou inscription
public class Accueil {
	// création de la racine
	private StackPane root;
 
	public Accueil(StackPane root) {
		this.root = root;
		construireAccueil();
	}
	 
	 // construction des objets que l'on va afficher
	 private void construireAccueil()  
	 {
		 // on enlève les objets de la page de connection au serveur
		 root.getChildren().clear();
		 
		 // définition des objets graphiques
		 // ajout du titre "GeoQuiz" dans une HBox
		 // création et positionnement de la HBox
		 HBox hboxTitre = new HBox();
		 hboxTitre.setAlignment(Pos.TOP_CENTER); 
		 // Création de la première partie du texte
		 Text titreGeo = new Text();
		 titreGeo.setFont(Font.font ("Lato", 50));
		 titreGeo.setTextAlignment(TextAlignment.CENTER);
		 titreGeo.setFill(Color.rgb(255,170,170));
		 titreGeo.setText("GEO");
		 titreGeo.setRotate(-12);
		 // Création de la deuxième partie du texte
		 Text titreQuiz = new Text();
		 titreQuiz.setFont(Font.font ("Verdana", 50));
		 titreQuiz.setTextAlignment(TextAlignment.CENTER);
		 titreQuiz.setFill(Color.WHITE);
		 titreQuiz.setText("Quiz");
		 titreQuiz.setRotate(12);
		 // Ajout du texte à la Hbox
		 hboxTitre.getChildren().addAll(titreGeo, titreQuiz);
		
		 // ajout d'une image
		 ImageView geoImage = new ImageView(new Image("background.png"));
		 geoImage.setFitWidth(450);
		 geoImage.setFitHeight(450);
		
		 // ajout de boutons dans une vbox
		 // création et positionnement de la vbox
		 VBox vboxBoutons = new VBox();
		 vboxBoutons.setAlignment(Pos.BOTTOM_CENTER);
		 vboxBoutons.setSpacing(20);
		 // ajout d'un bouton connexion
		 Button boutonConnexion = new Button("Se connecter");
		 // action sur le bouton
		 boutonConnexion.setOnAction(value ->  {
			 JOptionPane.showMessageDialog(null, "Connexion désirée");
			 new ConnexionOuInscription(root, ConnexionOuInscription.Mode.CONNEXION);
		 });
		 // ajout d'une classe pour pouvoir utiliser le style css
		 boutonConnexion.getStyleClass().add("button");
		// ajout d'un bouton s'inscrire
		Button boutonInscription = new Button("S'inscrire");
		boutonInscription.setOnAction(value ->  {
			JOptionPane.showMessageDialog(null, "Inscription désirée");
			 new ConnexionOuInscription(root, ConnexionOuInscription.Mode.INSCRIPTION);
		});
		boutonInscription.getStyleClass().add("button");
		// ajout des boutons à la vbox
		vboxBoutons.getChildren().addAll(boutonConnexion, boutonInscription);
	
		// ajout de tous les objets à la racine (qui est reliée à primaryStage)
	    root.getChildren().addAll(hboxTitre, geoImage, vboxBoutons);
	 
	    // ajout d'une transition sur l'opacité de l'image
	    FadeTransition transitionImage = new FadeTransition(Duration.millis(3000), geoImage);
	    transitionImage.setFromValue(0.01);
	    transitionImage.setToValue(1.0);
	    transitionImage.play();  
	}
}
