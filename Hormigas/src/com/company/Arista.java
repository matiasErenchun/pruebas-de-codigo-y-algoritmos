package com.company;

public class Arista
{
    private int idNodoA;
    private int idNodoB;
    private Double peso;
    private Double cargaHormonal;
    private Double factorAjusteCargaHormonal;

    public Arista(int idNodoA,int idNodoB,Double peso, Double cargaHormonal,Double factorAjusteCargaHormonal)
    {
        this.factorAjusteCargaHormonal=factorAjusteCargaHormonal;
        this.idNodoA=idNodoA;
        this.idNodoB=idNodoB;
        this.peso = peso;
        this.cargaHormonal = cargaHormonal;
    }

    public Double getPeso()
    {
        return peso;
    }

    public Double getCargaHormonal()
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
        System.out.println(this.idNodoA+"-"+this.idNodoB+" "+"suma un peso de:"+this.peso);
    }

    public Double incrementarCargaHormonal()
    {
        return this.getCargaHormonal()+this.factorAjusteCargaHormonal;
    }

    public void decrementarhormonas()
    {
        this.cargaHormonal-=0.1;
    }

    public void setCargaHormonal(Double cargaHormonal)
    {
        this.cargaHormonal = cargaHormonal;
    }
}
