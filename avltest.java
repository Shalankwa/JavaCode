
public class avltest {

	public static void main(String[] args) {
		AVLTree tree = new AVLTree();
		
		tree.insert(12);
		tree.insert(4);
		tree.insert(9);
		tree.insert(44);
		tree.insert(22);
		tree.insert(1);
		tree.insert(6);
		tree.insert(5);
		tree.insert(15);
		
		tree.print();

	}

}
