package com.company;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Scanner;

public class LeerImagen {

    private String nombreArchivo;
    private int ancho;
    private int alto;
    private int blancoAbsoluto;
    private int[][] pixeles;
    
    public LeerImagen(String nombreArchivo) throws Exception{
        Scanner leer = null;
        try {
            File imagen = new File(nombreArchivo);
            leer = new Scanner(imagen);
            System.out.println(leer.hasNext());
            leer.nextLine();
            leer.nextLine();
            ancho = leer.nextInt();
            alto = leer.nextInt();
            blancoAbsoluto = leer.nextInt();

            pixeles = new int[alto][ancho];

            for (int i = 0; i < alto; i++) {
                for (int j = 0; j < ancho; j++) {
                    pixeles[i][j] = leer.nextInt();
                    System.out.println(pixeles[i][j]);
                }
            }
            leer.close();

        } catch (Exception e) {
            throw e;
        } finally{
            if(leer != null){
                leer.close();
            }
        }
    }

    public void matrizImagen(String nombreArchivo){
    }

}
