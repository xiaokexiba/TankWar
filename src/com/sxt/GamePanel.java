package com.sxt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * @ 2021-10-04 23:27
 */
public class GamePanel extends JFrame {


    //定义双缓存图片
    Image OffScreemImage = null;
    //游戏模式 0 游戏未开始，1 单人模式，2 双人模式
    private int state = 0;
    //游戏是否开始
    private boolean start = false;
    //临时变量
    private int temp = 0;
    //重绘次数
    public int count = 0;
    int enemyCount = 0;
    //窗口长宽
    private int width = 800;
    private int length = 610;
    Image select = Toolkit.getDefaultToolkit().getImage("images/selecttank.gif");
    //指针初始化纵坐标
    private int y = 150;
    //游戏元素列表
    public ArrayList<Bullet> bulletList = new ArrayList<>();
    public ArrayList<Tank> tankList = new ArrayList<>();
    public ArrayList<Bot> botList = new ArrayList<>();
    //playerOne
    PlayerOne playerOne = new PlayerOne("images/player1/p1tankU.gif", 125, 510,
            "images/player1/p1tankU.gif", "images/player1/p1tankL.gif",
            "images/player1/p1tankR.gif", "images/player1/p1tankD.gif", this);

    //窗口的启动方法
    public void launch() {
        //标题
        setTitle("坦克大战");
        //窗口初始大小
        setSize(width, length);
        //使屏幕居中
        setLocationRelativeTo(null);
        //添加关闭事件
        setDefaultCloseOperation(3);
        //用户不能调整大小
        setResizable(false);
        //使窗口可见
        setVisible(true);
        //添加键盘监视器
        this.addKeyListener(new GamePanel.KeyMonitor());

        //重绘
        while (true) {
            if (count % 100 == 1 && enemyCount < 10) {
                Random random = new Random();
                int randomNum = random.nextInt(800);
                botList.add(new Bot("images/enemy/enemy1U.gif", randomNum, 110,
                        "images/enemy/enemy1U.gif", "images/enemy/enemy1L.gif",
                        "images/enemy/enemy1R.gif", "images/enemy/enemy1D.gif", this));
                //System.out.println("bot: " + botList.size());
                enemyCount++;
            }
            repaint();
            try {
                Thread.sleep(25);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //paint方法
    @Override
    public void paint(Graphics graphics) {
        //设置和容一样大小的Image图片
        if (OffScreemImage == null) {
            OffScreemImage = this.createImage(width, length);
        }
        //获得该图片的画笔
        Graphics gImage = OffScreemImage.getGraphics();
        //获得该图片小字的画笔
        Graphics gImage1 = OffScreemImage.getGraphics();
        //设置背景颜色
        gImage.setColor(Color.gray);
        //绘制实心矩形
        gImage.fillRect(0, 0, width, length);
        //改变画笔颜色
        gImage.setColor(Color.blue);
        //改变文字大小
        gImage.setFont(new Font("黑体", Font.BOLD, 50));
        gImage1.setFont(new Font("黑体", Font.BOLD, 20));
        //state == 0，游戏未开始
        if (state == 0) {
            //添加文字
            gImage.drawString("选择游戏模式", 220, 100);
            gImage.drawString("单人模式", 270, 200);
            gImage.drawString("双人模式", 270, 300);
            gImage1.drawString("1:单人模式   2:双人模式  enter:确定开始游戏", 130, 400);
            //绘制指针
            gImage.drawImage(select, 200, y, null);
        }
        //state == 0/1，游戏开始
        else if (state == 1 || state == 2) {
            gImage.drawString("游戏开始", 220, 100);
            if (state == 1) {
                gImage.drawString("单人模式", 220, 200);
            } else {
                gImage.drawString("双人模式", 220, 200);
            }

            //绘制游戏元素
            playerOne.paintSelf(gImage);
            for (Bullet bullet : bulletList) {
                bullet.paintSelf(gImage);
            }
            for (Bot bot : botList) {
                bot.paintSelf(gImage);
            }
            //重绘次数+1
            count++;
        }
        //将缓冲区绘制好的图形整个绘制到容器的画布中
        graphics.drawImage(OffScreemImage, 0, 0, null);


    }

    //键盘监视器
    class KeyMonitor extends KeyAdapter {
        //按下键盘
        @Override
        public void keyPressed(KeyEvent keyEvent) {
            //返回键值
            int key = keyEvent.getKeyCode();
            switch (key) {
                case KeyEvent.VK_1:
                    temp = 1;
                    y = 150;
                    break;
                case KeyEvent.VK_2:
                    temp = 2;
                    y = 250;
                    break;
                case KeyEvent.VK_ENTER:
                    state = temp;
                    break;
                default:
                    playerOne.keyPressed(keyEvent);
                    break;
            }
        }

        //松开键盘
        @Override
        public void keyReleased(KeyEvent keyEvent) {
            playerOne.keyReleased(keyEvent);
        }

    }


    //main方法
    public static void main(String[] args) {

        GamePanel gamePanel = new GamePanel();
        gamePanel.launch();
    }
}
