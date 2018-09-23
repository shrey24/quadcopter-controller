/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author shrey
 */
public class Scene2_Controller implements Initializable {
    @FXML
    Button Button_Back = new Button();
    @FXML Button next=new Button();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    public void Back_Button(ActionEvent e)
    {
        try{
         //get reference to the button's stage 
         
         Stage stage=(Stage) Button_Back.getScene().getWindow();
         
        //load up OTHER FXML document
         Parent root = FXMLLoader.load(getClass().getResource("myfxml.fxml"));
       //create a new scene with root and set the stage
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
        }catch(IOException ex){
            System.err.println("IOException occured");
        }
    }
    
    @FXML
    public void newScene(ActionEvent e){      
         
        try{            
         Stage stage=(Stage) next.getScene().getWindow();         
        //load up myFXML(main) document
         Parent root = FXMLLoader.load(getClass().getResource("myfxml.fxml"));//Parent root = FXMLLoader.load(getClass().getResource("myfxml.fxml"));
        //create a new scene with root and set the stage
            System.out.println("aa");
        Scene scene = new Scene(root);
         stage.setScene(scene);       
         stage.show();        
        }catch(Exception ex){
            System.err.println(ex+"IOException occured in JavaFXApplication1.newScene");
        }
     
    }
    
    
}
