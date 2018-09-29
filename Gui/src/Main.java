import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {
	// création de la racine
	private static StackPane root;
	private static Joueur joueur;
	private static Stage primaryStage;
 
	 // lancement
	 public static void main(String[] args) {
		 launch(args);
		 FonctionsCSV csv = new FonctionsCSV();
		 String[] cap = csv.CouplePaysCapitale();
		 System.out.println("Pays : " + cap[0] + " - capitale : " + cap[1]);
		 String pays = csv.PaysRandom();
		 System.out.println("Pays tiré au sort : " + pays);
		 String capitale = csv.CapitaleRandom();
		 System.out.println("Capitale tiré au sort : " + capitale);
	 }
	 
	 // lancement de l'application
	 public void start(Stage primaryStage) {
		 this.primaryStage = primaryStage;
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
	 
	 public static Stage getStage() {
		 return primaryStage;
	 }
}
