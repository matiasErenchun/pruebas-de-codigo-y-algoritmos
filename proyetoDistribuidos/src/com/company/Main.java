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
		try {
			boolean salir = false;
			Menu menu = new Menu();
			Scanner scanner = new Scanner(System.in);
			menu.entrada();
			do {
				menu.opciones();
				System.out.print("Opción: ");
				int opcion = scanner.nextInt();
				System.out.println("\n");
				switch (opcion) {
					case 1:
						System.out.println("Has seleccionado la opcion dilatación secuencial");
						break;
					case 2:
						System.out.println("Has seleccionado la opcion dilatación paralela");
						break;
					case 3:
						System.out.println("Has seleccionado la opcion erosión secuencial");
						break;
					case 4:
						System.out.println("Has seleccionado la opcion erosión paralela");
						break;
					case 5:
						salir = true;
						break;
					default:
						System.out.println("Solo números entre 1 y 5");
				}
				int filtro = scanner.nextInt();
				switch (filtro) {
					case 1:
						System.out.println("Has seleccionado la opcion dilatación secuencial");
						break;
					case 2:
						System.out.println("Has seleccionado la opcion dilatación paralela");
						break;
					case 3:
						System.out.println("Has seleccionado la opcion erosión secuencial");
						break;
					case 4:
						System.out.println("Has seleccionado la opcion erosión paralela");
						break;
					case 5:
						System.out.println("Has seleccionado la opcion erosión paralela");
						break;
					case 6:
						salir = true;
						break;
					default:
						System.out.println("Solo números entre 1 y 5");
				}
			} while (!salir);
			LeerImagen leer = new LeerImagen();
			leer.matrizImagen(
					"C:\\Users\\felip\\Documents\\GitHub\\pruebas-de-codigo-y-algoritmos\\proyetoDistribuidos\\src\\com\\company\\pruebafinalfinal.pgm");
			// ImageIO.write(img,"png",new File("C:/lawea.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
