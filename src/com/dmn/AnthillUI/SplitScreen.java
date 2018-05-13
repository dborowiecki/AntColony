package com.dmn.AnthillUI;

import com.dmn.AnthillContainer;
import com.dmn.Simulation;

import javax.swing.*;
import java.awt.*;
import javax.swing.BoxLayout;

public class SplitScreen extends JFrame {

    private JPanel picturePanel, info;
    private JFrame frame;

    public TextInfo tury      = new TextInfo(" Tour: "+ Simulation.getTura());
    public TextInfo ileMrowek = new TextInfo(" Ants: 20");

    public SplitScreen(AnthillContainer MR){
        super("Programik");

        picturePanel = MR;

        info = new JPanel();
        JPanel test = new JPanel();

        frame = new JFrame("FrameName");
        frame.pack();
        setResizable(false);

        info.setLayout(new BoxLayout(info, BoxLayout.Y_AXIS));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(960, AnthillContainer.Ylen);
        setLocationRelativeTo(null);


        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, new JScrollPane(picturePanel),new JScrollPane(info));
        splitPane.setOneTouchExpandable(false);
        splitPane.setDividerLocation(AnthillContainer.Xlen);
        add(splitPane);

        picturePanel.setBackground(Color.pink);


         setVisible(true);
    }

    void ChangeButtonSize(JButton button, int width, int height){
        button.setPreferredSize(new Dimension(width, height));
    }

    public void createInf(){
        JPanel test = new JPanel();


        JButton M1 = new JButton(); JButton M2 = new JButton(); JButton M3 = new JButton();


        ChangeButtonSize(M1, 40, 40); ChangeButtonSize(M2, 40, 40); ChangeButtonSize(M3, 40, 40);
        test.setMaximumSize(new Dimension(200, 60));
        test.add(M1);
        test.add(M2);
        test.add(M3);
        info.add(tury.getLabel());
        info.add(ileMrowek.getLabel());
        info.add(test, BorderLayout.WEST);
        info.revalidate();
    }
}


