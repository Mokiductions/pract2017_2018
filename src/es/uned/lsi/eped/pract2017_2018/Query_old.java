package es.uned.lsi.eped.pract2017_2018;

/**
 *
 * @author Miquel Ginés Borràs
 */
public class Query_old {
    
    private String text;
    private int freq;
    
    /**
     * Constructor vacío.
     */
    public Query_old() {
        
    }

    public Query_old(String text) {
        this.text = text;
        this.freq = 1;
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

    public void setFreq(int freq) {
        this.freq = freq;
    }

    @Override
    public String toString() {
        return "Consulta '" + text + "' - Frecuencia " + freq;
    }
    
}
