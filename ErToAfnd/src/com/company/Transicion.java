package com.company;

public class Transicion
{
    private String nodoSalida;
    private Nodo nodollegada;
    private String caracter;

    public Transicion(String nodoSalida, Nodo nodollegada, String caracter)
    {
        this.nodoSalida = nodoSalida;
        this.nodollegada = nodollegada;
        this.caracter = caracter;
    }

    public String getNodoSalida()
    {
        return nodoSalida;
    }

    public Nodo getNodollegada()
    {
        return nodollegada;
    }

    public String getCaracter()
    {
        return caracter;
    }
}
