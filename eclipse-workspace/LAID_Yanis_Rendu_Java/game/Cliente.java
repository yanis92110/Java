package game;
import java.nio.file.Paths;
import java.util.Scanner;
import java.io.IOException;
import java.nio.file.Path;


public class Cliente {
	public static boolean continuer=true;

	public static void main(String[] args)  {
		if(args.length==0) {
			System.err.println("Arguments non reçus !");
		}
        @SuppressWarnings("resource")
		Scanner obj = new Scanner(System.in);
        System.out.println("Entrer votre pseudo: ");
        String pseudo = obj.nextLine();
		int j=0;
		while(continuer) {
			Niveau niveau1 = new Niveau();

	        if(args.length>0) {
	        	if(j>=args.length) {
					break;
				}
	        	System.out.println("Arguments reçus");
				
	        	niveau1 = new Niveau(args[j]);
	        }
	        else {
	        	System.err.println("Arguments non reçus !");
	        }


	        Joueur j0= new Joueur(pseudo,1,1);



			//Affichage
			System.out.println("Nomde de joueurs crées: "+Joueur.getCountJoueur());
			System.out.println("Liste des joueurs:");
			

			niveau1.setElementSimple('1', j0.pos1, j0.pos2);
			System.out.println(niveau1);
			System.out.println(j0);
			new TouchesDirectionnelles(j0,niveau1);
			if(!continuer) {
				System.out.println("On recommence !");
				continuer=true;
				Path path = Paths.get(args[j]);
				try {
					char [][] temp=Niveau.readFileToCharArray(path);
					niveau1.getPiecesInit(temp);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			else {
				System.out.println("Niveau fini !");
				j++;
			}
			
			try {
			    Thread.sleep(2000); // Pause de 2000 millisecondes (2 secondes)
			} catch (InterruptedException e) {
			    e.printStackTrace();
			    
			}
		}
		
		System.out.println("Merci d'avoir joué a bientot !");
        }
	}
        
