package es.uned.lsi.eped.pract2017_2018;

import es.uned.lsi.eped.DataStructures.GTreeIF;
import es.uned.lsi.eped.DataStructures.ListIF;

/**
 *
 * @author Miquel Ginés Borràs
 */
public class QueryDepotTree implements QueryDepotIF {

    private GTreeIF<Query> queryTree;
    
    public QueryDepotTree() {
        
    }
    
    @Override
    public int numQueries() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
