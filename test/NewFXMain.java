/*
 *  THIS CLASS IS TO TRY GUI DESIGNS 
 */

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.STYLESHEET_MODENA;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.OverrunStyle;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author shrey
 */
public class NewFXMain extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
//        Button btn = new Button();
//        btn.setText("Say 'Hello World'");
//        btn.setOnAction(new EventHandler<ActionEvent>() {
//            
//            @Override
//            public void handle(ActionEvent event) {
//                String s="Body Body Body  ";
//               popUp("HEADING",s).showAndWait();
//               
//            }
//        });
//        
//        StackPane root = new StackPane();
//        root.getChildren().add(btn);
        Parent root;
       
            root = FXMLLoader.load(getClass().getResource("TestFXML.fxml"));
       
        
        Scene scene = new Scene(root, 900, 900);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    Stage popUp(String Head,String Body){
      Button btn=new Button("Ok");
      Label head=new Label();
      Label body=new Label();
      
      head.setText(Head); head.fontProperty().set(Font.font(STYLESHEET_MODENA,20));
      body.setText(Body); body.setTextOverrun(OverrunStyle.ELLIPSIS); body.autosize();
      
      VBox root=new VBox();
      root.getChildren().addAll(head,body,btn);
      root.setSpacing(20);
      root.setAlignment(Pos.CENTER);
      
      //root.autosize();
      
      Scene scene=new Scene(root);  //(root,width,height)
      
      
      
      Stage popWindow=new Stage();
      popWindow.setScene(scene);
      popWindow.initModality(Modality.APPLICATION_MODAL);
      popWindow.setTitle("Alert");
      popWindow.setMinWidth(200);
      popWindow.setMinHeight(100);
      
      btn.setOnAction((ActionEvent e)->{
          popWindow.close();
      });
      
      return popWindow;
    }

    
}
