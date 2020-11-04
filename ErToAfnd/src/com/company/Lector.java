package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Lector
{
    public Lector()
    {

    }

    public ArrayDeque<String> leerCadenas(String file)
    {
        ArrayDeque<String>cadenas = new ArrayDeque<>();

        try
        {
            String cadena;
            FileReader f = new FileReader(file);
            BufferedReader b = new BufferedReader(f);
            while ((cadena = b.readLine()) != null)
            {
                cadenas.add(cadena);
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
