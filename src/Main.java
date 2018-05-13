import com.dmn.AnthillUI.SplitScreen;
import com.dmn.AnthillContainer;
import com.dmn.Simulation;

public class Main {
    //Change for different time of one tour
    public static final int tourDuration=500;
    public static final int refreshTime=tourDuration/100+1;

    public static void main(String args[]){
        AnthillContainer M = new AnthillContainer();
        SplitScreen windowInterface = new SplitScreen(M);
        windowInterface.createInf();
        Simulation s = new Simulation(windowInterface, M,tourDuration, refreshTime);

        s.runSimulation();
    }

}
