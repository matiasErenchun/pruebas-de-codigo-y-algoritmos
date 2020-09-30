package com.company;

import java.util.ArrayDeque;


public class BuscadorProfundidad extends Buscador
{
    private ArrayDeque<Estado> colaEStados;
    private int idCounter;


    public BuscadorProfundidad()
    {
        this.colaEStados = new ArrayDeque<>();
        this.idCounter=1;
    }

    public void Buscar(Estado inicial, Estado fin)
    {
        this.colaEStados.clear();
        this.idCounter=1;
        String[][] contenedor;
        String aux = " ";
        Estado nuevoEstado;
        this.colaEStados.addFirst(inicial);
        Estado actual = this.colaEStados.pollFirst();
        int i, j;
        while (!compararEstados(fin, actual))
        {
            i = actual.getIspace();
            j = actual.getJspace();



            if (i < 3 && (j + 1) < 3)
            {

                contenedor = this.copiarEstado(actual);
                aux = contenedor[i][j + 1];
                contenedor[i][j + 1] = " ";
                contenedor[i][j] = aux;
                nuevoEstado = new Estado(i, j + 1, contenedor, actual.getNivel() + 1, this.idCounter);
                nuevoEstado.setEstadoAnterior(actual);
                this.addEstadoPila(nuevoEstado,actual);
            }
            if ((i + 1) < 3 && j < 3)
            {

                contenedor = this.copiarEstado(actual);
                aux = contenedor[i + 1][j];
                contenedor[i + 1][j] = " ";
                contenedor[i][j] = aux;
                nuevoEstado = new Estado(i + 1, j, contenedor, actual.getNivel() + 1, this.idCounter);
                nuevoEstado.setEstadoAnterior(actual);
                this.addEstadoPila(nuevoEstado,actual);
            }
            if (i >= 0 && (j - 1) >= 0)
            {

                contenedor = this.copiarEstado(actual);
                aux = contenedor[i][j - 1];
                contenedor[i][j - 1] = " ";
                contenedor[i][j] = aux;
                nuevoEstado = new Estado(i, j - 1, contenedor, actual.getNivel() + 1, this.idCounter);
                nuevoEstado.setEstadoAnterior(actual);
                this.addEstadoPila(nuevoEstado,actual);
            }
            if ((i - 1) >= 0 && j >= 0)
            {
                contenedor = this.copiarEstado(actual);
                aux = contenedor[i - 1][j];
                contenedor[i - 1][j] = " ";
                contenedor[i][j] = aux;
                nuevoEstado = new Estado(i - 1, j, contenedor, actual.getNivel() + 1, this.idCounter);
                nuevoEstado.setEstadoAnterior(actual);
                this.addEstadoPila(nuevoEstado,actual);
            }

            actual = this.colaEStados.pollFirst();
            System.out.println(" ");
            System.out.println("nodos expandido :"+this.idCounter);

        }
        System.out.println(" ");
        System.out.println("----------------------------------------~~~0o0~~~-----------------------------------");
        System.out.println("nodos expandidos totales:" + (this.idCounter));
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

    private void addEstadoPila(Estado nuevo, Estado actual)
    {
        try {
            if(!this.compararEstados(actual.getEstadoAnterior(),nuevo))
            {
                this.colaEStados.addFirst(nuevo);
                this.idCounter++;
            }
            else
            {
                System.out.println(" no se agrego El estado ya que es igual al padre del estado actual");
                nuevo.print();
                actual.getEstadoAnterior().print();
            }
        }catch (Exception e)
        {
            System.out.println("---raiz---");
        }
    }

    
}
