package com.dmn.Elements.Static;

import com.dmn.other.ImageManage;

public class Grass extends AnthillElement {
    public int count() {
        return quantity;
    }

    private int quantity = 2;
    public Grass(){
        setElementImage(ImageManage.readImage("Grafika/GrassV1.png"));
        imageSizeMultipler=0.002f;
    }
    public void update(){
        super.update();

        if(age<20) {
            imageSizeMultipler+=0.02;
            quantity+=2;
            position.setpositionX(position.GetX()-0.02*elemImg.getWidth()/2);
            position.setpositionY(position.GetY()-0.02*elemImg.getHeight()/2);
        }
        if(age>20) {
            imageSizeMultipler-=0.01;
            position.setpositionX(position.GetX()+0.01*elemImg.getWidth()/2);
            position.setpositionY(position.GetY()+0.01*elemImg.getHeight()/2);
            quantity+=-2;
        }

    }
}

