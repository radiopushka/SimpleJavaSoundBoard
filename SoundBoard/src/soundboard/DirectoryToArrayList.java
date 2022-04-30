/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package soundboard;

import java.awt.Component;
import java.io.File;
import java.util.ArrayList;
import java.util.prefs.Preferences;
import javax.swing.JFileChooser;

/**
 *
 * @author ivan
 */
public class DirectoryToArrayList {
    private JFileChooser jf;
    private ArrayList<File> files;
    private String[] names;
    private  Preferences userPreference;
    private String suffix="";
    public DirectoryToArrayList(String suffix){
     this.suffix=suffix;
     userPreference = Preferences.userRoot();
    }
    public void convertToWAV(Component parent){
     
    
     jf=new JFileChooser(){
      @Override
      public boolean accept(File f){
       if(f.isDirectory()){
        return true;
       }
       return false;
      }
     };
     String pf=userPreference.get("pathforfiles", "");
     if(!pf.isEmpty())
      jf.setCurrentDirectory(new File(pf));
     jf.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
     jf.showOpenDialog(parent);
     try{
         File[] list=jf.getSelectedFile().listFiles();
         userPreference.put("pathforfiles", jf.getCurrentDirectory().getAbsolutePath());
         userPreference.flush();
        String destp;
        String srcp;
         for(File f:list){
             
           if(f.getName().toLowerCase().endsWith(".mp3")){
            srcp=f.getAbsolutePath();
            destp=srcp.substring(0,srcp.length()-4)+".wav";
            
            if(!new File(destp).exists()){
           
             Runtime.getRuntime().exec(new String[] {"ffmpeg","-i",srcp,destp}).waitFor();
            
            }
           }
         
         }
        
          
         
     
     }catch(Exception e){
     
     }
    }
    public boolean openDialog(Component parent){
    
    
     jf=new JFileChooser(){
      @Override
      public boolean accept(File f){
       if(f.isDirectory()){
        return true;
       }
       return false;
      }
     };
     String pf=userPreference.get("pathforfiles", "");
     if(!pf.isEmpty())
      jf.setCurrentDirectory(new File(pf));
     jf.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
     jf.showOpenDialog(parent);
     try{
         File[] list=jf.getSelectedFile().listFiles();
          userPreference.put("pathforfiles", jf.getCurrentDirectory().getAbsolutePath());
          userPreference.flush();
         files=new ArrayList();
         int i=0;
         for(File f:list){
           if(f.getName().toLowerCase().endsWith(suffix.toLowerCase()))
           i++;
         }
         names=new String[i];
         i=0;
         for(File f:list){
          if(f.getName().toLowerCase().endsWith(suffix.toLowerCase())){
           files.add(f);
           names[i]=f.getName();
           i++;
          }
          
         }
      return true;
     }catch(Exception e){
      return false;
     }
    }
    public ArrayList<File> getFiles(){
     return files;
    }
    public String[] getNames(){
     return names;
    }
}
