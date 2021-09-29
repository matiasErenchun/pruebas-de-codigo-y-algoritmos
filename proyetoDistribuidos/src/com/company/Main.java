package com.company;

import java.io.IOException;
import java.util.Scanner;

public class Main {

	int[][] matriz_pixeles;

	public static void main(String[] args) throws IOException {
		try {
			// Recibir la url por consola y el nombre del archivo? Analizar e implementar.
			String url = "C:\\Users\\vichoste\\source\\repos\\pruebas-de-codigo-y-algoritmos\\proyetoDistribuidos\\src\\com\\company\\prueba4.pgm";
			LeerImagen leer = new LeerImagen();
			boolean salir = false;
			Menu menu = new Menu();
			menu.entrada();
			Scanner opcionMenuScanner = new Scanner(System.in);
			Scanner opcionSubMenuScanner = new Scanner(System.in);
			do {
				menu.opciones();
				System.out.print("Opción: ");
				int opcionMenu = opcionMenuScanner.nextInt();
				int opcionSubMenu;
				System.out.println("\n");
				switch (opcionMenu) {
					case 1:
						menu.subOpciones();
						System.out.print("Opción: ");
						opcionSubMenuScanner = new Scanner(System.in);
						opcionSubMenu = opcionSubMenuScanner.nextInt();
						System.out.println("\n");
						switch (opcionSubMenu) {
							case 1:
								leer.matrizImagen(url, opcionMenu, opcionSubMenu);
								break;
							case 2:
								leer.matrizImagen(url, opcionMenu, opcionSubMenu);
								break;
							case 3:
								leer.matrizImagen(url, opcionMenu, opcionSubMenu);
								break;
							case 4:
								leer.matrizImagen(url, opcionMenu, opcionSubMenu);
								break;
							case 5:
								leer.matrizImagen(url, opcionMenu, opcionSubMenu);
								break;
							case 6:
								leer.matrizImagen(url, opcionMenu, opcionSubMenu);
								break;
							default:
								break;
						}
						break;
					case 2:
						menu.subOpciones();
						System.out.print("Opción: ");
						opcionSubMenuScanner = new Scanner(System.in);
						opcionSubMenu = opcionSubMenuScanner.nextInt();
						System.out.println("\n");
						switch (opcionSubMenu) {
							case 1:
								leer.matrizImagen(url, opcionMenu, opcionSubMenu);
								break;
							case 2:
								leer.matrizImagen(url, opcionMenu, opcionSubMenu);
								break;
							case 3:
								leer.matrizImagen(url, opcionMenu, opcionSubMenu);
								break;
							case 4:
								leer.matrizImagen(url, opcionMenu, opcionSubMenu);
								break;
							case 5:
								leer.matrizImagen(url, opcionMenu, opcionSubMenu);
								break;
							case 6:
								leer.matrizImagen(url, opcionMenu, opcionSubMenu);
								break;
							default:
								break;
						}
						break;
					case 3:
						menu.subOpciones();
						System.out.print("Opción: ");
						opcionSubMenuScanner = new Scanner(System.in);
						opcionSubMenu = opcionSubMenuScanner.nextInt();
						System.out.println("\n");
						switch (opcionSubMenu) {
							case 1:
								leer.matrizImagen(url, opcionMenu, opcionSubMenu);
								break;
							case 2:
								leer.matrizImagen(url, opcionMenu, opcionSubMenu);
								break;
							case 3:
								leer.matrizImagen(url, opcionMenu, opcionSubMenu);
								break;
							case 4:
								leer.matrizImagen(url, opcionMenu, opcionSubMenu);
								break;
							case 5:
								leer.matrizImagen(url, opcionMenu, opcionSubMenu);
								break;
							case 6:
								leer.matrizImagen(url, opcionMenu, opcionSubMenu);
								break;
							default:
								break;
						}
						break;
					case 4:
						menu.subOpciones();
						System.out.print("Opción: ");
						opcionSubMenuScanner = new Scanner(System.in);
						opcionSubMenu = opcionSubMenuScanner.nextInt();
						System.out.println("\n");
						switch (opcionSubMenu) {
							case 1:
								leer.matrizImagen(url, opcionMenu, opcionSubMenu);
								break;
							case 2:
								leer.matrizImagen(url, opcionMenu, opcionSubMenu);
								break;
							case 3:
								leer.matrizImagen(url, opcionMenu, opcionSubMenu);
								break;
							case 4:
								leer.matrizImagen(url, opcionMenu, opcionSubMenu);
								break;
							case 5:
								leer.matrizImagen(url, opcionMenu, opcionSubMenu);
								break;
							case 6:
								leer.matrizImagen(url, opcionMenu, opcionSubMenu);
								break;
							default:
								break;
						}
						break;
					case 5:
						salir = true;
						menu.dibujo();
						break;
					default:
						break;
				}
			} while (!salir);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
