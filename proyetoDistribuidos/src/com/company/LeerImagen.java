package com.company;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Scanner;

public class LeerImagen {

    private String nombreArchivo;
    private int ancho;
    private int alto;
    private int blancoAbsoluto;
    private int[][] pixeles;
    
    public LeerImagen(){}

    public void matrizImagen(String nombreArchivo) throws IOException {
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
        int width = sc.nextInt();
        int height = sc.nextInt();
        int maxVal = Integer.parseInt(br.readLine());

        int[][] originalImage = new int[height + 2][width + 2];
        for (int i = 0; i < height; i++){
            for (int j = 0; j < width; j++){
                originalImage[i + 1][j + 1] = br.read();
                System.out.print(originalImage[i + 1][j + 1] + "|");
            }
            System.out.println(" ");
        }

        f.close();
    }



}
