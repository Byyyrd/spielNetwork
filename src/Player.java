import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.Serializable;
import java.util.ArrayList;

public class Player implements Serializable {
    int x;
    int y;
    int width;
    int height;
    int speed = 5;
    boolean downPressed;
    boolean upPressed;
    boolean leftPressed;
    boolean rightPressed;
    boolean downCollids = false;
    boolean upCollids = false;
    boolean leftCollids = false;
    boolean rightCollids = false;
    double xVel;
    double yVel;
    double xDir;
    double yDir;
    String name = "";
    Player player2;
    Sword enemySword;
    ArrayList<Double[]> allArrows;
    ArrayList<Double[]> allArrowsself;
    boolean slingshotPickedup;
    boolean swordPickedup;
    Panel panel;
    double hp;
    public Player(int x, int y, int width, int height, Player player2, Sword enemySword, Panel panel) {
        slingshotPickedup = true;
        swordPickedup = false;
        this.player2 = player2;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.enemySword = enemySword;
        this.panel= panel;
    }

    public void tick(){
        checkCollision();
        inputs();
        applyVel();
        if (x > panel.getWidth()){
            x = -width;
        }
        if (x < -width){
            x = panel.getWidth();
        }
        if (y > panel.getHeight()){
            y = -height;
        }
        if (y < -height){
            y = panel.getHeight();
        }
    }

    public void checkCollision() {
        if (enemySword != null && player2.swordPickedup) {
            if (inRectangle((int)(enemySword.x + 21 + Math.sin(enemySword.rotation) * 21),(int)(enemySword. y + width/2 + Math.cos(enemySword.rotation) * -25),x,y, width, height) || inRectangle((int)(enemySword.x + 21 + Math.sin(enemySword.rotation) * 40),(int)(enemySword.y + width/2 + Math.cos(enemySword.rotation) * -40),x,y, width, height) || inRectangle((int)(enemySword.x + 21 + Math.sin(enemySword.rotation) * 55),(int)(enemySword.y + width/2 + Math.cos(enemySword.rotation) * -55),x,y, width, height) ) {
                panel.p1Inv.playerHit(2.5);
            }
        }
        if (allArrows != null){
            for (Double[] allArrow : allArrows) {
                if (inRectangle((int) (allArrow[0] + 5), (int) (allArrow[1] + 5), x, y, width, height)) {
                    panel.p1Inv.playerHit(1);
                }
            }
        }
        for (int j = 0; j < panel.allObsticals.size(); j++){
            for (int i = 0; i < allArrows.size(); i++){
                if(inRectangle((int)(allArrows.get(i)[0]+5) , (int)(allArrows.get(i)[1]+5),panel.allObsticals.get(j)[0],panel.allObsticals.get(j)[1],panel.allObsticals.get(j)[2],panel.allObsticals.get(j)[3])){
                    allArrows.remove(i);
                }
            } 
        }
        for (int j = 0; j < panel.allObsticals.size(); j++){
            for (int i = 0; i < allArrowsself.size(); i++){
                if(inRectangle((int)(allArrowsself.get(i)[0]+5) , (int)(allArrowsself.get(i)[1]+5),panel.allObsticals.get(j)[0],panel.allObsticals.get(j)[1],panel.allObsticals.get(j)[2],panel.allObsticals.get(j)[3])){
                    allArrowsself.remove(i);
                }
            }
        }
    }
    public void drawPlayer(Graphics2D g2d, Image playerImage,Client client,Sword sword){
        //Player Names
        g2d.setColor(Color.red);
        g2d.drawString(client.name + "  " + ((int)(sword.cooldown*10f))/10f, x,y);
        g2d.drawString(player2.name + "  " + ((int)(enemySword.cooldown*10f))/10f, player2.x, player2.y);

        //Player
        g2d.drawImage(playerImage, x, y, width, height, null);
        g2d.drawImage(playerImage, player2.x, player2.y, player2.width, player2.height, null);

    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == 83) {
            downPressed = true;
        }
        if (e.getKeyCode() == 87) {
            upPressed = true;
        }
        if (e.getKeyCode() == 65) {
            leftPressed = true;
        }
        if (e.getKeyCode() == 68) {
            rightPressed = true;
        }
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == 83) {
            downPressed = false;
        }
        if (e.getKeyCode() == 87) {
            upPressed = false;
        }
        if (e.getKeyCode() == 65) {
            leftPressed = false;
        }
        if (e.getKeyCode() == 68) {
            rightPressed = false;
        }

    }

    public void applyVel() {
        if (xDir != 0) {
            xVel = lerp(xVel, speed * xDir, 1);
        } else {
            xVel = lerp(xVel, 0, 1);
        }
        x += xVel;
        if (yDir != 0) {
            yVel = lerp(yVel, speed * yDir, 1);
        } else {
            yVel = lerp(yVel, 0, 1);
        }
        y += yVel;
    }

    public void inputs() {
        for ( int i = 0; i < panel.allObsticals.size(); i++) {
            if( inRectangle(x + 5, y, panel.allObsticals.get(i)[0], panel.allObsticals.get(i)[1], panel.allObsticals.get(i)[2], panel.allObsticals.get(i)[3]) || inRectangle(x + player2.width - 5, y, panel.allObsticals.get(i)[0], panel.allObsticals.get(i)[1], panel.allObsticals.get(i)[2], panel.allObsticals.get(i)[3])|| inRectangle(x + player2.width/2 - 5, y, panel.allObsticals.get(i)[0], panel.allObsticals.get(i)[1], panel.allObsticals.get(i)[2], panel.allObsticals.get(i)[3])) {
                upCollids = true;
            }
            if (inRectangle(x + 5, y + player2.height, panel.allObsticals.get(i)[0], panel.allObsticals.get(i)[1], panel.allObsticals.get(i)[2], panel.allObsticals.get(i)[3]) || inRectangle(x + player2.width - 5, y + player2.height, panel.allObsticals.get(i)[0], panel.allObsticals.get(i)[1], panel.allObsticals.get(i)[2], panel.allObsticals.get(i)[3]) || inRectangle(x + player2.width/2 - 5, y + player2.height/2, panel.allObsticals.get(i)[0], panel.allObsticals.get(i)[1], panel.allObsticals.get(i)[2], panel.allObsticals.get(i)[3])) {
                downCollids = true;
            }
            if (inRectangle(x, y + 5, panel.allObsticals.get(i)[0], panel.allObsticals.get(i)[1], panel.allObsticals.get(i)[2], panel.allObsticals.get(i)[3]) || inRectangle(x, y + player2.height - 5,panel.allObsticals.get(i)[0], panel.allObsticals.get(i)[1], panel.allObsticals.get(i)[2], panel.allObsticals.get(i)[3])|| inRectangle(x, y + player2.height/2 - 5,panel.allObsticals.get(i)[0], panel.allObsticals.get(i)[1], panel.allObsticals.get(i)[2], panel.allObsticals.get(i)[3])) {
                leftCollids = true;
            }
            if (inRectangle(x + player2.width, y - 5, panel.allObsticals.get(i)[0], panel.allObsticals.get(i)[1], panel.allObsticals.get(i)[2], panel.allObsticals.get(i)[3]) || inRectangle(x + player2.width, y + player2.height - 5, panel.allObsticals.get(i)[0], panel.allObsticals.get(i)[1], panel.allObsticals.get(i)[2], panel.allObsticals.get(i)[3])|| inRectangle(x + player2.width/2, y + player2.height/2 - 5, panel.allObsticals.get(i)[0], panel.allObsticals.get(i)[1], panel.allObsticals.get(i)[2], panel.allObsticals.get(i)[3])) {
                rightCollids = true;
            }
        }
        if (upPressed && !inRectangle(x + 5, y, player2.x, player2.y, player2.width, player2.width) && !inRectangle(x + player2.width - 5, y, player2.x, player2.y, player2.width, player2.width) && !upCollids) {
            yDir = -1;
        } else if (downPressed && !inRectangle(x + 5, y + player2.height, player2.x, player2.y, player2.width, player2.width) && !inRectangle(x + player2.width - 5, y + player2.height, player2.x, player2.y, player2.width, player2.width) && !downCollids) {
            yDir = 1;
        } else {
            yDir = 0;
        }
        if (leftPressed && !inRectangle(x, y + 5, player2.x, player2.y, player2.width, player2.width) && !inRectangle(x, y + player2.height - 5, player2.x, player2.y, player2.width, player2.width) && !leftCollids) {
            xDir = -1;
        } else if (rightPressed && !inRectangle(x + player2.width, y - 5, player2.x, player2.y, player2.width, player2.width) && !inRectangle(x + player2.width, y + player2.height - 5, player2.x, player2.y, player2.width, player2.width) && !rightCollids) {
            xDir = 1;
        } else {
            xDir = 0;
        }
        upCollids = false;
        downCollids = false;
        leftCollids = false;
        rightCollids = false;
    }

    public void setEnemySword(Sword enemySword) {
        this.enemySword = enemySword;
    }

    public double lerp(double start, double end, double amt) {
        return (1 - amt) * start + amt * end;
    }

    public boolean inRectangle(int px, int py, int rx, int ry, int rb, int rh) {
        return rx < px && px < rx + rb && ry < py && py < ry + rh;

    }

    public boolean isSlingshotPickedup() {
        return slingshotPickedup;
    }

    public void setSlingshotPickedup(boolean slingshotPickedup) {
        this.slingshotPickedup = slingshotPickedup;
    }

    public void setAllArrows(ArrayList<Double[]> allArrows) {
        this.allArrows = allArrows;
    }

    public void setAllArrowsself(ArrayList<Double[]> allArrowsself) {
        this.allArrowsself = allArrowsself;
    }

    public boolean isSwordPickedup() {
        return swordPickedup;
    }
    public void setSwordPickedup(boolean swordPickedup) {
        this.swordPickedup = swordPickedup;
    }
}
