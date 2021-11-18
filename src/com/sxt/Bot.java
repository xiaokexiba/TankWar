package com.sxt;

import java.awt.*;
import java.util.Random;

/**
 * @ 2021-10-09 21:15
 */
public class Bot extends Tank {

    int moveTime = 0;

    public Bot(String image, int x, int y, String upImg, String leftImg, String rightImg, String downImg, GamePanel gamePanel) {
        super(image, x, y, upImg, leftImg, rightImg, downImg, gamePanel);
    }

    public Direction getRandomeDirection() {
        Random random = new Random();
        int randomNum = random.nextInt(4);
        switch (randomNum) {
            case 0:
                return Direction.LEFT;
            case 1:
                return Direction.RIGHT;
            case 2:
                return Direction.UP;
            case 3:
                return Direction.DOWN;
            default:
                return null;
        }
    }

    public void go() {
        attack();
        if (moveTime >= 20) {
            direction = getRandomeDirection();
            moveTime = 0;
        } else {
            moveTime++;
        }
        switch (direction) {
            case LEFT:
                leftward();
                break;
            case UP:
                upward();
                break;
            case DOWN:
                downward();
                break;
            case RIGHT:
                rightward();
            default:
                break;
        }
    }

    public void attack(){
        Point p = getHeadPoint();
        Random random = new Random();
        int randomNum = random.nextInt(400);
        if (randomNum <= 4) {
            this.gamePanel.bulletList.add(new EnemyBullet("images/bullet/bulletYellow.gif", p.x, p.y, this.gamePanel, direction));
        }
    }

    @Override
    public void paintSelf(Graphics graphics) {
        graphics.drawImage(image, x, y, null);
        go();
    }

    @Override
    public Rectangle getRec() {
        return new Rectangle(x, y, width, length);
    }
}
