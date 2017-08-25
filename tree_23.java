
public class tree_23 {

	private Node root;
	
	//Find if x is in the tree
	public boolean find(int x){
		return find(root, x);
	}
	private boolean find(Node r, int x){
		//End of line
		if(r == null){
			return false;
		}
		//check if found
		if(r.low == x){
			return true;
		}else if(r.high != null && x == r.high){
			return true;
		}else{
			if(x < r.low){ //Go left
				return find(r.left, x);
			}else if(r.mid != null && x < r.high){ // go mid 
				return find(r.mid, x);
			}else{ //go right
				return find(r.right, x);
			}
		}
	}
	//Height
	public int height(){
		return height(root);
	}
	private int height(Node r){
		if(r == null) return 0;
		return 1 + height(r.left);
	}
	//Insert
	public void insert(int x) throws Exception{
		insert(root, x);
	}
	private void insert(Node r, int x) throws Exception{
		if(find(x)){
			throw new Exception("dup");
		}
		
		//Null root
		if(root == null){
			root = new Node(x);
			return;
		}
		//Find leaf path
		if(r.left != null){
			if(x < r.low){ //Go left
				insert(r.left, x);
			}else if(r.mid != null && x < r.high){ // go mid 
				insert(r.mid, x);
			}else{ //go right
				insert(r.right, x);
			}
		}
		//At Leaf Node
		else if(r.high == null){ //When no second value
			if(x > r.low){ // insert value is higher than low
				r.high = x;
			}else{ // insert value is lower than cur low
				r.high = r.low;
				r.low = x;
			}
		}else{ //third item
			if(x < r.low){
				r.third = r.low;
				r.low = x;
			}else if(x > r.high){
				r.third = r.high;
				r.high = x;
			}else{
				r.third = x;
			}
		}
		//When 3 items split
		if(r.third != null){
			split(r);
		}
		return;
	}
	//Splitting when 3 inner
	private void split(Node r) {
		Node p, n1, n2;
		n1 = new Node(r.low);
		n2 = new Node(r.high);
		
		//Setting children and parents of new nodes
		if(r.left != null){
			n1.left = r.left;
			n1.right = r.mid;
			n2.left = r.midR;
			n2.right = r.right;
			
			r.left.parent = n1;
			r.mid.parent = n1;
			r.midR.parent = n2;
			r.right.parent = n2;
		}
		
		//Split on root
		if(r.parent == null){
			p = new Node(r.third);
			p.left = n1;
			p.right = n2;
			
			root = p;
		}else{ //Split on inner or leaf
			p = r.parent;
			if(r.third < p.low){ //child is left
				if(p.high == null){ //if no high
					p.high = p.low;
					p.low = r.third;
					
					p.left = n1;
					p.mid = n2;
				}else{ // if high
					p.third = p.low;
					p.low = r.third;
					
					//space for new mid cause we came from the left
					p.midR = p.mid;
					
					p.left = n1;
					p.mid = n2;
				}
				
			}else if(p.high == null){ //child is right, p has no high, we came from the right, must have no mid
				p.high = r.third;	
				
				p.mid = n1;
				p.right = n2;
			}else if(r.third > p.high){ //child is right p has high
				p.third = p.high;
				p.high = r.third;
				
				p.midR = n1;
				p.right = n2;
			}else{ //child is mid, from mid
				p.third = r.third;
				
				p.mid = n1;
				p.midR = n2;
				//n1 is mid, n2 is midR
			}
		}
		n1.parent = p;
		n2.parent = p;
		return;
	}//end split
	
	//Print In-order
	public void inOrder(){
		inOrder(root);
	}
	private void inOrder(Node r){
		if(r == null ) return;
		inOrder(r.left);
		System.out.println("Node values:  First: " + r.low + ((r.high != null)? "  Second: " + r.high : " ") );
		if(r.mid != null) inOrder(r.mid);
		inOrder(r.right);
	}
	//print PreOrder
	public void preOrder(){
		preOrder(root);
	}
	private void preOrder(Node r){
		if(r == null ) return;
		System.out.println("Node values:  First: " + r.low + ((r.high != null)? "  Second: " + r.high : " ") );
		preOrder(r.left);
		if(r.mid != null) preOrder(r.mid);
		preOrder(r.right);
	}
	
	class Node{
		private Node left, mid, midR, right, parent;
		private Integer low, third, high;
		
		Node(int l){
			low = l;  
		}
	}
}
