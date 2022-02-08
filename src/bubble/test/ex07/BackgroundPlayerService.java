package bubble.test.ex07;

import java.awt.image.BufferedImage;
import java.awt.Color;
import java.io.File;

import javax.imageio.ImageIO;

public class BackgroundPlayerService implements Runnable{

    private BufferedImage image;
    private Player player;

    public BackgroundPlayerService(Player player){
        this.player = player;
        try {
            image = ImageIO.read(new File("images/backgroundMapService.png"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    @Override
    public void run() {
        while(true){
            Color leftColor = new Color(image.getRGB(player.getX() - 5, player.getY() + 25));
            Color rightColor = new Color(image.getRGB(player.getX() + 50 + 15, player.getY() + 25));

            if(leftColor.getRed() == 255 && leftColor.getGreen() ==0 && leftColor.getBlue() == 0){
                System.out.println("왼쪽 벽에 충돌함");
                player.setLeft(false);
                player.setLeftWallCrash(true);
            }else if(rightColor.getRed() == 255 && rightColor.getGreen() ==0 && rightColor.getBlue() == 0){
                System.out.println("오른쪽 벽에 충돌함");
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
