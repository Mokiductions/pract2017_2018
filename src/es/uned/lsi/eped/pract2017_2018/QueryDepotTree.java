package es.uned.lsi.eped.pract2017_2018;

import es.uned.lsi.eped.DataStructures.GTree;
import es.uned.lsi.eped.DataStructures.GTreeIF;
import es.uned.lsi.eped.DataStructures.IteratorIF;
import es.uned.lsi.eped.DataStructures.List;
import es.uned.lsi.eped.DataStructures.ListIF;

/**
 *
 * @author Miquel Ginés Borràs
 */
public class QueryDepotTree implements QueryDepotIF {

    private GTreeIF<TreeNode> queryDepot; // Arbol principal para almacenar las queries

    /**
     * Constructor del arbol de consultas
     */
    public QueryDepotTree() {
        queryDepot = new GTree();
        queryDepot.setRoot(new TreeNode());
    }

    /**
     * Obtiene el numero de consultas almacenadas en el arbol
     * @return el numero de queries
     */
    @Override
    public int numQueries() {
        int i = 0;
        IteratorIF<TreeNode> it = queryDepot.iterator(GTreeIF.IteratorModes.PREORDER);
        while (it.hasNext()) {
            TreeNode tn = it.getNext();
            if (tn.isLeaf()) {
                i++;
            }
        }
        //printTree(queryDepot, "");
        return i;
    }

    /**
     * Metodo usado solo para pruebas, hay que eliminarlo
     * @param tree el arbol que va a recorrer
     * @param s cadena formandose de la palabra
     */
    private void printTree(GTreeIF<TreeNode> tree, String s) {
        if (!tree.isEmpty()) {
            s += tree.getRoot().getCharValue();
            ListIF<GTreeIF<TreeNode>> children = tree.getChildren();
            if (children.isEmpty()) {
                s += "' - Frecuencia " + tree.getRoot().getValue();
                System.out.println("Consulta '" + s);
            } else {
                IteratorIF<GTreeIF<TreeNode>> it = children.iterator();
                while (it.hasNext()) {
                    GTreeIF<TreeNode> child = it.getNext();
                    printTree(child, s);
                }
            }
        }
    }

    /**
     * Devuelve la frecuencia de una query en el arbol de queries
     * @param q el texto de la query
     * @return la frecuencia de la query q
     */
    @Override
    public int getFreqQuery(String q) {
        return getFreq(q, queryDepot);
    }

    /**
     * Metodo recursivo usado para calcular la frecuencia de una query en el arbol
     * @param q el texto a buscar dentro del subarbol
     * @param tree el subarbol en el que buscar el texto
     * @return la frecuencia de la query
     */
    private int getFreq(String q, GTreeIF<TreeNode> tree) {
        if (q.isEmpty()) {
            GTreeIF<TreeNode> freqLeaf = getFreqLeaf(tree.getChildren());
            if (freqLeaf.getRoot().isLeaf()) {
                return freqLeaf.getRoot().getValue();
            } else {
                return 0;
            }
        } else if (tree.getChildren().isEmpty()) {
            return 0;
        } else {
            GTreeIF<TreeNode> charNode = getCharNode(tree.getChildren(), q.charAt(0));
            if (charNode == null) {
                return 0;
            } else {
                return getFreq(q.substring(1), charNode);
            }
        }
    }

    /**
     * Metodo que devuelve una lista de sugerencias para una cadena de inicio de query
     * @param prefix la cadena a buscar como prefijo
     * @return una lista de consultas con las sugerencias para la cadena prefix
     */
    @Override
    public ListIF<Query> listOfQueries(String prefix) {
        GTreeIF<TreeNode> prefixSubtree = getPrefixSubtree(prefix, queryDepot);
        QueryComparator qc = new QueryComparator();
        return qc.sort(fillQueryList(prefix, prefixSubtree, "", new List<Query>()));
    }

    /**
     * Obtiene recursivamente todo el subarbol que contiene las sugerencias 
     * para una cadena de prefijo especificada
     * @param prefix cadena usada como prefijo
     * @param tree arbol en el que buscar el prefijo
     * @return subarbol de sugerencias para el prefijo
     */
    private GTreeIF<TreeNode> getPrefixSubtree(String prefix, GTreeIF<TreeNode> tree) {
        if (tree == null || prefix.isEmpty()) {
            return tree;
        }
        return getPrefixSubtree(prefix.substring(1), getCharNode(tree.getChildren(), prefix.charAt(0)));
    }

    /**
     * Obtiene de forma recursiva la lista de queries que son sugerencias para 
     * una cadena especificada como prefijo, las obtiene a partir del subarbol
     * calculado en getPrefixSubtree()
     * @param prefix cadena de prefijo
     * @param tree arbol en el que buscar las queries
     * @param s cadena de sufijo para la query en curso
     * @param queryList lista de queries a la que añadir la query formada
     * @return la lista de queries que son sugerencias para el prefijo 
     */
    private ListIF<Query> fillQueryList(String prefix, GTreeIF<TreeNode> tree, String s, ListIF<Query> queryList) {
        ListIF<GTreeIF<TreeNode>> children = tree.getChildren();
        GTreeIF<TreeNode> leaf = getFreqLeaf(children);
        if (leaf != null) {
            Query q = new Query(prefix + s);
            q.setFreq(leaf.getRoot().getValue());
            queryList.insert(q, queryList.size() + 1);
        }
        if (children.isEmpty()) {
            return queryList;
        }

        IteratorIF<GTreeIF<TreeNode>> it = children.iterator();
        while (it.hasNext()) {
            GTreeIF<TreeNode> node = it.getNext();
            if (!node.getRoot().isLeaf()) {
                queryList = fillQueryList(prefix, node, s + node.getRoot().getCharValue(), queryList);
            }
        }

        return queryList;
    }

    /**
     * Incrementa la frecuencia de una query en 1, si dicha query no existe la 
     * creara con frecuencia 1
     * @param q el texto de la query
     */
    @Override
    public void incFreqQuery(String q) {
        incFreq(q, queryDepot);
    }

    /**
     * 
     * @param q
     * @param tree 
     */
    private void incFreq(String q, GTreeIF<TreeNode> tree) {
        // Si q esta vacia, significa que hay que añadir/acumular frecuencia
        // hay que coger el hijo de la posicion 1 (que deberia ser el de la
        // frec
        if (q.isEmpty()) {
            GTreeIF<TreeNode> freqLeaf = getFreqLeaf(tree.getChildren());
            if (freqLeaf == null) { // No tiene frecuencia, poner frecuencia 1
                freqLeaf = new GTree();
                freqLeaf.setRoot(new TreeNode(1));
                tree.addChild(tree.getNumChildren() + 1, freqLeaf);
            } else { // Ya tenia frecuencia, aumentar en 1.
                freqLeaf.setRoot(new TreeNode(freqLeaf.getRoot().getValue() + 1));
            }
        } else {
            // Queda palabra por añadir
            ListIF<GTreeIF<TreeNode>> children = tree.getChildren();
            GTreeIF<TreeNode> charNode = getCharNode(children, q.charAt(0));
            if (charNode == null) {
                tree.addChild(children.size() + 1, createSubTree(q));
            } else {
                incFreq(q.substring(1), charNode);
            }
        }
    }

    /**
     * Este metodo no sirve, era una prueba de insertarordenadamente, no creo 
     * que sea rentable en arbol
     * @param children
     * @param c
     * @return 
     */
    private int getNewChildPos(ListIF<GTreeIF<TreeNode>> children, char c) {
        IteratorIF<GTreeIF<TreeNode>> it = children.iterator();
        int i = 1;
        while (it.hasNext()) {
            if (it.getNext().getRoot().compareTo(new TreeNode(c)) > -1) {
                return i - 1;
            }
            i++;
        }
        return i;
    }

    /**
     * Devuelve el nodo de la frecuencia, que es el unico nodo hoja posible, 
     * para una lista de nodos. Esta sera la frecuencia de la palabra formada
     * por todos sus padres
     * @param children lista de nodos a buscar
     * @return el nodo frecuencia de la palabra formada por sus padres
     */
    private GTreeIF<TreeNode> getFreqLeaf(ListIF<GTreeIF<TreeNode>> children) {
        if (children.isEmpty() || children == null) {
            return null;
        } else {
            IteratorIF<GTreeIF<TreeNode>> it = children.iterator();
            while (it.hasNext()) {
                GTreeIF<TreeNode> child = it.getNext();
                if (child.isLeaf()) {
                    return child;
                }
            }
        }
        return null;
    }

    /**
     * Obtiene, si existe, el subarbol que cuelga del nodo que contiene el 
     * caracter recibido por parametro
     * @param children lista de nodos a revisar
     * @param c caracter a buscar en los nodos
     * @return el subarbol que contiene ese caracter, null si no existe
     */
    private GTreeIF<TreeNode> getCharNode(ListIF<GTreeIF<TreeNode>> children, char c) {
        if (children.isEmpty() || children == null) {
            return null;
        } else {
            IteratorIF<GTreeIF<TreeNode>> it = children.iterator();
            while (it.hasNext()) {
                GTreeIF<TreeNode> child = it.getNext();
                if (!child.isLeaf() && child.getRoot().compareTo(new TreeNode(c)) == 0) {
                    return child;
                }
            }
        }
        return null;
    }

    /**
     * Crea un subarbol para una query, usado cuando todo son letras nuevas 
     * en una parte ya existente del arbol padre
     * @param q texto de la query
     * @return el subarbol creado para la query
     */
    private GTreeIF<TreeNode> createSubTree(String q) {
        if (q.isEmpty()) {
            GTreeIF<TreeNode> freqLeaf = new GTree();
            freqLeaf = new GTree();
            freqLeaf.setRoot(new TreeNode(1));
            return freqLeaf;
        } else {
            GTreeIF<TreeNode> charNode = new GTree();
            charNode.setRoot(new TreeNode(q.charAt(0)));
            charNode.addChild(1, createSubTree(q.substring(1)));
            return charNode;
        }
    }
}
