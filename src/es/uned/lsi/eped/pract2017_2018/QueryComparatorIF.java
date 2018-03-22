package es.uned.lsi.eped.pract2017_2018;

/**
 *
 * @author Miquel Ginés Borràs
 */
public interface QueryComparatorIF {
    
    public final int LESS = -1, EQUAL = 0, GREATER = 1;
    
    public int compare(Query q1, Query q2);
    
}
