package ue_02_labyrinth;
//@author Clemens Hodina

import java.util.ArrayList;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

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


	/**
	 * Methode um die Anzahl der möglichen Wege durch ein Labyrinth zu finden
	 *
	 * @param zeile     aktuelle Position
	 * @param spalte    aktuelle Position
	 * @param lab		labyrinth in dem gesucht wird
	 * @param counter	anzahl der möglichkeiten
	 * @return			Anzahl der Möglichkeiten
	 */
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


	/**
	 * liest ein Labyrinth aus einem Textfile aus
	 *
	 * @param filePath 	Dateipfad des labyrinths
	 * @return			laybrinth als String Array
	 */
	public static String[] read_lab(String filePath){
		List<String> lines = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String line;
			while ((line = br.readLine()) != null) {
				lines.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		String[] erg = new String[lines.size()];
		for (int i = 0; i < lines.size(); i++) {
			erg[i] = lines.get(i);
		}
		return erg;
	}


	public static void main(String[] args) throws InterruptedException {
		//char[][] labyrinth = fromStrings(maps[0]);
		//printLabyrinth(labyrinth);
//		//System.out.println("Ausgang gefunden: " + (suchen(5, 5, labyrinth) ? "ja" : "nein"));
		//System.out.println("Anzahl Wege: " + suchenAlle(5, 5, labyrinth, 0));
		//labyrinth = fromStrings(maps[1]);
		//printLabyrinth(labyrinth);
		//System.out.println("Anzahl Wege: " + suchenAlle(5, 5, labyrinth, 0));
		//labyrinth = fromStrings(maps[2]);
		//printLabyrinth(labyrinth);
		//System.out.println("Anzahl Wege: " + suchenAlle(5, 5, labyrinth, 0));
		//labyrinth = fromStrings(maps[3]);
		//printLabyrinth(labyrinth);
		//System.out.println("Anzahl Wege: " + suchenAlle(1, 1, labyrinth, 0));
		//System.out.println("-------------------------------------------");
		char[][] lab1 = fromStrings(read_lab("C:\\Schule\\5_Klasse\\SEW\\Java\\src\\ue_02_labyrinth\\l1.txt"));
		char[][] lab2 = fromStrings(read_lab("C:\\Schule\\5_Klasse\\SEW\\Java\\src\\ue_02_labyrinth\\l2.txt"));
		char[][] lab3 = fromStrings(read_lab("C:\\Schule\\5_Klasse\\SEW\\Java\\src\\ue_02_labyrinth\\l3.txt"));
		long startTime = System.currentTimeMillis();
		System.out.println(suchenAlle(1, 1, lab1, 0));
		System.out.println(suchenAlle(1, 1, lab2, 0));
		System.out.println(suchenAlle(1, 1, lab3, 0));
		long endTime = System.currentTimeMillis();
		System.out.println("Zeit: " + (endTime - startTime));
		//printLabyrinth(labyrinth);
	}
}
