public class TNode {
  //non static because shouldn't retain value like totalMessagesInFile <-- something like that
  private String id = "";
  private int recordNumber = -1;
  private TNode left = null;
  private TNode right = null;
  private TNode parent = null;
  
  public TNode() {
    id = "";
    recordNumber = -1;
    left = null;
    right = null;
    parent = null;
  }
  
  public TNode (String i, int record, TNode l, TNode r, TNode p) {
    id = i;
    recordNumber = record;
    left = l;
    right = r;
    parent = p;
  }
  
  public String getID() {
    return id;
  }
  
  public int getRecordNumber() {
    return recordNumber;
  }
  
  public TNode getLeft() {
    return left;
  }
  
  public TNode getRight() {
    return right;
  }
  
  public TNode getParent() {
    return parent;
  }
  
  public void setID(String i) {
    id = i;
  }
  
  public void setRecordNumber(int rn) {
    recordNumber = rn;
  }
  
  public void setLeft(TNode l) {
    left = l;
  }
  
  public void setRight(TNode r) {
    right = r;
  }
  
  public void setParent(TNode p) {
    parent = p;
  }
  
  public String toString() {
    //this means object that made the call
    if (this == null) {
      return "null";
    } else {
    return id + " " + recordNumber;
    }
  }
  
}
