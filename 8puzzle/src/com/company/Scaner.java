package com.company;

import java.util.Scanner;

public class Scaner
{
    private Scanner scaner;

    public Scaner()
    {
        this.scaner = new Scanner(System.in);
    }

    public Integer leerNumero(String mensaje )
    {
        System.out.println(mensaje);
        System.out.print("> ");
        String valor=this.scaner.nextLine();
        while(!valor.matches("^[0-9]+$"))
        {
            System.out.println("\nValor Erroneo, Ingrese un Valor según lo Requerido [Número]\n");
            System.out.println(mensaje);
            System.out.print("> ");
            valor=this.scaner.nextLine();


        }
        return Integer.parseInt(valor);
    }

    public Integer leerNumeroPositivoSegunValor(String mensaje,Integer i)
    {
        System.out.println(mensaje);
        System.out.print("> ");
        String valor=this.scaner.nextLine();
        boolean segir=true;
        while(segir)
        {
            if(!valor.matches("^[0-9]+$"))
            {
                System.out.println("Valor Erroneo, Ingrese un Valor según lo Requerido [Número]\n");
                System.out.println(mensaje);
                System.out.print("> ");
                valor=this.scaner.nextLine();
            }
            if(Integer.parseInt(valor)<i)
            {
                System.out.println("Valor Erroneo, Ingrese un Valor según lo Requerido [Número] menor o igual que: "+i+"\n");
                valor=this.scaner.nextLine();
            }
            segir=false;
        }
        return Integer.parseInt(valor);
    }



}
