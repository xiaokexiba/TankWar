package com.sxt;

import java.awt.*;

/**
 * @ 2021-10-07 9:50
 */
public abstract class GameObject {
    //图片
    public Image image;
    //坐标
    public int x;
    public int y;
    //界面
    public GamePanel gamePanel;

    public GameObject() {
    }

    public GameObject(String image, int x, int y, GamePanel gamePanel) {
        this.image = Toolkit.getDefaultToolkit().getImage(image);
        this.x = x;
        this.y = y;
        this.gamePanel = gamePanel;
    }

    public abstract void paintSelf(Graphics graphics);

    public abstract Rectangle getRec();

}
