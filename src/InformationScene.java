import javax.swing.*;
import java.awt.*;

public class InformationScene extends JPanel {
    private int x ;
    private int y ;
    private int width;
    private int height;
    private ImageIcon information;

    public InformationScene (int x, int y, int width , int height) {
        setBounds(x , y, width, height);
        information = new ImageIcon("Information.png");
        setBackground(Color.GREEN);
        setVisible(true);
        repaint();
    }

    public void paintComponont (Graphics graphics) {
        super.paintComponent(graphics);
        information.paintIcon(this, graphics , Constants.X_PIC, Constants.Y_PIC);
    }
}
