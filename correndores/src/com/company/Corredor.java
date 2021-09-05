package com.company;

import java.util.Random;

public class Corredor implements Runnable
{
    private Testigo testigo;
    private Random random;

    public Corredor(Testigo testigo, int turno)
    {
        this.random = new Random(System.nanoTime());
        this.testigo = testigo;
        this.testigo.setTurno(turno);
    }

    @Override
    public void run()
    {
        int distancia = 0;
        if(this.testigo.getTurno()<3)
        {
            while (distancia<30)
            {
                int value = random.nextInt(11) + 1;
                distancia+=value;
                System.out.println("corredor:"+this.testigo.getTurno()+" del equipo:"+ this.testigo.getEquipo()+" avanzo:"+ value);
            }
            Corredor siguenteCorredor = new Corredor(this.testigo, this.testigo.getTurno()+1);
            Thread nuevoHilo = new Thread(siguenteCorredor);
            nuevoHilo.start();
            try
            {
                nuevoHilo.join();
            }
            catch (InterruptedException e )
            {
                e.printStackTrace();
            }
        }
        else
        {
            while (distancia<40)
            {
                int value = random.nextInt(11) + 1;
                distancia+=value;
                System.out.println("corredor:"+this.testigo.getTurno()+" del equipo:"+ this.testigo.getEquipo()+" avanzo:"+ value);
            }
            System.out.println("el equipo:"+ this.testigo.getEquipo() + "termino la carrera");
        }

    }
}
