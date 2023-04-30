import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Frog extends JPanel implements KeyListener {

    private ImageIcon frog;
    private int xFrog ;
    private int yFrog;

    public Frog () {
        this.frog = new ImageIcon("FrogUp.png");
        this.yFrog = Constants.WINDOW_HEIGHT - 75;
        this.xFrog = Constants.WINDOW_WIDTH / 2 ;
    }

    public int getxFrog () {
        return xFrog;
    }

    public void setXFrog (int x) {
        xFrog = x;
    }

    public int getyFrog () {
        return yFrog;
    }

    public void setYFrog (int y) {
        yFrog = y;
    }

    protected void paintComponent (Graphics graphics) {
        super.paintComponent(graphics);
        this.frog.paintIcon(this,graphics,xFrog,yFrog);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed (KeyEvent e) {
        int keyCode = e.getKeyCode();

        switch (keyCode) {
            case KeyEvent.VK_RIGHT :
                this.moveRight();
                break;
            case KeyEvent.VK_LEFT:
                moveLeft();
                break;
            case KeyEvent.VK_UP:
                moveUp();
                break;
            case KeyEvent.VK_DOWN:
                moveDown();
                break;
            case KeyEvent.VK_SPACE:
                jumpUp();
                break;

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void moveRight () {
        this.xFrog += Constants.STEPS;
        System.out.println("Move Right :" + xFrog);
        if (this.xFrog >= Constants.WINDOW_WIDTH - Constants.MOVE_RIGHT) {
            xFrog -= Constants.STEPS;
        }
    }

    public void moveLeft () {
        this.xFrog -= Constants.STEPS;
        System.out.println("Move Left :" + xFrog);
        if (this.xFrog <= 0) {
            xFrog += Constants.STEPS;
        }
    }

    public void moveUp () {
        this.yFrog -= Constants.STEPS;
        System.out.println("Move up :" + yFrog);
    }
    public void moveDown () {
        this.xFrog += Constants.STEPS;
        System.out.println("Move Down : " + yFrog);
        if (this.yFrog >= Constants.WINDOW_HEIGHT - Constants.MOVE_DOWN){
            yFrog -= Constants.STEPS;
        }
        System.out.println("Lower Border");
    }

    public void jumpUp () {
        yFrog -= Constants.JUMP_UP;
        System.out.println("Move up : Frog jump up : " + this.yFrog);
    }
}
