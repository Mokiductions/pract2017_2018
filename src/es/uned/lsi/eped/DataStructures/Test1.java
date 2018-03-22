package es.uned.lsi.eped.DataStructures;

public class Test1 {

    static boolean a = true;

    public static void main(String[] args) throws Exception {
        
        /*Test operaciones sobre �rboles generales*/
		System.out.println("Test de operaciones sobre árboles generales");
		GTreeIF<Integer> gtree = new GTree<Integer>();
		gtree.setRoot(1);
		//hijo 1
		GTreeIF<Integer> gtree1 = new GTree<Integer>();
		gtree1.setRoot(2);
		GTreeIF<Integer> gtree1tree1 = new GTree<Integer>();
		gtree1tree1.setRoot(5);
		//obtener posici�n donde ubicar un nuevo hijo: n�mero de hijos + 1
		int sizeTree1 = gtree1.getChildren().size()+1;
		gtree1.addChild(sizeTree1, gtree1tree1);
		GTreeIF<Integer> gtree1tree2 = new GTree<Integer>();
		gtree1tree2.setRoot(6);
		sizeTree1 = gtree1.getChildren().size()+1;
		gtree1.addChild(sizeTree1, gtree1tree2);
		int sizeTree = gtree.getChildren().size()+1;
		gtree.addChild(sizeTree,gtree1);
		//hijo 2
		GTreeIF<Integer> gtree2 = new GTree<Integer>();
		gtree2.setRoot(3);
		sizeTree = gtree.getChildren().size()+1;
		gtree.addChild(sizeTree,gtree2);
		//hijo 3
		GTreeIF<Integer> gtree3 = new GTree<Integer>();
		gtree3.setRoot(4);
		GTreeIF<Integer> gtree3tree1 = new GTree<Integer>();
		gtree3tree1.setRoot(7);
		int sizeTree3 = gtree3.getChildren().size()+1;
		gtree3.addChild(sizeTree3,gtree3tree1);
		sizeTree = gtree.getChildren().size()+1;
		gtree.addChild(sizeTree,gtree3);
		
		//operaciones sobre �rboles n-�rios
		System.out.println("Numero de nodos: "+gtree.size());
		System.out.println("contains(3): "+gtree.contains(3));
		System.out.println("contains(9): "+gtree.contains(9));
		System.out.println("isEmpty?: "+gtree.isEmpty());
		System.out.println("isLeaf(1)?: "+gtree.isLeaf());
		System.out.println("isLeaf(3)?: "+gtree2.isLeaf());
		
		//recorridos
		
		//recorrido en preorden
		System.out.print("Preorden: ");
		IteratorIF<Integer> iterPreorderGTree = gtree.iterator(GTree.IteratorModes.PREORDER);
		while(iterPreorderGTree.hasNext()){
			System.out.print(iterPreorderGTree.getNext()+" ");
		}
		System.out.println();
		
		//recorrido en postorden
		System.out.print("Postorden: ");
		IteratorIF<Integer> iterPostorderGTree = gtree.iterator(GTree.IteratorModes.POSTORDER);
		while(iterPostorderGTree.hasNext()){
			System.out.print(iterPostorderGTree.getNext()+" ");
		}
		System.out.println();
		
		//recorrido en profundidad
		System.out.print("Breadth: ");
		IteratorIF<Integer> iterBreadthGTree = gtree.iterator(GTree.IteratorModes.BREADTH);
		while(iterBreadthGTree.hasNext()){
			System.out.print(iterBreadthGTree.getNext()+" ");
		}
		System.out.println();
    }

    

}
