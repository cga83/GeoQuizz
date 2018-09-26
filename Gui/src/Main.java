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
	// cr�ation de la racine
	private static StackPane root;
 
	 // lancement
	 public static void main(String[] args) {
		 launch(args);
	 }
	 
	 // lancement de l'application
	 public void start(Stage primaryStage) {
		 construireScene(primaryStage);
	 }
	 
	 // construction des objets que l'on va afficher
	 void construireScene(Stage primaryStage)  
	 {
		 // d�finition de la fen�tre
		 int largeur = 500;
		 int hauteur = 800;
		 
		 root = new StackPane();
		
		 // ajout d'une feuille css
		 root.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
		
		 // ajout de la sc�ne principale dans laquelle vont �tre contenus les �l�ments
		 Scene scene = new Scene(root, largeur, hauteur);
		 primaryStage.getIcons().add(new Image("logo.png"));
		 primaryStage.setTitle("GeoQuiz");
		 primaryStage.setScene(scene);
		 
		 // cr�ation de la premi�re page de l'application
		 new ConnexionServeur(root);
		 
	    //afficher la sc�ne
	    primaryStage.show();      
	}
}
