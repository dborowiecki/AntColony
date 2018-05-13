package com.dmn.other;

import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

public class RandomValues{
    static int WylK=0, WylA=0;
    public static int LosujZWagami(int a, int aX, int...argumentyIWagi){
        int n=aX, k=0, r; //n to suma wag, k to sprawdzanie kolejnych, a r to wartoc wylosowana

        for(int wagi: argumentyIWagi){
            if(wagi%2==1)n+=argumentyIWagi[wagi];
        }

        r=LosujZakres(0, n);


        if(r<aX){WylA++; return a;}
        k+=aX;
        int Kolejna=1;
        while(k<n){

            if(r>=k) {WylK++; break;}// return argumentyIWagi[Kolejna-1];
            else {
                k+=argumentyIWagi[Kolejna];
                Kolejna+=2;
            }
        }


        return argumentyIWagi[Kolejna-1];
    }
    public static int LosujZakres(int min, int max){
        int randomNum = ThreadLocalRandom.current().nextInt(min, max);
        return randomNum;

    }
    public static Color randomColorRGB(){
        Color color;
        int i=LosujZakres(1, 4);
        switch (i) {
            case 1:  color= Color.RED;
                break;
            case 2:  color= Color.GREEN;
                break;
            case 3:  color= Color.BLUE;
                break;
            default: color= Color.white;
                break;
        }
        return color;
    }
    public static Color randomColor(int rMax, int gMax, int bMax){
        int r = LosujZakres(0, rMax); int g=LosujZakres(0, gMax); int b=LosujZakres(0, bMax);
        return new Color(r, g, b);
    }
}
