package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class Simulador
{
    private ArrayList<Nodo> nodos;
    private Arista[][] aristas;
    private ArrayDeque<Arista>aristasConHormonas;
    private Double factorIncrementoHormonas;

    public Simulador(String url,int cantidadHormigas, Double factorIncrementoHormonas)
    {
        this.nodos= new ArrayList<>();
        this.aristas= new Arista[280][280];
        this.aristasConHormonas=new ArrayDeque<>();
        this.factorIncrementoHormonas=factorIncrementoHormonas;
        leerArchivo(url);

        this.simular(cantidadHormigas);
        this.calcularDistanciaPromedio();
        //this.printNodos();
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
        Double factorHormonal=new Double(factorIncrementoHormonas);
        int aristasCargadas = 0;
        for (Nodo nodo:this.nodos)
        {
            Double peso = Math.sqrt(Math.pow(nodo.getCoordenadaX()-nodoNuevo.getCoordenadaX(),2)+Math.pow(nodo.getCoordenadaY()-nodoNuevo.getCoordenadaY(),2));
            int nodoA = nodoNuevo.getId();
            int nodoB = nodo.getId();
            Double carga=new Double(0);
            Arista nuevaArista= new Arista(nodoA,nodoB,peso, carga,factorHormonal);
            this.aristas[nodoA][nodoB]=nuevaArista;
            this.aristas[nodoB][nodoA]=nuevaArista;
            aristasCargadas++;
        }
        return aristasCargadas;
    }

    public void mostrarMatriz()
    {
        for (int i = 0; i < this.aristas.length; i++)
        {
            System.out.print("{");
            for (int j = 0; j < this.aristas.length; j++)
            {
                if(this.aristas[i][j]!=null)
                {
                    this.aristas[i][j].printt();
                }
                else
                {
                    System.out.print("null:null-null");
                }
            }
            System.out.println("}");
        }
    }

    public void calcularDistanciaPromedio()
    {
        int totalPromedios=0;
        for (int i = 0; i < this.aristas.length; i++)
        {
            int total=0;
            Double min= new Double(100000000);
            Double max= new Double(0);
            for (int j = 0; j < this.aristas.length; j++)
            {
                if(this.aristas[i][j]!=null)
                {
                    Double contenedor=this.aristas[i][j].getPeso();
                    if(contenedor>max)
                    {
                        max=contenedor;
                    }
                    if (contenedor<min && contenedor!=0)
                    {
                        min=contenedor;
                    }
                    total+=contenedor;
                }
                else
                {
                    System.out.print("null:null-null");
                }
            }
            int promedio=total/(this.aristas.length);
            //System.out.println("nodo: "+i+" tiene una distancia promedio de :"+promedio);
            //System.out.println("la distancia maxima desde este nodo es:"+max+" y la minimaes:"+min);
            //System.out.println(" ");
            totalPromedios+=promedio;
        }
        System.out.println("el total de los promedios es:"+totalPromedios);
    }

    public void simular(int cantidadHormigas)
    {
        Hormiga mejorHromiga =new Hormiga(-1);
        Double pesoMax = new Double(1000000);
        for (int i = 0; i < cantidadHormigas; i++)
        {
            Hormiga hormigaNueva= new Hormiga(i);
            recorrer(hormigaNueva,this.nodos.get(0));
            System.out.println("hormigas:"+hormigaNueva.getId()+" termino con un camino de peso:"+hormigaNueva.getPeso());
            if(pesoMax>hormigaNueva.getPeso())
            {
                pesoMax=hormigaNueva.getPeso();
                mejorHromiga=hormigaNueva;
            }

        }
        System.out.println("la mejor hormigas:"+mejorHromiga.getId()+" termino con un camino de peso:"+mejorHromiga.getPeso());

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
            nodo=this.nodos.get(this.seleccionarNodoSiguente(aristasRecorrer,nodo.getId()));
            this.degradarHormonas();
        }

        aristasRecorrer=this.aristas[0][nodo.getId()];
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
        for (int i = 0; i < this.aristas.length; i++)
        {
            if(this.aristas[idNodoActual][i]!=null)//validamos que exista la aristas ya que las donde i==j no existen
            {
                if(!hormiga.visiteYaEsteNodo(i))//validamos cuales son los nodos que ya visitamos
                {
                    int valor = miRandom.nextInt(1000);//para los nodos no visitados se genera un valor random
                    if (this.aristas[idNodoActual][i].getCargaHormonal() > 0)//si tiene carga hormonal se multiplica el valor por la carga
                    {
                        if(this.aristas[idNodoActual][i].getCargaHormonal() < 1)
                        {
                           int valor2 =valor;
                            valor2 *= this.aristas[idNodoActual][i].getCargaHormonal();
                            valor+=valor2;
                        }
                        else
                        {
                            valor *= this.aristas[idNodoActual][i].getCargaHormonal();
                        }

                    }
                    if (valor > maxActual)//se busca  el valor mas alto de todos asi ese sera el siguente nodo
                    {
                        maxActual = valor;
                        if(nodoACtual.getId()==this.aristas[idNodoActual][i].getIdNodoB())
                        {
                            idNextNodo = this.aristas[idNodoActual][i].getIdNodoA();
                        }
                        else
                        {
                            idNextNodo = this.aristas[idNodoActual][i].getIdNodoB();
                        }

                    }
                }
            }
            miRandom.setSeed(System.nanoTime());
        }
        return this.aristas[idNodoActual][idNextNodo];
    }

    //este metodo recorre todas las aristas y decrementa su carga hormonal
    public void degradarHormonas()
    {
        int fin=this.aristasConHormonas.size();
        int i=0;
        while (i<fin)
        {
            Arista contenedor = this.aristasConHormonas.peekFirst();
            contenedor.decrementarhormonas();
            if(contenedor.getCargaHormonal()>0)
            {
                this.aristasConHormonas.add(contenedor);//esto agrega la arista al final
            }
            else
            {
                Double cero=new Double(0);
                contenedor.setCargaHormonal(cero);
            }
            i++;
        }
    }

    public int seleccionarNodoSiguente(Arista arista, int nodoActual)
    {
        if(arista.getIdNodoA()==nodoActual)
        {
            return arista.getIdNodoB();
        }
        else
        {
            return arista.getIdNodoA();
        }
    }


    public void printNodos()
    {
        for (Nodo nodo :this.nodos)
        {
            nodo.printt();
        }
    }
}

