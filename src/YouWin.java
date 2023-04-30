import javax.swing.*;
import java.awt.*;

public class YouWin extends JPanel {
    private int x ;
    private int y ;
    private int width ;
    private int height ;
    private ImageIcon youWin ;
    private GameScene gameScene ;

    public YouWin (int x, int y, int width, int height) {
        setBounds(x, y, width, height);
        youWin = new ImageIcon("YouWin.png");
        setVisible(true);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        youWin.paintIcon(this, g , Constants.X_PIC, Constants.Y_PIC);
    }
}
