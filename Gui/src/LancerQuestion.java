import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
import javafx.util.Duration;

public class LancerQuestion {
	private final static int NBQUESTION = 2;
	private StackPane root;
	//private String[] questionReponsesRound1 =  {"Quelle est la capitale de la France ?", "Marseille", "Lyon", "Gardanne", "Paris"};
	private String[] questionReponsesRound2 =  {"Département 13 ?", "Haute Corse", "Var", "Seine St Denis", "Bouches du Rhones"};
	private int round;
	
	public LancerQuestion(StackPane root, Joueur joueur, String[] questionReponses, int round) {
		this.root = root;
		this.round = round;
		construireQuestion(joueur, questionReponses);
	}
	
	void construireQuestion(Joueur joueur, String[] questionReponses) {
		// on enlève les objets de la page de connection au serveur
		 root.getChildren().clear();
		 
		 // creation d'un borderPane auquel on va ajouter les objets
		 BorderPane pane = new BorderPane();
		 
		 // définition des objets graphiques
		 // création du menu
		 Node menu = Utils.createMenu(this, root, joueur);
		 
		 // TO DO : utiliser la fonction qui sera créé
		 // si la fonction retourne { question, rep1 , rep2, rep3, bonne rep } c'est parfait sinon faudra modifier un peu
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
		 
		 String bonneReponse = questionReponses[4];
		 reponse1.setOnAction(value -> {
			 // si c'est la bonne reponse alors on l'indique en vert sinon en rouge
			 if (reponse1.getText().equals(bonneReponse)) {
			 	reponse1.setStyle("-fx-background-color: green;");
			 	// score augmente
			 }
			 else 
				 reponse1.setStyle("-fx-background-color: red;");
			 // on passe à la question suivante si on a pas fait toutes les questions
			 if (round<NBQUESTION) {
				 Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.5), a ->  new LancerQuestion(root, joueur, questionReponsesRound2, round+1)));  
				 timeline.play();
			 }
			 else 
				 new PartieFinie(root, joueur);

			 
		 });
		 Button reponse2 = new Button(reponses[1]);
		 reponse2.setOnAction(value -> {
			 // si c'est la bonne reponse alors on l'indique en vert sinon en rouge
			 if (reponse2.getText().equals(bonneReponse)) {
			 	reponse2.setStyle("-fx-background-color: green;");
			 	// score augmente
			 }
			 else 
				 reponse2.setStyle("-fx-background-color: red;");
			 // on passe à la question suivante si on a pas fait toutes les questions
			 if (round<NBQUESTION) {
				 Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.5), a ->  new LancerQuestion(root, joueur, questionReponsesRound2, round+1)));  
				 timeline.play();
			 }
			 else 
				 new PartieFinie(root, joueur);
		 });
		 Button reponse3 = new Button(reponses[2]);
		 reponse3.setOnAction(value -> {
			 // si c'est la bonne reponse alors on l'indique en vert sinon en rouge
			 if (reponse3.getText().equals(bonneReponse)) {
			 	reponse3.setStyle("-fx-background-color: green;");
			 	// score augmente
			 }
			 else 
				 reponse3.setStyle("-fx-background-color: red;");
			 // on passe à la question suivante si on a pas fait toutes les questions
			 if (round<NBQUESTION) {
				 Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.5), a ->  new LancerQuestion(root, joueur, questionReponsesRound2, round+1)));  
				 timeline.play();
			 }
			 else 
				 new PartieFinie(root, joueur);
		 });
		 Button reponse4 = new Button(reponses[3]);
		 reponse4.setOnAction(value -> {
			 // si c'est la bonne reponse alors on l'indique en vert sinon en rouge
			 if (reponse4.getText().equals(bonneReponse)) {
			 	reponse4.setStyle("-fx-background-color: green;");
			 	// score augmente
			 }
			 else 
				 reponse4.setStyle("-fx-background-color: red;");
			 // on passe à la question suivante si on a pas fait toutes les questions
			 if (round<NBQUESTION) {
				 Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.5), a ->  new LancerQuestion(root, joueur, questionReponsesRound2, round+1)));  
				 timeline.play();
			 }
			 else 
				 new PartieFinie(root, joueur);
		 });
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
