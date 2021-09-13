package com.company;

public abstract class Filtrador
{
    protected int[][] matrizBase;
    protected int id;
    protected int salto;
    protected Contenedor miContendor;
    protected   int mayorOMenor;

    public Filtrador(int[][] matrizBase, int id, int salto, Contenedor miContendor, int mayorOMenor) {
        this.matrizBase = matrizBase;
        this.id = id;
        this.salto = salto;
        this.miContendor = miContendor;
        this.mayorOMenor = mayorOMenor;
    }

}
