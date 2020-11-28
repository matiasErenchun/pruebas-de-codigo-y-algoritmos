package com.company;

import java.util.Random;

public class Main {

    public static void main(String[] args)
    {
        int salidaEsperada=0;
        int entrada1=1;
        int entrada2 =1;
        Random miRandom = new Random(System.nanoTime());
        Double camino1 = miRandom.nextDouble();
        Double camino2= miRandom.nextDouble();
        Double ratioAprendisaje = new Double(0.3);
        Double umbral = new Double(0.1);

        Perceptron miPerceptron = new Perceptron(camino1,camino2,umbral);

        boolean segir=true;
        int resultado;
        while (segir)
        {
            resultado = miPerceptron.calcularResultado(entrada1,entrada2);
            if(resultado== salidaEsperada)
            {
                segir=false;
            }
            else
            {
                miPerceptron.ajustarCaminos(entrada1, entrada2, resultado, salidaEsperada, ratioAprendisaje);
            }
        }

        miPerceptron.mostrar();
    }
}
