package com.company;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class Main {

    int[][] matriz_pixeles;

    public static void main(String[] args) throws IOException {
        
        try {
            LeerImagen leer = new LeerImagen();
            leer.matrizImagen("C:\\Users\\felip\\Documents\\GitHub\\pruebas-de-codigo-y-algoritmos\\proyetoDistribuidos\\src\\com\\company\\prueba1.pgm");
            //ImageIO.write(img,"png",new File("C:/lawea.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
