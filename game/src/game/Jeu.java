package game;
import java.util.AbstractMap;
/**
 * Classe Jeu qui représente le jeu, comportant une liste de tous les niveaux et un joueur
 */
public class Jeu {
	private Niveau[] liste_niveaux;
	private Joueur joueur;
	private int indiceNiveauActuel;
	
	/**
	 * Constructeur qui prend en paramètres un tableau de chaine de caracteres (les chemins vers les niveaux) et un joueur
	 * @param args Tableau de chaine de caracteres, ou chaque element correspond au chemin d'un niveau
	 * @param j Joueur
	 */
	public Jeu(String[] args,Joueur j) {
		Niveau[] liste_niveaux = new Niveau[args.length]; // Tableau de args.lenght objets de type Niveau

		for(int i=0;i<args.length;i++) {
			
			liste_niveaux[i]= new Niveau(args[i],j);
		}
		this.liste_niveaux = liste_niveaux;
		this.joueur=j;
		this.indiceNiveauActuel=0;
	}
	/**
	 * Constructeur qui génère un niveau de base avec un joueur passé en parametres
	 * @param Joueur j
	 */
	public Jeu(Joueur j){
		this.liste_niveaux[0] = new Niveau(j);
		this.joueur=j;
	}
	/**
	 * Méthode qui renvoie l'indice du niveau actuel (Le N° du niveau)
	 * @return indiceNiveauActuel
	 */
	public int getIndiceNiveauActuel(){
		return this.indiceNiveauActuel;
	}
	/**
	 * Méthode qui renvoie la liste de tous les niveaux d'une partie
	 * @return listeNiveaux
	 */
	public Niveau[] getListeNiveaux() {
		return this.liste_niveaux;
	}
	/**
	 * Méthode qui renvoie le niveau N° indice
	 * @param indice Numéro de niveau
	 * @return liste_niveaux[indice] Niveau N°indice
	 */
	public Niveau getNiveau(int indice) {
		if(indice<this.liste_niveaux.length){
			return liste_niveaux[indice];
		}
		else {
			return null;
		}
	}
	/**
	 * Méthode qui renvoie le joueur du jeu
	 * @return joueur
	 */
	public Joueur getJoueur() {
		return this.joueur;
	}
	/**
	 * Méthode qui met à jour le joueur du jeu
	 * @param j Joueur dans le jeu
	 */
	public void setJoueur(Joueur j) {
		this.joueur=j;
	}
	/**
	 * Méthode qui met à jour la liste des niveaux du jeu
	 * @param liste Liste des niveaux
	 */
	public void setListeNiveaux(Niveau[] liste) {
		this.liste_niveaux = liste;
	}
	/**
	 * Méthode qui met à jour le niveau n°indice
	 * @param indice Indice du niveau à mettre a jour
	 * @param niveau Nouveau niveau
	 */
	public void setNiveau(int indice,Niveau niveau) {
		this.liste_niveaux[indice]=niveau;
	}
	public void partie(){
		boolean gameOver=false;
		boolean continuer=true;
		Niveau niveauActuel;
		try {
			niveauActuel = this.liste_niveaux[this.indiceNiveauActuel];
		} catch (IndexOutOfBoundsException e) {
			niveauActuel= new Niveau(this.joueur);
			System.out.println("Erreur : Indice du niveau actuel hors limites.");
			return;
		}

		while (continuer) {
			
			if(gameOver){
				//TODO
			}
			else{
				//On entre dans le else si on a fini un niveau ou au premier niveau
				if(this.indiceNiveauActuel>=this.liste_niveaux.length){
					System.out.println("Vous avez fini tous les niveaux ! A la prochaine !");
					try {
						Thread.sleep(1000); // Pause for 1 second
					} catch (InterruptedException e) {
						Thread.currentThread().interrupt(); // Restore interrupted status
						System.out.println("Thread was interrupted");
					}
					System.exit(20);
					
				}
				niveauActuel = this.liste_niveaux[this.indiceNiveauActuel];
				//this.indiceNiveauActuel++;
				AbstractMap.SimpleEntry<Integer, Integer> positionInitiale = niveauActuel.chercherElt('1');
				if(positionInitiale==null){
					System.err.println("Le joueur n'a pas été trouvé dans le niveau");
					System.exit(5);
				}
				
				niveauActuel.setPositionInitiale(positionInitiale);
				//Initialisation du joueur (tuple)
				this.joueur.setPosx(positionInitiale.getKey());
				this.joueur.setPosy(positionInitiale.getValue());
				System.out.println(niveauActuel);
				new TouchesDirectionnelles()
			}
		}
	}
}
