package com.company;

import java.util.ArrayList;

public class Nodo
{
    private int id;
    private int coordenadaX;
    private int coordenadaY;

    public Nodo(int id, int coordenadaX, int coordenadaY)
    {
        this.id = id;
        this.coordenadaX = coordenadaX;
        this.coordenadaY = coordenadaY;
    }

    public int getId()
    {
        return id;
    }

    public int getCoordenadaX()
    {
        return coordenadaX;
    }

    public int getCoordenadaY()
    {
        return coordenadaY;
    }

    public void printt()
    {
        System.out.println(" hola soy el nodo: "+this.id+" coordenad X:"+this.getCoordenadaX()+" coordenada Y:"+coordenadaY);
    }
}
