/***********************************************************************
  * Collection: Email server
  * Name: Kalvin Thye
  * Description: Global variables and constants
  * Date: Nov 2015
  *********************************************************************/
import java.io.*;
public class Globals {
  //sketchy
  public static Record r = new Record ("",-1);
 // debug constants
    public static final boolean DEBUG_ON = false; 

    // initialization constants
    public static final int NULL = 0;
    public static final String STR_NULL = "";
    public static final char BLANK = ' ';
    public static final char CR = '\n';

    // error constants
    public static final int PROCESS_OK = 0;
    public static final int PROCESS_ERR = -1;
    
    // position constants for message
    public static final int COMMAND_POS = 0;
    public static final int COMMAND_LEN = 1;
    public static final int SENDER_POS  = COMMAND_POS + COMMAND_LEN;
    public static final int SENDER_LEN  = 9;
    public static final int RECEIVER_POS = SENDER_POS + SENDER_LEN;
    public static final int RECEIVER_LEN  = 9;
    public static final int DATE_TIME_POS = RECEIVER_POS + RECEIVER_LEN;
    public static final int DATE_TIME_LEN = 8;  // long current milliseconds coded as eight bytes
    public static final char MARKER = '+';
    public static final int MARKER_POS = DATE_TIME_POS + DATE_TIME_LEN;
    public static final int MARKER_LEN = 1;
    public static final char END_OF_SUBJECT_MARKER = '~';
    public static final int END_OF_SUBJECT_MARKER_LEN = 1;
                                                 
    // constants for records; note that the first record of a message will contain the
    // identification and marker; therefore, there will be more room for text in the
    // subsequent records
    
    public static final int TEXT_LEN = END_OF_SUBJECT_MARKER_LEN + 30;
    public static final int RECORD_DATA_LEN = COMMAND_LEN   + 
                                              SENDER_LEN    +
                                              RECEIVER_LEN  +
                                              DATE_TIME_LEN +
                                              MARKER_LEN    +
                                              TEXT_LEN;
    
    public static final int NEXT_RECORD_LEN = 4;  // integer that stores pointer to next record
    public static final int RECORD_LEN = RECORD_DATA_LEN + NEXT_RECORD_LEN;  // integer at end: 40 bytes
    public static final int END_OF_MESSAGE = -1;                             // marks end of list of records that make up a message
    
    // message and record delimiters; characters used so that special ascii like 1, 2, 3, can be used    
    public static final char FIRST_RECORD_OF_MESSAGE = '+';       // we mark the start of a message with this marker in case we have to rebuild the indices
    public static final char DELETED = '*';                // this character will only be placed at front of every record in case
          // we need to rebuild the deleted linked list 
    
    // constants for linked list of available records
    public static int AVAILABLE_LIST_IS_EMPTY = -1; // no records deleted.

    // global variables
    public static RandomAccessFile msg = null;       // main messages file
    public static AvailableList availableList = new AvailableList();    // start of available of records linked list
    public static int totalRecordsInMessageFile = 0; // update every time a record is added; fileLen does not update fast enough  
    
    public static final int APPEND = 1;  // modes for writing into the file
    public static final int MODIFY = 2; 
    
    // messages file
    public static final String MESSAGES_FILE = "messages.kas";
    
    // available list file
    public static final String AVAILABLE_LIST_FILE = "available.kas";
    public static final int AVAILABLE_NODE_RECORD_NUMBER_LEN = 4;
    
    // binary tree file
    public static final String SENDER_TREE_FILE = "stree.txt";  // tree by sender
    public static final String RECIPIENT_TREE_FILE = "rtree.txt";  // tree by recipient
    
    public static final int KEY_LEN = SENDER_LEN + RECEIVER_LEN + DATE_TIME_LEN;
    //public static Node senderIndexRoot = null;
    //public static Node recipientIndexRoot = null;
    
    //for Tree stuff (Kalvin)
    public static final int SENDER_ID = 0;
    public static final int RECEIVER_ID = 1;
    
    //Jeffrey's transmission stuff
     public static String transmissionString = "";
     
     //clement 
     public static final int IDENTIFICATION_LEN = SENDER_LEN + RECEIVER_LEN + DATE_TIME_LEN;

     public static final int NODE_LEN = IDENTIFICATION_LEN + 4; // 4 is the number of bytes in the recordnumber
     
     //new stuff
     public static final char OUT_BOX = 'O';
     public static final char IN_BOX = 'I';
     public static String clientIPAddress = null;

     public static final int  END_OF_MESSAGES_TRANSMISSION = -2; 
     
     //client stuff
     public static final char SEND_MESSAGE = 'S';
     public static Tree senderIndex = new Tree();
     public static Tree receiverIndex = new Tree(); 
     

}