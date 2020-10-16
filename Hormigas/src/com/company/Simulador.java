package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.StringTokenizer;

public class Simulador {
    private ArrayList<Nodo> nodos;
    private Arista[][] Aristas;

    public Simulador(String url)
    {
        this.nodos= new ArrayList<>();
        this.Aristas= new Arista[280][280];
        leerArchivo(url);
        //this.mostrarMatriz();
        this.nodos.get(0).printt();
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


    public void recorrer(Hormiga nuevaHormiga,Nodo nodo)
    {
        Arista aristasRecorrer;
        while (nuevaHormiga.visitadosTotales()<280)
        {
            nuevaHormiga.AddIdNodoToCamino(nodo.getId());
            nuevaHormiga.MarckNodoWithVisited(nodo.getId());
            aristasRecorrer=elegirArista(nuevaHormiga, nodo);
            nuevaHormiga.addPeso(aristasRecorrer.getPeso());
            aristasRecorrer.incrementarCargaHormonal();
            nodo=this.nodos.get(aristasRecorrer.getIdNodoB());
        }

        aristasRecorrer=this.Aristas[0][nodo.getId()];
        nuevaHormiga.AddIdNodoToCamino(0);
        nuevaHormiga.addPeso(aristasRecorrer.getPeso());
        aristasRecorrer.incrementarCargaHormonal();

    }


    public Arista elegirArista(Hormiga hormiga,Nodo nodoACtual)
    {
        int idNodoActual=nodoACtual.getId();
        int maxActual=0;
        int idNextNodo=0;
        Random miRandom = new Random(System.currentTimeMillis());
        //primero revisamos todas las aristas disponbles
        for (int i = 0; i < this.Aristas.length; i++)
        {
            if(this.Aristas[idNodoActual][i]!=null)//validamos que exista la aristas ya que las donde i==j no existen
            {
                if(!hormiga.visiteYaEsteNodo(i))//validamos cuales son los nodos que ya visitamos
                {
                    int valor = miRandom.nextInt(1000);//para lso nodos no visitados se genera un valor random
                    if (this.Aristas[idNodoActual][i].getCargaHormonal() > 0)//si tiene carga hormonal se multiplica el valor por la carga
                    {
                        valor *= this.Aristas[idNodoActual][i].getCargaHormonal();
                    }

                    if (valor > maxActual)//se busca  el valor mas alto de todos asi ese sera el siguente nodo
                    {
                        maxActual = valor;
                        idNextNodo = this.Aristas[idNodoActual][i].getIdNodoB();
                    }
                }
            }
            miRandom.setSeed(System.currentTimeMillis());
        }

        return this.Aristas[idNodoActual][idNextNodo];
    }
}

