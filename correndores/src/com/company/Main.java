package com.company;

public class Main {

    public static void main(String[] args)
    {
	    Testigo testigo1 = new Testigo(1);
        Testigo testigo2 = new Testigo(2);
        Corredor corredorEquipo1 = new Corredor(testigo1, 1);
        Corredor corredorEquipo2 = new Corredor(testigo2, 1);
        Thread hiloEquipo1 = new Thread(corredorEquipo1);
        Thread hiloEquipo2 = new Thread(corredorEquipo2);
        hiloEquipo1.start();
        hiloEquipo2.start();
        try
        {
            hiloEquipo1.join();
        }
        catch (InterruptedException e )
        {
            e.printStackTrace();
        }
        try
        {
            hiloEquipo2.join();
        }
        catch (InterruptedException e )
        {
            e.printStackTrace();
        }
    }
}
