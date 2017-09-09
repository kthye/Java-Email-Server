/***********************************************************************
  * Collection: Email server
  * Name: Kalvin Thye
  * Description: class of static methods containing utils
  * Date: Nov 2015
  *********************************************************************/
public class Utils {
  
  public static String leftPad(String text, int desiredLength, char paddingItem) {
    int textLength = text.length();
    if (text.length() < desiredLength) {
      for (int i = 0; i < desiredLength-textLength; i++) {
        text = paddingItem + text;
      }
    } else if (textLength == 0) {
      for (int i = 0; i < desiredLength; i++) {
        text+= paddingItem;
      }
    }
    return text;
    
  }
  public static String removeChars(String text, char c) {
    String newText = "";
    for (int i = 0; i < text.length(); i++) {
      if (text.charAt(i) != c) {
        newText += text.charAt(i);
      }
    }
    return newText;
  }
  public static void printAvailableList() {
    Available p;
    for (p = Globals.availableList.getHead(); p != null; p = p.getNext()) {
      System.out.println(p);
    }
  }
  
  public static String setReceivingTime(String request){
    return request.substring(0,Globals.DATE_TIME_POS) + longToBytesStr(System.currentTimeMillis()) + request.substring(Globals.MARKER_POS);
  }
  
  
  public static String longToBytesStr(long number) {
    
    return "" + 
      (char)(number >> 56) +
      (char)((number & 0xFF000000000000L) >> 48) +
      (char)((number & 0xFF0000000000L) >> 40) +
      (char)((number & 0xFF00000000L) >> 32) +
      (char)((number & 0xFF000000L) >> 24) +
      (char)((number & 0xFF0000L) >> 16) +
      (char)((number & 0xFF00L) >> 8) +
      (char)(number & 0xFFL);
    
    
  }
  
  public static long bytesStrToLong(String s) { 
    return ((long)(s.charAt(0)) << 56) |
      ((long)(s.charAt(1)) << 48) |
      ((long)(s.charAt(2)) << 40) |
      ((long)(s.charAt(3)) << 32) | 
      ((long)(s.charAt(4)) << 24) | 
      ((long)(s.charAt(5)) << 16) |
      ((long)(s.charAt(6)) << 8) |
      ((long)(s.charAt(7)));
  }
  
  public static String intToBytesStr(int n){
    return "" + 
      (char) (n  >> 24) +
      (char) ((n & 0xFF0000) >> 16) +
      (char) ((n & 0xFF00) >> 8) +
      (char)  (n & 0xFF);
  }
  
  public static long bytesStrToInt(String s) {
    String strValue = "";
    
    for (int i = 0; i<s.length(); i++){
      strValue += Integer.toHexString((int) (s.charAt(i)));
    }
    
    return 
      
      Integer.parseInt(strValue,16);
  }
  
  
  
  public static void main(String[] args) {
    long n =  4702942340726470000L;
    System.out.println(bytesStrToLong(longToBytesStr(n))); 
  }
}
