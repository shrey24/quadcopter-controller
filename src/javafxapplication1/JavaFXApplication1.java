/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


/**
 *
 * @author shrey
 */
public class JavaFXApplication1 extends Application {
    
    @FXML Button next=new Button();
    
    public static void main(String[] args) {
        
       launch(args);
       try{
       if(Controller.drone.port.isOpen())
       {
           Controller.drone.port.closePort();
           System.out.println("Port closed...");
       }
       }catch(Exception ex){ System.out.println("Exception: drone obj NOT Instantiated");}
        System.out.println("App closed");
        
    }
    
    

    @Override
    public void start(Stage primaryStage) throws Exception {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
      
       /* //building GUI code without scene builder
        Button bt=new Button("click");
        bt.setOnAction(new EventHandler<ActionEvent>() {  //Anonymous inner class, can use lambda exp. instead
            @Override
            public void handle(ActionEvent event) {
                System.out.println("bt clicked");
            }
        });
        
        // Lambda expressions 
        Button quit=new Button("Exit");
        quit.setOnAction(e -> {
                System.out.println("exiting app");
                System.exit(0);
        });
        
        
         VBox root=new VBox();
        root.getChildren().addAll(bt,quit);
        */
       
// GUI with scene builder 
       
        Parent root=FXMLLoader.load(getClass().getResource("Welcome.fxml"));
        Scene sc=new Scene(root);
       // action a=new action();
        
        /* the setOn[Event](EventHandler<event-type>) method of any control registers 
            the Event-Handler of type <event-type> to that control  */
//        sc.setOnKeyPressed(new EventHandler<KeyEvent>(){
//            
//            public void handle(KeyEvent k){
//                    a.setup();
//                 System.out.println("Key Pressed: " +k.getText()+" "+a.get());
//                
//            }
//        }   
//        );
//        sc.setOnKeyReleased(new EventHandler<KeyEvent>(){       //KEY PRESSED Event     
//            public void handle(KeyEvent k){
//            a.setdown();
//                 System.out.println("Key released: " +k.getText()+" "+a.get());
//            }
//        }   
//        );
       
        
//        sc.setOnKeyTyped((KeyEvent e) -> {                      //KEY TYPED EVENT, used lambda exp
//            System.out.println("Key Typed: " + e.getCharacter());
//        });
        
        primaryStage.setScene(sc);
        primaryStage.setTitle("Test App");
        primaryStage.show();
       
        primaryStage.setOnCloseRequest((WindowEvent e)->{
        //Free all occupied resources
          System.out.println("Free ocupied resource");
            
        });
        
    }
    
    @FXML
    public void newScene(ActionEvent e){      
         
        try{            
         Stage stage=(Stage) next.getScene().getWindow();         
        //load up myFXML(main) document
         Parent root = FXMLLoader.load(getClass().getResource("myfxml.fxml"));
        //create a new scene with root and set the stage
            System.out.println("Next: Loading main scene");
        Scene scene = new Scene(root);
         stage.setScene(scene);       
         stage.show();        
        }catch(Exception ex){
            System.err.println(ex+"IOException occured in JavaFXApplication1.newScene");
        }
     
    }
     
    
}
