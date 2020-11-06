package com.company;

import java.util.ArrayDeque;
import java.util.HashMap;

public class Expresion
{
    private ArrayDeque<String>tokens;
    private HashMap<String,Integer>priorityMap;

    public Expresion( String expresion)
    {
        this.priorityMap = new HashMap<>();
        this.cargarPriorityMap();
        this.tokens = new ArrayDeque<>();
        this.procesarString(expresion);
        this.infijoToPrefijo(this.tokens);
        //falta guardar la salida 
    }

    public void procesarString(String string)
    {
        char [] caracteres= string.toCharArray();
        StringBuilder token = new StringBuilder("");
        for (char c: caracteres)
        {
            if(c=='|'|| c=='.' || c=='(' || c==')' || c=='~'|| c=='*')
            {
                if(token.length()==0)
                {
                    this.tokens.add(Character.toString(c));
                }
                else
                {
                    this.tokens.add(token.toString());
                    this.tokens.add(Character.toString(c));
                    token = new StringBuilder("");
                }
            }
            else
            {
                token.append(c);
            }
        }
        if(token.length()>0)
        {
            tokens.add(token.toString());
        }
    }

    public void mostrar()
    {
        System.out.println("\n");
        for (String s:this.tokens)
        {
            System.out.print(s+"-");
        }
        System.out.println("\n");
    }

    public ArrayDeque<String> infijoToPrefijo(ArrayDeque<String> misTokens)
    {
        ArrayDeque<String>salida = new ArrayDeque<>();
        ArrayDeque<String>auxOperadores = new ArrayDeque<>();
        while (!misTokens.isEmpty())
        {
            String elemento = misTokens.pollFirst();
            if(elemento.matches("^[a-zA-Z0-9]+$"))
            {
                salida.add(elemento);
            }
            else if(elemento.equalsIgnoreCase("("))
            {
                auxOperadores.addFirst(elemento);
                System.out.println("hola2");
            }
            else if(elemento.equalsIgnoreCase(")"))
            {
                System.out.println("num aux"+auxOperadores.size());
                while(!auxOperadores.isEmpty() &&  !auxOperadores.peekFirst().equalsIgnoreCase("("))
                {

                    String operador = auxOperadores.pollFirst();
                    System.out.println("operador"+operador);
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
            System.out.println("salida :D");
            salida.add(auxOperadores.pollFirst());
        }

        for (String s:salida)
        {
            System.out.print(s+"-");
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
        this.priorityMap.put(".",2);
        this.priorityMap.put("|",1);

    }


}
