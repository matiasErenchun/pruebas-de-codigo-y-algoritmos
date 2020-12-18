package com.company;

import com.sun.org.apache.xml.internal.utils.StopParseException;

import java.math.BigInteger;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;

public class Interprete
{
    private HashMap<String,Integer>priorityMap;
    private ArrayList<ArrayList<String>> lineas;
    private LectorInputs miLector;
    private HashMap<String,BigInteger> tablaVariables;
    private Boolean continuar;
    private HashMap<Integer,Integer> mapaParOperaciones;

    public Interprete(ArrayList<ArrayList<String>>lineas)
    {
        this.priorityMap=new HashMap<>();
        this.miLector = new LectorInputs();
        this.continuar=true;
        this.mapaParOperaciones = new HashMap<>();
        this.lineas = lineas;
        this.cargarPriorityMap();
        this.tablaVariables = new HashMap<>();
    }

    public boolean interpretar()
    {
        this.mapearIfElse(0);
        boolean salida=true;
        int i=0;
        while(this.continuar)
        {
            ArrayList<String > lineaActual =this.lineas.get(i);
            int indexLineaActual =0;
            String tokenActual=lineaActual.get(indexLineaActual);
            if (tokenActual.equalsIgnoreCase("read"))
            {
                String  nextToken = lineaActual.get(indexLineaActual+1);
                if (nextToken.matches("^[$][a-zA-Z0-9]+$"))
                {
                    BigInteger valor = miLector.readBigInteger();
                    this.tablaVariables.put(nextToken,valor);
                }
                else
                {
                    this.continuar=false;
                    System.out.println("error al leer, el resultado no se asigna o guarda en una variable ");
                }
                i++;
            }
            else if(tokenActual.equalsIgnoreCase("write"))
            {
                if(indexLineaActual+1<lineaActual.size())
                {
                    ArrayDeque<String>contenedor = this.extraerExpresionNumerica(lineaActual,indexLineaActual+1);
                    ArrayDeque<String>contenedor2=this.infijoToPrefijo(contenedor);
                    System.out.println(this.resolverExpresionNumerica(contenedor2));
                }
                i++;

            }
            else if(tokenActual.equalsIgnoreCase("if"))
            {
                ArrayList<String> contenedor = this.extraerExpresionBooleana(lineaActual,1);
                boolean respuesta = this.resolverExpresionBooleana(contenedor);
                if(!respuesta)
                {
                        // pedimos que busque en nuestro mapa a que linea tenemos que saltar
                    i= this.mapaParOperaciones.get(i)+1;
                }
                else
                {
                    System.out.println("holaaaa");
                    //si la respuesta es true pasamo a ejecutar la siguente linea
                    i++;
                }
            }
            else if (tokenActual.equalsIgnoreCase("else"))
            {
                i=this.mapaParOperaciones.get(i);
            }
            else if (tokenActual.equalsIgnoreCase("endif"))
            {
                i++;
            }
            else if(tokenActual.equalsIgnoreCase("while"))
            {
                ArrayList<String> contenedor = this.extraerExpresionBooleana(lineaActual,1);
                boolean respuesta = this.resolverExpresionBooleana(contenedor);
                if(!respuesta)
                {
                    // pedimos que busque en nuestro mapa a que linea tenemos que saltar
                    i= this.mapaParOperaciones.get(i)+1;
                }
                else
                {
                    //si la respuesta es true pasamo a ejecutar la siguente linea
                    i++;
                }

            }
            else if (tokenActual.equalsIgnoreCase("wend"))
            {
                i=this.mapaParOperaciones.get(i);
            }
            else
            {
                if(tokenActual.matches("^[$][a-zA-Z0-9]+$"))
                {
                    String nexToken=lineaActual.get(indexLineaActual+1);
                    if(nexToken.equalsIgnoreCase("="))
                    {
                        ArrayDeque<String>contenedor = this.extraerExpresionNumerica(lineaActual,indexLineaActual+2);
                        ArrayDeque<String>contenedor2=this.infijoToPrefijo(contenedor);
                        this.tablaVariables.put(tokenActual,this.resolverExpresionNumerica(contenedor2));
                        //System.out.println(tokenActual+": "+this.tablaVariables.get(tokenActual));
                    }
                    i++;
                }

            }
            if(i>=this.lineas.size())
            {
                this.continuar=false;
                salida=false;
            }
        }

        return salida;
    }

    public ArrayDeque<String> extraerExpresionNumerica(ArrayList<String> lineaActual, int index)
    {
        ArrayDeque<String> salida = new ArrayDeque<>();
        while(index<lineaActual.size())
        {
            salida.add(lineaActual.get(index));
            index++;
        }
        return salida;
    }

    //busca el par de parentessi correspondiente y copia un sub cadena con lo que esta etre estos
    public ArrayList<String> extraerExpresionBooleana(ArrayList<String> lineaActual, int index)
    {
        String tokenActual = lineaActual.get(index);
        ArrayList<String>salida= new ArrayList<>();
        if(tokenActual.equalsIgnoreCase("("))
        {
            int i=index+1;
            if(i<lineaActual.size())
            {
                tokenActual = lineaActual.get(i);
                boolean segir = true;
                while (segir && !tokenActual.equalsIgnoreCase(")")) {

                    if (!tokenActual.equalsIgnoreCase("("))
                    {
                        salida.add(tokenActual);
                    }
                    else
                    {
                        segir=false;
                        System.out.println("error paretesis sobrantes");
                        this.continuar=false;
                    }
                    i++;
                    if (i >= lineaActual.size())
                    {
                        segir = false;
                    }
                    else
                    {
                        tokenActual = lineaActual.get(i);
                    }
                }
            }
            else
            {
                this.continuar=false;
                System.out.println("error en lso parentesis");
            }
        }
        else
        {
            System.out.println("falta parentesis ( al inicio de la linea actual");
            this.continuar=false;
        }

        return salida;
    }

    //aplica precedencia y resuelve una exprecion numerica
    public BigInteger resolverExpresionNumerica(ArrayDeque<String>tokens)
    {
        BigInteger salida= new BigInteger("0");
        ArrayDeque<BigInteger>pilaResultados = new ArrayDeque<>();
        BigInteger valor1;
        BigInteger valor2;
        BigInteger valor3;
        while (!tokens.isEmpty())
        {
            String tokenActual = tokens.pollFirst();

            //si es una variable
            if(tokenActual.matches("^[$][a-zA-Z0-9]+$"))
            {
                if(this.tablaVariables.containsKey(tokenActual))
                {
                    BigInteger aux=this.tablaVariables.get(tokenActual);
                    pilaResultados.addFirst(aux);
                }
                else
                {
                    System.out.println("variable: "+tokenActual+" no esta definida");
                    this.continuar=false;
                    break;
                }

            }
            //si es un numero
            else if(tokenActual.matches("^[-]{0,1}[0-9]+$"))
            {
                BigInteger aux=new BigInteger(tokenActual);
                pilaResultados.addFirst(aux);
            }
            else if(tokenActual.equalsIgnoreCase("+"))
            {
                valor1=pilaResultados.pollFirst();
                valor2=pilaResultados.pollFirst();
                if(valor1!=null && valor2!= null)
                {
                    valor3 = valor2.add(valor1);
                    pilaResultados.addFirst(valor3);
                }
                else
                {
                    System.out.println("error de en la sintaxis");
                }
            }
            else if(tokenActual.equalsIgnoreCase("-"))
            {
                valor1=pilaResultados.pollFirst();
                valor2=pilaResultados.pollFirst();
                if(valor1!=null && valor2!= null)
                {
                    valor3 = valor2.subtract(valor1);
                    pilaResultados.addFirst(valor3);
                }
                else
                {
                    System.out.println("error de en la sintaxis");
                }
            }
            else if(tokenActual.equalsIgnoreCase("*"))
            {
                valor1=pilaResultados.pollFirst();
                valor2=pilaResultados.pollFirst();
                if(valor1!=null && valor2!= null)
                {
                    valor3 = valor2.multiply(valor1);
                    pilaResultados.addFirst(valor3);
                }
                else
                {
                    System.out.println("error de en la sintaxis");
                }
            }
            else if(tokenActual.equalsIgnoreCase("/"))
            {
                valor1=pilaResultados.pollFirst();
                valor2=pilaResultados.pollFirst();
                if(valor1!=null && valor2!= null)
                {
                    valor3 = valor2.divide(valor1);
                    pilaResultados.addFirst(valor3);
                }
                else
                {
                    System.out.println("error de en la sintaxis");
                }
            }
            else if(tokenActual.equalsIgnoreCase("%"))
            {
                valor1=pilaResultados.pollFirst();
                valor2=pilaResultados.pollFirst();
                if(valor1!=null && valor2!= null)
                {
                    valor3 = valor2.remainder(valor1);
                    pilaResultados.addFirst(valor3);
                }
                else
                {
                    System.out.println("error de en la sintaxis");
                }
            }
            else
            {
                System.out.println("error de en la sintaxis");
            }
        }
        salida = pilaResultados.peekFirst();
        return salida;
    }

    // resive una cadena con una expresion con resultado booleano y la resuelve
    public boolean resolverExpresionBooleana(ArrayList<String>expresion)
    {
        boolean salida = true;
        ArrayDeque<String>expresionDerecha= new ArrayDeque<>();
        String aEvaluar= " ";
        ArrayDeque<String>expresionIzquierda = new ArrayDeque<>();
        int i=0;
        while (i<expresion.size())
        {
            String tokenActual = expresion.get(i);
            if(tokenActual.matches("[-]{0,1}[$][a-zA-Z0-9]|[-]{0,1}[0-9]|[+]|[-]|[*]|[/]|[%]") && aEvaluar.equalsIgnoreCase(" "))
            {
                expresionIzquierda.add(tokenActual);
            }
            else if (!aEvaluar.equalsIgnoreCase(" ") && (tokenActual.matches("[-]{0,1}[$][a-zA-Z0-9]|[-]{0,1}[0-9]|[+]|[-]|[*]|[/]|[%]")))
            {
                expresionDerecha.add(tokenActual);
            }
            else if (tokenActual.matches("[>][=]{0,1}|[<][=]{0,1}|[=]{2}|[!][=]"))
            {
                aEvaluar=tokenActual;
            }
            i++;
        }
        if(expresionIzquierda.isEmpty()|| expresionDerecha.isEmpty()||aEvaluar.equalsIgnoreCase(" "))
        {
            salida=false;
            this.continuar=false;
            System.out.println("expresionmal escrita:"+expresionIzquierda.toString()+aEvaluar+expresionDerecha.toString());
        }
        else
        {
            BigInteger izquierda = this.resolverExpresionNumerica(this.infijoToPrefijo(expresionIzquierda));
            BigInteger derecha = this.resolverExpresionNumerica(this.infijoToPrefijo(expresionDerecha));
            salida = this.evaluarExpresiones(izquierda, derecha, aEvaluar);
        }
        return salida;
    }

    //aplica conbersion a notacion polaca inversa (el nombre esta mal pero bueno )
    public ArrayDeque<String> infijoToPrefijo(ArrayDeque<String> misTokens)
    {
        ArrayDeque<String>salida = new ArrayDeque<>();
        ArrayDeque<String>auxOperadores = new ArrayDeque<>();
        while (!misTokens.isEmpty())
        {
            String elemento = misTokens.pollFirst();
            if(elemento.matches("^[$a-zA-Z0-9]+$"))
            {
                salida.add(elemento);
            }
            else if(elemento.equalsIgnoreCase("("))
            {
                auxOperadores.addFirst(elemento);
            }
            else if(elemento.equalsIgnoreCase(")"))
            {
                while(!auxOperadores.isEmpty() &&  !auxOperadores.peekFirst().equalsIgnoreCase("("))
                {

                    String operador = auxOperadores.pollFirst();
                    salida.add(operador);
                }
                if(!auxOperadores.isEmpty())
                {
                    auxOperadores.pollFirst();
                }
            }
            else
            {
                boolean continuar = true;
                while(!auxOperadores.isEmpty() && !auxOperadores.peekFirst().equalsIgnoreCase("(") && continuar)
                {
                    if(pPrioriE(auxOperadores.getFirst(),elemento))
                    {
                        String aux= auxOperadores.pollFirst();
                        salida.add(aux);
                    }
                    else
                    {
                        continuar =false;
                    }
                }
                auxOperadores.addFirst(elemento);
            }
        }

        while (!auxOperadores.isEmpty())
        {
            salida.add(auxOperadores.pollFirst());
        }

        return salida;
    }

    public boolean pPrioriE(String p,String e )
    {
        if(this.priorityMap.get(p)>=this.priorityMap.get(e))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    private void cargarPriorityMap()
    {
        this.priorityMap.put("*",3);
        this.priorityMap.put("/",3);
        this.priorityMap.put("%",3);
        this.priorityMap.put("+",2);
        this.priorityMap.put("-",2);


    }

    //recorre todas las cadenas y revisa que los if,else, endif , whiles y wned esten correctamente pareados.
    public void mapearIfElse(int index)
    {
        ArrayDeque<ContenedorParCaracteres>piladeElseIf = new ArrayDeque<>();
        for (int i = 0; i < this.lineas.size(); i++)
        {
            String tokenActual = this.lineas.get(i).get(0);
            if(tokenActual.equalsIgnoreCase("if"))
            {
                ContenedorParCaracteres contenedor = new ContenedorParCaracteres(i,tokenActual);
                piladeElseIf.addFirst(contenedor);
            }
            else if(tokenActual.equalsIgnoreCase("else"))
            {
                if(piladeElseIf.size()>0)
                {
                    ContenedorParCaracteres contenedor = piladeElseIf.pollFirst();
                    if(contenedor.getTipo().equalsIgnoreCase("if"))
                    {
                        ContenedorParCaracteres contenedor2=new ContenedorParCaracteres(i,"else");
                        piladeElseIf.addFirst(contenedor2);
                        this.mapaParOperaciones.putIfAbsent(contenedor.getNumeroLinea(), contenedor2.getNumeroLinea());
                    }
                    else
                    {
                        //error endif
                        System.out.println("error en la linea:"+(i+1)+" el else esta demas");
                        this.continuar=false;
                    }
                }
                else
                {
                    //error else
                    System.out.println("error en la linea:"+(i+1)+" el else no encuentra un if que cerrar");
                    this.continuar=false;
                }
            }
            else if (tokenActual.equalsIgnoreCase("endif"))
            {
                if (piladeElseIf.size()>0)
                {
                    ContenedorParCaracteres contenedor = piladeElseIf.pollFirst();
                    if( contenedor.getTipo().equalsIgnoreCase("if")|| contenedor.getTipo().equalsIgnoreCase("else"))
                    {
                        this.mapaParOperaciones.putIfAbsent(contenedor.getNumeroLinea(),i);
                    }
                    else
                    {
                        //error endif
                        System.out.println("error en la linea:"+(i+1)+" el endif esta demas");
                        this.continuar=false;
                    }

                }
                else
                {
                    System.out.println("error en la linea:"+(i+1)+" el endif no encuentra un if que cerrar");
                    this.continuar=false;
                }
            }
            else if (tokenActual.equalsIgnoreCase("while"))
            {
                ContenedorParCaracteres contenedor = new ContenedorParCaracteres(i,tokenActual);
                piladeElseIf.addFirst(contenedor);
            }
            else if (tokenActual.equalsIgnoreCase("wend"))
            {
                if(piladeElseIf.size()>0)
                {
                    ContenedorParCaracteres contenedor = piladeElseIf.pollFirst();
                    if(contenedor.getTipo().equalsIgnoreCase("while"))
                    {
                        this.mapaParOperaciones.putIfAbsent(i,contenedor.getNumeroLinea());
                        this.mapaParOperaciones.putIfAbsent(contenedor.getNumeroLinea(),i);
                    }
                    else
                    {
                        //error endif
                        System.out.println("error en la linea:"+(i+1)+" el wend no cierra ningun while esta demas");
                        this.continuar=false;
                    }
                }
                else
                {
                    //error else
                    System.out.println("error en la linea:"+(i+1)+" el wend no encuentra un  while que cerrar");
                    this.continuar=false;
                }
            }
        }
    }

    public Boolean evaluarExpresiones(BigInteger izquierda,BigInteger derecha, String token)
    {
        if(token.equalsIgnoreCase("=="))
        {
            if(izquierda.equals(derecha))
            {
                return true;
            }
            return false;
        }
        else if (token.equalsIgnoreCase(">"))
        {
            if (izquierda.compareTo(derecha)==1)
            {
                return true;
            }
            return false;
        }
        else if (token.equalsIgnoreCase("<"))
        {
            if (izquierda.compareTo(derecha)==-1)
            {
                return true;
            }
            return false;
        }
        else if (token.equalsIgnoreCase(">="))
        {
            if (izquierda.compareTo(derecha)==1 || izquierda.compareTo(derecha)==0 )
            {
                return true;
            }
            return false;
        }
        else if (token.equalsIgnoreCase("<="))
        {
            if (izquierda.compareTo(derecha)==-1 || izquierda.compareTo(derecha)==0 )
            {
                return true;
            }
            return false;
        }
        else
        {
            if (izquierda.compareTo(derecha)!=0)
            {
                return true;
            }
            return false;
        }
    }
}
