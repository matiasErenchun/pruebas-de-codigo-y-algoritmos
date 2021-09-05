package com.company;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args)
    {
        Integer[][] matriz= new Integer[100][100];
        ArrayList<Thread> hilos = new ArrayList<>();
        Monitor monitor = new Monitor();
        for (int i = 0; i <100 ; i++)
        {
            for (int j = 0; j < 100; j++)
            {
                matriz[i][j] = 1;
            }

        }
        for (int i = 0; i < 100; i++)
        {
            SumadorFila nuevoSumador = new SumadorFila(i,matriz[i],monitor);
            Thread nuevoHilo = new Thread(nuevoSumador);
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
        System.out.println("el total es:"+monitor.getTotal());
    }
}
