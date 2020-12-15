package com.company;

import java.math.BigInteger;
import java.util.Scanner;

public class LectorInputs
{
    private Scanner miScanner;

    public LectorInputs()
    {
        this.miScanner = new Scanner(System.in);
    }

    public BigInteger readBigInteger()
    {
        System.out.println("ingrese un entero");
        String input = miScanner.nextLine();
        while(!input.matches("^[-]{0,1}[0-9]+$"))
        {
            System.out.println("valor ingresado, no es un entero");
            System.out.println("ingrese un entero");
            input = miScanner.nextLine();

        }
        BigInteger salida = new BigInteger(input);
        return salida;
    }
}
