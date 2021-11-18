package com.sxt;

import java.awt.*;

/**
 * @ 2021-10-10 8:26
 */
public class EnemyBullet extends Bullet {
    public EnemyBullet(String image, int x, int y, GamePanel gamePanel, Direction direction) {
        super(image, x, y, gamePanel, direction);
    }

    public void paintSelf(Graphics graphics) {
        graphics.drawImage(image, x, y, null);
        this.go();
    }

    public Rectangle getRec() {
        return new Rectangle(x, y, width, length);
    }
}
