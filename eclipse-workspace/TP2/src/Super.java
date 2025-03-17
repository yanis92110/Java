
public class Super {
	public static String jour(int jour) {
		switch (jour) {
		case 0:
			return "Dimanche";
		case 1:
			return "Lundi";

		case 2:
			return "Mardi";

		case 3:
			return "Mercredi";

		case 4:
			return "Jeudi";

		case 5:
			return "Vendredi";

		case 6:
			return "Samedi";

		default:
			System.out.println("Entrer un chiffre entre 0 et 6 compris");
			return "Error";
		}

	}

	public static float zeller(int jour, int mois, int annee1, int annee2) {
		if (mois == 1) {
			mois = 11;
		}
		if (mois == 2) {
			mois = 12;
		}
		float res = 0;
		res = (13 * mois - 1) / 5;
		res = res + jour + annee1 / 4 + annee2 / 4 + annee1 - 2 * annee2;
		res = res % 7;
		return res;
	}
	public static float pi(int n) {
		
	}

	public static void main(String args[]) {
		for (int i = 1; i < 8; i++) {
			if (i == 7) {
				System.out.println(jour(0));
			} else {
				System.out.println(jour(i));
			}
		}

		System.out.println("Jour d'aujourd'hui:");
		System.out.println(zeller(29, 1, 20, 25));
		System.out.println("Mon anniversaire:");
		System.out.println(zeller(7, 7, 20, 04));
		System.out.println(zeller(1, 1, 20, 00));
	}
}
