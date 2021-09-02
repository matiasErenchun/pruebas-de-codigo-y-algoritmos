package com.company;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args)
    {
        ArrayList<Integer> hola= new ArrayList<>();
        ArrayList<Thread> hilos = new ArrayList<>();
        Monitor monitor = new Monitor();
        for (int i = 0; i <100 ; i++)
        {
            hola.add(1);
        }
        for (int i = 0; i < 1000; i++)
        {
            SumadorFila nuevoSumador = new SumadorFila(i,hola,monitor);
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
