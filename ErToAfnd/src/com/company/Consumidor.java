package com.company;

import java.util.ArrayDeque;

public class Consumidor
{
    Integer contadorNodo;
    public ArrayDeque<String>tokensAConsumir;
    public Consumidor(ArrayDeque<String> tokens)
    {
        this.contadorNodo=0;
        this.tokensAConsumir=tokens;
        consumir();
    }

    public void consumir()
    {
        try {
            boolean errores =false;
            Er eRActual = new Er(new Nodo("-1"), new Nodo("-1"));
            Er eRAnterior = new Er(new Nodo("-1"), new Nodo("-1"));
            while (!this.tokensAConsumir.isEmpty()&& !errores )
            {
                String tokenActual = this.tokensAConsumir.pollFirst();
                if(tokenActual.equalsIgnoreCase("~"))
                {
                    System.out.println("hola ~");
                    eRActual = this.expresionVacio();
                }
                else if(tokenActual.equalsIgnoreCase("*"))
                {
                    System.out.println("hola *");
                    if(eRActual.getNodoInicial().getId().equalsIgnoreCase("-1"))
                    {
                        errores = true;
                        System.out.println("error en la expresion inicia con *");
                    }
                    else
                    {
                        eRActual = this.cierreEstrellaEr(eRActual);
                    }

                }
                else if(tokenActual.equalsIgnoreCase("."))
                {
                    System.out.println("hola .");
                    if(eRActual.getNodoInicial().getId().equalsIgnoreCase("-1"))
                    {
                        errores = true;
                        System.out.println("error en la expresion inicia con .");
                    }
                    else if(eRAnterior.getNodoInicial().getId().equalsIgnoreCase("-1"))
                    {
                        errores = true;
                        System.out.println("error en la expresion falta un operando para el .");
                    }
                    else
                    {
                        eRActual = this.concatenarErs(eRAnterior,eRActual);
                    }
                }
                else if(tokenActual.equalsIgnoreCase("|"))
                {
                    System.out.println("hola |");
                    if(eRActual.getNodoInicial().getId().equalsIgnoreCase("-1"))
                    {
                        errores = true;
                        System.out.println("error en la expresion inicia con |");
                    }
                    else if(eRAnterior.getNodoInicial().getId().equalsIgnoreCase("-1"))
                    {
                        errores = true;
                        System.out.println("error en la expresion falta un operando para el |");
                    }
                    else
                    {
                        eRActual = this.construirAOrB(eRAnterior,eRActual);
                    }
                }
                else
                {
                    System.out.println("hola :"+tokenActual);
                    eRAnterior=eRActual;
                    eRActual=this.construirErSimple(tokenActual);
                    eRActual.mostrarEr();
                }
            }
            eRActual.mostrarEr();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("error en la emprecion ");
        }



    }

    public Er construirErSimple(String caracter)
    {
        Nodo nodoInicial= new Nodo(this.getNuevoIdNodo());
        Nodo nodoFinal= new Nodo(this.getNuevoIdNodo());
        Transicion nuevaTransicion = new Transicion(nodoInicial.getId(),nodoFinal,caracter);
        nodoInicial.addTrancision(nuevaTransicion);
        Er nuevaEr= new Er(nodoInicial,nodoFinal);
        nuevaEr.addNodo(nodoInicial);
        nuevaEr.addNodo(nodoFinal);
        return nuevaEr;
    }

    public Er concatenarErs(Er inicio ,Er fin)
    {
        Er nuevaEr = new Er(inicio.getNodoInicial(),fin.getNodoFinal());
        Transicion puente = new Transicion(inicio.getNodoFinal().getId(),fin.getNodoInicial(),"_");
        inicio.getNodoFinal().addTrancision(puente);
        nuevaEr.cargarNodos(inicio.getNodos());
        nuevaEr.cargarNodos(fin.getNodos());

        return nuevaEr;
    }

    public Er cierreEstrellaEr(Er erActula)
    {
        Transicion puenteFinInicio = new Transicion(erActula.getNodoFinal().getId(),erActula.getNodoInicial(),"_");
        erActula.getNodoFinal().addTrancision(puenteFinInicio);
        return erActula;
    }

    public Er construirAOrB(Er a,Er b)
    {
        System.out.println("mostramos a");
        a.mostrarEr();
        System.out.println("mostramos b");
        b.mostrarEr();
        Nodo nodoInicial = new Nodo(this.getNuevoIdNodo());
        Nodo nodoFinal = new Nodo(this.getNuevoIdNodo());

        Transicion inicialToA = new Transicion(nodoInicial.getId(),a.getNodoInicial(),"_");
        Transicion inicialToB = new Transicion(nodoInicial.getId(),b.getNodoInicial(),"_");
        nodoInicial.addTrancision(inicialToA);
        nodoInicial.addTrancision(inicialToB);

        Transicion aToFinal = new Transicion(a.getNodoFinal().getId(),nodoFinal,"_");
        a.getNodoFinal().addTrancision(aToFinal);

        Transicion bToFinal = new Transicion(b.getNodoFinal().getId(),nodoFinal,"_");
        b.getNodoFinal().addTrancision(bToFinal);

        Er nuevaEr = new Er(nodoInicial,nodoFinal);
        nuevaEr.addNodo(nodoInicial);
        nuevaEr.cargarNodos(a.getNodos());
        nuevaEr.cargarNodos(b.getNodos());
        nuevaEr.addNodo(nodoFinal);
        return nuevaEr;
    }

    public Er expresionVacio()
    {
        Nodo nodoInicial = new Nodo(this.getNuevoIdNodo());
        Nodo nodoFinal = new Nodo(this.getNuevoIdNodo());

        Er nuevaEr = new Er(nodoInicial,nodoFinal);
        nuevaEr.addNodo(nodoInicial);
        nuevaEr.addNodo(nodoFinal);

        return nuevaEr;
    }

    public String getNuevoIdNodo()
    {
        StringBuilder idNodo= new StringBuilder("");
        idNodo = new StringBuilder("");
        idNodo.append("q");
        idNodo.append(this.contadorNodo);
        this.contadorNodo++;
        return idNodo.toString();
    }


}
