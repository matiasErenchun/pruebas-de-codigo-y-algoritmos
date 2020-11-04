package com.company;

import java.util.ArrayDeque;
import java.util.ArrayList;

public class BuscadorAnchura extends Buscador {
    private ArrayDeque<Estado> colaEStados;
    private ArrayList<Estado> estados;
    private int idCounter;

    public BuscadorAnchura() {
        this.colaEStados = new ArrayDeque<>();
        this.estados = new ArrayList<>();
        this.idCounter = 1;
    }

    public void buscar(Estado inicial, Estado fin)
    {
        this.colaEStados.clear();
        this.idCounter=1;
        this.estados.clear();
        String[][] contenedor;
        String aux = " ";
        Estado nuevoEstado;
        this.estados.add(inicial);
        this.colaEStados.add(inicial);
        Estado actual = this.colaEStados.pollFirst();
        int i, j;
        while (!compararEstados(fin, actual))
        {
            i = actual.getIspace();
            j = actual.getJspace();
            if ((i - 1) >= 0 && j >= 0) {
                contenedor = this.copiarEstado(actual);
                aux = contenedor[i - 1][j];
                contenedor[i - 1][j] = " ";
                contenedor[i][j] = aux;
                nuevoEstado = new Estado(i - 1, j, contenedor, actual.getNivel() + 1, this.idCounter);
                nuevoEstado.setEstadoAnterior(actual);
                this.estados.add(nuevoEstado);
                this.colaEStados.add(nuevoEstado);
                this.idCounter++;
            }
            if (i >= 0 && (j - 1) >= 0) {

                contenedor = this.copiarEstado(actual);
                aux = contenedor[i][j - 1];
                contenedor[i][j - 1] = " ";
                contenedor[i][j] = aux;
                nuevoEstado = new Estado(i, j - 1, contenedor, actual.getNivel() + 1, this.idCounter);
                nuevoEstado.setEstadoAnterior(actual);
                this.estados.add(nuevoEstado);
                this.colaEStados.add(nuevoEstado);
                this.idCounter++;
            }
            if ((i + 1) < 3 && j < 3) {

                contenedor = this.copiarEstado(actual);
                aux = contenedor[i + 1][j];
                contenedor[i + 1][j] = " ";
                contenedor[i][j] = aux;
                nuevoEstado = new Estado(i + 1, j, contenedor, actual.getNivel() + 1, this.idCounter);
                nuevoEstado.setEstadoAnterior(actual);
                this.estados.add(nuevoEstado);
                this.colaEStados.add(nuevoEstado);
                this.idCounter++;
            }
            if (i < 3 && (j + 1) < 3) {

                contenedor = this.copiarEstado(actual);
                aux = contenedor[i][j + 1];
                contenedor[i][j + 1] = " ";
                contenedor[i][j] = aux;
                nuevoEstado = new Estado(i, j + 1, contenedor, actual.getNivel() + 1, this.idCounter);
                nuevoEstado.setEstadoAnterior(actual);
                this.estados.add(nuevoEstado);
                this.colaEStados.add(nuevoEstado);
                this.idCounter++;
            }

            actual = this.colaEStados.pollFirst();
            System.out.println(" ");
            actual.print();

        }
        System.out.println(" ");
        System.out.println("----------------------------------------~~~0o0~~~-----------------------------------");
        System.out.println("nodos expandidos totales:" +this.idCounter);
        System.out.println("inicio del camino de solucion encontrado");
        while(actual.getNivel()!=-1)
        {
            actual.print();
            try {
                actual=actual.getEstadoAnterior();
            }catch (Exception e)
            {
                System.out.println("----raiz----");
            }

        }
        System.out.println(" ");
        System.out.println(" se imprimio el camino de solucion por favor suba para revisarlo  ");
        System.out.println(" ");

    }
}


