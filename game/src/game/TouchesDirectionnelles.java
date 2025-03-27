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
		private Niveau niveau;
		/**
		 * Constructeur qui prend en paramètre un Joueur et un Niveau, qui lance une fenetre pour récupérer les saisies utilisateur
		 * @param Joueur joueur
		 * @param Niveau niveau
		 **/
	    public TouchesDirectionnelles(Joueur joueur, Niveau niveau) {
	    	this.niveau=niveau;
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
		public void Deplacement(Direction deplacement){
			Joueur joueur = this.niveau.getJoueur();
			
			int xPrecedent=this.niveau.getJoueur().getx();
			int yPrecedent=this.joueur.gety();
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
	        //Déplacement en fonction de la touche tapée (ZQSD)  P Pour quitter
	        switch(e.getKeyChar()) {
	        	case 'z':
	        		niveau.Deplacement(Direction.NORD);
	        		break;
	        	case 'q':
	        		niveau.Deplacement(Direction.OUEST);
	        		break;
	        	case 's':
	        		niveau.Deplacement(Direction.SUD);
	        		break;
	        	case 'd':
	        		niveau.Deplacement(Direction.EST);
	        		break;
	        	case 'p':
	        		System.out.println("Ciao !");
	        		dispose();
	        		System.exit(1);
	        	default:
	        		//TODO
	        }
	        System.out.println(niveau);
	        if(niveau.niveauTermine() || Cliente.gameOver) { //Si le niveau est fini ou si on recommence un niveau, on ferme la fenetre
	        	dispose();
	        }
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
