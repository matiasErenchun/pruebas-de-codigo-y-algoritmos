package com.company;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class Main {

	int[][] matriz_pixeles;

	public static void main(String[] args) throws IOException {
		Scanner scanner = new Scanner(System.in);
		System.out.println(args.length);
		if (args.length >= 0) {
			try {
				// Recibir la url por consola y el nombre del archivo? Analizar e implementar.
				String url = "E:\\repoGit\\pruebas-de-codigo-y-algoritmos\\proyetoDistribuidos\\src\\com\\company\\prueba5.pgm";
				LeerImagen leer = new LeerImagen();
				int opcionMenu = 0;
				int opcionSubmenu = 0;
				boolean salir = false;
				Menu menu = new Menu();
				menu.entrada();
				do {
					menu.opciones();
					System.out.print("Opción: ");
					opcionMenu = scanner.nextInt();
					System.out.println("\n");
					switch (opcionMenu) {
						case 1:
							System.out.println("Has seleccionado la opcion dilatación secuencial");
							menu.subOpciones();
							System.out.print("Opcion: ");
							opcionSubmenu = scanner.nextInt();
							switch (opcionSubmenu) {
								case 1:
									System.out.println("Has seleccionado la opcion de Filtro X");
									leer.matrizImagen(url, opcionMenu, opcionSubmenu);
									break;
								case 2:
									System.out.println("Has seleccionado la opcion de Filtro +");
									leer.matrizImagen(url, opcionMenu, opcionSubmenu);
									break;
								case 3:
									System.out.println("Has seleccionado la opcion de Filtro Pilar");
									leer.matrizImagen(url, opcionMenu, opcionSubmenu);
									break;
								case 4:
									System.out.println("Has seleccionado la opcion de Filtro Horizontal");
									leer.matrizImagen(url, opcionMenu, opcionSubmenu);
								case 5:
									System.out.println("Has seleccionado la opcion de Filtro Esquina Inferior");
									leer.matrizImagen(url, opcionMenu, opcionSubmenu);
									break;
								case 6:
									System.out.println("Has seleccionado la opcion de Filtro Esquina Superior");
									leer.matrizImagen(url, opcionMenu, opcionSubmenu);
									break;
								default:
									System.out.println("Solo números entre 1 y 6");
							}
							break;
						case 2:
							System.out.println("Has seleccionado la opcion dilatación paralela");
							menu.subOpciones();
							opcionSubmenu = scanner.nextInt();
							switch (opcionSubmenu) {
								case 1:
									System.out.println("Has seleccionado la opcion de Filtro X");
									leer.matrizImagen(url, opcionMenu, opcionSubmenu);
									break;
								case 2:
									System.out.println("Has seleccionado la opcion de Filtro +");
									leer.matrizImagen(url, opcionMenu, opcionSubmenu);
									break;
								case 3:
									System.out.println("Has seleccionado la opcion de Filtro Pilar");
									leer.matrizImagen(url, opcionMenu, opcionSubmenu);
									break;
								case 4:
									System.out.println("Has seleccionado la opcion de Filtro Horizontal");
									leer.matrizImagen(url, opcionMenu, opcionSubmenu);
								case 5:
									System.out.println("Has seleccionado la opcion de Filtro Esquina Inferior");
									leer.matrizImagen(url, opcionMenu, opcionSubmenu);
									break;
								case 6:
									System.out.println("Has seleccionado la opcion de Filtro Esquina Superior");
									leer.matrizImagen(url, opcionMenu, opcionSubmenu);
									break;
								default:
									System.out.println("Solo números entre 1 y 6");
							}
							break;
						case 3:
							System.out.println("Has seleccionado la opcion erosión secuencial");
							menu.subOpciones();
							opcionSubmenu = scanner.nextInt();
							switch (opcionSubmenu) {
								case 1:
									System.out.println("Has seleccionado la opcion de Filtro X");
									leer.matrizImagen(url, opcionMenu, opcionSubmenu);
									break;
								case 2:
									System.out.println("Has seleccionado la opcion de Filtro +");
									leer.matrizImagen(url, opcionMenu, opcionSubmenu);
									break;
								case 3:
									System.out.println("Has seleccionado la opcion de Filtro Pilar");
									leer.matrizImagen(url, opcionMenu, opcionSubmenu);
									break;
								case 4:
									System.out.println("Has seleccionado la opcion de Filtro Horizontal");
									leer.matrizImagen(url, opcionMenu, opcionSubmenu);
								case 5:
									System.out.println("Has seleccionado la opcion de Filtro Esquina Inferior");
									leer.matrizImagen(url, opcionMenu, opcionSubmenu);
									break;
								case 6:
									System.out.println("Has seleccionado la opcion de Filtro Esquina Superior");
									leer.matrizImagen(url, opcionMenu, opcionSubmenu);
									break;
								default:
									System.out.println("Solo números entre 1 y 6");
							}
							break;
						case 4:
							System.out.println("Has seleccionado la opcion erosión paralela");
							menu.subOpciones();
							opcionSubmenu = scanner.nextInt();
							switch (opcionSubmenu) {
								case 1:
									System.out.println("Has seleccionado la opcion de Filtro X");
									leer.matrizImagen(url, opcionMenu, opcionSubmenu);
									break;
								case 2:
									System.out.println("Has seleccionado la opcion de Filtro +");
									leer.matrizImagen(url, opcionMenu, opcionSubmenu);
									break;
								case 3:
									System.out.println("Has seleccionado la opcion de Filtro Pilar");
									leer.matrizImagen(url, opcionMenu, opcionSubmenu);
									break;
								case 4:
									System.out.println("Has seleccionado la opcion de Filtro Horizontal");
									leer.matrizImagen(url, opcionMenu, opcionSubmenu);
								case 5:
									System.out.println("Has seleccionado la opcion de Filtro Esquina Inferior");
									leer.matrizImagen(url, opcionMenu, opcionSubmenu);
									break;
								case 6:
									System.out.println("Has seleccionado la opcion de Filtro Esquina Superior");
									leer.matrizImagen(url, opcionMenu, opcionSubmenu);
									break;
								default:
									System.out.println("Solo números entre 1 y 6");
							}
							break;
						case 5:
							salir = true;
							menu.dibujo();
							break;
						default:
							System.out.println("Solo números entre 1 y 5");
					}
				} while (!salir);
				scanner.close();
			} catch (Exception e) {
				e.printStackTrace();
				scanner.close();
			}
		}
	}

}
