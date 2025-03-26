package game;
/**
 * Classe Jeu qui représente le jeu, comportant une liste de tous les niveaux et un joueur
 */
public class Jeu {
	private Niveau[] liste_niveaux;
	private Joueur joueur;
	
	/**
	 * Constructeur qui prend en paramètres un tableau de chaine de caracteres (les chemins vers les niveaux) et un joueur
	 * @param args Tableau de chaine de caracteres, ou chaque element correspond au chemin d'un niveau
	 * @param j Joueur
	 */
	public Jeu(String[] args,Joueur j) {
		Niveau[] liste_niveaux = new Niveau[args.length]; // Tableau de 5 objets de type MaClasse

		for(int i=0;i<args.length;i++) {
			
			liste_niveaux[i]= new Niveau(args[i],j);
		}
		this.liste_niveaux = liste_niveaux;
		this.joueur=j;
	}
	/**
	 * Méthode qui renvoie la liste de tous les niveaux d'une partie
	 * @return listeNiveaux
	 */
	public Niveau[] getListeNiveaux() {
		return this.liste_niveaux;
	}
	/**
	 * Méthode qui renvoie le niveau *indice*
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
}
