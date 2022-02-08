package bubble.test.ex18;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import lombok.Getter;
import lombok.Setter;

// class Player -> new 가능한 애들!! 게임에 존재할 수 있음. (추상메서드를 가질 수 없다.)
@Getter
@Setter
public class Enemy extends JLabel implements Moveable{

    private BubbleFrame mContext;

    // 위치 상태
    private int x;
    private int y;

    // 플레이어의 방향
    private EnemyWay enemyWay;

    // 움직임 살태
    private boolean left;
    private boolean right;
    private boolean up;
    private boolean down;

    // 적군을 맞춘 상태
    private int state;  // 0(살아있는 상태), 1(적을 가둔 물방울)

    // 벽에 충돌한 상태
    private boolean leftWallCrash;
    private boolean rightWallCrash;

    // 플레이어 속도 상태
    private final int SPEED = 3;
    private final int UPSPEED = 1; // up, down speed
    private final int DOWNSPEED = 3; // up, down speed

    private ImageIcon enemyR, enemyL;

    public Enemy(BubbleFrame mContext){
        this.mContext = mContext;
        initObject();
        initSetting();
        initBackgroundEnemyService();
        right();
    }

    private void initObject(){
        enemyR = new ImageIcon("images/enemyR.png");
        enemyL = new ImageIcon("images/enemyL.png");
    }

    private void initSetting(){
        x = 480;
        y = 178;

        left = false;
        right = false;
        up = false;
        down = false;

        state = 0;

        leftWallCrash = false;
        rightWallCrash = false;

        enemyWay = EnemyWay.RIGHT;
        setIcon(enemyR);
        setSize(50,50);
        setLocation(x, y);
    }

    private void initBackgroundEnemyService(){
        new Thread(new BackgroundEnemyService(this)).start();
    }

    @Override
    public void left() {
        // System.out.println("left");
        enemyWay = EnemyWay.LEFT;
        left = true;
        new Thread(() ->{
                while(left){
                setIcon(enemyL);
                x = x-SPEED;
                setLocation(x, y);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void right() {
        // System.out.println("right");
        enemyWay = EnemyWay.RIGHT;
        right = true;
        new Thread(() ->{
            while(right){
                setIcon(enemyR);
                x = x+SPEED;
                setLocation(x, y);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        
    }

    @Override
    public void up() {
    //    System.out.println("up");
       up = true;
       new Thread(()->{
            for(int i=0; i<130/UPSPEED; i++){
                y = y - UPSPEED;
                setLocation(x, y);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            up = false;
            down();

       }).start();
        
    }

    @Override
    public void down() {
        // System.out.println("down");
        down = true;
        new Thread(()->{
            while(down){
                y = y + DOWNSPEED;
                setLocation(x, y);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            down = false;
        }).start();
    }
}
