package com.company;

import java.util.ArrayDeque;

public class Trasformador
{
    private ArrayDeque<Expresion>expresiones;

    public Trasformador(String file)
    {
        this.expresiones = new ArrayDeque<>();
        Lector lector= new Lector();
        ArrayDeque<String>contenedor = lector.leerCadenas(file);
        ArrayDeque<String>nuevoContenedor =this.procesarGuion(contenedor);

        for (String string:nuevoContenedor)
        {
            Expresion nuevaExpesion = new Expresion(string);
            this.expresiones.add(nuevaExpesion);
        }


    }

    public ArrayDeque<String> procesarGuion(ArrayDeque<String> contendor)
    {
        ArrayDeque<String> nuevoContenedor= new ArrayDeque<>();
        StringBuilder stringB = new StringBuilder("");
        for (String s:contendor)
        {
            if(s.contains("-"))
            {
                String nuevoString = s.replace("-","");
                stringB.append(nuevoString);
            }
            else if (stringB.length()>1)
            {
                stringB.append(s);
                String aux = stringB.toString();
                stringB = new StringBuilder("");
                nuevoContenedor.add(aux);

            }
            else
            {
                nuevoContenedor.add(s);
            }
        }

        return nuevoContenedor;
    }

    public void mostrar (ArrayDeque<String> arreglo)
    {
        for (String s:arreglo)
        {
            System.out.println(s);
        }
    }
}
