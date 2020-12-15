package com.company;

import java.math.BigInteger;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;

public class Interprete
{
    private HashMap<String,Integer>priorityMap;
    private ArrayList<ArrayList<String>> lineas;
    private LectorInputs miLector;
    private HashMap<String,BigInteger> tablaVariables;

    public Interprete(ArrayList<ArrayList<String>>lineas)
    {
        this.priorityMap=new HashMap<>();
        this.miLector = new LectorInputs();
        this.lineas = lineas;
        this.cargarPriorityMap();
        this.tablaVariables = new HashMap<>();
    }

    public boolean interpretar()
    {
        boolean salida=true;
        for (int i = 0; i < this.lineas.size(); i++)
        {
            ArrayList<String > lineaActual =this.lineas.get(i);
            int indexLineaActual =0;
            String tokenActual=lineaActual.get(indexLineaActual);
            if (tokenActual.equalsIgnoreCase("read"))
            {
                String  nextToken = lineaActual.get(indexLineaActual+1);
                if (nextToken.matches("^[$][a-zA-Z0-9]+$"))
                {
                    BigInteger valor = miLector.readBigInteger();
                    this.tablaVariables.put(nextToken,valor);
                    System.out.println(tokenActual+": "+nextToken+"="+this.tablaVariables.get(nextToken));
                }
                else
                {
                    i+=lineas.size();
                    System.out.println("error al leer, el resultado no se asigna o guarda en una variable ");
                }
            }
            else if(tokenActual.equalsIgnoreCase("write"))
            {
                if(indexLineaActual+1<lineaActual.size())
                {
                    ArrayDeque<String>contenedor = this.extraerExpresionNumerica(lineaActual,indexLineaActual+1);
                    ArrayDeque<String>contenedor2=this.infijoToPrefijo(contenedor);
                    for (String s: contenedor2)
                    {
                        System.out.print(s+" ");
                    }
                    System.out.println(" ");
                    System.out.println(this.resolverExpresionNumerica(contenedor2));
                }

            }
            else if(tokenActual.equalsIgnoreCase("if"))
            {

            }
            else if(tokenActual.equalsIgnoreCase("while"))
            {

            }
            else
            {
                if(tokenActual.matches("^[$][a-zA-Z0-9]+$"))
                {

                }
                else
                {

                }
            }
        }
        return salida;
    }

    public ArrayDeque<String> extraerExpresionNumerica(ArrayList<String> lineaActual, int index)
    {
        ArrayDeque<String> salida = new ArrayDeque<>();
        while(index<lineaActual.size())
        {
            salida.add(lineaActual.get(index));
            index++;
        }
        return salida;
    }

    /*
    esto podria ser requerido o no
    public boolean seguirBuscandoExpresion(String tokenActual)
    {
        if(tokenActual.equalsIgnoreCase("="))
        {
            return false;
        }
        else if()
        {

        }
    }

     */

    public BigInteger resolverExpresionNumerica(ArrayDeque<String>tokens)
    {
        BigInteger salida= new BigInteger("0");
        ArrayDeque<BigInteger>pilaResultados = new ArrayDeque<>();
        BigInteger valor1;
        BigInteger valor2;
        BigInteger valor3;
        while (!tokens.isEmpty())
        {
            String tokenActual = tokens.pollFirst();

            //si es una variable
            if(tokenActual.matches("^[$][a-zA-Z0-9]+$"))
            {
                BigInteger aux=this.tablaVariables.get(tokenActual);
                pilaResultados.addFirst(aux);
            }
            //si es un numero
            else if(tokenActual.matches("^[-]{0,1}[0-9]+$"))
            {
                BigInteger aux=new BigInteger(tokenActual);
                pilaResultados.addFirst(aux);
            }
            else if(tokenActual.equalsIgnoreCase("+"))
            {
                valor1=pilaResultados.pollFirst();
                valor2=pilaResultados.pollFirst();
                if(valor1!=null && valor2!= null)
                {
                    valor3 = valor2.add(valor1);
                    pilaResultados.addFirst(valor3);
                }
                else
                {
                    System.out.println("error de en la sintaxis");
                }
            }
            else if(tokenActual.equalsIgnoreCase("-"))
            {
                valor1=pilaResultados.pollFirst();
                valor2=pilaResultados.pollFirst();
                if(valor1!=null && valor2!= null)
                {
                    valor3 = valor2.subtract(valor1);
                    pilaResultados.addFirst(valor3);
                }
                else
                {
                    System.out.println("error de en la sintaxis");
                }
            }
            else if(tokenActual.equalsIgnoreCase("*"))
            {
                valor1=pilaResultados.pollFirst();
                valor2=pilaResultados.pollFirst();
                if(valor1!=null && valor2!= null)
                {
                    valor3 = valor2.multiply(valor1);
                    pilaResultados.addFirst(valor3);
                }
                else
                {
                    System.out.println("error de en la sintaxis");
                }
            }
            else if(tokenActual.equalsIgnoreCase("/"))
            {
                valor1=pilaResultados.pollFirst();
                valor2=pilaResultados.pollFirst();
                if(valor1!=null && valor2!= null)
                {
                    valor3 = valor2.divide(valor1);
                    pilaResultados.addFirst(valor3);
                }
                else
                {
                    System.out.println("error de en la sintaxis");
                }
            }
            else if(tokenActual.equalsIgnoreCase("%"))
            {
                valor1=pilaResultados.pollFirst();
                valor2=pilaResultados.pollFirst();
                if(valor1!=null && valor2!= null)
                {
                    valor3 = valor2.remainder(valor1);
                    pilaResultados.addFirst(valor3);
                }
                else
                {
                    System.out.println("error de en la sintaxis");
                }
            }
            else
            {
                System.out.println("error de en la sintaxis");
            }
        }
        salida = pilaResultados.peekFirst();
        return salida;
    }

    public boolean resolverExpresionBooleana(ArrayList<String>expresion)
    {
        boolean salida = true;
        return salida;
    }


    public ArrayDeque<String> infijoToPrefijo(ArrayDeque<String> misTokens)
    {
        ArrayDeque<String>salida = new ArrayDeque<>();
        ArrayDeque<String>auxOperadores = new ArrayDeque<>();
        while (!misTokens.isEmpty())
        {
            String elemento = misTokens.pollFirst();
            if(elemento.matches("^[$a-zA-Z0-9]+$"))
            {
                salida.add(elemento);
            }
            else if(elemento.equalsIgnoreCase("("))
            {
                auxOperadores.addFirst(elemento);
            }
            else if(elemento.equalsIgnoreCase(")"))
            {
                while(!auxOperadores.isEmpty() &&  !auxOperadores.peekFirst().equalsIgnoreCase("("))
                {

                    String operador = auxOperadores.pollFirst();
                    salida.add(operador);
                }
                if(!auxOperadores.isEmpty())
                {
                    auxOperadores.pollFirst();
                }
            }
            else
            {
                boolean continuar = true;
                while(!auxOperadores.isEmpty() && !auxOperadores.peekFirst().equalsIgnoreCase("(") && continuar)
                {
                    if(pPrioriE(auxOperadores.getFirst(),elemento))
                    {
                        String aux= auxOperadores.pollFirst();
                        salida.add(aux);
                    }
                    else
                    {
                        continuar =false;
                    }
                }
                auxOperadores.addFirst(elemento);
            }
        }

        while (!auxOperadores.isEmpty())
        {
            salida.add(auxOperadores.pollFirst());
        }

        return salida;
    }

    public boolean pPrioriE(String p,String e )
    {
        if(this.priorityMap.get(p)>=this.priorityMap.get(e))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    private void cargarPriorityMap()
    {
        this.priorityMap.put("*",3);
        this.priorityMap.put("/",3);
        this.priorityMap.put("%",3);
        this.priorityMap.put("+",2);
        this.priorityMap.put("-",2);


    }
}
