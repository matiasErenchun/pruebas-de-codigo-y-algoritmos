package com.company;

import java.awt.image.BufferedImage;

public class LeerImagen {
    
    public LeerImagen(){}

    public int[][] matrizImagen(BufferedImage bufferedImage){

        System.out.println("dentro de la matrizImagen");

        int ancho = bufferedImage.getWidth(null);
        System.out.println(ancho);
        int alto = bufferedImage.getHeight(null);
        System.out.println(alto);

        int[][] pixeles = new int[ancho][alto];

        for (int i = 0; i < alto; i++) {
            for (int j = 0; j < ancho; j++) {
                pixeles[i][j] = bufferedImage.getRGB(i,j);
                System.out.print(pixeles[i][j] + "|");
            }
            System.out.println("");
        }

        return pixeles;
    }

}
