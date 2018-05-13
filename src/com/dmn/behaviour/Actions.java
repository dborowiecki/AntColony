package com.dmn.behaviour;

import com.dmn.Elements.Static.AnthillElement;
import com.dmn.other.Position;


public class Actions{

    public static AnthillElement findNearestElement(AnthillElement element, AnthillElement[] fromElements, int maxDistance){
        AnthillElement nearest=null;
        int elementX = (int)element.position.GetX();
        int elementY = (int)element.position.GetX();
        for (AnthillElement l : fromElements) {
            if(l==null)continue;
            double x = l.position.GetX() - elementX;
            double y = l.position.GetY() - elementY;
            int lDistance = (int)Math.sqrt(x*x+y*y);

            if (lDistance < maxDistance) {
                maxDistance=lDistance;
                nearest = l;
            }
        }
        return nearest;
    }

    public static void RemoveElementFromArray(AnthillElement[] array, AnthillElement toRemove, boolean createNew, com.dmn.AnthillContainer M){
        for(int i=0; i<array.length; i++){
            if(array[i].position.GetX()==toRemove.position.GetX()){
                if(createNew){
                    AnthillElement.insertElement(array[i], M);
                }
                break;
            }
            M.revalidate();
        }
    }

    public static boolean CheckCollision(Position first, Position second, int offset){
        int DystansX = ((int) (first.GetX() - (second.GetX())));
        int DystansY = ((int) (first.GetY() - (second.GetY())));

        if(Math.abs(DystansX)<1+offset&&Math.abs(DystansY)<1+offset) return true;
        else return false;
    }
}
