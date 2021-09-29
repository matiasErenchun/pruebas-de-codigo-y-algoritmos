package com.company;

import java.time.Duration;
import java.time.Instant;

public class FiltradorMas extends Filtrador implements Runnable {
	public FiltradorMas(int[][] matrizBase, int id, int salto, Contenedor contenedor, int mayorMenor) {
		super(matrizBase, id, salto, contenedor, mayorMenor);
	}

	public void filtarIterativoMas() {
		for (int id = 0; id < this.matrizBase.length; id++) {
			for (int i = 0; i < this.matrizBase[id].length; i++) {
				int valor;
				if (this.validarFiltroSuma(id, i)) {
					if (this.mayorOMenor == 1) {
						valor = filtroMayor(id, i);
					} else {
						valor = filtroMenor(id, i);
					}

				} else {
					valor = this.matrizBase[id][i];
				}
				this.miContendor.setCoordenadaMatrizFinal(id, i, valor);
			}
		}
	}

	@Override
	public void run() {
		Instant start = Instant.now();
		for (this.id = 0; this.id < this.matrizBase.length; this.id++) {
			for (int i = 0; i < this.matrizBase[this.id].length; i++) {
				int valor;
				if (this.validarFiltroSuma(this.id, i)) {
					if (this.mayorOMenor == 1) {
						valor = filtroMayor(this.id, i);
					} else {
						valor = filtroMenor(this.id, i);
					}

				} else {
					valor = this.matrizBase[this.id][i];
				}
				this.miContendor.setCoordenadaMatrizFinal(this.id, i, valor);
			}
		}
		Instant finish = Instant.now();
		System.out.println("Tiempo: " + Duration.between(start, finish).toNanos() + "[ns]");
	}

	private boolean validarFiltroSuma(int id, int i) {
		boolean existen = true;
		if (0 > id - 1) {
			existen = false;
		} else if (0 > i - 1) {
			existen = false;
		} else if (this.matrizBase.length <= id + 1) {
			existen = false;
		} else if (this.matrizBase[this.id].length <= i + 1) {
			existen = false;
		}
		return existen;
	}

	private int filtroMayor(int id, int i) {
		int mayor = this.matrizBase[id][i];
		if (mayor < this.matrizBase[id - 1][i]) {
			mayor = this.matrizBase[id - 1][i];
		}
		if (mayor < this.matrizBase[id][i - 1]) {
			mayor = this.matrizBase[id][i - 1];
		}
		if (mayor < this.matrizBase[id + 1][i]) {
			mayor = this.matrizBase[id + 1][i];
		}
		if (mayor < this.matrizBase[id][i + 1]) {
			mayor = this.matrizBase[id][i + 1];
		}
		return mayor;
	}

	private int filtroMenor(int id, int i) {
		int menor = this.matrizBase[id][i];
		if (menor > this.matrizBase[id - 1][i]) {
			menor = this.matrizBase[id - 1][i];
		}
		if (menor > this.matrizBase[id][i - 1]) {
			menor = this.matrizBase[id][i - 1];
		}
		if (menor > this.matrizBase[id + 1][i]) {
			menor = this.matrizBase[id + 1][i];
		}
		if (menor > this.matrizBase[id][i + 1]) {
			menor = this.matrizBase[id][i + 1];
		}
		return menor;
	}

}
