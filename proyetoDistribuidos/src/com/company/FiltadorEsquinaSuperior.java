package com.company;

public class FiltadorEsquinaSuperior extends Filtrador implements Runnable {

	public FiltadorEsquinaSuperior(int[][] matrizBase, int id, int salto, Contenedor miContendor, int mayorOMenor) {
		super(matrizBase, id, salto, miContendor, mayorOMenor);
	}

	public void filtarIterativoESquinaSuperior() {
		for (int id = 0; id < this.matrizBase.length; id++) {
			for (int i = 0; i < this.matrizBase[id].length; i++) {
				int valor;
				if (this.validarFiltroEsquinaSuperior(id, i)) {
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
		for (this.id = 0; this.id < this.matrizBase.length; this.id++) {
			for (int i = 0; i < this.matrizBase[this.id].length; i++) {
				int valor;
				if (this.validarFiltroEsquinaSuperior(this.id, i)) {
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
	}

	private boolean validarFiltroEsquinaSuperior(int id, int i) {
		boolean existen = true;
		if (this.matrizBase.length <= id + 1) {
			existen = false;
		} else if (this.matrizBase[this.id].length <= i + 1) {
			existen = false;
		}
		return existen;
	}

	private int filtroMayor(int id, int i) {
		int mayor = this.matrizBase[id][i];
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
		if (menor > this.matrizBase[id + 1][i]) {
			menor = this.matrizBase[id + 1][i];
		}
		if (menor > this.matrizBase[id][i + 1]) {
			menor = this.matrizBase[id][i + 1];
		}
		return menor;
	}
}
