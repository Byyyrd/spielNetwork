import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class Weapon implements MouseListener, ActionListener {
    double y1;
    double x1;
    long yLenght;
    long xLenght;
    double playerRotation;
    boolean entert;
    Player player;
    Panel panel;
    Image image;
    Timer timer;
    ArrayList<ArrayList<Double>> allArrows = new ArrayList<>();

    public Weapon(Player player, Panel panel, Image image) {
        this.player = player;
        this.panel = panel;
        this.image = image;
        timer = new Timer(10,this);
        timer.start();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        CreateArrow();
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        entert = true;
    }

    @Override
    public void mouseExited(MouseEvent e) {
        entert = false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        x1 = player.x;
        y1 = player.y;
        if(panel.getMousePosition() != null && entert) {
            yLenght = (long) ((panel.getMousePosition().getY()) - y1);
            xLenght = (long) ((panel.getMousePosition().getX()) - x1);
            playerRotation = Math.atan2(yLenght, xLenght)+Math.PI;
            for (int i = allArrows.size() ;i >= 1  ;i--) {
                if (allArrows.get(i - 1).get(0)==allArrows.get(i - 1).get(2) && allArrows.get(i - 1).get(3) < allArrows.get(i - 1).get(1) ){
                    allArrows.get(i - 1).set(3,2500 + (allArrows.get(i - 1).get(7) * allArrows.get(i - 1).get(2) + allArrows.get(i - 1).get(8)));
                }
                else if (allArrows.get(i - 1).get(0)==allArrows.get(i - 1).get(2) && allArrows.get(i - 1).get(3) > allArrows.get(i - 1).get(1)){
                    allArrows.get(i - 1).set(3,2500 - (allArrows.get(i - 1).get(7) * allArrows.get(i - 1).get(2) + allArrows.get(i - 1).get(8)));
                }
                else {
                    allArrows.get(i - 1).set(3, allArrows.get(i - 1).get(7) * allArrows.get(i - 1).get(2) + allArrows.get(i - 1).get(8));
                }

            }

            for (int i = allArrows.size() ;i >= 1  ;i--) {
                allArrows.get(i - 1).set(5, (allArrows.get(i - 1).get(2)) - (allArrows.get(i - 1).get(0)));//xLength
                allArrows.get(i - 1).set(6, (allArrows.get(i - 1).get(3)) - (allArrows.get(i - 1).get(1)));//yLength
                allArrows.get(i - 1).set(4, (Math.atan2(allArrows.get(i - 1).get(6), allArrows.get(i - 1).get(5))));//Rotation
            }
            for (int i = allArrows.size() ;i >= 1  ;i--) {
                if (allArrows.get(i - 1).get(6) > -3 && allArrows.get(i - 1).get(6) < 3 || allArrows.get(i - 1).get(5) < 3 && allArrows.get(i - 1).get(5) > -3) {
                    allArrows.remove(i-1);
                } else {
                    allArrows.get(i - 1).set(0, (allArrows.get(i - 1).get(0) + (Math.cos(allArrows.get(i - 1).get(4)) * 1500 * 0.01)));//xMovement
                    allArrows.get(i - 1).set(1, (allArrows.get(i - 1).get(1) + (Math.sin(allArrows.get(i - 1).get(4)) * 1500 * 0.01)));//yMovement
                }
            }



            if(yLenght>-3 && yLenght < 3 || xLenght < 3 && xLenght > -3){

            }
            else{
                x1 = x1 + (int) (Math.cos(playerRotation) * 500 * 0.01);
                y1 = y1 + (int) (Math.sin(playerRotation) * 500 * 0.01);
            }
       /*if ((Math.toDegrees(playerRotation)-45)>360){
           playerRotation = playerRotation - Math.toRadians(360) ;
       }*/
            for (int i = allArrows.size() ;i >= 1  ;i--){
                if (allArrows.get(i-1).get(0) >= panel.getWidth() || allArrows.get(i-1).get(0) <= 0) {
                    allArrows.remove(i-1);
                }
                else if (allArrows.get(i-1).get(1) >= panel.getHeight() || allArrows.get(i-1).get(1) <= 0){
                    allArrows.remove(i-1);
                }

            }
            //repaint();
        }

    }
    public void CreateArrow(){
        ArrayList<Double> Arrow = new ArrayList<>();
        Arrow.add((double) player.x);// x1 Index = 0
        Arrow.add((double) player.y);// y1 Index = 1
        if (x1 > panel.getMousePosition().getX() ){
            Arrow.add((panel.getMousePosition().getX())-500);//x2 Index = 2
        }
        else if (x1 < panel.getMousePosition().getX() ) {
            Arrow.add((panel.getMousePosition().getX()) + 500);//x2 Index = 2
        }
        else {
            Arrow.add((panel.getMousePosition().getX()));//x2 Index = 2
        }

        //Arrow.add((this.getMousePosition().getX())*1);//x2 Index = 2
        Arrow.add((panel.getMousePosition().getY()));//y2 Index = 3
        Arrow.add(0.0); //Rotation Index = 4
        Arrow.add(0.0); //xLength Index = 5
        Arrow.add(0.0); //yLength Index = 6
        Arrow.add((panel.getMousePosition().getY()-y1)/(panel.getMousePosition().getX()-x1));//LineareFunktion : m Index = 7
        Arrow.add(y1 - ((panel.getMousePosition().getY()-y1)/(panel.getMousePosition().getX()-x1))* x1); //LineareFunktion : b Index = 8
        allArrows.add(Arrow);
    }
}
