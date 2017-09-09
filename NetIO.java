import java.net.*;
import java.io.*;

public class NetIO {
  public static String myIPAddress() {
    String ipAddress = "";
    try {
      InetAddress myComputer = InetAddress.getLocalHost();
      ipAddress = myComputer.getHostAddress();
      
    } catch (Exception e) {
      System.out.println("Error in getting ip address");
    }//try-catch construct close 
    return ipAddress;
  }//public method myIPAddress close
  
  
  public static String myUsername() {
    String userName = "";
    try {
      userName = System.getProperty("user.name");
      
    } catch (Exception e) {
      System.out.println("Error in getting username");
    }//try-catch construct close 
    return userName;
  }//public method myUsername close
  
  
  public static int sendRequest(String message, String destinationIPAddress) {
    int errorCode = -1;
    try {
      Socket me = new Socket(); //gives channel on the line 
      me.connect(new InetSocketAddress(destinationIPAddress, 5000), 10000); //5000 is port number, 10000 is timeout, in milliseconds
      me.setSoTimeout(10000); //confirmation timeout, if information is not confirmed to be sent within time then error
      
      DataOutputStream output = new DataOutputStream(me.getOutputStream());
      output.writeUTF(message);//sends message to the other side
      
      DataInputStream input = new DataInputStream(me.getInputStream());
      String confirmation = input.readUTF();
      
      if (isANumber(confirmation)) 
        errorCode = Integer.parseInt(confirmation);
      me.close();
    } catch (IOException e) {
      System.out.println("Error in sending message");
    }//try-close construct close
    return errorCode;
  }//public method sendRequest close
  
  
  public static boolean isANumber(String s) {
    boolean result = true;
    for (int i = 0; i < s.length() && result == true; i++) 
      result = Character.isDigit(s.charAt(i));
    
    return result;
  }
  
  public static String receiveRequest() {
    String request = "";
    int errorCode = -1;
    try {
      ServerSocket server = new ServerSocket (5000, 100); //100 numbers maximum that can be queued
      Socket me = server.accept(); //makes machine wait for requests
      me.setSoTimeout(10000);
      
      DataInputStream input = new DataInputStream(me.getInputStream());
      request = input.readUTF(); 
      
      Globals.clientIPAddress = me.getInetAddress().getHostAddress();
      
      DataOutputStream output = new DataOutputStream(me.getOutputStream());
      output.writeUTF("0");
      
      me.close();
      server.close();
      errorCode = 0;
    } catch (IOException e) {
      System.out.println("Error receiving message" + errorCode);
    }//try-catch construct error

    
    return request;
  }//receiveRequest close
  
  //JEFFFRET
    public static void addToTransmissionString(int recordNumber) {
  Message message = new Message();
  message.readFromMessagesFile(recordNumber);
  Globals.transmissionString = Globals.transmissionString + message.getAsString() + Utils.intToBytesStr(-1);
 }

  
  
    public static void main(String [] args) {
      System.out.println(myIPAddress());
    }
}//class close
