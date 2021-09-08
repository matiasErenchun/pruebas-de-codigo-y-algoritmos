package com.company;

import java.sql.Array;

public class Filtrador implements Runnable
{
    private int[][] matrizBase;
    private int id;
    private int salto;
    private Contenedor miContendor;

    public Filtrador(int[][] matrizBase, int id, int salto, Contenedor contenedor)
    {
        this.matrizBase = matrizBase;
        this.id = id;
        this.salto = salto;
        this.miContendor = contenedor;
    }

    @Override
    public void run()
    {
        for (int i = 0; i < matrizBase[id].length; i++)
        {
            int valor;
            if(this.validarFiltroSuma(i))
            {
                valor = filtroMas(i);
            }
            else
            {
                valor = this.matrizBase[this.id][i];
            }
            this.miContendor.setCoordenadaMatrizFinal(this.id,i, valor);
        }
    }

    private boolean validarFiltroSuma(int i)
    {
        boolean existen = true;
        if(0 > this.id -1)
        {
            existen =false;
        }
        else if(0 > i-1)
        {
           existen = false;
        }
        else if( this.matrizBase.length <= this.id+1)
        {
            existen = false;
        }
        else if( this.matrizBase[this.id].length <= i+1)
        {
            existen = false;
        }
        return existen;
    }
    private int filtroMas(int i)
    {
        int mayor =this.matrizBase[id][i];
        if(mayor < this.matrizBase[id-1][i])
        {
            mayor = this.matrizBase[id-1][i];
        }
        if(mayor < this.matrizBase[id][i-1])
        {
            mayor = this.matrizBase[id][i-1];
        }
        if(mayor < this.matrizBase[id+1][i])
        {
            mayor = this.matrizBase[id+1][i];
        }
        if(mayor < this.matrizBase[id][i+1])
        {
            mayor = this.matrizBase[id][i+1];
        }
        return mayor;
    }

}
