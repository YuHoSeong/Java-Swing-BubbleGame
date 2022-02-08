package bubble.game.service;

import java.awt.image.BufferedImage;
import java.awt.Color;
import java.io.File;
import java.util.List;

import javax.imageio.ImageIO;

import bubble.game.component.Bubble;
import bubble.game.component.Player;

public class BackgroundPlayerService implements Runnable{

    private BufferedImage image;
    private Player player;
    private List<Bubble> bubbleList;

    public BackgroundPlayerService(Player player){
        this.player = player;
        this.bubbleList = player.getBubbleList();
        try {
            image = ImageIO.read(new File("images/backgroundMapService.png"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    @Override
    public void run() {
        while(true){

            // 1. 버블 충돌 체크
            for(int i=0; i<bubbleList.size(); i++){
                Bubble bubble = bubbleList.get(i);
                if(bubble.getState() == 1){
                    if(Math.abs(player.getX() - bubble.getX()) < 10 && 
                            Math.abs(player.getY() - bubble.getY()) > 0 && Math.abs(player.getY() - bubble.getY()) < 50 ){
                        bubble.clearBubbled();
                        break;
                    }
                }
            }

            // 2. 벽 충돌 체크
            // 색상 확인
            Color leftColor = new Color(image.getRGB(player.getX() - 5, player.getY() + 25));
            Color rightColor = new Color(image.getRGB(player.getX() + 50 + 15, player.getY() + 25));
            int bottomColor = image.getRGB(player.getX() + 10, player.getY() + 50 + 5)
             + image.getRGB(player.getX() + 50 - 10, player.getY() + 50 + 5);
            // System.out.println("바텀 컬러 :"+bottomColor );

            // 바닥 충돌 확인
            if(bottomColor != -2){
                // System.out.println("바텀 컬러 :" + bottomColor);
                // System.out.println("바닥에 충돌함");
                player.setDown(false);
            }else{
                if(!player.isUp() && !player.isDown()){
                    player.down();
                }
            }

            // 외벽 충돌 확인
            if(leftColor.getRed() == 255 && leftColor.getGreen() ==0 && leftColor.getBlue() == 0){
                // System.out.println("왼쪽 벽에 충돌함");
                player.setLeft(false);
                player.setLeftWallCrash(true);
            }else if(rightColor.getRed() == 255 && rightColor.getGreen() ==0 && rightColor.getBlue() == 0){
                // System.out.println("오른쪽 벽에 충돌함");
                player.setRight(false);
                player.setRightWallCrash(true);
            }else{
                player.setLeftWallCrash(false);
                player.setRightWallCrash(false);
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        
    }
    
}
