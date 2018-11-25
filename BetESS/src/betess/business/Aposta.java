package betess.business;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Manuel Sousa
 * @author Tiago Alves
 */
public class Aposta implements Serializable {
    
    private int idAposta;
    private Map<Integer, Evento> eventos;
    private double valor;

    public Aposta() {
        this.eventos = new HashMap<>();
    }

    public Aposta(int id) {
        this.idAposta = id;
        this.eventos = new HashMap<>();
    }
    
    public Aposta(Aposta a) {
        this.idAposta = a.getIdAposta();
        this.valor = a.getValor();
        this.eventos = a.getEventos();
    }
    
    public Aposta(int idAposta, Map<Integer, Evento> eventos, double valor) {
        this.idAposta = idAposta;
        this.setEventos(eventos);
        this.valor = valor;
    }

    /**
     * Getters e Setters
     * @return 
     */
    
    public int getIdAposta() {
        return this.idAposta;
    }

    public void setIdAposta(int idAposta) {
        this.idAposta = idAposta;
    }

    public Map<Integer, Evento> getEventos() {
        Map<Integer, Evento> evs = new HashMap<>();
        
        this.eventos.entrySet().forEach(m -> {
            eventos.put(m.getKey(), m.getValue());
        });
        
        return evs;
    }

    public void setEventos(Map<Integer, Evento> evs) {
        this.eventos.entrySet().forEach(m -> {
            this.eventos.put(m.getKey(), m.getValue());
        });
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    /**
     * Método toString().
     * Retorna uma representação textual do objeto.
     * @return 
     */
    @Override
    public String toString() {
        return "Aposta{" + "idAposta=" + this.idAposta + 
               ", eventos=" + this.eventos + ", valor=" + this.valor + '}';
    }
    
    // Adicionar evento a aposta
    public void addEventoToAposta(Evento e) {
        if (!this.eventos.containsKey(e.getIdEvento()))
            this.eventos.put(e.getIdEvento(), e);
    }
    
    // Adicionar evento a aposta
    public void remEventoFromAposta(Evento e) {
        this.eventos.remove(e.getIdEvento(), e);
    }
}
