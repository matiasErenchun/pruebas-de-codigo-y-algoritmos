package com.company;

import java.util.ArrayList;

public class Nodo
{
    private String id;
    private ArrayList<Transicion>transiciones;

    public Nodo(String id)
    {
        this.id = id;
        this.transiciones = new ArrayList<>();
    }

    public boolean addTrancision(Transicion transicion)
    {
        return this.transiciones.add(transicion);
    }

    public String getId()
    {
        return this.id;
    }

    public void mostrarTansicion()
    {
        for (Transicion t:this.transiciones)
        {
            System.out.println("("+t.getNodoSalida()+","+t.getCaracter()+","+t.getNodollegada().getId()+")");
        }
    }



}
