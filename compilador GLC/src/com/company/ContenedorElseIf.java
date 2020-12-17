package com.company;

public class ContenedorElseIf
{
    private int numeroLinea;
    private String tipo;

    public ContenedorElseIf(int linea, String tipo)
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
