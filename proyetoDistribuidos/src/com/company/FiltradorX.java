package com.company;

public class FiltradorX extends Filtrador implements Runnable
{
    private int[][] matrizBase;
    private int id;
    private int salto;
    private Contenedor miContendor;
    private  int mayorOMenor;

    public FiltradorX(int[][] matrizBase, int id, int salto, Contenedor contenedor, int mayorMenor)
    {
        super(matrizBase,id,salto,contenedor,mayorMenor);
    }

    public void filtarIterativoX()
    {

    }

    @Override
    public void run()
    {

    }

    private boolean validarFiltroX(int id, int i)
    {
        boolean existen = true;
        if(0 > id -1 || 0 > i-1 || this.matrizBase[id].length < i+1 || this.matrizBase.length <id+1)
        {
            existen =false;
        }
        return existen;
    }

    private int filtroMayor(int id, int i)
    {
        int mayor =this.matrizBase[id][i];
        if(mayor < this.matrizBase[id-1][-i])
        {
            mayor = this.matrizBase[id-1][-i];
        }
        if(mayor < this.matrizBase[id+1][i-1])
        {
            mayor = this.matrizBase[id+1][i-1];
        }
        if(mayor < this.matrizBase[id+1][i+1])
        {
            mayor = this.matrizBase[id+1][i+1];
        }
        if(mayor < this.matrizBase[id-1][i+1])
        {
            mayor = this.matrizBase[id-1][i+1];
        }
        return mayor;
    }

    private int filtroMenor(int id, int i)
    {
        int menor = this.matrizBase[id][i];
        if(menor > this.matrizBase[id-1][i-1])
        {
            menor = this.matrizBase[id-1][i-1];
        }
        if(menor > this.matrizBase[id+1][i-1])
        {
            menor = this.matrizBase[id+1][i-1];
        }
        if(menor > this.matrizBase[id+1][i+1])
        {
            menor = this.matrizBase[id+1][i+1];
        }
        if(menor > this.matrizBase[id-1][i+1])
        {
            menor = this.matrizBase[id-1][i+1];
        }
        return menor;
    }
}
