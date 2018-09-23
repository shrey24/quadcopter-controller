/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;

import static javafx.application.Application.STYLESHEET_MODENA;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.OverrunStyle;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author shrey
 */
public class Popup {
    
    
    static Stage Alert(String Head,String Body){
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
