package com.company;

public class Perceptron
{
    private Double camino1;
    private Double camino2;
    private Double umbral;

    public Perceptron(Double camino1, Double camino2, Double umbral) {
        this.camino1 = camino1;
        this.camino2 = camino2;
        this.umbral= umbral;
    }

    public int calcularResultado(int input1, int input2)
    {
        Double salida = new Double((this.camino1*input1)+(this.camino2*input2)-this.umbral);
        if(salida>0)
        {
            return 1;
        }
        else
        {
            return 0;
        }
    }

    public void ajustarCaminos(int input1, int input2, int salida, int salidaEsperada, Double ratioAprendisaje)
    {
        Double nuevoCamino1= new Double(this.camino1+(ratioAprendisaje*(salidaEsperada-salida)*input1));
        Double nuevoCamino2= new Double(this.camino2+(ratioAprendisaje*(salidaEsperada-salida)*input2));
        this.setCamino1(nuevoCamino1);
        this.setCamino2(nuevoCamino2);
    }

    public void setCamino1(Double camino1)
    {
        this.camino1 = camino1;
    }

    public void setCamino2(Double camino2)
    {
        this.camino2 = camino2;
    }

    public void mostrar()
    {
        System.out.println("para los parametros ingresados el valor del peso del camino1 es:"+this.camino1+" y el del camino dos es:"+this.camino2);
    }
}
