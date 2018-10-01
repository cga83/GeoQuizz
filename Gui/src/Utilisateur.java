
public class Utilisateur  {
	private String nom;
	private String mdp;
	private int[] scores = new int[10];
	private int scoretot = 0;
	
	Utilisateur (String name, String pw) {
		this.nom = name;
		this.mdp = pw ;
	}
	void setName(String name) {
		this.nom = name;
	}
	String getName() {
		return this.nom;
	}
	
	void setMdp(String pw ) {
		this.mdp = pw;
	}
	
	String getMdp() {
		return this.mdp;
	}
	
	void setScores(int[] scores) {
		for(int i = 0 ; i<10 ; i++) {
			this.scores[i] = scores[i];
		}
	}
	
	int[] getScores() {
		return this.scores;
	}
	
	void setScoreTot(int score) {
		this.scoretot = score;
	}
	
	int getScoreTot() {
		return this.scoretot;
	}

}
