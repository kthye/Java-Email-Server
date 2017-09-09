  import java.io.*;
public class Tree {
  private TNode root = null;
  public Tree () {
    root = null;
  }

  public Tree (TNode r) {
    root = r;
  }

  public void setRoot(TNode r) {
    root = r;
  }

  public TNode getRoot() {
    return root;
  }

  public int height () {
    if (root == null) {
      return 0;
    } else if (root.getLeft() != null && root.getRight() != null) {
      Tree tree1 = new Tree (root.getLeft());
      Tree tree2 = new Tree(root.getRight());
      return Math.max(tree1.height(), tree2.height()) + 1;

    } else if (root.getLeft() != null && root.getRight() == null) {
      Tree tree = new Tree (root.getLeft());
      return 1 + tree.height();
    }
    else if (root.getRight() != null && root.getLeft() == null) {
      Tree tree = new Tree(root.getRight());
      return 1 + tree.height();

    } else {
      return 1;
    }
  }
  public void insertNode(TNode p) {
    insertNodeRec(p);
    balance(p);
  }


  private void insertNodeRec(TNode p) {
    if (root == null) {
      root = p;
    } else if(p.getID().compareTo(root.getID()) < 0) {
      if (root.getLeft() == null) {
        root.setLeft(p);
        p.setParent(root);
      } else {
        Tree tree = new Tree(root.getLeft());
        tree.insertNodeRec(p);
      }
    } else if(p.getID().compareTo(root.getID()) > 0) {
      if (root.getRight() == null) {
        root.setRight(p);
        p.setParent(root);
      } else {
        Tree tree = new Tree(root.getRight());
        tree.insertNodeRec(p);
      }
    } else {
      System.out.println("Error: Duplicated Id");
    }

  }

  public void printTree() {
    if (root == null) {
    } else {
      Tree tree = new Tree (root.getLeft());
      tree.printTree();
      System.out.println(root);
      tree = new Tree(root.getRight());
      tree.printTree();
    }
  }

  public int nodeCount(int c) {
    if (root == null) {
    } else {
      c++;
      Tree tree = new Tree (root.getLeft());
      c= tree.nodeCount(c);
      tree = new Tree(root.getRight());
      c =tree.nodeCount(c);
    }
    return c;
  }

  public int printTree(String[] sortedList, int c) {
    if (root == null) {
    } else {
      Tree tree = new Tree(root.getLeft());
      c = tree.printTree(sortedList, c);
      tree = new Tree(root.getRight());
      c = tree.printTree(sortedList, c);
      c++;
    }

    return c;
  }

//printtest
  public void printTest() {
    if (root == null) {
    } else {
      Tree tree = new Tree (root.getLeft());
      tree.printTree();
      System.out.println(root);
      tree = new Tree(root.getRight());
      tree.printTest();
    }
  }

//find Node
  public TNode findNode(String identification) {
    if (root == null) {
      return null;
    } else {
      if (root.getID().equals(identification)) {
        return root;
      } else if (identification.compareTo(root.getID()) < 0){
        Tree tree = new Tree(root.getLeft());
        return tree.findNode(identification);
      } else if (identification.compareTo(root.getID()) > 0) {
        Tree tree = new Tree(root.getRight());
        return tree.findNode(identification);
      } else {
        return null;
      }
    }
  }

  public TNode findNode (String partialId, int where)
  {
    if (partialId.length () == 26){
      return findNode (partialId);
    }
    else if (root == null) {
      return null;
    }
    else
    {
      int partialLen = partialId.length ();
      String nodePartialId = root.getID().substring (0, partialLen);

      if (partialId.compareTo (nodePartialId) < 0)
      {
        Tree lTree = new Tree (root.getLeft ());
        return lTree.findNode (partialId, where);
      }
      else if (partialId.compareTo (nodePartialId) > 0)
      {
        Tree rTree = new Tree (root.getRight ());
        return rTree.findNode (partialId, where);
      }
      else if (partialId.compareTo (nodePartialId) == 0)
      {
        TNode p = root;
        TNode q = p;
        if (where == 0)
        {
          do
          {
            if (p.getID ().substring (0, partialLen).equals (partialId))
            {
              q = p;
              p = p.getLeft ();

            }
            else
              p = p.getRight ();
          }
          while (p != null);
        }
        else
        {
          do {
            if (p.getID ().substring (0, partialLen).equals (partialId))
            {
              q = p;
              p = p.getRight ();
            }
            else
              p = p.getLeft ();
          }
          while (p != null);
        }
        return q;
      }
      else{
        System.out.println("Fatal error in findNode(String, int)");
        return null;
      }
    }
  }


//special method used below
  private static void setParentsChildLink(TNode p, TNode q) {
    if (p.getParent().getLeft() == p) {
      p.getParent().setLeft(q);
    } else {
      p.getParent().setRight(q);
    }
  }

//deleting nodes
  public void deleteNode(TNode p) {
    TNode q = null;
    TNode r = null;
    // case 1
    if (p.getLeft() == null && p.getRight() == null) {
      r = p.getParent();
      if (p == root) {
        root = null;
      } else {
        setParentsChildLink(p, null);
      }
      // case 2, 3, 5, 6
    } else if((p.getLeft() != null && p.getRight() == null) || (p.getLeft() == null && p.getRight() != null)) {
      if (p.getLeft() != null) q = p.getLeft();
      else q = p.getRight();
      if (p == root) {
        root = q;
      } else {
        setParentsChildLink(p, q);
      }
      q.setParent(p.getParent());
      r =  q.getParent();
      // case 4, 7
    } else if(p.getLeft() != null && p.getRight() != null) {
      q = p.getLeft();
      // case 4, 7a
      if (q.getRight() == null) {
        if (p == root) {
          root = q;
        } else {
          setParentsChildLink(p, q);
        }
        q.setParent(p.getParent());
        q.setRight(p.getRight());
        q.getRight().setParent(q);
        r = q;
        // case 7b, c
      } else {
        while(q.getRight() != null) {
          q = q.getRight();
        }
        r =  q.getParent();
        setParentsChildLink(q, q.getLeft());
        if (q.getLeft() != null) q.getLeft().setParent(q.getParent());
        if (p == root) {
          root = q;
        } else {
          setParentsChildLink(p, q);
        }
        q.setParent(p.getParent());
        q.setLeft(p.getLeft());
        q.setRight(p.getRight());
        q.getLeft().setParent(q);
        q.getRight().setParent(q);

      }
    }
    balance(r);
    p.setLeft(null);
    p.setRight(null);
    p.setParent(null);
    p = null;
  }
  //balancing trees****************************************************************************************
  // right rotation

  private TNode rightRotate() {
    //this.root is the same thing as this.getRoot()
    TNode p = this.root.getLeft();
    p.setParent(this.root.getParent());
    this.root.setLeft(p.getRight());
    if (this.getRoot().getLeft() != null) {
      this.getRoot().getLeft().setParent(this.getRoot());
    }
    p.setRight(this.getRoot());
    p.getRight().setParent(p);

    return p;

  }


  //left rotation
  private TNode leftRotate() {
    TNode p = this.getRoot().getRight();
    p.setParent(this.getRoot().getParent());
    this.getRoot().setRight(p.getLeft());
    if (this.getRoot().getRight() != null) {
      this.getRoot().getRight().setParent(this.getRoot());
    }
    p.setLeft(this.getRoot());
    p.getLeft().setParent(p);

    return p;
  }

  //balance factor
  private int balanceFactor() {
    Tree lTree = new Tree(this.root.getLeft());
    Tree rTree = new Tree(this.root.getRight());
    return lTree.height() - rTree.height();
  }

  //balance tree
  private void balance (TNode p) {
    TNode ancestor = p;
    while (ancestor != null) {
      Tree ancestorTree = new Tree(ancestor);
      if (ancestorTree.balanceFactor() == -2) {
        Tree rTree = new Tree(ancestor.getRight());
        //CASE ONE
        if (rTree.balanceFactor() == -1 || rTree.balanceFactor() == 0) {
          if (ancestor == root) {
            root = ancestorTree.leftRotate();
          } else {
            if (ancestor.getParent().getLeft() == ancestor) {
              ancestor.getParent().setLeft(ancestorTree.leftRotate());
            } else {
              ancestor.getParent().setRight(ancestorTree.leftRotate());
            }

          }

          //CASE TWO
        } else if (rTree.balanceFactor() == 1 || rTree.balanceFactor() == 0) {
          ancestor.setRight(rTree.rightRotate());
          if (ancestor == root) {
            root = ancestorTree.leftRotate();
          } else {
            if (ancestor.getParent().getLeft() == ancestor) {
              ancestor.getParent().setLeft(ancestorTree.leftRotate());
            } else {
              ancestor.getParent().setRight(ancestorTree.leftRotate());
            }
          }



        }

      } else if (ancestorTree.balanceFactor() == 2) {
        Tree lTree = new Tree (ancestor.getLeft());
        //CASE THREE
        if (lTree.balanceFactor() == 1 || lTree.balanceFactor() == 0) {
          if (ancestor == root) {
            root = ancestorTree.rightRotate();
          } else {
            if (ancestor.getParent().getLeft() == ancestor) {
              ancestor.getParent().setLeft(ancestorTree.rightRotate());
            } else {
              ancestor.getParent().setRight(ancestorTree.rightRotate());
            }
          }
          //CASE FOUR
        } else if (lTree.balanceFactor() == -1 || lTree.balanceFactor() == 0) {
          ancestor.setLeft(lTree.leftRotate());
          if (ancestor == root) {
            root = ancestorTree.rightRotate();
          } else {
            if (ancestor.getParent().getLeft() == ancestor) {
              ancestor.getParent().setLeft(ancestorTree.rightRotate());
            } else {
              ancestor.getParent().setRight(ancestorTree.rightRotate());
            }
          }

        }

      } // end of first initial if statement
      ancestor = ancestor.getParent();
    } // end of while loop

  } // end of method


  private void printNodesOfOneLevel(TNode p, int level, int currentLevel) {
    if (p != null) {
      if (currentLevel == level) {
        System.out.print(p.getID() + " in level " + level);

        if (p.getParent() == null) {
          System.out.println(" Root");
        }
        else if (p.getParent().getLeft() == p) {
          System.out.println(" Left child of " + p.getParent().getID());
        }
        else {
          System.out.println(" Right child of " + p.getParent().getID());
        }
      }
      else {
        printNodesOfOneLevel(p.getLeft(), level, currentLevel + 1);
        printNodesOfOneLevel(p.getRight(), level, currentLevel + 1);
      }
    }
  }

  public void breadthFirstPrint() {
    int treeHeight = height();
    for (int level = 0; level <= treeHeight; level++) {
      printNodesOfOneLevel(root, level, 0);
    }
  }

  //printing a tree from the start node to the end node without going through the entire tree
  public void printTree(TNode start, TNode end)
  {
    if (start != null && end != null) {
      if (start.getID().compareTo(end.getID()) <= 0) {
        if (start.getID().compareTo(root.getID()) <= 0 && root.getID().compareTo(end.getID()) <= 0) {
          TNode p = start;
          while (p != root)
          {

            System.out.println(p);
            Tree rTree = new Tree(p.getRight());
            rTree.printTree();
            if (p != root || p.getID().compareTo(start.getID()) < 0)
            {
              p = p.getParent(); //move up through parents' links until either p is root or p.id >= start.id
            }
          }

          System.out.println(root);
          p = root.getRight();

          while (p != null)
          {
            if (p.getID().compareTo(end.getID()) <= 0)
            {
              Tree lTree = new Tree(p.getLeft());
              lTree.printTree();
              System.out.println(p);
              p = p.getRight();
            }
            else {
              p = p.getLeft();
            }
          }
        }

        else if (end.getID().compareTo(root.getID()) == -1) {
          Tree lTree = new Tree(root.getLeft());
          lTree.printTree(start, end);
        }
        else if (start.getID().compareTo(root.getID()) == 1)
        {
          Tree rTree = new Tree(root.getRight());
          rTree.printTree(start, end);
        }
      }
    }
  }

  public void buildFromMessagesFile(int whatID) {
    int recordNumber = 0;
    Record record = new Record();
    for (recordNumber = 0; recordNumber < Globals.totalRecordsInMessageFile; recordNumber++) {
      record.readFromMessagesFile(recordNumber);
      if (record.getData().charAt(Globals.MARKER_POS) == Globals.FIRST_RECORD_OF_MESSAGE) {
        Message message = new Message();
        message.readFromMessagesFile(recordNumber);
        String key = "";
        if (whatID == Globals.SENDER_ID) {
          key = message.getId();
        } else if (whatID == Globals.RECEIVER_ID) {
          key = message.getReceiver() + message.getSender() + message.getDateTime();
        } else {
          System.out.println("Error in build inbox");
        }
        TNode p = new TNode(key, recordNumber, null, null, null);
        insertNode(p);
      }
    }

  }

  //breadth saving
    public void breadthFirstSave(String fileName){
    try
    {
      RandomAccessFile messageTree = new RandomAccessFile (fileName, "rw");

      for(int i = 0; i < height(); i ++)
        writeLevel(i,messageTree);

      messageTree.close();
    }
    catch (IOException e)
    {
      System.out.println (e);
    }
  }

   public void writeLevel(int level, RandomAccessFile f){
    if(level == 0){
      try
      {
        if(root != null){
          f.write(root.getID().getBytes());
          f.writeInt(root.getRecordNumber());
        }
      }
      catch (IOException e)
      {
        System.out.println (e);
      }
    }else if(root!=null){
      Tree t = new Tree(root.getLeft());
      t.writeLevel(level-1,f);
      t = new Tree(root.getRight());
      t.writeLevel(level-1,f);
    }

  }


  public int breadthFirstRetrieve(String fileName) {

    String ID = "";
    int recordNumber = 0;
    TNode p = null;
    byte[] b = new byte[Globals.IDENTIFICATION_LEN];
    try {
      RandomAccessFile messageTree = new RandomAccessFile (fileName, "rw");
      long fileSize = messageTree.length();
      for (int i = 0; i < fileSize ; i+= Globals.NODE_LEN) {
        messageTree.read(b);
        ID = new String(b);
        recordNumber = messageTree.readInt();
       // System.out.println(ID + " " + recordNumber);
        p = new TNode (ID, recordNumber, null, null, null);
       insertNode(p);
      }
      messageTree.close();
      return Globals.PROCESS_OK;
    }
    catch (IOException e) {
      System.out.println ("Error in breadthFirstRetrieve method of Tree class");
      return Globals.PROCESS_ERR;
    }
  }



  //This method creates a single string of all messages that the client is requesting
  public void  prepareTransmissionString(TNode start, TNode end) {
    Tree rTree = new Tree();
    Tree lTree = new Tree();
    if (start!= null && end!=null){ //If start and finish aren't null
      if (start.getID().compareTo(end.getID())<=0) { //If start's id is smaller than or equal to end's
        if ((start.getID().compareTo(root.getID()) <=0) && (root.getID().compareTo(end.getID()) <=0)) { //If start and end are on opposite sides of the root or the root
          TNode p = start;
          while (p != null && p != root) {
            NetIO.addToTransmissionString(p.getRecordNumber());

            rTree = new Tree(p.getRight());
            rTree.prepareTransmissionString();

            do {
              p = p.getParent();
            } while (p != null && p.getID().compareTo(start.getID()) < 0);  //move up to ancestor that is within range
          }
          NetIO.addToTransmissionString(root.getRecordNumber());
          p = root.getRight();

          while (p != null) {
            if ((p.getID().compareTo(end.getID()))<=0){ //While p is smaller than or equal to the end node it's
              //left branch will be printed then it will be moved to the right

              lTree = new Tree (p.getLeft());
              lTree.prepareTransmissionString();

              NetIO.addToTransmissionString(p.getRecordNumber());
              p = p.getRight();
            }
            else {
              p = p.getLeft();
            }
          }
        }
        else if (end.getID().compareTo (root.getID()) < 0) {//If the end is on the left of the root then the left of the root branch will be printed
          //recursively until the root of the recursively called tree's id is less than the end node
          lTree = new Tree (root.getLeft());
          lTree.prepareTransmissionString(start, end);
        }
        else if  (start.getID().compareTo (root.getID()) > 0){ //If the end is on the right of the root then the right branch of the root will be printed
          //recursively until the root of the recursively called tree's id is less than the end node
          rTree = new Tree (root.getRight());
          rTree.prepareTransmissionString(start, end);
        }
      }
    }
  }


 public void prepareTransmissionString()
  {
    if (root == null)
    {

    }

    else
    {
      Tree tree = new Tree(root.getLeft());
      tree.prepareTransmissionString();
      NetIO.addToTransmissionString(root.getRecordNumber());
      tree = new Tree(root.getRight());
      tree.prepareTransmissionString();
    }
  }




  public String toString() {
    if (root == null) {
      return null;
    } else {
      return "" + root.getID();
    }
  }



}
