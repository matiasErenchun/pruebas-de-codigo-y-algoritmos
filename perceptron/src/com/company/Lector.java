package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Lector
{
    private Scanner lector;

    public Lector()
    {
        this.lector = new Scanner(System.in);
    }

    public ArrayList<ArrayList<String>> leerCadenas(String file)
    {
        ArrayList<ArrayList<String>>cadenas = new ArrayList<>();

        try
        {
            String cadena;
            FileReader f = new FileReader(file);
            BufferedReader b = new BufferedReader(f);
            while ((cadena = b.readLine()) != null)
            {
                StringTokenizer tokenizar = new StringTokenizer(cadena, " ");
                ArrayList<String>auxCadena = new ArrayList<>();
                while(tokenizar.hasMoreTokens())
                {
                    String aux= tokenizar.nextToken();
                    auxCadena.add(aux);
                }
                cadenas.add(auxCadena);
            }
        }
        catch (Exception e)
        {
            System.out.println("error durante la lectura");
            e.printStackTrace();
        }

        return cadenas;
    }
}
