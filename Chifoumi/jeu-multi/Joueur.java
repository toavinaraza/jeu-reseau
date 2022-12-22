package jeu;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Observable;

public  class Joueur extends Observable  implements Runnable {
    public int numero;
    public int nbPoints;
    public BufferedReader in;
    public PrintStream out;
    public Choix choix;
    public Joueur adversaire;
    public boolean jouer;
    public boolean jeuFini;
    public boolean partieGagnee;
    public boolean partiePerdue;

	public Joueur() {}

	public Joueur(Socket socket) {
		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintStream(socket.getOutputStream());
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
		(new Thread(this)).start();
		adversaire = new Joueur();
	}

	public Joueur(Socket socket, int numero) {
		this(socket);
		this.numero = numero;
	}

	public void aGagne() {
		nbPoints = nbPoints + 1;
		choix = null;
		adversaire.choix = null;
		jouer = true;
	}

	public void egalite() {
		choix = null;
		adversaire.choix = null;
		jouer = true;
	}

	public void aPerdu() {
		choix = null;
		adversaire.choix = null;
		adversaire.nbPoints = adversaire.nbPoints + 1;
		jouer = true;
	}

	public boolean partieGagnee() {
		return nbPoints == Constantes.CIBLE;
	}

	public void run() {}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public int getNbPoints() {
		return nbPoints;
	}

	public void setNbPoints(int nbPoints) {
		this.nbPoints = nbPoints;
	}

	public BufferedReader getIn() {
		return in;
	}

	public void setIn(BufferedReader in) {
		this.in = in;
	}

	public PrintStream getOut() {
		return out;
	}

	public void setOut(PrintStream out) {
		this.out = out;
	}

	public Choix getChoix() {
		return choix;
	}

	public void setChoix(Choix choix) {
		this.choix = choix;
	}

	public Joueur getAdversaire() {
		return adversaire;
	}

	public void setAdversaire(Joueur adversaire) {
		this.adversaire = adversaire;
	}

	public boolean isJouer() {
		return jouer;
	}

	public void setJouer(boolean jouer) {
		this.jouer = jouer;
	}

	public boolean isJeuFini() {
		return jeuFini;
	}

	public void setJeuFini(boolean jeuFini) {
		this.jeuFini = jeuFini;
	}

	public boolean isPartieGagnee() {
		return partieGagnee;
	}

	public void setPartieGagnee(boolean partieGagnee) {
		this.partieGagnee = partieGagnee;
	}

	public boolean isPartiePerdue() {
		return partiePerdue;
	}

	public void setPartiePerdue(boolean partiePerdue) {
		this.partiePerdue = partiePerdue;
	}

	
}