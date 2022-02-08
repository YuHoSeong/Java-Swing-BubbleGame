package bubble.test.ex18;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;


public class BGM {
    public BGM(){
            try {
                AudioInputStream als = AudioSystem.getAudioInputStream(new File("sounds/bgm.wav"));
                Clip clip = AudioSystem.getClip();
                clip.open(als);
    
                // 소리 설정
                FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
    
                // 볼륨 조정
                gainControl.setValue(-30.0f);
    
                clip.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
           
        
    }
    
}
