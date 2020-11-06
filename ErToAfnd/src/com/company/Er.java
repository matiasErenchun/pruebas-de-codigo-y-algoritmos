package com.company;

import javax.swing.*;
import java.util.ArrayList;

public class Er
{
    private Nodo nodoInicial;
    private Nodo nodoFinal;
    private ArrayList<Nodo> nodos;

    public Er(Nodo nodoInicial, Nodo nodoFinal)
    {
        this.nodoInicial = nodoInicial;
        this.nodoFinal = nodoFinal;
        this.nodos = new ArrayList<>();
    }

    public void setNodoInicial(Nodo nodoInicial)
    {
        this.nodoInicial = nodoInicial;
    }

    public void setNodoFinal(Nodo nuevoNodoFinal)
    {
        nodoFinal = nuevoNodoFinal;
    }

    public boolean addNodo(Nodo nodo)
    {
        return this.nodos.add(nodo);
    }

    public Nodo getNodoInicial()
    {
        return nodoInicial;
    }

    public Nodo getNodoFinal()
    {
        return this.nodoFinal;
    }

    public int sizeNodos()
    {
        return this.nodos.size();
    }

    public ArrayList<Nodo> getNodos()
    {
        return this.nodos;
    }

    public int cargarNodos( ArrayList<Nodo> nodos)
    {
        int i=0;
        for (Nodo nodo: nodos)
        {
            this.nodos.add(nodo);
            i++;
        }
        return i;
    }

    public void mostrarNodos()
    {

    }

    public void mostrarEr()
    {
        System.out.println("AFND M:");
        System.out.print("K={");
        for (int i = 0; i <this.nodos.size() ; i++)
        {
            if(i==this.nodos.size()-1)
            {
                System.out.print(this.nodos.get(i).getId());
            }
            else
            {
                System.out.print(this.nodos.get(i).getId() + ",");
            }
        }
        System.out.println("}");
        System.out.println("Delta:");
        for (Nodo nodo: this.nodos)
        {
            nodo.mostrarTansicion();
        }
        System.out.println("S="+this.nodoInicial.getId());
        System.out.println("F={"+this.nodoFinal.getId()+"}");
    }
}
