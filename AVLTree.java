
public class AVLTree {
	
	Node root;
	
	AVLTree(){
		
	}
	
	
	public void insert(int x){
		root = insert(root, x);
	}
	
	private Node insert(Node r, int x){
		
		if(r == null)
			return new Node(x);
		if(x > r.item)
			r.right = insert(r.right, x);
		if(x < r.item)
			r.left = insert(r.left, x);
		
		//calc new balance
		r.height = 1 + Math.max((r.left == null)? 0 : r.left.height, 
							(r.right == null)? 0 : r.right.height);
			
		if(Math.abs(getBalance(r)) > 1){
			
			//Right side is unbalanced
			if(getBalance(r) < 0){
				r = rotateL(r);
				if(Math.abs(getBalance(r)) > 1){
					r = rotateR(r);
					r.right = rotateR(r.right);
					r = rotateL(r);
				}
			}
			
			//Left side is unbalanced
			if(getBalance(r) > 0){
				r = rotateR(r);
				if(Math.abs(getBalance(r)) > 1){
					r = rotateL(r);
					r.left = rotateL(r.left);
					r = rotateR(r);
				}
			}
		}
		
		return r;
	}
	
	private int calcHeight(Node r){
		return 1 + Math.max((r.left == null)? 0 : r.left.height, 
				(r.right == null)? 0 : r.right.height);
	}
	
	private Node rotateR(Node n){

		Node oldl = n.left;
		n.left = oldl.right;
		oldl.right = n;
		n.height = calcHeight(n);
		oldl.height = calcHeight(n);
		
		return oldl;
	}
	
	private Node rotateL(Node n){

		Node oldR = n.right;
		n.right = oldR.left;
		oldR.left = n;
		n.height = calcHeight(n);
		oldR.height = calcHeight(n);
		
		return oldR;
	}
	
	private int getBalance(Node r) {
		return ((r.left == null)? 0 : r.left.height) - 
				((r.right == null)? 0 : r.right.height);
	}
	
	public void print(){
		System.out.print("[");
		print(root);
		System.out.println("]");
	}
	
	public void print(Node r){
		if(r == null) return;
		print(r.left);
		System.out.print(r.item + ", ");
		print(r.right);
	}
	
	public void printPre(){
		System.out.print("[");
		printPre(root);
		System.out.println("]");
	}
	
	public void printPre(Node r){
		if(r == null) return;
		System.out.print(r.item + ", ");
		printPre(r.left);
		printPre(r.right);
	}
	class Node {
		
		int item;
		int height = 1;
		Node left;
		Node right;
		
		Node(int i){
			item = i;
		}
		
	}
}
