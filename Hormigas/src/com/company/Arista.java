package com.company;

public class Arista
{
    private int idNodoA;
    private int idNodoB;
    private Double peso;
    private int cargaHormonal;

    public Arista(int idNodoA,int idNodoB,Double peso, int cargaHormonal)
    {
        this.idNodoA=idNodoA;
        this.idNodoB=idNodoB;
        this.peso = peso;
        this.cargaHormonal = cargaHormonal;
    }

    public Double getPeso()
    {
        return peso;
    }

    public int getCargaHormonal()
    {
        return cargaHormonal;
    }

    public int getIdNodoA()
    {
        return idNodoA;
    }

    public int getIdNodoB()
    {
        return idNodoB;
    }

    public void printt()
    {
        System.out.print(this.peso +":"+this.idNodoA+"-"+this.idNodoB+" ");
    }
}
