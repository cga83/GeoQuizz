// pour le debug
import javax.swing.JOptionPane;

// import javafx
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
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
import javafx.stage.Stage;
import javafx.util.Duration;
     
// classe accueil = interface utilisateur pour la connexion et/ou inscription
public class Accueil extends Application {
	// cr�ation de la racine
	StackPane root;
 
	 // lancement de l'application
	 public void start(Stage primaryStage) {
		 construireScene(primaryStage);
	 }
	 
	 // construction des objets que l'on va afficher
	 void construireScene(Stage scenePrincipale)  
	 {
		 // d�finition de la fen�tre
		 int largeur = 500;
		 int hauteur = 800;
		 
		 root = new StackPane();
		
		 // ajout d'une feuille css
		 root.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
		
		 // ajout de la sc�ne principale dans laquelle vont �tre contenus les �l�ments
		 Scene scene = new Scene(root, largeur, hauteur);
		 scenePrincipale.getIcons().add(new Image("logo.png"));
		 scenePrincipale.setTitle("GeoQuiz");
		 scenePrincipale.setScene(scene);
	 
		 // d�finition des objets graphiques
		 // ajout du titre "GeoQuiz" dans une HBox
		 // cr�ation et positionnement de la HBox
		 HBox hboxTitre = new HBox();
		 hboxTitre.setAlignment(Pos.TOP_CENTER); 
		 // Cr�ation de la premi�re partie du texte
		 Text titreGeo = new Text();
		 titreGeo.setFont(Font.font ("Lato", 50));
		 titreGeo.setTextAlignment(TextAlignment.CENTER);
		 titreGeo.setFill(Color.rgb(255,170,170));
		 titreGeo.setText("GEO");
		 titreGeo.setRotate(-12);
		 // Cr�ation de la deuxi�me partie du texte
		 Text titreQuiz = new Text();
		 titreQuiz.setFont(Font.font ("Verdana", 50));
		 titreQuiz.setTextAlignment(TextAlignment.CENTER);
		 titreQuiz.setFill(Color.WHITE);
		 titreQuiz.setText("Quiz");
		 titreQuiz.setRotate(12);
		 // Ajout du texte � la Hbox
		 hboxTitre.getChildren().addAll(titreGeo, titreQuiz);
		
		 // ajout d'une image
		 ImageView geoImage = new ImageView(new Image("background.png"));
		 geoImage.setFitWidth(450);
		 geoImage.setFitHeight(450);
		
		 // ajout de boutons dans une vbox
		 // cr�ation et positionnement de la vbox
		 VBox vboxBoutons = new VBox();
		 vboxBoutons.setAlignment(Pos.BOTTOM_CENTER);
		 vboxBoutons.setSpacing(20);
		 // ajout d'un bouton connexion
		 Button boutonConnexion = new Button("Se connecter");
		 // action sur le bouton
		 boutonConnexion.setOnAction(value ->  {
			 JOptionPane.showMessageDialog(null, "Connexion d�sir�e");
		 });
		 // ajout d'une classe pour pouvoir utiliser le style css
		 boutonConnexion.getStyleClass().add("button");
		// ajout d'un bouton s'inscrire
		Button boutonInscription = new Button("S'inscrire");
		boutonInscription.setOnAction(value ->  {
			JOptionPane.showMessageDialog(null, "Inscription d�sir�e");
		});
		boutonInscription.getStyleClass().add("button");
		// ajout des boutons � la vbox
		vboxBoutons.getChildren().addAll(boutonConnexion, boutonInscription);
	
		// ajout de tous les objets � la racine (qui est reli�e � primaryStage)
	    root.getChildren().addAll(hboxTitre, geoImage, vboxBoutons);
	 
	    // ajout d'une transition sur l'opacit� de l'image
	    FadeTransition transitionImage = new FadeTransition(Duration.millis(3000), geoImage);
	    transitionImage.setFromValue(0.01);
	    transitionImage.setToValue(1.0);
	    transitionImage.play();
	 
	    //afficher la sc�ne
	    scenePrincipale.show();      
	}
	   
	 // lancement
	 public static void main(String[] args) {
		 launch(args);
		 FonctionsCSV csv = new FonctionsCSV();
		 csv.LireTab();
	 }
}
