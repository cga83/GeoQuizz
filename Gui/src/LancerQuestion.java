import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class LancerQuestion {
	private StackPane root;
	private String login;
	
	public LancerQuestion(StackPane root, String login) {
		this.root = root;
		this.login = login;
		
		construireQuestion();
	}
	
	void construireQuestion() {
		// on enlève les objets de la page de connection au serveur
		 root.getChildren().clear();
		 
		 // creation d'un borderPane auquel on va ajouter les objets
		 BorderPane pane = new BorderPane();
		 
		 // définition des objets graphiques
		 // création du menu
		 Node menu = Utils.createMenu(this, root);
		 
		 // TO DO : utiliser la fonction qui sera créé
		 String[] questionReponses = {"Quelle est la capitale de la France ?", "Marseille", "Lyon", "Gardanne", "Paris"};
		 String[] reponses = { questionReponses[1], questionReponses[2], questionReponses[3], questionReponses[4] };
		 reponses = Utils.shuffleArray(reponses);
		 
		 // TO DO : supprimer après avoir tester plusieurs fois
		 for (int i = 0; i<reponses.length; i++) {
			 System.out.println(reponses[i] + "\n");
		 }
		 
		 VBox vboxQuestionReponses = new VBox();
		 vboxQuestionReponses.setAlignment(Pos.CENTER);
		 vboxQuestionReponses.setSpacing(30);
		 Text question = new Text(questionReponses[0]);
		 question.setFont(Font.font("Verdana", 25));
		 question.setTextAlignment(TextAlignment.CENTER);
		 question.setFill(Color.WHITE);
		 Button reponse1 = new Button(reponses[0]);
		 Button reponse2 = new Button(reponses[1]);
		 Button reponse3 = new Button(reponses[2]);
		 Button reponse4 = new Button(reponses[3]);
		 reponse1.getStyleClass().add("buttonStyle1");
		 reponse2.getStyleClass().add("buttonStyle1");
		 reponse3.getStyleClass().add("buttonStyle1");
		 reponse4.getStyleClass().add("buttonStyle1");
		 vboxQuestionReponses.getChildren().addAll(question, reponse1, reponse2, reponse3, reponse4);
		 
		 pane.setTop(menu);
		 pane.setCenter(vboxQuestionReponses);
		 
		 // ajout à la racine
		 root.getChildren().addAll(pane);
	}
}
