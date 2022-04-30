/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package soundboard;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
  
/**
 *
 * @author ivan
 */
public class Player {
    private final int BUFFER_SIZE = 128000;
    private AudioInputStream audioStream;
    private AudioFormat audioFormat;
    private SourceDataLine sourceLine;
    public Player(){}
    public void play(File in)throws Exception{
        audioStream = AudioSystem.getAudioInputStream(in);//use first File as reference. Use WAV.
        audioFormat = audioStream.getFormat();
       
      
      if(sourceLine==null){ //line persistence for Pulse Audio
        sourceLine = (SourceDataLine) AudioSystem.getSourceDataLine(audioFormat);
        sourceLine.open(audioFormat);
        sourceLine.start();
      }

        

        int nBytesRead = 0;
        byte[] abData = new byte[BUFFER_SIZE];
        while ((nBytesRead = audioStream.read(abData, 0, abData.length)) != -1) {
           
      
               sourceLine.write(abData, 0, nBytesRead);
 
        }
        sourceLine.flush();
        //sourceLine.drain();
       // sourceLine.close();
    }
}
