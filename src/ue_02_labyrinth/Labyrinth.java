package ue_02_labyrinth;
//@author Clemens Hodina

import java.util.Arrays;

public class Labyrinth {
	public static String[][] maps = {{
		"############",
		"#  #     # #",
		"## # ### # #",
		"#  # # # # #",
		"## ### # # #",
		"#        # #",
		"## ####### #",
		"#          #",
		"# ######## #",
		"# #   #    #",
		"#   #   # ##",
		"######A#####"
	}, {
		"################################",
		"#                              #",
		"# ############################ #",
		"# # ###       ##  #          # #",
		"# #     ##### ### # ########## #",
		"# #   ##### #     # #      ### #",
		"# # ##### #   ###   # # ## # # #",
		"# # ### # ## ######## # ##   # #",
		"# ##### #  # #   #    #    ### #",
		"# # ### ## # # # # ####### # # #",
		"# #        # #   #     #     # #",
		"# ######## # ######### # ### # #",
		"# ####     #  # #   #  # ##### #",
		"# # #### #### # # # # ## # ### #",
		"#                      # #     #",
		"###########################A####"
	}, {
		"###########################A####",
		"#   #      ## # # ###  #     # #",
		"# ###### #### # # #### ##### # #",
		"# # ###  ## # # # #          # #",
		"# # ### ### # # # # # #### # # #",
		"# #     ### # # # # # ## # # # #",
		"# # # # ### # # # # ######## # #",
		"# # # #     #          #     # #",
		"# ### ################ # # # # #",
		"# #   #             ## # #   # #",
		"# # #### ############# # #   # #",
		"# #                    #     # #",
		"# # #################### # # # #",
		"# # #### #           ###     # #",
		"# # ## # ### ### ### ### # ### #",
		"# #    #     ##  ##  # ###   # #",
		"# ####   ###### #### # ###  ## #",
		"###########################A####"
	}, {
		"#############",
		"#           #",
		"#           #",
		"#           #",
		"###########A#"
	}};

	/**
	 * Wandelt (unveränderliche) Strings in Char-Arrays
	 * @param map  der Plan, ein String je Zeile
	 * @return char[][] des Plans
	 */
	public static char[][] fromStrings(String[] map) {
		char[][] erg = new char[map.length][map[0].length()];
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length(); j++) {
				char currentChar = map[i].charAt(j);
				erg[i][j] = currentChar;
			}
		}
		return erg;
	}


	/**
	 * Ausgabe des Layrinths
	 * @param lab
	 */
	public static void printLabyrinth(char[][] lab) {
		for (int i = 0; i < lab.length; i++) {
			for (int j = 0; j < lab[i].length; j++) {
				System.out.print(lab[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}


	/**
	 * Suche den Weg
	 * @param zeile     aktuelle Position
	 * @param spalte     aktuelle Position
	 * @param lab
	 * @throws InterruptedException    für die verlangsamte Ausgabe mit sleep()
	 */
	public static boolean suchen(int zeile, int spalte, char[][] lab) throws InterruptedException {
		// nur lab[zeile][spalte] betrachten
		if (lab[zeile][spalte] == 'A'){
			return true;
		}
		lab[zeile][spalte] = 'X';
		printLabyrinth(lab);
		if (lab[zeile + 1][spalte] != '#' && lab[zeile + 1][spalte] != 'X'){
			if (suchen(zeile + 1, spalte, lab)){
				return true;
			};
		}
		if (lab[zeile][spalte + 1] != '#' && lab[zeile][spalte + 1] != 'X') {
			if (suchen(zeile, spalte + 1, lab)){
				return true;
			};
		}
		if (lab[zeile - 1][spalte] != '#' && lab[zeile - 1][spalte] != 'X') {
			if (suchen(zeile - 1, spalte, lab)){
				return true;
			};
		}
		if (lab[zeile][spalte - 1] != '#' && lab[zeile][spalte - 1] != 'X') {
			if (suchen(zeile, spalte - 1, lab)) {
				return true;
			};
		}
		return false;
	}


	public static int suchenAlle(int zeile, int spalte, char[][] lab, int counter){
		if (lab[zeile][spalte] == 'A'){
			return counter + 1;
		}
		lab[zeile][spalte] = 'X';
		//printLabyrinth(lab);
		if (lab[zeile + 1][spalte] != '#' && lab[zeile + 1][spalte] != 'X'){
			counter = suchenAlle(zeile + 1, spalte, lab, counter);
		}
		if (lab[zeile][spalte + 1] != '#' && lab[zeile][spalte + 1] != 'X') {
			counter = suchenAlle(zeile, spalte + 1, lab, counter);
		}
		if (lab[zeile - 1][spalte] != '#' && lab[zeile - 1][spalte] != 'X') {
			counter = suchenAlle(zeile - 1, spalte, lab, counter);
		}
		if (lab[zeile][spalte - 1] != '#' && lab[zeile][spalte - 1] != 'X') {
			counter = suchenAlle(zeile, spalte - 1, lab, counter);
		}
		lab[zeile][spalte] = ' ';
		return counter;
	}




	public static void main(String[] args) throws InterruptedException {
		char[][] labyrinth = fromStrings(maps[0]);
		printLabyrinth(labyrinth);
		System.out.println("Ausgang gefunden: " + (suchen(5, 5, labyrinth) ? "ja" : "nein"));
		System.out.println("Anzahl Wege: " + suchenAlle(5, 5, labyrinth, 0));
	}
}
