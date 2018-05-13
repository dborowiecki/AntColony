package com.dmn.Elements.Static;

import com.dmn.other.ImageManage;

public class Leaf extends AnthillElement {
    public Leaf(){
        setElementImage(ImageManage.readImage("Grafika/leaf.png"));
        imageSizeMultipler=0.3f;
    }
}
