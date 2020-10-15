package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Simulador {
    private ArrayList<Nodo> nodos;
    private Arista[][] Aristas;

    public Simulador()
    {
        this.nodos= new ArrayList<>();
        this.Aristas= new Arista[280][280];
        leerArchivo("D:\\repo-git-local-2\\pruebas-de-codigo-y-algoritmos\\Hormigas\\src\\com\\company\\a280.txt");
        this.mostrarMatriz();
    }

    public boolean leerArchivo(String file) {
        boolean lecturaCompleta = false;
        HashMap<String,Integer> nodoexiste = new HashMap<>();
        try {
            String cadena;
            FileReader f = new FileReader(file);
            BufferedReader b = new BufferedReader(f);
            while ((cadena = b.readLine()) != null) {
                StringTokenizer tokens= new StringTokenizer(cadena," ");
                int id = Integer.parseInt(tokens.nextToken());
                String coordenaX = tokens.nextToken();
                String coordenaY = tokens.nextToken();

                StringBuilder builder = new StringBuilder(" ");
                builder.append(coordenaX);
                builder.append(coordenaY);
                if(!nodoexiste.containsKey(builder.toString()))
                {
                    nodoexiste.putIfAbsent(builder.toString(), id);
                    int coordenax=Integer.parseInt(coordenaX);
                    int coordenay=Integer.parseInt(coordenaY);
                    Nodo nuevoNodo = new Nodo(id-1,coordenax,coordenay);
                    nodos.add(nuevoNodo);
                    this.cargarAristas(nuevoNodo);
                }
                else
                {
                    System.out.println("coordenadas del nodo:"+id+" ya estan ocupadas por el nodo:"+nodoexiste.get(builder.toString()));
                }
            }
            b.close();
            lecturaCompleta=true;
        } catch (Exception e) {
            System.out.println("lectura interrumpida");
            e.printStackTrace();
        }
        return lecturaCompleta;

    }

    public int cargarAristas(Nodo nodoNuevo)
    {
        int aristasCargadas = 0;
        for (Nodo nodo:this.nodos)
        {
            Double peso = Math.sqrt(Math.pow(nodo.getCoordenadaX()-nodoNuevo.getCoordenadaX(),2)+Math.pow(nodo.getCoordenadaY()-nodoNuevo.getCoordenadaY(),2));
            int nodoA = nodoNuevo.getId();
            int nodoB = nodo.getId();
            Arista nuevaArista= new Arista(nodoA,nodoB,peso, 0);
            this.Aristas[nodoA][nodoB]=nuevaArista;
            this.Aristas[nodoB][nodoA]=nuevaArista;
            aristasCargadas++;
        }
        return aristasCargadas;
    }

    public void mostrarMatriz()
    {
        for (int i = 0; i < this.Aristas.length; i++)
        {
            System.out.print("{");
            for (int j = 0; j < this.Aristas.length; j++)
            {
                if(this.Aristas[i][j]!=null)
                {
                    this.Aristas[i][j].printt();
                }
                else
                {
                    System.out.print("null:null-null");
                }
            }
            System.out.println("}");
        }
    }
}

