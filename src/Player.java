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
    double xVel;
    double yVel;
    double xDir;
    double yDir;
    String name = "";
    Player player2;
    //ArrayList deadlyProjectiles;
    Sword enemySword;
    ArrayList<Double[]> allArrows;
    boolean bowPickedup;
    boolean swordPickedup;
    public Player(int x, int y, int width, int height, Player player2, Sword enemySword) {
        bowPickedup = false;
        swordPickedup = true;
        this.player2 = player2;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.enemySword = enemySword;
    }

    public void tick(){
        checkCollision();
        inputs();
        applyVel();
    }

    public void checkCollision() {
        if (enemySword != null && player2.swordPickedup) {
            if (inRectangle((int)(enemySword.x + 21 + Math.sin(enemySword.rotation) * 21),(int)(enemySword. y + width/2 + Math.cos(enemySword.rotation) * -25),x,y, width, height) || inRectangle((int)(enemySword.x + 21 + Math.sin(enemySword.rotation) * 40),(int)(enemySword.y + width/2 + Math.cos(enemySword.rotation) * -40),x,y, width, height) || inRectangle((int)(enemySword.x + 21 + Math.sin(enemySword.rotation) * 55),(int)(enemySword.y + width/2 + Math.cos(enemySword.rotation) * -55),x,y, width, height) ) {
                x = 0;
                y = 0;
            }
        }
        if (allArrows != null){
            for (Double[] allArrow : allArrows) {
                if (inRectangle((int) (allArrow[0] + 5), (int) (allArrow[1] + 5), x, y, width, height)) {
                    x = 0;
                    y = 0;
                }
            }
        }
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
        if (upPressed && !inRectangle(x + 5, y, player2.x, player2.y, player2.width, player2.width) && !inRectangle(x + player2.width - 5, y, player2.x, player2.y, player2.width, player2.width)) {
            yDir = -1;
        } else if (downPressed && !inRectangle(x + 5, y + player2.height, player2.x, player2.y, player2.width, player2.width) && !inRectangle(x + player2.width - 5, y + player2.height, player2.x, player2.y, player2.width, player2.width)) {
            yDir = 1;
        } else {
            yDir = 0;
        }
        if (leftPressed && !inRectangle(x, y + 5, player2.x, player2.y, player2.width, player2.width) && !inRectangle(x, y + player2.height - 5, player2.x, player2.y, player2.width, player2.width)) {
            xDir = -1;
        } else if (rightPressed && !inRectangle(x + player2.width, y - 5, player2.x, player2.y, player2.width, player2.width) && !inRectangle(x + player2.width, y + player2.height - 5, player2.x, player2.y, player2.width, player2.width)) {
            xDir = 1;
        } else {
            xDir = 0;
        }

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

    public boolean isBowPickedup() {
        return bowPickedup;
    }

    public void setBowPickedup(boolean bowPickedup) {
        this.bowPickedup = bowPickedup;
    }

    public void setAllArrows(ArrayList<Double[]> allArrows) {
        this.allArrows = allArrows;
    }

    public boolean isSwordPickedup() {
        return swordPickedup;
    }
    public void setSwordPickedup(boolean swordPickedup) {
        this.swordPickedup = swordPickedup;
    }
}
