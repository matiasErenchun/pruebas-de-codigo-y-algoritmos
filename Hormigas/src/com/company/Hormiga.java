package com.company;

import java.util.ArrayList;
import java.util.HashMap;

public class Hormiga
{
    private int id;
    private HashMap<Integer,Boolean> visitados;
    private Double peso;
    private ArrayList<Integer> camino;

    public Hormiga(int id)
    {
        this.id = id;
        this.visitados = new HashMap<>();
        this.peso = new Double(0);
        this.camino = new ArrayList<>();
    }

    public boolean AddIdNodoToCamino(int idNodo)
    {
        return this.camino.add(idNodo);
    }

    public boolean MarckNodoWithVisited(Integer idNodo)
    {
        return this.visitados.putIfAbsent(idNodo,true);
    }

    public boolean visiteYaEsteNodo(Integer idNodo)
    {
        return this.visitados.containsKey(idNodo);
    }

    public Double addPeso(Double pesoArista)
    {
       return this.peso+=pesoArista;
    }

    public int visitadosTotales()
    {
        return this.visitados.size();
    }

}
