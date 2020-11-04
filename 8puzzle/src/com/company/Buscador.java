package com.company;

import java.util.ArrayDeque;
import java.util.ArrayList;

public abstract class Buscador
{

    public boolean compararEstados(Estado estadofinal, Estado estadoActual)
    {
        boolean isEqual=true;
        int i=0;
        while(i<3)
        {
            int j=0;
            while(j<3)
            {
                if(!estadoActual.getElemntoMatriz(i,j).equalsIgnoreCase(estadofinal.getElemntoMatriz(i,j)))
                {
                    isEqual=false;
                    j=3;
                    i=3;
                }
                j++;
            }
            i++;
        }
        return isEqual;
    }

    public String [][] copiarEstado(Estado actual)
    {
        String[][] nuevo = new String[3][3];
        int i=0;
        while(i<3)
        {
            int j=0;
            while (j<3)
            {
                nuevo[i][j] = actual.getElemntoMatriz(i,j);
                j++;
            }
            i++;
        }
        return nuevo;
    }
}
