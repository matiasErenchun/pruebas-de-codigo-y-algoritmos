package com.company;

import java.util.ArrayDeque;
import java.util.ArrayList;

public class Main
{

    public static void main(String[] args)
    {
	    String file = "D:\\repo-git-local-2\\pruebas-de-codigo-y-algoritmos\\compilador GLC\\src\\com\\company\\texto.txt";
        Lector miLector = new Lector();
        ArrayDeque<String> cadenas = miLector.leerCadenas(file);
        ArrayList<ArrayList<String>> tokensCadenas = miLector.procesarCadenas(cadenas);
        for (ArrayList<String> tokensCadena: tokensCadenas) {
            for (String token: tokensCadena)
            {
                System.out.print(token+" |--| ");
            }
            System.out.print("\n");
        }
    }
}
