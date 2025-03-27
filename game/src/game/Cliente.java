package game;
//import java.nio.file.Paths;
import java.io.IOException;
import java.util.AbstractMap;
import java.util.Scanner;


public class Cliente {
	public static boolean continuer=true;
	public static boolean gameOver=false;

	public static void main(String[] args) throws IOException  {
		if(args.length==0) {
			System.err.println("Arguments non reçus !");
		}
        @SuppressWarnings("resource")
		Scanner obj = new Scanner(System.in);
        System.out.println("Entrer votre pseudo: ");
        
        String pseudo = obj.nextLine();
        
        Joueur j0= new Joueur(pseudo);
		int j=0;
		Niveau niveau1 = new Niveau(j0);
		Jeu jeu = new Jeu(args,j0);
		//le jeu est créer, on appelle mtn partie pour jouer
		jeu.partie();
		

        
		while(continuer) {
			//Si gameOver c'est qu'on reste dans le meme niveau, (impossible au premier tour)
			if(gameOver) {
				
				//On reprend le niveau actuel
				System.out.println("On recommence !");
				continuer=true;
				gameOver=false;
				//Réinitialisation de niveau
				niveau1 = new Niveau(args[j],j0); //On recrée le niveau a partir du fichier, les pieces sont MAJ et la position initiale aussi
				AbstractMap.SimpleEntry<Integer, Integer> positionInitiale = niveau1.chercherElt('1');
				niveau1.setPositionInitiale(positionInitiale); //Normalement c inutile
				//char [][] temp=Niveau.readFileToCharArray(path);
				positionInitiale = niveau1.getPositionInitiale();
				//Réinitialisation de joueur
				j0.setPosx(positionInitiale.getKey());
				j0.setPosy(positionInitiale.getValue());
        		j0.setVies(3);
        		

				//va falloire faire un setter
				j0.ajouterScore(-j0.getScore()); //Score remis à 0
				try {
				    Thread.sleep(2000); // Pause de 2000 millisecondes (2 secondes)
				} catch (InterruptedException e) {
				    e.printStackTrace();
				    
				}
				//On recreer une fenetre de jeu
				System.out.println("Nombre de pieces apres un gameOver: "+niveau1.getPieces());
				System.out.println(niveau1);
				new TouchesDirectionnelles(j0,niveau1);
				
				
			}
			//On entre dans le else si on a fini le niveau
			else {
				if(args.length>0) {
		        	if(j>=args.length) {
						System.exit(-1);
					}
		        	System.out.println("Arguments reçus");
					
		        	niveau1 = new Niveau(args[j],j0);
		        }
		        else {
		        	System.err.println("Arguments non reçus !");
		        }
				//On cherche ou se situe le joueur
		        AbstractMap.SimpleEntry<Integer, Integer> positionInitiale = niveau1.chercherElt('1');
		        //Si joueur pas trouvé
		        if(positionInitiale==null) {
		        	System.err.println("Le joueur n'a pas été trouvé dans le niveau ! \n Merci de placer un '1' dans votre fichier niveau");
		        	System.exit(5);
		        }
		        //c'est logique
		        niveau1.setPositionInitiale(positionInitiale);
		        //Initialisation du joueur
		        
		        j0.setPosx(positionInitiale.getKey()); //Clé du tuple, ici la premiere coordonnée
		        j0.setPosy(positionInitiale.getValue()); //Valeur du tuple, 2e coordonnée
		        //Lancement de la fenetre
				System.out.println(niveau1);
				new TouchesDirectionnelles(j0,niveau1);
				//A partir d'ici SOIT:
				//- VICTOIRE alors on entre pas dans la boucle
				//- GAME OVER alors on entre dans la boucle
				//- Fermeture de la fenetre et donc arret du code
				if(!gameOver) {
					j++;
					System.out.println("Niveau fini !");
					
				}
				
				
				
			
			
			
	        }
			}
		System.out.println("Merci d'avoir joué a bientot !");
			
	}
	}
        
