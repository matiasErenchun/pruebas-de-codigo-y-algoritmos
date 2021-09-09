package com.company;

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

    public void filtarIterativo ()
    {
        for (int id = 0; id < matrizBase.length; id++)
        {
            for (int i = 0; i < matrizBase[id].length; i++)
            {
                int valor;
                if(this.validarFiltroSuma(id,i))
                {
                    valor = filtroMayor(id,i);
                }
                else
                {
                    valor = this.matrizBase[id][i];
                }
                this.miContendor.setCoordenadaMatrizFinal(id,i, valor);
            }
        }
    }

    @Override
    public void run()
    {
        for (int i = 0; i < matrizBase[id].length; i++)
        {
            int valor;
            if(this.validarFiltroSuma( this.id,i))
            {
                valor = filtroMayor(this.id, i);
            }
            else
            {
                valor = this.matrizBase[this.id][i];
            }
            this.miContendor.setCoordenadaMatrizFinal(this.id,i, valor);
        }
    }

    private boolean validarFiltroSuma(int id, int i)
    {
        boolean existen = true;
        if(0 > id -1)
        {
            existen =false;
        }
        else if(0 > i-1)
        {
           existen = false;
        }
        else if( this.matrizBase.length <= id+1)
        {
            existen = false;
        }
        else if( this.matrizBase[this.id].length <= i+1)
        {
            existen = false;
        }
        return existen;
    }
    private int filtroMayor(int id, int i)
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
