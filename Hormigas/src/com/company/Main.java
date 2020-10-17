package com.company;

public class Main
{

    public static void main(String[] args)
    {
        String url="D:\\repo-git-local-2\\pruebas-de-codigo-y-algoritmos\\Hormigas\\src\\com\\company\\a280.txt";
        int cantidadHormigas=10000;
        Double factorDeIncrementoHromonas= new Double(5.8);
        Simulador s= new Simulador(url,cantidadHormigas,factorDeIncrementoHromonas);
    }
}
