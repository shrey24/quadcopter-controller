# quadcopter-controller desktop application

This java based application is used to control a quadcopter (drone) and also receive the data from it. I've Used java serial communication library, that can send raw data to any communication port available in the computer running this software. It follows a custom protocol for communication (see below for details).

<img src="https://github.com/shrey24/quadcopter-controller/blob/master/pics/welcome.png" width=70%/>

### Demo:
<img src="https://github.com/shrey24/quadcopter-controller/blob/master/pics/controller_demo.gif" width=70%/>

1. This software reads for all available serial communication ports. (Ex. if you attach a new wireless device to a USB port, it will show up.)
2. Select the port you choose for communication.
3. The X Y Z axis and YAW represent the movement directions of quadcopter. The value range is -100 to +100.
4. Press the corresponding key to send the command to the quadcopter.
5. You can change the key configurations by editing the <b>config.xml</b> file. Hit <b>Refresh Keys</b> button to make the changes in config.xml effictive.

### Protocol of communication
#### Data sent:
Each time data is sent in bunch of six bytes. Each byte represents specific value. The sent data is as follows:
<img src="https://github.com/shrey24/quadcopter-controller/blob/master/pics/data_send_table.png"/>

Where,
STAB – Stabilization
HOLD is a special feature which makes copter steady at a point as soon as user release motion keys.
If HOLD is off copter stays in motion due to inertia of copter and user has to make it steady by controlling. 
±100 is in %. X byte having value of -100 means motion in negative X direction (backward motion).
FByte:
Quad-copter software runs on the top of Linux kernel 4.x in raspberry pi. Any external program other than quad-copter control software can be executed in separate user privilege using this byte. For example: if raspberry pi can read data from humidity sensor

<b>Note:</b> Currently control software supports 254 function values. On execution control software can be configured to reply a response called FResponse byte in response data.

#### Data received:
ON SUCCESSFUL RECEIVING OF DATA QUAD-COPTER WILL REPLY WITH A RESPONSE
RESPONSE CONTAINS THREE BYTES OF DATA:
<img src="https://github.com/shrey24/quadcopter-controller/blob/master/pics/data_from_drone.png"/>


### Requirements
- needs Java8 installed on the system
### Installation and starting the App
- Download this project
- go to the project folder ( that has JavaFXApplication1.jar file) using terminal or command prompt
- run command: java -jar JavaFXApplication1.jar
- your app is up and ready!


