package com.company;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class LeerImagen {

    private String nombreArchivo;
    private int ancho = 0;
    private int alto = 0;
    private int blancoAbsoluto;
    private int[][] pixeles;
    
    public LeerImagen(){}

    public void matrizImagen(String nombreArchivo) throws IOException {
        this.nombreArchivo = nombreArchivo;
        File file = new File(this.nombreArchivo);
        System.out.println(file.length());
        final FileInputStream f = new FileInputStream(file);
        ArrayList<ArrayList<Integer>> lineas = new ArrayList<>();
        ArrayList<Integer> caja =  new ArrayList<>();
        System.out.println(f.available());
        int chrr;
        int i = 0;
        int j = 0;

        while (f.available() != 0)
        {
            System.out.println("avaible: " + f.available());
            chrr = f.read();
            if(chrr != 10)
            {
                System.out.print(chrr +" ");
                caja.add(chrr);
            }
            else
            {
               System.out.println(" linea " + i );
               caja.add(chrr);
               lineas.add(caja);
               caja = new ArrayList<>();
               i++;
            }
            j++;
        }

        int aux = lineas.size();
        for (int k = 0; k < caja.size(); k++)
        {
            lineas.get(aux - 1).add(caja.get(k));
        }
        System.out.println(lineas.size());
        System.out.println(lineas.get(lineas.size()-1).size());
        System.out.println( " ------ ");


        char charAux;
        int ancho = 0;
        int alto = 0;
        ArrayList<Integer> numeros = new ArrayList<>();
        for (int k = 0; k < lineas.size(); k++)
        {
            StringBuffer st = new StringBuffer();
            ArrayList<Integer> cajaAuxContenedor = lineas.get(k);
            System.out.println("lagor :" + cajaAuxContenedor.size());
            if( k ==  0)
            {
                for (int l = 0; l < cajaAuxContenedor.size(); l++)
                {
                    int valorAuxiliar =  cajaAuxContenedor.get(l);
                    charAux = (char) valorAuxiliar;
                    st.append(charAux);
                }
                if(!st.toString().contains("P5")){
                    System.out.println("no es formato p5");
                }
                else
                {
                    System.out.println(st.toString());
                }
            }
            else if(  k == 1 )
            {
                for (int l = 0; l < cajaAuxContenedor.size(); l++)
                {
                    int valorAuxiliar =  cajaAuxContenedor.get(l);
                    charAux = (char) valorAuxiliar;
                    st.append(charAux);
                }
                StringTokenizer stken = new StringTokenizer(st.toString());
                ancho = Integer.parseInt(stken.nextToken());
                alto = Integer.parseInt(stken.nextToken());
                System.out.println("ancho :" + ancho + ", alto:"+ alto);
            }
            else if(k == 2)
            {
                for (int l = 0; l < cajaAuxContenedor.size(); l++)
                {
                    int valorAuxiliar =  cajaAuxContenedor.get(l);
                    charAux = (char) valorAuxiliar;
                    //sfsdfdf
                    st.append(charAux);
                }
                StringTokenizer stken = new StringTokenizer(st.toString());
                String blancoContenedor = stken.nextToken();
                int blancoMasBlanco = Integer.parseInt(blancoContenedor);
                System.out.println("el blanco mas blanco:" + blancoMasBlanco);
            }
            else
            {
                for (int l = 0; l < cajaAuxContenedor.size(); l++) {
                    numeros.add(cajaAuxContenedor.get(l));
                }
            }
        }
        this.pixeles = new int[alto][ancho];
        int[][] originalImage = new int[alto][ancho];

        int valorEnElArregloDeNumeros = 0;
        for ( i = 0; i < alto; i++){
            System.out.print( "fila: "+ i + " ");
            for ( j = 0; j < ancho; j++){
                int numero = originalImage[i][j] = numeros.get(valorEnElArregloDeNumeros);
                System.out.print(numero + "|");
                this.pixeles[i][j] = numero;
                valorEnElArregloDeNumeros++;
            }
            System.out.println(" ");
        }

        /*
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
        System.out.println(blancoAbsoluto + "blanco absoluto");

        int[][] originalImage = new int[alto][ancho];
        this.pixeles = new int[alto][ancho];

        for ( i = 0; i < alto; i++){
            for ( j = 0; j < ancho; j++){
                int numero = originalImage[i][j] = br.read();
                System.out.print(numero + "|");
                this.pixeles[i][j] = numero;
            }
            System.out.println(" ");
        }

        */
        f.close();
        //this.crear_pmg(originalImage);
        //imprimir_pixeles();
        //filtros();


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
        int opcion = 1;
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
        crear_pmg(micontenedor.getMatrizFinal());
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
