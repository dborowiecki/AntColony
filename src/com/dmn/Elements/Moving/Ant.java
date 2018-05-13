package com.dmn.Elements.Moving;

import com.dmn.Elements.Static.AnthillElement;
import com.dmn.Elements.Static.Camp;
import com.dmn.AnthillContainer;
import com.dmn.Elements.Static.Leaf;
import com.dmn.behaviour.Actions;
import com.dmn.other.ImageManage;
import com.dmn.other.Position;

public class Ant extends MovableElement {

    public Ant() {
        setElementImage(ImageManage.readImage("Grafika/basicAnt.png"));
        isStatic = false;
    }

    int wielkoscObrazka = 30;
    public String getFraction() {
        return fraction;
    }

    String fraction = "Other";
    public Position campPosition;


    public void setFraction(String fraction, AnthillContainer M) {
        this.fraction = fraction;
        for (Camp c : M.getCamps()) {
            if (c.name == fraction) {
                campPosition = c.position;

            }
        }
    }

    public void lvlUP() {
        if (movementSpeed < 3.5) movementSpeed += 0.02;
        if (wielkoscObrazka < 60) wielkoscObrazka += 1;
    }

    public int getWielkoscObrazka() {
        return wielkoscObrazka;


    }


    public void followTarget(AnthillElement[] liscie, com.dmn.AnthillContainer M){
        if(isPerformingAction){ //
            moveElementToPoint((int)FollowedElement.position.GetX(), (int)FollowedElement.position.GetY());
            isMoving=false;
        }
        if(Actions.CheckCollision(position, FollowedElement.position, 30)) {
            Actions.RemoveElementFromArray(liscie, FollowedElement, true, M);
            stopMoving();
            M.revalidate();
            lvlUP();
            M.revalidate();
        }
    }

    public void returnToCamp(com.dmn.AnthillContainer M){
        if(isPerformingAction){
            moveElementToPoint((int)campPosition.GetX(), (int)campPosition.GetY());
            isMoving=false;
        }
        if(Actions.CheckCollision(position, campPosition, 60)) {
            Wait(10);
            isPerformingAction=false;
            isPerformingAction=false;

            for(Camp c: M.getCamps()){
                if(getFraction().contains(c.getName())) {
                    if(c.imageSizeMultipler<0.53) c.grow(0.01f);
                }
            }
        }
        M.revalidate();
    }

    public void searchForNearestLeaf(Leaf[] liscie){
        if(!isPerformingAction) {
            FollowedElement=Actions.findNearestElement(this, liscie, 450);
            if(FollowedElement!=null && !isPerformingAction) isPerformingAction=true;
        }
    }
}










