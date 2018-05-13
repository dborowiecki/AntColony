package com.dmn.other;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageManage{
    static public BufferedImage readImage(String sciezka) {
        BufferedImage imageFile = null;// = new File(sciezka);
        // System.out.print(imageFile.getAbsolutePath());
        try {
            imageFile = ImageIO.read(new File(sciezka));
        } catch (IOException e) {
            System.err.println("Blad odczytu obrazka");
            e.printStackTrace();
        }
        return imageFile;
    }

    static public AffineTransformOp rotateImageFilter(BufferedImage image, double KatRadian){
        double rotationRequired = KatRadian;//Math.toRadians (45);
        double locationX = image.getWidth() / 2;
        double locationY = image.getHeight() / 2;
        AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BICUBIC);

        return op;
    }
    static public void changeImageColor(BufferedImage image, Color color) {
        for (int col = 0; col < image.getWidth(); col++) {
            for (int row = 0; row < image.getHeight(); row++) {
                Color pixel = new Color(image.getRGB(col, row), true); //bierze kolor pixela
                if(pixel.getRGB()==0) continue; //jeśli alpha jest równe 0 to nie zmienia
                int r = color.getRed();//.getPrimitiveType();
                int g = color.getGreen();//.getPrimitiveType();
                int b = color.getBlue();//.getPrimitiveType();
                int a = color.getAlpha();

                double L = GetPixelLuminence(pixel); //ustala jasność pixela
                Color newColor = new Color((int) (r * L / 255), (int) (g * L / 255), (int) (b * L / 255), a); //na podstawie jasności ustala nowy kolor

                image.setRGB(col, row, newColor.getRGB());

            }

        }
    }
    static public int GetPixelLuminence(Color color){
        double pixelLuminence=0.2126*color.getRed() + 0.7152*color.getGreen() + 0.0722*color.getBlue(); //jasność pixela

        return (int)pixelLuminence;
    }
}