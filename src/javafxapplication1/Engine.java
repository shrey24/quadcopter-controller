package javafxapplication1;

import com.fazecast.jSerialComm.*;

public class Engine {
    // byte array to send { x, y, z, YAW , STAB(1,2,3) , FByte(0-255) }
    SerialPort port=null;    
    
    private boolean isPortSet=false;
    public byte speedZ=0;
    public byte speedX=0;
    public byte speedY=0;
    public byte speedYAW=0;
    public byte STAB=1;
    public byte fbyte=0;
    
    Engine(){        
    }
    
    Engine(SerialPort port){
        this.port = port;
        
        isPortSet=true;
        
    }
    
    void setPort(SerialPort port){
        this.port = port;
        isPortSet=true;
    }
    
    void ZMotion(){
        if(isPortSet==true){
            //port.setComPortTimeouts(diff, diff, diff);
            byte[] cmd = new byte[6];
            cmd[0]=0;       //x
            cmd[1]=0;       //y
            cmd[2]=speedZ;   //z
            cmd[3]=0;       //YAW
            cmd[4]=STAB;       //STAB
            cmd[5]=0;       //fbyte
             
            
           int n = port.writeBytes(cmd,cmd.length);         //blocking call - writeBytes()
            System.out.println("@Engine [SEND to port]: ");
            for(int i=0;i<cmd.length;i++){
                System.out.println(" | byte["+i+"]="+cmd[i] +" |");
            }
            System.out.println(n+"bytes written successfully");
            
            byte[] read=new byte[3];                            
            int r=port.readBytes(read, read.length);        //blocking call - readBytes()
            for(int i=0;i<read.length;i++){
                System.out.println(" | byte["+i+"]="+read[i] +" |");
            }
            System.out.println("@Engine [RECIVED from port]: "+r+"bytes read successfully");
            
          
        }
        else
        {
            System.out.println("@Engine ERROR in method: [void ZMotion] property 'port' not set");
        }    
        
    }
    
     void YMotion(){
        if(isPortSet==true){
            //port.setComPortTimeouts(diff, diff, diff);
            byte[] cmd = new byte[6];
            cmd[0]=0;       //x
            cmd[1]=speedY;       //y
            cmd[2]=0;   //z
            cmd[3]=0;       //YAW
            cmd[4]=STAB;       //STAB
            cmd[5]=0;       //fbyte
            
           int n = port.writeBytes(cmd,cmd.length);         //blocking call - writeBytes()
            System.out.println("@Engine [SEND to port]: ");
            for(int i=0;i<cmd.length;i++){
                System.out.println(" | byte["+i+"]="+cmd[i] +" |");
            }
            System.out.println(n+"bytes written successfully");
           
            byte[] read=new byte[3];                            
            int r=port.readBytes(read, read.length);        //blocking call - readBytes()
            for(int i=0;i<read.length;i++){
                System.out.println(" | byte["+i+"]="+read[i] +" |");
            }
            System.out.println("@Engine [RECIVED from port]: "+r+"bytes read successfully");
        }
        else
        {
            System.out.println("@Engine ERROR in method: [void YMotion] property 'port' not set");
        }    
        
    }
    
      void XMotion(){
        if(isPortSet==true){
            //port.setComPortTimeouts(diff, diff, diff);
            byte[] cmd = new byte[6];
            cmd[0]=speedX;       //x
            cmd[1]=0;       //y
            cmd[2]=0;   //z
            cmd[3]=0;       //YAW
            cmd[4]=STAB;       //STAB
            cmd[5]=0;       //fbyte
            
           int n = port.writeBytes(cmd,cmd.length);         //blocking call - writeBytes()
            System.out.println("@Engine [SEND to port]: ");
            for(int i=0;i<cmd.length;i++){
                System.out.println(" | byte["+i+"]="+cmd[i] +" |");
            }
            System.out.println(n+"bytes written successfully");
           
            byte[] read=new byte[3];                            
            int r=port.readBytes(read, read.length);        //blocking call - readBytes()
            for(int i=0;i<read.length;i++){
                System.out.println(" | byte["+i+"]="+read[i] +" |");
            }
            System.out.println("@Engine [RECIVED from port]: "+r+"bytes read successfully");
        }
        else
        {
            System.out.println("@Engine ERROR in method: [void XMotion] property 'port' not set");
        }    
        
    }
     
       void YAWMotion(){
        if(isPortSet==true){
            //port.setComPortTimeouts(diff, diff, diff);
            byte[] cmd = new byte[6];
            cmd[0]=0;       //x
            cmd[1]=0;       //y
            cmd[2]=0;   //z
            cmd[3]=speedYAW;       //YAW
            cmd[4]=STAB;       //STAB
            cmd[5]=0;       //fbyte
            
           int n = port.writeBytes(cmd,cmd.length);         //blocking call - writeBytes()
            System.out.println("@Engine [SEND to port]: ");
            for(int i=0;i<cmd.length;i++){
                System.out.println(" | byte["+i+"]="+cmd[i] +" |");
            }
            System.out.println(n+"bytes written successfully");
           
            byte[] read=new byte[3];                            
            int r=port.readBytes(read, read.length);        //blocking call - readBytes()
            for(int i=0;i<read.length;i++){
                System.out.println(" | byte["+i+"]="+read[i] +" |");
            }
            System.out.println("@Engine [RECIVED from port]: "+r+"bytes read successfully");
        }
        else
        {
            System.out.println("@Engine ERROR in method: [void YAWMotion] property 'port' not set");
        }    
        
    }
    
}
