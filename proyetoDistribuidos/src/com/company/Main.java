package com.company;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class Main {

    // public static void main(String[] args)
    // {

    // int [][] matriz= new int [4][4];
    // Contenedor micontenedor = new Contenedor(matriz);
    // int [][] matrizprueba=
    // {{54,130,206,224},
    // {166,167,210,114},
    // {27,121,13,213},
    // {66,0,78,18}};
    // int iterativo = 1;
    // int opcion = 5;
    // int mayorMenor = 1;
    // if(iterativo == 0)
    // {
    // if (opcion == 0)
    // {
    // FiltradorMas nuevoFiltrador = new FiltradorMas(matrizprueba,0,0,
    // micontenedor, mayorMenor);
    // nuevoFiltrador.filtarIterativoMas();
    // }
    // else if (opcion ==1 )
    // {
    // FiltradorX nuevoFiltadorX = new FiltradorX(matrizprueba,0,0, micontenedor,
    // mayorMenor);
    // nuevoFiltadorX.filtarIterativoX();
    // }
    // else if( opcion == 2)
    // {
    // FiltadorEsquinaSuperior nuevoFiltadorEsquinaSuperior = new
    // FiltadorEsquinaSuperior(matrizprueba,0,0, micontenedor, mayorMenor);
    // nuevoFiltadorEsquinaSuperior.filtarIterativoESquinaSuperior();
    // }
    // else if( opcion == 3)
    // {
    // FiltradorEsquinaInferior nuevoFiltadorEsquinaInferior = new
    // FiltradorEsquinaInferior(matrizprueba,0,0, micontenedor, mayorMenor);
    // nuevoFiltadorEsquinaInferior.filtarIterativoEsquinaInferior();
    // }
    // else if(opcion == 4)
    // {
    // FiltradorPilar nuevoFiltadorPolar = new FiltradorPilar(matrizprueba,0,0,
    // micontenedor, mayorMenor);
    // nuevoFiltadorPolar.filtarIterativoPilar();
    // }
    // else if(opcion == 5)
    // {
    // FiltradorHorizontal nuevoFiltadorPolar = new
    // FiltradorHorizontal(matrizprueba,0,0, micontenedor, mayorMenor);
    // nuevoFiltadorPolar.filtarIterativoHorizontal();
    // }

    // }
    // else
    // {
    // ArrayList<Thread> hilos = new ArrayList<>();
    // if (opcion == 0)
    // {
    // for (int i = 0; i < 4; i++)
    // {
    // FiltradorMas nuevoFiltrador = new FiltradorMas(matrizprueba,i,i,
    // micontenedor, mayorMenor);
    // Thread nuevoHilo = new Thread(nuevoFiltrador);
    // hilos.add(nuevoHilo);
    // }

    // for (Thread a : hilos)
    // {
    // a.start();
    // }

    // for (Thread a: hilos)
    // {
    // try {
    // a.join();
    // }
    // catch (InterruptedException e )
    // {
    // e.printStackTrace();
    // }

    // }
    // }
    // else if(opcion == 1)
    // {
    // for (int i = 0; i < 4; i++)
    // {
    // FiltradorX nuevoFiltrador = new FiltradorX(matrizprueba,i,i, micontenedor,
    // mayorMenor);
    // Thread nuevoHilo = new Thread(nuevoFiltrador);
    // hilos.add(nuevoHilo);
    // }

    // for (Thread a : hilos)
    // {
    // a.start();
    // }

    // for (Thread a: hilos)
    // {
    // try {
    // a.join();
    // }
    // catch (InterruptedException e )
    // {
    // e.printStackTrace();
    // }

    // }
    // }
    // else if(opcion == 2)
    // {
    // for (int i = 0; i < 4; i++)
    // {
    // FiltadorEsquinaSuperior nuevoFiltrador = new
    // FiltadorEsquinaSuperior(matrizprueba,i,i, micontenedor, mayorMenor);
    // Thread nuevoHilo = new Thread(nuevoFiltrador);
    // hilos.add(nuevoHilo);
    // }

    // for (Thread a : hilos)
    // {
    // a.start();
    // }

    // for (Thread a: hilos)
    // {
    // try {
    // a.join();
    // }
    // catch (InterruptedException e )
    // {
    // e.printStackTrace();
    // }

    // }
    // }
    // else if(opcion == 3)
    // {
    // for (int i = 0; i < 4; i++)
    // {
    // FiltradorEsquinaInferior nuevoFiltrador = new
    // FiltradorEsquinaInferior(matrizprueba,i,i, micontenedor, mayorMenor);
    // Thread nuevoHilo = new Thread(nuevoFiltrador);
    // hilos.add(nuevoHilo);
    // }

    // for (Thread a : hilos)
    // {
    // a.start();
    // }

    // for (Thread a: hilos)
    // {
    // try {
    // a.join();
    // }
    // catch (InterruptedException e )
    // {
    // e.printStackTrace();
    // }

    // }
    // }
    // else if(opcion == 4)
    // {
    // for (int i = 0; i < 4; i++)
    // {
    // FiltradorPilar nuevoFiltrador = new FiltradorPilar(matrizprueba,i,i,
    // micontenedor, mayorMenor);
    // Thread nuevoHilo = new Thread(nuevoFiltrador);
    // hilos.add(nuevoHilo);
    // }

    // for (Thread a : hilos)
    // {
    // a.start();
    // }

    // for (Thread a: hilos)
    // {
    // try {
    // a.join();
    // }
    // catch (InterruptedException e )
    // {
    // e.printStackTrace();
    // }

    // }
    // }
    // else if(opcion == 5)
    // {
    // for (int i = 0; i < 4; i++)
    // {
    // FiltradorHorizontal nuevoFiltrador = new
    // FiltradorHorizontal(matrizprueba,i,i, micontenedor, mayorMenor);
    // Thread nuevoHilo = new Thread(nuevoFiltrador);
    // hilos.add(nuevoHilo);
    // }

    // for (Thread a : hilos)
    // {
    // a.start();
    // }

    // for (Thread a: hilos)
    // {
    // try {
    // a.join();
    // }
    // catch (InterruptedException e )
    // {
    // e.printStackTrace();
    // }

    // }
    // }
    // }

    // for (int j = 0; j < matriz.length; j++)
    // {
    // for (int k = 0; k < matriz.length; k++)
    // {
    // System.out.print(matriz[j][k] +", ");
    // }
    // System.out.println(" ");
    // }
    // // write your code here
    // }

    public static void main(String[] args) throws IOException {
        
        try {
            LeerImagen leer = new LeerImagen();
            leer.matrizImagen("C:\\Users\\felip\\Documents\\GitHub\\pruebas-de-codigo-y-algoritmos\\proyetoDistribuidos\\src\\com\\company\\dilatacion.pgm");
            //BufferedImage img = ImageIO.read(new File("anime.jpg"));
            //leer.matrizImagen(img);
            //System.out.println(img);
            //ImageIO.write(img,"png",new File("C:/lawea.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
