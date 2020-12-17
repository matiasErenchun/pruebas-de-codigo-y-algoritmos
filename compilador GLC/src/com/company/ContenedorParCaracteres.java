package com.company;

public class ContenedorParCaracteres
{
    private int numeroLinea;
    private String tipo;

    public ContenedorParCaracteres(int linea, String tipo)
    {
        this.numeroLinea= linea;
        this.tipo = tipo;
    }

    public int getNumeroLinea()
    {
        return numeroLinea;
    }

    public String getTipo()
    {
        return tipo;
    }
}
