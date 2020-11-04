package com.company;

import java.util.ArrayList;

public class Main
{

    public static void main(String[] args)
    {
        Scaner miScaner= new Scaner();
        BuscadorAnchura miBuscadorAnchura = new BuscadorAnchura();
        BuscadorProfundidad miBucadorProfundidad= new BuscadorProfundidad();

        String [][] inicio= {{"8","1","3"}
                            ,{"6","4"," "}
                            ,{"2","7","5"}};

        String [][] fin={{"1","2","3"}
                        ,{"8"," ","4"}
                        ,{"7","6","5"}};

        String [][] raiz={{" "," "," "}
                         ,{" "," "," "}
                         ,{" "," "," "}};



        Estado estadoRaiz = new Estado(-1,-1,raiz,-1,-1);
        Estado estadoInicial = new Estado( 1,2,inicio,0,0);
        estadoInicial.setEstadoAnterior(estadoRaiz);
        Estado estadoFinal = new Estado(1,1,fin,0,0);

        int number=10;
        while (number>0)
        {
            System.out.println(" ingrese el numero entero correspondiente a altipo de busqueda que desea realizar ");
            System.out.println(" 1 si desea realizar busqueda en anchura");
            System.out.println(" 2 si desea realizar busqueda en profundidad");
            System.out.println( "0 si desea terminar el programa ");
            number = miScaner.leerNumeroPositivoSegunValor("ingrese su opcion",-1);
            if(number==1)
            {
                miBuscadorAnchura.buscar(estadoInicial,estadoFinal);
            }
            else if(number==2)
            {
                miBucadorProfundidad.Buscar(estadoInicial,estadoFinal);
            }
            else if(number==0)
            {
                System.out.println("el programa finalizara a continuacion, que tenga un lindo dia ");
            }
            else
            {
                System.out.println("opcion no valida ingrese una de las opciones existentes");
            }
        }
    }
}
