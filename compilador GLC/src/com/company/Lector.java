package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
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

    public ArrayList<ArrayList<String>> procesarCadenas(ArrayDeque<String> cadenas)
    {
        ArrayList<ArrayList<String>>cadenasFinales = new ArrayList<>();
        ArrayList<String>contenedor = new ArrayList<>();
        for (String string:cadenas)
        {
            ArrayList<String>tokenSinSpacios = this.tokenizarPorEspacios(string);
            StringBuilder builder= new StringBuilder();

            for (String aux: tokenSinSpacios)
            {
                boolean div=false;
                for (int i = 0; i < aux.length(); i++)
                {
                    Character auxChar = aux.charAt(i);
                    if(auxChar == '%' || auxChar == '/' || auxChar == '*' || auxChar == '+' || auxChar == '-' || auxChar=='=')
                    {
                        contenedor.add(builder.toString());
                        contenedor.add(auxChar.toString());
                        builder = new StringBuilder();
                        div=true;
                    }
                    else if (auxChar == ';')
                    {
                        contenedor.add(builder.toString());
                        cadenasFinales.add(contenedor);
                        contenedor= new ArrayList<>();
                        builder = new StringBuilder();
                        div=false;
                    }
                    else
                    {
                        builder.append(auxChar);
                    }

                }
                if(div==true)
                {
                    contenedor.add(builder.toString());
                }

            }
        }

        return cadenasFinales;

    }

    public ArrayList<String> tokenizarPorEspacios (String cadena )
    {
        ArrayList<String>salida = new ArrayList<>();
        StringTokenizer tokens = new StringTokenizer(cadena," ");
        while(tokens.hasMoreTokens())
        {
            salida.add(tokens.nextToken());
        }
        return salida;
    }

}