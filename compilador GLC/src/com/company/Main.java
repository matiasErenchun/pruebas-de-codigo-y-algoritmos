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
        Interprete miInterprete = new Interprete(lineas);
        miInterprete.mapearIfElse(0);
        miInterprete.interpretar();


        String tokenActual = "!=";
        //if(tokenActual.matches("^[-]{0,1}[$][a-zA-Z0-9]+$"))
        //if(tokenActual.matches("^[-]{0,1}[0-9]+$"))
        //if(tokenActual.matches("([+]|[-]|[*]|[/]|[%])"))
        //if(tokenActual.matches("[-]{0,1}[$][a-zA-Z0-9]|[-]{0,1}[0-9]|[+]|[-]|[*]|[/]|[%]"))
        //if(tokenActual.matches("[>][=]{0,1}|[<][=]{0,1}|[=]{2}|[!][=]"))



    }
}
