package com.company;

import java.util.ArrayDeque;

public class Expresion
{
    private ArrayDeque<String>tokens;

    public Expresion( String expresion)
    {
        this.tokens = new ArrayDeque<>();
        this.procesarString(expresion);
    }

    public void procesarString(String string)
    {
        char [] caracteres= string.toCharArray();
        for (char c: caracteres)
        {
            this.tokens.add(Character.toString(c));
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


}
