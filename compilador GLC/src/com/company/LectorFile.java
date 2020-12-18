package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class LectorFile
{
    public LectorFile()
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

                for (int i = 0; i < aux.length(); i++)
                {
                    Character auxChar = aux.charAt(i);
                    if(aux.length()>1 && (auxChar=='>'|| auxChar=='<' || auxChar=='=' || auxChar=='!') )
                    {
                        Character auxChar2 = aux.charAt(i+1);
                        if(auxChar2 == '=')
                        {
                            builder=this.validarAgregarBuilder(builder,contenedor);
                            builder.append(auxChar);
                            builder.append(auxChar2);
                            builder=this.validarAgregarBuilder(builder,contenedor);
                            i++;
                        }
                        else
                        {
                            builder=this.validarAgregarBuilder(builder,contenedor);
                            contenedor.add(auxChar.toString());
                        }
                    }
                    else if(auxChar == '%' || auxChar == '/' || auxChar == '*' || auxChar == '+' || auxChar == '-' || auxChar=='(' || auxChar==')')
                    {
                        builder=this.validarAgregarBuilder(builder,contenedor);
                        contenedor.add(auxChar.toString());

                    }
                    else if (auxChar == ';')
                    {
                        builder=this.validarAgregarBuilder(builder,contenedor);
                    }
                    else if(auxChar == '$')
                    {
                        builder=this.validarAgregarBuilder(builder,contenedor);
                        builder.append(auxChar.toString());
                    }
                    else
                    {
                        builder.append(auxChar);
                    }

                }
                builder = this.validarAgregarBuilder(builder,contenedor);

            }
            cadenasFinales.add(contenedor);
            contenedor= new ArrayList<>();
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

    public StringBuilder validarAgregarBuilder(StringBuilder builder, ArrayList<String> contenedor)
    {
        if(builder.length()>0)
        {
            contenedor.add(builder.toString());
            builder = new StringBuilder();
        }
        return builder;
    }

}