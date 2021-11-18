package com.sxt;

import java.awt.*;

/**
 * @ 2021-10-09 20:32
 */
public class Bullet extends GameObject {

    //尺寸
    int width = 10;
    int length = 10;
    //速度
    int speed = 7;
    //方向
    Direction direction;

    public Bullet(String image, int x, int y, GamePanel gamePanel, Direction direction) {
        super(image, x, y, gamePanel);
        this.direction = direction;
    }

    public void leftward() {
        x -= speed;
    }

    public void rightward() {
        x += speed;
    }

    public void upward() {
        y -= speed;
    }

    public void downward() {
        y += speed;
    }

    public void go() {
        switch (direction) {
            case LEFT:
                leftward();
                break;
            case RIGHT:
                rightward();
                break;
            case UP:
                upward();
                break;
            case DOWN:
                downward();
                break;
            default:
                break;
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
