
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Weapon extends JPanel implements ActionListener, MouseListener, ChangeListener {
    Button button = new Button();

    ArrayList<ArrayList<Double>> AllBullets = new ArrayList();

    Image Triangle;
    int imgWidth = 40;
    int imgHeight = 40;

    int [] PolygonX = {(int) (imgWidth*0.75),imgWidth/4, (int) (imgWidth*0.75)};
    int [] PolygonY = {(int) (imgHeight*0.75),imgHeight/4,imgHeight/4};

    int x1 = 100;
    int y1 = 100;
    int Test = MouseInfo.getPointerInfo().getLocation().x;
    double PlayerRotation;
    long yLenght;
    long xLenght;
    int xBullet;
    int yBullet;

    Timer timer = new Timer(10, this);

    Weapon() {

        Triangle = new ImageIcon("Triangle.png").getImage();

        this.add(button);
        addMouseListener(this);


        this.setPreferredSize(new Dimension(500, 500));
        this.setVisible(true);
        this.setBackground(Color.CYAN);


    }


    public void paint(Graphics g) {

        super.paint(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.RED);

        for (int i = AllBullets.size() ;i >= 1  ;i--){
            g2d.setColor(new Color(255,69,0));
            g2d.fillOval((int) (AllBullets.get(i-1).get(0) + 5), (int) (AllBullets.get(i-1).get(1) + 5),10,10);

        }
        g2d.rotate(PlayerRotation,x1+imgWidth/2,y1+imgHeight/2);
        g2d.drawImage(Triangle,x1,y1,imgWidth,imgHeight,null);
    }

    public void actionPerformed(ActionEvent ae) {
        yLenght = (long) ((this.getMousePosition().getY()) - y1);
        xLenght = (long) ((this.getMousePosition().getX()) - x1);
        PlayerRotation = Math.atan2(yLenght, xLenght);
        for (int i = AllBullets.size() ;i >= 1  ;i--) {
            if (AllBullets.get(i - 1).get(0)==AllBullets.get(i - 1).get(2) && AllBullets.get(i - 1).get(3) < AllBullets.get(i - 1).get(1) ){
                AllBullets.get(i - 1).set(3,2500 + (AllBullets.get(i - 1).get(7) * AllBullets.get(i - 1).get(2) + AllBullets.get(i - 1).get(8)));
            }
            else if (AllBullets.get(i - 1).get(0)==AllBullets.get(i - 1).get(2) && AllBullets.get(i - 1).get(3) > AllBullets.get(i - 1).get(1)){
                AllBullets.get(i - 1).set(3,2500 - (AllBullets.get(i - 1).get(7) * AllBullets.get(i - 1).get(2) + AllBullets.get(i - 1).get(8)));
            }
            else {
                AllBullets.get(i - 1).set(3, AllBullets.get(i - 1).get(7) * AllBullets.get(i - 1).get(2) + AllBullets.get(i - 1).get(8));
            }

        }

        for (int i = AllBullets.size() ;i >= 1  ;i--) {
            AllBullets.get(i - 1).set(5, (AllBullets.get(i - 1).get(2)) - (AllBullets.get(i - 1).get(0)));//xLength
            AllBullets.get(i - 1).set(6, (AllBullets.get(i - 1).get(3)) - (AllBullets.get(i - 1).get(1)));//yLength
            AllBullets.get(i - 1).set(4, (Math.atan2(AllBullets.get(i - 1).get(6), AllBullets.get(i - 1).get(5))));//Rotation
        }
        for (int i = AllBullets.size() ;i >= 1  ;i--) {
            if (AllBullets.get(i - 1).get(6) > -3 && AllBullets.get(i - 1).get(6) < 3 || AllBullets.get(i - 1).get(5) < 3 && AllBullets.get(i - 1).get(5) > -3) {
                AllBullets.remove(i-1);
            } else {
                AllBullets.get(i - 1).set(0, (AllBullets.get(i - 1).get(0) + (Math.cos(AllBullets.get(i - 1).get(4)) * 1500 * 0.01)));//xMovement
                AllBullets.get(i - 1).set(1, (AllBullets.get(i - 1).get(1) + (Math.sin(AllBullets.get(i - 1).get(4)) * 1500 * 0.01)));//yMovement
            }
        }

        System.out.println(Math.toDegrees(PlayerRotation));


        if(yLenght>-3 && yLenght < 3 || xLenght < 3 && xLenght > -3){

        }
        else{
            x1 = x1 + (int) (Math.cos(PlayerRotation) * 500 * 0.01);
            y1 = y1 + (int) (Math.sin(PlayerRotation) * 500 * 0.01);
        }
       /*if ((Math.toDegrees(PlayerRotation)-45)>360){
           PlayerRotation = PlayerRotation - Math.toRadians(360) ;
       }*/
        for (int i = AllBullets.size() ;i >= 1  ;i--){
            if (AllBullets.get(i-1).get(0) >= this.getWidth() || AllBullets.get(i-1).get(0) <= 0) {
                AllBullets.remove(i-1);
            }
            else if (AllBullets.get(i-1).get(1) >= this.getHeight() || AllBullets.get(i-1).get(1) <= 0){
                AllBullets.remove(i-1);
            }

        }
        repaint();
    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        this.CreateBullet();

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        timer.start();
        System.out.println("Started");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        timer.stop();
        System.out.println("Stopped");
    }

    @Override
    public void stateChanged(ChangeEvent e) {

    }
    public void CreateBullet (){
        ArrayList<Double> Bullet = new ArrayList<>();
        Bullet.add(x1+0.0);// x1 Index = 0
        Bullet.add(y1+0.0);// y1 Index = 1
        if (x1 > this.getMousePosition().getX() ){
            Bullet.add((this.getMousePosition().getX())-500);//x2 Index = 2
        }
        else if (x1 < this.getMousePosition().getX() ) {
            Bullet.add((this.getMousePosition().getX()) + 500);//x2 Index = 2
        }
        else {
            Bullet.add((this.getMousePosition().getX()));//x2 Index = 2
        }

        //Bullet.add((this.getMousePosition().getX())*1);//x2 Index = 2
        Bullet.add((this.getMousePosition().getY()));//y2 Index = 3
        Bullet.add(0.0); //Rotation Index = 4
        Bullet.add(0.0); //xLength Index = 5
        Bullet.add(0.0); //yLength Index = 6
        Bullet.add((this.getMousePosition().getY()-y1)/(this.getMousePosition().getX()-x1));//LineareFunktion : m Index = 7
        Bullet.add(y1 - ((this.getMousePosition().getY()-y1)/(this.getMousePosition().getX()-x1))* x1); //LineareFunktion : b Index = 8
        AllBullets.add(Bullet);


    }
}
