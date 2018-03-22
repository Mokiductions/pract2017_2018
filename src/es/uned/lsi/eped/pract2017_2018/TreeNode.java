package es.uned.lsi.eped.pract2017_2018;

/**
 *
 * @author Miquel Ginés Borràs
 */
public class TreeNode implements Comparable<TreeNode> {
    
    private boolean leaf = false;
    private int value;
    
    public TreeNode(){};
    
    public TreeNode(char value) {
        this.value = (int) value;
    }
    
    public TreeNode(int value) {
        this.value = value;
        this.leaf = true;
    }

    public boolean isLeaf() {
        return leaf;
    }

    public void setLeaf(boolean leaf) {
        this.leaf = leaf;
    }

    public int getValue() {
        return value;
    }
    
    public char getCharValue() {
        return (char) value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public int compareTo(TreeNode o) {
        if (this.value < o.getValue()) {
            return -1;
        } else if (this.value == o.getValue()) {
            return 0;
        } else {
            return 1;
        }
    }
    
    
}
