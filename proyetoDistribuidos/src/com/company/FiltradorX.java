package com.company;

public class FiltradorX extends Filtrador implements Runnable
{
    public FiltradorX(int[][] matrizBase, int id, int salto, Contenedor contenedor, int mayorMenor)
    {
        super(matrizBase,id,salto,contenedor,mayorMenor);
    }

    @Override
    public void run()
    {
        for (int i = 0; i < this.matrizBase[id].length; i++)
        {
            int valor;
            if(this.validarFiltroX( this.id,i))
            {
                if (this.mayorOMenor == 1)
                {
                    valor = filtroMayor(this.id, i);
                }
                else
                {
                    valor = filtroMenor(this.id, i);
                }

            }
            else
            {
                valor = this.matrizBase[this.id][i];
            }
            this.miContendor.setCoordenadaMatrizFinal(this.id,i, valor);
        }

    }

    public void filtarIterativoX()
    {
        for (int id = 0; id < this.matrizBase.length; id++)
        {
            for (int i = 0; i < this.matrizBase[id].length; i++)
            {
                int valor;
                if(this.validarFiltroX(id,i))
                {
                    System.out.println("hola id:" + id + " i:"+i);
                    if (this.mayorOMenor == 1)
                    {
                        valor = filtroMayor(id,i);
                    }
                    else
                    {
                        valor = filtroMenor(id,i);
                    }

                }
                else
                {
                    valor = this.matrizBase[id][i];
                }
                System.out.println("valor:" + valor);
                this.miContendor.setCoordenadaMatrizFinal(id,i, valor);
            }
        }
    }

    private boolean validarFiltroX(int id, int i)
    {
        boolean existen = true;
        if(0 > id -1 || 0 > i-1 || this.matrizBase[id].length <= i+1 || this.matrizBase.length <= id+1)
        {
            existen =false;
        }
        return existen;
    }

    private int filtroMayor(int id, int i)
    {
        int mayor =this.matrizBase[id][i];
        if(mayor < this.matrizBase[id-1][i-1])
        {
            mayor = this.matrizBase[id-1][i-1];
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
