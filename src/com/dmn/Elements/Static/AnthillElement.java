package com.dmn.Elements.Static;

import com.dmn.AnthillContainer;
import com.dmn.other.ImageManage;
import com.dmn.other.Position;
import com.dmn.other.RandomValues;

import java.awt.*;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public class AnthillElement{

    public  boolean isStatic = true;
    public Position position = new Position();



    public int age=0;
    public int getAge() {
        return age;
    }
    private Color objColor;
    protected BufferedImage elemImg;
    public double imageSizeMultipler=0.002;// = 0.4f;
    public BufferedImage getelemImg() {
        return elemImg;
    }
    public AffineTransformOp zwrot;
    protected void setElementImage(BufferedImage elemImg) {
        this.elemImg = elemImg;
    }
    public double getImageSizeMultipler() {
        return imageSizeMultipler;
    }
    public void changeColor(Color color){
        ImageManage.changeImageColor(elemImg, color);
        objColor=color;
    }
    public Color getObjColor(){
        return  objColor;
    }


    private static void addToAnthill(AnthillElement e, AnthillContainer M){
        M.addElement(e);
    }
    
    static public void insertElement(AnthillElement Obj, AnthillContainer M){
        boolean Poprawnosc=false;
        while (!Poprawnosc) {
            Obj.position.setpositionY(RandomValues.LosujZakres(8, 54-2-4)*10); //pomnozyc razy 10
            Obj.position.setpositionX(RandomValues.LosujZakres(8, 54-2-4)*10);

            Poprawnosc=true; //dodać warunek występowania

        }
        addToAnthill(Obj, M);


    }
    public int getWielkoscObrazka(){ return 30;}
    public void update(){
        age++;
    }
}



 






