/***********************************************************************
  * Collection: Email server
  * Name: Kalvin Thye
  * Description: Main program
  * Date: Nov 2015
  *********************************************************************/
//import java.util.*;

public class Hub {
  public static void main(String[] args) {
    //Scanner in = new Scanner(System.in);
    boolean shutdown = false;
    Message message = new Message();
    int recordNumber = -1;
    String identification = "";
    TNode p, q;
    
    
    int error = FileIO.openMessagesFile(Globals.MESSAGES_FILE);
    if (error == Globals.PROCESS_OK) {
      error = Globals.senderIndex.breadthFirstRetrieve("senderIndex.kas");
      if (error == Globals.PROCESS_OK) {
        
        error = Globals.receiverIndex.breadthFirstRetrieve("receiverIndex.kas");
        if (error == Globals.PROCESS_OK) {
          error = FileIO.retrieveAvailableList(Globals.AVAILABLE_LIST_FILE);
          if (error == Globals.PROCESS_OK) {
            System.out.println("Receiving at " + NetIO.myIPAddress());
            
            //***********************************************************************************
            /*Globals.senderIndex.breadthFirstPrint();
             identification = "322022310";
             p = Globals.senderIndex.findNode(identification, 0);
             q = Globals.senderIndex.findNode(identification, 1);
             System.out.println(p);
             System.out.println(q);
             Globals.senderIndex.prepareTransmissionString(p, q);
             Globals.transmissionString += Utils.intToBytesStr(Globals.END_OF_MESSAGES_TRANSMISSION);
             System.out.println(Globals.transmissionString);*/
            //***********************************************************************************
            do {
              String request = NetIO.receiveRequest();
              //System.out.println(request);
              switch (request.charAt(0)) {
                case 'S':
                  System.out.println("Incoming message...");
                  //changes time to time received?
                  message = new Message();
                  request = Utils.setReceivingTime(request);
                  message.setMessage(request);
                  
                  //writes message received to file and gets recordNumber
                  recordNumber = message.writeToMessagesFile();
                  System.out.println("The incoming message was saved:");
                  //prints message received
                  message.printFromMessagesFile(recordNumber);
                  
                  
                  
                  //records sender ID
                  identification = message.getId();
                  p = new TNode (identification, recordNumber, null, null, null);
                  Globals.senderIndex.insertNode(p);
                  Globals.senderIndex.breadthFirstSave("senderIndex.kas");
                  
                  //records receiver ID
                  identification = message.getReceiverId();
                  p = new TNode (identification, recordNumber, null, null, null);
                  Globals.receiverIndex.insertNode(p);
                  Globals.receiverIndex.breadthFirstSave("receiverIndex.kas");
                  break;
                  
                case Globals.IN_BOX:
                  //message = new Message();
                  //message.setMessage(request);
                  identification = request.substring(1, 10);
                  //System.out.println(identification);
                  //System.out.println("1");
                  System.out.println(identification);
                  p = Globals.receiverIndex.findNode(identification, 0);
                  //System.out.println("2");
                  q = Globals.receiverIndex.findNode(identification, 1);
                  //System.out.println("3");
                  //System.out.println(p);
                  //System.out.println(q);
                  Globals.receiverIndex.prepareTransmissionString(p, q);
                  //System.out.println("4");
                 Globals.transmissionString = Globals.transmissionString + Utils.intToBytesStr(Globals.END_OF_MESSAGES_TRANSMISSION);
                  //System.out.println("5");
                  System.out.println("The following messages were sent to " + Globals.clientIPAddress+ ": \n" + Globals.transmissionString);
                  //System.out.println("6");
                  NetIO.sendRequest(Globals.transmissionString,  Globals.clientIPAddress);
                  
                  //System.out.println("7");
                  Globals.transmissionString = "";
                  break;
                  
                case Globals.OUT_BOX:
                  identification = request.substring(1, 10);
                  //System.out.println(identification);
                  //System.out.println("1");
                  //System.out.println(identification);
                  p = Globals.senderIndex.findNode(identification, 0);
                  //System.out.println("2");
                  q = Globals.senderIndex.findNode(identification, 1);
                  //System.out.println("3");
                  //System.out.println(p);
                  //System.out.println(q);
                  Globals.senderIndex.prepareTransmissionString(p, q);
                  //System.out.println("4");
                  //System.out.println(Utils.intToBytesStr(Globals.END_OF_MESSAGES_TRANSMISSION));
                  Globals.transmissionString = Globals.transmissionString + Utils.intToBytesStr(Globals.END_OF_MESSAGES_TRANSMISSION);
                  // System.out.println("5");
                  System.out.println("The following messages were sent to " + Globals.clientIPAddress+ ": \n" + Globals.transmissionString);
                  //System.out.println("6");
                  NetIO.sendRequest(Globals.transmissionString,  Globals.clientIPAddress);//10.104.85.45
                  //System.out.println("7");
                  Globals.transmissionString = "";
                  break;
                  
                  /*String identification = get the client’s id from the request
                   TNode p = find the lowest node in the sender tree with the
                   partial id identification
                   TNode q = find the highest node in the sender tree with the
                   partial id identification
                   prepare the transmission string
                   add to transmission string intToBytesStr(Globals.END_OF_TRANMISSION)
                   2
                   send transmission string to client
                   set transmission string to null string
                   default:
                   print “unknown request” message on server screen*/
                  
                  
                default :
                  System.out.println("Unknown request");
                  break;
              }
              System.out.println();
            } while(!shutdown);
            
          } else {
            System.out.println("Opening available list error");
            FileIO.closeMessagesFile();
          }
        } else {
          System.out.println("Opening receiverIndex error");
          FileIO.closeMessagesFile();
        }
      } else {
        System.out.println("Opening senderIndex error");
        FileIO.closeMessagesFile();
      }
      FileIO.closeMessagesFile();
    } else {
      System.out.println("Opening messages file error");
    }
    
  }
  
}

