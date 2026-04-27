public class Node{
	//cvars
	private Node left, right;
	private String str;
	private char c; //j in case?
	
	public Node(){		//root?
		left = right = null;		
	}
	public Node(String s){	//leaves...
		left = right = null;
		str = s;
	}

	public Node getL(){
		return left;
	}
	public Node getR(){
		return right;
	}
	public String getS(){
		return str;
	}

	public void setL(Node sl){
		left = sl;
	}
	public void setR(Node sr){
		right = sr;
	}

	public void setK(String ss){
		str = ss;
	}
}
