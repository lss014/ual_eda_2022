package practica_01;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Jugadores {

	private static String ruta = System.getProperty("user.dir") + File.separator + "src" + File.separator
			+ "practica_01" + File.separator + "NbaStats.txt";

	public static void main(String[] args) {
		ArrayList<Player> arrayCargado = new ArrayList<Player>();
		cargarArchivo(ruta, arrayCargado);
		ArrayList<Player> sortedArray = new ArrayList<Player>();
		long start = System.currentTimeMillis();
		sortedArray = mergeSort(arrayCargado);
		long end = System.currentTimeMillis();
		System.out.println("\nLOS DIEZ JUGADORES CON MAYOR PUNTUACIÓN SON:\n");
		System.out.println(sortedArray.get(0).toString());
		System.out.println(sortedArray.get(1).toString());
		System.out.println(sortedArray.get(2).toString());
		System.out.println(sortedArray.get(3).toString());
		System.out.println(sortedArray.get(4).toString());
		System.out.println(sortedArray.get(5).toString());
		System.out.println(sortedArray.get(6).toString());
		System.out.println(sortedArray.get(7).toString());
		System.out.println(sortedArray.get(8).toString());
		System.out.println(sortedArray.get(9).toString());
		System.out.println("Tiempo de ejecucion: " + (end - start) + " ms");
		start = System.currentTimeMillis();
		sortedArray = mergeSort2(arrayCargado);
		end = System.currentTimeMillis();
		System.out.println("Tiempo de ejecucion con mejora: " + (end - start) + " ms");
	}

	public static void cargarArchivo(String archivo, ArrayList<Player> array) {

		Scanner sc;
		try {
			sc = new Scanner(new File(archivo));
			sc.nextLine();
			while (sc.hasNext()) {
				String linea = sc.nextLine();
				String items[];
				if (linea.isEmpty())
					continue;
				items = linea.split(";");

				// Calcular puntuacion
				double ptos, fg;
				ptos = Double.parseDouble(items[8]);
				fg = Double.parseDouble(items[7].isEmpty() ? "0" : items[7].replace(",", "."));
				fg = fg / 100;
				int score = (int) (ptos * fg);

				if (array.isEmpty()) {
					array.add(new Player(items[2], items[6], items[4], score));
					continue;
				}
				// Comprobar si ya existe el jugador en el arrayList
				if (items[2].equals(array.get(array.size() - 1).getPlayerName())) {
					Player aux = array.get(array.size() - 1);
					aux.addPosition(items[4]);
					aux.addTeam(items[6]);
					score = (int) ((score + aux.getScore()) / 2);
					aux.setScore(score);
				} else {
					array.add(new Player(items[2], items[6], items[4], score));
				}
			}
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static ArrayList<Player> mergeSort(ArrayList<Player> list) {
		ArrayList<Player> sorted;
		if (list.size() == 1) {
			sorted = list;
		} else {
			int mitad = list.size() / 2;

			ArrayList<Player> left = new ArrayList<Player>(); // primera mitad
			ArrayList<Player> right = new ArrayList<Player>(); // segunda mitad

			for (int x = 0; x < mitad; x++) {
				left.add(list.get(x));
			}
			for (int x = mitad; x < list.size(); x++) {
				right.add(list.get(x));
			}
			left = mergeSort(left);
			right = mergeSort(right);
			sorted = mergeArray(left, right);
		}
		return sorted;
	}
	
	public static ArrayList<Player> mergeSort2(ArrayList<Player> list) {
		ArrayList<Player> sorted;
		if (list.size() == 1) {
			sorted = list;
		} else {
			int mitad = list.size() / 2;

			ArrayList<Player> left = new ArrayList<Player>(); // primera mitad
			ArrayList<Player> right = new ArrayList<Player>(); // segunda mitad

			for (int x = 0; x < mitad; x++) {
				left.add(list.get(x));
			}
			for (int x = mitad; x < list.size(); x++) {
				right.add(list.get(x));
			}
			left = mergeSort2(left);
			right = mergeSort2(right);
			sorted = mergeArray2(left, right);
		}
		return sorted;
	}

	private static ArrayList<Player> mergeArray(ArrayList<Player> left, ArrayList<Player> right) {
		ArrayList<Player> merged = new ArrayList<Player>();

		int contadorMerged = 0;
		int contadorIzq = 0;
		int contadorDcha = 0;

		while (contadorIzq < left.size() && contadorDcha < right.size()) {
			if ((left.get(contadorIzq)).compareTo(right.get(contadorDcha)) < 0) {
				merged.add(left.get(contadorIzq));
				contadorIzq++;
			} else {
				merged.add(right.get(contadorDcha));
				contadorDcha++;
			}
			contadorMerged++;
		}

		while (contadorIzq < left.size()) {
			merged.add(left.get(contadorIzq));
			contadorIzq++;
			contadorMerged++;
		}

		while (contadorDcha < right.size()) {
			merged.add(right.get(contadorDcha));
			contadorDcha++;
			contadorMerged++;
		}
		
		return merged;
	}
	
	private static ArrayList<Player> mergeArray2(ArrayList<Player> left, ArrayList<Player> right) {
		ArrayList<Player> merged = new ArrayList<Player>();

		int contadorMerged = 0;
		int contadorIzq = 0;
		int contadorDcha = 0;

		while (contadorIzq < left.size() && contadorDcha < right.size()) {
			if ((left.get(contadorIzq)).compareTo(right.get(contadorDcha)) < 0) {
				merged.add(left.get(contadorIzq));
				contadorIzq++;
			} else {
				merged.add(right.get(contadorDcha));
				contadorDcha++;
			}
			contadorMerged++;
		}

		while (contadorIzq < left.size()) {
			merged.add(left.get(contadorIzq));
			contadorIzq++;
			contadorMerged++;
		}

		while (contadorDcha < right.size()) {
			merged.add(right.get(contadorDcha));
			contadorDcha++;
			contadorMerged++;
		}
		
		// Mejora
		if(merged.size() > 10) {
			merged = solucion(merged);
		}
		
		return merged;
	}
	
	public static ArrayList<Player> solucion(ArrayList<Player> jugadores) {
		ArrayList<Player> solucion = new ArrayList<Player>();
		solucion.add(jugadores.get(0));
		solucion.add(jugadores.get(1));
		solucion.add(jugadores.get(2));
		solucion.add(jugadores.get(3));
		solucion.add(jugadores.get(4));
		solucion.add(jugadores.get(5));
		solucion.add(jugadores.get(6));
		solucion.add(jugadores.get(7));
		solucion.add(jugadores.get(8));
		solucion.add(jugadores.get(9));
		return solucion;
	}

}
