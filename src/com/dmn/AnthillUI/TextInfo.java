package com.dmn.AnthillUI;

import javax.swing.*;
import java.awt.*;

public class TextInfo extends JPanel {
    private JLabel label;
        public JLabel getLabel(){
        return label;
    }
    Font font = new Font("Verdana", 1, 15);
    private String message;

    public TextInfo(String message){
        this.message=message;
        label = new JLabel(message);
        label.setFont(font);
        label.setMaximumSize(new Dimension(150, 20));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    public  void changeText(String newContent){
        this.label.setText(newContent);
    }

}