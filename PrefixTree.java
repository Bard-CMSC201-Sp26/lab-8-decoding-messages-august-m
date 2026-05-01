import java.util.HashMap;
import java.util.Scanner;

public class PrefixTree {

  private Node root;
  private int size;
  private HashMap<Character, String> encodings;
  private HashMap<String, Character> decodings;

  public PrefixTree() {
    root = new Node('*');
    //System.out.println("Adding root: " + root);
    size = 1;
    encodings = new HashMap<Character, String>();
    decodings = new HashMap<String, Character>();
  }

  public PrefixTree(String traversal) {
    this();
    this.put(traversal.substring(1));
  }

  public int getSize() {
    return this.size;
  }

  private void put(Node root, Node newNode) {
    if (root.isLeaf())
      return;
    if (root.hasLeftBranch() && root.getLeft().hasAvailability()) {
      //System.out.println(root + " has left branch <- ");
      put(root.getLeft(), newNode);
    }
    else if (root.hasLeftSlotAvailable()) {
      //System.out.println("\t\tAttaching: " + root + " <- " + newNode);
      root.setLeft(newNode);
      return;
    }
    else if (root.hasRightBranch() && root.getRight().hasAvailability()) {
      //System.out.println(root + " has right branch -> ");
      put(root.getRight(), newNode);
    }
    else if (root.hasRightSlotAvailable() ) {
      //System.out.println("\t\tAttaching: " + root + " -> " + newNode);
      root.setRight(newNode);
      return;
    }
    else
      System.out.println("\t\t\t\tI Don't know where to put: " + newNode);
  }
  
  private void put(String t) {
    for (char c : t.toCharArray() ) {
      Node newN = new Node(c);
      //System.out.println("\tCreating: " + newN + " (" + c + ")");
      this.put(root, newN);
      this.size++;
    }
    this.getEncodings();
  }

  public void print() {
    this.print(this.root);
  }

  public void print(Node root) {
    if (root == null)
      return;
    //System.out.println(root.getData());
    this.print(root.getLeft());
    this.print(root.getRight());
  }

  public void populateEncodings() {
    this.populateEncodings(this.root, "");
  }

  private void populateEncodings(Node n, String currentPath) {
    if (n.isLeaf()) {
      this.encodings.put(n.getData(), currentPath);
      this.decodings.put(currentPath, n.getData());
      return;
    }
    if ( n.hasLeft() )
      populateEncodings(n.getLeft(), currentPath + "0");
    if ( n.hasRight() )
      populateEncodings(n.getRight(), currentPath + "1");
  }

  public void getEncodings() {
    System.out.println("character\tbits\tencoding");
    if (this.encodings.isEmpty())
        this.populateEncodings();
    System.out.println("--------------------------------");
    for (char e : this.encodings.keySet() )
      System.out.println(e + "\t\t" + encodings.get(e).length() + "\t" + encodings.get(e));
    System.out.println();
  }
// Number of bits: 28
// Number of characters: 12
// Compression ratio: 29.166666666666668 %
  
  public void printEncoding(char e) {
    System.out.println(e + ": " + encodings.get(e));
  }

  //. decode(String) // no btally
  public void decode(String message) {
    this.decode(message, 0, 0);
  }

  public void decode(String message, int bTally, int cTally) {
    // System.out.println("Message: " + message + " - " + message.length());
    if (this.encodings.isEmpty())
        this.populateEncodings(); 
    if (message.length() == 0) {
      System.out.println("\n\nNumber of bits: " + bTally);
      System.out.println("\nNumber of characters: " + cTally);
      System.out.println("\nCompression ratio: " + bTally / (cTally * 8.0) + " %");
      return;
    }
    String subMessage = "";
    for (int i=1; i <= message.length(); i++) {
      subMessage = message.substring(0, i); 
      // System.out.println("Checking : " + subMessage);
      if (decodings.containsKey(subMessage)) { //idenitify char
        // System.out.println("Found : " + subMessage);
        System.out.print(decodings.get(subMessage));
        bTally += i; //. i+1?
        cTally += 1;
        break;
      }
    }
    int l = subMessage.length();
    String poop = message.substring(l);
    // System.out.println(l + " " + poop);
    this.decode(poop, bTally, cTally);
  }


  public static void main (String[] args) {
    Scanner in = new Scanner(System.in);

    String line = null;
    PrefixTree pt = null;
    while (in.hasNextLine()) {
      line = in.nextLine();
      if (line.startsWith("*"))
        pt = new PrefixTree(line);
      else
        pt.decode(line);
    }
    // PrefixTree pt = new PrefixTree("*a**!d");
    //System.out.println("Number of elements in Tree: " + pt.getSize());
    /*
     * PrefixTree pt = new PrefixTree("*a**!*dc*rb");
    pt.getEncodings();
    pt.decode("0111110010110101001111100100");
    */
  }
}
