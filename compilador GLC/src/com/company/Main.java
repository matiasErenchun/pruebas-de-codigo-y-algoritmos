package com.company;

import java.util.ArrayDeque;
import java.util.ArrayList;

public class Main
{

    public static void main(String[] args)
    {

	    String file = "D:\\repo-git-local-2\\pruebas-de-codigo-y-algoritmos\\compilador GLC\\src\\com\\company\\texto.txt";
        LectorFile miLectorFile = new LectorFile();
        ArrayDeque<String> cadenas = miLectorFile.leerCadenas(file);
        ArrayList<ArrayList<String>> lineas = miLectorFile.procesarCadenas(cadenas);
        for (ArrayList<String> linea: lineas)
        {
            for (String token: linea)
            {
                System.out.print(token+" |---| ");
            }
            System.out.print("\n");
        }
        Interprete miInterprete = new Interprete(lineas);
        miInterprete.mapearIfElse(0);
        miInterprete.interpretar();

        String tokenActual = "12524522";
        //if(tokenActual.matches("^[$][a-zA-Z0-9]+$"))
        if(tokenActual.matches("^[-]{0,1}[0-9]+$"))
        {
            System.out.println("sicumple:"+tokenActual);
        }
        else
        {
            System.out.println("no cumple ");
        }
        System.out.println("p:"+(12%7/5*5));



    }
}
