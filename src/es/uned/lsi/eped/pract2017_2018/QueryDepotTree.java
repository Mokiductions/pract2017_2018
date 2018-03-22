package es.uned.lsi.eped.pract2017_2018;

import es.uned.lsi.eped.DataStructures.GTree;
import es.uned.lsi.eped.DataStructures.GTreeIF;
import es.uned.lsi.eped.DataStructures.IteratorIF;
import es.uned.lsi.eped.DataStructures.ListIF;

/**
 *
 * @author Miquel Ginés Borràs
 */
public class QueryDepotTree implements QueryDepotIF {

    private GTreeIF<Character> queryDepot;

    public QueryDepotTree() {
        queryDepot = new GTree<Character>();
                queryDepot.setRoot('.');
    }

    @Override
    public int numQueries() {
        int i = 0;
        //System.out.println("Size: " + queryDepot.size());
        IteratorIF<Character> it = queryDepot.iterator(GTreeIF.IteratorModes.PREORDER);
        while (it.hasNext()) {
            //System.out.println("" + it.getNext());
        }
        return i;
    }

    @Override
    public int getFreqQuery(String q) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ListIF<Query> listOfQueries(String prefix) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void incFreqQuery(String q) {
        // Llama al metodo incFreq(String q, GTreeIF queries) que realizara una 
        // insercion recursiva
        System.out.println("\n-------------------------------\nEmpezando a cargar " + q + "\n");
        incFreq(q, queryDepot);
    }

    private void incFreq(String q, GTreeIF<Character> tree) {
        // Si q esta vacia, significa que hay que añadir/acumular frecuencia
        // hay que coger el hijo de la posicion 1 (que deberia ser el de la
        // frec
        if (q.isEmpty()) {
            System.out.println("Aumentando frecuencia");
            /*ListIF children = tree.getChildren();
            GTreeIF<Integer> freqLeaf = getFreqLeaf(children);
            if (freqLeaf == null) { // No tiene frecuencia, poner frecuencia 1
                freqLeaf = new GTree();
                freqLeaf.setRoot(1);
                tree.addChild(tree.getNumChildren() + 1, freqLeaf);
                System.out.println("Freq nueva: " + freqLeaf.getRoot());
            } else { // Ya tenia frecuencia, aumentar en 1.
                freqLeaf.setRoot(freqLeaf.getRoot() + 1);
                System.out.println("Freq exist: " + freqLeaf.getRoot());
            }*/
        } else {
            // Queda palabra por añadir
            System.out.println(tree.getRoot());
            ListIF children = tree.getChildren();
            GTreeIF<Character> charNode = getCharNode(children, q.charAt(0));
            System.out.println("Añadiendo " + q.charAt(0) + " al árbol");
            if (charNode == null) {
                //tree.addChild(tree.getChildren().size() + 1, createSubTree(q));
                charNode = new GTree<Character>();
                charNode.setRoot(q.charAt(0));
                //GTreeIF newTree = createSubTree(q);
                //charNode.addChild(1, createSubTree(q));
                tree.addChild(1, charNode);
                incFreq(q.substring(1), charNode);
            } else {
                incFreq(q.substring(1), charNode);
            }
        }
    }

    private GTreeIF<Integer> getFreqLeaf(ListIF<GTreeIF> children) {
        if (children.isEmpty() || children == null) {
            return null;
        } else {
            IteratorIF<GTreeIF> it = children.iterator();
            while (it.hasNext()) {
                GTreeIF child = it.getNext();
                if (child.isLeaf()) {
                    return child;
                }
            }
        }
        return null;
    }

    private GTreeIF<Character> getCharNode(ListIF<GTreeIF> children, char c) {
        if (children.isEmpty() || children == null) {
            return null;
        } else {
            IteratorIF<GTreeIF> it = children.iterator();
            while (it.hasNext()) {
                GTreeIF<Character> child = it.getNext();
                if (!child.isLeaf() && child.getRoot() == c) {
                    return child;
                }
            }
        }
        return null;
    }

    private GTreeIF<Character> createSubTree(String q) {
        if (q.isEmpty()) {
            GTreeIF<Character> freqLeaf = new GTree();
            freqLeaf = new GTree();
            freqLeaf.setRoot('1');
            return freqLeaf;
        } else {
            GTreeIF<Character> charNode = new GTree();
            charNode.setRoot(q.charAt(0));
            charNode.addChild(1, createSubTree(q.substring(1)));
            return charNode;
        }
    }
}
