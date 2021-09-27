package com.company;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class LeerImagen {

    private String nombreArchivo;
    private int ancho = 0;
    private int alto = 0;
    private int blancoAbsoluto;
    private int[][] pixeles;
    
    public LeerImagen(){}

    public void matrizImagen(String nombreArchivo) throws IOException {
        this.nombreArchivo = nombreArchivo;
        final FileInputStream f = new FileInputStream(nombreArchivo);
        final BufferedReader br = new BufferedReader(new InputStreamReader(f));
        if (!br.readLine().equals("P5")) {
            System.err.println("Image file is not P5.");
            System.exit(-1);
        }

        String line;
        do {
            line = br.readLine();
        } while (line.startsWith("#"));

        Scanner sc = new Scanner(line);
        this.ancho = sc.nextInt();
        this.alto = sc.nextInt();
        this.blancoAbsoluto = Integer.parseInt(br.readLine());


        int[][] originalImage = new int[alto + 2][ancho + 2];
        this.pixeles = new int[alto][ancho];
        for (int i = 0; i < alto; i++){
            for (int j = 0; j < ancho; j++){
                int numero = originalImage[i + 1][j + 1] = br.read();
                this.pixeles[i][j] = numero;
            }
            System.out.println(" ");
        }

        f.close();
        filtros();
    }

    public void imprimir_pixeles(){
        for (int i = 0; i < pixeles.length-2; i++) {
            for (int j = 0; j < pixeles.length - 2; j++) {
                System.out.print(this.pixeles[i][j] + "|");
            }
            System.out.println(" ");
        }
    }

    public void filtros(){
        int [][] matriz= new int [alto][ancho]; // se modifica el tamaño con el tamaño de la matriz pasada por parametros.
        Contenedor micontenedor = new Contenedor(matriz);
        //int [][] matrizprueba = this.pixeles;
        int iterativo = 0;
        int opcion = 5;
        int mayorMenor = 1; // buscar el numero mayor o buscar el numero menor. 0 - 1
        if(iterativo == 0)
        {
            if (opcion == 0)
            {
                FiltradorMas nuevoFiltrador = new FiltradorMas(this.pixeles,0,0,
                        micontenedor, mayorMenor);
                nuevoFiltrador.filtarIterativoMas();
            }
            else if (opcion ==1 )
            {
                FiltradorX nuevoFiltadorX = new FiltradorX(this.pixeles,0,0, micontenedor,
                        mayorMenor);
                nuevoFiltadorX.filtarIterativoX();
            }
            else if( opcion == 2)
            {
                FiltadorEsquinaSuperior nuevoFiltadorEsquinaSuperior = new
                        FiltadorEsquinaSuperior(this.pixeles,0,0, micontenedor, mayorMenor);
                nuevoFiltadorEsquinaSuperior.filtarIterativoESquinaSuperior();
            }
            else if( opcion == 3)
            {
                FiltradorEsquinaInferior nuevoFiltadorEsquinaInferior = new
                        FiltradorEsquinaInferior(this.pixeles,0,0, micontenedor, mayorMenor);
                nuevoFiltadorEsquinaInferior.filtarIterativoEsquinaInferior();
            }
            else if(opcion == 4)
            {
                FiltradorPilar nuevoFiltadorPolar = new FiltradorPilar(this.pixeles,0,0,
                        micontenedor, mayorMenor);
                nuevoFiltadorPolar.filtarIterativoPilar();
            }
            else if(opcion == 5)
            {
                FiltradorHorizontal nuevoFiltadorPolar = new
                        FiltradorHorizontal(this.pixeles,0,0, micontenedor, mayorMenor);
                nuevoFiltadorPolar.filtarIterativoHorizontal();
            }

        }
        else
        {
            ArrayList<Thread> hilos = new ArrayList<>();
            if (opcion == 0)
            {
                for (int i = 0; i < 4; i++)
                {
                    FiltradorMas nuevoFiltrador = new FiltradorMas(this.pixeles,i,i,
                            micontenedor, mayorMenor);
                    Thread nuevoHilo = new Thread(nuevoFiltrador);
                    hilos.add(nuevoHilo);
                }

                for (Thread a : hilos)
                {
                    a.start();
                }

                for (Thread a: hilos)
                {
                    try {
                        a.join();
                    }
                    catch (InterruptedException e )
                    {
                        e.printStackTrace();
                    }

                }
            }
            else if(opcion == 1)
            {
                for (int i = 0; i < 4; i++)
                {
                    FiltradorX nuevoFiltrador = new FiltradorX(this.pixeles,i,i, micontenedor,
                            mayorMenor);
                    Thread nuevoHilo = new Thread(nuevoFiltrador);
                    hilos.add(nuevoHilo);
                }

                for (Thread a : hilos)
                {
                    a.start();
                }

                for (Thread a: hilos)
                {
                    try {
                        a.join();
                    }
                    catch (InterruptedException e )
                    {
                        e.printStackTrace();
                    }

                }
            }
            else if(opcion == 2)
            {
                for (int i = 0; i < 4; i++)
                {
                    FiltadorEsquinaSuperior nuevoFiltrador = new
                            FiltadorEsquinaSuperior(this.pixeles,i,i, micontenedor, mayorMenor);
                    Thread nuevoHilo = new Thread(nuevoFiltrador);
                    hilos.add(nuevoHilo);
                }

                for (Thread a : hilos)
                {
                    a.start();
                }

                for (Thread a: hilos)
                {
                    try {
                        a.join();
                    }
                    catch (InterruptedException e )
                    {
                        e.printStackTrace();
                    }

                }
            }
            else if(opcion == 3)
            {
                for (int i = 0; i < 4; i++)
                {
                    FiltradorEsquinaInferior nuevoFiltrador = new
                            FiltradorEsquinaInferior(this.pixeles,i,i, micontenedor, mayorMenor);
                    Thread nuevoHilo = new Thread(nuevoFiltrador);
                    hilos.add(nuevoHilo);
                }

                for (Thread a : hilos)
                {
                    a.start();
                }

                for (Thread a: hilos)
                {
                    try {
                        a.join();
                    }
                    catch (InterruptedException e )
                    {
                        e.printStackTrace();
                    }

                }
            }
            else if(opcion == 4)
            {
                for (int i = 0; i < 4; i++)
                {
                    FiltradorPilar nuevoFiltrador = new FiltradorPilar(this.pixeles,i,i,
                            micontenedor, mayorMenor);
                    Thread nuevoHilo = new Thread(nuevoFiltrador);
                    hilos.add(nuevoHilo);
                }

                for (Thread a : hilos)
                {
                    a.start();
                }

                for (Thread a: hilos)
                {
                    try {
                        a.join();
                    }
                    catch (InterruptedException e )
                    {
                        e.printStackTrace();
                    }

                }
            }
            else if(opcion == 5)
            {
                for (int i = 0; i < 4; i++)
                {
                    FiltradorHorizontal nuevoFiltrador = new FiltradorHorizontal(this.pixeles,i,i, micontenedor, mayorMenor);
                    Thread nuevoHilo = new Thread(nuevoFiltrador);
                    hilos.add(nuevoHilo);
                }

                for (Thread a : hilos)
                {
                    a.start();
                }

                for (Thread a: hilos)
                {
                    try {
                        a.join();
                    }
                    catch (InterruptedException e )
                    {
                        e.printStackTrace();
                    }

                }
            }
        }
        crear_pmg(matriz);
    }

    public void crear_pmg(int[][] matriz){

        try{
            FileWriter fw = new FileWriter("salida.pgm");
            BufferedWriter salida = new BufferedWriter(fw);
            System.out.println(this.blancoAbsoluto);
            salida.write("P2\n# CREATOR: XV Version 3.10a  Rev: 08/26/21\n"+this.ancho+" "+this.alto+"\n"+this.blancoAbsoluto+"\n");
            for (int i = 0; i < this.alto; i++) {
                for (int j = 0; j < this.ancho; j++) {
                    salida.write(matriz[i][j]+" ");
                }
            }
            System.out.println("Se ha creado con exito el archivo de prueba");
            salida.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }



}
