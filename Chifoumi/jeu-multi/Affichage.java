package jeu;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import client.*;


@SuppressWarnings("serial")
public class Affichage  extends Box implements ActionListener,Observer { 
	JButton feuilleM = new JButton(new ImageIcon("feuille.png"));
	JButton ciseauxM = new JButton(new ImageIcon("ciseaux.png"));
	JButton caillouM= new JButton(new ImageIcon("caillou.png"));
	JButton feuilleA = new JButton(new ImageIcon("feuille.png"));
	JButton ciseauxA = new JButton(new ImageIcon("ciseaux.png"));
	JButton caillouA= new JButton(new ImageIcon("caillou.png"));
	JLabel nombrePoints = new JLabel("0");
	JLabel action = new JLabel("attendre");
	JoueurClient joueur;
	JLabel nombrePointsAdversaire = new JLabel("0");
	Color couleurBouton;
	JButton boutonChoixAdversaire;
	JButton boutonChoisi;

	public Affichage(JoueurClient joueur) {
		super(BoxLayout.Y_AXIS);
		this.joueur = joueur;
		joueur.addObserver(this);

		JPanel panneau = new JPanel();
		panneau.add(new JLabel("Le joueur  : "));
		panneau.add(action);
		add(panneau);

		panneau = new JPanel();
		panneau.add(new JLabel("nombre de points"));
		panneau.add(nombrePoints);
		add(panneau);

		panneau = new JPanel();
		panneau.add(feuilleM);
		panneau.add(ciseauxM);
		panneau.add(caillouM);
		add(panneau);
		if (!joueur.jouer) {
			feuilleM.setEnabled(false);
			ciseauxM.setEnabled(false);
			caillouM.setEnabled(false);
		}
		else action.setText("vous pouvez jouer");
		feuilleM.setActionCommand(Choix.FEUILLE.toString());
		ciseauxM.setActionCommand(Choix.CISEAUX.toString());
		caillouM.setActionCommand(Choix.CAILLOU.toString());
		feuilleM.addActionListener(this);
		ciseauxM.addActionListener(this);
		caillouM.addActionListener(this);

		add(new JLabel("L'adversaire"));
		panneau = new JPanel();
		panneau.add(feuilleA);
		panneau.add(ciseauxA);
		panneau.add(caillouA);
		add(panneau);

		panneau = new JPanel();
		panneau.add(new JLabel("nombre de points de l'adversaire "));
		panneau.add(nombrePointsAdversaire);
		add(panneau);

		couleurBouton = feuilleM.getBackground();
	}

	public void actionPerformed(ActionEvent evt) {
		boutonChoisi = (JButton) evt.getSource();
		boutonChoisi.setBackground(Color.RED);
		joueur.choix = Choix.valueOf(boutonChoisi.getActionCommand());
		joueur.out.println(Constantes.CHOIX + " " + boutonChoisi.getActionCommand());
		action.setText("attendre");
		feuilleM.setEnabled(false);
		ciseauxM.setEnabled(false);
		caillouM.setEnabled(false);
		joueur.jouer = false;
		indiquerChoixAdversaire();
	}

	public void indiquerChoixAdversaire() {
		Choix choixAdversaire = joueur.adversaire.choix;

		if ((joueur.choix != null) &&( choixAdversaire != null)) {
			if (choixAdversaire == Choix.FEUILLE) boutonChoixAdversaire = feuilleA;
			else if (choixAdversaire == Choix.CISEAUX) boutonChoixAdversaire = ciseauxA;
			else if (choixAdversaire == Choix.CAILLOU) boutonChoixAdversaire = caillouA;
			boutonChoixAdversaire.setBackground(Color.RED);
		}
	}
	
	public void update(Observable o, Object obj) {
		nombrePoints.setText(Integer.toString(joueur.nbPoints));
		nombrePointsAdversaire.setText(Integer.toString(joueur.adversaire.nbPoints));
		indiquerChoixAdversaire();		
		if (joueur.partieGagnee) {
			action.setText("vous avez gagne");
			feuilleM.setEnabled(false);
			ciseauxM.setEnabled(false);
			caillouM.setEnabled(false);
		}
		else if (joueur.partiePerdue) {
			action.setText("vous avez perdu");
			feuilleM.setEnabled(false);
			ciseauxM.setEnabled(false);
			caillouM.setEnabled(false);
		}  
		else if (joueur.jouer) {
			if (boutonChoisi != null) boutonChoisi.setBackground(couleurBouton);
			feuilleM.setEnabled(true);
			ciseauxM.setEnabled(true);
			caillouM.setEnabled(true);
			action.setText("vous pouvez jouer");
			if (boutonChoixAdversaire != null) 
				boutonChoixAdversaire.setBackground(couleurBouton); 
		}
	}

    public JButton getFeuilleM() {
        return feuilleM;
    }

    public void setFeuilleM(JButton feuilleM) {
        this.feuilleM = feuilleM;
    }

    public JButton getCiseauxM() {
        return ciseauxM;
    }

    public void setCiseauxM(JButton ciseauxM) {
        this.ciseauxM = ciseauxM;
    }

    public JButton getCaillouM() {
        return caillouM;
    }

    public void setCaillouM(JButton caillouM) {
        this.caillouM = caillouM;
    }

    public JButton getFeuilleA() {
        return feuilleA;
    }

    public void setFeuilleA(JButton feuilleA) {
        this.feuilleA = feuilleA;
    }

    public JButton getCiseauxA() {
        return ciseauxA;
    }

    public void setCiseauxA(JButton ciseauxA) {
        this.ciseauxA = ciseauxA;
    }

    public JButton getCaillouA() {
        return caillouA;
    }

    public void setCaillouA(JButton caillouA) {
        this.caillouA = caillouA;
    }

    public JLabel getNombrePoints() {
        return nombrePoints;
    }

    public void setNombrePoints(JLabel nombrePoints) {
        this.nombrePoints = nombrePoints;
    }

    public JLabel getAction() {
        return action;
    }

    public void setAction(JLabel action) {
        this.action = action;
    }

    public JoueurClient getJoueur() {
        return joueur;
    }

    public void setJoueur(JoueurClient joueur) {
        this.joueur = joueur;
    }

    public JLabel getNombrePointsAdversaire() {
        return nombrePointsAdversaire;
    }

    public void setNombrePointsAdversaire(JLabel nombrePointsAdversaire) {
        this.nombrePointsAdversaire = nombrePointsAdversaire;
    }

    public Color getCouleurBouton() {
        return couleurBouton;
    }

    public void setCouleurBouton(Color couleurBouton) {
        this.couleurBouton = couleurBouton;
    }

    public JButton getBoutonChoixAdversaire() {
        return boutonChoixAdversaire;
    }

    public void setBoutonChoixAdversaire(JButton boutonChoixAdversaire) {
        this.boutonChoixAdversaire = boutonChoixAdversaire;
    }

    public JButton getBoutonChoisi() {
        return boutonChoisi;
    }

    public void setBoutonChoisi(JButton boutonChoisi) {
        this.boutonChoisi = boutonChoisi;
    }

    
}