
public class test_23tree {

	public static void main(String[] args) {
		tree_23 two3 = new tree_23();
		
		try {
			two3.insert(20);
			two3.insert(1);
			two3.insert(5);
			two3.insert(2);
			two3.insert(14);
			two3.insert(15);
			two3.insert(8);
			two3.insert(19);
			two3.insert(17);
			two3.insert(13);
			two3.insert(9);
			two3.insert(18);
			two3.insert(25);
			two3.insert(22);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		two3.inOrder();
		System.out.println();
		two3.preOrder();
		
		System.out.println(two3.find(19));
		System.out.println(two3.find(77));
		System.out.println(two3.height());
	}

}
