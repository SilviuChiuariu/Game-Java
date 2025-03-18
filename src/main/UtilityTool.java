package main;

import java.awt.*;
import java.awt.image.BufferedImage;

//PASTREAZA FUNCTIILE UTILE PE TOT PARCURSUL JOCULUI PT O ORDONARE MAI BUNA SI EFICIENTA
public class UtilityTool {
    //METODA UTILA LA DESENAREA TILE-URILOR DIN MAPA
    public BufferedImage scaleImage(BufferedImage original, int width, int height){

        BufferedImage scaledImage=new BufferedImage(width, height, original.getType());  //transmitem marimea si tipul imaginii la instantiere
        Graphics2D g2=scaledImage.createGraphics();                                      //orice va desena g2 se va salva in scaledImage
        g2.drawImage(original, 0, 0, width, height, null);                 //dimensiunea va fii salvata in scaledImage
        g2.dispose();

        return scaledImage;
    }
}
