package com.company;

public class SumadorFila implements Runnable
{
    private int id;
    private Integer[] sumandos;
    private int miTotal;
    private Monitor monitor;

    public SumadorFila(int id,Integer[] sumandos,Monitor monitor )
    {
        this.id = id;
        this.sumandos = sumandos;
        this.miTotal = 0;
        this.monitor = monitor;
    }

    @Override
    public void run()
    {
        System.out.println("ejecutando run de:"+ this.id);
        for (Integer aSumar: this.sumandos)
        {
            this.miTotal +=aSumar;
        }
        try {
            monitor.sumarSubTotal(this.miTotal, this.id);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
