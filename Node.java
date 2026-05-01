import java.util.logging.Logger;
public class Node {
  private static final Logger L = Logger.getLogger(Node.class.getName());

  Node left, right;
  char data;

  public Node() {
    this.left = this.right = null;
  }

  public Node(char c) {
    this();
    this.data = c;
  }
/*
  public void setData(char c) {
    this.data = c;
  }
*/
  public char getData() {
    return this.data;
  }

  public boolean isBranch() {
    return this.data == '*';
  }

  public boolean isLeaf() {
    return ! this.isBranch();
  }

  public boolean hasLeft() {
    return this.left != null;
  }

  public boolean hasRight() {
    return this.right != null;
  }
/*
  public boolean isLeafWithSlot() {
    return this.isLeaf() && this.hasSlotAvailable();
  }
*/
  public boolean hasLeftSlotAvailable() {
    return this.left == null;
  }

  public boolean hasRightSlotAvailable() {
    return this.right == null;
  }

  public boolean hasSlotAvailable() {
    return this.left == null || this.right == null;
  }

  public boolean isFullBranch() {
    return this.isBranch() && ( (this.left != null && this.left.isLeaf()) && (this.right != null && this.right.isLeaf() ) );
  }

  public boolean hasAvailability() {
    if (this.isLeaf())
      return false;
    if (this.isFullBranch())
      return false;
    return ( (! this.hasLeft() || this.left.hasAvailability() ) || ( ! this.hasRight() || this.right.hasAvailability() ) );
  }

  public boolean hasLeftBranch() {
    return this.hasLeft() && this.getLeft().getData() == '*';
  }

  public boolean hasRightBranch() {
    return this.hasRight() && this.getRight().getData() == '*';
  }
/*
  public boolean hasBranch() {
    return this.hasLeftBranch() || this.hasRightBranch();
  }
  */



  public void setLeft(Node n) {
    this.left = n;
  }

  public void setRight(Node n) {
    this.right = n;
  }

  public Node getLeft() {
    return this.left;
  }

  public Node getRight() {
    return this.right;
  }

}

