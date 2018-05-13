package com.dmn.Elements.Moving;

import com.dmn.Elements.Static.AnthillElement;
import com.dmn.other.ImageManage;
import com.dmn.other.RandomValues;

public class MovableElement extends AnthillElement {
    private double radianAngle=0;
        public double getradianAngle() {
            return radianAngle;
        }
        public void setradianAngle(double katStopnie) {
            radianAngle+= Math.toRadians(katStopnie);
            zwrot = ImageManage.rotateImageFilter(elemImg, radianAngle);
        }

    private int cooldown;
        public int getCooldown() {
            return cooldown;
        }

    public boolean isMoving=false;
    public boolean isPerformingAction=false;
    int actualStep=0;
    public AnthillElement FollowedElement;

    double stepLen = 100;
        public double getDlugoscKroku() {
            return stepLen;
        }

    public double movementSpeed = 1;


    @Override
    public void update() {
        super.update();
        if(cooldown!=0) cooldown--;
    }
    public void addCooldown(int value){
        if(value>=0)cooldown+=value;
    }



    protected static int relativeSpeed= 100;  //Default 100, tourDuration/refreshTime+1

    public void setRandomDestinationPoint(){
        double angle = RandomValues.LosujZakres(0,360);

        setradianAngle(angle);

        position.deltaX = (Math.cos(getradianAngle())*(double)getDlugoscKroku());
        position.deltaY = (Math.sin(getradianAngle())*(double)getDlugoscKroku());
        position.dystans=getDlugoscKroku();

        isMoving=true;
    }

    public void setPrecisePointP(int x, int y) {
        double newX = x+20;
        double newY = y;
        double deltaX =  (newX-position.GetX());
        double deltaY =  (newY-position.GetY());
        position.deltaX=deltaX;
        position.deltaY=deltaY;
        position.dystans=Math.abs(deltaX)+Math.abs(deltaY);

        deltaY/=(position.dystans/stepLen);
        deltaX/=(position.dystans/stepLen);
        position.dystans=4000;

        if(actualStep<2){
           setradianAngle(Math.toDegrees(-getradianAngle()));
           double AngledeltaY = (y - position.GetY());
           double AngledeltaX = (newX - position.GetX());
           setradianAngle(Math.toDegrees(Math.atan2(AngledeltaY, AngledeltaX)));
        }
        if ((movementSpeed * actualStep) > position.dystans) {
        } else {
            WykonajKrok(deltaX, deltaY, movementSpeed/ relativeSpeed);
        }
    }

    public void Lerp() {
        //sprawdza czy mrowka powinna wybrać nowy kierunek
        if ((movementSpeed*actualStep)>position.dystans) {
            stopMoving();
        } else {
            WykonajKrok(position.deltaX, position.deltaY, movementSpeed/relativeSpeed);
        }
    }


    protected  void Wait(long time){
        try
        {
            Thread.sleep(time);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
    }

    public void WykonajKrok(double moveX, double moveY, double deltaTime){
        double deltaX = deltaTime * moveX;
        double deltaY = deltaTime * moveY;

        double EndX= position.GetX() +  deltaX;
        double EndY= position.GetY() +  deltaY;

        actualStep++;

        if(checkBound(EndX, EndY)){
            position.setpositionX(EndX);
            position.setpositionY(EndY);
        }
        else stopMoving();
    }

    public void stopMoving(){
        isMoving=false;
        isPerformingAction=false;
        actualStep=0;
        position.dystans=0;
    }

    public boolean checkBound(double X, double Y){
        if(X<720 && X>30 && Y<480 &&Y>30) return true;
        else return false;
    }

    public  void moveElementRandomDirection(){
        if(!isMoving) setRandomDestinationPoint();
        else Lerp();
    }

    public  void moveElementToPoint(int x, int y){
        if(!isMoving)setPrecisePointP(x, y);
    }
}
