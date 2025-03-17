package game;
import java.awt.event.*;
import javax.swing.JFrame;

/**
 * Cette classe <i> TouchesDirectionnelles </i>gère les saisies clavier nécessaires pour déplacer le joueur
 * @version alpha
 * @author yanis
 **/
public class TouchesDirectionnelles extends JFrame implements KeyListener {
		private static final long serialVersionUID = 1L; //Pour éviter un Warning
		private Joueur j;
		private Niveau lvl;
		/**
		 * Constructeur qui prend en paramètre un Joueur et un Niveau, qui lance une fenetre pour récupérer les saisies utilisateur
		 * @param Joueur joueur
		 * @param Niveau niveau
		 **/
	    public TouchesDirectionnelles(Joueur joueur, Niveau niveau) {
	    	this.j=joueur;
	    	this.lvl=niveau;
	    	//Titre de la fenetre
	        setTitle("Minecraft");
	        //Taille de la fenetre
	        setSize(400, 300);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setFocusable(true);  // Important pour capturer les touches
	        addKeyListener(this);  // Ajout du KeyListener
	        setVisible(true);//Voir la fenetre
	        //Pour éviter que le main ne se lance directement
	        while (this.isVisible()) {
	            try {
	                Thread.sleep(100); // Petite pause pour éviter de surcharger le CPU
	            } catch (InterruptedException e) {
	            	//Si le thread est interrompu
	                e.printStackTrace();
	            }
	        }

	    }
		/**
		 * Réécriture de la méthode keyTyped pour déplacer le joueur quand une touche est pressée (ZQSD), ou fermer la fenetre avec PBEKey
		 * @param KeyEvent e
		 **/
	    @Override
	    public void keyTyped(KeyEvent e) {
	        // Détecte si une touche alphanumérique est tapée
	        System.out.println("Touche tapée : " + e.getKeyChar());
	        System.out.println(e.getKeyChar());
	        // Tests pour Monde 3 Niveau 3
	        boolean a=true;
	        //Déplacement en fonction de la touche tapée (ZQSD)  P Pour quitter
	        switch(e.getKeyChar()) {
	        	case 'z':
	        		a=lvl.Deplacement(Direction.NORD, j);
	        		break;
	        	case 'q':
	        		a=lvl.Deplacement(Direction.OUEST, j);
	        		break;
	        	case 's':
	        		a=lvl.Deplacement(Direction.SUD, j);
	        		break;
	        	case 'd':
	        		a=lvl.Deplacement(Direction.EST, j);
	        		break;
	        	case 'p':
	        		System.out.println("Ciao !");
	        		dispose();
	        	default:
	        		//TODO
	        }
	        if(!a) {
	        	dispose();
	        }
	        System.out.println(lvl);
	    }
		//Nécessaire au bon fonctionnement
	    @Override
	    public void keyPressed(KeyEvent e) {
	        // Détecte quand une touche est enfoncée

	    }
		//Nécessaire au bon fonctionnement
	    @Override
	    public void keyReleased(KeyEvent e) {
	        // Détecte quand une touche est relâchée

	    }
}
