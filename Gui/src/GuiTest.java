import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class GuiTest  extends JPanel implements ActionListener{

	private JLabel server;
	private JLabel port;
	private JButton connect;
	private JTextField serveurF = new JTextField("localhost",20);
	private JTextField portF = new JTextField("2000",20);
	private Joueur joueur;

	GuiTest(Joueur joueur) {
		this.joueur = joueur;
		setLayout(new BorderLayout());
		JPanel panelNorth = new JPanel();
	
		//Nord
		
		server = new JLabel("serveur:");
		panelNorth.add(server);
		panelNorth.add(serveurF);
		port = new JLabel("port:");
		panelNorth.add(port);
		panelNorth.add(portF);	
		
		//bouton connect
		connect = new JButton("Connect");
		connect.addActionListener(this);
		panelNorth.add(connect);
		
		add(panelNorth, BorderLayout.NORTH);
		
	}



	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == connect) {
			joueur.connectAuServeur(serveurF.getText(), Integer.parseInt(portF.getText()));
		} 
	}
	
}
