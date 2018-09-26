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

public class Main extends Application {
	// création de la racine
	private static StackPane root;
	private static Joueur joueur;
 
	 // lancement
	 public static void main(String[] args, Joueur joueur) {
		 launch(args);
	 }
	 
	 // lancement de l'application
	 public void start(Stage primaryStage) {
		 joueur = new Joueur();
		 construireScene(primaryStage);
	 }
	 
	 // construction des objets que l'on va afficher
	 void construireScene(Stage primaryStage)  
	 {
		 // définition de la fenêtre
		 int largeur = 500;
		 int hauteur = 800;
		 
		 root = new StackPane();
		
		 // ajout d'une feuille css
		 root.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
		
		 // ajout de la scène principale dans laquelle vont être contenus les éléments
		 Scene scene = new Scene(root, largeur, hauteur);
		 primaryStage.getIcons().add(new Image("logo.png"));
		 primaryStage.setTitle("GeoQuiz");
		 primaryStage.setScene(scene);
		 
		 // création de la première page de l'application
		 new ConnexionServeur(root, joueur);
		 
	    //afficher la scène
	    primaryStage.show();      
	}
}
