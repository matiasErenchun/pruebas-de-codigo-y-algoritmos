package com.company;

public class Contenedor
{
    private int[][] matrizFinal;

    public Contenedor(int[][] matrizFinal)
    {
        this.matrizFinal = matrizFinal;
        System.out.println(this.matrizFinal.length);
    }

    public void setCoordenadaMatrizFinal(int i, int j,int valor) {
        System.out.println("i:" + i + ",j:" + j + ",valor:" + valor);
        this.matrizFinal[i][j] = valor;
    }

    public int lawea(){
        return this.matrizFinal.length;
    }

}

