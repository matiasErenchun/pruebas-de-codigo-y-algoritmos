package com.company;

public class Contenedor
{
    private int[][] matrizFinal;

    public Contenedor(int[][] matrizFinal)
    {
        this.matrizFinal = matrizFinal;
    }

    public void setCoordenadaMatrizFinal(int i, int j,int valor)
    {
        this.matrizFinal[i][j]=valor;
    }
}

