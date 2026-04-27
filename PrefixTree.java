public class PrefixTree {
	// c vars
	int size;
	Node root;

	//constr
	public PrefixTree(){
		size = 1;
		root = new Node("*"); // ?
	}

	public boolean isEmpty(){
		return (size == 0 || root == null);
	}
	public int size(){
		return size;
	}

	//public Node get(String str){
	////	if (this.isEmpty()) return null;
	//	return	this.get(str, root);
	//}
	// public Node get(String str, Node n){
	// 	// if (n == null) return null;
	// 	// if (n.getS().compareTo(str) > 0 ) return get(key, n.getL()); //compareTo go to left
	// 	// else if (n.getK().compareTo(key) < 0 ) return get(key, n.getR()); //compareTo?
	// 	// else return n;
	// }

	public void printTree(Node n){
		if (n == null) return;
		if (n.getL() != null){
			printTree(n.getL());
		}
		System.out.print( " str: " + n.getS());
		if (n.getR() != null){
			printTree(n.getR());
		}
	}

	public static void main(){
		PrefixTree pt = new PrefixTree();
			System.out.println("pt empty?: " + pt.isEmpty());
		
	}

}
