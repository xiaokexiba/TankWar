package com.sxt;

import java.awt.*;

/**
 * @ 2021-10-07 9:59
 */
public class Tank extends GameObject {

    //尺寸
    public int width = 40;
    public int length = 50;
    //速度
    private int speed = 3;
    //方向
    Direction direction = Direction.UP;
    //四个方向的图片
    private String upImg;
    private String leftImg;
    private String rightImg;
    private String downImg;

    //攻击冷却状态
    private boolean attackCoolDown = true;
    //攻击冷却时间间隔1000毫秒
    private int attackCoolDownTime = 1000;


    public Tank(String image, int x, int y,
                String upImg, String leftImg, String rightImg, String downImg, GamePanel gamePanel) {
        super(image, x, y, gamePanel);
        this.upImg = upImg;
        this.leftImg = leftImg;
        this.rightImg = rightImg;
        this.downImg = downImg;
    }

    public void leftward() {
        this.x -= speed;
        setImg(leftImg);
        direction = Direction.LEFT;
    }

    public void upward() {
        this.y -= speed;
        setImg(upImg);
        direction = Direction.UP;
    }

    public void rightward() {
        this.x += speed;
        setImg(rightImg);
        direction = Direction.RIGHT;
    }

    public void downward() {
        this.y += speed;
        setImg(downImg);
        direction = Direction.DOWN;
    }


    public void attack() {
        if (attackCoolDown) {
            Point p = this.getHeadPoint();
            Bullet bullet = new Bullet("images/bullet/bulletGreen.gif", p.x, p.y, this.gamePanel, direction);
            this.gamePanel.bulletList.add(bullet);
            //线程开始
            new AttackCD().start();
        }
    }

    //新线程
    class AttackCD extends Thread {
        public void run() {
            //将攻击功能设置为冷却状态
            attackCoolDown = false;
            //休眠1秒
            try {
                Thread.sleep(attackCoolDownTime);
            } catch (Exception e) {
                e.printStackTrace();
            }
            //将攻击功能解除为冷却状态
            attackCoolDown = true;
            //终止线程
            this.stop();
        }
    }

    public Point getHeadPoint() {
        switch (direction) {
            case LEFT:
                return new Point(x, y + length / 2);
            case RIGHT:
                return new Point(x + width, y + length / 2);
            case UP:
                return new Point(x + width / 2, y);
            case DOWN:
                return new Point(x + width / 2, y);
            default:
                return null;
        }
    }


    public void setImg(String image) {
        this.image = Toolkit.getDefaultToolkit().getImage(image);
    }

    @Override
    public void paintSelf(Graphics graphics) {
        graphics.drawImage(image, x, y, null);
    }

    @Override
    public Rectangle getRec() {
        return new Rectangle(x, y, width, length);
    }
}
