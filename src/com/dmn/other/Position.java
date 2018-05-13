package com.dmn.other;

public class Position{
    private double positionX=1;
    private double positionY=1;
    public double dystans;
    public double deltaX, deltaY;

    public void setpositionX(double value){
        positionX=value;
    }
    public void setpositionY(double value){
        positionY=value;
    }
    public void setposition(double X, double Y){
        positionX=X;
        positionY=Y;
    }
    public double GetX(){return positionX; }
    public double GetY(){return positionY; }



}
