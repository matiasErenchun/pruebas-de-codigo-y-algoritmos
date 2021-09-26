package com.company;

public class FiltradorHorizontal extends Filtrador implements Runnable
{

    public FiltradorHorizontal(int[][] matrizBase, int id, int salto, Contenedor miContendor, int mayorOMenor)
    {
        super(matrizBase, id, salto, miContendor, mayorOMenor);
    }

    public void filtarIterativoHorizontal()
    {
        System.out.println(this.miContendor.lawea());
        System.out.println(this.matrizBase.length);
        this.imprimir(this.matrizBase);
        for (int id = 0; id < this.matrizBase.length; id++)
        {
            for (int i = 0; i < this.matrizBase[id].length; i++)
            {
                int valor;
                if(this.validarFiltroHorizontal(id,i))
                {
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
                this.miContendor.setCoordenadaMatrizFinal(id,i, valor);
            }
        }
    }

    public void imprimir(int[][] matriz){
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                System.out.print(matriz[i][j] + "|");
            }
            System.out.println("");
        }
    }

    @Override
    public void run()
    {
        for (int i = 0; i < this.matrizBase[id].length; i++)
        {
            int valor;
            if(this.validarFiltroHorizontal( this.id,i))
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

    private boolean validarFiltroHorizontal(int id, int i)
    {
        boolean existen = true;
        if( this.matrizBase[this.id].length <= i+1)
        {
            existen = false;
        }
        return existen;
    }
    private int filtroMayor(int id, int i)
    {
        int mayor =this.matrizBase[id][i];
        if(mayor < this.matrizBase[id][i+1])
        {
            mayor = this.matrizBase[id][i+1];
        }
        return mayor;
    }

    private int filtroMenor(int id, int i)
    {
        int menor = this.matrizBase[id][i];
        if(menor > this.matrizBase[id][i+1])
        {
            menor = this.matrizBase[id][i+1];
        }
        return menor;
    }
}
