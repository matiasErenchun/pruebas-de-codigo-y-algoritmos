package com.company;

public class Monitor
{
    private int total;
    private Boolean libre;

    public Monitor()
    {
        this.total = 0;
        this.libre = true;
    }

    public int getTotal() {
        return total;
    }

    public synchronized void sumarSubTotal(int subTotal, int idHilo) throws InterruptedException
    {
        while (!this.libre)
        {
            wait();
        }
        this.libre = false;
        this.total += subTotal;
        System.out.println("el hilo:"+ idHilo +", sumo:"+ subTotal +" y el total actual es:"+this.total);
        this.libre=true;
        notify();
    }
}
