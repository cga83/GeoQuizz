// pour le debug
import javax.swing.JOptionPane;

// import javafx
import javafx.animation.FadeTransition;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
     
// classe accueil = interface utilisateur pour la connexion et/ou inscription
public class Accueil {
	// création de la racine
	private StackPane root;
 
	public Accueil(StackPane root) {
		 this.root = root;
		 FonctionsCSV csv = new FonctionsCSV();
		 csv.LireTab();
		 construireAccueil();
	}
	 
	 // construction des objets que l'on va afficher
	 private void construireAccueil()  
	 {
		 // on enlève les objets de la page de connection au serveur
		 root.getChildren().clear();
		 
		 // creation d'un borderPane auquel on va ajouter les objets
		 BorderPane pane = new BorderPane();
		 
		 // définition des objets graphiques
		 // création du menu
		 Node menu = Utils.createMenu(this, root);
		
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
		 boutonConnexion.getStyleClass().add("buttonStyle1");
		// ajout d'un bouton s'inscrire
		Button boutonInscription = new Button("S'inscrire");
		boutonInscription.setOnAction(value ->  {
			JOptionPane.showMessageDialog(null, "Inscription désirée");
			 new ConnexionOuInscription(root, ConnexionOuInscription.Mode.INSCRIPTION);
		});
		boutonInscription.getStyleClass().add("buttonStyle1");
		// ajout des boutons à la vbox
		vboxBoutons.getChildren().addAll(boutonConnexion, boutonInscription);
		vboxBoutons.setPickOnBounds(false);
		
		// positionnement des objets
		pane.setTop(menu);
		pane.setCenter(geoImage);
		pane.setBottom(vboxBoutons);
		
		// ajout de tous les objets à la racine (qui est reliée à primaryStage)
	    root.getChildren().add(pane);
	 
	    // ajout d'une transition sur l'opacité de l'image
	    FadeTransition transitionImage = new FadeTransition(Duration.millis(3000), geoImage);
	    transitionImage.setFromValue(0.01);
	    transitionImage.setToValue(1.0);
	    transitionImage.play();  
	}
}
