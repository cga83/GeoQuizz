import javax.swing.JOptionPane;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

// fonctions utilitaires
public class Utils {
	public static Node returnToPreviousPage(Object actualPage, StackPane root) {
		HBox hboxMenu = new HBox();
		 hboxMenu.setAlignment(Pos.CENTER_RIGHT);
		 Button boutonRetour = new Button();
		 boutonRetour.setOnAction(value ->  {
			 JOptionPane.showMessageDialog(null, "retour désiré");
			 runPreviousPage(actualPage, root);
		 });
		 boutonRetour.getStyleClass().add("buttonMenu");
		 hboxMenu.getChildren().add(boutonRetour);
		 
		 return hboxMenu;
	}
	
	private static void runPreviousPage(Object actualPage, StackPane root) {
		if (actualPage instanceof Accueil)
			new ConnexionServeur(root);
		if (actualPage instanceof ConnexionOuInscription)
			new Accueil(root);
	}
}
