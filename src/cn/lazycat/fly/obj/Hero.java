package cn.lazycat.fly.obj;

import cn.lazycat.fly.flag.FlyingObject;
import cn.lazycat.fly.obj.bullet.Bullet;
import cn.lazycat.fly.FlyGame;

import java.util.LinkedList;
import java.util.List;

public class Hero extends FlyingObject {

    private int life;           // 剩余生命
    private int doubleFire;     // 火力值

    public Hero() {
        super.image = FlyGame.hero0;
        super.width = super.image.getWidth();
        super.height = super.image.getHeight();
        init();
    }

    public int getDoubleFire() {
        return doubleFire;
    }

    public int getLife() {
        return life;
    }

    public void addLife(int more) {
        this.life += more;
    }

    public void reduceLife(int reduce) {
        this.life -= reduce;
    }

    public void addDoubleFire() {
        doubleFire += 8;
    }

    public void init() {  // 初始化英雄机
        // 英雄机的初始位置是固定死的
        super.x = 150;
        super.y = 400;
        this.life = 10;
        this.doubleFire = 0;
    }

    @Override
    public void step() {
        // 不停地切换图片
        if (this.image.equals(FlyGame.hero0)) {
            this.image = FlyGame.hero1;
        } else {
            this.image = FlyGame.hero0;
        }
    }

    /**
     * 表示英雄机发射子弹<br>
     * 如果英雄机此时是双倍火力状态，则发射两法子弹，反之只发射一枚
     */
    public List<Bullet> shoot() {
        List<Bullet> bullets = new LinkedList<>();
        int xStep = width / 4;
        int yStep = 20;
        if (this.doubleFire == 0) {  // 此时发射一发子弹
            bullets.add(new Bullet(x + 2 * xStep, y - yStep));
        } else {   // 此时发射双倍子弹
            bullets.add(new Bullet(x + xStep, y - yStep));
            bullets.add(new Bullet(x + 3 * xStep, y - yStep));
            doubleFire -= 1;  // 双倍火力减少
        }
        return bullets;
    }

    /**
     * 英雄机随着鼠标移动<br>
     * @param x 鼠标的x坐标
     * @param y 鼠标的y坐标
     */
    public void moveTo(int x, int y) {
        // 鼠标应该处于英雄机的中央位置
        super.x = x - this.width / 2;
        super.y = y - this.height / 2;

    }

    /**
     * 英雄机被飞行物撞击
     * @param flying 飞行物
     * @return 是否被撞击
     */
    public boolean hitBy(FlyingObject flying) {
        int x1 = super.x;
        int x2 = super.x + super.width;
        int y1 = super.y;
        int y2 = super.y + super.height;

        int x = flying.getX();
        int y = flying.getY();

        return x > (x1 - flying.getWidth()) && x < x2  && y > y1 && y < y2;
    }

}