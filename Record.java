/***********************************************************************
  * Collection: Email server
  * Name: Kalvin Thye
  * Description: Object class Record, a node containing a message
  * Date: Nov 2015
  *********************************************************************/
import java.io.*;
public class Record {
  private byte[] data = new byte[Globals.RECORD_DATA_LEN];
  private int next = Globals.END_OF_MESSAGE;
  
  //Default constructor 
  public Record() {
    for (int i = 0; i < Globals.RECORD_DATA_LEN; i++) {
      data[i] = Globals.BLANK;
    }
    next = Globals.END_OF_MESSAGE;
  }
  
//Constructor with parameters
  public Record (String s, int nextRecord) {
    setValues(s, nextRecord);
  }
  
  
  //Access methods
  
  public void setValues(String txt, int nextRecord) {
    int i  = 0;
    for (; i  < txt.length(); i++) {
      data[i]  = (byte) txt.charAt(i);
    }
    for (; i < Globals.RECORD_DATA_LEN; i++) {
      data[i] = ' ';
    }
    next = nextRecord; 
  }
  
  public String getData() {
    String s = "";
    for (int i = 0; i < Globals.RECORD_DATA_LEN; i++) {
      s = s + (char) data[i];
    }
    return s;
  }
  
  public int getNext() {
    return next;
  }
  
  public int writeToMessagesFile(int recordNumber, int mode) {
    try {
      Globals.msg.seek(Globals.RECORD_LEN * recordNumber);
      Globals.msg.write(data);
      Globals.msg.writeInt(next);
      if (mode == Globals.APPEND) {
        Globals.totalRecordsInMessageFile++;
      }
      return Globals.PROCESS_OK;
    }
    catch (IOException e) {
      return Globals.PROCESS_ERR;
    }
  }
  
  public int readFromMessagesFile(int recordNumber) {
    try {
      Globals.msg.seek(Globals.RECORD_LEN * recordNumber);
      Globals.msg.read(data);
      //Globals.msg.read(data, 0, Globals.RECORD_LEN-4);
      next = Globals.msg.readInt();
      
      //System.out.println(getData() + next);
      return Globals.PROCESS_OK;
    }
    catch (IOException err) {
      return Globals.PROCESS_ERR;
    }
  }

  public void deleteFromMessagesFile(int recordNumber, int mode) {
    readFromMessagesFile(recordNumber);
    if (mode == Globals.APPEND) {
      data[0] = '@';
      writeToMessagesFile(recordNumber, 0) ;
      Globals.availableList.addRecord(recordNumber);
    }
}

/*move the messages file pointer to the beginning of the record
 from the file, read and store into the array bytes (the number of bytes does not
 need to be specified with the method read(); it automatically reads the correct
 number of bytes)
 read and store into the variable next (we need to use readInt() in the same way
 we used writeInt() in the writing method)*/
//System.out.print method
public String toString() {
  return getData() + next;
}

}
