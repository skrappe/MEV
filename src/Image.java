import javax.swing.*;
import java.awt.*;
import java.util.Objects;
import java.util.*;

public class Image extends JFrame {
    ImageIcon image;
    JFrame frame;
    JLabel displayField;

    public Image(){
        frame = new JFrame("Netflix");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try {
            image = new ImageIcon(getClass().getResource("./Images/Braveheart.jpg"));
            displayField = new JLabel(image);
            frame.add(displayField);
        } catch(Exception e){
            System.out.println("Image cannot be found");
        }
        frame.pack();
        frame.setVisible(true);
    }
}
