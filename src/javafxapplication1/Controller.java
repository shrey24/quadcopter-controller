/*
   **********CONTROLLER CLASS**************
 */
package javafxapplication1;

import com.fazecast.jSerialComm.SerialPort;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/** *
 * @author shrey
 */
public class Controller implements Initializable {
   @FXML Button Button1=new Button();   
   @FXML ChoiceBox<String> PortList=new ChoiceBox<>();
   @FXML Label PortListLabel=new Label();
   @FXML Slider SpeedInputZ=new Slider();  @FXML Label SpeedLabelZ=new Label();  @FXML Label KeyLabelZ=new Label();
   @FXML Slider SpeedInputX=new Slider();  @FXML Label SpeedLabelX=new Label();  @FXML Label KeyLabelX=new Label();
   @FXML Slider SpeedInputY=new Slider();  @FXML Label SpeedLabelY=new Label();  @FXML Label KeyLabelY=new Label();
   @FXML Slider SpeedInputYAW=new Slider();  @FXML Label SpeedLabelYAW=new Label(); @FXML Label KeyLabelYAW=new Label();
   
   @FXML Text StatusText=new Text();
   
   static Engine drone;
   //========================================  control Key variables
    String Zkey="W";
    String Xkey="S";
    String Ykey="D";
    String YAWkey="Q";
    String STAB="1";
    String Fbyte="0";
   
   
   @Override
    public void initialize(URL url, ResourceBundle rb) {       
        //Search for ports and update DropDown list "PortList" Accordingly
        SerialPort ports[]= SerialPort.getCommPorts();
        if(ports.length == 0) //No ports fount
        {
         PortListLabel.setText("No ports found");      
        }
        else                  //ports found
        {                 
            for(SerialPort port: ports){
                PortList.getItems().add(port.getSystemPortName()) ;
            }            
        //    PortList.setValue(ports[0].getSystemPortName());
            PortList.setTooltip(new Tooltip("click to select a port"));
            StatusText.setText("Please select a port");
            //Event called when a port is selected
    //        PortList.getSelectionModel().selectedItemProperty().addListener(
    //                (ObservableValue<? extends String> V, String oldValue, String newValue) -> {
    //                    System.out.println("[PORT SELECTED]: new-"+newValue+"  Old - "+oldValue+"    --v="+V);    
    //                
    //                });
       
            //PortList dropdown menu Event
            PortList.setOnAction((ActionEvent e) -> {
                String chosenport = PortList.getValue();
                System.out.println("[PORT SELECTED]: "+chosenport);            
                PortListLabel.setText("");
                
                //Open chosen port and instantiate Engine object
                SerialPort port = SerialPort.getCommPort(chosenport);
               if(port.openPort()){
                   drone=new Engine(port); 
                   System.out.println("@Controller[Initialize] port opened:"+port.getSystemPortName());  
                   StatusText.setText("Status: OK");
                   drone.STAB=Byte.parseByte(STAB); //SET STAB EXPLICITLY ON READING XML 
               }else{
                   Stage st=Popup.Alert("Unable to open port","try some other or restart application");
                         st.showAndWait();
               }              
            });
            
        }
        getKeySettingsFromXML();
        InitSpeedInputSliders();  
        
        
    }
    
    
    /* //TEXT INPUT HANDLING
    @FXML
    public void SpeedInputEntered(ActionEvent e){
        
        System.out.println("SPEED VALUE: "+SpeedInput.getValue());           
        byte speed=Byte.parseByte(SpeedInput.getText());  ///TextField stuff NOT REQUIRED
        if(SpeedInput.getText().matches("[0-9]+") ){
            System.out.println("ONLY DIGITS");
        }
        if(speed>=0 && speed<=100){
            System.out.println("set speed: "+speed);
        }else{
            
        }        
    }
  */    
   
    
    @FXML
    public void Key_released(KeyEvent k){
   // System.out.println("[FROM CONTROLLER CLASS]Key released: " +k.getText());    
    }
    @FXML
    public void Key_pressed(KeyEvent k){
       // System.out.println("{KEY PRESSED}\n");
        if(drone!=null)
        {       
             String InputKey = k.getCode().toString();
             
            if(k.isShiftDown())  //SHIFT + {key}
            {         
                if(InputKey.equalsIgnoreCase(Zkey)){  //X-axis
                   // drone.speedZ=(byte) -drone.speedZ;
                    SpeedInputZ.setValue(-(drone.speedZ));
                  //  SpeedLabelZ.setText(""+drone.speedZ);
                    System.out.println(">>"+drone.speedZ);                    
                    drone.ZMotion();
                }else if(InputKey.equalsIgnoreCase(Xkey)){
                     SpeedInputX.setValue(-(drone.speedX));
                     System.out.println(">>"+drone.speedX);                    
                    drone.XMotion();
                }else if(InputKey.equalsIgnoreCase(Ykey)){
                    SpeedInputY.setValue(-(drone.speedY));
                     System.out.println(">>"+drone.speedY);                    
                    drone.YMotion();
                }else if(InputKey.equalsIgnoreCase(YAWkey)){
                    SpeedInputYAW.setValue(-(drone.speedYAW));
                     System.out.println(">>"+drone.speedYAW);                    
                    drone.YAWMotion();
                }else{                
                 System.out.println("<NOT assigned> SHIFT +  "+k.getCode());
                }
                k.consume();
            }else
            {              // {key}             
            // {W} - give 'up' command
               // String InputKey = k.getCode().toString();
                
                if(InputKey.equalsIgnoreCase(Zkey)){  //X-axis                   
                    drone.ZMotion();
                }else if(InputKey.equalsIgnoreCase(Xkey)){
                    drone.XMotion();
                }else if(InputKey.equalsIgnoreCase(Ykey)){
                    drone.YMotion();
                }else if(InputKey.equalsIgnoreCase(YAWkey)){
                    drone.YAWMotion();
                }else{
                    System.out.println("<NOT assigned> "+InputKey);
                }               
                 k.consume();
            }
            
        }       
    
    }
    
    @FXML
    public void RefreshKey(){     
        if(drone!=null){
             getKeySettingsFromXML();
            drone.STAB=Byte.parseByte(STAB); //SET STAB EXPLICITLY ON READING XML 
        }else{
            Stage st=Popup.Alert("Error","Please select a port First, and then press Refresh Key");
            st.showAndWait();
        }
       
        
    }
            
            
    void getKeySettingsFromXML(){
        
        try{
            File xml = new File("config.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xml);
            doc.getDocumentElement().normalize();
            
            System.out.println("----------------------key Configurations-----------------------");
            // Obtain tags and read settings
            Node n=doc.getElementsByTagName("Z-axis").item(0) ;
            System.out.println(">> Z-axis : ");
            Element e=(Element)n;
            Zkey=e.getElementsByTagName("key").item(0).getTextContent();
            System.out.println("key: "+Zkey);
            
            n=doc.getElementsByTagName("X-axis").item(0) ;
            System.out.println(">> X-axis : ");
            e=(Element)n;
            Xkey=e.getElementsByTagName("key").item(0).getTextContent();
            System.out.println("key: "+Xkey);
            
            n=doc.getElementsByTagName("Y-axis").item(0) ;
            System.out.println(">> Y-axis : ");
            e=(Element)n;
            Ykey=e.getElementsByTagName("key").item(0).getTextContent();
            System.out.println("key: "+Ykey);
            
            n=doc.getElementsByTagName("YAW").item(0) ;
            System.out.println(">> YAW : ");
            e=(Element)n;
            YAWkey=e.getElementsByTagName("key").item(0).getTextContent();
            System.out.println("key: "+YAWkey);
            
     //STAB Config       
           n=doc.getElementsByTagName("STAB").item(0) ;
            System.out.println(">> STAB : ");
            e=(Element)n;
            STAB=e.getElementsByTagName("value").item(0).getTextContent();
            System.out.println("Value: "+STAB);
            
       //Fbyte Config       
//           n=doc.getElementsByTagName("Fbyte").item(0) ;
            System.out.println(">> Fbyte currently unavailable : ");
//            e=(Element)n;
            

        // GUI KeyLabel update --
        KeyLabelX.setText(Xkey);
        KeyLabelY.setText(Ykey);
        KeyLabelZ.setText(Zkey);
        KeyLabelYAW.setText(YAWkey);
        
            
        }catch(Exception e){
            System.out.println("!ERROR  @getting Key Settings From XML:\n");
            System.out.println(e);
            System.out.println("\n LOADING DEFAULT CONFIG ----");
            System.out.println("Z-axis key: "+Zkey);
            System.out.println("X-axis key: "+Xkey);
            System.out.println("Y-axis key: "+Ykey);
            System.out.println("YAW key: "+YAWkey);
            // GUI KeyLabel update --
            KeyLabelX.setText(Xkey);
            KeyLabelY.setText(Ykey);
            KeyLabelZ.setText(Zkey);
            KeyLabelYAW.setText(YAWkey);
        }
        
        
        
    }
    
    
    
    void InitSpeedInputSliders(){  //Speed slidebar
        
        SpeedInputZ.adjustValue(0); SpeedLabelZ.setText("0");
        
//TEST>>>>>>>>>>>> drone=new Engine();  //INITIALIZE FOR TESTING

// Z - SLIDER

        SpeedInputZ.valueProperty().addListener((e,oldVal,newVal)->{            
        //System.out.println("@slideBar Changed- New Val: "+Math.floor(Double.parseDouble(newVal.toString()))+" SPEED VALUE: "+SpeedInputZ.getValue());            
        //    System.out.println("[[[[[[[[[[[[[[[[[[[   slider listener ]]]]]]]]]]]]]]]]]");    
        try{
                drone.speedZ=(byte)Double.parseDouble(newVal.toString());         
                SpeedLabelZ.setText(""+drone.speedZ); //Math.floor(Double.parseDouble(newVal.toString()))

                System.out.println("Z-axis >> SPEED SET: "+drone.speedZ);
            }catch(Exception ex){
                System.out.println("@slideBar ERROR: set the port first");
            }
            
        });
  
 // X - SLIDER       
        SpeedInputX.adjustValue(0); SpeedLabelX.setText("0");
        SpeedInputX.valueProperty().addListener((e,oldVal,newVal)->{            
        
            try{
                drone.speedX=(byte)Double.parseDouble(newVal.toString());         
                SpeedLabelX.setText(""+drone.speedX); //Math.floor(Double.parseDouble(newVal.toString()))

                System.out.println("X-axis >> SPEED SET: "+drone.speedX);
            }catch(Exception ex){
                System.out.println("@slideBar ERROR: set the port first");
            }            
        });
        
 // Y - SLIDER       
        SpeedInputY.adjustValue(0); SpeedLabelY.setText("0");
        SpeedInputY.valueProperty().addListener((e,oldVal,newVal)->{            
        
            try{
                drone.speedY=(byte)Double.parseDouble(newVal.toString());         
                SpeedLabelY.setText(""+drone.speedY); //Math.floor(Double.parseDouble(newVal.toString()))

                System.out.println("Y-axis >> SPEED SET: "+drone.speedY);
            }catch(Exception ex){
                System.out.println("@slideBar ERROR: set the port first");
            }            
        });
        
 // YAW - SLIDER       
        SpeedInputYAW.adjustValue(0); SpeedLabelYAW.setText("0");
        SpeedInputYAW.valueProperty().addListener((e,oldVal,newVal)->{            
        
            try{
                drone.speedYAW=(byte)Double.parseDouble(newVal.toString());         
                SpeedLabelYAW.setText(""+drone.speedYAW); //Math.floor(Double.parseDouble(newVal.toString()))

                System.out.println("YAW >> SPEED SET: "+drone.speedYAW);
            }catch(Exception ex){
                System.out.println("@slideBar ERROR: set the port first");
            }            
        });
    }

    
}
