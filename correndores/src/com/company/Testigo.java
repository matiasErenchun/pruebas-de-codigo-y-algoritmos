package com.company;

public class Testigo
{
    private int turno;
    private int equipo;

    public Testigo(int equipo)
    {
        this.turno = 1;
        this.equipo = equipo;
    }

    public int getTurno()
    {
        return this.turno;
    }
    public int getEquipo()
    {
        return equipo;
    }

    public void setTurno(int turno) {
        this.turno = turno;
    }
}
