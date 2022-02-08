package bubble.test.ex12;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import lombok.Getter;
import lombok.Setter;

// class Player -> new 가능한 애들!! 게임에 존재할 수 있음. (추상메서드를 가질 수 없다.)
@Getter
@Setter
public class Player extends JLabel implements Moveable{

    // 위치 상태
    private int x;
    private int y;

    // 플레이어의 방향
    private PlayerWay playerWay;

    // 움직임 살태
    private boolean left;
    private boolean right;
    private boolean up;
    private boolean down;

    // 벽에 충돌한 상태
    private boolean leftWallCrash;
    private boolean rightWallCrash;

    // 플레이어 속도 상태
    private final int SPEED = 5;
    private final int UPSPEED = 3; // up, down speed
    private final int DOWNSPEED = 5; // up, down speed

    private ImageIcon playerR, playerL;

    public Player(){
        initObject();
        initSetting();
        initBackgroundPlayerService();
    }

    private void initObject(){
        playerR = new ImageIcon("images/playerR.png");
        playerL = new ImageIcon("images/playerL.png");
    }

    private void initSetting(){
        x = 80;
        y = 535;

        left = false;
        right = false;
        up = false;
        down = false;

        leftWallCrash = false;
        rightWallCrash = false;

        playerWay = PlayerWay.RIGHT;
        setIcon(playerR);
        setSize(50,50);
        setLocation(x, y);
    }

    private void initBackgroundPlayerService(){
        new Thread(new BackgroundPlayerService(this)).start();
    }

    @Override
    public void left() {
        // System.out.println("left");
        playerWay = PlayerWay.LEFT;
        left = true;
        new Thread(() ->{
                while(left){
                setIcon(playerL);
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
        playerWay = PlayerWay.RIGHT;
        right = true;
        new Thread(() ->{
            while(right){
                setIcon(playerR);
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
