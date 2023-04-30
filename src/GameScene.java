import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GameScene extends JPanel {

    private Frog frog;
    private ImageIcon carLeft;
    private ImageIcon carRight;
    private ImageIcon truckLeft;
    private ImageIcon truckRight;
    private ImageIcon rose;
    private ImageIcon backGround;
    private ImageIcon snake;

    private int yTruckLeft ;
    private int xTruckLeft ;
    private int yTruckRight ;
    private int xTruckRight ;
    private int xCarLeft ;
    private int yCarLeft ;
    private int xCarRight ;
    private int yCarRight ;
    private int xRose ;
    private int yRose ;
    private int xSnake ;
    private int ySnake ;
    private int time ;

    public GameScene (int x, int y, int width , int height) {
        setBackground(Color.GREEN);
        setBounds(x,y,width,height);
        frog = new Frog();
        backGround = new ImageIcon("BackGround.png");
        carLeft = new ImageIcon("Car1-Left.png");
        carRight = new ImageIcon("Car1-Right.png");
        truckLeft = new ImageIcon("Truck-Left.png");
        truckRight = new ImageIcon("Truck-Right.png");
        rose = new ImageIcon("lilyPad.png");
        snake = new ImageIcon("Snake.png");
        ySnake = Constants.Y_SNAKE;
        yTruckLeft = Constants.Y_TRUCK_LEFT;
        yTruckRight = Constants.Y_TRUCK_RIGHT;
        yCarLeft = Constants.Y_CAR_LEFT;
        yCarRight = Constants.Y_CAR_RIGHT;
        this.yRose = Constants.Y_ROSE;
        this.xCarRight = Constants.WINDOW_WIDTH;
        this.xTruckRight = Constants.WINDOW_WIDTH;
        this.time = Constants.TIMER;
    }

    public void mainGameLoop () {
        new Thread(() -> {
           setFocusable(true);
           requestFocus();
           addKeyListener(this.frog);
            System.out.println("Main Game Loop : start");
            while (true) {
                try{
                    if (!collision(frog)) {
                        frog.setYFrog(Constants.WINDOW_HEIGHT - Constants.NEW_POSITION_FROG);
                    }

                    if (xTruckLeft >= Constants.WINDOW_WIDTH + Constants.OBJECT_POSITION) {
                        xTruckLeft -= Constants.WINDOW_WIDTH + Constants.OBJECT_POSITION;
                    }
                    if (xCarLeft >= Constants.WINDOW_WIDTH + Constants.OBJECT_POSITION) {
                        xCarLeft -= Constants.WINDOW_WIDTH + Constants.OBJECT_POSITION;
                    }
                    if (xCarRight <= -Constants.OBJECT_POSITION) {
                        xCarRight += Constants.WINDOW_WIDTH + Constants.OBJECT_POSITION;
                    }
                    if (xTruckRight <= 0) {
                        xTruckRight += Constants.WINDOW_WIDTH + Constants.OBJECT_POSITION;
                    }
                    xTruckLeft += Constants.STEPS;
                    xTruckRight -= Constants.STEPS;
                    xCarLeft += Constants.STEPS;
                    xCarRight -= Constants.STEPS;
                    if (frog.getyFrog() <= 0) {
                        YouWin youWin = new YouWin(Constants.X_PIC, Constants.Y_PIC, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
                        this.add(youWin);
                    }
                    if (time == 0) {
                        GameOver gameOver = new GameOver(Constants.X_PIC, Constants.Y_PIC, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
                        this.add(gameOver);
                    }
                    repaint();
                    Thread.sleep(Constants.MILLIS);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public boolean collision(Frog frog) {
        boolean alive = true;
        if (frog.getyFrog() <= Constants.START_ROAD1 && frog.getyFrog() >= Constants.END_ROAD1) { // ROAD 1
            if (frog.getxFrog() == xTruckLeft
                    || frog.getxFrog() == xTruckLeft - Constants.OBJECT_POSITION_NEW
                    || frog.getxFrog() == xTruckLeft - Constants.OBJECT_POSITION) {
                alive = false;
            }
        }
        if (frog.getyFrog() <= Constants.START_ROAD2 && frog.getyFrog() >= Constants.END_ROAD2) { // ROAD 2
            if (frog.getxFrog() == xTruckRight
                    || frog.getxFrog() == xTruckRight + Constants.OBJECT_POSITION_NEW
                    || frog.getxFrog() == xTruckRight + Constants.OBJECT_POSITION) {
                alive = false;
            }
        }
        if (frog.getyFrog() <= Constants.START_RIVER && frog.getyFrog() >= Constants.END_RIVER) { //RIVER
            if (frog.getyFrog() <= Constants.START_ROSE && frog.getyFrog() >= Constants.END_ROSE) { // ROSE
                System.out.println("rose x:" + xRose + " frog x:" + frog.getxFrog());
                if ((frog.getxFrog() >= xRose - Constants.START_AND_END_ROSE1 && frog.getxFrog() <= xRose + Constants.START_AND_END_ROSE1)
                        ||
                        (frog.getxFrog() >= xRose + Constants.START_ROSE2 && frog.getxFrog() <= xRose + Constants.END_ROSE2)
                        ||
                        (frog.getxFrog() >= xRose + Constants.START_ROSE3 && frog.getxFrog() <= xRose + Constants.END_ROSE3)
                        ||
                        (frog.getxFrog() >= xRose + Constants.START_ROSE4 && frog.getxFrog() <= xRose + Constants.END_ROSE4)) {
                    alive = true;
                } else {
                    alive = false;
                }
            } else {
                alive = false;
            }
        }
        if (frog.getyFrog() <= Constants.START_ROAD3 && frog.getyFrog() >= Constants.END_ROAD3) { // ROAD 3
            if (frog.getxFrog() == xCarLeft
                    || frog.getxFrog() == xCarLeft - Constants.OBJECT_POSITION_NEW
                    || frog.getxFrog() == xCarLeft - Constants.OBJECT_POSITION) {
                alive = false;
            }
        }
        if (frog.getyFrog() <= Constants.START_ROAD4 && frog.getyFrog() >= Constants.END_ROAD4) { // ROAD 4
            if (frog.getxFrog() == xCarRight
                    || frog.getxFrog() == xCarRight + Constants.OBJECT_POSITION_NEW
                    || frog.getxFrog() == xCarRight + Constants.OBJECT_POSITION) {
                alive = false;
            }
        }
        return alive;
    }

    //
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        this.backGround.paintIcon(this, graphics, Constants.X_PIC, Constants.Y_PIC);
        this.carLeft.paintIcon(this, graphics, this.xCarLeft, this.yCarLeft);
        this.carLeft.paintIcon(this, graphics, this.xCarLeft - Constants.OBJECT_POSITION_NEW, this.yCarLeft);
        this.carLeft.paintIcon(this, graphics, this.xCarLeft - Constants.OBJECT_POSITION, this.yCarLeft);
        this.carRight.paintIcon(this, graphics, this.xCarRight, this.yCarRight);
        this.carRight.paintIcon(this, graphics, this.xCarRight + Constants.OBJECT_POSITION_NEW, this.yCarRight);
        this.carRight.paintIcon(this, graphics, this.xCarRight + Constants.OBJECT_POSITION, this.yCarRight);
        this.truckLeft.paintIcon(this, graphics, this.xTruckLeft, this.yTruckLeft);
        this.truckLeft.paintIcon(this, graphics, this.xTruckLeft - Constants.OBJECT_POSITION_NEW, this.yTruckLeft);
        this.truckLeft.paintIcon(this, graphics, this.xTruckLeft - Constants.OBJECT_POSITION, this.yTruckLeft);
        this.truckRight.paintIcon(this, graphics, this.xTruckRight, this.yTruckRight);
        this.truckRight.paintIcon(this, graphics, this.xTruckRight + Constants.OBJECT_POSITION_NEW, this.yTruckRight);
        this.truckRight.paintIcon(this, graphics, this.xTruckRight + Constants.OBJECT_POSITION, this.yTruckRight);
        this.rose.paintIcon(this, graphics, this.xRose, this.yRose);
        this.rose.paintIcon(this, graphics, this.xRose + Constants.ROSE1, this.yRose);
        this.rose.paintIcon(this, graphics, this.xRose + Constants.ROSE2, this.yRose);
        this.rose.paintIcon(this, graphics, this.xRose + Constants.ROSE3, this.yRose);
        this.snake.paintIcon(this, graphics, this.xSnake + Constants.SNAKE1, this.ySnake);
        this.snake.paintIcon(this, graphics, this.xSnake + Constants.SNAKE2, this.ySnake);
        this.snake.paintIcon(this, graphics, this.xSnake + Constants.SNAKE3, this.ySnake);
        this.frog.paintComponent(graphics);
        time--;
        String timer = String.valueOf(time);
        String timeSec = "Frog time left :";
        getGraphics().drawString(timeSec + timer, Constants.X_TIME, Constants.Y_TIME);
    }
}
