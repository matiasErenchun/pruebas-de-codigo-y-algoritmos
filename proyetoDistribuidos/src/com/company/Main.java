package com.company;

import java.util.ArrayList;

public class Main
{

    public static void main(String[] args)
    {
        int [][] matriz= new int [4][4];
        Contenedor micontenedor = new Contenedor(matriz);
        int [][] matrizprueba=
                {{54,130,206,224},
                {166,167,210,114},
                {27,121,13,213},
                {66,0,78,18}};
        int iterativo = 0;
        int opcion = 1;
        if(iterativo==0)
        {
            if (opcion == 0)
            {
                FiltradorMas nuevoFiltrador = new FiltradorMas(matrizprueba,0,0, micontenedor, 0);
                nuevoFiltrador.filtarIterativoMas();
            }
            else if (opcion ==1 )
            {
                FiltradorX nuevoFiltadorX = new FiltradorX(matrizprueba,0,0, micontenedor, 1);
                nuevoFiltadorX.filtarIterativoX();
            }

        }
        else
        {
            ArrayList<Thread> hilos = new ArrayList<>();
            for (int i = 0; i < 4; i++)
            {
                FiltradorMas nuevoFiltrador = new FiltradorMas(matrizprueba,i,i, micontenedor, 0);
                Thread nuevoHilo = new Thread(nuevoFiltrador);
                hilos.add(nuevoHilo);
            }

            for (Thread a : hilos)
            {
                a.start();
            }

            for (Thread a: hilos)
            {
                try {
                    a.join();
                }
                catch (InterruptedException e )
                {
                    e.printStackTrace();
                }

            }
        }

        for (int j = 0; j < matriz.length; j++)
        {
            for (int k = 0; k < matriz.length; k++)
            {
                System.out.print(matriz[j][k] +", ");
            }
            System.out.println(" ");
        }

	// write your code here
    }
}
