package com.company;

import java.util.ArrayList;
import java.util.Random;

public class Main
{
    public static void main(String[] args)
    {
        //String archivo=args[0];
        String archivo ="D:\\repo-git-local-2\\pruebas-de-codigo-y-algoritmos\\perceptron\\src\\com\\company\\inputFile.txt";
        Lector miLector = new Lector();
        ArrayList<ArrayList<String>> inputs = miLector.leerCadenas(archivo);

        for (ArrayList<String> inpust: inputs)
        {
            int salidaEsperada=Integer.parseInt(inpust.get(2));
            int entrada1=Integer.parseInt(inpust.get(0));;
            int entrada2 =Integer.parseInt(inpust.get(1));;
            Random miRandom = new Random(System.nanoTime());
            Double camino1 = miRandom.nextDouble();
            Double camino2= miRandom.nextDouble();
            Double ratioAprendisaje = new Double(0.3);
            Double umbral = new Double(0.6);

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
            System.out.println("para los inputs:");
            System.out.println("input 1:"+entrada1);
            System.out.println("input 2:"+entrada2);
            System.out.println("salida esperada:"+salidaEsperada);
            miPerceptron.mostrar();
            System.out.println(" ");
        }

    }
}
