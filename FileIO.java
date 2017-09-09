/***********************************************************************
  * Collection: Email server
  * Name: Kalvin Thye
  * Description: Opens and closes files methods
  * Date: Nov 2015
  *********************************************************************/
import java.io.*;
public class FileIO {
  public static int openMessagesFile (String fileName) {
    try {
      Globals.msg = new RandomAccessFile(fileName, "rw");
      Globals.totalRecordsInMessageFile = (int)(Globals.msg.length()/Globals.RECORD_LEN);
      return Globals.PROCESS_OK;
    }
    catch (IOException e) {
      return Globals.PROCESS_ERR;
    }
  }
  
  public static int closeMessagesFile() {
    try {
      Globals.msg.close();
      return Globals.PROCESS_OK;
    }
    catch (IOException e) {
      return Globals.PROCESS_ERR;
    }
  }
//  public static int retrieveAvailableList(String fileName) {
//    RandomAccessFile f;
//    try {
//      f = new RandomAccessFile(fileName, "rw");
//      long fSize = f.length();
//      for (int r = 0; r < fSize /4; r++) {
//        Globals.availableList.addRecord(f.readInt());
//      }
//      f.close();
//      return Globals.PROCESS_OK;
//    }
//    catch (IOException e) {
//      return Globals.PROCESS_ERR;
//    }
//    
//  }
//  
//  public static int saveAvailableList(String fileName) {
//    RandomAccessFile f;
//    try {
//      f = new RandomAccessFile(fileName, "rw");
//      for (Available p = Globals.availableList.getHead(); p != null; p = p.getNext()) {
//        f.writeInt(p.getRecordNumber());
//      }
//      f.close();
//      return Globals.PROCESS_OK;
//    }
//    catch (IOException e) {
//      return Globals.PROCESS_ERR;
//    }
//  }
  
}