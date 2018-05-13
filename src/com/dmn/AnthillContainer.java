package com.dmn;

import com.dmn.Elements.Static.*;
import com.dmn.Elements.Moving.*;
import com.dmn.other.ImageManage;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static java.lang.Thread.yield;

public class AnthillContainer extends JPanel {

    public static final int Xlen=760;
    public static final int Ylen=560;
    BufferedImage borderImage = ImageManage.readImage("Grafika/border3.png");

    Camp RedCamp = new Camp("Red", Color.RED);
    Camp BlueCamp = new Camp("Blue", Color.BLUE);
    Camp GreenCamp = new Camp("Green", Color.GREEN);
    List<Camp> camps = new ArrayList<>();
            public List<Camp> getCamps() {
            return camps;
        }

    List<AnthillElement> toRender = new LinkedList<>();

    public AnthillContainer() {
        this.setBackground(Color.pink);
        RedCamp.position.setposition(90, 90);
        BlueCamp.position.setposition(650, 90);
        GreenCamp.position.setposition(650, 400);
        camps.add(RedCamp); camps.add(BlueCamp); camps.add(GreenCamp);
        toRender.addAll(camps);
    }
    
    public void addElement(AnthillElement e){
        toRender.add(e);
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.drawImage(borderImage, -2, -20, null);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        try {

            for (AnthillElement l : toRender){

                renderElement(g2, l);
            }

        }
        catch (Exception e){
            System.out.println("Error occurred: "+e);
        }
    }

    public static void renderElement(Graphics2D g, AnthillElement l){
        BufferedImage image = l.getelemImg();

        if(l.isStatic) {
            int xSize = (int) (image.getWidth() * l.getImageSizeMultipler());
            int ySize = (int) (image.getHeight() * l.getImageSizeMultipler());
            g.drawImage(image, (int) l.position.GetX(), (int) l.position.GetY(), xSize, ySize, null);
        }
        if(!l.isStatic){
            g.drawImage(l.zwrot.filter(image, null),
                    (int) l.position.GetX(), (int) l.position.GetY(),
                    l.getWielkoscObrazka(), l.getWielkoscObrazka(), null);
        }
    }

}
