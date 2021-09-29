package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class LeerImagen {

	private String nombreArchivo;
	private int ancho = 0;
	private int alto = 0;
	private int blancoAbsoluto;
	private int[][] pixeles;

	public LeerImagen() {
	}

	public void matrizImagen(String nombreArchivo, int opcionMenu, int opcionSubmenu) throws IOException {
		this.nombreArchivo = nombreArchivo;
		File file = new File(this.nombreArchivo);
		// System.out.println(file.length());
		final FileInputStream f = new FileInputStream(file);
		ArrayList<ArrayList<Integer>> lineas = new ArrayList<>();
		ArrayList<Integer> caja = new ArrayList<>();
		// System.out.println(f.available());
		int chrr;
		int i = 0;
		int j = 0;

		while (f.available() != 0) {
			// System.out.println("avaible: " + f.available());
			chrr = f.read();
			if (chrr != 10) {
				// System.out.print(chrr +" ");
				caja.add(chrr);
			} else {
				if (lineas.size() <= 3) {
					// System.out.println(" linea " + i );
					caja.add(chrr);
					lineas.add(caja);
					caja = new ArrayList<>();
					i++;
				} else {
					caja.add(chrr);
				}
			}
			j++;
		}

		lineas.add(caja);
		// System.out.println(lineas.size());
		// System.out.println(lineas.get(lineas.size()-1).size());
		// System.out.println( " ------ ");

		char charAux;
		// System.out.println("largo lineas" + lineas.size());
		ArrayList<Integer> numeros = new ArrayList<>();
		for (int k = 0; k < lineas.size(); k++) {
			// System.out.println(" k:"+ k);
			StringBuffer st = new StringBuffer();
			ArrayList<Integer> cajaAuxContenedor = lineas.get(k);
			// System.out.println("lagor :" + cajaAuxContenedor.size());
			if (k == 0) {
				for (int l = 0; l < cajaAuxContenedor.size(); l++) {
					int valorAuxiliar = cajaAuxContenedor.get(l);
					charAux = (char) valorAuxiliar;
					st.append(charAux);
				}
				if (!st.toString().contains("P5")) {
					System.out.println("¡No es formato P5!");
				} else {
					System.out.println(st.toString());
				}
			} else if (k == 1) {
				for (int l = 0; l < cajaAuxContenedor.size(); l++) {
					int valorAuxiliar = cajaAuxContenedor.get(l);
					charAux = (char) valorAuxiliar;
					st.append(charAux);
				}
				// System.out.println("st" + st.toString());
				StringTokenizer stken = new StringTokenizer(st.toString());
				this.ancho = Integer.parseInt(stken.nextToken());
				this.alto = Integer.parseInt(stken.nextToken());
				// System.out.println("ancho :" + this.ancho + ", alto:"+ this.alto);
			} else if (k == 2) {
				for (int l = 0; l < cajaAuxContenedor.size(); l++) {
					int valorAuxiliar = cajaAuxContenedor.get(l);
					charAux = (char) valorAuxiliar;
					// sfsdfdf
					st.append(charAux);
				}
				StringTokenizer stken = new StringTokenizer(st.toString());
				String blancoContenedor = stken.nextToken();
				this.blancoAbsoluto = Integer.parseInt(blancoContenedor);

			} else {
				// System.out.println("largo caja" + cajaAuxContenedor.size());
				for (int l = 0; l < cajaAuxContenedor.size(); l++) {
					numeros.add(cajaAuxContenedor.get(l));
				}
			}
		}
		// System.out.println("numeros" + numeros.size());
		this.pixeles = new int[this.alto][this.ancho];
		int[][] originalImage = new int[this.alto][this.ancho];

		int valorEnElArregloDeNumeros = 0;
		for (i = 0; i < this.alto; i++) {

			for (j = 0; j < this.ancho; j++) {
				int numero = originalImage[i][j] = numeros.get(valorEnElArregloDeNumeros);

				this.pixeles[i][j] = numero;
				valorEnElArregloDeNumeros++;
			}

		}

		f.close();
		filtros(opcionMenu, opcionSubmenu);

	}

	public void filtros(int opcionMenu, int opcionSubmenu) {
		int iterativo = 0; // 0 es secuencial - 1 es paralelo
		int mayorMenor = 0; // buscar el numero mayor o buscar el numero menor. 0 - 1
		int opcion = opcionSubmenu;

		if (opcionMenu == 1 || opcionMenu == 3) {
			iterativo = 0;
			if (opcionMenu == 1) {
				mayorMenor = 0; // REVISAR ES DILATACION
			} else {
				mayorMenor = 1; // REVISAR ES EROSION
			}
		} else {
			iterativo = 1;
			if (opcionMenu == 2) {
				mayorMenor = 0; // REVISAR DILATACION
			} else {
				mayorMenor = 1; // REVISAR EROSION
			}
		}
		// FALTA DECIR QUE SI OPCION MENU ES X COSA ENTONCES LOS VALORES DE ITERATIVO Y
		// MAYORMENOR CAMBIAN. ADEMAS FALTA SABER OPCION SUBMENU PARA SABER QUE FILTRO
		// USAR.

		int[][] matriz = new int[alto][ancho]; // se modifica el tamaño con el tamaño de la matriz pasada por
												// parametros.
		Contenedor micontenedor = new Contenedor(matriz);
		// int [][] matrizprueba = this.pixeles;
		Long tiempoInicio = System.nanoTime();
		if (iterativo == 0) {
			if (opcion == 1) {
				FiltradorX nuevoFiltadorX = new FiltradorX(this.pixeles, 0, 0, micontenedor, mayorMenor);
				nuevoFiltadorX.filtarIterativoX();
			} else if (opcion == 2) {
				FiltradorMas nuevoFiltrador = new FiltradorMas(this.pixeles, 0, 0, micontenedor, mayorMenor);
				nuevoFiltrador.filtarIterativoMas();
			} else if (opcion == 3) {
				FiltradorPilar nuevoFiltadorPolar = new FiltradorPilar(this.pixeles, 0, 0, micontenedor, mayorMenor);
				nuevoFiltadorPolar.filtarIterativoPilar();
			} else if (opcion == 4) {
				FiltradorHorizontal nuevoFiltadorPolar = new FiltradorHorizontal(this.pixeles, 0, 0, micontenedor,
						mayorMenor);
				nuevoFiltadorPolar.filtarIterativoHorizontal();
			} else if (opcion == 5) {
				FiltradorEsquinaInferior nuevoFiltadorEsquinaInferior = new FiltradorEsquinaInferior(this.pixeles, 0, 0,
						micontenedor, mayorMenor);
				nuevoFiltadorEsquinaInferior.filtarIterativoEsquinaInferior();
			} else if (opcion == 6) {
				FiltadorEsquinaSuperior nuevoFiltadorEsquinaSuperior = new FiltadorEsquinaSuperior(this.pixeles, 0, 0,
						micontenedor, mayorMenor);
				nuevoFiltadorEsquinaSuperior.filtarIterativoESquinaSuperior();
			}
		} else {
			ArrayList<Thread> hilos = new ArrayList<>();
			if (opcion == 1) {
				for (int i = 0; i < 4; i++) {
					FiltradorX nuevoFiltrador = new FiltradorX(this.pixeles, i, i, micontenedor, mayorMenor);
					Thread nuevoHilo = new Thread(nuevoFiltrador);
					hilos.add(nuevoHilo);
				}
				for (Thread a : hilos) {
					a.start();
				}
				for (Thread a : hilos) {
					try {
						a.join();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			} else if (opcion == 2) {
				for (int i = 0; i < 4; i++) {
					FiltradorMas nuevoFiltrador = new FiltradorMas(this.pixeles, i, i, micontenedor, mayorMenor);
					Thread nuevoHilo = new Thread(nuevoFiltrador);
					hilos.add(nuevoHilo);
				}
				for (Thread a : hilos) {
					a.start();
				}
				for (Thread a : hilos) {
					try {
						a.join();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			} else if (opcion == 3) {
				for (int i = 0; i < 4; i++) {
					FiltradorPilar nuevoFiltrador = new FiltradorPilar(this.pixeles, i, i, micontenedor, mayorMenor);
					Thread nuevoHilo = new Thread(nuevoFiltrador);
					hilos.add(nuevoHilo);
				}
				for (Thread a : hilos) {
					a.start();
				}
				for (Thread a : hilos) {
					try {
						a.join();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			} else if (opcion == 4) {
				for (int i = 0; i < 4; i++) {
					FiltradorHorizontal nuevoFiltrador = new FiltradorHorizontal(this.pixeles, i, i, micontenedor,
							mayorMenor);
					Thread nuevoHilo = new Thread(nuevoFiltrador);
					hilos.add(nuevoHilo);
				}
				for (Thread a : hilos) {
					a.start();
				}
				for (Thread a : hilos) {
					try {
						a.join();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			} else if (opcion == 5) {
				for (int i = 0; i < 4; i++) {
					FiltradorEsquinaInferior nuevoFiltrador = new FiltradorEsquinaInferior(this.pixeles, i, i,
							micontenedor, mayorMenor);
					Thread nuevoHilo = new Thread(nuevoFiltrador);
					hilos.add(nuevoHilo);
				}
				for (Thread a : hilos) {
					a.start();
				}
				for (Thread a : hilos) {
					try {
						a.join();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			} else if (opcion == 6) {
				for (int i = 0; i < 4; i++) {
					FiltadorEsquinaSuperior nuevoFiltrador = new FiltadorEsquinaSuperior(this.pixeles, i, i,
							micontenedor, mayorMenor);
					Thread nuevoHilo = new Thread(nuevoFiltrador);
					hilos.add(nuevoHilo);
				}
				for (Thread a : hilos) {
					a.start();
				}
				for (Thread a : hilos) {
					try {
						a.join();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
		Long tiempoFin = System.nanoTime();
		System.out.println("Tiempo [ns]: " + (tiempoFin - tiempoInicio));
		crear_pmg(micontenedor.getMatrizFinal());
	}

	public void crear_pmg(int[][] matriz) {
		try {
			System.out.print("Nombre del archivo de salida: ");
			Scanner output = new Scanner(System.in);
			String nombreSalida = output.nextLine();
			System.out.println("\n");
			FileWriter fw = new FileWriter(nombreSalida + ".pgm");
			BufferedWriter salida = new BufferedWriter(fw);
			salida.write("P2\n# CREATOR: XV Version 3.10a  Rev: 08/26/21\n" + this.ancho + " " + this.alto + "\n"
					+ this.blancoAbsoluto + "\n");
			// System.out.println(this.blancoAbsoluto);
			for (int i = 0; i < this.alto; i++) {
				for (int j = 0; j < this.ancho; j++) {
					salida.write(matriz[i][j] + " ");
				}
			}
			System.out.println("¡Se ha creado el archivo de salida con éxito!");
			salida.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
