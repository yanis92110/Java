package game;
/**
 * Cette classe <i>Joueur</i> représente un joueur avec un nom et un score
 * @author yanis
 * @version alpha
 */
public class Joueur{
	//Nom du joueur
	private final String nom;
	//Score du joueur
	private int score;
	//Compteur global
	private static int compteurJoueur=0;
	//Numéro du joueur unique
	private final int numeroJoueur;
	//Position du joueur
	public int pos1;
	public int pos2;
	//Vies du joueur
	public int vies=3;
	/**
	 * Constructeur Joueur qui attribut un nom et une position pris en paramètres et un score à 0
	 * @param temp Nom du joueur
	 * @param x Coordonnée x
	 * @param y Coordonée y
	 */
	public Joueur(String temp,int x,int y) {
		nom=temp;
		score=0;
		pos1=x;
		pos2=y;
		compteurJoueur++;
        numeroJoueur = compteurJoueur;
	}
	/**
	 * Constructeur Joueur qui attribue le nom passé en parametre, met le score a 0 et la position a (-1,-1)
	 * @param temp Nom du joueur
	 */
	public Joueur(String temp) {
		this(temp,-1,-1);
	}
	/**
	 * Constructeur qui prends en parametres 2 int et les attribut aux coordonnees
	 * @param x Coordonnée x
	 * @param y Coordonnée y
	 */
	public Joueur(int x,int y) {
	    this("Joueur" + (compteurJoueur + 1), x, y);
	}

	/**
	 * Constructeur Joueur par defaut qui appelle le constructeur avec nom JoueurN
	 */
	public Joueur() {
		//Appelle le constructeur avec le nom JoueurN
		this("Joueur"+(compteurJoueur+1));
	}
	/**
	 * Méthode qui retourne le nombre de joueurs crées
	 * @return compteurJoueur  Renvoie le nombre de joueurs crées
	 */
	public static int getCountJoueur() {
		return compteurJoueur;
	}
	/**
	 * Méthode qui renvoie le numéro du joueur
	 * @return numeroJoueur Numéro du joueur
	 */
	public int getNumeroJoueur() {
		return numeroJoueur;
	}
	/**
	 * Méthode qui met à jour le score d'un joueur et qui verifie sa positivité
	 * @param nb Valeur à ajouter au score
	 */
	public void addScore(int nb) {

		if(score+nb>=0) {
			score=score+nb;
		}
	}
	/**
	 * Méthode qui met la position du joueur a x et y
	 * @param x Coordonnée x
	 * @param y Coordonnée y
	 */
	public void setPos(int x, int y) {
		pos1=x;
		pos2=y;
	}
	/**
	 * Méthode qui retourne la premiere coordonnée
	 * @return pos1 Coordonnée x
	 */
	public int getPos1() {
		return pos1;
	}
	/**
	 * Méthode qui retourne la deuxième coordonnée
	 * @return pos2 Coordonnée y
	 */
	public int getPos2() {
		return pos2;
	}
	/**
	 * Méthode qui renvoie le socre du joueur
	 * @return score Score du joueur
	 */
	public int getScore() {
		return score;
	}
	/**
	 * Méthode qui renvoie le nom du joueur
	 * @return nom Nom du joueur
	 */
	public String getNom() {
		return nom;
	}
	/**
	 * Méthode qui renvoie le nombre de vies restantes du joueur
	 * @return vies Vies du joueur
	 */
	public int getVies() {
		return vies;
	}
	/**
	 * Méthode qui met à jour le nombre de vies du joueur
	 * @param nb Nombre de vies du joueur
	 */
	public void setVies(int nb) {
		this.vies=nb;
	}
	
	/**
	 * Retourne une représentation textuelle du joueur sous la forme:
	 * X pt(s) (POSITION) X vie(s) 
	 *
	 * @return Une chaine de caracteres représentant le joueur avec son nom, score, position et vies restantes
	 */
	@Override
	public String toString() {
		//Accords si les variables sont au pluriel
		String var=this.getScore()>1 ? " pts" : " pt"; 
		String vie=this.getVies()>1 ? " vies" : " vie";
		return this.getNom()+" : "+this.getScore()+var+" ("+this.pos1+","+this.pos2+") "+this.getVies()+vie;
	}
	/**
	 * Compare ce joueur à un autre objet pour vérifier s'ils sont égaux
	 * Deux joueurs sont égaux s'ils ont le même nom (sans casse)
	 * 
	 * @param obj L'objet a comparer avec ce joueur
	 * 
	 * @return {@code true} Si l'objet est un joueur avec le même nom, {@code false} sinon
	 */
	@Override
	public boolean equals(Object obj) {
		//Teste si l'objet est un Joueur
		if(obj instanceof Joueur) {
			Joueur j = (Joueur) obj;
			//Si les joueurs ont le même nom alors ils sont égaux
			if(this.getNom().equalsIgnoreCase(j.getNom())) {
				return true;
			}
		}
		return false;
	}
	}


