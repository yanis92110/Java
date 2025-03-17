package game;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

//import java.util.Arrays;
/**
 * Cette classe <i>Niveau</i> représente un Niveau avec un tableau et des pièces
 * @author yanis
 * @version alpha
 */
public class Niveau {
	//Tableau représentant le niveau
	private char[][] tableau;
	//Nombre de pièces dans le niveau
	private static int pieces;
	/**
	 * Constructeur qui prend en parametre un tableau de tableaux char
	 * @param tab '#' représente un mur, ' ' représente un vide, '.' représente une pièce, '*' représente un piège
	 */
	public Niveau(char[][] tab) {
		tableau = tab;
	}
	/**
	 * Constructeur de base qui créer un tableau de tableau de taille 3x3 avec au centre un vide et autour des murs
	 */
	public Niveau() {
		//Créer un tableau 3x3
		this(new char[3][3]);
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
	 * Constructeur qui créer un niveau à partir d'un fichier, s'il n'existe pas afficher une erreur
	 * @param path Chemin du fichier
	 */
	public Niveau(String path) {
		Path filePath = Path.of(path); // chemin
        try {
        	//Transforme le fichier en tableau de caracteres
            tableau= readFileToCharArray(filePath);
    		for(int i=0;i<tableau.length;i++) {
    			for(int j=0;j<tableau[i].length;j++) {
    				if(tableau[i][j]=='.') {
    					//Si on rencontre une pièce, met a jour le nombre de pieces du niveau
    					pieces++;
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
	public static char[][] readFileToCharArray(Path filePath) throws IOException {
        List<String> lines = Files.readAllLines(filePath); //Dans une liste

        return lines.stream() // Conversion en tab de tab
                    .map(String::toCharArray)
                    .toArray(char[][]::new);
    }
	/**
	 * Méthode qui renvoie le nombre de pieces dans le niveau
	 * @return pieces Nombre de pièces dans le niveau
	 */
	public static int getPieces() {
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
	public int getSizeDouble() {
		return tableau.length;
	}
	/**
	 * Méthode qui retourne la taille du tableau a l'indice donné
	 * @param index La ligne du premier tableau
	 * @return tableau[index].length Taille de la ligne
	 */
	public int getSizeSimple(int index) {
		return tableau[index].length;
	}
	/**
	 * Méthode qui prend en parametre un tableau de char et un int index et l'ajoute à l'index du tableau de tableau
	 * @param tab Tableau
	 * @param index Indice
	 */
	public void setElementDouble(char[] tab,int index){
		tableau[index]=tab;
	}
	/**
	 * Méthode qui prend en parametre un caractere, et l'ajoute au tableau numéro indexDouble a l'index indexSimple
	 * @param elt Élément à ajouter
	 * @param indexSimple Position a laquelle ajouter le caractere
	 */
	public void setElementSimple(char elt, int indexDouble, int indexSimple) {
		tableau[indexDouble][indexSimple]=elt;
	}
	/**
	 * Méthode qui retourne le caractere a l'indice [indexDouble][indexSimple] du tableau
	 * @param indexDouble
	 * @param indexSimple
	 * @return char
	 */
	public char getEltSimple(int indexDouble, int indexSimple) {
		return tableau[indexDouble][indexSimple];
	}
	/**
	 * Méthode qui retourne la tableau a l'indice indexDouble du tableau
	 * @param indexDouble Indice du tableau
	 * @return Le tableau à l'indice indexDouble
	 */
	public char[] getEltDouble(int indexDouble) {
		return tableau[indexDouble];
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
	    for (int j = 0; j < this.getSizeDouble(); j++) {
	        
	        // Parcours des colonnes de la ligne actuelle
	        for (int i = 0; i < this.getSizeSimple(j); i++) {
	            // Ajout de l'élément courant à la représentation textuelle
	            sb.append(this.getEltSimple(j, i));
	        }
	        
	        // Ajout d'un saut de ligne après chaque ligne de la matrice
	        sb.append('\n');
	    }

	    // Retourne la chaîne construite
	    return sb.toString();
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
	        if (this.getSizeDouble() != tab.getSizeDouble()) {
	            return false;
	        }

	        // Compare chaque ligne du tableau
	        for (int j = 0; j < this.getSizeDouble(); j++) {
	            // Compare la taille des lignes
	            if (this.getSizeSimple(j) != tab.getSizeSimple(j)) {
	                return false;
	            }

	            // Compare les éléments de chaque ligne
	            for (int i = 0; i < this.getSizeSimple(j); i++) {
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
	public boolean isInGrid(Joueur j){
		//Si une des deux position est négative, alors renvoie faux car valeurs d'initialisation
		if(j.pos1==-1 || j.pos2==-1) {
			System.err.println("Indice non initialisé");
			return false;
		}
		//Si le joueur sort du niveau (en lignes)
		if(j.pos1>this.getSizeDouble()) {
			System.err.println("Le Joueur dépasse en ligne");
			return false;
		}
		//Si le joueur sort du niveau (en colonnes)
		for(int i=0;i<this.getSizeDouble();i++) {
			if(j.pos2>this.getSizeSimple(i)) {
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
	public boolean isLegalMove(Joueur j) {
		//Si le joueur est hors niveau
		if(!isInGrid(j)) {
			return false;
		}
		//S'il rencontre un mur
		if(this.getEltSimple(j.pos1, j.pos2)=='#') {
			return false;
		}

		else {
			return true;
		}
	}
	/**
	* Méthode qui déplace le joueur dans le niveau, si possible
	* @param move Direction selon laquelle le joueur va être déplacé
	* @param j Joueur à déplacer
	**/
	public boolean Deplacement(Direction move, Joueur j) {
		//On garde les anciennes positions au cas ou le déplacement serait illégitime
		int previousPos1=j.pos1;
		int previousPos2=j.pos2;
		//Change les positions en fonction de la direction
		switch(move) {
			case NORD:
				j.pos1--;
				break;
			case EST:
				j.pos2++;
				break;
			case SUD:
				j.pos1++;
				break;
			case OUEST:
				j.pos2--;
				break;
			default:
				System.err.println("Probleme de deplacement");
		}
		//Si le joueur sors du niveau
		if(!(this.isInGrid(j))) {
			System.err.println("Move illégal BOUND ARRAY");
		}
		//Si le joueur fait un déplacement illégitime
		if(!this.isLegalMove(j)) {
			System.out.println("Déplacement illégitime!");
			j.pos1=previousPos1;
			j.pos2=previousPos2;
		}
		else {
			if(this.getEltSimple(j.pos1, j.pos2)=='.') {
				//On rencontre une piece, on diminue le nombre de pieces dans le niveau et ajoute 100 de score au joueur
				pieces--;
				j.addScore(100);
				System.out.println(j);
			}
			else if(this.getEltSimple(j.pos1, j.pos2)=='*') {
				//On rencontre un piege, -1 vie au joueur
				j.vies--;
				System.out.println("Attention, vous avez été piégé !");
				System.out.println("-1 VIE");
				System.out.println(j);
				//si vies du joueur = 0 alors il a perdu
				if(j.vies==0) {
					Cliente.continuer=false;
					System.out.println("GAME OVER");
					System.out.println("Voulez vous rejouer ? (Entrer oui ou non)");
					
					@SuppressWarnings("resource")
					Scanner obj = new Scanner(System.in);
			        
			        String reponse = obj.nextLine();
			        switch(reponse) {
			        	case "non":
			        		System.out.println("Merci d'avoir joué, a bientot");
			        		System.exit(0);
			        	case "oui":
			        		//TODO  Monde 3 lvl3
			        		j.setVies(3);
			        }
					return false;
					
				}
				else {
					//Sinon il reviens a la position précédente
					j.pos1=previousPos1;
					j.pos2=previousPos2;
				}
			}
			this.setElementSimple(' ', previousPos1, previousPos2);
			//'0' code ascii du numero 0 et on additionne le numero du joueur pour l'avoir en char
			char elt = (char) ('0'+j.getNumeroJoueur());
			this.setElementSimple(elt, j.pos1, j.pos2);
			if(pieces==0) {
				System.out.println("VICTOIRE");
				System.out.println(j);
				return false;
			}
		}
		return true;
	}
}
