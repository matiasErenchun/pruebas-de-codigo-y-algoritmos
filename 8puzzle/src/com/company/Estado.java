package com.company;

public class Estado
{
    private Estado estadoAnterior;
    private int ispace;
    private int jspace;
    private String [][] matrisEstado;
    private int nivel;
    private int id;

    public Estado(int ispace, int jspace, String [][] hola,int nivel,int id)
    {
        this.ispace = ispace;
        this.jspace = jspace;
        this.matrisEstado = hola;
        this.nivel =nivel;
        this.id = id;
    }

    public Estado getEstadoAnterior() throws Exception
    {
        return this.estadoAnterior;
    }

    public void setEstadoAnterior(Estado estadoAnterior)
    {
        this.estadoAnterior = estadoAnterior;
    }

    public String getElemntoMatriz(int i, int j)
    {
        return this.matrisEstado [i][j];
    }

    public int getIspace()
    {
        return this.ispace;
    }

    public int getJspace()
    {
        return this.jspace;
    }

    public void print()
    {
        System.out.println("nivel del arbol en que se encuentra este estado:"+this.nivel);
        System.out.println("id estado padre:"+this.estadoAnterior.getId());
        System.out.println("id estado actual:"+this.getId());
        for (int i = 0; i < 3; i++)
        {
            System.out.print("[");
            for (int j = 0; j <3 ; j++) {
                System.out.print(this.matrisEstado[i][j]);
            }
            System.out.println("]");
        }
    }

    public int getNivel()
    {
        return this.nivel;
    }

    public int getId()
    {
        return id;
    }
}
