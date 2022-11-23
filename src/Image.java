import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Image extends JFrame {
    ImageIcon image;
    JFrame frame;
    JLabel displayField;

    public Image(){
        frame = new JFrame("Image Display test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try {
            image = new ImageIcon(getClass().getResource("Data/serieforsider/24.jpg"));
            displayField = new JLabel(image);
            frame.add(displayField);
        } catch(Exception e){
            System.out.println("Image cannot be found");
        }
        frame.pack();
        frame.setVisible(true);
    }
}
