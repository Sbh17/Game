import javax.swing.*;
import java.awt.*;

public class OpenScene extends JPanel {
    private int x;
    private int y;
    private int width;
    private int height;
    private ImageIcon openPic ;
    private GameScene gameScene;

    public OpenScene (int x, int y, int width, int height) {
        setBounds(x, y, width,height);
        openPic = new ImageIcon("GreenFrog.png");
        gameScene = new GameScene(x, y, width, height);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        InformationScene is = new InformationScene(Constants.X_PIC, Constants.Y_PIC, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        openPic.paintIcon(this, g, Constants.X_PIC, Constants.Y_PIC);
        JButton button = new JButton("Start Game ");
        button.setBounds(Constants.X_BUTTON, Constants.Y_BUTTON1, Constants.BUTTON_WIDTH, Constants.BUTTON_HEIGHT);
        this.add(button);
        button.setOpaque(true);
        button.setBackground(Color.GREEN);
        JButton secButton = new JButton("Information");
        secButton.setBounds(Constants.X_BUTTON , Constants.Y_BUTTON2 , Constants.BUTTON_WIDTH , Constants.BUTTON_HEIGHT);
        this.add(secButton);
        secButton.setOpaque(true);
        secButton.setBackground(Color.ORANGE);
        button.addActionListener((event) -> {
            this.remove(button);
            this.remove(secButton);
            this.add(gameScene);
            gameScene.mainGameLoop();
        });

        secButton.addActionListener((event) -> {
            this.add(is);
            repaint();
            this.remove(secButton);
        });
    }
}
