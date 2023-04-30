import javax.swing.*;

public class Main extends JFrame {
    public Main () {
        OpenScene openScene = new OpenScene(Constants.X_PIC, Constants.Y_PIC,Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        add(openScene);
        setResizable(false);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        setVisible(true);
        repaint();
    }
}
