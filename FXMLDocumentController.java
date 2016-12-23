/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw5_nt;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;

/**
 *
 * @author Nathen
 */
public class FXMLDocumentController implements Initializable {
   
    @FXML
    private MenuItem itmStart, itmPause;
    @FXML
    private Button btnStart, btnPause;
    @FXML
    private TextArea text;
    
    Thread thread;
    int ims, is, im, ih = 0;  
    String mils = "00";
    String sec = "00";
    String min = "00";
    String hr = "00";
    String time;
    boolean isPaused = false;
    
    @FXML
    private void handleStartStop(ActionEvent event) {
        if(btnStart.getText().equals("Start")){
            itmPause.setDisable(false);
            btnPause.setDisable(false);
            btnStart.setText("Stop");
            itmStart.setText("Stop");
            isPaused = false;
        
        
            thread = new Thread(new StartThread());
            thread.setDaemon(true);
            thread.start();
        }else{
            thread.stop();
            btnStart.setText("Start");
            itmStart.setText("Start");
        }
        
    }

    class StartThread implements Runnable{

        @Override
        public void run(){
            try{
                while (true){
                    ims++;
                    if(ims < 10){
                            mils = "0" + ims;
                    }else{
                            mils = "" + ims;
                    }
                    
                    if(ims == 99){
                        ims = 0;
                        is++;
                        if(is < 10){
                            sec = "0" + is;
                        }else if(is == 60){
                            sec = "00";
                        }else{
                            sec = "" + is;
                        }
                    }
                    
                    if(is == 60){
                        is = 0;
                        im++;
                        if(im < 10){
                            min = "0" + im;
                        }else if(im == 60){
                            min = "00";
                        }else{
                            min = "" + im;
                        }
                    }
                    
                    if(im == 60){
                        im = 0;
                        ih++;
                        if(ih < 10){
                            hr = "0" + ih;
                        }else if(ih == 60){
                            hr = "00";
                        }else{
                            hr = "" + ih;
                        }
                    }
                    if(isPaused == false){
                        time = hr+":" +min + ":" + sec + "." + mils;
                    }
                    Platform.runLater(() -> text.setText(time));
                    
                    Thread.sleep(10);
                }
                
            }catch(Exception e){
              
            }
        }
    }
    
    @FXML
    private void handleReset(ActionEvent event) {
        
        thread.stop(); 
        itmPause.setDisable(true);
        itmPause.setText("Pause");
        btnPause.setDisable(true);
        btnPause.setText("Pause");
        btnStart.setText("Start");
        itmStart.setText("Start");
        
        ims = 0;
        is= 0;
        im = 0;
        ih = 0;
        mils = "00";
        sec = "00";
        min = "00";
        hr = "00";
        text.setText("00:00:00.00");
    }
    
    @FXML
    private void handleTogglePause(ActionEvent event) {
        if(btnPause.getText().equals("Pause")){
            btnPause.setText("Unpause");
            itmPause.setText("Unpause");
            isPaused = true;
            
        }else if(btnPause.getText().equals("Unpause") && btnStart.getText().equals("Stop")){
            btnPause.setText("Pause");
            itmPause.setText("Pause");
            isPaused = false;
        }else if(btnPause.getText().equals("Unpause") && btnStart.getText().equals("Start")){
            btnPause.setText("Pause");
            itmPause.setText("Pause");
            isPaused = false;
            time = hr+":" +min + ":" + sec + "." + mils;
            text.setText(time);
        }
    }
    
    @FXML
    private void handleExit(ActionEvent event) {
        Platform.exit();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
