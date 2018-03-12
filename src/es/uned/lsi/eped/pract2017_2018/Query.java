package es.uned.lsi.eped.pract2017_2018;

/**
 *
 * @author Miquel Ginés Borràs
 */
public class Query {
    
    private String text;
    private int freq;
    
    /**
     * Constructor vacío.
     */
    public Query() {
        
    }

    public Query(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getFreq() {
        return freq;
    }

    @Override
    public String toString() {
        return "Consulta '" + text + "' - Frecuencia " + freq;
    }
    
}
