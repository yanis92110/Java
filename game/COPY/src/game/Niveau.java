package game;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;
import java.util.AbstractMap;
//import java.util.Arrays;
/**
 * Cette classe <i>Niveau</i> représente un Niveau avec un tableau, des pièces, des pièges, un joueur est le statut du niveau
 * @author yanis
 * @version alpha
 */
public class Niveau {
	//Tableau représentant le niveau
	private char[][] tableau;
	//Nombre de pièces dans le niveau
	private int pieces=0;
	//Position du joueur de base
	private AbstractMap.SimpleEntry<Integer,Integer> position;
	//Joueur dans le niveau
	private Joueur joueur;
	//Etat du niveau
	private boolean estTermine=false;
	/**
	 * Constructeur qui prend en parametre un tableau de tableaux char et un Joueur
	 * @param tab '#' représente un mur, ' ' représente un vide, '.' représente une pièce, '*' représente un piège
	 * @param j Joueur dans le niveau
	 */
	/**
	 * Méthode qui renvoie la position de départ du joueur
	 * @return Tuple de int
	 */
	public AbstractMap.SimpleEntry<Integer,Integer> getPositionInitiale(){
		return this.position;
	}
	/**
	 * Méthode qui prend en parametre un tuple d'entier et qui définit la position initiale du joueur
	 * @param tuple Tuple d'entiers
	 */
	public void setPositionInitiale(AbstractMap.SimpleEntry<Integer,Integer> tuple) {
		this.position = tuple;
	}
	public Niveau(char[][] tab,Joueur j) {
		tableau = tab;
		joueur = j;
		
	}
	/**
	 * Constructeur de base qui appelle le constructeur avec un nouveau joueur par défaut.
	 * Ce constructeur crée un niveau avec un joueur initialisé par défaut.
	 */
	public Niveau() {
		this(new Joueur());
	}

	/**
	 * Constructeur qui crée un niveau (tableau 3x3) à partir d'un joueur spécifié.
	 * Ce constructeur initialise un tableau 3x3 où le joueur est placé au centre
	 * et le reste du tableau est rempli de murs ('#').
	 * 
	 * @param j0 Joueur du niveau qui sera placé dans le tableau
	 */

	public Niveau(Joueur j0) {
		//Créer un tableau 3x3
		this(new char[3][3],j0);
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				if(i==j && i==1) {
					//Met un espace au milieu
					tableau[i][j]=' ';
					continue;
				}
				//Le rempli de murs
				tableau[i][j]='#';
			}
		}
	}
	/**
	 * Constructeur qui créer un niveau à partir d'un fichier et d'un joueur, si le fichier n'existe pas afficher une erreur
	 * @param path Chemin du fichier
	 * @param joueur0 Joueur du niveau
	 */
	public Niveau(String chemin,Joueur joueur0) {
		this.joueur=joueur0;
		Path filePath = Path.of(chemin); // chemin
        try {
        	//Transforme le fichier en tableau de caracteres
            tableau= readFileToCharArray(filePath);
    		for(int i=0;i<tableau.length;i++) {
    			for(int j=0;j<tableau[i].length;j++) {
    				if(tableau[i][j]=='.') {
    					//Si on rencontre une pièce, met a jour le nombre de pieces du niveau
    					pieces++;
    				}
    				if(tableau[i][j]=='1') {
    					this.position= new AbstractMap.SimpleEntry<>(i, j);
    				}
    			}
    		}

        } catch (IOException e) {
        	//S'il y a un probleme lors de la lecture du fichier
            System.err.println("Problème lors du chargement du fichier");
            System.exit(0);
        }
		}

	/**
	 * Méthode qui lit un fichier texte, qui en créer un tableau de tableaux de caracteres et le retourne
	 * @param filePath Chemin du fichier
	 * @return Tableau de tableaux de caracteres
	 * @throws IOException Si une erreur d'entrée/sortie se produit à la lecture du fichier
	 */
	public static char[][] readFileToCharArray(Path chemin) throws IOException {
        List<String> lines = Files.readAllLines(chemin); //Dans une liste

        return lines.stream() // Conversion en tab de tab
                    .map(String::toCharArray)
                    .toArray(char[][]::new);
    }

	/**
	 * Méthode qui renvoie le nombre de pieces dans le niveau
	 * @return pieces Nombre de pièces dans le niveau
	 */
	public int getPieces() {
		return pieces;
	}
	/**
	 * Méthode qui renvoie le nombre de pièces qu'il y a initialement dans le niveau
	 * 
	 * @param lvl Tableau qui représente un niveau
	 * 
	 * @return nbpieces Nombre de pieces à la création du niveau
	 */
	public int getPiecesInit(char [][] lvl) {
		int nbpieces=0;
		for(int i=0;i<lvl.length;i++) {
			for(int j=0;j<lvl[i].length;j++) {
				if(lvl[i][j]=='.') {
					//Si on rencontre une pièce, met a jour le nombre de pieces du niveau
					nbpieces++;
				}
			}
		}
		return nbpieces;
	}

	/**
	 * Méthode qui retourne un le niveau
	 * @return tableau Niveau sous forme de char[][]
	 */
	public char[][] getTab() {
		return tableau;
	}
	/**
	 * Méthode qui retourne la taille du Niveau
	 * @return tableau.length Taille du niveau
	 */
	public int getTailleDouble() {
		return tableau.length;
	}
	/**
	 * Méthode qui retourne la taille du tableau a l'indice donné
	 * @param index La ligne du premier tableau
	 * @return tableau[index].length Taille de la ligne
	 */
	public int getTailleSimple(int indice) {
		return tableau[indice].length;
	}
	/**
	 * Méthode qui prend en parametre un tableau de char et un int index et l'ajoute à l'index du tableau de tableau
	 * @param tab Tableau
	 * @param index Indice
	 */
	public void setElementDouble(char[] tab,int indice){
		tableau[indice]=tab;
	}
	/**
	 * Méthode qui prend en parametre un caractere, et l'ajoute au tableau numéro indexDouble a l'index indexSimple
	 * @param elt Élément à ajouter
	 * @param indexSimple Position a laquelle ajouter le caractere
	 */
	public void setElementSimple(char elt, int indiceDouble, int indiceSimple) {
		tableau[indiceDouble][indiceSimple]=elt;
	}
	/**
	 * Méthode qui retourne le caractere a l'indice [indiceDouble][indiceSimple] du tableau
	 * @param indiceDouble Indice de la ligne
	 * @param indiceSimple Indice de la colonne
	 * 
	 * @return char Caractere à l'indice indiceDouble indiceSimple
	 */
	public char getEltSimple(int indiceDouble, int indiceSimple) {
		return tableau[indiceDouble][indiceSimple];
	}
	/**
	 * Méthode qui retourne la tableau a l'indice indexDouble du tableau
	 * @param indexDouble Indice du tableau
	 * @return Le tableau à l'indice indexDouble
	 */
	public char[] getEltDouble(int indiceDouble) {
		return tableau[indiceDouble];
	}
	/**
	 * Retourne une représentation textuelle du niveau sous forme de matrice.
	 * Chaque ligne de la matrice est séparée par un saut de ligne.
	 *
	 * @return Une chaîne de caractères représentant le niveau sous forme matricielle.
	 */

	@Override
	public String toString() {
	    // Utilisation d'un StringBuilder pour construire la chaîne de manière efficace
	    StringBuilder sb = new StringBuilder();

	    // Parcours des lignes de la matrice
	    for (int j = 0; j < this.getTailleDouble(); j++) {
	        
	        // Parcours des colonnes de la ligne actuelle
	        for (int i = 0; i < this.getTailleSimple(j); i++) {
	            // Ajout de l'élément courant à la représentation textuelle
	            sb.append(this.getEltSimple(j, i));
	        }
	        
	        // Ajout d'un saut de ligne après chaque ligne de la matrice
	        sb.append('\n');
	    }

	    // Retourne la chaîne construite
	    return this.joueur+"\n"+sb.toString();
	}
	/**
	 * Méthode qui cherche si un élément est dans le niveau, et si présent renvoie sa position sinon null
	 * @param elt Element a recherche
	 * @return tuple Couple de valeur entieres
	 */
	public AbstractMap.SimpleEntry<Integer,Integer> chercherElt(char elt) {
		for(int i=0;i<this.getTailleDouble();i++) {
			
			for(int j=0;j<this.getTailleSimple(i);j++) {
				if(this.getEltSimple(i, j)==elt) {
					AbstractMap.SimpleEntry<Integer, Integer> tuple = new AbstractMap.SimpleEntry<>(i, j);
					return tuple;
				}
			}
		}
		return null;
	}

	/**
	 * Override de la méthode equals pour comparer 2 niveaux.
	 * Cette méthode compare la taille et les éléments des deux tableaux qui composent les niveaux.
	 * @param obj L'objet à comparer avec le niveau actuel
	 * @return true si les deux niveaux sont égaux (taille et contenu), false sinon
	 */
	@Override
	public boolean equals(Object obj) {
	    // Vérifie si l'objet est une instance de Niveau
	    if (obj instanceof Niveau) {
	        Niveau tab = (Niveau) obj;
	        
	        // Compare les tailles des tableaux principaux
	        if (this.getTailleDouble() != tab.getTailleDouble()) {
	            return false;
	        }

	        // Compare chaque ligne du tableau
	        for (int j = 0; j < this.getTailleDouble(); j++) {
	            // Compare la taille des lignes
	            if (this.getTailleSimple(j) != tab.getTailleSimple(j)) {
	                return false;
	            }

	            // Compare les éléments de chaque ligne
	            for (int i = 0; i < this.getTailleSimple(j); i++) {
	                // Si un élément est différent, retourne false
	                if (!(this.getEltSimple(j, i) == tab.getEltSimple(j, i))) {
	                    return false;
	                }
	            }
	        }
	    }

	    // Si tous les tests sont passés, les niveaux sont considérés égaux
	    return true;
	}
	/**
	 * Méthode qui renvoie un booleen si un joueur est dans le niveau
	 * @param j Joueur à vérifier
	 * @return {@code true} si le joueur est dans le niveau, {@code false} sinon
	 */
	public boolean estDansNiveau(){
		//Si une des deux position est négative, alors renvoie faux car valeurs d'initialisation
		if(this.joueur.getx()==-1 || this.joueur.gety()==-1) {
			System.err.println("Indice non initialisé");
			return false;
		}
		//Si le joueur sort du niveau (en lignes)
		if(this.joueur.getx()>this.getTailleDouble()) {
			System.err.println("Le Joueur dépasse en ligne");
			return false;
		}
		//Si le joueur sort du niveau (en colonnes)
		for(int i=0;i<this.getTailleDouble();i++) {
			if(this.joueur.gety()>=this.getTailleSimple(i)) {
				System.err.println("Le Joueur dépasse en colonnes");
				return false;
			}
		}
		return true;
	}
	/**
	 * Methode qui renvoie vérifie si le joueur se déplace légitimement
	 * @param j Joueur à verifier
	 * @return {@code true} si le joueur rencontre un mur ou sors du niveau, {@code false} sinon
	 */
	public boolean estDeplacementLegal() {
		//Si le joueur est hors niveau
		if(!estDansNiveau()) {
			return false;
		}
		//S'il rencontre un mur
		if(this.getEltSimple(this.joueur.getx(), this.joueur.gety())=='#') {
			return false;
		}

		else {
			return true;
		}
	}
	/**
	 * Méthode qui permet de savoir si le niveau est en cours ou non
	 * 
	 * @return {@code true} si le niveau est terminé, {@code false} sinon
	 */
	public boolean niveauTermine() {
		return this.estTermine;
	}
	/**
	 * Méthode qui prend en parametre un tableau de tableau de caracteres et l'assigne en tant que niveau
	 * @param tab Tableau qui represente un niveau
	 */
	public void setTableau(char [][] tab) {
		this.tableau = tab;
	}
	/**
	* Méthode qui déplace le joueur dans le niveau, si possible
	* @param deplacement Direction selon laquelle le joueur va être déplacé
	* @param j Joueur à déplacer
	**/
	public void Deplacement(Direction deplacement) {
		//On garde les anciennes positions au cas ou le déplacement serait illégitime
		int xPrecedent=this.joueur.getx();
		int yPrecedent=this.joueur.gety();
		//Change les positions en fonction de la direction
		switch(deplacement) {
			case NORD:
				this.joueur.setPosx(this.joueur.getx()-1);
				break;
			case EST:
				this.joueur.setPosy(this.joueur.gety()+1);
				break;
			case SUD:
				this.joueur.setPosx(this.joueur.getx()+1);
				break;
			case OUEST:
				this.joueur.setPosy(this.joueur.gety()-1);
				break;
			default:
				System.err.println("Probleme de deplacement");
		}
		//Si le joueur fait un déplacement illégitime ou Si le joueur sors du niveau
		if(!this.estDeplacementLegal() || !(this.estDansNiveau()) ) {
			System.out.println("Déplacement illégitime!");
			this.joueur.setPosx(xPrecedent);
			this.joueur.setPosy(yPrecedent);
		}
		else {
			if(this.getEltSimple(this.joueur.getx(), this.joueur.gety())=='.') {
				//On rencontre une piece, on diminue le nombre de pieces dans le niveau et ajoute 100 de score au joueur
				pieces--;
				this.joueur.ajouterScore(100);
			}
			else if(this.getEltSimple(this.joueur.getx(), this.joueur.gety())=='*') {
				
			
				//On rencontre un piege, -1 vie au joueur
				this.joueur.setVies(this.joueur.getVies()-1);
				System.out.println("Attention, vous avez été piégé !");
				System.out.println("-1 VIE");
				this.setElementSimple('*', this.joueur.getx(), this.joueur.gety());
				this.joueur.setPosx(this.position.getKey()); //Clé du tuple, ici x
				this.joueur.setPosy(this.position.getValue()); // Valeur du tuple, ici y
				
				//si vies du joueur = 0 alors il a perdu
				if(this.joueur.getVies()==0) {
					Cliente.gameOver=true;
					System.out.println("GAME OVER");
					System.out.println("Voulez vous rejouer ? (Entrer oui ou non)");
					
					@SuppressWarnings("resource")
					Scanner obj = new Scanner(System.in);
			        
			        String reponse = obj.nextLine();
			        switch(reponse) {
			        // prier pour que le joueur rentre oui ou non
			        	case "non":
			        		System.out.println("Merci d'avoir joué, a bientot");
			        		System.exit(0);
			        	case "oui":
			        		
			        		//Cliente.gameOver=true;

			        		return;
			        }
				}
					
				
				
			}
			this.setElementSimple(' ', xPrecedent, yPrecedent);
			//'0' code ascii du numero 0 et on additionne le numero du joueur pour l'avoir en char
			char elt = (char) ('0'+this.joueur.getNumeroJoueur());
			this.setElementSimple(elt, this.joueur.getx(), this.joueur.gety());
			if(pieces==0) {
				System.out.println("VICTOIRE");
				System.out.println(this.joueur);
				this.estTermine=true;
				
			}
			System.out.println(this.joueur);
			}
		}
}
		
	

