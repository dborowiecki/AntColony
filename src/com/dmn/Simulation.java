package com.dmn;

import com.dmn.Elements.Static.AnthillElement;
import com.dmn.AnthillUI.SplitScreen;
import com.dmn.Elements.Moving.Ant;
import com.dmn.Elements.Static.Grass;
import com.dmn.Elements.Static.Leaf;
import com.dmn.behaviour.Actions;
import com.dmn.other.RandomValues;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static java.lang.Thread.yield;

public class Simulation {
    public static  int tourDuration;
    public static  int refreshTime;

    int runningTime=0;
    private Ant m = new Ant();
    private com.dmn.AnthillContainer M;
    private SplitScreen ui;
    private Leaf[] leafs = new Leaf[3];
    private Grass[] grass = new Grass[10];
    private List<Ant> otherAnts = new ArrayList<>();
    private Ant[] Robotnicy = new Ant[20];
    private static int tura=0;
    public static int getTura() { return tura;}
    int k=0;//Number of ants
    int n=0;//Grass
    private boolean  canAddAnt=true;
    private boolean canAddGrass=true;
    private boolean canUpdate=true;

    public Simulation(SplitScreen ui, com.dmn.AnthillContainer M, int tourDuration, int refreshTime){
        this.tourDuration = tourDuration;
        this.refreshTime = refreshTime;
        this.M = M;
        this.ui = ui;
    }
    public Simulation(com.dmn.AnthillContainer M, int tourDuration, int refreshTime,
                      int maxLeafs, int maxGrass, int maxAnts){
        this.tourDuration = tourDuration;
        this.refreshTime = refreshTime;
        this.M = M;
        this.leafs = new Leaf[maxLeafs];
        this.grass = new Grass[maxGrass];
        this.Robotnicy = new Ant[maxAnts];
    }

    public void runSimulation(){
        for(int i=0; i<leafs.length; i++)leafs[i]=new Leaf();

        m.insertElement(m, M);

        try
        {
            Thread.sleep(refreshTime);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }

        for(Leaf l:leafs) AnthillElement.insertElement(l, M);

        nextFrame();
        while (true){
            nextFrame();
            ui.tury.changeText("  Tour: "+getTura());
            ui.ileMrowek.changeText("  Ants:"+(k+1));

        }
    }

    private  void Wait(){
        try
        {
            Thread.sleep(refreshTime);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
    }

    private  void AddNewAnt(){
        if(tura%5==1 &&k<20 && canAddAnt){
            canAddAnt=false;
            Robotnicy[k]=new Ant();

            Robotnicy[k].changeColor(RandomValues.randomColorRGB());
            switch (Robotnicy[k].getObjColor().toString()){
                case "java.awt.Color[r=255,g=0,b=0]": Robotnicy[k].setFraction("Red", M);
                    break;
                case "java.awt.Color[r=0,g=255,b=0]": Robotnicy[k].setFraction("Green", M);
                    break;
                case "java.awt.Color[r=0,g=0,b=255]": Robotnicy[k].setFraction("Blue", M);
                    break;
                default: Robotnicy[k].setFraction("Other", M);
            }
            Ant.insertElement(Robotnicy[k], M); k++;
        }
        if(tura%5==2) canAddAnt=true;
    }

    private  void AddGrass() {
        if (tura % 7 == 6 && n < 10 &&canAddGrass) {
            canAddGrass = false;

            grass[n] = new Grass();

            Grass.insertElement(grass[n], M);
            n++;
            M.revalidate();
        }

        if(tura%7==2) canAddGrass=true;


    }

    private void UpdateAllAnts(){
        for(Ant z: otherAnts) z.moveElementRandomDirection();
        for(int i=0; i<k; i++){
            if(!Robotnicy[i].isPerformingAction) Robotnicy[i].moveElementRandomDirection();
            if(Robotnicy[i].isPerformingAction) Robotnicy[i].returnToCamp(M);


            Grass t = (Grass) Actions.findNearestElement(Robotnicy[i], grass, 30);

            if(t!=null){

                t.age=2;
                t.imageSizeMultipler=0.2;
                M.toRender.removeIf(toDelete -> toDelete == t);

                Robotnicy[i].isPerformingAction=true;

            }
        }
    }

    private boolean mozna=true;
    private void UpdateSpecialAnt(){
        if(!m.isPerformingAction) m.searchForNearestLeaf(leafs);

        if(m.isPerformingAction) m.followTarget(leafs, M);
        else m.moveElementRandomDirection();
        if(m.getCooldown()<=0 && tura>20){
            for(int i=0; i<k; i++){
                if(Actions.CheckCollision(m.position, Robotnicy[i].position, 10)){
                    Ant em = new Ant();
                    em.changeColor(RandomValues.randomColor(250, 250, 250));
                    AnthillElement.insertElement(em, M);
                    otherAnts.add(em);
                    m.addCooldown(10);
                    break;
                }
            }
        }
        M.revalidate();
        if(tura%2==1 && mozna){
            mozna=false;
            m.update();
        }
        if(tura%2==0) mozna=true;
    }

    private void UpdateAllGrass(){
        if(tura%2==1 && canUpdate) {
            canUpdate=false;
            for (int i = 0; i < n; i++) {
                grass[i].update();
                if (grass[i].getAge() > 30) System.out.println("WIEK: " + grass[i].getAge());

                if(grass[i].count()<=1) AnthillElement.insertElement(grass[i], M);
            }
        }
        if(tura%2==0) canUpdate=true;
    }

    private void nextFrame(){
        runningTime+=refreshTime;
        tura=runningTime/tourDuration;
        Wait();

        AddNewAnt();
        AddGrass();
        UpdateAllAnts();
        UpdateSpecialAnt();
        UpdateAllGrass();
        M.revalidate();
        M.repaint();
    }

}
