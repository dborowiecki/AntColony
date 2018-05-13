package com.dmn.Elements.Static;

import com.dmn.other.ImageManage;

import java.awt.*;
import java.util.List;

public class Camp extends AnthillElement {
    public String getName() {
        return name;
    }

    public String name;
    Color resColor;
    public Camp(String name, Color color){
        this.name=name;
        this.setElementImage(ImageManage.readImage("Grafika/Grass2.png"));
        resColor=color;
        ImageManage.changeImageColor(this.elemImg, resColor);
        this.imageSizeMultipler=0.05;

    }
    float power=0;
    public void addPower(float amound){
        power+=amound;

    }
    public void grow(float amound){
        if(imageSizeMultipler*getelemImg().getHeight()<4500){
            addPower(amound*10);
            imageSizeMultipler += amound;
            position.setpositionX(position.GetX() - amound * elemImg.getWidth() / 2);
            position.setpositionY(position.GetY() - amound * elemImg.getHeight() / 2);

        }
    }
    private static void bubbleSort(List<Grass> intArray) {

        int n = intArray.size();
        Grass temp;

        for(int i=0; i < n; i++){
            for(int j=1; j < (n-i); j++){

                if(intArray.get(j-1).imageSizeMultipler < intArray.get(j).imageSizeMultipler){
                    //swap the elements!
                    temp = intArray.get(j-1);
                    intArray.set(j-1, intArray.get(j));
                    intArray.set(j, temp);
                }

            }
        }
    }
}

